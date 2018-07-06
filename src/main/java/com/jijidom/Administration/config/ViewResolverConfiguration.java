package com.jijidom.Administration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafView;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

/**
 * @Author: JinTao
 * @Version:
 * @Date: Create in 19:50 2018/5/22
 * @Description: 主要配置多视图实现的视图解析器相关bean实例
 */
@Configuration
public class ViewResolverConfiguration {
    @Value("${spring.mvc.view.prefix}")
    private String jsp_prefix;
    @Value("${spring.mvc.view.suffix}")
    private String jsp_suffix;
    @Value("${spring.mvc.view.name}")
    private String jsp_viewNames;
    @Value("${spring.mvc.view.order}")
    private int jsp_order;
    @Value("${spring.thymeleaf.prefix}")
    private String thyme_prefix;
    @Value("${spring.thymeleaf.suffix}")
    private String thyme_suffix;
    @Value("${spring.thymeleaf.view-names}")
    private String thyme_viewNames;
    @Value("${spring.thymeleaf.template-resolver-order}")
    private int thyme_order;
    @Value("${spring.thymeleaf.cache}")
    private boolean cache;
    @Value("${spring.thymeleaf.check-template-location}")
    private boolean location;
    @Value("${spring.thymeleaf.content-type}")
    private String type;
    @Value("${spring.thymeleaf.enabled}")
    private String enabled;
    @Value("${spring.thymeleaf.encoding}")
    private String encoding;
    @Value("${spring.thymeleaf.excluded-view-names}")
    private String names;
    @Value("${spring.thymeleaf.mode}")
    private String mode;

    /*@Bean(name="InternalResourceViewResolver")
    public InternalResourceViewResolver getJspViewResolver(){
        InternalResourceViewResolver jspViewResolver=new InternalResourceViewResolver();
        jspViewResolver.setPrefix(jsp_prefix);
        jspViewResolver.setSuffix(jsp_suffix);
        jspViewResolver.setViewClass(JstlView.class);
        jspViewResolver.setViewNames(jsp_viewNames);
        jspViewResolver.setOrder(jsp_order);
        jspViewResolver.setCache(false);
        return jspViewResolver;
    }*/
    @Bean(name="SpringResourceTemplateResolver")
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver=new SpringResourceTemplateResolver();
        templateResolver.setPrefix(thyme_prefix);
        templateResolver.setSuffix(thyme_suffix);
        templateResolver.setTemplateMode(mode);
        templateResolver.setOrder(thyme_order);
        templateResolver.setCacheable(cache);
        templateResolver.setCharacterEncoding(encoding);
        return templateResolver;
    }
    @Bean(name="SpringTemplateEngine")
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine=new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }
    @Bean(name="ThymeleafViewResolver")
    public ThymeleafViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver thymeleafViewResolver=new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(templateEngine());
        thymeleafViewResolver.setViewClass(ThymeleafView.class);
        thymeleafViewResolver.setViewNames(new String[]{thyme_viewNames});
        thymeleafViewResolver.setCharacterEncoding(encoding);
        thymeleafViewResolver.setCache(cache);
        return thymeleafViewResolver;
    }
}
