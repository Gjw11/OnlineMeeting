package com.IMeeting.controller;

import com.IMeeting.entity.*;
import com.IMeeting.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjw on 2018/12/10.
 */
@RestController
@RequestMapping("/meeting")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;
    //预定会议首页
    @RequestMapping("/reserveIndex")
    public ServerResult reserveIndex(HttpServletRequest request){
        Integer tenantId= (Integer) request.getSession().getAttribute("tenantId");
        //获取预定会议参数
        MeetroomParameter meetroomParameter=meetingService.selectParameter(tenantId);
        //获取可预定的会议室集合
        List<Meetroom>meetrooms=meetingService.getEffectiveMeetroom(tenantId,request);
        //获取每个会议室对应的设备功能集合
        List<Object> meetroomEquipResult=new ArrayList<>();
        for (int i=0;i<meetrooms.size();i++){
            List<MeetroomEquip> meetroomEquips=meetingService.selectOneMeetroomEquip(meetrooms.get(i).getId());
            meetroomEquipResult.add(meetroomEquips);
        }
        //获取该租户的设备功能集合
        List<Equip>equips=meetingService.selectEquips(tenantId);
        List<Object>datas=new ArrayList<>();
        datas.add(meetroomParameter);
        datas.add(equips);
        datas.add(meetrooms);
        datas.add(meetroomEquipResult);
        ServerResult serverResult = new ServerResult();
        serverResult.setData(datas);
        return serverResult;
    }
    //通过日期查找
    @RequestMapping("/selectBydate")
    public ServerResult selectBydate(HttpServletRequest request  ){
        Integer tenantId= (Integer) request.getSession().getAttribute("tenantId");
        //获取预定会议参数
        MeetroomParameter meetroomParameter=meetingService.selectParameter(tenantId);
        //获取可预定的会议室集合
        List<Meetroom>meetrooms=meetingService.getEffectiveMeetroom(tenantId,request);
        //获取每个会议室对应的设备功能集合
        List<Object> meetroomEquipResult=new ArrayList<>();
        for (int i=0;i<meetrooms.size();i++){
            List<MeetroomEquip> meetroomEquips=meetingService.selectOneMeetroomEquip(meetrooms.get(i).getId());
            meetroomEquipResult.add(meetroomEquips);
        }
        //获取该租户的设备功能集合
        List<Equip>equips=meetingService.selectEquips(tenantId);
        List<Object>datas=new ArrayList<>();
        datas.add(meetroomParameter);
        datas.add(equips);
        datas.add(meetrooms);
        datas.add(meetroomEquipResult);
        ServerResult serverResult = new ServerResult();
        serverResult.setData(datas);
        return serverResult;
    }
}
