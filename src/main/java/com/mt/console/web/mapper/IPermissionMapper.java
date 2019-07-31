package com.mt.console.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mt.console.web.po.Account;

@Mapper
public interface IPermissionMapper extends IBaseMapper<Account, Long> {
	// 获取分页记录
	public List<Map<String, Object>> getPageList_1(Map<String, Object> map);

}
