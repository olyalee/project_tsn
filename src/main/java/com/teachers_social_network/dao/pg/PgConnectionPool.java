package com.teachers_social_network.dao.pg;

import com.teachers_social_network.dao.interfaces.ConnectionPool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

/**
 * PostgreSQL implementation for ConnectionPool
 */

public class PgConnectionPool implements ConnectionPool {
    private final BlockingQueue<Connection> connections;
    private final List<Connection> allConnections;
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    private static int capacity;

    public PgConnectionPool() {

        final Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db/db.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
        capacity = Integer.parseInt(properties.getProperty("poolsize", "5"));

        this.connections = new ArrayBlockingQueue<>(capacity, false);
        allConnections = new ArrayList<>(capacity);  //  allConnections = new ArrayList<>(connections);

        try {
            Class.forName(driver);
            for (int i = 0; i < capacity; i++) {
                final Connection connection = DriverManager.getConnection(url, user, password);
                connections.add(connection);   //added
                allConnections.add(connection);  //added
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws InterruptedException {
        return new PooledConnection(connections.take());
    }

    private void returnConnection(Connection connection) {
        connections.add(connection);
    }

    @Override
    public void close() throws Exception {
        for (Connection connection : allConnections) {
            connection.close();
        }
    }

    private class PooledConnection implements Connection {
        private final Connection inner;
        private boolean isClosed = false;

        public PooledConnection(Connection inner) {
            this.inner = inner;
        }


        @Override
        public void close() throws SQLException {
//            inner.setAutoCommit(false);
            inner.setAutoCommit(true);
            inner.setReadOnly(false);
            inner.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            inner.clearWarnings();
            returnConnection(inner);
            isClosed = true;
        }

        @Override
        public boolean isClosed() throws SQLException {
            return isClosed;
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {

        }

        @Override
        public Statement createStatement() throws SQLException {
            return inner.createStatement();
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return inner.prepareStatement(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return inner.prepareCall(sql);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return inner.nativeSQL(sql);
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            inner.setAutoCommit(autoCommit);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return inner.getAutoCommit();
        }

        @Override
        public void commit() throws SQLException {
            inner.commit();
        }

        @Override
        public void rollback() throws SQLException {
            inner.rollback();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return inner.getMetaData();
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            inner.setReadOnly(readOnly);
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return inner.isReadOnly();
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            inner.setTransactionIsolation(level);
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return inner.getTransactionIsolation();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return inner.getWarnings();
        }

        @Override
        public void clearWarnings() throws SQLException {
            inner.clearWarnings();
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return inner.createStatement(resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return inner.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return inner.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return inner.getTypeMap();
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            inner.setTypeMap(map);
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            inner.setHoldability(holdability);
        }

        @Override
        public int getHoldability() throws SQLException {
            return inner.getHoldability();
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return inner.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return inner.setSavepoint(name);
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {
            inner.rollback(savepoint);
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            inner.releaseSavepoint(savepoint);
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return inner.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return inner.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return inner.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return inner.prepareStatement(sql, autoGeneratedKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return inner.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return inner.prepareStatement(sql, columnNames);
        }

        @Override
        public Clob createClob() throws SQLException {
            return inner.createClob();
        }

        @Override
        public Blob createBlob() throws SQLException {
            return inner.createBlob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return inner.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return inner.createSQLXML();
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return inner.isValid(timeout);
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            inner.setClientInfo(name, value);
        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            inner.setClientInfo(properties);
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return inner.getClientInfo(name);
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return inner.getClientInfo();
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return inner.createArrayOf(typeName, elements);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return inner.createStruct(typeName, attributes);
        }

        @Override
        public void setSchema(String schema) throws SQLException {
            inner.setSchema(schema);
        }

        @Override
        public String getSchema() throws SQLException {
            return inner.getSchema();
        }

        @Override
        public void abort(Executor executor) throws SQLException {
            inner.abort(executor);
        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
            inner.setNetworkTimeout(executor, milliseconds);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return inner.getNetworkTimeout();
        }

        @Override
        public String getCatalog() throws SQLException {
            return null;
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return inner.unwrap(iface);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return inner.isWrapperFor(iface);
        }
    }
}
