package com.IMeeting.controller;

import com.IMeeting.entity.Userinfo;
import com.IMeeting.service.UserinfoService;
import com.IMeeting.util.Message;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gjw on 2018/11/19.
 */
@RestController
@CrossOrigin
public class UserinfoController {
    @Autowired
    private UserinfoService userinfoService;
    @RequestMapping("/login")
    public Userinfo login(@RequestParam("username")String username,@RequestParam("password")String password) throws ClientException {
        Message message=new Message();
        SendSmsResponse response = message.sendSms();
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());
        Userinfo u=userinfoService.login(username,password);
        if(u!=null)
            return u;
        else
            return null;
    }

}
