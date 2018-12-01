package com.IMeeting.controller;

import com.IMeeting.entity.*;
import com.IMeeting.resposirity.DepartRepository;
import com.IMeeting.resposirity.GroupRecordRepository;
import com.IMeeting.resposirity.GroupRepository;
import com.IMeeting.resposirity.UserinfoRepository;
import com.IMeeting.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gjw on 2018/11/24.
 */
@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private DepartRepository departRepository;
    @Autowired
    private UserinfoRepository userinfoRepository;
    @Autowired
    private GroupRecordRepository groupRecordRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupService groupService;
    //显示用户
    @RequestMapping("/showUser")
    public ServerResult showUserinfo(HttpServletRequest request) {
        ServerResult serverResult=new ServerResult();
        Integer tenantId= (Integer) request.getSession().getAttribute("tenantId");
        List<Depart>departs=departRepository.findByTenantId(tenantId);
        Map<Integer,List<Userinfo>> userinfoMap=new HashMap<>();
        List<Object>result=new ArrayList<>();
        for(int i=0;i<departs.size();i++){
            Integer departId=departs.get(i).getId();
            List<Userinfo> userinfos=userinfoRepository.findByDepartId(departId);
            userinfoMap.put(departId,userinfos);
        }
        result.add(departs);
        result.add(userinfoMap);
        serverResult.setData(result);
        return serverResult;
    }
    //保存单条群组记录
    @RequestMapping("/saveGroup")
    public ServerResult insertGroupRecord(HttpServletRequest request, @RequestParam ("group")List<Userinfo>group,@RequestParam("name") String name) {
        ServerResult serverResult=new ServerResult();
        Integer userId= (Integer) request.getSession().getAttribute("userId");
        Group group1=new Group();
        group1.setName(name);
        group1.setUserId(userId);
        Group bol=groupRepository.saveAndFlush(group1);
        Integer GroupId=group1.getId();
        GroupRecord groupRecord=new GroupRecord();
        groupRecord.setGroupId(GroupId);
        for (int i=0;i<group.size();i++){
            groupRecord.setUserId(group.get(i).getId());
            GroupRecord bol1=groupRecordRepository.saveAndFlush(groupRecord);
        }
        serverResult.setStatus(true);
        return serverResult;
    }
    //删除单条群组记录
    @RequestMapping("/deleteGroup")
    public ServerResult deleteGroup(@RequestParam("id") Integer id) {
        ServerResult serverResult=groupService.deleteGroup(id);
        return serverResult;
    }
    //显示该用户的所有群组
    @RequestMapping("/showGroup")
    public ServerResult showGroup(HttpServletRequest request){
        Integer userId=(Integer) request.getSession().getAttribute("userId");
        List<Group>groups=groupRepository.findByUserId(userId);
        ServerResult serverResult=new ServerResult();
        serverResult.setData(groups);
        serverResult.setStatus(true);
        return serverResult;
    }
    //显示单条详细群组记录
    @RequestMapping("/showOneGroup")
    public ServerResult showOneGroup(@RequestParam("id")Integer id){
        ServerResult serverResult=groupService.showOneGroup(id);
        return serverResult;
    }
    //更新单条群组记录
    @RequestMapping("/updateOneGroup")
    public ServerResult updateOneGroup(@RequestParam("id")Integer id,@RequestParam ("group")List<Userinfo>group,@RequestParam("name") String name){
        ServerResult serverResult=groupService.updateOneGroup(id,group,name);
        return serverResult;
    }
}
