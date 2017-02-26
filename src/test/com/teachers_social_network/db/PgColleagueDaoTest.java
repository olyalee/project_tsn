package com.teachers_social_network.db;

import com.teachers_social_network.dao.interfaces.ColleaguesDao;
import com.teachers_social_network.dao.interfaces.ConnectionPool;
import com.teachers_social_network.dao.pg.PgColleaguesDao;
import com.teachers_social_network.dao.pg.PgConnectionPool;
import com.teachers_social_network.model.Colleagues;
import com.teachers_social_network.model.User;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Test for PgColleagueDao methods - OK
 */
public class PgColleagueDaoTest {
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
    public void addCollTest() throws InterruptedException, SQLException {
        boolean isAddedA;
        boolean isAddedI;
        try(Connection con = connectionPool.getConnection()){
            ColleaguesDao dao = new PgColleaguesDao(con);
            isAddedA = dao.addColleague("alex","ivan");
            isAddedI = dao.addColleague("ivan","alex");
        }
        Assert.assertTrue(isAddedA);
        Assert.assertTrue(isAddedI);
    }

    @Test
    public void removeCollTest() throws InterruptedException, SQLException {
        boolean isRemovedA;
        boolean isRemovedI;
        try(Connection con = connectionPool.getConnection()){
            ColleaguesDao dao = new PgColleaguesDao(con);
            isRemovedA = dao.removeColleague("alex","ivan");
            isRemovedI = dao.removeColleague("ivan","alex");
        }
        Assert.assertTrue(isRemovedA);
        Assert.assertTrue(isRemovedI);
    }

    @Test
    public void isCollTest() throws InterruptedException, SQLException {
        boolean isColl;
        try(Connection con = connectionPool.getConnection()) {
            ColleaguesDao dao = new PgColleaguesDao(con);
            isColl = dao.isColleague("alex","ivan");
        }
        Assert.assertTrue(isColl);
    }

    @Test
    public void listOfCollTes() throws InterruptedException, SQLException {
        List<User> colleagues = new ArrayList<>();
        try(Connection con = connectionPool.getConnection()){
            ColleaguesDao dao = new PgColleaguesDao(con);
            colleagues = dao.listOfColleagues("alex");
        }
        Assert.assertTrue(!colleagues.isEmpty());
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
