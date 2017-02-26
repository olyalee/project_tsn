package com.teachers_social_network.db;

import com.teachers_social_network.dao.interfaces.ConnectionPool;
import com.teachers_social_network.dao.interfaces.EducationDao;
import com.teachers_social_network.dao.pg.PgConnectionPool;
import com.teachers_social_network.dao.pg.PgEducationDao;
import com.teachers_social_network.model.Education;
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
 * Tests for all PgEducationDao methods (CRUD) - OK
 */
public class PgEducationDaoTest {
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
    public void createEduTest() throws InterruptedException, SQLException {
        boolean isCreated;
//        Education education = Education.builder().id(0).login("ivan").educationType("специалитет").placeType("Университет").placeTitle("МГУ").major("Астрономия")
//                .startYear(1989).endYear(1990).build();
        Education education = Education.builder().id(0).login("alex").educationType("магистратура").placeType("Университет").placeTitle("МГУ").major("Прикладная математика")
                .startYear(1989).endYear(1993).build();
        try(Connection con = connectionPool.getConnection()){
            EducationDao educationDao = new PgEducationDao(con);
            isCreated = educationDao.create(education);
        }
        Assert.assertTrue(isCreated);
    }

    @Test
    public void getEduByLogin() throws InterruptedException, SQLException {
        List<Education> list = new ArrayList<>();
        try(Connection con = connectionPool.getConnection()){
            EducationDao educationDao = new PgEducationDao(con);
            list = educationDao.getByLogin("elena");
        }
        Assert.assertTrue(!list.isEmpty());
    }

    @Test
    public void updateEduTest() throws InterruptedException, SQLException {
        boolean isUpdated;
        Education education = Education.builder().id(0).login("ivan").educationType("специалитет").placeType("Университет").placeTitle("МГУ").major("Астрономия")
                .startYear(1989).endYear(1994).build();
        try(Connection con = connectionPool.getConnection()){
            EducationDao dao = new PgEducationDao(con);
            isUpdated = dao.update(education);
        }
        Assert.assertTrue(isUpdated);
    }

    @Test
    public void deleteEduTest() throws InterruptedException, SQLException {
        boolean isDeleted;
        Education education = Education.builder().id(4).login("alex").educationType("магистратура").placeType("Университет").placeTitle("МГУ").major("Прикладная математика")
                .startYear(1988).endYear(1993).build();
        try(Connection con = connectionPool.getConnection()){
            EducationDao dao = new PgEducationDao(con);
            isDeleted = dao.delete(education);
        }
        Assert.assertTrue(isDeleted);
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
