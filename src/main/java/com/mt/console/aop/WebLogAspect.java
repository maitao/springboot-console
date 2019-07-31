package com.mt.console.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.collect.Lists;
import com.mt.console.util.IllegalStrFilterUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Web层日志切面
 *
 * @author maitao
 * @version 1.0.0
 * @date 19/4/11 上午14:34.
 * @blog
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class WebLogAspect {

	SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");

	@Autowired
	private MongoTemplate mongoTemplate;

	@Pointcut("execution(public * com.mt.console.web..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
	}

	@AfterReturning(value = "webLog()", returning = "ret")
	public void doAfterReturning(Object ret) throws Throwable {
	}

	@Around("webLog()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		// 获取HttpServletRequest
		long startTime = System.currentTimeMillis();
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		Object result = null;
		try {
			// 处理入参特殊字符和sql注入攻击
			checkRequestParam(joinPoint);

			result = joinPoint.proceed();
			Signature signature = joinPoint.getSignature();
			MethodSignature methodSignature = (MethodSignature) signature;
			Method method = methodSignature.getMethod();

			// 获取要记录的日志内容
			if (mongoTemplate != null) {
				Map<String, Object> data = getMap(request, joinPoint, method);
				long endTime = System.currentTimeMillis();
				data.put("spendTime", (int) (endTime - startTime));
				mongoTemplate.insert(data, "console-request-log");
			}
		} catch (Exception throwable) {
			log.error("WebLogAspect Exception: {}", throwable.getMessage());
		}
		return result;
	}

	private Map<String, Object> getMap(HttpServletRequest request, ProceedingJoinPoint joinPoint, Method method) {
		// 基本信息
		Map<String, Object> r = new HashMap<String, Object>();
		r.put("time", ft.format(new Date()));
		r.put("requestURL", request.getRequestURL().toString());
		log.info("WebLogAspect: {}", request.getRequestURL().toString());
		r.put("requestURI", request.getRequestURI());
		r.put("queryString", request.getQueryString());
		r.put("remoteAddr", request.getRemoteAddr());
		r.put("remoteHost", request.getRemoteHost());
		r.put("remotePort", request.getRemotePort());
		r.put("localAddr", request.getLocalAddr());
		r.put("localName", request.getLocalName());
		r.put("method", request.getMethod());
		r.put("headers", getHeadersInfo(request));
		r.put("parameters", request.getParameterMap());
		r.put("parameters1", getParameter(method, joinPoint.getArgs()));
		r.put("classMethod",
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		r.put("args", Arrays.toString(joinPoint.getArgs()));
		return r;
	}

	/**
	 * 获取头信息
	 *
	 * @param request
	 * @return
	 */
	private Map<String, String> getHeadersInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 根据方法和传入的参数获取请求参数
	 */
	private Object getParameter(Method method, Object[] args) {
		List<Object> argList = new ArrayList<>();
		Parameter[] parameters = method.getParameters();
		for (int i = 0; i < parameters.length; i++) {
			// 将RequestBody注解修饰的参数作为请求参数
			RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
			if (requestBody != null) {
				argList.add(args[i]);
			}
			// 将RequestParam注解修饰的参数作为请求参数
			RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
			if (requestParam != null) {
				Map<String, Object> map = new HashMap<>();
				String key = parameters[i].getName();
				if (!StringUtils.isEmpty(requestParam.value())) {
					key = requestParam.value();
				}
				map.put(key, args[i]);
				argList.add(map);
			}
		}
		if (argList.size() == 0) {
			return null;
		} else if (argList.size() == 1) {
			return argList.get(0);
		} else {
			return argList;
		}
	}

	/**
	 * @Author: maitao
	 * @Description: 处理入参特殊字符和sql注入攻击
	 * @Date: 8:33 2019/7/27
	 */
	private void checkRequestParam(ProceedingJoinPoint pjp) {
		String str = String.valueOf(pjp.getArgs());
		if (!IllegalStrFilterUtil.sqlStrFilter(str)) {
			log.info("访问接口：" + pjp.getSignature() + "，输入参数存在SQL注入风险！参数为："
					+ Lists.newArrayList(pjp.getArgs()).toString());
		}
		if (!IllegalStrFilterUtil.isIllegalStr(str)) {
			log.info("访问接口：" + pjp.getSignature() + ",输入参数含有非法字符!，参数为：" + Lists.newArrayList(pjp.getArgs()).toString());
		}
	}

}
