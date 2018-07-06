package com.jijidom.Administration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @Author: JinTao
 * @Version:
 * @Date: Create in 17:59 2018/5/29
 * @Description:
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryRead",
        transactionManagerRef="transactionManagerRead",
        basePackages= {"com.jijidom.Administration.jpa"})
public class RepositoryReadConfig {

    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    @Qualifier("jpaReadDataSource")
    private DataSource readDataSource;

    @Bean(name = "jpaReadDataSource")
    @Qualifier("jpaReadDataSource")
    @ConfigurationProperties(prefix="spring.datasource.user.read")
    public DataSource primaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagerRead")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryRead(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryRead")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryRead (EntityManagerFactoryBuilder builder) {
        return builder.dataSource(readDataSource)
                .properties(getVendorProperties(readDataSource))
                .packages("com.jijidom.Administration.entity") //设置实体类所在位置
                .persistenceUnit("readPersistenceUnit")
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    //创建事务Manager
    @Bean(name = "transactionManagerRead")
    PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryRead(builder).getObject());
    }
}
