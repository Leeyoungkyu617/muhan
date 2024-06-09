package com.muhan.app.main.service;

import com.muhan.app.board.domain.BoardDto;
import com.muhan.app.main.dao.MainDao;
import com.muhan.app.schedule.domain.ScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
    @Autowired
    MainDao mainDao;

    public List<BoardDto> getTopNotice() {return mainDao.selectClass();}

    public List<ScheduleDto> select(int sche_writer) {return mainDao.select(sche_writer);}

}
