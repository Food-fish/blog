package com.jijidom.Administration.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import com.jijidom.util.SqlPrintInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = {"com.jijidom.Administration.dao.read"},sqlSessionFactoryRef = "readUserSqlSessionFactory")
public class UserDruidDataSourceConfig {

    private Logger logger = LoggerFactory.getLogger(UserDruidDataSourceConfig.class);
    @Value("${spring.datasource.user.read.url}")
    private String dbUrl;
    @Value("${spring.datasource.user.read.type}")
    private String dbType;
    @Value("${spring.datasource.user.read.username}")
    private String username;
    @Value("${spring.datasource.user.read.password}")
    private String password;
    @Value("${spring.datasource.user.read.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.user.read.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.user.read.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.user.read.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.user.read.maxWait}")
    private int maxWait;
    @Value("${spring.datasource.user.read.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.user.read.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.datasource.user.read.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.user.read.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.user.read.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.user.read.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.user.read.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.datasource.user.read.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.user.read.filters}")
    private String filters;
    @Value("${spring.datasource.user.read.connectionProperties}")
    private String connectionProperties;
    @Value("${spring.datasource.user.read.MapperLocations}")
    private String userMapperLocations;
    @Value("${spring.datasource.user.read.userGlobalDataSourceStat}")
    private boolean useGlobalDataSourceStat;
    @Value("${spring.datasource.druidLoginName}")
    private String druidLoginName;
    @Value("${spring.datasource.druidPassword}")
    private String druidPassword;
    @Value("${spring.datasource.user.read.TypeAliasesPackage}")
    private String typeAliasesPackage;
    @Value("${spring.datasource.user.read.configLocation}")
    private String configLocation;

    //@ConfigurationProperties(prefix = "spring.datasource.user")// 数据源的自动配置的前缀
    @Bean(name = "readDataSource")
    @Primary
    public DataSource readDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        //datasource.setDbType(dbType);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        datasource.setConnectionProperties(connectionProperties);
        datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(connectionProperties);
        return datasource;
    }

    /**
     * SqlSessionFactory配置
     *
     * @return
     * @throws Exception
     */
    @Bean(name = "readUserSqlSessionFactory")
    @Primary
    public SqlSessionFactory userSqlSessionFactory(@Qualifier("readDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 配置mapper文件位置
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(userMapperLocations));

        //配置分页插件
        PageInterceptor pageHelper = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //设置插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper,sqlPrintInterceptor()});
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory userSqlSessionFactory) {
        return new SqlSessionTemplate(userSqlSessionFactory);
    }

    /**
     * 配置事物管理器
     *
     * @return
     */
    @Bean(name = "userTransactionManager")
    @Primary
    public DataSourceTransactionManager userTransactionManager(@Qualifier("readDataSource") DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    //将要执行的sql进行日志打印(不想拦截，就把这方法注释掉)
    @Bean
    public SqlPrintInterceptor sqlPrintInterceptor(){
        return new SqlPrintInterceptor();
    }

}