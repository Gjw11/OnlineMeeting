package com.IMeeting.controller;

import com.IMeeting.entity.*;
import com.IMeeting.resposirity.MeetingRepository;
import com.IMeeting.resposirity.UserinfoRepository;
import com.IMeeting.service.MeetingService;
import com.IMeeting.service.UserinfoService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

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
       ServerResult serverResult=meetingService.toReserveIndex(request);
        return serverResult;
    }
    //查找一个会议室某一天的预定情况
    @RequestMapping("/oneRoomReserver")
    public ServerResult oneRoomReserver(@RequestParam(value = "reserverDate",required = false) String reserverDate,@RequestParam(value = "roomId",required = false)Integer roomId){
        ServerResult serverResult=meetingService.getOneRoomReserver(reserverDate,roomId);
        return serverResult;
    }
    //查询某天会议室集合的预定情况，进度条显示
    @RequestMapping("/oneDayReserver")
    public ServerResult oneDayReserver(@RequestBody OneDayReservation oneDayReservation){
        ServerResult serverResult=meetingService.getOneDayReserve(oneDayReservation);
        return serverResult;
    }
}
