package com.mt.console.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.jdbc.core.JdbcTemplate;

@Mapper
public interface ICommonDao {

	// 总结
	int count(JdbcTemplate jdbcTemplate, String tableName, Object[] objs);

	// 新增
	void save(JdbcTemplate jdbcTemplate, String sql, Object[] objs);

	// 删除
	void delete(JdbcTemplate jdbcTemplate, String sql, Object[] objs);

	// 修改
	void update(JdbcTemplate jdbcTemplate, String sql, Object[] objs);

	// 查询
	Map<String, Object> selectMap(JdbcTemplate jdbcTemplate, String sql, Object[] objs);

	// 查询
	List<Map<String, Object>> selectList(JdbcTemplate jdbcTemplate, String sql, Object[] objs);

}
