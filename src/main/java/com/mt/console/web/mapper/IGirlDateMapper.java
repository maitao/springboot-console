package com.mt.console.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mt.console.web.po.GDate;

@Mapper
public interface IGirlDateMapper {
	// 获取记录总数
	public int getTotalCount(Map<String, Object> map);

	// 获取分页记录
	public List<Map<String, Object>> getPageList(Map<String, Object> map);
	
	public GDate getGDate(@Param(value = "num") int num);
	
	public List<Object> getGDatePic(@Param(value = "num") int num);
}
