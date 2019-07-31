package com.mt.console.web.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.mt.console.aop.MRemark;
import com.mt.console.util.Tools;
import com.mt.console.util.algorithm.PasswordCrypt;
import com.mt.console.web.mapper.IAccountMapper;
import com.mt.console.web.mapper.IAdminMembershipMapper;
import com.mt.console.web.po.Account;
import com.mt.console.web.po.AdminMembership;
import com.mt.console.web.po.AdminMembershipInfo;
import com.mt.console.web.po.Permission;
import com.mt.console.web.po.Role;
import com.mt.console.web.service.ABaseServiceImpl;
import com.mt.console.web.service.IAccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("AccountServiceImpl")
public class AccountServiceImpl extends ABaseServiceImpl<Account, Serializable> implements IAccountService {

	@Autowired
	private IAccountMapper iam;

	@Autowired
	private IAdminMembershipMapper imm;
	
	@Transactional
	@Override
	//@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveMembership(AdminMembership m) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		m.setCreateTime(time);
		String pw = m.getPassword();
		m.setPassword(PasswordCrypt.crypt(m.getAccount(), m.getPassword()));
		m.setPlatform("welltou_user");
		int count = imm.checkAccountExit(m.getAccount());
		if (count == 0) {
			// id为返回成功的行数
			imm.insertAdminMembership(m);
			AdminMembershipInfo ami = new AdminMembershipInfo();
			ami.setAccount(m.getAccount());
			if(Tools.isPhone(m.getAccount())){
				ami.setPhone(m.getAccount());
			} else if(Tools.isEmail(m.getAccount())){
				ami.setEmail(m.getAccount());
			}
			ami.setCreateTime(time);
			ami.setRemark(pw);
			imm.insertAdminMembershipInfo(ami);
			
			return "";
		} else {
			log.info("帐号重复");
			return "帐号重复";
		}
	}

	@Override
	public Account getAccount(String account, String pwd) {
		Account a = iam.selectAccount(account, PasswordCrypt.crypt(account, pwd));
		if (null == a)
			return null;
		a.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		iam.updateAccountLoginCount(a);
		return a;
	}

	@Override
	public void p_status(int number, String permission, int status) {
		Role role = iam.selectRole(number);
		String p = role.getPermission();
		if (1 == status) {
			String strs[] = p.split(",");
			List<String> list = new ArrayList<String>();
			Collections.addAll(list, strs);
			list.add(permission);
			Collections.sort(list, Collator.getInstance(java.util.Locale.CHINA));
			StringBuffer sb = new StringBuffer();
			for (String string : list) {
				sb.append(string + ",");
			}
			permission = sb.toString();
			permission = permission.substring(0, permission.length() - 1);
		} else {
			if (p.indexOf(permission) == 0) {
				permission = p.replace(permission, "");
			} else {
				permission = p.replace("," + permission, "");
			}
		}
		Role r = new Role();
		r.setNumber(number);
		r.setPermission(permission);
		r.setUpdateTime(new Timestamp(System.currentTimeMillis()));
	}

	@Override
	public void resetPermission(WebApplicationContext wc) {
		iam.deletePermission();
		RequestMappingHandlerMapping rmhp = wc.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> map_p = rmhp.getHandlerMethods();
		String temp = "";
		int i = 1, m = 1, n = 1;
		String permission = "";
		for (RequestMappingInfo info : map_p.keySet()) {
			HandlerMethod hm = (HandlerMethod) map_p.get(info);
			String classPath = hm.getBeanType().getName();
			String methodName = hm.getMethod().getName();
			MRemark methodCache = hm.getMethod().getAnnotation(MRemark.class);
			String number = (null == methodCache) ? null : methodCache.number();
			String methodRemark = (null == methodCache) ? null : methodCache.remark();
			int isMenu = (null == methodCache) ? 0 : methodCache.isMenu();
			int isAjax = (null == hm.getMethod().getAnnotation(ResponseBody.class)) ? 0 : 1;
			Permission p = new Permission();
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
			p.setNumber(number);
			String name = info.getPatternsCondition().toString();
			p.setName(name.substring(1, name.length() - 1));
			p.setMethodNum(m + "-" + n);
			p.setClassPath(classPath);
			p.setMethodName(methodName);
			p.setIsAjax(isAjax);
			p.setIsMenu(isMenu);
			p.setStatus(1);
			p.setCreateTime(new Timestamp(System.currentTimeMillis()));
			p.setMethodRemark(methodRemark);
			iam.insertPermission(p);
			permission += (number + ",");

		}
		Role r = new Role();
		r.setPermission(permission.substring(0, permission.length() - 1));
		r.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		r.setNumber(1);
		iam.updateRole(r);

	}

	// @Override
	// public PageList accountPageList(int pageNo, int pageCount, String
	// condition) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// return getPageList(iam, pageNo, pageCount, map);
	//
	// }
	//
	// @Override
	// public PageList rolePageList(int pageNo, int pageCount, String condition)
	// {
	// Map<String, Object> map = new HashMap<String, Object>();
	// return getPageList(irm, pageNo, pageCount, map);
	// }
	//
	// @Override
	// public PageList permissionPageList(int pageNo, int pageCount, String
	// condition) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("permission_type", 0);
	// return getPageList(ipm, pageNo, pageCount, map);
	// }
	//
	// @Override
	// public PageList permissionPageList_1(int pageNo, int pageCount, String
	// condition) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("permission_type", 1);
	// return getPageList(ipm, pageNo, pageCount, map);
	// }

	@Override
	public void accountStatus(long id, int status) {
		iam.accountStatus(id, status);
	}

}
