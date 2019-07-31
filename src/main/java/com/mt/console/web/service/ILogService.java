package com.mt.console.web.service;

import com.mt.console.web.po.Log;

public interface ILogService {

	public void log(long accountId, String account, String classPath, String methodName, String remark, String args, long useTime,
			Throwable ex);

	public void log(Log log);
}
