package com.teachers_social_network.dao.pg;

import com.google.inject.Singleton;
import com.teachers_social_network.dao.interfaces.CommunityDao;
import com.teachers_social_network.model.Community;
import com.teachers_social_network.model.Gender;
import com.teachers_social_network.model.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * PostgreSQL implementation for CommunityDao
 */
@Singleton
public class PgCommunityDao implements CommunityDao {
    Connection connection;

    final static Logger logger = Logger.getLogger(PgCommunityDao.class);

    public PgCommunityDao (Connection connection) { this.connection = connection;}

    @Override
    public boolean create(Community community) {
        Statement st = null;
        ResultSet nextID = null;
        String sql = "INSERT INTO public.communities VALUES (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            String nextval = "SELECT nextval('community_id_seq')";
            st = connection.createStatement();
            nextID = st.executeQuery(nextval);
            nextID.next();

            ps.setInt(1, nextID.getInt(1));
            ps.setString(2, community.getTitle());

            int i = ps.executeUpdate();
            if (i > 0) return true;
        } catch (SQLException e) {
            logger.error("Couldn't execute SQL query " + community.getTitle(), e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    logger.error("Closing statement error", e);
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
        return false;
    }

    @Override
    public Optional<Community> getCommunity(int id) {
        Community community;
        String sql = "SELECT * from public.communities WHERE community_id = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                community = Community.builder()
                        .id(rs.getInt("community_id"))
                        .title(rs.getString("title"))
                        .build();
                return Optional.of(community);
            }
        } catch (SQLException e) {
            logger.error("Couldn't execute SQL query " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Community> getCommunity(String title) {
        Community community;
        String sql = "SELECT * from public.communities WHERE title = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                community = Community.builder()
                        .id(rs.getInt("community_id"))
                        .title(rs.getString("title"))
                        .build();
                return Optional.of(community);
            }
        } catch (SQLException e) {
            logger.error("Couldn't execute SQL query " + title, e);
        }
        return Optional.empty();
    }

    @Override
    public boolean addMember(Community community, String login) {
        String sql = "INSERT INTO public.community_members (community_id, login) VALUES (?,?);";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, community.getId());
            ps.setString(2, login);
            int rows = ps.executeUpdate();
            if (rows > 0) return true;
        } catch (SQLException e) {
            logger.error("Couldn't execute SQL query - adding member " + login, e);
        }
        return false;
    }

    @Override
    public boolean removeMember(Community community, String login) {
        String sql = "DELETE FROM public.community_members WHERE community_id = ? AND login = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, community.getId());
            ps.setString(2, login);
            int rows = ps.executeUpdate();
            if (rows > 0) return true;
        } catch (SQLException e) {
            logger.error("Couldn't execute SQL query - removing member" + login, e);
        }
        return false;
    }

    @Override
    public List<User> getMembers(Community community) {
        List<User> members = new ArrayList<>();
        String sql = "SELECT * FROM public.users LEFT JOIN public.community_members ON users.login = community_members.login WHERE community_id = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, community.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User member = User.builder()
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
                members.add(member);
            }
        } catch (SQLException e) {
            logger.error("Couldn't execute SQL query - get members from " + community.getTitle(), e);
        }
        return members;
    }

    @Override
    public boolean isUserInCommunity(Community community, String login) {
        String sql = "SELECT * FROM public.community_members WHERE community_id = ? AND login = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, community.getId());
            ps.setString(2, login);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return true;
        } catch (SQLException e) {
            logger.error("Couldn't execute SQL query " + community.getTitle() + " " + login, e);
        }
        return false;
    }

    @Override
    public boolean update(Community community) {
        String sql = "UPDATE public.communities SET title = ? WHERE community_id = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, community.getTitle());
            ps.setInt(2, community.getId());
            int rows = ps.executeUpdate();
            if (rows > 0) return true;
        } catch (SQLException e) {
            logger.error("Couldn't execute SQL query - update community " + community.getTitle(), e);
        }
        return false;
    }

    @Override
    public boolean delete(Community community) {
        String sql = "DELETE FROM public.communities WHERE community_id = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, community.getId());
            int rows = ps.executeUpdate();
            if (rows > 0) return true;
        } catch (SQLException e) {
            logger.error("Couldn't execute SQL query - delete community" + community.getTitle(), e);
        }
        return false;
    }
}
