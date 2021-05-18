package com.wyf.securitydemo01.aop;

import com.wyf.securitydemo01.entity.pojo.OdSysLog;
import com.wyf.securitydemo01.mapper.OdSysLogMapper;
import com.wyf.securitydemo01.util.HttpContextUtils;
import com.wyf.securitydemo01.util.SysLogUtils;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: wyf
 * @Date: 2020/12/8 17:53
 */
@Aspect
@Component
@RequiredArgsConstructor
public class SysLogAspect {

    @Autowired
    private OdSysLogMapper sysLogMapper;

    @Pointcut("@annotation(com.wyf.securitydemo01.aop.SysLog)")
    public void pointcut() {
    }

    //接口和操作日志
    @AfterReturning("pointcut()")
    public Object around(JoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point);
        return result;
    }

    private void saveLog(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String ipAddr = SysLogUtils.getIpAddr(HttpContextUtils.getHttpServletRequest());
        OdSysLog odSysLog = new OdSysLog();
        SysLog logAnnotation = method.getAnnotation(SysLog.class);

        // 注解上的描述
        odSysLog.setTitle(logAnnotation.value());

        odSysLog.setIpAddr(ipAddr);
        // 保存系统日志
        sysLogMapper.insert(odSysLog);
    }

}
