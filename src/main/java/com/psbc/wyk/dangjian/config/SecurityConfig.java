package com.psbc.wyk.dangjian.config;

import com.psbc.wyk.dangjian.common.responsecode.CodeDefault;
import com.psbc.wyk.dangjian.common.util.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.AuthenticationManagerBeanDefinitionParser;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import java.util.ArrayList;
import java.util.Map;

import static com.psbc.wyk.dangjian.common.Constant.TYPE_ADMIN;
import static com.psbc.wyk.dangjian.common.Constant.TYPE_NORMAL;

/**
 * @author wyk on 2019/02/27
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    //UserDetailService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//      http.httpBasic()// httpBasic 登录

        http.formLogin()// 表单登录  来身份认证
                .loginPage("/login") //
                .failureUrl("/login?error")
                .permitAll()// 自定义登录页面
                .and()
                .authorizeRequests()// 对请求授权
                .antMatchers(
                        "/login/*").permitAll()// 这些页面不需要身份认证,其他请求需要认证
                .antMatchers("/admin/*").hasAuthority(TYPE_ADMIN)
                .antMatchers("/normal/*").hasAnyAuthority(TYPE_ADMIN,TYPE_NORMAL)
                .anyRequest() // 任何请求
                .authenticated()//; // 都需要身份认证
                .and()
                .csrf().disable()// 禁用跨站攻击
        .logout().logoutUrl("/logout").permitAll();

        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            String jwtToken = request.getHeader("jwtToken");
            Assert.notNull(jwtToken,CodeDefault.LOGIN);
        });

        // 关闭跨域拦截
        http.csrf().disable();
        // 关闭不允许在frame中访问的拦截
        http.headers().frameOptions().disable();
        // 单用户最大登陆数
        http.sessionManagement().maximumSessions(1);



        TokenLoginFilter tokenLoginFilter = new TokenLoginFilter();
        Map<String, TokenLoginStrategy> tokenLoginStrategys = this.getApplicationContext()
                .getBeansOfType(TokenLoginStrategy.class);
        ArrayList<TokenLoginStrategy> tokenLoginStrategysList = new ArrayList<>(tokenLoginStrategys.values());
        tokenLoginFilter.setTokenLoginStrategys(tokenLoginStrategysList);
        http.addFilterBefore(tokenLoginFilter, UsernamePasswordAuthenticationFilter.class);
    }

    //@Override
    //protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.userDetailsService(userDetailService);
    //}

}
