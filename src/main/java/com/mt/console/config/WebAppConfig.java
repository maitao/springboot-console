package com.mt.console.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mt.console.interceptor.LoginInterceptor;

@Configuration
@EnableWebMvc
public class WebAppConfig implements WebMvcConfigurer {

	@Autowired
	private LoginInterceptor loginInterceptor;

	@Value("${web.upload-path}")
	private String uploadPath;
	/**
	 * SpringBoot自动配置本身并不会把/swagger-ui.html这个路径映射到对应的目录META-INF/resources/下面。我们加上这个映射即可。
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		 registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		 registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		 registry.addResourceHandler("/upload/**").addResourceLocations("file:///" + uploadPath + "/console/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String[] excludes = new String[] { "/console/cookie", "/console/login", "/console/reg", "/console/register",
				"/console/logon", "/console/logout", "/console/verifycode", "/error" };
		registry.addInterceptor(loginInterceptor).addPathPatterns("/console/**").excludePathPatterns(excludes);
	}

}
