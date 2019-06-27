package com.kshrd.btb.holymomo.configuration.customHandler;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) {
        httpServletRequest.getSession().setAttribute("REDIRECT_URL",httpServletRequest.getRequestURL());
        try {
            httpServletResponse.sendRedirect("/custom-login");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
