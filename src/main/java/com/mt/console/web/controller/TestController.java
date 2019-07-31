package com.mt.console.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mt.console.aop.MRemark;

@Controller
@RequestMapping("/test/*")
public class TestController {

	@MRemark(number = "test-001", isMenu = 1, remark = "支付宝测试首页")
	@RequestMapping(value = "test1")
	public ModelAndView test1() {
		return new ModelAndView("Test/test1");
	}

	@MRemark(number = "test-002", isMenu = 1, remark = "微信测试首页")
	@RequestMapping(value = "test2")
	public ModelAndView test2() {
		return new ModelAndView("Test/test2");
	}
	
	@MRemark(number = "test-003", isMenu = 1, remark = "多标签页")
	@RequestMapping(value = "test3")
	public ModelAndView test3() {
		return new ModelAndView("Admin/Common/home");
	}
	
	@MRemark(number = "test-004", isMenu = 1, remark = "左侧导航")
	@RequestMapping(value = "test4")
	public ModelAndView test4() {
		return new ModelAndView("Admin/Common/left");
	}
}
