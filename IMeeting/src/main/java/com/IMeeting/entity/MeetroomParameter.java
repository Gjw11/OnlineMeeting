package com.IMeeting.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by gjw on 2018/11/18.
 */
@Entity
@Table(name = "m_meetroom_parameter")
public class MeetroomParameter {
    @Id
    @GeneratedValue
    private Integer id;
    private String beigin;
    private String over;
    private String date_limit;
    private String time_limit;
    private String time_interval;
    private Integer tenant_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBeigin() {
        return beigin;
    }

    public void setBeigin(String beigin) {
        this.beigin = beigin;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public String getDate_limit() {
        return date_limit;
    }

    public void setDate_limit(String date_limit) {
        this.date_limit = date_limit;
    }

    public String getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(String time_limit) {
        this.time_limit = time_limit;
    }

    public String getTime_interval() {
        return time_interval;
    }

    public void setTime_interval(String time_interval) {
        this.time_interval = time_interval;
    }

    public Integer getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(Integer tenant_id) {
        this.tenant_id = tenant_id;
    }
}
