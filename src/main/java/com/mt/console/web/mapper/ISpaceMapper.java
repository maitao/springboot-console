package com.mt.console.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mt.console.web.po.Category;

@Mapper
public interface ISpaceMapper {


	public List<Map<String, Object>> getCategory(@Param(value = "account") String account);
	
	public void addNode(Category c);
	
	void renameNode(Map<String, Object> map);
	
	void removeNode(Map<String, Object> map);
	
}
