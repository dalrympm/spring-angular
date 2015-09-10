package com.mousedown

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.csrf.CsrfFilter
import org.springframework.security.web.csrf.CsrfTokenRepository
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpSession
import java.security.Principal

/**
 *
 * <p/>
 * Copyright &copy; 2006-2015 Watchwith, Inc. The software included herein is property of Watchwith, Inc and its 
 * licensors which reserve all rights, title and interest.
 */
@SpringBootApplication
@RestController
@EnableRedisHttpSession
class UiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UiApplication.class, args)
    }

    @RequestMapping("/token")
    def token(HttpSession session) {
        ["token": session.id]
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .httpBasic()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/index.html", "/home.html", "/login.html", "/").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter)
                    .csrf().csrfTokenRepository(csrfTokenRepository())
        }

        CsrfTokenRepository csrfTokenRepository() {
            HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository()
            repository.setHeaderName('X-XSRF-TOKEN')
            repository
        }
    }

}
