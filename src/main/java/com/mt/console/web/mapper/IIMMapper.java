package com.mt.console.web.mapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mt.console.web.po.IM;

@Mapper
public interface IIMMapper {
	public void saveMsg(IM im);

	public void updateMsg(@Param(value = "reachTime") Timestamp reachTime, @Param(value = "datetime") String datetime);

	public List<IM> getMsg(@Param(value = "account") String account);

	List<Map<String, Object>> getIMUnReachNum(@Param(value = "account") String account);

	List<IM> getUnReachMsg(@Param(value = "fromAccount") String fromAccount,
			@Param(value = "toAccount") String toAccount);

	void updateUnReachMsg(@Param(value = "reachTime") Timestamp reachTime,
			@Param(value = "fromAccount") String fromAccount, @Param(value = "toAccount") String toAccount);

	List<IM> selectIMContactHistory(Map<String, Object> map);
}
