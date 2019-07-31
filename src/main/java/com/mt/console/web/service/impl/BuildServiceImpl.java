package com.mt.console.web.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.mt.console.aop.MRemark;
import com.mt.console.web.mapper.IBuildMapper;
import com.mt.console.web.po.Account;
import com.mt.console.web.po.Menu;
import com.mt.console.web.po.Operation;
import com.mt.console.web.po.SysConf;
import com.mt.console.web.service.ABaseServiceImpl;
import com.mt.console.web.service.IBuildService;

@Service("BuildServiceImpl")
public class BuildServiceImpl extends ABaseServiceImpl<Account, Serializable> implements IBuildService {

	@Autowired
	private IBuildMapper ibm;

	@Override
	public void resetSysConf() {

		ibm.deleteSysConf();

		List<Object[]> l = new ArrayList<Object[]>();
		l.add(new Object[] { 100, 0, "网站域名", "web_site", "www.wchot.com", "" });
		l.add(new Object[] { 101, 0, "网站名称", "web_name", "AdminMT", "" });
		l.add(new Object[] { 102, 0, "网站版本", "web_version", "1.0.1", "" });
		l.add(new Object[] { 103, 0, "网站运营时间", "web_in_time", "2014-2016", "" });
		l.add(new Object[] { 104, 0, "网站运营者信息", "web_in_user", "mai tao", "" });
		List<SysConf> scs = new ArrayList<SysConf>();
		for (Object[] objects : l) {
			SysConf sc = new SysConf();
			sc.setNumber((Integer) objects[0]);
			sc.setParentNum((Integer) objects[1]);
			sc.setName((String) objects[2]);
			sc.setKey((String) objects[3]);
			sc.setValue((String) objects[4]);
			sc.setRemark((String) objects[5]);
			sc.setCreateTime(new Timestamp(System.currentTimeMillis()));
			scs.add(sc);
		}
		ibm.insertSysConf(scs);
	}

	/**
	 * 全部更新操作列表
	 */
	@Override
	public void resetOperation(WebApplicationContext wc) {
		ibm.deleteOperation();
		RequestMappingHandlerMapping rmhp = wc.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> map_p = rmhp.getHandlerMethods();
		String temp = "";
		StringBuffer sb = new StringBuffer("");
		int i = 1, m = 1, n = 1;
		List<Operation> ops = new ArrayList<Operation>();
		for (RequestMappingInfo info : map_p.keySet()) {
			HandlerMethod hm = (HandlerMethod) map_p.get(info);
			String classPath = hm.getBeanType().getName();
			String methodName = hm.getMethod().getName();
			MRemark methodCache = hm.getMethod().getAnnotation(MRemark.class);
			String methodRemark = (null == methodCache) ? null : methodCache.remark();
			Operation op = new Operation();
			if (1 == i) {
				temp = classPath;
				++i;
				--n;
			}
			if (temp.equals(classPath)) {
				++n;
			} else {
				temp = classPath;
				++m;
				n = 1;
			}
			String t = m + "-" + n;
			op.setNumber(t);
			sb.append(t + ",");
			String name = info.getPatternsCondition().toString();
			op.setName(name.substring(1, name.length() - 1));
			op.setClassPath(classPath);
			op.setMethodName(methodName);
			op.setCreateTime(new Timestamp(System.currentTimeMillis()));
			op.setMethodRemark(methodRemark);
			ops.add(op);
		}
		ibm.insertOperation(ops);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("number", 0);
		map.put("operation", sb.toString().substring(0, sb.toString().length() - 1));
		map.put("updateTime", new Timestamp(System.currentTimeMillis()));
		ibm.updateRoleOperation(map);
	}

