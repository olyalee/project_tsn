package com.teachers_social_network.db;

import com.teachers_social_network.dao.interfaces.ConnectionPool;
import com.teachers_social_network.dao.interfaces.PostDao;
import com.teachers_social_network.dao.pg.PgConnectionPool;
import com.teachers_social_network.dao.pg.PgPostDao;
import com.teachers_social_network.model.Community;
import com.teachers_social_network.model.Post;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



/**
 * Tests for PgPostDao methods
 */
public class PgPostDaoTest {
    private static ConnectionPool connectionPool;

    @BeforeClass
    public static void startup() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        final List<Connection> connections = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            connections.add(DriverManager.getConnection("jdbc:postgresql://localhost:5433/tsn", "postgres", "postgres"));
        }
        connectionPool = new PgConnectionPool(connections);
    }

    @Test
    public void addPostTest() throws InterruptedException, SQLException {
        boolean isAdded;
        Calendar calendar = Calendar.getInstance();
        Post post = Post.builder().id(0).community_id(1).login("elena").text("1 марта состоится конференция, посвящённая современным открытиям в области математики. Присоединяйтесь!")
                .time(new Timestamp(calendar.getTimeInMillis())).build();
        try(Connection con = connectionPool.getConnection()){
            PostDao dao = new PgPostDao(con);
            isAdded = dao.create(post);
        }
        Assert.assertTrue(isAdded);
    }

    @Test
    public void updatePostTest() throws InterruptedException, SQLException {
        boolean isUpdated;
        Calendar calendar = Calendar.getInstance();
        Post post = Post.builder().id(2).community_id(1).login("elena").text("Российские школьники заняли 24 место в мире по уровню знаний в математике.")
                .time(new Timestamp(calendar.getTimeInMillis())).build();
        try(Connection con = connectionPool.getConnection()){
            PostDao dao = new PgPostDao(con);
            isUpdated = dao.update(post);
        }
        Assert.assertTrue(isUpdated);
    }

    @Test
    public void deletePostTest() throws InterruptedException, SQLException {
        boolean isDeleted;
        Calendar calendar = Calendar.getInstance();
        Post post = Post.builder().id(2).community_id(1).login("elena").text("1 марта состоится конференция, посвящённая современным открытиям в области математики. Присоединяйтесь!")
                .time(new Timestamp(calendar.getTimeInMillis())).build();
        try(Connection con = connectionPool.getConnection()){
            PostDao dao = new PgPostDao(con);
            isDeleted = dao.delete(post);
        }
        Assert.assertTrue(isDeleted);
    }

    @Test
    public void getPostsByCommunityTest() throws InterruptedException, SQLException {
        List<Post> postsByComm = new ArrayList<>();
        Community community = Community.builder().id(1).title("Математика - царица наук").build();
        try(Connection con = connectionPool.getConnection()){
            PostDao dao = new PgPostDao(con);
            postsByComm = dao.getPostsByCommunity(community);
        }
        Assert.assertTrue(postsByComm.get(0).getId()==1);
    }

    @Test
    public void getPostsByUserTest() throws InterruptedException, SQLException {
        List<Post> postsByUser = new ArrayList<>();
        try(Connection con = connectionPool.getConnection()){
            PostDao dao = new PgPostDao(con);
            postsByUser = dao.getPostsByUser("elena");
        }
        Assert.assertTrue(postsByUser.get(0).getLogin().equals("elena"));
    }

    @Test
    public void getPostsByUserAndCommunityTest() throws InterruptedException, SQLException {
        List<Post> posts = new ArrayList<>();
        Community community = Community.builder().id(1).title("Математика - царица наук").build();
        try(Connection con = connectionPool.getConnection()){
            PostDao dao = new PgPostDao(con);
            posts = dao.getPostsByUserForCommunity("elena",community);
        }
        Assert.assertTrue(posts.get(0).getId()==1 && posts.get(0).getLogin().equals("elena"));
    }

    @AfterClass
    public static void shutdown() {
        try {
            connectionPool.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
