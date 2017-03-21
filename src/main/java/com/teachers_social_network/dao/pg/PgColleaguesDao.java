package com.teachers_social_network.dao.pg;

import com.teachers_social_network.dao.interfaces.ColleaguesDao;
import com.teachers_social_network.dao.interfaces.ConnectionPool;
import com.teachers_social_network.model.Gender;
import com.teachers_social_network.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import javax.inject.Inject;

/**
 * PostgreSQL implementation for ColleaguesDao
 */

public class PgColleaguesDao implements ColleaguesDao {
    private final ConnectionPool connectionPool;
    final static Logger logger = Logger.getLogger(PgColleaguesDao.class);

    @Inject
    public PgColleaguesDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean addColleague(String user_login, String colleague_login) {
        String sql = "INSERT INTO public.colleagues (login, colleague_login) VALUES (?,?);";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, user_login);
                ps.setString(2, colleague_login);
                int rows = ps.executeUpdate();
                if (rows > 0) return true;
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query " + user_login + colleague_login, e);
            }
        } catch (InterruptedException | SQLException e) {
            logger.error("Couldn't get connection " + user_login + colleague_login, e);
        }
        return false;
    }

    @Override
    public boolean removeColleague(String user_login, String colleague_login) {
        String sql = "DELETE FROM public.colleagues WHERE login = ? AND colleague_login = ?;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, user_login);
                ps.setString(2, colleague_login);
                int rows = ps.executeUpdate();
                if (rows > 0) return true;
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query " + user_login + colleague_login, e);
            }
        } catch (InterruptedException | SQLException e) {
            logger.error("Couldn't get connection " + user_login + colleague_login, e);
        }
        return false;
    }

    @Override
    public boolean isColleague(String user_login, String colleague_login) {
        String sql = "SELECT * from public.colleagues WHERE login = ? AND colleague_login = ?;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, user_login);
                ps.setString(2, colleague_login);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) return true;
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query " + user_login + colleague_login, e);
            }
        } catch (InterruptedException | SQLException e) {
            logger.error("Couldn't get connection " + user_login + colleague_login, e);
        }
        return false;
    }

    @Override
    public List<User> listOfColleagues(String user_login) {
        List<User> colleagues = new ArrayList<>();
        String sql = "SELECT * FROM public.users LEFT JOIN public.colleagues ON users.login = colleagues.colleague_login WHERE colleagues.login = ?;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, user_login);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    User colleague = User.builder()
                            .login(rs.getString("login"))
                            .passwordHash(rs.getString("password_hash"))
                            .firstName(rs.getString("first_name"))
                            .lastName(rs.getString("last_name"))
                            .gender(Gender.valueOf(rs.getString("gender")))
                            .email(rs.getString("email"))
                            .country(rs.getString("country"))
                            .city(rs.getString("city"))
                            .science_field(rs.getString("science_field"))
                            .working_place(rs.getString("working_place"))
                            .position(rs.getString("position")).build();
                    colleagues.add(colleague);
                }
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query " + user_login, e);
            }
        } catch (InterruptedException | SQLException e) {
            logger.error("Couldn't get connection " + user_login, e);
        }
        return colleagues;
    }
}
