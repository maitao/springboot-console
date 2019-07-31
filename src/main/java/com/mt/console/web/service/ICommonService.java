package com.mt.console.web.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.mt.console.util.PageList;
import com.mt.console.web.po.Setting;

public interface ICommonService {

	public String addFormUuid(HttpSession session);
	
	public boolean removeFormUuid(HttpSession session, String uuid);
	
	public <S> PageList getPageList(String key, int pageNo, int pageCount, String condition);

	/**
	 * 获取系统参数
	 * 
	 * @return
	 */
	public Map<String, Object> sysConf();

	public Setting getSetting(Long accountId);

	public Map<String, Object> getXWZXCount();
}