package com.mt.console.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.console.web.mapper.IGirlDateMapper;
import com.mt.console.web.po.GDate;
import com.mt.console.web.service.IGDateService;

@Service("GDateServiceImpl")
public class GDateServiceImpl implements IGDateService {
	
	@Autowired
	private IGirlDateMapper igm;

	@Override
	public GDate getGDate(int num) {
		return igm.getGDate(num);
	}

	@Override
	public List<Object> getGDatePic(int num) {
		return igm.getGDatePic(num);
	}

}
