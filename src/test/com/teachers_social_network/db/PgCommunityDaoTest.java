package com.teachers_social_network.db;

import com.teachers_social_network.dao.interfaces.CommunityDao;
import com.teachers_social_network.dao.interfaces.ConnectionPool;
import com.teachers_social_network.dao.pg.PgCommunityDao;
import com.teachers_social_network.dao.pg.PgConnectionPool;
import com.teachers_social_network.model.Community;
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
import java.util.Optional;

/**
 * Communities related tests - OK
 */
public class PgCommunityDaoTest {
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
    public void createCommTest() throws InterruptedException, SQLException {
        Community community = Community.builder().id(0).title("Математика - царица наук").build();
        boolean isCreated;
        try(Connection con = connectionPool.getConnection()){
            CommunityDao dao = new PgCommunityDao(con);
            isCreated = dao.create(community);
        }
        Assert.assertTrue(isCreated);
    }

    @Test
    public void addMemberTest() throws SQLException, InterruptedException {
        boolean isAdded = false;
        try(Connection con = connectionPool.getConnection()){
            CommunityDao dao = new PgCommunityDao(con);
            Optional<Community> communityOp = dao.getCommunity("Научные сотрудники МГУ");
            if(communityOp.isPresent()){
                Community community = communityOp.get();
                isAdded = dao.addMember(community, "alex");
                dao.addMember(community, "ivan");
            }
        }
        Assert.assertTrue(isAdded);
    }

    @Test
    public void removeMemberTest() throws SQLException, InterruptedException{
        boolean isRemoved = false;
        try(Connection con = connectionPool.getConnection()){
            CommunityDao dao = new PgCommunityDao(con);
            Optional<Community> communityOp = dao.getCommunity("Научные сотрудники МГУ");
            if(communityOp.isPresent()){
                Community community = communityOp.get();
                isRemoved = dao.removeMember(community, "alex");
                dao.removeMember(community, "ivan");
            }
        }
        Assert.assertTrue(isRemoved);
    }

    @Test
    public void checkIsMemberTest() throws InterruptedException, SQLException {
        boolean isMember;
        Community community = Community.builder().id(1).title("Математика - царица наук").build();
        try(Connection con = connectionPool.getConnection()){
            CommunityDao dao = new PgCommunityDao(con);
            isMember = dao.isUserInCommunity(community,"alex");
        }
        Assert.assertTrue(isMember);
    }

    @Test
    public void listOfMembersTest() throws InterruptedException, SQLException {
        List<User> members = new ArrayList<>();
        Community community = Community.builder().id(1).title("Математика - царица наук").build();
        try(Connection con = connectionPool.getConnection()){
            CommunityDao dao = new PgCommunityDao(con);
            members.addAll(dao.getMembers(community));
        }
        Assert.assertTrue(!members.isEmpty());
    }

    @Test
    public void updateCommTest() throws InterruptedException, SQLException {
        boolean isUpdated;
        Community community = Community.builder().id(1).title("Математика - царица наук").build();
        try(Connection con = connectionPool.getConnection()){
            CommunityDao dao = new PgCommunityDao(con);
            isUpdated = dao.update(community);
        }
        Assert.assertTrue(isUpdated);
    }

    @Test
    public void deleteCommTest()  throws SQLException, InterruptedException{
        // TO BE CHANGED!!
        int community_id = 3;
        Community community = Community.builder().id(community_id).title("Математика - царица наук").build();
        boolean isDeleted;
        try(Connection con = connectionPool.getConnection()){
            CommunityDao dao = new PgCommunityDao(con);
            isDeleted = dao.delete(community);
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
