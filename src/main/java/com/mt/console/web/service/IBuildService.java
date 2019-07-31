package com.mt.console.web.service;

import org.springframework.web.context.WebApplicationContext;

public interface IBuildService {

	public void resetOperation(WebApplicationContext wc);
	
	public void resetMenu();
	
	public void resetSysConf();
	
}
