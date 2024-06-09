package com.muhan.app.attend.dao;

import com.muhan.app.attend.domain.AttendDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class Attnd_daoImpl implements Attend_dao {

    @Autowired
    SqlSession session;
    String namespace = "com.muhan.app.attend.dao.";

    @Override
    public int get_first_attend(Map map) {
System.out.println("dao 접금 :: " + map);
        int result =session.selectOne(namespace+"get_first_attend",map);
System.out.println("dao 끝");
        return result;
    }
    @Override
    public AttendDto select_first_attend_dto(int att_user){
        return session.selectOne(namespace+"select_first_attend_dto", att_user);
    }

    @Override
    public List<AttendDto> select_all(int att_user){
        return session.selectList(namespace + "selectAll", att_user);
    }

    @Override
    public Integer select_weekly_time(int att_user){
        return session.selectOne(namespace + "selectWeeklyTime", att_user);
    }

    @Override
    public int set_attend_start_time(Map map) {
        return session.insert(namespace+"insertAttend",map);
    }

    @Override
    public int update_end_time(int att_user){
        return session.update(namespace+"update_end_time", att_user);
    }

    @Override
    public int update_att_chk(Map map) {
        return session.update(namespace + "update_att_chk", map);
    }

    @Override
    public int select_att_page_all (int att_user) {
        return session.selectOne(namespace+"select_att_page_all", att_user);
    }

    @Override
    public List<AttendDto> select_att_page(Map map){
        return session.selectList(namespace+"select_att_page", map);
    }
}
