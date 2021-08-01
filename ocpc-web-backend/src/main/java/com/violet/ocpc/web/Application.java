package com.violet.ocpc.web;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * @Description 启动器配置
 * @Author xpguo
 * @Date 2018/8/29 17:38
 */
@SpringBootApplication
@MapperScan("com.violet.ocpc.web.dao.mapper")
public class Application extends SpringBootServletInitializer
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
    {
        // 注意这里一定要指向原先用main方法执行的Application启动类
        return builder.sources(Application.class);
    }

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource()
    {
    	return new DataSource();
    }

}
