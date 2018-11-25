package com.IMeeting.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by gjw on 2018/11/18.
 */
@Entity
@Table(name = "m_meeting")
public class Meeting {
    @Id
    @GeneratedValue
    private Integer id;
    private String topic;
    private String content;
    private String begin;
    private String over;
    private Integer userId;
    private Integer meetroomId;
    private Integer satus;
    private Integer tenantId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMeetroomId() {
        return meetroomId;
    }

    public void setMeetroomId(Integer meetroomId) {
        this.meetroomId = meetroomId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getSatus() {
        return satus;
    }

    public void setSatus(Integer satus) {
        this.satus = satus;
    }

}
