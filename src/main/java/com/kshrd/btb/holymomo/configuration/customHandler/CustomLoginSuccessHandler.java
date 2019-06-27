package com.kshrd.btb.holymomo.configuration.customHandler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        try {
            if (httpServletRequest.getSession().getAttribute("REDIRECT_URL") != null){
                httpServletResponse.sendRedirect(httpServletRequest.getSession().getAttribute("REDIRECT_URL").toString());
            }else {
                httpServletResponse.sendRedirect("/");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

