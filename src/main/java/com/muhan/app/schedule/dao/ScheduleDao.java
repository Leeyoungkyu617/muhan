package com.muhan.app.schedule.dao;

import com.muhan.app.schedule.domain.ScheduleDto;

import java.util.List;
import java.util.Map;

public interface ScheduleDao {

    int delete(ScheduleDto sche_dto);

    int insert(ScheduleDto sche_dto);

    int update(ScheduleDto sche_dto);

    ScheduleDto select(int seq);

    List<ScheduleDto> selectAll(Map map);

    List<ScheduleDto> selectAll2(Map map);
}
