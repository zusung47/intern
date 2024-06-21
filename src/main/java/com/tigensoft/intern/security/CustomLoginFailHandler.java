package com.tigensoft.intern.security;

import lombok.extern.log4j.Log4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Service
@Log4j
public class CustomLoginFailHandler extends SimpleUrlAuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.warn("*********login failed***********CustomLoginFailHandler*********");

        try {
            String loginFailMsg = null;
            if (exception instanceof BadCredentialsException) {
                loginFailMsg =  "아이디 또는 비밀번호를 확인해주세요.";
            } else if (exception instanceof AuthenticationServiceException) {
                loginFailMsg = "죄송합니다. 시스템에 오류가 발생했습니다.";
            } else {
                loginFailMsg = "계정을 찾을 수 없습니다.";
            }

            loginFailMsg = URLEncoder.encode(loginFailMsg, "UTF-8");
            setDefaultFailureUrl("/login/error?error=true&exception=" + loginFailMsg);

            super.onAuthenticationFailure(request, response, exception);

        } catch (Exception e) {
            log.error("error in fail", e);
            throw e;
        }
    }
}
