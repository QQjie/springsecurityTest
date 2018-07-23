package com.cnsunet.kjw.config;

import com.alibaba.druid.pool.DruidDataSource;

import com.cnsunet.kjw.config.setting.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@EnableConfigurationProperties({FlowDruidSetting.class,NspmsDruidSetting.class,
        MyTestDruidSetting.class,BaseDruidSetting.class,PmDruidSetting.class})
public class DruidConfig {

    @Autowired
    private FlowDruidSetting flowDruidSetting;
    @Autowired
    private MyTestDruidSetting myTestDruidSetting;
    @Autowired
    private BaseDruidSetting baseDruidSetting;
    @Autowired
    private PmDruidSetting pmDruidSetting;
    @Autowired
    private NspmsDruidSetting nspmsDruidSetting;

    @Bean(name="flowDataSource")
    @Qualifier("flowDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasourceflow")
    public DruidDataSource dataSource1(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(flowDruidSetting.getDriverClassName());
        dataSource.setUrl(flowDruidSetting.getUrl());
        dataSource.setUsername(flowDruidSetting.getUsername());
        dataSource.setPassword(flowDruidSetting.getPassword());
        return  dataSource;
    }

    @Bean(name="myDataSource")
    @Qualifier("myDataSource")
    @ConfigurationProperties(prefix = "spring.datasourcemytest")
    @Transactional
    public DruidDataSource dataSource2(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(myTestDruidSetting.getDriverClassName());
        dataSource.setUrl(myTestDruidSetting.getUrl());
        dataSource.setUsername(myTestDruidSetting.getUsername());
        dataSource.setPassword(myTestDruidSetting.getPassword());
        return  dataSource;
    }
    @Bean(name="baseDataSource")
    @Qualifier("baseDataSource")
    @ConfigurationProperties(prefix = "spring.datasourcebase")
    public DruidDataSource dataSource3(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(baseDruidSetting.getDriverClassName());
        dataSource.setUrl(baseDruidSetting.getUrl());
        dataSource.setUsername(baseDruidSetting.getUsername());
        dataSource.setPassword(baseDruidSetting.getPassword());
        return  dataSource;
    }
    @Bean(name="pmDataSource")
    @Qualifier("pmDataSource")
    @ConfigurationProperties(prefix = "spring.datasourcepm")
    public DruidDataSource dataSource4(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(pmDruidSetting.getDriverClassName());
        dataSource.setUrl(pmDruidSetting.getUrl());
        dataSource.setUsername(pmDruidSetting.getUsername());
        dataSource.setPassword(pmDruidSetting.getPassword());
        return  dataSource;
    }
    @Bean(name="nspmsDataSource")
    @Qualifier("nspmsDataSource")
    @ConfigurationProperties(prefix = "spring.datasourcenspms")
    public DruidDataSource dataSource5(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(nspmsDruidSetting.getDriverClassName());
        dataSource.setUrl(nspmsDruidSetting.getUrl());
        dataSource.setUsername(nspmsDruidSetting.getUsername());
        dataSource.setPassword(nspmsDruidSetting.getPassword());
        return  dataSource;
    }

    @Bean(name = "flowJdbcTemplate")
    @Primary
    public JdbcTemplate flowJdbcTemplate(
            @Qualifier("flowDataSource") DruidDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "myJdbcTemplate")
    public JdbcTemplate myJdbcTemplate(
            @Qualifier("myDataSource") DruidDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    @Bean(name = "baseJdbcTemplate")
    public JdbcTemplate baseJdbcTemplate(
            @Qualifier("baseDataSource") DruidDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    @Bean(name = "pmJdbcTemplate")
    public JdbcTemplate pmJdbcTemplate(
            @Qualifier("pmDataSource") DruidDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    @Bean(name = "nspmsJdbcTemplate")
    public JdbcTemplate nspmsJdbcTemplate(
            @Qualifier("nspmsDataSource") DruidDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
   /* @Bean(name = "txManager1")
    public PlatformTransactionManager txManager(@Qualifier("dataSource2")DataSource dataSource2) {
        return new DataSourceTransactionManager(dataSource2);
    }*/
}
