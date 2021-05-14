package com.wyf.securitydemo01.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.google.common.collect.Maps;
import com.wyf.securitydemo01.entity.dto.UserLoginDto;
import com.wyf.securitydemo01.exceptionHandle.LoginException;
import com.wyf.securitydemo01.service.UserService;
import com.wyf.securitydemo01.util.ImageVerificationCode;
import com.wyf.securitydemo01.util.TipsEnum;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wyf
 * @Date: 2021/4/21 11:29
 */
@Slf4j
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final RedisTemplate redisTemplate;

    private static final String CAPTCHA_DETAILS= "CAPTCHA_DETAILS:";

    @Autowired
    private UserService userService;

    /**
     *  用户登录
     * @param userDto 登录信息
     * @return R
     */
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public R login(@RequestBody UserLoginDto userDto){
        // 验证码验证(忽略大小写)
        checkCaptcha(userDto.getRandomStr(),userDto.getCode());
        // 用户登录
        return R.ok(userService.login(userDto));
    }

    /**
     *  登录发送验证码
     */
    @GetMapping("/getLoginCaptchaCode")
    @ApiOperation("发送验证码(进入登录页面时调用)")
    public R getCaptchaCodeForLogin(){
        ImageVerificationCode ivc = new ImageVerificationCode();     //用我们的验证码类，生成验证码类对象
        BufferedImage image = ivc.getImage();  //获取验证码
        String key = UUID.randomUUID().toString();
        // 验证码忽略大小写
        String text = ivc.getText().toLowerCase();
        System.out.println("验证码key："+key);
        System.out.println("验证码："+text);
        // 保存至redis，过期时间180s
        redisTemplate.opsForValue().set(CAPTCHA_DETAILS + key,text, 600, TimeUnit.SECONDS);
        log.debug("验证码保存至redis，过期时间：60s");
        HashMap<String,Object> map = Maps.newHashMap();
        map.put("key",key);
        map.put("image",ivc.toBase64(image,"data:image/png;base64,"));
        return R.ok(map);
    }
    /**
     *  获取验证码(redis),并验证
     */
    private void checkCaptcha(String key,String code){
        Object obj = redisTemplate.opsForValue().get(CAPTCHA_DETAILS + key);
        if(obj == null){
            throw new LoginException(TipsEnum.CAPTCHA_IS_EXPIRE.getMessage(),TipsEnum.CAPTCHA_IS_EXPIRE.getCode());
        }
        String captcha = (String)obj;
        if(!captcha.equals(code.toLowerCase())){
            throw new LoginException(TipsEnum.CAPTCHA_ERROR.getMessage(),TipsEnum.CAPTCHA_ERROR.getCode());
        }
    }

}
