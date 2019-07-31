package com.mt.console.web.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.mt.console.web.po.QaArticle;
import com.mt.console.web.service.ICommonService;
import com.mt.console.web.service.IRecordService;

@Controller
@RequestMapping("/record/*")
public class RecordController {

	@Autowired
	private IRecordService rs;

	@Autowired
	private ICommonService cs;

	@MRemark(number = "record-001", isMenu = 1, remark = "记录首页")
	@RequestMapping(value = "/")
	public ModelAndView index() {
		return new ModelAndView("Manager/Record/index");
	}

	@MRemark(number = "record-002", remark = "记录保存")
	@RequestMapping(value = "save")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, @ModelAttribute QaArticle rd) {
		Map<String, Object> map = new HashMap<String, Object>(1);
		String account = (String) request.getSession().getAttribute(AdminSessionConstants.LOGIN_MEMBERSHIP_ACCOUNT);
		if (null == rd.getId()) {
			if (!cs.removeFormUuid(request.getSession(), request.getParameter("uuid"))) {
				map.put("msg", "hadSubmit");
			} else {
				rd.setCreateAccount(account);
				map.put("id", rs.add(rd));
			}
		} else {
			rd.setUpdateAccount(account);
			rs.update(rd);
			map.put("id", rd.getId());
			map.put("updateTime", new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(rd.getUpdateTime()));
		}
		return map;
	}

	@MRemark(remark = "记录查看")
	@RequestMapping(value = "articleView", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> articleView(HttpSession session, @RequestParam String id, String type) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		QaArticle tsMap = rs.view(Long.valueOf(id), "1".equals(type) ? true : false);
		map.put("data", tsMap);
		map.put("uuid", cs.addFormUuid(session));
		return map;
	}

	@MRemark(number = "record-004", remark = "记录标星")
	@RequestMapping(value = "addStar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addStar(@RequestParam String id, @RequestParam String level) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		rs.addStar(Long.valueOf(id), Integer.valueOf(level));
		map.put("level", level);
		return map;
	}

	@MRemark(number = "record-005", remark = "删除记录")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestParam String id, String key) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		rs.delete(Long.valueOf(id));
		map.put("success", true);
		return map;
	}

}
