package com.muhan.app.meetingroom.dao;

import com.muhan.app.meetingroom.domain.MeetingDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class MeetingDaoImpl implements MeetingDao {
  @Autowired
  private SqlSession session;

  private String namespace = "com.muhan.app.meetingroom.dao.mResrveMapper.";


  @Override
  public List<MeetingDto> selectAll(Map map){
    return session.selectList(namespace+"selectAll", map);
  }

  @Override
  public int insertReserve(MeetingDto meetingDto) {
    return session.insert(namespace+"insertReserve", meetingDto);
  }

  @Override
  public int updateReserve(MeetingDto meetingDto) {
    return session.update(namespace+"updateReserve", meetingDto);
  }

  @Override
  public int deleteReserve(Map map){
    return session.delete(namespace+"deleteReserve", map);
  }
}
