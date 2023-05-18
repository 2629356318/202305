package com.deng.proj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/24 10:46
 * @Version 1.0
 */
@Configuration
public class AppWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //如果controller仅仅用于转发页面，那在当前方法中配置映射即可
        registry.addViewController("/login").setViewName("login");
    }
}
