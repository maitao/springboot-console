package com.mt.console.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mt.console.web.po.AdminMembership;
import com.mt.console.web.po.AdminMembershipInfo;
import com.mt.console.web.po.IMContact;

@Mapper
public interface IAdminMembershipMapper {

	public Integer checkAccountExit(@Param(value = "account") String account);

	public Long insertAdminMembership(AdminMembership am);

	public void insertAdminMembershipInfo(AdminMembershipInfo ami);

	public AdminMembership selectAdminMembership(AdminMembership am);

	public AdminMembershipInfo selectAdminMembershipInfo(@Param(value = "account") String account);

	public void updateAdminMembership(AdminMembership am);

	// 获取记录总数
	public int getTotalCount(Map<String, Object> map);

	// 获取分页记录
	public List<Map<String, Object>> getPageList(Map<String, Object> map);

	public List<Map<String, Object>> getAllAdminMembership();

	public List<Map<String, Object>> getIMContact(@Param(value = "account") String account);

	public void insertContactAdminMembership(IMContact c);

}
