package com.mt.console.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mt.console.aop.MRemark;
import com.mt.console.constants.AdminSessionConstants;
import com.mt.console.util.IPKit;
import com.mt.console.util.Tools;
import com.mt.console.util.algorithm.encryption.base64.Base64;
import com.mt.console.util.identifyingcode.SCaptcha;
import com.mt.console.web.po.AdminMembership;
import com.mt.console.web.po.AdminMembershipInfo;
import com.mt.console.web.service.IAdminMembershipService;
import com.mt.console.web.service.ICommonService;
import com.mt.console.web.service.IMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value="", tags="console登陆注册主页模块")
@Controller
public class ConsoleController {

	@Autowired
	private IAdminMembershipService iams;
	@Autowired
	private IMenuService ims;
	@Autowired
	private ICommonService ics;

	@RequestMapping(value = "/")
	public String index(HttpSession session) {
		if (null == (AdminMembership) session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP)) {
			return "console/login";
		} else {
			return "console/index";
		}
		
	}
	
	@MRemark(remark = "管理后台首页")
	@RequestMapping(value = "/console")
	public String admin(HttpSession session) {
		session.setAttribute("currentPage", session.getAttribute(AdminSessionConstants.SESSION_CURRENT_PAGE));
		ModelAndView mav = new ModelAndView("console/index");
		mav.addObject("aminfo",
				(AdminMembershipInfo) session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP_INFO));
		return "console/index";
	}

	@MRemark(remark = "登录前的cookie检测")
	@RequestMapping(value = "/console/cookie", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cookie(HttpSession session, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				System.out.println("cookies name: " + cookies[i].getName());
				if (AdminSessionConstants.LOGIN_ACCOUNT_ENCRYPTION.equals(cookies[i].getName())) {
					map.put("account", Base64.getFromBase64(cookies[i].getValue()));
				}
			}
		}
		return map;
	}

	@MRemark(remark = "跳转到登录页")
	@ApiOperation(value="跳转登陆页", notes = "登陆页")
	@RequestMapping(value = "/console/login")
	public String login() {
		return "console/login";
	}

	@MRemark(remark = "跳转到注册页")
	@RequestMapping(value = "/console/register")
	public String register() {
		return "console/register";
	}

	@MRemark(remark = "注册")
	@RequestMapping(value = "/console/reg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveReg(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("AdminMembership") AdminMembership am, @RequestParam String repassword, String remember) {
		Map<String, Object> map = new HashMap<String, Object>();
		am.setRegIp(IPKit.getIpAddrByRequest(request));
		if (StringUtils.isBlank(am.getName())) {
			map.put("msg", "用户名不能为空！");
		} else if (StringUtils.isBlank(am.getAccount())) {
			map.put("msg", "手机号或邮箱不能为空！");
		} else if (!Tools.isPhone(am.getAccount()) && !Tools.isEmail(am.getAccount())) {
			map.put("msg", "手机号或邮箱格式不正确！");
		} else if (!am.getPassword().equals(repassword)) {
			map.put("msg", "两次输入密码不一致！");
		} else {
			String str = iams.registerAdminMembership(am); // ias.saveMembership(am);
			if (StringUtils.isNotBlank(str)) {
				map.put("msg", str);
			}
		}
		return map;
	}

	@MRemark(remark = "登录，需要验证码")
	@ApiOperation(value="登录", notes = "用户登录")
    @ApiImplicitParam(name="AdminMembership", value="管理用户实体", required = true, dataType = "AdminMembership")
	@RequestMapping(value = "/console/logon", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> logon(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			AdminMembership am, String remember) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (Tools.isPhone(am.getAccount()) || Tools.isEmail(am.getAccount())) {
			// 记录登录次数，登录3次不成功
			Object logonCount = session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP_LOGON_COUNT);
			if (null == logonCount) {
				session.setAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP_LOGON_COUNT, 1);
			} else {
				session.setAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP_LOGON_COUNT, (Integer) logonCount + 1);
			}
			am.setLastLoginIp(IPKit.getIpAddrByRequest(request));
			am = iams.getAdminMembership(am);
			if (null != am) {
				if (0 == am.getStatus()) {
					map.put("msg", "帐号已经停用！");
				} else {
					// 保存登录帐号到session
					session.setMaxInactiveInterval(60 * 60);
					session.setAttribute(AdminSessionConstants.MT_SYS_CONF, ics.sysConf());
					session.setAttribute(AdminSessionConstants.MT_USER_SETTING, ics.getSetting(am.getId()));
					session.setAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP_INFO,
							iams.getAdminMembershipInfo(am.getAccount()));
					session.setAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP, am);
					session.setAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP_ACCOUNT, am.getAccount());
					// 保存到cookies
					if ("on".equals(remember)) {
						// 保存登录帐号MD5加密值到cookie
						Cookie accountCookie = new Cookie(AdminSessionConstants.LOGIN_ACCOUNT_ENCRYPTION,
								Base64.getBase64(am.getAccount()));
						accountCookie.setMaxAge(60 * 60 * 24 * 3);
						response.addCookie(accountCookie);
					} else {
						Cookie accountCookie = new Cookie(AdminSessionConstants.LOGIN_ACCOUNT_ENCRYPTION, null);
						accountCookie.setMaxAge(0);
						response.addCookie(accountCookie);
					}
				}
			} else {
				map.put("msg", "帐号或密码不正确！");
				map.put("logonCount", session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP_LOGON_COUNT));
			}
		} else {
			map.put("msg", "帐号格式不正确（手机号、邮箱）！");
		}
		return map;
	}

	@MRemark(remark = "退出平台")
	@RequestMapping(value = "/console/logout")
	public String logout(HttpSession session, HttpServletResponse response) throws IOException {
		if (null != session) {
			session.invalidate();
		}
		return "console/login";// new ModelAndView("redirect:/membership/logon");
		// return response.sendRedirect("/web/membership/login");
	}

	@MRemark(remark = "会员权限菜单")
	@RequestMapping(value = "/console/init", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> init(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		AdminMembership am = (AdminMembership) session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP);
		List<Map<String, Object>> list = ims.getMenu(am.getRoleNum());
		map.put("menu", list);
		map.put("currentPage", session.getAttribute(AdminSessionConstants.SESSION_CURRENT_PAGE));
		map.put("am", (AdminMembership) session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP));
		map.put("aminfo", (AdminMembershipInfo) session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP_INFO));
		return map;
	}

	@MRemark(remark = "会员权限菜单")
	@RequestMapping(value = "/console/profile", method = RequestMethod.GET)
	public ModelAndView resume(HttpSession session) {
		session.setAttribute(AdminSessionConstants.SESSION_CURRENT_PAGE, "console/profile");
		ModelAndView mav = new ModelAndView("console/page/profile");
		mav.addObject("am", (AdminMembership) session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP));
		AdminMembershipInfo amInfo = (AdminMembershipInfo) session
				.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP_INFO);
		mav.addObject("aminfo", amInfo);
		mav.addObject("skills", null == amInfo.getSkills() ? "" : amInfo.getSkills().split("\\s+"));
		return mav;
	}

	@MRemark(remark = "个人空间")
	@RequestMapping(value = "/console/space", method = RequestMethod.GET)
	public ModelAndView space(HttpSession session) {
		session.setAttribute(AdminSessionConstants.SESSION_CURRENT_PAGE, "console/space");
		ModelAndView mav = new ModelAndView("console/page/space");
		mav.addObject("am", (AdminMembership) session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP));
		AdminMembershipInfo amInfo = (AdminMembershipInfo) session
				.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP_INFO);
		mav.addObject("aminfo", amInfo);
		return mav;
	}

	@MRemark(remark = "个人任务")
	@RequestMapping(value = "/console/schedule", method = RequestMethod.GET)
	public ModelAndView schedule(HttpSession session) {
		session.setAttribute(AdminSessionConstants.SESSION_CURRENT_PAGE, "console/schedule");
		ModelAndView mav = new ModelAndView("console/page/schedule");
		mav.addObject("am", (AdminMembership) session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP));
		AdminMembershipInfo amInfo = (AdminMembershipInfo) session
				.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP_INFO);
		mav.addObject("aminfo", amInfo);
		return mav;
	}

	@MRemark(remark = "管理页面")
	@RequestMapping(value = "/console/manager", method = RequestMethod.GET)
	public ModelAndView manager(HttpSession session) {
		session.setAttribute(AdminSessionConstants.SESSION_CURRENT_PAGE, "console/manager");
		ModelAndView mav = new ModelAndView("console/page/manager");
		mav.addObject("am", (AdminMembership) session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP));
		AdminMembershipInfo amInfo = (AdminMembershipInfo) session
				.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP_INFO);
		mav.addObject("aminfo", amInfo);
		return mav;
	}

	@MRemark(remark = "登录验证码")
	@RequestMapping(value = "/console/verifycode")
	public void verifycode(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		SCaptcha sCaptcha = new SCaptcha(100, 40);
		session.setAttribute(AdminSessionConstants.MEMBERSHIP_VERIFYCODE, sCaptcha.getCode());
		System.out.println("verifycode= " + sCaptcha.getCode());
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		sCaptcha.write(response.getOutputStream());
	}
}
