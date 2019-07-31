package com.mt.console.web.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.console.web.mapper.IMenuMapper;
import com.mt.console.web.po.Menu;
import com.mt.console.web.service.ABaseServiceImpl;
import com.mt.console.web.service.IMenuService;

@Service("MenuServiceImpl")
public class MenuServiceImpl extends ABaseServiceImpl<Menu, Serializable> implements IMenuService {


	@Autowired
	private IMenuMapper imm;

	@Override
	public List<Map<String, Object>> getMenu(int roleNum) {
		String menu = imm.getRoleMemu(roleNum);
		String[] ss = menu.split(",");
		StringBuffer sb = new StringBuffer("");
		for (String string : ss) {
			sb.append("'" + string + "',");
		}
		List<Map<String, Object>> list = imm.getMenu(ss);
		return list;
	}

}
