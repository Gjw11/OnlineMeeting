package com.IMeeting.controller;

import com.IMeeting.entity.ServerResult;
import com.IMeeting.entity.Userinfo;
import com.IMeeting.resposirity.UserinfoRepository;
import com.IMeeting.service.UserinfoService;
import com.IMeeting.util.MD5;
import com.IMeeting.util.Message;
import com.IMeeting.util.Random;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gjw on 2018/11/19.
 */
@RestController
@CrossOrigin
public class UserinfoController {
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private UserinfoRepository userinfoRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    //登陆
    @RequestMapping("/login")
    public ServerResult login(@RequestParam("username") String username, @RequestParam("password") String password,HttpServletRequest request) {
        ServerResult serverResult = new ServerResult();
        String token = "testdemo11111111111119";
        Map userInfo = new HashMap();
        userInfo.put("number", "1");
        userInfo.put("account", "2");
        userInfo.put("username", "李四");
        userInfo.put("clientip", "4");
        //将管理员信息保存到 redis：管理员编号、管理员账号、管理员客户端ip
        redisTemplate.opsForHash().putAll(token,userInfo);

        System.out.println("success");
        Userinfo u = userinfoService.login(username, password);
        if (u != null) {
            serverResult.setData(u);
            HttpSession session=request.getSession();
            session.setAttribute("user_id",u.getId());
            session.setAttribute("tenant_id",u.getTenant_id());
        } else {
            serverResult.setMessage("账号密码错误");
        }
        return serverResult;
    }

    //找回密码获取验证码
    @RequestMapping("/pwdCode")
    public ServerResult pwdCode(@RequestParam("phone") String phone) throws ClientException {
        ServerResult serverResult = new ServerResult();
        Userinfo u = userinfoRepository.findByPhone(phone);
        if (u == null) {
            serverResult.setMessage("手机号码不正确");
        } else {
            Message message = new Message();
            Random random = new Random();
            String randomNum = random.GetRandom();
            SendSmsResponse response = message.sendSms(phone, randomNum);
            System.out.println("短信接口返回的数据----------------");
            System.out.println("Code=" + response.getCode());
            System.out.println("Message=" + response.getMessage());
            System.out.println("RequestId=" + response.getRequestId());
            System.out.println("BizId=" + response.getBizId());
            serverResult.setData(randomNum);
        }
        return serverResult;
    }
    //找回密码修改密码
    @RequestMapping("/forgetPwd")
    public ServerResult forgetPwd(@RequestParam("phone") String phone,@RequestParam("password") String password) {
        ServerResult serverResult = new ServerResult();
        MD5 md5=new MD5();
        String newPassword=md5.MD5(password);
        int u = userinfoRepository.updatePwd(newPassword,phone);
        if(u!=0){
            serverResult.setStatus(true);
        }
        return serverResult;
    }
    //通过手机号获取短信验证码
    @RequestMapping("/getCode")
    public ServerResult getCode(@RequestParam("phone") String phone) throws ClientException {
        ServerResult serverResult = new ServerResult();
        Message message = new Message();
        Random random = new Random();
        String randomNum = random.GetRandom();
        SendSmsResponse response = message.sendSms(phone, randomNum);
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());
        serverResult.setData(randomNum);
        return serverResult;
    }
    //短信验证绑定手机号
    @RequestMapping("/recordPhone")
    public ServerResult recordPhone(@RequestParam("id") Integer id,@RequestParam("phone") String phone)  {
        ServerResult serverResult = new ServerResult();
        int bol=userinfoRepository.updatePhone(phone, id);
        if (bol!=0)
            serverResult.setStatus(true);
        return serverResult;
    }
    //修改密码
    @RequestMapping("/changePwd")
    public ServerResult changePwd(HttpServletRequest request,@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword) {
        ServerResult serverResult = new ServerResult();
        MD5 md5=new MD5();
        String oldPwd=md5.MD5(oldPassword);
        HttpSession session=request.getSession();
        Integer id= (Integer) session.getAttribute("user_id");
        Userinfo u = userinfoRepository.findByIdAndPassword(id,oldPwd);
        if(u==null){
            serverResult.setMessage("旧密码不正确");
        }
        else if(u!=null){
            String newPwd=md5.MD5(newPassword);
            int bol=userinfoRepository.changePwd(newPwd,id);
            if(u!=null)
                serverResult.setMessage("密码修改成功");
            else
                serverResult.setMessage("密码修改失败");
        }
        return serverResult;
    }
}
