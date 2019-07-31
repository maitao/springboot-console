package com.mt.console.web.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.console.web.mapper.ISpaceArticleMapper;
import com.mt.console.web.po.Category;
import com.mt.console.web.service.ISpaceService;

@Service("SpaceServiceImpl")
public class SpaceServiceImpl implements ISpaceService {


	@Autowired
	private ISpaceArticleMapper ism;

	@Override
	public List<Map<String, Object>> getCategory(String account){
		List<Map<String, Object>> list = ism.getCategory(account);
		return list;
	}

	@Override
	public Long addNode(String pId, String name, String account) {
		Category c = new Category();
		c.setParentId(Long.valueOf(pId));
		c.setName(name);
		c.setAccount(account);
		c.setCreateTime(new Timestamp(System.currentTimeMillis()));
		ism.addNode(c);
		return c.getId();
	}

	@Override
	public void renameNode(String id, String name, String account) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		map.put("account", account);
		map.put("updateTime", new Timestamp(System.currentTimeMillis()));
		ism.renameNode(map);
	}

	@Override
	public void removeNode(String pId, String id, String account) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pId", pId);
		map.put("id", id);
		map.put("account", account);
		map.put("updateTime", new Timestamp(System.currentTimeMillis()));
		ism.removeNode(map);
		
	}

}
