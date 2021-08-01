package com.violet.ocpc.web.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * TODO To describe the functionality of this method
 *
 * @author HYJ
 */
@Configuration
public class SqlSessionConfig
{
    private Logger logger = LoggerFactory.getLogger(SqlSessionConfig.class);

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    
    @Value("${entity_package}")
    private String entityPackage;

    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

    @Bean
    public SqlSessionFactoryBean createSqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = null;
        try {
            sqlSessionFactoryBean = new SqlSessionFactoryBean();
            // 扫描mybatis配置文件
            // sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFilePath));
            // 扫描相关mapper文件
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
            // 调用dataSource
            sqlSessionFactoryBean.setDataSource(dataSource);
            // 映射实体类
            sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);
            return sqlSessionFactoryBean;

        } catch (Exception e) {
            logger.error("创建SqlSession连接工厂错误：{}", e);
        }
        return sqlSessionFactoryBean;
    }
}
