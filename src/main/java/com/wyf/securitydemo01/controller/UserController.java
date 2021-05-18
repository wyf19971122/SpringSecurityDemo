package com.wyf.securitydemo01.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.wyf.securitydemo01.aop.SysLog;
import com.wyf.securitydemo01.entity.dto.*;
import com.wyf.securitydemo01.entity.pojo.Users;
import com.wyf.securitydemo01.exceptionHandle.LoginException;
import com.wyf.securitydemo01.service.UserService;
import com.wyf.securitydemo01.util.ImageVerificationCode;
import com.wyf.securitydemo01.util.R;
import com.wyf.securitydemo01.util.TipsEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
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
@CrossOrigin
@Api(tags = "用户相关接口",value = "用户相关接口")
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
    @SysLog(value = "用户登录")
    public R login(@RequestBody UserLoginDto userDto){
        // 验证码验证(忽略大小写)
        checkCaptcha(userDto.getRandomStr(),userDto.getCode());
        // 用户登录
        return R.ok(userService.login(userDto));
    }
    @GetMapping("/logout")
    @ApiOperation("退出登录")
    @SysLog(value = "退出登录")
    public R logout(){
        return R.ok("退出登录");
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
    public void checkCaptcha(String key,String code){
        Object obj = redisTemplate.opsForValue().get(CAPTCHA_DETAILS + key);
        if(obj == null){
            throw new LoginException(TipsEnum.CAPTCHA_IS_EXPIRE.getMessage(),TipsEnum.CAPTCHA_IS_EXPIRE.getCode());
        }
        String captcha = (String)obj;
        if(!captcha.equals(code.toLowerCase())){
            throw new LoginException(TipsEnum.CAPTCHA_ERROR.getMessage(),TipsEnum.CAPTCHA_ERROR.getCode());
        }
    }

    //分页查询
    @SysLog(value = "分页查询用户信息")
    @PostMapping("/queryUserPage")
    @ApiOperation("分页查询用户信息")
    public R queryUserPage(@Validated @RequestBody UserDto dto){
        Page<Users> result = userService.queryUserPage(dto);
        return R.ok(result);
    }

    @GetMapping("/queryById")
    @SysLog(value = "查询用户信息")
    @ApiOperation("根据id查询用户")
    public R queryById(@RequestParam Integer id){
        Users users = userService.queryById(id);
        return R.ok(users);
    }

    @GetMapping("/removeUser")
    @ApiOperation("删除用户")
    @SysLog(value = "删除用户")
    public R removeUser(@RequestParam Integer id){
        userService.removeUser(id);
        return R.ok("删除成功");
    }

    @PostMapping("/updatePassword")
    @ApiOperation("修改密码")
    @SysLog(value = "修改密码")
    public R updatePassword(@Validated @RequestBody UpdatePasswordDto dto){
        userService.updatePassword(dto);
        return R.ok("修改密码成功！");
    }

    @PostMapping("/addUser")
    @SysLog(value = "新增用户")
    @ApiOperation("新增用户")
    public R addUser(@Validated @RequestBody UserAddDto dto){
        userService.addUser(dto);
        return R.ok("新增用户成功！");
    }

    @PostMapping("/updateUser")
    @SysLog(value = "修改用户信息")
    @ApiOperation("修改用户信息")
    public R updateUser(@Validated @RequestBody UserUpdateDto dto){
        userService.updateUser(dto);
        return R.ok("修改用户信息成功！");
    }

}
