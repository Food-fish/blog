package com.jijidom.Administration.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;
import com.jijidom.Administration.interceptor.MyInterceptor;

/**
 * @Author: JinTao
 * @Version:
 * @Date: Create in 17:56 2018/5/24
 * @Description:
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("css/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/css/");
        registry.addResourceHandler("js/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/js/");
        registry.addResourceHandler("html/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/templates/");

        super.addResourceHandlers(registry);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截规则：除了login，其他都拦截判断
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("html/login");
        super.addInterceptors(registry);
    }
    //重写添加视图控制器（不用写controller就可以跳转至指定的jsp,比如：http://localhost:8080/Login）
    //此时就跳转到/WEB-INF/jsp/index.jsp页面
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("html/login");
        super.addViewControllers(registry);
    }
}
