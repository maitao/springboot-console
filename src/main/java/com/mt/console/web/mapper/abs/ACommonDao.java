package com.mt.console.web.mapper.abs;

import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mt.console.util.PageList;


public abstract class ACommonDao {

	public int count(JdbcTemplate jdbcTemplate, String tableName, Object[] objs) {
		String sql = "select count(0) from " + tableName;
		return jdbcTemplate.queryForObject(sql, objs, Integer.class);
	}

	public void save(JdbcTemplate jdbcTemplate, String sql, Object[] objs) {
		jdbcTemplate.update(sql, objs);
	}

	public void delete(JdbcTemplate jdbcTemplate, String sql, Object[] objs) {
		jdbcTemplate.update(sql, objs);
	}

	public void update(JdbcTemplate jdbcTemplate, String sql, Object[] objs) {
		jdbcTemplate.update(sql, objs);
	}

	public List<Map<String, Object>> selectList(JdbcTemplate jdbcTemplate, String sql, Object[] objs) {
		return jdbcTemplate.queryForList(sql, objs);
	}

	public Map<String, Object> selectMap(JdbcTemplate jdbcTemplate, String sql, Object[] objs) {
		try {
			return jdbcTemplate.queryForMap(sql, objs);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public PageList pageList(JdbcTemplate jdbcTemplate, int pageNo, int pageCount, String querySql) {
		PageList pl = new PageList();
		return pl.findPageListBySql(jdbcTemplate, pageNo, pageCount, querySql);
	}

}
