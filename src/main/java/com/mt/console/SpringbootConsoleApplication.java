package com.mt.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//启用服务注册与发现
@EnableDiscoveryClient
//启用feign进行远程调用
@EnableFeignClients //注意：这里必须指定feign接口所在的包 
public class SpringbootConsoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootConsoleApplication.class, args);
	}

}
