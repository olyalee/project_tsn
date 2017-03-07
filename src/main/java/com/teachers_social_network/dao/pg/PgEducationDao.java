package com.teachers_social_network.dao.pg;

import com.google.inject.Singleton;
import com.teachers_social_network.dao.interfaces.ConnectionPool;
import com.teachers_social_network.dao.interfaces.EducationDao;
import com.teachers_social_network.model.Education;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PostgreSQL implementation for EducationDao
 */

public class PgEducationDao implements EducationDao {
    private final ConnectionPool connectionPool;

    final static Logger logger = Logger.getLogger(PgEducationDao.class);

    @Inject
    public PgEducationDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean create(Education education) {
        Statement st = null;
        ResultSet nextID = null;
        String sql = "INSERT INTO public.education (education_id, login, education_type, place_type, place_title, major, start_year, end_year) VALUES (?,?,?,?,?,?,?,?);";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                String nextval = "SELECT nextval('education_id_seq')";
                st = connection.createStatement();
                nextID = st.executeQuery(nextval);
                nextID.next();

                ps.setInt(1, nextID.getInt(1));
                ps.setString(2, education.getLogin());
                ps.setString(3, education.getEducationType());
                ps.setString(4, education.getPlaceType());
                ps.setString(5, education.getPlaceTitle());
                ps.setString(6, education.getMajor());
                ps.setInt(7, education.getStartYear());
                ps.setInt(8, education.getEndYear());
                int rows = ps.executeUpdate();
                if (rows > 0) return true;
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query - create education for " + education.getLogin(), e);
            } finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {
                        logger.error("Closing statement error ", e);
                    }
                }
                if (nextID != null) {
                    try {
                        nextID.close();
                    } catch (SQLException e) {
                        logger.error("Closing statement error", e);
                    }
                }
            }
        } catch (InterruptedException e) {
            logger.error("Couldn't get connection ", e);
        } catch (SQLException e) {
            logger.error("Couldn't get connection ", e);
        }
        return false;
    }

    @Override
    public List<Education> getByLogin(String login) {
        List<Education> user_educations = new ArrayList<>();
        String sql = "SELECT * FROM public.education WHERE login = ?;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, login);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Education education = Education.builder()
                            .id(rs.getInt("education_id"))
                            .login(rs.getString("login"))
                            .educationType(rs.getString("education_type"))
                            .placeType(rs.getString("place_type"))
                            .placeTitle(rs.getString("place_title"))
                            .major(rs.getString("major"))
                            .startYear(rs.getInt("start_year"))
                            .endYear(rs.getInt("end_year")).build();
                    user_educations.add(education);
                }
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query " + login, e);
            }
        } catch (InterruptedException e) {
            logger.error("Couldn't get connection ", e);
        } catch (SQLException e) {
            logger.error("Couldn't get connection ", e);
        }
        return user_educations;
    }

    @Override
    public boolean update(Education education) {
        String sql = "UPDATE public.education SET education_type = ?, place_type = ?, start_year = ?, end_year = ? WHERE login = ? AND place_title = ? AND major = ? ;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, education.getEducationType());
                ps.setString(2, education.getPlaceType());
                ps.setInt(3, education.getStartYear());
                ps.setInt(4, education.getEndYear());
                ps.setString(5, education.getLogin());
                ps.setString(6, education.getPlaceTitle());
                ps.setString(7, education.getMajor());
                int rows = ps.executeUpdate();
                if (rows > 0) return true;
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query - update education for " + education.getLogin(), e);
            }
        } catch (InterruptedException e) {
            logger.error("Couldn't get connection ", e);
        } catch (SQLException e) {
            logger.error("Couldn't get connection ", e);
        }
        return false;
    }

    @Override
    public boolean delete(Education education) {
        String sql = "DELETE FROM public.education WHERE education_id = ?;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, education.getId());
                int rows = ps.executeUpdate();
                if (rows > 0) return true;
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query - delete education for " + education.getLogin(), e);
            }
        } catch (InterruptedException e) {
            logger.error("Couldn't get connection ", e);
        } catch (SQLException e) {
            logger.error("Couldn't get connection ", e);
        }
        return false;
    }
}
