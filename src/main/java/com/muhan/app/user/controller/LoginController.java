package com.muhan.app.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muhan.app.approval.service.Appr_boardList_Service;
import com.muhan.app.attend.dao.Attend_dao;
import com.muhan.app.attend.service.Attend_service;
import com.muhan.app.user.dao.UserDao;
import com.muhan.app.user.domain.Session_dto;
import com.muhan.app.user.domain.UserDto;
import com.muhan.app.user.domain.UserLoginDto;
import com.muhan.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    Attend_service attend_service;
    @Autowired
    UserService user_Service;
    @Autowired
    Appr_boardList_Service apr_service;



    @GetMapping("/login")
    public String loginForm(HttpSession session) {

        // 세션이 있으면 (로그인 이미 했단 말) - 메인페이지로 이동
        if(session.getAttribute("user_num") != null) {
            return "redirect:/";
        }
        // 아니면 로그인 페이지 나타나게 하기
        return "user/loginForm";
    }

    @GetMapping("/logout") /*로그아웃하면 로그인페이지로 리다이렉트*/
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(UserLoginDto userLoginDto, HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("로그인을 시작합니다.");
        //유효성 검사-서버에 현재 받은 사번, pw 가 있는지
        Integer user_num = userLoginDto.getUser_num();
        String user_pw = userLoginDto.getUser_pw();
        boolean rememberId = userLoginDto.isRememberId();

//        Map map =new HashMap();
//        map.put("user_num",user_num);
//        map.put("user_pw",user_pw);

        if (!loginChk(user_num, user_pw)) {
            String msg = URLEncoder.encode("일치하는 회원정보가 없습니다.", "UTF-8");
            return "redirect:/login?msg="+msg;
        }

        // 쿠키생성
        Cookie cookie = new Cookie("user_num", String.valueOf(user_num)); // "user_num":"10001"
        if (!rememberId) { // '사번기억하기' 체크 안했을때
            cookie.setMaxAge(0);
        }
        response.addCookie(cookie); // 사용자에게 보내기

        // 세션생성
        HttpSession session = request.getSession(); // 세션 객체 얻어오기 (세션 준비)
        session.setAttribute("isLoginSucceded", true);

// *************************************************
        // 유저 정보 가져오기

        // 첫 로그인인지 확인(출근 시간 여부 체크
        Map map = new HashMap();
        map.put("att_user", user_num);

        //첫 로그인(출근 시간 없음)
        if(attend_service.get_first_attend(map) == 0){
            Calendar now = Calendar.getInstance();
            Map map2 = new HashMap();
            map2.put("att_user",user_num);
            map2.put("att_start",now);
            attend_service.set_attend_start_time(map2);
        }else {
            System.out.println("이미 출근 되어 있음.");
        }

        // 유저의 현재 정보 가져오기
        Session_dto dto = user_Service.get_user_info(user_num);

        // 결재 내역 가져오기
        int appr_count = apr_service.get_approval_count(user_num);
        dto.setAppr_count(appr_count);

        System.out.println(dto);

        //세션에 세팅
        session.setAttribute("user_num",dto.getUser_num());
        session.setAttribute("user_name",dto.getUser_name());
        session.setAttribute("user_photo",dto.getUser_photo());
        session.setAttribute("level_name",dto.getLevel_name());
        session.setAttribute("att_start",dto.getAtt_start());
        session.setAttribute("att_end",dto.getAtt_end());
        session.setAttribute("appr_count",dto.getAppr_count());
        session.setAttribute("user_dept",dto.getUser_dept());
        session.setAttribute("user_level",dto.getUser_level());

// *************************************************

        // prevPage가 있으면 해당 페이지로 이동, 비어있으면 메인페이지로 이동
        String prevPage = String.valueOf(session.getAttribute("prevPage")); /* 0602 - 살렸음: 이전 페이지 있으면 거기로 가게*/


        /*메인페이지로 변경예정    ex) "redirect:/mainPage";  */
        return "redirect:"+(("null".equals(prevPage))?"/":prevPage);//0602 - 살렸음
    }


    // 사번,pw를 DB에 보내서 해당 정보가 있냐 없냐 판별
    private boolean loginChk(Integer user_num , String user_pw){
        UserDto userDto = user_Service.selectUser(user_num);

        if (userDto == null) return false;// 결과가 null이면 회원 없다는 뜻이니깐 false

        return userDto.getUser_pw().equals(user_pw);

    }

    @GetMapping("/mypageMain")
    public String test() {
        return "user/mypageMain";
    }


    @GetMapping("/findPw")
    public String test2() {
        return "user/searchPw";
    }

    @PostMapping("/findPw")
    public String findPw(UserDto userDto, HttpSession session, Model m) throws Exception {
//        System.out.println("userDto: " + userDto);
        UserDto user = user_Service.findPwChk(userDto);

//        System.out.println("user: " + user);

        if(user != null) {
//            System.out.println("유저 번호 :: " + user.getUser_num());
            m.addAttribute("user_num", user.getUser_num());

            return "redirect:/changePw";

        } else {
            //입력한 정보가 일치하는 회원이 없으므로 다시 입력하라 안내
            String msg = URLEncoder.encode("일치하는 회원정보가 없습니다.", "UTF-8");
            return "redirect:/findPw?msg=" + msg;

        }
    }

    @GetMapping("/changePw")
    public String changePw(int user_num, Model m) throws Exception {

        m.addAttribute("user_num", user_num);
        return "user/searchPwChangePw";
    }
    @PostMapping("/changePw/save")
    public String changPwsave(HttpSession session, Model m, int user_num, String user_pw){
        Map map = new HashMap();
        map.put("user_num", user_num);
        map.put("user_pw", user_pw);
        user_Service.changePw(map);
//        UserDto user = user_service.findPwChk(userDto);

//        System.out.println(userDto.toString());
//        UserService.changePw(UserDto);

        return "redirect:/";
    }
}
