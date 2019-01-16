package com.IMeeting.resposirity;

import com.IMeeting.entity.Meeting;
import com.IMeeting.entity.ServerResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by gjw on 2018/12/16.
 */
@Repository
public interface MeetingRepository extends JpaRepository<Meeting,Integer>{
    List<Meeting> findAll(Specification<Meeting> specification);
    List<Meeting> findByMeetroomIdAndMeetDateAndStatus(Integer meetRoomId, String meetDate,Integer status);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Meeting m set m.status=?2 where m.id=?1")
    int updateStatus(Integer meetingId,Integer status);
    List<Meeting>findByBeginAndOverAndMeetroomIdOrderByCreateTimeAsc(long begin,long over,Integer meetroomId);
    @Query(value = "select * from Meeting m where m.begin<?2 and m.over>?1")
    List<Meeting>findIntersectMeeting(long beginTime,long overTime);
}
