package com.tigensoft.intern.security;

import lombok.extern.log4j.Log4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Log4j
public class SHA512PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {

        log.warn("**************encode***************");
        return hashedSHA512(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String hashedPassword = encode(rawPassword);

        log.warn("**************matches***************" + encodedPassword.equals(hashedPassword));

        return encodedPassword.equals(hashedPassword);
    }

    private String hashedSHA512(String input) {
        StringBuilder result = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] digested = md.digest(input.getBytes());
            for (int i = 0; i < digested.length; i++) {
                result.append(Integer.toHexString(0xFF & digested[i]));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return result.toString();
    }
}
