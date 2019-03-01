package com.psbc.wyk.dangjian.config;

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
 * @author wyk on 2019/02/28
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 指定 package，避免展现 spring-boot-starter-actuator 引入的 log-file-mvc-endpoint、health-mvc-endpoint 等
                .apis(RequestHandlerSelectors.basePackage("com.psbc.wyk.dangjian"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot 示例框架")
                .description("SpringBoot 示例框架")
                .contact(new Contact("文宇坤", "yk.wen", "530016380@qq.com"))
                .version("1.0").build();
    }
}
