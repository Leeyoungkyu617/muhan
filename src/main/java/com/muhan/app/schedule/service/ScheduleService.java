package com.muhan.app.schedule.service;

import com.muhan.app.schedule.dao.ScheduleDao;
import com.muhan.app.schedule.domain.ScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleService {

    @Autowired
    ScheduleDao scheduleDao;

    public int remove(ScheduleDto sche_dto) {return scheduleDao.delete(sche_dto);}

    public int write(ScheduleDto sche_dto) {return scheduleDao.insert(sche_dto);}

    public int modify(ScheduleDto sche_dto) {return scheduleDao.update(sche_dto);}

    public ScheduleDto select(Integer seq) {
        ScheduleDto dto = scheduleDao.select(seq);
        return dto;
    }

    public List<ScheduleDto> cal_load(String start, String end) {

        Map map = new HashMap();
        map.put("start", start); // 2024-05-00
        map.put("end", end); // 2425-06-00

        return scheduleDao.selectAll(map);
    }

    public List<ScheduleDto> cal_load2(String start, String end, int user_num) {

        Map map = new HashMap();
        map.put("start", start); // 2024-05-00
        map.put("end", end); // 2425-06-00
        map.put("user_num", user_num);

        return scheduleDao.selectAll2(map);
    }

}
