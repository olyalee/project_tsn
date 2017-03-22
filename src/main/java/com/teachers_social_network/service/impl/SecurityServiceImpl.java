package com.teachers_social_network.service.impl;

import com.teachers_social_network.service.interfaces.SecurityService;
import org.apache.log4j.Logger;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Security Service implementation
 */
public class SecurityServiceImpl implements SecurityService {
    final static Logger logger = Logger.getLogger(SecurityServiceImpl.class);

    @Override
    public String encrypt(String password) {
//        logger.info("secure the pass");
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
//        logger.info("validate");
        return hash.equals(encrypt(password));

    }
}
