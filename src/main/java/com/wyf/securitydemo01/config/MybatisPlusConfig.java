package com.wyf.securitydemo01.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luoyuting
 * @date 2021-05-17 0:46
 * @description
 */
@Configuration
public class MybatisPlusConfig {
        @Bean
        public PaginationInterceptor paginationInterceptor() {
            return new PaginationInterceptor();
        }
    }
