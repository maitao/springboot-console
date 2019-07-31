package com.mt.console.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mt.console.web.po.IMMembership;
import com.mt.console.web.po.IMMembershipInfo;

@Mapper
public interface IIMMembershipMapper {
	public List<IMMembership> getAllIMMembership();

	public IMMembershipInfo getIMAccountInfo(@Param(value = "account") String account);

}
