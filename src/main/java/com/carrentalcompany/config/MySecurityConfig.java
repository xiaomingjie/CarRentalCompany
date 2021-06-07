package com.carrentalcompany.config;

import com.carrentalcompany.service.UserService;
import com.carrentalcompany.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("userId").passwordParameter("password")
                .defaultSuccessUrl("/main")
                .and()
            .authorizeRequests()
                .antMatchers("/main", "/main.html","/register.html","/register","/register/error","/login/error","/login.html","/login","/user/register","/user/login","/static/image/**","/image/**","/test.html","/authentication/form","/code/image","/share/**","/visitor/activity/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN") // 需要相应的角色才能访问
                .anyRequest()
                .authenticated()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/main")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        auth.userDetailsService(userService);
    }
}


