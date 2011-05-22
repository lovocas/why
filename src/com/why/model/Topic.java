package com.why.model;

import java.util.List;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
import com.why.dao.TopicDAO;
import com.why.util.DB;


@Entity(value="topics")
public class Topic {
    private String name;
    private String summery;//介绍
    private int asks_count;
    
    @Reference
    private List<User> followers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAsks_count() {
        return asks_count;
    }

    public void setAsks_count(int asks_count) {
        this.asks_count = asks_count;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }
    
    
    private static Topic findByName(String name) {
        TopicDAO dao = new TopicDAO(DB.morphia, DB.mongo);
        return (Topic)dao.findOne("name", name);
    }
    
    
    public static boolean AddTopic(Topic aTopic, User u) {
        if(aTopic != null && !"".equals(aTopic.getName()) && null==findByName(aTopic.name)) {
            TopicDAO dao = new TopicDAO(DB.morphia, DB.mongo);
            dao.save(aTopic);
            return true;
        }
        return false;
    }
}
