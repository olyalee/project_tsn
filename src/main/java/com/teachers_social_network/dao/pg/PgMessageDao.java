package com.teachers_social_network.dao.pg;

import com.google.inject.Singleton;
import com.teachers_social_network.dao.interfaces.ConnectionPool;
import com.teachers_social_network.dao.interfaces.MessageDao;
import com.teachers_social_network.model.Message;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PostgreSQL implementation for MessageDao
 */
public class PgMessageDao implements MessageDao {
    private final ConnectionPool connectionPool;

    final static Logger logger = Logger.getLogger(PgMessageDao.class);

    @Inject
    public PgMessageDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean create(Message message) {
        Statement st = null;
        ResultSet nextID = null;
        String sql = "INSERT INTO public.messages VALUES (?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                String nextval = "SELECT nextval('message_id_seq')";
                st = connection.createStatement();
                nextID = st.executeQuery(nextval);
                nextID.next();

                ps.setInt(1, nextID.getInt(1));
                ps.setString(2, message.getFrom_user());
                ps.setString(3, message.getTo_user());
                ps.setString(4, message.getText());
                ps.setTimestamp(5, message.getTime());

                int i = ps.executeUpdate();
                if (i > 0) return true;
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query - create message from " + message.getFrom_user(), e);
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
    public List<Message> getByToUser(String login) {
        List<Message> messagesToUser = new ArrayList<>();
        String sql = "SELECT message_id, from_user, to_user, text, time FROM public.messages WHERE to_user = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, login);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Message message = Message.builder().id(rs.getInt("message_id")).from_user(rs.getString("from_user")).to_user(rs.getString("to_user")).text(rs.getString("text")).time(rs.getTimestamp("time")).build();
                    messagesToUser.add(message);
                }

            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query - get messages to " + login, e);
            }
        } catch (InterruptedException e) {
            logger.error("Couldn't get connection ", e);
        } catch (SQLException e) {
            logger.error("Couldn't get connection ", e);
        }
        return messagesToUser;
    }

    @Override
    public List<Message> getByFromUser(String login) {
        List<Message> messagesFromUser = new ArrayList<>();
        String sql = "SELECT message_id, from_user, to_user, text, time FROM public.messages WHERE from_user = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, login);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Message message = Message.builder().id(rs.getInt("message_id")).from_user(rs.getString("from_user")).to_user(rs.getString("to_user")).text(rs.getString("text")).time(rs.getTimestamp("time")).build();
                    messagesFromUser.add(message);
                }
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query - get messages from " + login, e);
            }
        } catch (InterruptedException e) {
            logger.error("Couldn't get connection ", e);
        } catch (SQLException e) {
            logger.error("Couldn't get connection ", e);
        }
        return messagesFromUser;
    }

    @Override
    public List<Message> getByToUserFromUser(String to_user, String from_user) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT message_id, from_user, to_user, text, time FROM public.messages WHERE from_user = ? AND to_user = ?;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, from_user);
                ps.setString(2, to_user);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Message message = Message.builder().id(rs.getInt("message_id")).from_user(rs.getString("from_user")).to_user(rs.getString("to_user")).text(rs.getString("text")).time(rs.getTimestamp("time")).build();
                    messages.add(message);
                }
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query - get messages to " + to_user + " from " + from_user, e);
            }
        } catch (InterruptedException e) {
            logger.error("Couldn't get connection ", e);
        } catch (SQLException e) {
            logger.error("Couldn't get connection ", e);
        }
        return messages;
    }

    @Override
    public boolean delete(Message message) {
        int i = 0;
        String sql = "DELETE FROM public.messages WHERE message_id=?;";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, message.getId());
                i = ps.executeUpdate();
            } catch (SQLException e) {
                logger.error("Couldn't execute SQL query - delete " + message.getText(), e);
            }
        }catch (InterruptedException e) {
            logger.error("Couldn't get connection ", e);
        } catch (SQLException e) {
            logger.error("Couldn't get connection ", e);
        }
        if (i > 0) return true;
        else return false;
    }
}
