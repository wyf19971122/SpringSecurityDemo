package com.wyf.securitydemo01.util;

import com.wyf.securitydemo01.exceptionHandle.CustomerException;
import com.wyf.securitydemo01.exceptionHandle.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author xxoo
 * @date 2019/2/1
 * 全局的的异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局异常
     */
    @ExceptionHandler(Exception.class)
    public R exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return R.build(TipsEnum.FAIL);
    }


    /**
     * 自定义异常
     */
    @ExceptionHandler(LoginException.class)
    public R LoginExceptionHandler(LoginException e) {
        return R.build(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(CustomerException.class)
    public R handlerCustomerException(CustomerException e, HttpServletResponse response){
        System.out.println("111");
        return R.build(400,e.getMsg());
    }
    /**
     * validation Exception
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public R bodyValidExceptionHandler(Exception e) {
        if (e instanceof MethodArgumentNotValidException) {
            String defaultMessage = Objects.requireNonNull(((MethodArgumentNotValidException) e).getBindingResult().getFieldError()).getDefaultMessage();
            log.warn(defaultMessage);
            return R.build(400, defaultMessage);
        } else {
            String defaultMessage = Objects.requireNonNull(((BindException) e).getBindingResult().getFieldError()).getDefaultMessage();
            log.warn(defaultMessage);
            return R.build(400, defaultMessage);
        }
    }

    /**
     * 用来设置WebDataBinder，WebDataBinder用来自动绑定前台请求参数到Moder中
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

}
