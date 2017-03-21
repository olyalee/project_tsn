package com.teachers_social_network.dao.interfaces;

import com.teachers_social_network.model.Community;
import com.teachers_social_network.model.Post;
import com.teachers_social_network.model.User;

import java.util.List;
import java.util.Optional;

/**
 * DAO for Communities Entity
 */
public interface CommunityDao {

    /**add new community to the DB*/
    boolean create(Community community);

    /**return optional of community by its ID*/
    Optional<Community> getCommunity(int id);

    /**return optional of community by its title*/
    public Optional<Community> getCommunity(String title);

    /**add member in community*/
    boolean addMember(Community community, String login);

    /**remove member from community*/
    boolean removeMember(Community community, String login);

    /**return all users of community*/
    List<User> getMembers(Community community);

    /**return true if user is in the community*/
    boolean isUserInCommunity (Community community, String login);

    /**update community info*/
    boolean update(Community community);

    /**delete community from DB*/
    boolean delete(Community community);

    /**return all communities where user is member*/
    List<Community> communitiesForUser(String login);
}
