package com.mt.console.web.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.console.web.mapper.IIMMapper;
import com.mt.console.web.po.IM;
import com.mt.console.web.service.IIMService;

@Service("IMServiceImpl")
public class IMServiceImpl implements IIMService {

	@Autowired
	private IIMMapper iim;

	@Override
	public void saveMsg(IM im) {
		iim.saveMsg(im);
	}

	@Override
	public List<IM> getMsg(String account) {
		return iim.getMsg(account);
	}

	@Override
	public List<Map<String, Object>> getIMUnReachNum(String account) {
		return iim.getIMUnReachNum(account);
	}

	@Override
	public List<IM> getUnReachMsg(String fromAccount, String toAccount) {
		List<IM> list = iim.getUnReachMsg(fromAccount, toAccount);
		// 跟新没读数据状态
		iim.updateUnReachMsg(new Timestamp(System.currentTimeMillis()), fromAccount, toAccount);
		return list;
	}

	@Override
	public List<IM> getIMContactHistory(String fromAccount, String toAccount, int limitNum) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("fromAccount", fromAccount);
		map.put("toAccount", toAccount);
		map.put("limitNum", limitNum);
		return iim.selectIMContactHistory(map);
	}

	@Override
	public void updateMsg(String datetime) {
		iim.updateMsg(new Timestamp(System.currentTimeMillis()), datetime);
	}
}
