package com.teachers_social_network.dao.pg;


import com.google.inject.Singleton;
import com.teachers_social_network.dao.interfaces.UserDao;
import com.teachers_social_network.model.Gender;
import com.teachers_social_network.model.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Optional;

/**
 * PostgreSQL implementation for UserDao
 */
@Singleton
public class PgUserDao implements UserDao {
    private Connection connection;

    final static Logger logger = Logger.getLogger(PgUserDao.class);

    public PgUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> getByLogin(String login) {
        User user;
        String sql = "SELECT * FROM public.users WHERE login = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = User.builder().birthDate(rs.getDate("birth_date"))
                        .city(rs.getString("city"))
                        .country(rs.getString("country"))
                        .email(rs.getString("email"))
                        .firstName(rs.getString("first_name"))
                        .lastName(rs.getString("last_name"))
                        .login(rs.getString("login"))
                        .gender(Gender.valueOf((rs.getString("gender")).toUpperCase()))
                        .science_field(rs.getString("science_field"))
                        .working_place(rs.getString("working_place"))
                        .position(rs.getString("position"))
                        .passwordHash(rs.getString("password_hash")).build();
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error("Couldn't execute SQL query - get user " + login, e);
        }

        return Optional.empty();
//        if (!"test".equals(login)) {
//            return Optional.empty();
//        }
//
//        return Optional.of(User.builder().firstName("John").lastName("Doe").gender(Gender.MALE).birthDate(new Date()).email("ya@mail.ru").login("test@test.ru").passwordHash(securityService.encrypt("test")).build());
    }

    @Override
    public boolean create(User user) {
        String sql = "INSERT INTO public.users VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPasswordHash());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, String.valueOf(user.getGender()));
            ps.setDate(6, user.getBirthDate());
            ps.setString(7, user.getEmail());
            ps.setString(8, user.getCountry());
            ps.setString(9, user.getCity());
            ps.setString(10, user.getScience_field());
            ps.setString(11, user.getWorking_place());
            ps.setString(12, user.getPosition());
            int i = ps.executeUpdate();
            if (i > 0) return true;
        } catch (SQLException e) {
            logger.error("Couldn't execute SQL query - create user " + user.getLogin(), e);
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        String sql = "UPDATE public.users SET password_hash = ?, first_name = ?, last_name = ?, gender = ?, birth_date = ?, email = ?, country = ?, city = ?, science_field = ?, working_place = ?, position = ? WHERE login = ? ;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, user.getPasswordHash());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, String.valueOf(user.getGender()));
            ps.setDate(5, user.getBirthDate());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getCountry());
            ps.setString(8, user.getCity());
            ps.setString(9, user.getScience_field());
            ps.setString(10, user.getWorking_place());
            ps.setString(11, user.getPosition());
            ps.setString(12, user.getLogin());
            int i = ps.executeUpdate();
            if (i > 0) return true;
        } catch (SQLException e) {
            logger.error("Couldn't execute SQL query - update user " + user.getLogin(), e);
        }
        return false;
    }

    @Override
    public boolean delete(String login) {
        int i = 0;
        String sql = "DELETE FROM public.users WHERE login = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Couldn't execute SQL query - delete user " + login, e);
        }
        if (i > 0) return true;
        else return false;
    }
}
