package com.teachers_social_network.service.interfaces;

import com.teachers_social_network.model.Message;

import java.util.List;

/**
 * Created by Olya Lee on 11.03.2017.
 */

public interface MessageService {
    /**add new row to the DB's*/
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
