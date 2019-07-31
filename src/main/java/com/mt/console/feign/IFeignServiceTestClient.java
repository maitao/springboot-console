package com.mt.console.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-collection", fallback = FeignServiceHystrix.class )// 这里的name对应调用服务的spring.applicatoin.name，大写，只能注册一次
public interface IFeignServiceTestClient {

	@RequestMapping(value = "/getMsg",method = RequestMethod.GET)
	String getMsg(@RequestParam(value = "id") Integer id);

	@RequestMapping("/v1/send/{msg}")
	String send(@PathVariable("msg") String msg);
	
	@RequestMapping(method = RequestMethod.GET, value = "/add")
    Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
}