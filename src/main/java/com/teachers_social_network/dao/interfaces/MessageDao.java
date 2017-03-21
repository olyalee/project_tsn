package com.teachers_social_network.dao.interfaces;

import com.teachers_social_network.model.Message;

import java.util.List;
import java.util.Optional;

/**
 * DAO for Messages Entity
 */
public interface MessageDao {
    /**add new message to the DB*/
    boolean create(Message message);

    /**return all messages for user*/
    List<Message> getByToUser(String login);

    /**return all messages from user*/
    List<Message> getByFromUser(String login);

    /**return all messages to user from user*/
    List<Message> getByToUserFromUser(String to_user, String from_user);

    /**delete message from DB*/
    boolean delete(Message message);
}
