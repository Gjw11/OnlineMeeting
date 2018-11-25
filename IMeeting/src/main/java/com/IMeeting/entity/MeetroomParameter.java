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
    private String dateLimit;
    private String timeLimit;
    private String timeInterval;
    private Integer tenantId;

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

    public String getDateLimit() {
        return dateLimit;
    }

    public void setDateLimit(String dateLimit) {
        this.dateLimit = dateLimit;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}
