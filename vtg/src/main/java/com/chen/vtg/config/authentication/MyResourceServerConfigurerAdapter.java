package com.chen.vtg.config.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @Author : Chen
 * @Date : Create in 2018/9/12 20:44
 * @Description :
 * @Modify by :
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/authentication/github").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/**/*.jpg", "/**/*.png", "/**/*.jpeg").permitAll()
                .antMatchers("/users/**", "/menus/**", "/roles/**", "/admin/**").hasRole("ADMIN")
                .antMatchers(
                        "/webjars/**",
                        "/resources/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v2/api-docs").permitAll()
                .antMatchers("/druid/**").permitAll()
                .antMatchers("/article/image/upload").permitAll()
                .antMatchers("/user/avatar/upload").permitAll()
                .antMatchers("/login/github").permitAll()
                .anyRequest()
                .authenticated();
    }
}
