package com.IMeeting.entity;

import javax.persistence.*;

/**
 * Created by gjw on 2019/1/15.
 */
@Entity
@Table(name = "m_outsideJoinPerson")
public class OutsideJoinPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
