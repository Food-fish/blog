package com.jijidom.Administration.config;

import com.jijidom.Administration.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * Created by yangyibo on 17/1/18.
 * @EnableWebSecurity: 禁用Boot的默认Security配置，配合@Configuration启用自定义配置（需要扩展WebSecurityConfigurerAdapter）
 * @EnableGlobalMethodSecurity(prePostEnabled = true): 启用Security注解，例如最常用的@PreAuthorize
 * configure(HttpSecurity): Request层面的配置，对应XML Configuration中的<http>元素
 * configure(WebSecurity): Web层面的配置，一般用来配置无需安全检查的路径
 * configure(AuthenticationManagerBuilder): 身份验证配置，用于注入自定义身份验证Bean和密码校验规则
 */



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public UserDetailsService userDetailsService() { //注册UserDetailsService 的bean
        return new CustomUserService();
    }


    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()); //user Details Service验证
        //auth.eraseCredentials(false);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilter(filterSecurityInterceptor())
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and().formLogin().loginPage("/login.html")
                .passwordParameter("password")//form表单用户名参数名
                .usernameParameter("username") //form表单密码参数名
                .loginProcessingUrl("/login")//form表单POST请求url提交地址
                .failureForwardUrl("/login?error")//登录失败跳转地址
                .loginProcessingUrl("/login")//form表单POST请求url提交地址，默认为/login
                .successForwardUrl("/home")//登录成功跳转地址
                .permitAll()
                .successHandler(loginSuccessHandler()) //登录成功后可使用loginSuccessHandler()存储用户信息，可选。
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll()
                .invalidateHttpSession(true)//注销后使session相关信息无效
                .and().rememberMe();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略css.jq.img等文件
        web.ignoring().antMatchers("/css/**");
        super.configure(web);
    }

    @Autowired
    private MyInvocationSecurityMetadataSourceService mySecurityMetadataSource;

    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public FilterSecurityInterceptor filterSecurityInterceptor(){
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(mySecurityMetadataSource);
        filterSecurityInterceptor.setAccessDecisionManager(myAccessDecisionManager);
        filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
        return filterSecurityInterceptor;
    }
}
