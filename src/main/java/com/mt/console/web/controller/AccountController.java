package com.mt.console.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.mt.console.aop.MRemark;
import com.mt.console.web.po.AdminMembership;
import com.mt.console.web.service.IAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "", tags = "账号模块")
@Controller
@RequestMapping("/account/*")
public class AccountController {

	@Autowired
	WebApplicationContext wc;

	@Autowired
	private IAccountService ias;

	@MRemark(remark = "后台帐号列表页")
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("Manager/Account/list");
	}

	@MRemark(number = "account-002", isMenu = 1, remark = "角色权限列表页")
	@ApiOperation(value = "角色", notes = "角色权限")
	@RequestMapping(value = "role", method = RequestMethod.GET)
	public ModelAndView role() {
		return new ModelAndView("Manager/Account/list_r");
	}

	@MRemark(number = "account-003", isMenu = 1, remark = "权限控制列表页")
	@RequestMapping(value = "permission", method = RequestMethod.GET)
	public ModelAndView permission() {
		return new ModelAndView("Manager/Account/list_p");
	}

	@MRemark(number = "account-005", remark = "改变帐号状态")
	@ApiOperation(value = "状态", notes = "帐号状态", response = AdminMembership.class, responseContainer = "Item", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "book's id", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "status", value = "book's status", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "a_tatus", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> accountStatus(@RequestParam String id, @RequestParam String status) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		ias.accountStatus(Long.valueOf(id), Integer.valueOf(status));
		map.put("success", true);
		return map;
	}

	@MRemark(number = "account-006", remark = "重新生成权限列表")
	@RequestMapping("reset_p")
	@ResponseBody
	public Map<String, Object> reset_p(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		ias.resetPermission(wc);
		map.put("success", "true");
		return map;
	}

	@MRemark(number = "account-007", remark = "改变角色权限状态")
	@RequestMapping("p_status")
	@ResponseBody
	public Map<String, Object> p_status(String number, String permission, String status) {
		Map<String, Object> map = new HashMap<String, Object>();
		ias.p_status(Integer.valueOf(number), permission, Integer.valueOf(status));
		map.put("success", "true");
		return map;
	}

}
