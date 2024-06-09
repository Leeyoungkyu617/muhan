package com.muhan.app.meetingroom.service;


import com.muhan.app.meetingroom.dao.MeetingDao;
import com.muhan.app.meetingroom.domain.MeetingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MeetingService {
  @Autowired
  MeetingDao meetingDao;

  public List<MeetingDto> getAllList(String mr_date, int mr_room){
    Map map = new HashMap();
    map.put("mr_date", mr_date);
    map.put("mr_room", mr_room);
    return meetingDao.selectAll(map);
  }

  public int makeReserve(MeetingDto meetingDto){
    return meetingDao.insertReserve(meetingDto);
  }

  public int modifyReserve(MeetingDto meetingDto){
    return meetingDao.updateReserve(meetingDto);
  }

  public int removeReserve(int mr_user, int mr_seq){
System.out.println("미팅 서비스 삭제 들어옴! 사원번호: "+ mr_user + ", 시퀀스 넘버: " + mr_seq);
    Map map= new HashMap();
    map.put("mr_user", mr_user);
    map.put("mr_seq", mr_seq);
    return meetingDao.deleteReserve(map);
  }
}