package com.wyf.securitydemo01.aop;

import java.lang.annotation.*;

/**
 * @Author: wyf
 * @Date: 2020/12/8 16:50
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
        String value() default "";
}
