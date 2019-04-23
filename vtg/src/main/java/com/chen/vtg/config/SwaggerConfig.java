package com.chen.vtg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author : Chen
 * @Date : Create in 2019/4/22 20:24
 * @Description :
 * @Modify by :
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.chen.vtg.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("权限管理")
                .description("权限管理中心 API 1.0 操作文档")
                //服务条款网址
                .termsOfServiceUrl("https://github.com/chencqy/PermissionAdmin")
                .contact(new Contact("RichardChen", "https://github.com/chencqy",
                                     "chen.qy1996@hotmail.com"))
                .version("1.0")
                .build();
    }
}
