package com.mt.console.feign;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 使用aop会使得hystrix被捕获，从而失效
 * @author tao_m
 *
 */
@Component
public class FeignServiceHystrix implements IFeignServiceTestClient {


	@Override
	public String getMsg(@RequestParam(value = "id") Integer id) {
		return "sorry "+id+"，service has fail!";
	}

	@Override
	public String send(String msg) {
		// TODO Auto-generated method stub
		return "123";
	}

	@Override
	public Integer add(Integer a, Integer b) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}