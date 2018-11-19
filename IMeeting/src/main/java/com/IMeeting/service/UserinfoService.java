package com.IMeeting.service;

import com.IMeeting.entity.Userinfo;
import com.IMeeting.resposirity.UserinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by gjw on 2018/11/19.
 */
public interface UserinfoService {
    Userinfo login(String username,String password);
}
