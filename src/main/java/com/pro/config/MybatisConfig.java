package com.pro.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/** 配置类
 * Created by paul on 2017/11/23.
 */
@Configuration
@ConfigurationProperties
public class MybatisConfig {

    @Autowired
    private DruidDataSourceConfig druidDataSourceConfig;

    @Bean(name="dataSource")
    public DataSource dataSource() {
        // 加载配置文件属性
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(druidDataSourceConfig.getDriverClassName());
        ds.setUsername(druidDataSourceConfig.getUsername());
        ds.setPassword(druidDataSourceConfig.getPassword());
        ds.setUrl(druidDataSourceConfig.getUrl());
        ds.setMaxActive(druidDataSourceConfig.getMaxActive());
        ds.setValidationQuery(druidDataSourceConfig.getValidationQuery());
        ds.setTestOnBorrow(druidDataSourceConfig.isTestOnBorrow());
        ds.setTestOnReturn(druidDataSourceConfig.isTestOnReturn());
        ds.setTestWhileIdle(druidDataSourceConfig.isTestWhileIdle());
        ds.setTimeBetweenEvictionRunsMillis(druidDataSourceConfig.getTimeBetweenEvictionRunsMillis());
        ds.setMinEvictableIdleTimeMillis(druidDataSourceConfig.getMinEictableIdleTimeMillis());
        ds.setPoolPreparedStatements(druidDataSourceConfig.isPoolPreparedStatements());
        ds.setMaxOpenPreparedStatements(druidDataSourceConfig.getMaxOpenPreparedStatements());
        try {
            ds.setFilters(druidDataSourceConfig.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    @Bean(name ="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource());

        bean.setTypeAliasesPackage("com.pro.entity");


        //分页插件设置
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        //添加分页插件
        bean.setPlugins(new Interceptor[]{pageHelper});



        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            //基于注解扫描Mapper，不需配置xml路径
            bean.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
