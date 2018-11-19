package com.main.security;

import com.main.service.CommonService;
import com.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: yunxiang.yang
 * @Date: 2018/9/18 14:54
 */


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //使用基于内存的用户存储
//        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin").password("password").roles("USER", "ADMIN");
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置不拦截规则
//        web.ignoring().antMatchers("/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests().antMatchers("/login").anonymous();//指定登录界面容许匿名登录
        http.authorizeRequests().anyRequest().authenticated()
//容许嵌入框架iframe
                .and().headers().frameOptions().disable().and().httpBasic()
//defaultSuccessUrl:指定登录成功后界面, loginPage:指定登录界面
                .and().formLogin().defaultSuccessUrl("/main.to").loginPage("/login").permitAll();*/
        http
                .authorizeRequests()
                .antMatchers("/*.html").permitAll()
                .antMatchers("/user/logon").permitAll()
                .antMatchers("/rest/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .anyRequest().authenticated();
        http
                .csrf().disable();
        http
                .formLogin().loginPage("/user/logon")
                .failureUrl("/user/logon?error=failed")
                .loginProcessingUrl("/login_form")
                .usernameParameter("u")
                .passwordParameter("p");

        http
                .logout()
                .logoutUrl("/signout")
                .logoutSuccessUrl("/500.html");
        //配置RememberMe
        http
                .rememberMe()
                .tokenValiditySeconds(86400)
                .key("spring~~");
        //开启HTTP Basic认证
        /*http
                .httpBasic().realmName("user");*/
    }
}