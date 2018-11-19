package com.IMeeting.service.serviceImpl;

import com.IMeeting.entity.Userinfo;
import com.IMeeting.resposirity.UserinfoRepository;
import com.IMeeting.service.UserinfoService;
import com.IMeeting.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gjw on 2018/11/19.
 */
@Service
public class UserserviceImpl implements UserinfoService{
    @Autowired
    private UserinfoRepository userinfoRepository;
    @Override
    public Userinfo login(String username, String password) {
        MD5 m=new MD5();
        String newPassword=m.MD5(password);
        Userinfo u1=userinfoRepository.findByUsernameAndPassword(username, newPassword);
        if(u1!=null)
        return u1;
        else {
            Userinfo u2 = userinfoRepository.findByPhoneAndPassword(username, newPassword);
            if(u2!=null)
                return u2;
        }
        return null;
    }
}
