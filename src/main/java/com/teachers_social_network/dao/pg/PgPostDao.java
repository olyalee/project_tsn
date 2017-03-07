package com.teachers_social_network.dao.pg;

import com.google.inject.Singleton;
import com.teachers_social_network.dao.interfaces.ConnectionPool;
import com.teachers_social_network.dao.interfaces.PostDao;
import com.teachers_social_network.model.Community;
import com.teachers_social_network.model.Post;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PostgreSQL implementation for PostDao
 */
public class PgPostDao implements PostDao {
    private final ConnectionPool connectionPool;

    final static Logger logger = Logger.getLogger(PgPostDao.class);

    @Inject
    public PgPostDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean create(Post post) {
        Statement st = null;
        ResultSet nextID = null;
        String sql = "INSERT INTO public.community_posts VALUES (?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                String nextval = "SELECT nextval('posts_id_seq')";
                st = connection.createStatement();
                nextID = st.executeQuery(nextval);
                nextID.next();

                ps.setInt(1, nextID.getInt(1));
                ps.setInt(2, post.getCommunity_id());
                ps.setString(3, post.getLogin());
                ps.setString(4, post.getText());
                ps.setTimestamp(5, post.getTime());

                int i = ps.executeUpdate();
                if (i > 0) return true;
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query - create post from " + post.getLogin(), e);
            } finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {
                        logger.error("Couldn't close statement ", e);
                    }
                }
                if (nextID != null) {
                    try {
                        nextID.close();
                    } catch (SQLException e) {
                        logger.error("Couldn't close statement ", e);
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
    public List<Post> getPostsByCommunity(Community community) {
        List<Post> community_posts = new ArrayList<>();
        String sql = "SELECT * FROM public.community_posts WHERE community_id = ?;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, community.getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Post post = Post.builder()
                            .id(rs.getInt("post_id"))
                            .community_id(rs.getInt("community_id"))
                            .login(rs.getString("login"))
                            .text(rs.getString("text"))
                            .time(rs.getTimestamp("time")).build();
                    community_posts.add(post);
                }
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query - get posts for community - " + community.getTitle(), e);
            }
        } catch (InterruptedException e) {
            logger.error("Couldn't get connection ", e);
        } catch (SQLException e) {
            logger.error("Couldn't get connection ", e);
        }
        return community_posts;
    }

    @Override
    public List<Post> getPostsByUser(String login) {
        List<Post> community_posts = new ArrayList<>();
        String sql = "SELECT * FROM public.community_posts WHERE login = ?;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, login);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Post post = Post.builder()
                            .id(rs.getInt("post_id"))
                            .community_id(rs.getInt("community_id"))
                            .login(rs.getString("login"))
                            .text(rs.getString("text"))
                            .time(rs.getTimestamp("time")).build();
                    community_posts.add(post);
                }
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query - get posts by " + login, e);
            }
        } catch (InterruptedException e) {
            logger.error("Couldn't get connection ", e);
        } catch (SQLException e) {
            logger.error("Couldn't get connection ", e);
        }
        return community_posts;
    }

    @Override
    public List<Post> getPostsByUserForCommunity(String login, Community community) {
        List<Post> community_posts = new ArrayList<>();
        String sql = "SELECT * FROM public.community_posts WHERE login = ? AND community_id = ?;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, login);
                ps.setInt(2, community.getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Post post = Post.builder()
                            .id(rs.getInt("post_id"))
                            .community_id(rs.getInt("community_id"))
                            .login(rs.getString("login"))
                            .text(rs.getString("text"))
                            .time(rs.getTimestamp("time")).build();
                    community_posts.add(post);
                }
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query - get posts by user " + login + " for community " + community.getTitle(), e);
            }
        } catch (InterruptedException e) {
            logger.error("Couldn't get connection ", e);
        } catch (SQLException e) {
            logger.error("Couldn't get connection ", e);
        }
        return community_posts;
    }

    @Override
    public boolean update(Post post) {
        String sql = "UPDATE public.community_posts SET community_id = ?, login = ?, text = ?, time = ? WHERE post_id = ? ;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, post.getCommunity_id());
                ps.setString(2, post.getLogin());
                ps.setString(3, post.getText());
                ps.setTimestamp(4, post.getTime());
                ps.setInt(5, post.getId());
                int i = ps.executeUpdate();
                if (i > 0) return true;
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query - update post " + post.getText(), e);
            }
        } catch (InterruptedException e) {
            logger.error("Couldn't get connection ", e);
        } catch (SQLException e) {
            logger.error("Couldn't get connection ", e);
        }
        return false;
    }

    @Override
    public boolean delete(Post post) {
        int i = 0;
        String sql = "DELETE FROM public.community_posts WHERE post_id=?;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, post.getId());
                i = ps.executeUpdate();
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query - delete post " + post.getText(), e);
            }
        } catch (InterruptedException e) {
            logger.error("Couldn't get connection ", e);
        } catch (SQLException e) {
            logger.error("Couldn't get connection ", e);
        }
        if (i > 0) return true;
        else return false;
    }
}
