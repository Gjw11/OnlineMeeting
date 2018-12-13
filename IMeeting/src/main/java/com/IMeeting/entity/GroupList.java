package com.IMeeting.entity;

import java.util.List;

/**
 * Created by gjw on 2018/12/13.
 */
public class GroupList {
    private String name;
    private List<Integer>ids;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
