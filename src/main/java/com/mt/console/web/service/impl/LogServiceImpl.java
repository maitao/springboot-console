package com.mt.console.web.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.console.web.mapper.ILogMapper;
import com.mt.console.web.po.Log;
import com.mt.console.web.service.ABaseServiceImpl;
import com.mt.console.web.service.ILogService;

@Service("LogServiceImpl")
public class LogServiceImpl extends ABaseServiceImpl<Log, Serializable> implements ILogService {

	@Autowired
	private ILogMapper ilm;

	@Override
	public void log(long accountId, String account, String classPath, String methodName, String remark, String args, long useTime,
			Throwable ex) {
		Log log = new Log();
		long time = System.currentTimeMillis();
		log.setNumber(String.valueOf(time));
		log.setAccountId(accountId);
		log.setClassPath(classPath);
		log.setMethodName(methodName);
		log.setMethodRemark(remark);
		log.setArguments(args);
		log.setUseTime(useTime);
		StringBuffer sb = new StringBuffer();
		if (null != ex) {
			log.setLevel(1);
			StackTraceElement[] st = ex.getStackTrace();
			sb.setLength(0);
			if (st.length > 0) {
				sb.append(ex.toString() + "\n");
				for (StackTraceElement stackTraceElement : st) {
					sb.append(stackTraceElement.toString() + "\n");
				}
			}
			log.setErrorMsg(sb.toString());
		} else {
			log.setLevel(0);
		}
		log.setCreateTime(new Timestamp(time));
		ilm.insert(log);
	}

	@Override
	public void log(Log log) {
//		logger.info("记录日志");
		long time = System.currentTimeMillis();
		log.setNumber(String.valueOf(time));
		log.setCreateTime(new Timestamp(time));
		ilm.insert(log);
	}


}
