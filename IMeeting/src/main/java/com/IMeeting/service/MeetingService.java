package com.IMeeting.service;


import com.IMeeting.entity.*;
import org.springframework.web.bind.annotation.RequestBody;

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
    ServerResult getOneRoomReserver(String reserverDate,Integer roomId);
    ServerResult getOneDayReserve(OneDayReservation oneDayReservation);
    ServerResult reserveMeeting(@RequestBody ReserveParameter reserveParameter,HttpServletRequest request);
    ServerResult robMeeting(@RequestBody ReserveParameter reserveParameter,HttpServletRequest request);
}
