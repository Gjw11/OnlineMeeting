package com.IMeeting.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by gjw on 2018/11/18.
 */
@Entity
@Table(name = "m_meetroom")
public class Meetroom {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String num;
    private String place;
    private Integer contain;
    private Integer avail_status;
    private Integer now_status;
    private Integer tenant_id;

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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getContain() {
        return contain;
    }

    public void setContain(Integer contain) {
        this.contain = contain;
    }

    public Integer getAvail_status() {
        return avail_status;
    }

    public void setAvail_status(Integer avail_status) {
        this.avail_status = avail_status;
    }

    public Integer getNow_status() {
        return now_status;
    }

    public void setNow_status(Integer now_status) {
        this.now_status = now_status;
    }

    public Integer getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(Integer tenant_id) {
        this.tenant_id = tenant_id;
    }
}
