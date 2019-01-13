package com.IMeeting.resposirity;

import com.IMeeting.entity.Meeting;
import com.IMeeting.entity.ServerResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by gjw on 2018/12/16.
 */
@Repository
public interface MeetingRepository extends JpaRepository<Meeting,Integer>{
    List<Meeting> findAll(Specification<Meeting> specification);
    List<Meeting> findByMeetroomIdAndMeetDateAndStatus(Integer meetRoomId, String meetDate,Integer status);
}
