package com.mt.console.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mt.console.aop.MRemark;
import com.mt.console.constants.AdminSessionConstants;
import com.mt.console.web.po.AdminMembership;
import com.mt.console.web.service.IMenuService;

@Controller
public class MenuController {

	@Autowired
	private IMenuService ims;

	@MRemark(remark = "会员权限菜单")
	@RequestMapping(value = "membership/menu", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> menu(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		AdminMembership am = (AdminMembership) session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP);
		List<Map<String, Object>> list = ims.getMenu(am.getRoleNum());
		map.put("menu", list);
		return map;
	}

	@MRemark(remark = "菜单json")
	@RequestMapping(value = "menu/json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> json(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		AdminMembership am = (AdminMembership) session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP);
		List<Map<String, Object>> list = ims.getMenu(am.getRoleNum());
		List<Map<String, Object>> zNodes = new ArrayList<Map<String, Object>>();

		for (Map<String, Object> m : list) {
			Map<String, Object> inner_m = new HashMap<String, Object>();
			if ("0-0".equals((String) m.get("parent_num"))) {
				inner_m.put("name", m.get("name"));
//				inner_m.put("isParent", true);
				inner_m.put("open", true);
				inner_m.put("children", getChildren(list, (String) m.get("number")));
				zNodes.add(inner_m);
			}
		}

		map.put("menu", list);
		map.put("zNodes", zNodes);
		return map;
	}

	private List<Map<String, Object>> getChildren(List<Map<String, Object>> list, String num) {
		List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> m : list) {
			Map<String, Object> inner_m = new HashMap<String, Object>();
			if (num.equals((String) m.get("parent_num"))) {
				inner_m.put("name", m.get("name"));
//				inner_m.put("isParent", true);
				inner_m.put("open", true);
				inner_m.put("children", getChildren(list, (String) m.get("number")));
				children.add(inner_m);
			}
		}
		return children;
	}
}
