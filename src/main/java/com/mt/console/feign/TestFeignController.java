package com.mt.console.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestFeignController {

	@Autowired
	IFeignServiceTestClient serviceAFeignClient;

	@GetMapping(value = "/getMsg")
	public String getMsg(@RequestParam Integer id) {
		return serviceAFeignClient.getMsg(id);
	}
	
	@RequestMapping("/v1/send/{msg}")
	public String send(@PathVariable("msg") String msg) {
//        throw new MyException("发生错误2");
		return serviceAFeignClient.send(msg);
	}
}