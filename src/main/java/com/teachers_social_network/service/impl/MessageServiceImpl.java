package com.teachers_social_network.service.impl;

import com.teachers_social_network.dao.interfaces.MessageDao;
import com.teachers_social_network.model.Message;
import com.teachers_social_network.service.interfaces.MessageService;

import javax.inject.Inject;
import java.util.List;

/**
 * Message Service implementation
 */
public class MessageServiceImpl implements MessageService {
    private final MessageDao messageDao;

    @Inject
    public MessageServiceImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public boolean create(Message message) {
        return messageDao.create(message);
    }

    @Override
    public List<Message> getByToUser(String login) {
        return messageDao.getByToUser(login);
    }

    @Override
    public List<Message> getByFromUser(String login) {
        return messageDao.getByFromUser(login);
    }

    @Override
    public List<Message> getByToUserFromUser(String to_user, String from_user) {
        return messageDao.getByToUserFromUser(to_user, from_user);
    }

    @Override
    public boolean delete(Message message) {
        return messageDao.delete(message);
    }
}
