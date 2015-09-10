package com.mousedown

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * <p/>
 * Copyright &copy; 2006-2015 Watchwith, Inc. The software included herein is property of Watchwith, Inc and its 
 * licensors which reserve all rights, title and interest.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter implements Filter {

    @Override
    void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res
        HttpServletRequest request = (HttpServletRequest) req
        response.setHeader("Access-Control-Allow-Origin", "*")
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader("Access-Control-Allow-Headers", "x-auth-token, x-requested-with")
        if (request.method !='OPTIONS') {
            chain.doFilter(req, res)
        }
    }

    @Override
    void init(FilterConfig filterConfig) {}

    @Override
    void destroy() {}
}