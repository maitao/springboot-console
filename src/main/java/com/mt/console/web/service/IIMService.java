package com.mt.console.web.service;

import java.util.List;
import java.util.Map;

import com.mt.console.web.po.IM;

public interface IIMService {

	public void saveMsg(IM im);

	public void updateMsg(String datetime);

	public List<IM> getMsg(String account);

	List<Map<String, Object>> getIMUnReachNum(String account);

	public List<IM> getUnReachMsg(String fromAccount, String toAccount);

	public List<IM> getIMContactHistory(String fromAccount, String toAccount, int limitNum);
}
