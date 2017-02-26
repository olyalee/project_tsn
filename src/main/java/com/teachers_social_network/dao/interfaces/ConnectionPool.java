package com.teachers_social_network.dao.interfaces;

import java.sql.Connection;

/**
 * Created by Olya Lee on 09.02.2017.
 */
public interface ConnectionPool extends AutoCloseable {
    Connection getConnection() throws InterruptedException;
}
