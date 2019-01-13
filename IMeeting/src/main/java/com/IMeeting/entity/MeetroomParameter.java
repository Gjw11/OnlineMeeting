package com.IMeeting.entity;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by gjw on 2018/11/18.
 */
@Entity
@Table(name = "m_meetroom_parameter")
public class MeetroomParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Time beigin;
    private Time over;
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

    public Time getBeigin() {
        return beigin;
    }

    public void setBeigin(Time beigin) {
        this.beigin = beigin;
    }

    public Time getOver() {
        return over;
    }

    public void setOver(Time over) {
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
