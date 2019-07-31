package com.mt.console.web.service;

import org.springframework.web.context.WebApplicationContext;

import com.mt.console.web.po.Account;
import com.mt.console.web.po.AdminMembership;

public interface IAccountService {

	// 获取帐号
	public Account getAccount(String account, String pwd);

	// 保存帐号
	public String saveMembership(AdminMembership m);

	public void accountStatus(long id, int status);

	// 重置权限
	public void resetPermission(WebApplicationContext wc);

	// 修改权限状态
	public void p_status(int number, String permission, int status);

//	public PageList accountPageList(int pageNo, int pageCount, String condition);
//
//	public PageList permissionPageList(int pageNo, int pageCount, String condition);
//
//	public PageList permissionPageList_1(int pageNo, int pageCount, String condition);
//
//	public PageList rolePageList(int pageNo, int pageCount, String condition);

}
