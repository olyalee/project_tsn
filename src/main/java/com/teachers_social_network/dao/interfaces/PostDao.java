package com.teachers_social_network.dao.interfaces;

import com.teachers_social_network.model.Community;
import com.teachers_social_network.model.Post;

import java.util.List;

/**
 * Created by Olya Lee on 11.02.2017.
 */
public interface PostDao {
    /**add new row to the DB's*/
    boolean create(Post post);

    /**return all post for community*/
    List<Post> getPostsByCommunity(Community community);

    /**return all post from user*/
    List<Post> getPostsByUser(String login);

    /**return all posts from user for community*/
    List<Post> getPostsByUserForCommunity(String login, Community community);

    /**update post*/
    boolean update(Post post);

    /**delete message from DB*/
    boolean delete(Post post);
}
