package com.teachers_social_network.db;

import com.teachers_social_network.dao.interfaces.ConnectionPool;
import com.teachers_social_network.dao.interfaces.EducationDao;
import com.teachers_social_network.dao.interfaces.MessageDao;
import com.teachers_social_network.dao.pg.PgConnectionPool;
import com.teachers_social_network.dao.pg.PgMessageDao;
import com.teachers_social_network.model.Message;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Tests for PgMessageDao methods - OK
 */
public class PgMessageDaoTest {
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
    public void createMessageTest() throws InterruptedException, SQLException {
        boolean mIsCreated;
        boolean rIsCreated;
        Calendar calendar = Calendar.getInstance();
        Message message = Message.builder().id(0).from_user("elena").to_user("alex").text("Добрый день! Приглашаю на конференцию.").time(new Timestamp(calendar.getTimeInMillis())).build();
        Message reply = Message.builder().id(0).from_user("alex").to_user("elena").text("День добрый! Приглашение принимается.").time(new Timestamp(calendar.getTimeInMillis())).build();
        try(Connection con = connectionPool.getConnection()){
            MessageDao messageDao = new PgMessageDao(con);
            mIsCreated = messageDao.create(message);
            rIsCreated = messageDao.create(reply);
        }
        Assert.assertTrue(mIsCreated);
        Assert.assertTrue(rIsCreated);
    }

    @Test
    public void getMessageFromTest() throws InterruptedException, SQLException {
        List<Message> list = new ArrayList<>();
        try(Connection con = connectionPool.getConnection()){
            MessageDao messageDao = new PgMessageDao(con);
            list = messageDao.getByFromUser("elena");
        }
        Assert.assertTrue(!list.isEmpty());
    }

    @Test
    public void getMessageToTest() throws InterruptedException, SQLException {
        List<Message> list = new ArrayList<>();
        try(Connection con = connectionPool.getConnection()){
            MessageDao messageDao = new PgMessageDao(con);
            list = messageDao.getByToUser("alex");
        }
        Assert.assertTrue(!list.isEmpty());
    }

    @Test
    public void getMessageToFromTest() throws InterruptedException, SQLException {
        List<Message> list = new ArrayList<>();
        try(Connection con = connectionPool.getConnection()){
            MessageDao messageDao = new PgMessageDao(con);
            list = messageDao.getByToUserFromUser("alex","elena");
        }
        Assert.assertTrue(!list.isEmpty());
    }

    @Test
    public void deleteMessageTest() throws InterruptedException, SQLException {
        boolean mIsDeleted;
        boolean rIsDeleted;
        // TO BE CHANGED!!
        int message_id = 3;
        // TO BE CHANGED!!
        int reply_id = 4;
        Calendar calendar = Calendar.getInstance();
        Message message = Message.builder().id(message_id).from_user("elena").to_user("alex").text("Добрый день! Приглашаю на конференцию.").time(new Timestamp(calendar.getTimeInMillis())).build();
        Message reply = Message.builder().id(reply_id).from_user("alex").to_user("elena").text("День добрый! Приглашение принимается.").time(new Timestamp(calendar.getTimeInMillis())).build();
        try(Connection con = connectionPool.getConnection()){
            MessageDao messageDao = new PgMessageDao(con);
            mIsDeleted = messageDao.delete(message);
            rIsDeleted = messageDao.delete(reply);
        }
        Assert.assertTrue(mIsDeleted);
        Assert.assertTrue(rIsDeleted);
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
