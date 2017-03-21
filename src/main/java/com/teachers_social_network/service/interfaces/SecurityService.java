package com.teachers_social_network.service.interfaces;

/**
 * Security Service
 * Password hashing and validation
 */
public interface SecurityService {
    String encrypt(String password);

    boolean validate(String password, String hash);
}
