package com.IMeeting.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by gjw on 2018/11/18.
 */
@Entity
@Table(name = "m_meetroom_depart")
public class MeetroomDepart {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer meetroom_id;
    private Integer depart_id;
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

    public Integer getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(Integer depart_id) {
        this.depart_id = depart_id;
    }

    public Integer getSatus() {
        return satus;
    }

    public void setSatus(Integer satus) {
        this.satus = satus;
    }
}
