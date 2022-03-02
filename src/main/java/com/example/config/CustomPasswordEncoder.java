package com.example.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString().replace(rawPassword.charAt(0), 'B');

    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().replace(rawPassword.charAt(0), 'B').equals(encodedPassword);
    }
}
