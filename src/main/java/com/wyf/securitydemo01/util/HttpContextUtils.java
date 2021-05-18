package com.wyf.securitydemo01.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wyf
 * @Date: 2020/12/9 1:05
 */
@UtilityClass
public class HttpContextUtils {

        public static HttpServletRequest getHttpServletRequest() {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }
}

