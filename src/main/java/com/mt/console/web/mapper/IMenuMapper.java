package com.mt.console.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mt.console.web.po.Log;

@Mapper
public interface IMenuMapper extends IBaseMapper<Log, Long> {

	public String getRoleMemu(@Param(value = "roleNum") int roleNum);

	public List<Map<String, Object>> getMenu(@Param(value = "menu") String[] menu);

}
