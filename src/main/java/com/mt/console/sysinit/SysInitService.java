package com.mt.console.sysinit;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mt loading properties
 */
@Slf4j
@Component
public class SysInitService {

	public static Map<String, Object> configMap = new HashMap<String, Object>();


	@PostConstruct
	private void sysInit() {
		log.info("开启定时任务++++++++++++++++++++++++++++++++");
		log.info("读入系统变量++++++++++++++++++++++++++++++++");

	}

}
