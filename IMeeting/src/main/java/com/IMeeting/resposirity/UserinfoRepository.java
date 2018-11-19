package com.IMeeting.resposirity;

import com.IMeeting.entity.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by gjw on 2018/11/19.
 */
@Repository
public interface UserinfoRepository extends JpaRepository<Userinfo,Integer>{
    Userinfo findByUsernameAndPassword(String username,String password);
    Userinfo findByPhoneAndPassword(String phone,String password);
}
