package com.mt.console.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mt.console.web.po.Setting;

@Mapper
public interface ICommonMapper {

	public List<Map<String, Object>> getSysConf();

	public Setting getSetting(@Param(value = "accountId") Long accountId);

	// 获取记录总数
	public int getTotalCount(Map<String, Object> map);

	// 获取分页记录
	public List<Map<String, Object>> getPageList(Map<String, Object> map);
}
