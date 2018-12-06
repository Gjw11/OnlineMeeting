package com.IMeeting.controller;

import com.IMeeting.entity.Depart;
import com.IMeeting.entity.Position;
import com.IMeeting.entity.ServerResult;
import com.IMeeting.entity.Userinfo;
import com.IMeeting.resposirity.DepartRepository;
import com.IMeeting.resposirity.UserinfoRepository;
import com.IMeeting.service.UserinfoService;
import com.IMeeting.util.MD5;
import com.IMeeting.util.Message;
import com.IMeeting.util.Random;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import javafx.geometry.Pos;
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
//@CrossOrigin(allowCredentials = "true",origins = "*")
public class UserinfoController {
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private UserinfoRepository userinfoRepository;

    //登陆
    @RequestMapping("/login")
    public ServerResult login(@RequestParam("username") String username,@RequestParam("password") String password,HttpServletRequest request) {
        ServerResult serverResult = new ServerResult();
        Userinfo u = userinfoService.login(username, password);
        if (u != null) {
            serverResult.setData(u);
            serverResult.setStatus(true);
            HttpSession session=request.getSession();
            session.setAttribute("userId",u.getId());
            session.setAttribute("tenantId",u.getTenantId());
            session.setAttribute("departId",u.getDepartId());
            session.setAttribute("positionId",u.getPositionId());
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
            serverResult.setStatus(true);
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
        serverResult.setStatus(true);
        return serverResult;
    }
    //短信验证绑定手机号
    @RequestMapping("/recordPhone")
    public ServerResult recordPhone(@RequestParam("phone") String phone,HttpServletRequest request)  {
        Integer id=(Integer)request.getSession().getAttribute("userId");
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
        Integer id= (Integer) session.getAttribute("userId");
        Userinfo u = userinfoRepository.findByIdAndPassword(id,oldPwd);
        if(u==null){
            serverResult.setMessage("旧密码不正确");
        }
        else if(u!=null){
            String newPwd=md5.MD5(newPassword);
            int bol=userinfoRepository.changePwd(newPwd,id);
            if(u!=null) {
                serverResult.setMessage("密码修改成功");
                serverResult.setStatus(true);
            }
            else {
                serverResult.setMessage("密码修改失败");
            }
        }
        return serverResult;
    }
    //查询显示个人信息
    @RequestMapping("/showUserinfo")
    public ServerResult showUserinfo(HttpServletRequest request) {
        ServerResult serverResult = new ServerResult();
        Map userinfo=new HashMap<>();
        Integer departId=(Integer) request.getSession().getAttribute("departId");
        Depart depart=userinfoService.getDepart(departId);
        String departName=depart.getName();
        Integer positionId=(Integer) request.getSession().getAttribute("positionId");
        Position position=userinfoService.getPosition(positionId);
        String positionName=position.getName();
        Integer userId=(Integer) request.getSession().getAttribute("userId");
        Userinfo u=userinfoService.getUserinfo(userId);
        System.out.println(departId);
        System.out.println(positionId);
        System.out.println(userId);
        userinfo.put("name",u.getName());
        userinfo.put("worknum",u.getWorknum());
        userinfo.put("phone",u.getPhone());
        userinfo.put("resume",u.getResume());
        userinfo.put("departName",departName);
        userinfo.put("positionName",positionName);
        serverResult.setData(userinfo);
        serverResult.setStatus(true);
        return serverResult;
    }
    //判断是否已经登陆
    @RequestMapping("/hadLog")
    public ServerResult hadLog(HttpServletRequest request) {
        ServerResult serverResult = new ServerResult();
        Integer userId=(Integer) request.getSession().getAttribute("userId");
        System.out.println(userId);
        System.out.println(String.valueOf(userId));
        if(userId==null){
            serverResult.setStatus(false);
        }else{
            serverResult.setStatus(true);
        }
        return serverResult;
    }
    //更新个人简介
    @RequestMapping("/updateResume")
    public ServerResult showUserinfo(@RequestParam ("resume")String resume, HttpServletRequest request) {
        Integer userId=(Integer)request.getSession().getAttribute("userId");
        int u=userinfoRepository.updateResume(resume,userId);
        ServerResult serverResult=new ServerResult();
        if (u!=0)
            serverResult.setStatus(true);
        return serverResult;
    }
    //退出
    @RequestMapping("/logout")
    public ServerResult logout(HttpServletRequest request) {
        request.getSession().invalidate();
        ServerResult serverResult=new ServerResult();
        serverResult.setStatus(true);
        return serverResult;
    }

}
