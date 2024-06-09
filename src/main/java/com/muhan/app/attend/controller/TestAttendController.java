package com.muhan.app.attend.controller;

import com.muhan.app.attend.domain.AttendDto;
import com.muhan.app.attend.service.Attend_service;
import com.muhan.app.common.PageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/attend")
public class TestAttendController {
    @Autowired
    Attend_service attend_service;

    @GetMapping("/checkpage")
    public String checkpage(Integer page_now, Integer page_size, Integer nav_page, HttpSession session, HttpServletRequest request, Model m) {
        Calendar today = Calendar.getInstance();
        today.setFirstDayOfWeek(Calendar.MONDAY);
        int todayWeek = today.get(Calendar.WEEK_OF_YEAR) - 1;
        if(!loginChk(session)){
            session.setAttribute("prevPage", request.getServletPath());
            return "redirect:/login";
        }

        int user_num = (int) session.getAttribute("user_num");

        if(page_now == null) page_now = 1;
        if(page_size == null) page_size = 10;
        if(nav_page == null) nav_page = 10;

        int total_count = attend_service.select_att_page_all(user_num);
        PageHandler pageHandler = new PageHandler(total_count, page_now, page_size, nav_page);

        Map page_map = new HashMap();
        page_map.put("user_num", user_num);
        page_map.put("offset", (page_now - 1) * page_size);
        page_map.put("page_size", page_size);

        List<AttendDto> list =attend_service.select_att_page(page_map);
        for(int i=0;i< list.size();i++){
          if(list.get(i).getAtt_chk() == 0){list.get(i).setAtt_chk_name("결근");}
          if(list.get(i).getAtt_chk() == 1){list.get(i).setAtt_chk_name("출근");}
          if(list.get(i).getAtt_chk() == 2){list.get(i).setAtt_chk_name("지각");}
          if(list.get(i).getAtt_chk() == 3){list.get(i).setAtt_chk_name("조퇴");}
        }

        AttendDto todayDto = list.get(0);
        Integer wk_availtime = attend_service.select_weekly_time(user_num);

        m.addAttribute("list", list);
        m.addAttribute("ph", pageHandler);
        m.addAttribute("todayDto", todayDto);

        if(wk_availtime != null ){
          m.addAttribute("wk_availtime", wk_availtime);
        }


    long[] result = totalWorkTime(user_num);
    long result_total = TimeUnit.MILLISECONDS.toHours(result[todayWeek]);
    System.out.println("로그인한 사람이 일한 시간 :: " + result_total + ", "+ todayWeek);
      m.addAttribute("totalWorkTime", result_total);

System.out.println("이번 주 번호: " + todayWeek);
        return "attend/checkpage";
    }
    @RequestMapping("/updatetime")
    public String modify_end_time(HttpSession session, Model m){
      Calendar today = Calendar.getInstance();
      today.setFirstDayOfWeek(Calendar.MONDAY);
      int todayWeek = today.get(Calendar.WEEK_OF_YEAR) - 1;
        Integer user_num = (Integer)session.getAttribute("user_num");

        Map map = new HashMap();
        map.put("att_user", user_num);

        if(attend_service.get_first_attend(map) != 1) {
            System.out.println("출근 기록 검색 실패");
        }
        else {
            attend_service.update_end_time(user_num);
            System.out.println(attend_service.get_first_attend(map));
            System.out.println("퇴근 시간 업데이트 완료");
            /* 퇴근 시간 업데이트 완료 */
                      /* 퇴근 버튼을 누르면 출근, 퇴근 시각 측정해서 상태 체크해 준다.
                      * 0 - 결근
                      * 1 - 출근
                      * 2 - 지각
                      * 3 - 조퇴
                      * */
          Date startTime = attend_service.select_first_attend_dto(user_num).getAtt_start();
          Date endTime = attend_service.select_first_attend_dto(user_num).getAtt_end();
          session.setAttribute("att_end",endTime);
          long duration = (endTime.getTime() - startTime.getTime())/(1000*60*60);

System.out.println("근무 시간? 구해지나여?" + duration);
          // calendar로 기준 시간 설정하는 걸 메서드로 만들어보자
            Date attend_late_time = setCheckingTime(9, 0, 0);

          Map map_for_update_att_con = new HashMap();
          map_for_update_att_con.put("att_user", user_num);
            if(duration >= 8){
                if(startTime.before(attend_late_time)){
                    map_for_update_att_con.put("att_chk", 1);
                }
                else {map_for_update_att_con.put("att_chk", 2);}
            } else if (duration>=4) {
                map_for_update_att_con.put("att_chk", 3);
            }else {map_for_update_att_con.put("att_chk", 0);}

          attend_service.update_att_chk(map_for_update_att_con);
//
          long[] result = totalWorkTime(user_num);
          long result_total = TimeUnit.MILLISECONDS.toHours(result[todayWeek]);

System.out.println("일단 이 사람이 일한 시간: " + result_total + ", "+ todayWeek);
          m.addAttribute("totalWorkTime: "+ result_total);
        }

        return "redirect:/attend/checkpage";
    }


    private boolean loginChk(HttpSession session){
        return session.getAttribute("user_num")!=null;
    }

    private long[] totalWorkTime(Integer att_user){
      long[] weeklyWorkTimes = new long[52];
      List<AttendDto> attendDtoList = attend_service.select_all(att_user);

      for(AttendDto rowResult:attendDtoList){
        long startTime = rowResult.getAtt_start().getTime();

        if(rowResult.getAtt_start() != null && rowResult.getAtt_end()!=null){
        long endTime = rowResult.getAtt_end().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(rowResult.getAtt_start());
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR) - 1;
        weeklyWorkTimes[weekOfYear] += endTime - startTime;
        }
      }
      return weeklyWorkTimes;
    }


    private Date setCheckingTime(int hour, int min, int sec){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, sec);
        return calendar.getTime();
    }
}
