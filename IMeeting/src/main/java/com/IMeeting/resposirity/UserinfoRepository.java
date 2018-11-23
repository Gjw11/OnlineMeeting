package com.IMeeting.resposirity;

import com.IMeeting.entity.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gjw on 2018/11/19.
 */
@Repository
public interface UserinfoRepository extends JpaRepository<Userinfo,Integer>{
    Userinfo findByUsernameAndPassword(String username,String password);
    Userinfo findByPhoneAndPassword(String phone,String password);
    Userinfo findByPhone(String phone);
    @Transactional
    @Modifying
    @Query(value = "update Userinfo m set m.password=?1 where m.phone=?2")
    int updatePwd(String password,String phone);
    @Transactional
    @Modifying(clearAutomatically = true)//刷新hibernate的一级缓存
    @Query(value = "update Userinfo m set m.password=?1 where m.id=?2")
    int changePwd(String password,Integer id);
    Userinfo findByIdAndPassword(Integer id,String password);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Userinfo m set m.phone=?1 where m.id=?2")
    int updatePhone(String phone,Integer id);

}
