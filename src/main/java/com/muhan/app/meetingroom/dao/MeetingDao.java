package com.muhan.app.meetingroom.dao;

import com.muhan.app.meetingroom.domain.MeetingDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MeetingDao {
  List<MeetingDto> selectAll(Map map);

  int insertReserve(MeetingDto meetingDto);

  int updateReserve(MeetingDto meetingDto);

  int deleteReserve(Map map);
}
