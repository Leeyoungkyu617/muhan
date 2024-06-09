package com.muhan.app.attend.service;

import com.muhan.app.attend.dao.Attend_dao;
import com.muhan.app.attend.domain.AttendDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Attend_service {
    @Autowired
    Attend_dao attend_dao;

    public int get_first_attend(Map map){
        return attend_dao.get_first_attend(map);
    }

    public AttendDto select_first_attend_dto(int user_num){
        return attend_dao.select_first_attend_dto(user_num);
    }

    public List<AttendDto> select_all(int user_num){
        return attend_dao.select_all(user_num);
    }

    public Integer select_weekly_time(int user_num) {
        return attend_dao.select_weekly_time(user_num);
    }

    public int set_attend_start_time(Map map){
        return attend_dao.set_attend_start_time(map);
    }

    public int update_end_time(int user_num){
        return attend_dao.update_end_time(user_num);
    }
    public int update_att_chk(Map map){
        return attend_dao.update_att_chk(map);
    }

    public int select_att_page_all (int user_num){
        return attend_dao.select_att_page_all(user_num);
    }

    public List<AttendDto> select_att_page(Map map){
        return attend_dao.select_att_page(map);
    }
}
