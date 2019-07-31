package com.mt.console.web.service;

import java.util.List;
import java.util.Map;

public interface ISpaceService {

	public List<Map<String, Object>> getCategory(String account);

	public Long addNode(String pId, String name, String account);
	
	public void renameNode(String id, String name, String account);
	
	public void removeNode(String pId, String id, String account);
}
