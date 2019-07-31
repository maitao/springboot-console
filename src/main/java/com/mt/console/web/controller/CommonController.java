package com.mt.console.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mt.console.aop.MRemark;
import com.mt.console.constants.AdminSessionConstants;
import com.mt.console.web.service.ICommonService;

@Controller
public class CommonController {
	
	@Autowired
	private ICommonService ics;

	/**
	 * ajax获取uuid，防止重提交，第一次滑动出新增页面时保存值到session，每次保存都更换值
	 * 
	 * @param request
	 * @param type
	 * @return
	 */
	@MRemark(remark = "获取表单提交前uuid")
	@RequestMapping(value = "common/formUuid", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> formUuid(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>(1);
		map.put("uuid", ics.addFormUuid(session));
		return map;
	}

	@MRemark(remark = "Admin后台左侧菜单跳转页")
	@RequestMapping(value = "menu/{path}", method = { RequestMethod.GET })
	public String menu(HttpSession session, @PathVariable("path") String path) {
		session.setAttribute(AdminSessionConstants.SESSION_CURRENT_PAGE, "menu/" + path);
		return "console/page/" + path;
	}
	
	@MRemark(remark = "welltou_Admin后台左侧菜单跳转页")
	@RequestMapping(value = "wt/{path}", method = { RequestMethod.GET })
	public ModelAndView welltouMenu(HttpSession session, @PathVariable("path") String path) {
		session.setAttribute(AdminSessionConstants.SESSION_CURRENT_PAGE, "welltou/" + path);
		return new ModelAndView("Admin/page/welltou/" + path);
	}

	@MRemark(remark = "Admin后台左侧菜单跳转页")
	@RequestMapping(value = "tableList/{path}", method = { RequestMethod.GET })
	public ModelAndView tableList(HttpSession session, @PathVariable("path") String path) {
		session.setAttribute(AdminSessionConstants.SESSION_CURRENT_PAGE, "tableList/" + path);
		return new ModelAndView("Admin/page/tableList");
	}

	@MRemark(remark = "获取分页显示数据")
	@RequestMapping(value = "dataList/{key}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> dataList(@PathVariable("key") String key, @RequestParam Integer pageNo,
			@RequestParam Integer pageCount, String condition) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("data", ics.getPageList(key, pageNo, pageCount, condition));
		return map;
	}

	@MRemark(remark = "夏娃之秀统计数据")
	@RequestMapping(value = "xwzx/count", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> xwzx() {
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("data", ics.getXWZXCount());
		return map;
	}

}
