package com.mt.console.web.service;

import java.util.List;
import java.util.Map;

import com.mt.console.web.po.AdminMembership;
import com.mt.console.web.po.AdminMembershipInfo;

public interface IAdminMembershipService {

	// 保存帐号
	public String registerAdminMembership(AdminMembership am);

	public AdminMembership getAdminMembership(AdminMembership am);

	public AdminMembershipInfo getAdminMembershipInfo(String account);

	List<Map<String, Object>> getAllAdminMembership();

	void addContactAdminMembership(String account, String toAccount);

	List<Map<String, Object>> getIMContact(String account);
	
}
