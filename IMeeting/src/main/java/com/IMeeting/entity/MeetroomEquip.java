package com.IMeeting.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by gjw on 2018/11/18.
 */
@Entity
@Table(name = "m_meetroom_equip")
public class MeetroomEquip {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer meetroom_id;
    private Integer equip_id;

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

    public Integer getEquip_id() {
        return equip_id;
    }

    public void setEquip_id(Integer equip_id) {
        this.equip_id = equip_id;
    }
}
