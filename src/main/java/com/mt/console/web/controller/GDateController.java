package com.mt.console.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mt.console.aop.MRemark;
import com.mt.console.web.po.GDate;
import com.mt.console.web.service.IGDateService;

@Controller
@RequestMapping("/gdate/*")
public class GDateController {

	@Autowired
	private IGDateService igs;

	@MRemark(number = "gdate-001", remark = "Admin后台左侧菜单跳转页")
	@RequestMapping(value = "infoPage")
	public ModelAndView infoPage(@RequestParam String num) {
		ModelAndView mav = new ModelAndView("console/page/gdate-info");
		mav.addObject("num", num);
		int pnum = Integer.valueOf(num);
		GDate gdate = igs.getGDate(pnum);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy年MM月dd日");// 设置日期格式
		SimpleDateFormat df2 = new SimpleDateFormat("MMdd");// 设置日期格式
		String str1 = (String) gdate.getBirthdate();

		Date date = null;
		try {
			date = df.parse(str1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gdate.setBirthdate(df1.format(date));
		String str2 = df2.format(date);
		int i = Integer.valueOf(str2);
		String constellation = "";
		if (321 <= i && i <= 419) {
			constellation = "白羊座";
		}
		if (420 <= i && i <= 520) {
			constellation = "金牛座";
		}
		if (521 <= i && i <= 621) {
			constellation = "双子座";
		}
		if (622 <= i && i <= 722) {
			constellation = "巨蟹座";
		}
		if (723 <= i && i <= 822) {
			constellation = "狮子座";
		}
		if (823 <= i && i <= 922) {
			constellation = "处女座";
		}
		if (923 <= i && i <= 1023) {
			constellation = "天秤座";
		}
		if (1024 <= i && i <= 1121) {
			constellation = "天蝎座";
		}
		if (1122 <= i && i <= 1221) {
			constellation = "射手座";
		}
		if (1222 <= i && i <= 119) {
			constellation = "摩羯座";
		}
		if (120 <= i && i <= 218) {
			constellation = "宝瓶座";
		}
		if (219 <= i && i <= 320) {
			constellation = "双鱼座";
		}
		mav.addObject("constellation", constellation);
		// List gplist = gDateDao.getGPersonPic(pnum, jdbcTemplate);
		// mav.addObject("gperson", list);
		List<Object> list = igs.getGDatePic(pnum);
		mav.addObject("gdate", gdate);
		mav.addObject("picList", list);
		return mav;
	}

	@MRemark(number = "gdate-002", remark = "帐号保存")
	@RequestMapping("pinfo")
	@ResponseBody
	public Map<String, Object> pinfo(HttpServletRequest request, @RequestParam String num) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pinfo", num);
		return map;
	}
}
