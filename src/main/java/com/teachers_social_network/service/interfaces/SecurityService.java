package com.teachers_social_network.service.interfaces;

/**
 * Created by Olya Lee on 04.02.2017.
 */
public interface SecurityService {
    String encrypt(String password);

    boolean validate(String password, String hash);
}
