package com.mt.console.web.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IBaseMapper<T, ID extends Serializable> {

	// 增
	int insert(T t);

	// 删
	void delete(T t);

	void deleteById(@Param(value = "id") long id);

	// 改
	void update(T t);

	void updateCount(T t);

	// 查
	List<T> select(T t);

	T selectById(@Param(value = "id") long id);

	// 获取记录总数
	public int getTotalCount(Map<String, Object> map);

	// 获取分页记录
	public List<Map<String, Object>> getPageList(Map<String, Object> map);

}
