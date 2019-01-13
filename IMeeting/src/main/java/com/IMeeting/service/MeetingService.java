package com.IMeeting.service;


import com.IMeeting.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

/**
 * Created by gjw on 2018/12/12.
 */
public interface MeetingService {
    MeetroomParameter selectParameter(Integer tenantId);
    List<Meetroom> getEffectiveMeetroom(Integer tenantId,HttpServletRequest request);
    List<Equip> selectEquips(Integer tenantId);
    List<MeetroomEquip>selectOneMeetroomEquip(Integer meetroomId);
    ServerResult toReserveIndex(HttpServletRequest request);
//    List<Meeting>selectBydate(Date date,Integer meetroomId);
    ServerResult getOneRoomReserver(String reserverDate,Integer roomId);
    ServerResult getOneDayReserve(OneDayReservation oneDayReservation);
}
