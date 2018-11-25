package com.IMeeting.service.serviceImpl;

import com.IMeeting.entity.Group;
import com.IMeeting.entity.GroupRecord;
import com.IMeeting.entity.ServerResult;
import com.IMeeting.entity.Userinfo;
import com.IMeeting.resposirity.GroupRecordRepository;
import com.IMeeting.resposirity.GroupRepository;
import com.IMeeting.resposirity.UserinfoRepository;
import com.IMeeting.service.GroupService;
import com.IMeeting.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Created by gjw on 2018/11/24.
 */
@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupRecordRepository groupRecordRepository;
    @Autowired
    private UserinfoRepository userinfoRepository;
    @Autowired
    private UserinfoService userinfoService;
    @Override
    @Transactional
    public ServerResult deleteGroup(Integer id) {
        ServerResult serverResult=new ServerResult();
        groupRepository.delete(id);
        groupRecordRepository.delete(id);
        serverResult.setStatus(true);
        return serverResult;
    }

    @Override
    public Group selectGroup(Integer id) {
        Optional<Group> group=groupRepository.findById(id);
        if (group.isPresent()) {
            return group.get();
        }
        return null;
    }

    @Override
    public ServerResult showOneGroup(Integer id) {
        ServerResult serverResult=new ServerResult();
        Group group=selectGroup(id);
        List<GroupRecord>groupRecords=groupRecordRepository.findByGroupId(id);
        HashMap<Integer,String>u=new HashMap<>();
        String name = null;
        for (int i=0;i<groupRecords.size();i++){
            Integer userId=groupRecords.get(i).getUserId();
            Userinfo userinfo=userinfoService.getUserinfo(userId);
            if (userinfo!=null) {
                name = userinfo.getName();
            }
           u.put(userId,name);
        }
        List<Object>list=new ArrayList<>();
        list.add(group);
        list.add(u);
        serverResult.setData(list);
        serverResult.setStatus(true);
        return serverResult;
    }

    @Override
    public ServerResult updateOneGroup(Integer groupId,List<Userinfo> group,String name) {
        int bol=groupRepository.update(groupId,name);
        groupRecordRepository.delete(groupId);
        GroupRecord groupRecord=new GroupRecord();
        groupRecord.setGroupId(groupId);
        for (int i=0;i<group.size();i++){
            groupRecord.setUserId(group.get(i).getId());
            GroupRecord bol1=groupRecordRepository.saveAndFlush(groupRecord);
        }
        ServerResult serverResult=new ServerResult();
        serverResult.setStatus(true);
        return serverResult;
    }
}
