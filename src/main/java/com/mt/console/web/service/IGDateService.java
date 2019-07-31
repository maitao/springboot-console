package com.mt.console.web.service;

import java.util.List;

import com.mt.console.web.po.GDate;

public interface IGDateService {
	
	public GDate getGDate(int num);
	
	public List<Object> getGDatePic(int num);
	
}
