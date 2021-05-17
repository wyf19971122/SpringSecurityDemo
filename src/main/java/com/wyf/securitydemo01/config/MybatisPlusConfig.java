package com.wyf.securitydemo01.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @author luoyuting
 * @date 2021-05-17 0:46
 * @description
 */
public class MybatisPlusConfig {
        @Bean
        public PaginationInterceptor paginationInterceptor() {
            return new PaginationInterceptor();
        }
    }
