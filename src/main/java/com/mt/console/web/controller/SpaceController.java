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
import com.mt.console.web.service.ISpaceService;

@Controller
@RequestMapping("/space/*")
public class SpaceController {

	@Autowired
	private ISpaceService iss;

	@MRemark(remark = "菜单json")
	@RequestMapping(value = "category", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> json(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		AdminMembership am = (AdminMembership) session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP);
		String account = am.getAccount();
		List<Map<String, Object>> list = iss.getCategory(account);
		List<Map<String, Object>> zNodes = new ArrayList<Map<String, Object>>();

		for (Map<String, Object> m : list) {
			Map<String, Object> inner_m = new HashMap<String, Object>();
			if (0==(Integer) m.get("parent_id")) {
				Long id = (Long) m.get("id");
				inner_m.put("id", id);
				inner_m.put("pId", 0);
				inner_m.put("name", account);
				inner_m.put("icon", m.get("icon"));
				// inner_m.put("isParent", true);
				inner_m.put("open", true);
				inner_m.put("children", getChildren(list, id));
				zNodes.add(inner_m);
			}
		}
		map.put("menu", list);
		map.put("zNodes", zNodes);
		return map;
	}

	@MRemark(remark = "新增节点")
	@RequestMapping(value = "addNode", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addNode(HttpSession session, String pId, String name) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		AdminMembership am = (AdminMembership) session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP);
		map.put("id", iss.addNode(pId, name, am.getAccount()));
		return map;
	}
	
	@MRemark(remark = "重命名节点")
	@RequestMapping(value = "renameNode", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> renameNode(HttpSession session, String id, String name) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		AdminMembership am = (AdminMembership) session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP);
		iss.renameNode(id, name, am.getAccount());
		return map;
	}
	
	@MRemark(remark = "删除名节点")
	@RequestMapping(value = "removeNode", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removeNode(HttpSession session, String pId, String id) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		AdminMembership am = (AdminMembership) session.getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP);
		iss.removeNode(pId, id, am.getAccount());
		return map;
	}

	private List<Map<String, Object>> getChildren(List<Map<String, Object>> list, Long id) {
		List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> m : list) {
			Map<String, Object> inner_m = new HashMap<String, Object>();
			if (id==Long.valueOf(m.get("parent_id").toString())) {
				Long cid = (Long) m.get("id");
				inner_m.put("id", cid);
				inner_m.put("pId", id);
				inner_m.put("name", m.get("name"));
				inner_m.put("icon", m.get("icon"));
				inner_m.put("open", false);
				inner_m.put("children", getChildren(list, cid));
				children.add(inner_m);
			}
		}
		return children;
	}
}
