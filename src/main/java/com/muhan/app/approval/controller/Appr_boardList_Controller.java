package com.muhan.app.approval.controller;

import com.muhan.app.approval.domain.Appr_appro_target_dto;
import com.muhan.app.approval.domain.Appr_boardList_Dto;
import com.muhan.app.approval.service.Appr_boardList_Service;
import com.muhan.app.common.PageHandler;
import com.muhan.app.user.domain.Session_dto;
import com.muhan.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Appr_boardList_Controller {

    @Autowired
    Appr_boardList_Service boardList_service;
    @Autowired
    UserService user_service;

    // 이미지를 저장할 최종 path
    private static final String F_PATH = "C:/Users/user/Desktop/KH/muhan/job/muhan/src/main/webapp/resources/img/";

    @GetMapping("/approval")
    public String arrpr_board_select(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(!loginChk(session)){
//            로그인 페이지로 이동해야함(추후 변경)
            session.setAttribute("prePage",request.getServletPath());
//            return "redirect:/login"
            return "redirect:/login";
        }

        return "approval/appr_select_list";
    }

    @GetMapping("/approval/selected")
    public String appr_boardList(Integer page_now, Integer page_size, Integer nav_page, Model m, HttpServletRequest request, String option){
        System.out.println("...approval start....");
//      결제 해당 사람이 올린거 가져오기
//      로그인 여부 확인(강제 화면이동 막기)
        HttpSession session = request.getSession();
        if(!loginChk(session)){
//            로그인 페이지로 이동해야함(추후 변경)
            session.setAttribute("prePage",request.getServletPath());
//            return "redirect:/login"
            return "redirect:/login";
        }
        if(option == null){
            return "redirect:/approval/";
        }


        // 현재 페이지 -> 없을 경우 1, 페이지사이즈 없을 경우 10
        if(page_now == null)page_now=1;
        if (page_size == null)page_size=10;
        if(nav_page == null) nav_page=10;

        //파라미터 확인(결재올린문서 - A / 결재해야할 문서 M
        String str_check_option = request.getParameter("option");
        System.out.println("option value :: " + str_check_option);
        //토탈 결제 리스트 변수
        int total_count = 0;

        // 쿼리에 사용할 map
        Map sql_map = new HashMap();
        sql_map.put("option", str_check_option);

        String str_chk_who = "";
        if ("A".equals(str_check_option)){
            str_chk_who = "apr_writer";
        }else{
            str_chk_who = "apr_upper";
        }
        //나중에는 세션 아이디 넣기
        sql_map.put(str_chk_who,session.getAttribute("user_num"));

        total_count = boardList_service.get_all_count(sql_map);


        PageHandler pageHandler = new PageHandler(total_count, page_now, page_size, nav_page);


        // 가져올 글 가져오기
        Map map = new HashMap();
        map.put("option", str_check_option);
        map.put(str_chk_who, session.getAttribute("user_num"));
        map.put("offset",(page_now - 1) * page_size);
        map.put("page_size",page_size);

        // 기본 페이지 설정에 따라 10개씩 가져오기
        List<Appr_boardList_Dto> list = boardList_service.get_page(map);

        // 모델에 추가해서 선김기
        m.addAttribute("list",list);
        m.addAttribute("ph",pageHandler);
        m.addAttribute("option",str_check_option);
        System.out.println("view count :: " + list.size());




        return "approval/appr_boardList";
    }

    @GetMapping("/approval/appr_insert")
    public String appr_insert(Model m, HttpServletRequest request, String option){

        HttpSession session = request.getSession();
        if(!loginChk(session)){
//            로그인 페이지로 이동해야함(추후 변경)
            session.setAttribute("prePage",request.getServletPath());
//            return "redirect:/login"
            return "redirect:/login";
        }
        if(option == null){
            return "redirect:/approval";
        }


        //모드 추가 new -> 신규작성, modi -> 수정
        m.addAttribute("mode","new");
        m.addAttribute("user_name",session.getAttribute("user_name"));
        m.addAttribute("user_num",session.getAttribute("user_num"));
//      세션 아이디 값 가져오기

        Map map = new HashMap();
        map.put("user_dept",session.getAttribute("user_dept"));
        map.put("user_level", session.getAttribute("user_level"));

        List<Session_dto> list =  user_service.get_dept_people(map);

        m.addAttribute("dept_peoples",list);



        return "approval/appr_insert";
    }

    @GetMapping("/approval/read")
    public String read(Integer apr_seq, Integer page_now, Integer page_size, Integer nav_page, String option,  Model m, HttpServletRequest request){
//<c:url value="/approval/read?apr_seq=${listDto.apr_seq}&page_now=${ph.page_now}&page_size=${ph.page_size}&nav_page=${ph.nav_page}"/>"

        HttpSession session = request.getSession();
        if(!loginChk(session)){
//            로그인 페이지로 이동해야함(추후 변경)
            session.setAttribute("prePage",request.getServletPath());
//            return "redirect:/login"
            return "redirect:/login";
        }

        System.out.println("게시글 상세");
//        Appr_boardList_Dto appr_boardList_dto = boardList_service.(apr_seq);

        Appr_boardList_Dto appr_dto = boardList_service.read(apr_seq);


//      결제 대상 다시 가져오기
        Map map = new HashMap();
        map.put("user_dept",session.getAttribute("user_dept"));
        map.put("user_level", session.getAttribute("user_level"));

        List<Session_dto> list =  user_service.get_dept_people(map);

        System.out.println("****dto taget = " + appr_dto.getUpper_user_name() + " // " + appr_dto.getUpper_level_name()+"    ****");


        m.addAttribute("dept_peoples",list);
        m.addAttribute("appr_dto", appr_dto);
        m.addAttribute("page_now", page_now);
        m.addAttribute("page_size", page_size);
        m.addAttribute("nav_page", nav_page);
        m.addAttribute("mode","modify");
        m.addAttribute("option", option);

        System.out.println(appr_dto);

        return "approval/appr_insert";
    }

    @GetMapping("/approval/appr_select_appval")
    @ResponseBody
    public ResponseEntity<List<Appr_appro_target_dto>> appr_select_appval(){
        System.out.println("결재선지정");
        Map map = new HashMap();
        map.put("user_dept", 1);
        map .put("user_level",5);

        try {
            List<Appr_appro_target_dto> list = boardList_service.select_target();
            System.out.println("개수 확이 :; " + list.size());

            return new ResponseEntity<List<Appr_appro_target_dto>>(list, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<Appr_appro_target_dto>>(HttpStatus.BAD_REQUEST);
        }


    }
    @GetMapping("/approval/write")
    public String test(){
        return "approval/appr_select_list";
    }



//    public String appr_write(@RequestParam(value="f_file", required = false) MultipartFile mf, String apr_seq, Integer apr_upper, String apr_title, Integer apr_writer, String apr_contents){
//
//        System.out.println("되나???");
//
//
//        return "redirect:/approval";
//    }

//    http://localhost:8080/approval/write?apr_seq=&apr_upper=10003&apr_title=134&apr_writer=10001&apr_contents=1313134

    //   write 데이터 베이스 값 입력
    @PostMapping("/approval/write")
    public String appr_write(@RequestParam(value="f_file", required = false) MultipartFile mf, Integer apr_seq, int apr_upper, String apr_title, int apr_writer, String apr_contents){
//
//      왜 dto타입으로 받으면 에러가 날까요???
        System.out.println("확인 여기까지111111");
        Appr_boardList_Dto dto = new Appr_boardList_Dto(apr_seq,apr_upper,apr_title,apr_writer,apr_contents);
        System.out.println(dto);

//Appr_boardList_Dto{apr_seq=null, apr_writer=10001, apr_upper=10003, apr_title='asdf', apr_contents='asdf', apr_attach='null', apr_ori_attach='null', apr_u_time=null, apr_p_time=null, apr_chk=0, apr_reg_date=null}

//        첨부파일 확인하기
        if(mf != null && !mf.isEmpty()){
            String orginal_file_name = mf.getOriginalFilename();
            long now_time = System.currentTimeMillis();
            String save_file = F_PATH + now_time+orginal_file_name;
            String only_file_name = now_time+orginal_file_name;

            System.out.println("원래 파일명 :: " + orginal_file_name + " // 저장 파일명 :: " + save_file);
            //첨부파일 저장
            try {
                // 경로에 물리적 저장
                mf.transferTo(new File(save_file));
                dto.setApr_attach(only_file_name);
                dto.setApr_ori_attach(orginal_file_name);
                // 파일 명만 저장
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        System.out.println("마지막  dto 확인 :: " + dto);

        boardList_service.write(dto);

        return "redirect:/approval";
    }


    @PostMapping("/approval/modify")
    public String modify(@RequestParam(value="f_file", required = false) MultipartFile mf, Integer apr_seq, int apr_upper,
                         String apr_title, int apr_writer, String apr_contents, String apr_attach,String apr_ori_attach){

        System.out.println("*************수정합니다******************");
        Appr_boardList_Dto dto = new Appr_boardList_Dto(apr_seq, apr_upper, apr_title, apr_writer, apr_contents, apr_attach, apr_ori_attach);

        if(mf != null && !mf.isEmpty()) {
            String orginal_file_name = mf.getOriginalFilename();
            long now_time = System.currentTimeMillis();
            String save_file = F_PATH + now_time + orginal_file_name;
            String only_file_name = now_time + orginal_file_name;

            System.out.println("원래 파일명 :: " + orginal_file_name + " // 저장 파일명 :: " + save_file);
            //첨부파일 저장
            try {
                // 경로에 물리적 저장
                mf.transferTo(new File(save_file));
                dto.setApr_attach(only_file_name);
                dto.setApr_ori_attach(orginal_file_name);
                // 파일 명만 저장
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        boardList_service.modify(dto);

        return "redirect:/approval";
    }

    @PostMapping("/approval/remove")
    public String romove(Integer apr_seq){

        boardList_service.remove(apr_seq);

        return "redirect:/approval";
    }

    @GetMapping("/approval/accept_is_not")
    public String accept_is_not(int check, int apr_seq){
        System.out.println("Check ::  " + check +  " // apr_seq :: " + apr_seq);
        Map map = new HashMap();
        map.put("apr_chk",check);
        map.put("apr_seq",apr_seq);
        boardList_service.accept_is_not(map);

        return "redirect:/approval";
    }





    private boolean loginChk(HttpSession session) {
        return session.getAttribute("user_num") != null;
        // 세션에 id가 있으면 결과는 true
        // 없으면 false 나옴
    }

//    @GetMapping("/approval/get_appr_target_list")
//    @ResponseBody
//    public ResponseEntity<List<Appr_appro_target_dto>> tartget_list(){
//        try {
//            List<Appr_appro_target_dto> list = boardList_service.select_target();
//            System.out.println("가져온 개수 :: " + list.size());
//            return new ResponseEntity<List<Appr_appro_target_dto>>(list,HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<List<Appr_appro_target_dto>>(HttpStatus.BAD_REQUEST);
//        }
//    }

//    @GetMapping("/approval/test")
//    public String text(){
//        return "approval/appr_select_appval";
//    }
//
//    // 세션에 값중 id값이 있을 경우 체크해서 결과 리턴
//    private boolean loginChk(HttpSession session){
//        return session.getAttribute("id") != null;
//    }
}
