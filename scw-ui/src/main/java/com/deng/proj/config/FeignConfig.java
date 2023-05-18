package com.deng.proj.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志登记
 * @Author by DHF
 * @Date 2021/12/2021/12/24 10:40
 * @Version 1.0
 */
@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level getFeignlogger() {
        return Logger.Level.FULL;
    }
}
