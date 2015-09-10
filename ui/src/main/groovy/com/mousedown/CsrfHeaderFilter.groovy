package com.mousedown

import org.springframework.security.web.csrf.CsrfToken
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.WebUtils

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * <p/>
 * Copyright &copy; 2006-2015 Watchwith, Inc. The software included herein is property of Watchwith, Inc and its 
 * licensors which reserve all rights, title and interest.
 */
class CsrfHeaderFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        def csrf = request.getAttribute(CsrfToken.class.name)
        if (csrf) {
            Cookie cookie = WebUtils.getCookie(request, 'XSRF-TOKEN')
            String token = csrf.token
            if (!cookie  || token && token != cookie.value) {
                cookie = new Cookie('XSRF-TOKEN', token)
                cookie.setPath('/')
                response.addCookie(cookie)
            }
        }
        filterChain.doFilter(request, response)
    }
}