	@Override
	public void resetMenu() {

		ibm.deleteMenu();

		List<Object[]> l = new ArrayList<Object[]>();
		l.add(new Object[] { "01-01", "0-0", "主页", "", "fa fa-dashboard", "fa fa-angle-left pull-right" });
		l.add(new Object[] { "01-02", "01-01", "主面板", "menu/home", "fa fa-circle-o", "" });

		l.add(new Object[] { "02-01", "0-0", "活动管理", "", "fa fa-google-wallet text-orange",
				"fa fa-angle-left pull-right" });
		l.add(new Object[] { "02-02", "02-01", "网站活动", "", "fa fa-pie-chart", "fa fa-angle-left pull-right" });
		l.add(new Object[] { "02-03", "02-02", "一起‘约’女神", "menu/gdate", "fa fa-heart text-red", "" });
		l.add(new Object[] { "02-04", "02-01", "微信活动", "", "fa fa-wechat text-green", "fa fa-angle-left pull-right" });
		l.add(new Object[] { "02-05", "02-04", "“夏娃之秀”", "menu/xwzx", "fa fa-dashboard", "" });

		l.add(new Object[] { "05-01", "0-0", "网站管理", "", "fa fa-laptop text-gray", "fa fa-angle-left pull-right" });
		l.add(new Object[] { "05-02", "05-01", "聊天系统", "", "fa fa-tasks", "" });
		l.add(new Object[] { "05-03", "05-01", "企业网站", "", "fa fa-firefox text-red", "fa fa-angle-left pull-right" });
		l.add(new Object[] { "05-04", "05-03", "用户列表", "menu/membership", "fa fa-users", "" });
		l.add(new Object[] { "05-05", "05-03", "操作日志", "menu/log", "fa fa-clipboard", "" });
		l.add(new Object[] { "05-06", "05-03", "系统配置", "tableList/sysConf", "fa fa-wrench", "" });
		l.add(new Object[] { "05-07", "05-01", "门户网站", "", "fa fa-hourglass-2", "fa fa-angle-left pull-right" });
		l.add(new Object[] { "05-08", "05-07", "专题管理", "tableList/operation", "fa fa-list", "" });
		l.add(new Object[] { "05-09", "05-07", "资讯系统", "", "fa fa-tasks", "" });
		l.add(new Object[] { "05-10", "05-07", "问答系统", "", "fa fa-tasks", "" });
		l.add(new Object[] { "05-11", "05-07", "图片列表", "", "fa fa-clipboard", "" });

		l.add(new Object[] { "06-01", "0-0", "报表管理", "", "fa fa-bar-chart text-purple",
				"fa fa-angle-left pull-right" });
		l.add(new Object[] { "06-02", "06-01", "图表", "", "fa fa-pie-chart", "fa fa-angle-left pull-right" });
		l.add(new Object[] { "06-03", "06-02", "用户数据分析", "menu/userReport", "fa fa-dashboard", "" });

		l.add(new Object[] { "07-01", "0-0", "资源管理", "", "fa fa-registered", "" });
		l.add(new Object[] { "07-02", "07-01", "图片资源", "menu/image", "fa fa-picture-o", "" });
		l.add(new Object[] { "07-03", "07-01", "视频资源", "menu/video", "fa fa-video-camera", "" });
		l.add(new Object[] { "07-04", "07-01", "网站模板", "menu/templet", "fa fa-internet-explorer", "" });

		l.add(new Object[] { "08-01", "0-0", "控制台配置", "", "fa fa-cogs text-gray", "fa fa-angle-left pull-right" });
		l.add(new Object[] { "08-02", "08-01", "系统配置", "tableList/sysConf", "fa fa-wrench", "" });
		l.add(new Object[] { "08-03", "08-01", "用户权限管理", "", "fa fa-users", "fa fa-angle-left pull-right" });
		l.add(new Object[] { "08-04", "08-03", "用户列表", "menu/membership", "fa fa-users", "" });
		l.add(new Object[] { "08-05", "08-03", "角色管理", "tableList/role", "fa fa-list", "" });
		l.add(new Object[] { "08-06", "08-03", "菜单管理", "menu/menu", "fa fa-list-ul", "" });
		l.add(new Object[] { "08-07", "08-03", "操作管理", "tableList/operation", "fa fa-list", "" });
		l.add(new Object[] { "08-08", "08-01", "操作日志", "menu/log", "fa fa-clipboard", "" });

		l.add(new Object[] { "10-01", "0-0", "用户管理", "", "fa fa-users text-gray", "fa fa-angle-left pull-right" });
		l.add(new Object[] { "10-02", "10-01", "管理员列表", "wt/wtadmin", "fa fa-wrench", "" });
		l.add(new Object[] { "10-03", "10-01", "用户列表", "wt/wtuser", "fa fa-wrench", "" });
		l.add(new Object[] { "11-01", "0-0", "设备管理", "", "fa fa-location-arrow text-gray",
				"fa fa-angle-left pull-right" });
		l.add(new Object[] { "11-02", "11-01", "设备列表", "wt/wtdevice", "fa fa-wrench", "" });

		l.add(new Object[] { "15-01", "0-0", "说明文档", "menu/document", "fa fa-book", "" });

		List<Menu> menus = new ArrayList<Menu>();
		StringBuffer sb0 = new StringBuffer("");
		StringBuffer sb2 = new StringBuffer("");
		for (Object[] objects : l) {
			Menu menu = new Menu();
			String temp = (String) objects[0];
			menu.setNumber(temp);
			sb0.append(temp + ",");
			if (temp.contains("1-") || temp.contains("3-") || temp.contains("6-")) {
				sb2.append(temp + ",");
			}
			menu.setParentNum((String) objects[1]);
			menu.setName((String) objects[2]);
			menu.setShortUrl((String) objects[3]);
			menu.setLeftIcon((String) objects[4]);
			menu.setRightIcon((String) objects[5]);
			menu.setCreateTime(new Timestamp(System.currentTimeMillis()));
			menus.add(menu);
		}
		ibm.insertMenu(menus);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("number", 0);
		map.put("menu", sb0.toString().substring(0, sb0.toString().length() - 1));
		map.put("updateTime", new Timestamp(System.currentTimeMillis()));
		ibm.updateRoleOperation(map);
		map.clear();
		map.put("number", 2);
		map.put("menu", sb2.toString().substring(0, sb2.toString().length() - 1));
		map.put("updateTime", new Timestamp(System.currentTimeMillis()));
		ibm.updateRoleOperation(map);
	}

}
