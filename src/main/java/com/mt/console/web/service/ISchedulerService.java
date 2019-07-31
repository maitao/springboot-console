package com.mt.console.web.service;

import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;

import com.mt.console.util.PageList;

public interface ISchedulerService {

	public PageList schedulerList();

	public void addSched(Job s);

	public void deleteSched(Long id);

}
