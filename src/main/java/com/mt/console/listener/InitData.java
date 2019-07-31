package com.mt.console.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 加载参数进系统变量20161105
 * 
 * @author maitao
 *
 */
@Slf4j
@Component
public class InitData implements ApplicationListener<ContextRefreshedEvent> {

//	@Autowired
//	private ICommonService ics;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
//		System.out.println(event.getApplicationContext().getApplicationName());
//		ServletContext application = ((XmlWebApplicationContext) event.getSource()).getServletContext();
		log.info("=================初始化系统参数=================");
		/*List<Map<String, Object>> list = ics.sysConf();

		for (Map<String, Object> map : list) {
			System.out.println("key: " + (String) map.get("conf_key") + " value: " + (String) map.get("value"));
			application.setAttribute((String) map.get("conf_key"), (String) map.get("value"));
		}*/
	}

}
