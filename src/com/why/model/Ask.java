package com.why.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;


@Entity(value="asks")
public class Ask {
    
    @Id private ObjectId id;
    public ObjectId getId() {
        return id;
    }


    public void setId(ObjectId id) {
        this.id = id;
    }
    private String title;
    private String body;
    private ObjectId authorId;
    //问题被修改的时间
    private Date lastModifiedAt; 
    //最后一次被回答的时间
    private Date answeredAt;


    private int answersCount;
    private int spamCount;//被举报垃圾问题数量


    private List<Topic> topics;
    private int viewCount;
    @Reference
    private User author;

    public User getAuthor() {
        return author;
    }


    public void setAuthor(User author) {
        this.author = author;
    }


    public Ask() {
        answersCount = 0;
        spamCount = 0;
        topics = new ArrayList<Topic>();
        viewCount = 0;
    }


    public ObjectId getAuthorId() {
        return authorId;
    }


    public void setAuthorId(ObjectId authorId) {
        this.authorId = authorId;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public Date getLastModifiedAt() {
        return lastModifiedAt;
    }
    public void setLastModifiedAt(Date lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
    public Date getAnsweredAt() {
        return answeredAt;
    }
    public void setAnsweredAt(Date answeredAt) {
        this.answeredAt = answeredAt;
    }
    public int getAnswersCount() {
        return answersCount;
    }
    public void setAnswersCount(int answersCount) {
        this.answersCount = answersCount;
    }
    public int getSpamCount() {
        return spamCount;
    }
    public void setSpamCount(int spamCount) {
        this.spamCount = spamCount;
    }
    public List<Topic> getTopics() {
        return topics;
    }
    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
    public int getViewCount() {
        return viewCount;
    }
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }


}
