package com.muhan.app.meetingroom.controller;

import com.muhan.app.meetingroom.domain.MeetingDto;
import com.muhan.app.meetingroom.service.MeetingService;
import com.muhan.app.user.domain.Session_dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class MeetingroomController {
  @Autowired
  MeetingService meetingService;

  @GetMapping("/meetingroom")
  public String meetingroom(HttpServletRequest request, Model m){
    HttpSession session = request.getSession();
    if(!loginChk(session)) { // 세션 만료되어 있으면 다시 로그인 페이지로 보낼게요 - 메서드 0604 추가
      session.setAttribute("prevPage", request.getServletPath());
      return "redirect:/login";
    }

    Integer user_num = (Integer) session.getAttribute("user_num");

    m.addAttribute("user_num", user_num);

    return "/meetingroom/meetingroom";
  }


  /* 검색 비스무리한 부분 ajax로 받기? */
  @PostMapping("/meetingroom")
  @ResponseBody
  public ResponseEntity<List<MeetingDto>> meetingroomSearch(@RequestBody MeetingDto meetingDto, HttpSession session){

    System.out.println("여기 타야하는데");
    try {
      int mr_user = (int) session.getAttribute("user_num");

      int mr_room = Integer.parseInt(meetingDto.getMtr_room_num());//1
      String mr_date = meetingDto.getMeet_date();//"2024-06-04"
      List<MeetingDto> list = meetingService.getAllList(mr_date, mr_room);




      return new ResponseEntity<List<MeetingDto>>(list, HttpStatus.OK);
    }catch (Exception e){
      e.printStackTrace();
      return new ResponseEntity<List<MeetingDto>>(HttpStatus.BAD_REQUEST);
    }

  }


  /* 예약 insert 하면 타는 url*/
  @PostMapping("/reserve/save")
  public String reserveSave(MeetingDto meetingDto,  HttpSession session) throws ParseException {


      Integer mr_user = (Integer) session.getAttribute("user_num");

      String meet_date = meetingDto.getMeet_date();//"2024-06-04"
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      Date mr_date = format.parse(meet_date);
      int mr_room = Integer.parseInt(meetingDto.getMtr_room_num());//1
      int mr_time = meetingDto.getMr_time();
      meetingDto.setMr_user(mr_user);
      meetingDto.setMr_date(mr_date);
      meetingDto.setMr_room(mr_room);
      meetingDto.setMr_time(mr_time);
      System.out.println("예약하는 파트: " + meetingDto);


      if(meetingService.makeReserve(meetingDto)!=1) {
        return "redirect:/meetingroom";
      }

    return "redirect:/";
  }




  private boolean loginChk(HttpSession session) {
    return session.getAttribute("user_num") != null;
  }
}
