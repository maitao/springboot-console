package com.mt.console.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.mt.console.aop.MRemark;
import com.mt.console.web.service.IBuildService;

/**
 * 构建初始化数据20161105
 * 
 * @author maitao
 *
 */
@Controller
@RequestMapping("/build/*")
public class BuildController {
	
	@Autowired
    WebApplicationContext wc;

	@Autowired
	private IBuildService ibs;

	@MRemark(remark = "生成admin系统配置")
	@RequestMapping("sysConf")
	@ResponseBody
	public Map<String, Object> sysConf(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		ibs.resetSysConf();
		map.put("success", "true");
		return map;
	}

	@MRemark(remark = "生成admin操作列表")
	@RequestMapping("operation")
	@ResponseBody
	public Map<String, Object> operation(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		ibs.resetOperation(wc);
		map.put("success", "true");
		return map;
	}

	@MRemark(remark = "生成admin左侧菜单列表")
	@RequestMapping("menu")
	@ResponseBody
	public Map<String, Object> menu(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		ibs.resetMenu();
		map.put("success", "true");
		return map;
	}
	
	@MRemark(remark = "生成admin左侧菜单列表")
	@RequestMapping("welltou_admin")
	@ResponseBody
	public Map<String, Object> welltouAdmin(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
//		ibs.welltouCopyToAdmin(Tool.getIpAddr(request));
		map.put("success", "true");
		return map;
	}

}
