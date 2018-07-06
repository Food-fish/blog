package com.jijidom.Administration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

/**
 * @Author: JinTao
 * @Description: 配置监听
 * @Date: 14:59 2018/5/22
 */
@Configuration
public class ListenerConfig {
    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }
}
