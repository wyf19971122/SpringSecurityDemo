package com.wyf.securitydemo01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @Author: wyf
 * @Date: 2021/4/21 14:20
 */
@Configuration
public class SecurityConfig01 extends WebSecurityConfigurerAdapter{
    /**
     * @Author: wyf
     * @Date: 2021/4/21 14:02
     */

    //录入数据源，配置数据库对象
        @Autowired
        private DataSource dataSource;
        @Bean
        public PersistentTokenRepository persistentTokenRepository(){
            JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
            jdbcTokenRepository.setDataSource(dataSource);
            //自动生成表
//            jdbcTokenRepository.setCreateTableOnStartup(true);
            return jdbcTokenRepository;
        }

        @Autowired
        private UserDetailsService userDetailsService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception{
            auth.userDetailsService(userDetailsService).passwordEncoder(password());
        }

        @Bean
        PasswordEncoder password(){
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception{
            //退出操作
            http.logout().logoutUrl("/logout").logoutSuccessUrl("/test/logout").permitAll();
            //自定义没有权限的自定义页面
            http.exceptionHandling().accessDeniedPage("/unAuth.html");
            http.formLogin()
                    .loginPage("/login.html")//自定义登录页面
                    .loginProcessingUrl("/user/login")//登录访问路径(具体访问路径)
                    .defaultSuccessUrl("/success.html").permitAll()//登录成功跳转的路径
                    .and().authorizeRequests()
                        .antMatchers("/","/test/hello","/test/login").permitAll()//设置可以直接访问的路径，不要认证
                        //当前登录用户，只有具有admins权限才能访问(单个权限)
//                        .antMatchers("/test/index").hasAnyAuthority("admins")
                        //多个权限
//                        .antMatchers("/test/index").hasAnyAuthority("admins","root")
                        //底层做封装sale-》ROLE_sale
//                        .antMatchers("/test/index").hasRole("sale")
                        .antMatchers("/test/index").hasAnyRole("sale","manager")
                        .anyRequest().authenticated()//所有都可以访问
                        //记住我自动登录过程 设置以秒为单位的时间，数据源以及用户
                        .and().rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(60).userDetailsService(userDetailsService)
                        .and().csrf().disable();//关闭csrf防护
        }

}
