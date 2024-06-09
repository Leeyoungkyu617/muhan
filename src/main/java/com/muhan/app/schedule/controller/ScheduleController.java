package com.muhan.app.schedule.controller;

import com.muhan.app.schedule.dao.ScheduleDao;
import com.muhan.app.schedule.domain.ScheduleDto;
import com.muhan.app.schedule.service.ScheduleService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    // 스케줄 첫 페이지 getmapping 해서 들어가기
    @GetMapping("/schedule")
    public String schedule(Model m)
    {

        return "/schedule/schedule";
    }


    // 스케줄 저장
    @PostMapping("/schedule/save")
    public String saveSchedule(ScheduleDto scheduleDto, HttpSession session) {


        var cal_user_num =Integer.parseInt(session.getAttribute("user_num").toString());
        scheduleDto.setSche_writer(cal_user_num);
        scheduleService.write(scheduleDto);
        return "redirect:/schedule";
    }

    // 스케줄 삭제
    @PostMapping("/schedule/delete")
    public String deleteSchedule(ScheduleDto scheduleDto, HttpSession session) {


        var cal_user_num =Integer.parseInt(session.getAttribute("user_num").toString());
        scheduleDto.setSche_writer(cal_user_num);
        scheduleService.remove(scheduleDto);

        return "redirect:/schedule";
    }

    // 스케줄 수정
    @PostMapping("/schedule/modify")
    public String modifySchedule(ScheduleDto scheduleDto, HttpSession session) {


        var cal_user_num =Integer.parseInt(session.getAttribute("user_num").toString());
        scheduleDto.setSche_writer(cal_user_num);
        scheduleService.modify(scheduleDto);
        return "redirect:/schedule";
    }

    /**********************************************************************/

    private static final Logger log = LoggerFactory.getLogger(ScheduleController.class);

    //Ajax 불러오는 return db
    @GetMapping("/scheduleAjax")
    @ResponseBody
    public List<Map<String, Object>> monthPlan(HttpSession session) {


        // 현재 년 월 구하기 > 2024-05
        LocalDate thisMonth = LocalDate.now();
        LocalDate nextMonth = thisMonth.plusMonths(1);


        int year =  thisMonth.getYear();
        String month = thisMonth.format(DateTimeFormatter.ofPattern("MM"));
        int date =  thisMonth.getDayOfMonth();
        String start = year + "-" + month + "-00";

        int year2 =  nextMonth.getYear();
        String month2 = nextMonth.format(DateTimeFormatter.ofPattern("MM"));
        int date2 =  nextMonth.getDayOfMonth();
        String end = year2 + "-" + (month2) + "-00";

        /* 추가 */
//        String user_num = (String) session.getAttribute("user_num");
        int user_num =Integer.parseInt(session.getAttribute("user_num").toString());

//        List<ScheduleDto> listAll = scheduleService.cal_load(start,end);
        /* 추가 */List<ScheduleDto> listAll = scheduleService.cal_load2(start,end, user_num);


//        System.out.println("/**************************************/");
//        System.out.println(listAll);
//        System.out.println("/**************************************/");

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();

        HashMap<String, Object> hash = new HashMap<>();
//        Map<String, String> extendsProps = new HashMap<>();
        for (int i = 0; i < listAll.size(); i++) {

            hash.put("id", listAll.get(i).getSche_seq());
            hash.put("start", listAll.get(i).getSche_start_time());
            hash.put("end", listAll.get(i).getSche_end_time());
            hash.put("title", listAll.get(i).getSche_title());
            hash.put("backgroundColor", listAll.get(i).getSche_color());
            hash.put("content", listAll.get(i).getSche_contents());

//            extendsProps.put("content",listAll.get(i).getSche_contents());
//            hash.put("extendsProps", extendsProps[i]);




//System.out.println("111111111111111111111111111111111hash: " + hash);

            jsonObj = new JSONObject(hash);
            jsonArr.add(jsonObj);
//System.out.println(i+" iiiiiiiiiiii");
//System.out.println("jsonArr: " + jsonArr);
        }
        return jsonArr;
    }


/**********************************************************************/



//    @ResponseBody
//    public ResponseEntity<List<ScheduleDto>> scheduleAjax(Model m) {
//
//        // ResponseEntity: 응답이나 요청시 전송할 정보를 보낼 대상에 임의의 상태코드를 보낼수 있음
//        try {
//
//            // 현재 년 월 구하기 > 2024-05
//            LocalDate thisMonth = LocalDate.now();
//            LocalDate nextMonth = thisMonth.plusMonths(1);
//
//            System.out.println("thisMonth is " + thisMonth);
//            System.out.println("nextMonth is " + nextMonth);
//
//
//            int year =  thisMonth.getYear();
//            String month = thisMonth.format(DateTimeFormatter.ofPattern("MM"));
//            int date =  thisMonth.getDayOfMonth();
//            String start = year + "-" + month + "-00";
//
//            int year2 =  nextMonth.getYear();
//            String month2 = nextMonth.format(DateTimeFormatter.ofPattern("MM"));
//            int date2 =  nextMonth.getDayOfMonth();
//            String end = year2 + "-" + (month2) + "-00";
//
//            System.out.println(start);
//            System.out.println(end);
//
//            List<ScheduleDto> list = scheduleService.cal_load(start,end );
//            System.out.println(list);
//
//            return new ResponseEntity<List<ScheduleDto>> (list, HttpStatus.OK);// 200
//
//            // 서비스에 보내기 (2024-05-00, 2024-06-00)
//
//        } catch (Exception e) {
////            throw new RuntimeException(e);
//            e.printStackTrace();
//            return new ResponseEntity<List<ScheduleDto>> (HttpStatus.BAD_REQUEST);// 400
//        }
//
//    }

}
