package com.teachers_social_network.db;

import com.teachers_social_network.dao.interfaces.ConnectionPool;
import com.teachers_social_network.dao.interfaces.UserDao;
import com.teachers_social_network.dao.pg.PgConnectionPool;
import com.teachers_social_network.dao.pg.PgUserDao;
import com.teachers_social_network.model.Gender;
import com.teachers_social_network.model.User;
import org.junit.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;
import java.util.*;

/**
 * Tests for all PgUserDao methods (CRUD) - OK
 */
public class PgUserDaoTest {

    private static ConnectionPool connectionPool;

    @BeforeClass
    public static void startup() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        final List<Connection> connections = new ArrayList<>(5);
        for (int i=0; i<5; i++){
            connections.add(DriverManager.getConnection("jdbc:postgresql://localhost:5433/tsn","postgres","postgres"));
        }
        connectionPool = new PgConnectionPool(connections);
    }

    @Test
    public void testAddUser() throws InterruptedException, SQLException {
        User user = User.builder().birthDate(Date.valueOf("1963-09-01")).city("Moscow").country("Russia").email("alexalex@gmail.ru").firstName("Alexandr").lastName("Alexeev").login("alex").gender(Gender.MALE).passwordHash("alexalex").science_field("Math").working_place("MGU").position("lecturer").build();
        boolean b = false;
        try(Connection con = connectionPool.getConnection()){
            UserDao userDao = new PgUserDao(con);
            b = userDao.create(user);
        }
        Assert.assertTrue(b==true);
    }

    @Test
    public void testUpdateUser() throws InterruptedException, SQLException {
        User user = User.builder().birthDate(Date.valueOf("1971-11-11")).position("lecturer").working_place("MGU").city("Moscow").country("Russia").email("ivan@gmail.ru").firstName("Ivan").lastName("Ivanov").login("ivan").gender(Gender.MALE).passwordHash("ivanivan").science_field("Astronomy").build();
        boolean wasUpdated = false;
        try(Connection con = connectionPool.getConnection()){
            UserDao userDao = new PgUserDao(con);
            wasUpdated = userDao.update(user);
        }
        Assert.assertTrue(wasUpdated==true);
    }

    @Test
    public void testGetUser() throws InterruptedException, SQLException {
        User user = null;
        try(Connection con = connectionPool.getConnection()) {
            UserDao userDao = new PgUserDao(con);
            Optional<User> userOptional = userDao.getByLogin("alex");
            if(userOptional.isPresent()){
                user = userOptional.get();
            }
        }
        Assert.assertNotNull(user);
    }

    @Test
    public void testDeleteUser() throws InterruptedException, SQLException {
        User user = null;
        boolean wasDeleted = false;
        try(Connection con = connectionPool.getConnection()){
            UserDao userDao = new PgUserDao(con);
            wasDeleted = userDao.delete("alex");
        }
        Assert.assertTrue(wasDeleted);
    }

    @AfterClass
    public static void shutdown(){
        try {
            connectionPool.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}