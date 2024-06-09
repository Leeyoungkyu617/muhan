package com.muhan.app.schedule.dao;

import com.muhan.app.schedule.domain.ScheduleDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {
    @Autowired
    SqlSession session;

    String namespace = "com.muhan.app.schedule.dao.scheduleMapper.";

    @Override
    public int delete(ScheduleDto sche_dto) { return session.delete(namespace + "delete", sche_dto);}

    @Override
    public int insert(ScheduleDto sche_dto) { return session.insert(namespace + "insert", sche_dto);}

    @Override
    public int update(ScheduleDto sche_dto) { return  session.update(namespace + "update", sche_dto);}

    @Override
    public ScheduleDto select(int sche_seq) {
        return session.selectOne(namespace+"select", sche_seq);
    }

    @Override
    public List<ScheduleDto> selectAll(Map map) { // {"start":"2024-05-00", "end":"2024-06-00"}

        System.out.println("map: " + map );

        return session.selectList(namespace + "selectAll", map);
    }
    @Override
    public List<ScheduleDto> selectAll2(Map map) { // {"start":"2024-05-00", "end":"2024-06-00"}

        System.out.println("map: " + map );

        return session.selectList(namespace + "selectAll2", map);
    }
}

