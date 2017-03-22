package com.teachers_social_network.service.impl;

import com.teachers_social_network.dao.interfaces.CommunityDao;
import com.teachers_social_network.model.Community;
import com.teachers_social_network.model.User;
import com.teachers_social_network.service.interfaces.CommunityService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Community Service implementation
 */
public class CommunityServiceImpl implements CommunityService {
    private final CommunityDao communityDao;

    @Inject
    public CommunityServiceImpl(CommunityDao communityDao) {
        this.communityDao = communityDao;
    }


    @Override
    public boolean create(Community community) {
        return communityDao.create(community);
    }

    @Override
    public Optional<Community> getCommunity(int id) {
        return communityDao.getCommunity(id);
    }

    @Override
    public Optional<Community> getCommunity(String title) {
        return communityDao.getCommunity(title);
    }

    @Override
    public boolean addMember(Community community, String login) {
        return communityDao.addMember(community, login);
    }

    @Override
    public boolean removeMember(Community community, String login) {
        return communityDao.removeMember(community, login);
    }

    @Override
    public List<User> getMembers(Community community) {
        return communityDao.getMembers(community);
    }

    @Override
    public boolean isUserInCommunity(Community community, String login) {
        return communityDao.isUserInCommunity(community, login);
    }

    @Override
    public boolean update(Community community) {
        return communityDao.update(community);
    }

    @Override
    public boolean delete(Community community) {
        return communityDao.delete(community);
    }

    @Override
    public List<Community> communitiesForUser(String login) {
        return communityDao.communitiesForUser(login);
    }
}
