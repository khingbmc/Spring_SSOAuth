package com.hellokoding.sso.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKey";
    private HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String username = JwtUtil.getSubject(httpServletRequest, jwtTokenCookieName, signingKey);
        String check = httpServletRequest.getHeader("Cookie");
        System.out.println(check);
        if(username == null){
            String authService = this.getFilterConfig().getInitParameter("services.auth");
            httpServletResponse.sendRedirect(authService + "?redirect=" + httpServletRequest.getRequestURL());
        } else{
            httpServletRequest.setAttribute("username", username);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
