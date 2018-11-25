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
    private Integer meetroomId;
    private Integer equipId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMeetroomId() {
        return meetroomId;
    }

    public void setMeetroomId(Integer meetroomId) {
        this.meetroomId = meetroomId;
    }

    public Integer getEquipId() {
        return equipId;
    }

    public void setEquipId(Integer equipId) {
        this.equipId = equipId;
    }
}
