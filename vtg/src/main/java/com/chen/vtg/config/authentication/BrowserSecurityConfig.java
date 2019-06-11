package com.chen.vtg.config.authentication;

import com.chen.vtg.service.impl.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;

/**
 * @Author : Chen
 * @Date : Create in 2018/12/22 20:08
 * @Description :
 * @Modify by :
 */
@Configuration
@EnableWebSecurity
@EnableOAuth2Client  // 启用 OAuth 2.0 客户端
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailService myUserDetailService;

//    @Autowired
//    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
//
//    @Autowired
//    MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    //注入Oauth2.0客户端上下文
    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")  //定义当需要用户登录时候，转到的登录页面
//                .loginProcessingUrl("/login")   // 自定义的登录接口
//                .successHandler(myAuthenticationSuccessHandler)
//                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .anyRequest()  // 任何请求,登录后可以访问
                .authenticated(); //执行请求时，必须已经登录了应用

        http.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
          .userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 需要配置这个支持password模式 support password grant type
     *
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    @ConfigurationProperties("github.client")
    public AuthorizationCodeResourceDetails github() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("github.resource")
    @Primary
    public ResourceServerProperties githubResource() {
        return new ResourceServerProperties();
    }

    //自定义过滤器，用于拦截oauth2第三方登录返回code的url,并根据code,clientid,clientSecret去授权服务器拿accace_token
    private Filter ssoFilter() {
        //OAuth2ClientAuthenticationProcessingFilter
        //它的构造器需要传入defaultFilterProcessesUrl，用于指定这个filter拦截哪个url。
        //它依赖OAuth2RestTemplate来获取token
        //还依赖ResourceServerTokenServices进行校验token
        OAuth2ClientAuthenticationProcessingFilter githubFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/github");
        //对rest template的封装，为获取token等提供便捷方法
        //DefaultUserInfoRestTemplateFactory实例了OAuth2RestTemplate,这个提供了OAuth2RestTemplate
        OAuth2RestTemplate githubTemplate = new OAuth2RestTemplate(github(), oauth2ClientContext);
        githubFilter.setRestTemplate(githubTemplate);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(githubResource().getUserInfoUri(), github().getClientId());
        tokenServices.setRestTemplate(githubTemplate);
        githubFilter.setTokenServices(tokenServices);
        return githubFilter;
    }

    /** 注册一个额外的Filter：OAuth2ClientContextFilter
     *  主要作用是重定向，当遇到需要权限的页面或URL，代码抛出异常，这时这个Filter将重定向到OAuth鉴权的地址
     */
    @Bean
    public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
        registration.setFilter(filter);
        registration.setOrder(-1);
        return registration;
    }
}
