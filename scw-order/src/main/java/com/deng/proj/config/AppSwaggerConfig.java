package com.deng.proj.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 19:10
 * @Version 1.0
 */
@Configuration
@EnableSwagger2  //开启swagger2自动生成api文档的功能
public class AppSwaggerConfig {
    @Bean("订单模块")
    public Docket projectApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("订单模块")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/order.*"))
                .build()
                .apiInfo(apiInfo())
                .enable(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("系统平台接口文档")
                .description("订单模块的文档")
                .termsOfServiceUrl("http://www.ujiuye.com/")
                .version("1.0")
                .build();
    }
}
