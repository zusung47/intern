package com.tigensoft.intern.security;

import lombok.extern.log4j.Log4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    //접근 제한이 된 경우에 다양한 처리를 하고 싶다면 직접 AccessDeniedHandler 구현

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessException) throws IOException, ServletException {

        log.error("ACCESS Denied Handler");

        log.error("Redirect....");

        response.sendRedirect("views/accessError.jsp");
    }
}
