package com.mt.console.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mt.console.web.po.Log;

@Mapper
public interface IOperationMapper extends IBaseMapper<Log, Long> {

}
