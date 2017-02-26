package com.teachers_social_network.service.impl;

import com.teachers_social_network.service.interfaces.SecurityService;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Olya Lee on 04.02.2017.
 */
public class SecurityServiceImpl implements SecurityService {
    @Override
    public String encrypt(String password) {
        final Charset charset = Charset.forName("UTF-8");
        try {
            return new String(MessageDigest.getInstance("MD5").digest(password.getBytes(charset)),charset);
        } catch (NoSuchAlgorithmException e) {
            // TODO add throws to method signature
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean validate(String password, String hash) {

        return hash.equals(encrypt(password));

    }
}
