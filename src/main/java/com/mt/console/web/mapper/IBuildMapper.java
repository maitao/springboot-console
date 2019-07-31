package com.mt.console.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mt.console.web.po.Menu;
import com.mt.console.web.po.Operation;
import com.mt.console.web.po.SysConf;

@Mapper
public interface IBuildMapper {

	public void deleteOperation();

	public void insertOperation(List<Operation> o);
	
	public void updateRoleOperation(Map<String, Object> map);

	public void deleteMenu();

	public void insertMenu(List<Menu> m);

	public void deleteSysConf();
	
	public void insertSysConf(List<SysConf> sc);
}
