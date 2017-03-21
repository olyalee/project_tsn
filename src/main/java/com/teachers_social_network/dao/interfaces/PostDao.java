package com.teachers_social_network.dao.interfaces;

import com.teachers_social_network.model.Community;
import com.teachers_social_network.model.Post;

import java.util.List;

/**
 * DAO for Posts Entity
 */
public interface PostDao {
    /**add new post to the DB*/
    boolean create(Post post);

    /**return all post for community*/
    List<Post> getPostsByCommunity(Community community);

    /**return all post from user*/
    List<Post> getPostsByUser(String login);

    /**return all posts from user for community*/
    List<Post> getPostsByUserForCommunity(String login, Community community);

    /**update post*/
    boolean update(Post post);

    /**delete post from DB*/
    boolean delete(Post post);
}
