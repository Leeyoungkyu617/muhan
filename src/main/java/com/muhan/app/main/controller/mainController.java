package com.muhan.app.main.controller;

import com.muhan.app.attend.domain.AttendDto;
import com.muhan.app.attend.service.Attend_service;
import com.muhan.app.board.domain.BoardDto;
import com.muhan.app.board.service.BoardService;
import com.muhan.app.event_photo.domain.EventPhotoDto;
import com.muhan.app.event_photo.service.EventPhotoService;
import com.muhan.app.main.service.MainService;
import com.muhan.app.schedule.domain.ScheduleDto;
import com.muhan.app.user.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class mainController {

  @Autowired
  EventPhotoService service;
  @Autowired
  BoardService boardService;
  @Autowired
  MainService mainService;
  @Autowired
  Attend_service attend_service;

  @GetMapping("/")
  public String main(Integer page_now, Integer page_size, Model m, HttpSession session){

    System.out.println("*****************" + session.getAttribute("user_num")+"*********************");
    if(session.getAttribute("user_num") == null) {
      System.out.println("로그인 이동");
      return "user/loginForm";
    }
    Integer user_num = (Integer) session.getAttribute("user_num");

    if (page_now == null) page_now = 1;
    if (page_size == null) page_size = 4;

    Map map = new HashMap<>();
    map.put("photo_offset", (page_now - 1) * page_size);
    map.put("photo_page_size", page_size);

    List<EventPhotoDto> list = service.getList(map);
    m.addAttribute("list", list);

    System.out.println("list::" + list);


    ////////////////////////////////////////////////////

    List<BoardDto> noticeList = mainService.getTopNotice();
    System.out.println("공지사항 디티오 들어 오는지 확인: " + noticeList);
    m.addAttribute("noticeList", noticeList);


    ///////////////////////////////////////////////

    List<ScheduleDto> selectList = mainService.select(user_num);
    m.addAttribute("selectList", selectList);
    System.out.println("스케줄 디티오 뜨는 지 호강니: " + selectList);

    ///////////////////////////////////////////////

    Integer wk_availtime = attend_service.select_weekly_time(user_num);

    if (wk_availtime != null) {
      m.addAttribute("wk_availtime", wk_availtime);
    }
    Calendar today = Calendar.getInstance();
    today.setFirstDayOfWeek(Calendar.MONDAY);
    int todayWeek = today.get(Calendar.WEEK_OF_YEAR) - 1;

    long[] result = totalWorkTime(user_num);
    long result_total = TimeUnit.MILLISECONDS.toHours(result[todayWeek]);

    m.addAttribute("totalWorkTime", result_total);


    return "main/index";
  }

  //시간 총 시간 가져오기
  private long[] totalWorkTime(Integer att_user) {
    long[] weeklyWorkTimes = new long[52];
    List<AttendDto> attendDtoList = attend_service.select_all(att_user);

    for (AttendDto rowResult : attendDtoList) {
      long startTime = rowResult.getAtt_start().getTime();

      if (rowResult.getAtt_start() != null && rowResult.getAtt_end() != null) {
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
}
