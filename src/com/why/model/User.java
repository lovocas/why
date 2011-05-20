package com.why.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import com.why.util.DB;

@Entity
public class User {
    
    @Id ObjectId id;
    private String username;
    private String email;
    private String password;
    private String bio;//simple self introduction  (biography)
    private String website; //personal website
    private boolean isMale;//gender


    //fllow的
    //private ObjectId[] followedAskIds; //
    //private ObjectId[] answeredAskIds;
    //问问题的数量
    private int asks_count;


    //sns
    //person network
    //和前面的id是重复的，空间来提高查询效率
    @Reference
    private List<User> following;
    @Reference
    private List<User> followers;

    //ask and user network
    @Reference
    private List<Ask> followedAsks;
    @Reference
    private List<Topic> followedTopics;


    public User() {
        following = new ArrayList<User>();
        followers = new ArrayList<User>();
        asks_count = 0;
        followedAsks = new ArrayList<Ask>();
        followedTopics = new ArrayList<Topic>();
    }


    public ObjectId getId() {
        return id;
    }


    public void setId(ObjectId id) {
        this.id = id;
    }


    public List<Ask> getFollowedAsks() {
        return followedAsks;
    }
    public void setFollowedAsks(List<Ask> followedAsks) {
        this.followedAsks = followedAsks;
    }
    public List<Topic> getFollowedTopics() {
        return followedTopics;
    }
    public void setFollowedTopics(List<Topic> followedTopics) {
        this.followedTopics = followedTopics;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public boolean isMale() {
        return isMale;
    }
    public void setMale(boolean isMale) {
        this.isMale = isMale;
    }
    public int getAsks_count() {
        return asks_count;
    }
    public void setAsks_count(int asks_count) {
        this.asks_count = asks_count;
    }
    public List<User> getFollowing() {
        return following;
    }
    public void setFollowing(List<User> following) {
        this.following = following;
    }
    public List<User> getFollowers() {
        return followers;
    }
    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
    
    
    public void followPerson(User user) {
        this.following.add(user);
        user.followers.add(this);
    }
    
    public void unfollowPerson(User user) {
        
        if(-1 != following.indexOf(user)) {
            following.remove(user);
            user.followers.remove(this);
        }
        
    }
    public void askQuestion(String title, String content, ServletContext context) {
        Ask ask = new Ask();
        ask.setLastModifiedAt(new Date());
        ask.setTitle(title);
        ask.setBody(content);
        ask.setAuthorId(this.getId());
        this.asks_count ++;
        
        DB.getDatastore(context).save(ask);
        DB.getDatastore(context).save(this);
    }
    
}
