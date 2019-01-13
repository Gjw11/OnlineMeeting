package com.IMeeting.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.util.List;

/**
 * Created by gjw on 2018/12/16.
 */
public class MeetroomDate {
    List<Integer>MeetroomIds;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    Date MeetroomDate;

    public List<Integer> getMeetroomIds() {
        return MeetroomIds;
    }

    public void setMeetroomIds(List<Integer> meetroomIds) {
        MeetroomIds = meetroomIds;
    }

    public Date getMeetroomDate() {
        return MeetroomDate;
    }

    public void setMeetroomDate(Date meetroomDate) {
        MeetroomDate = meetroomDate;
    }
}
