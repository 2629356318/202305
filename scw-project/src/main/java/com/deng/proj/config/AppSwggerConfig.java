package com.deng.proj.config;

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
 * @Date 2021/12/2021/12/23 10:29
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
public class AppSwggerConfig {
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("项目模块")
                .description("提供项目模块的文档")
                .version("1.0")
                .build();
    }
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.deng.proj"))
                .paths(PathSelectors.any()).build();
    }
}

