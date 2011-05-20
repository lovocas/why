package com.why.model;

import java.util.List;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;


@Entity
public class Topic {
    private String name;
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
    
    
}
