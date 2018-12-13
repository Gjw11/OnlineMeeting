package com.IMeeting.service;


import com.IMeeting.entity.Equip;
import com.IMeeting.entity.Meetroom;
import com.IMeeting.entity.MeetroomEquip;
import com.IMeeting.entity.MeetroomParameter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by gjw on 2018/12/12.
 */
public interface MeetingService {
    MeetroomParameter selectParameter(Integer tenantId);
    List<Meetroom> getEffectiveMeetroom(Integer tenantId,HttpServletRequest request);
    List<Equip> selectEquips(Integer tenantId);
    List<MeetroomEquip>selectOneMeetroomEquip(Integer meetroomId);
}
