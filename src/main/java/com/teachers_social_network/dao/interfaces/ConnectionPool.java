package com.teachers_social_network.dao.interfaces;

import java.sql.Connection;

/**
 * ConnectionPool interface
 */
public interface ConnectionPool extends AutoCloseable {
    Connection getConnection() throws InterruptedException;
}
