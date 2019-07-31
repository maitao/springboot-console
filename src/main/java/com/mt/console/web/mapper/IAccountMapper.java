package com.mt.console.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mt.console.web.po.Account;
import com.mt.console.web.po.Permission;
import com.mt.console.web.po.Role;
import com.mt.console.web.po.User;

@Mapper
public interface IAccountMapper extends IBaseMapper<Account, Long> {

	public void insertUser(User u);

	public void insertPermission(Permission p);

	public List<Account> checkAccountExit(@Param(value = "account") String account);

	public void updateAccountLoginCount(Account a);

	public void accountStatus(@Param(value = "id") long id, @Param(value = "status") int status);

	public Account selectAccount(@Param(value = "account") String account, @Param(value = "password") String password);

	public Role selectRole(@Param(value = "number") int number);

	public void deletePermission();

	public List<Map<String, Object>> list();

	public void save(Permission p);

	public void save(Role r);

	public void update(Role r);

	public void updateRole(Role r);

	public Map<String, Object> getStatus(String requestUrl);

	public List<Map<String, Object>> list_p();

	// 获取记录总数
	public int getAccountTotalCount(Map<String, Object> map);

	// 获取记录总数
	public int getRoleTotalCount(Map<String, Object> map);

	// 获取记录总数
	public int getPermissionTotalCount(Map<String, Object> map);

	// 获取分页记录
	public List<Map<String, Object>> getAccountPageList(Map<String, Object> map);

	// 获取分页记录
	public List<Map<String, Object>> getRolePageList(Map<String, Object> map);

	// 获取分页记录
	public List<Map<String, Object>> getPermissionPageList(Map<String, Object> map);

}
