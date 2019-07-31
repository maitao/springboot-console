package com.mt.console.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mt.console.constants.AdminSessionConstants;
import com.mt.console.util.IPKit;
import com.mt.console.web.po.AdminMembership;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final String USER_AGENT = "user-agent";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURL().toString();
		log.info("UserAgent: {}", request.getHeader(USER_AGENT));
		log.info("用户访问地址: {}, 来路地址: {}", url, IPKit.getIpAddrByRequest(request));
		if (null == (AdminMembership) request.getSession().getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP)) {
			response.sendRedirect("/console/login");
			return false;
			// 需要添加return 防止在centos：HTTP Status 500 - Request processing
			// failed; nested exception is java.lang.IllegalStateException:
			// Cannot call sendRedirect() after the response has been
			// committed
			// return true;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
