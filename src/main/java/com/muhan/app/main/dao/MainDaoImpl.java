package com.muhan.app.main.dao;


import com.muhan.app.board.domain.BoardDto;
import com.muhan.app.schedule.domain.ScheduleDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MainDaoImpl implements MainDao {
    @Autowired
    private SqlSession session;

    private String namespace = "com.muhan.app.main.dao.";
    @Override
    public List<BoardDto> selectClass() {
        return session.selectList(namespace + "selectClass");
    }

    @Override
    public List<ScheduleDto> select(int sche_writer) {return session.selectList(namespace+"select", sche_writer);}
}
