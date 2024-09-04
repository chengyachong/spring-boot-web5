package com.cyc.sercurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // super.configure(http);
        //定制授权规则
        http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3")
                .antMatchers("/level1/**").hasRole("VIP1");
        //开启自动配置的登录功能，如果没有登录，没有权限就会来到登录页面
        http.formLogin();
        //login来到登录页面
        //2、重定向到/login?error 表示登录失败

        //开启自动配置的注销功能  清除session功能
        http.logout().logoutSuccessUrl("/");//注销成功后返回首页

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      //  super.configure(auth);
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("zhangshan").password("123456").roles("VIP1","VIP2").and()
                .withUser("lisi").password("123456").roles("VIP2","VIP3").and()
                .withUser("wangwu").password("123456").roles("VIP2","VIP3");
    }
}
