package com.violet.ocpc.web.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 重新指定springboot的静态资源处理前缀
 */
@Configuration
public class SpringWebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    System.out.println("====> addResourceHandlers !");
		registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public");
		super.addResourceHandlers(registry);
	}

	@Bean
	public FilterRegistrationBean testFilterRegistration() {
	    System.out.println("====> FilterRegistrationBean !");
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new UiRewriteFilter());// 注册rewrite过滤器
		registration.addUrlPatterns("/*");
		registration.addInitParameter(UiRewriteFilter.REWRITE_TO, "/index.html");
		registration.addInitParameter(UiRewriteFilter.REWRITE_PATTERNS, "/#/ui/*");
		registration.setName("rewriteFilter");
		registration.setOrder(1);
		return registration;
	}

}