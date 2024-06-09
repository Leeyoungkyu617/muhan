package com.muhan.app.main.dao;

import com.muhan.app.board.domain.BoardDto;
import com.muhan.app.schedule.domain.ScheduleDto;

import java.util.List;

public interface MainDao {
    List<BoardDto> selectClass();

    List<ScheduleDto> select(int sche_writer);
}
