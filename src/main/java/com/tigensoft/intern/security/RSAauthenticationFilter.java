package com.tigensoft.intern.security;

import lombok.extern.log4j.Log4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.PrivateKey;

@Log4j
public class RSAauthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public RSAauthenticationFilter(AuthenticationManager authenticationManager, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        super.setAuthenticationManager(authenticationManager);
        super.setAuthenticationSuccessHandler(successHandler);
        super.setAuthenticationFailureHandler(failureHandler);
    }

        @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        log.warn("*************RSAauthenticationFilter on*************");
        try {

            //RSA암호화된거 가져오기
            String encryptedUsername = request.getParameter("TGusername");
            String encryptedPassword = request.getParameter("TGpassword");
            log.warn("*************RSAauthenticationFilter username*************" + encryptedUsername);
            log.warn("*************RSAauthenticationFilter password*************" + encryptedPassword);

            //indexController에서 세션에 저장한 개인키 가져오기
            HttpSession session = request.getSession();
            PrivateKey privateKey = (PrivateKey) session.getAttribute("_RSA_WEB_Key");

            //복호화
            String username = decryptRsa(privateKey, encryptedUsername);
            String password = decryptRsa(privateKey, encryptedPassword);

            log.warn("*************RSAauthenticationFilter decryptRsa username*************" + username);
            log.warn("*************RSAauthenticationFilter decryptRsa password*************" + password);

            //UsernamePasswordAuthenticationFilter의 attemptAuthentication로 보낼 요청 수정
            HttpServletRequest modifiedRequest = new ModifiedRequest(request, username, password);

            return super.attemptAuthentication(modifiedRequest, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationServiceException("Authentication failed", e);
        }
    }

    private String decryptRsa(PrivateKey privateKey, String encryptedText) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(hexToByteArray(encryptedText));
        return new String(decryptedBytes, "UTF-8");
    }

    private byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() % 2 != 0) {
            return new byte[]{};
        }

        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            bytes[i / 2] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
        }
        return bytes;
    }

    private static class ModifiedRequest extends HttpServletRequestWrapper {
        private final String username;
        private final String password;

        public ModifiedRequest(HttpServletRequest request, String username, String password) {
            super(request);
            this.username = username;
            this.password = password;
        }

        @Override
        public String getParameter(String name) {
            if ("username".equals(name)) {
                return username;
            } else if ("password".equals(name)) {
                return password;
            }
            return super.getParameter(name);
        }
    }
}
