package com.mt.console.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mt.console.web.po.Account;

@Mapper
public interface IRoleMapper extends IBaseMapper<Account, Long> {

	public String getPermission(@Param(value = "id") long id);

}
