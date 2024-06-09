package com.muhan.app.attend.dao;


import com.muhan.app.attend.domain.AttendDto;

import java.util.List;
import java.util.Map;

public interface Attend_dao {

    public int get_first_attend(Map map);

    AttendDto select_first_attend_dto(int user_num);

    List<AttendDto> select_all(int att_user);

    Integer select_weekly_time(int att_user);

    public int set_attend_start_time(Map amp);

    int update_end_time(int user_num);

    int update_att_chk(Map map);

    int select_att_page_all(int user_num);

    List<AttendDto> select_att_page(Map map);
}
