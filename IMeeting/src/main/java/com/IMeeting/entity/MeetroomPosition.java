package com.IMeeting.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by gjw on 2018/11/18.
 */
@Entity
@Table(name = "m_meetroom_position")
public class MeetroomPosition {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer meetroom_id;
    private Integer position_id;
    private Integer satus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMeetroom_id() {
        return meetroom_id;
    }

    public void setMeetroom_id(Integer meetroom_id) {
        this.meetroom_id = meetroom_id;
    }

    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }

    public Integer getSatus() {
        return satus;
    }

    public void setSatus(Integer satus) {
        this.satus = satus;
    }
}
