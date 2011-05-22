package com.why.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import com.why.dao.AskDAO;
import com.why.dao.UserDAO;
import com.why.util.DB;


/**
 * @author TeaInCoffee
 * lastModified: 2011-05-22 14:04
 * common user
 */
@Entity(value="users")
public class User {
    
    @Id ObjectId id;//mongodb 自动处理的ID
    
    private String username; 
    private String email;
    private String password;
    private String bio;//simple self introduction  (biography)
    private String tagline; //more simple introduce your self 
    private String website; //personal website
    private boolean isMale;//gender

    private int asks_count;
    //虽然保存单向关系可以处理，但是造成查询麻烦
    
    
    //所以牺牲完整性，每次关系的更新都要修改这两个list
    @Reference
    private List<User> following;
    @Reference
    private List<User> followers;

    
    
    @Reference
    private List<Ask> followedAsks;//关注的问题
    @Reference
    private List<Topic> followedTopics;//关注的话题
    

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
    public String getTagline() {
        return tagline;
    }
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    
    
    public void followPerson(User user) {
        this.following.add(user);
        user.followers.add(this);
        
        UserDAO dao = new UserDAO(DB.morphia, DB.mongo);
        dao.save(this);
        dao.save(user);
        
    }
    
    public void unfollowPerson(User user) {
        
        if(-1 != following.indexOf(user)) {
            following.remove(user);
            user.followers.remove(this);
        }
        
    }
    public ObjectId askQuestion(String title, String content) {
        Ask ask = new Ask();
        ask.setLastModifiedAt(new Date());
        ask.setTitle(title);
        ask.setBody(content);
        ask.setAuthorId(this.getId());
        ask.setLastModifiedAt(new Date());
        ask.setAuthorId(this.id);
        this.asks_count ++;
        AskDAO dao = new AskDAO(DB.morphia, DB.mongo);
        dao.save(ask);
        System.out.println(ask.getId() + "问题保存到数据库了，" + this.username + "问的");
        DB.ds.save(this);
        return ask.getId();
    }
    
    public void followTopic(Topic topic) {
        followedTopics.add(topic);
        topic.getFollowers().add(this);
        DB.ds.save(topic);
        DB.ds.save(this);
    }
    
    public void unfollowTopic(Topic topic) {
        followedTopics.remove(topic);
        topic.getFollowers().remove(this);
        DB.ds.save(topic);
        DB.ds.save(this);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof User)) return false;
        User otherUser = (User)obj;
        return this.id.equals(otherUser.id);
    }
    
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
