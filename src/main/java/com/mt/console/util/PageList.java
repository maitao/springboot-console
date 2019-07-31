package com.mt.console.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class PageList {

	public static final PageList EMPTY_PAGE = new PageList() {
		public int getTotalPageCount() {
			return 0;
		}
	};
	/** 当前页的大小 */
	private int currentPageSize;

	/** 总共的条数 */
	private int totalSize;

	/** 当前页 */
	private int currentPageNo;

	/** 分页数据 */
	private List<Map<String, Object>> dataList;

	public PageList() {
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getCurrentPageSize() {
		return currentPageSize;
	}

	public void setCurrentPageSize(int currentPageSize) {
		this.currentPageSize = currentPageSize;
	}

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getTotalPageCount() {
		return (getTotalSize() - 1) / getCurrentPageSize() + 1;
	}

	public boolean hasNextPage() {
		return getCurrentPageNo() < getTotalPageCount();
	}

	public boolean hasPreviousPage() {
		return getCurrentPageNo() > 1;
	}

	public boolean isEmpty() {
		return this == EMPTY_PAGE;
	}

	public PageList findPageListBySql(JdbcTemplate jdbcTemplate, int pageNo, int pageCount, String querySql) {
		String countSql = "select count(1) from (" + querySql + ")a ";
		PageList PageList = null;
		try {
			if (querySql != null) {
				int firstResultIndex = (pageNo - 1) * pageCount;
				int totalCount = jdbcTemplate.queryForObject(countSql,Integer.class);
				if (totalCount == 0 || firstResultIndex > totalCount) {
					PageList = EMPTY_PAGE;
					PageList.setCurrentPageNo(pageNo);
					PageList.setCurrentPageSize(pageCount);
					PageList.setDataList(new ArrayList<Map<String, Object>>());
				} else {
					String sql = "select * from (" + querySql + ") a limit " + firstResultIndex + "," + pageCount;
					List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
					PageList = new PageList();
					PageList.setCurrentPageNo(pageNo);
					PageList.setCurrentPageSize(pageCount);
					PageList.setTotalSize(totalCount);
					PageList.setDataList(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return EMPTY_PAGE;
		} finally {
		}
		return PageList;
	}
}
