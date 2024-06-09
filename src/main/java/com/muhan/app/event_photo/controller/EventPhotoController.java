package com.muhan.app.event_photo.controller;

import com.muhan.app.common.PageHandler;
import com.muhan.app.event_photo.domain.EventPhotoDto;
import com.muhan.app.event_photo.service.EventPhotoService;
import com.muhan.app.user.domain.UserDto;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EventPhotoController {

    @Autowired
    EventPhotoService service;


    // 추가버튼 누르면 나오는거
    @GetMapping("/event_photo/write")
    public String write(Model m) {

        m.addAttribute("mode","new");
        return "event_photo/eventPhotoAdmin";
    }




    //수정버튼 누르면 나오는거(1)
    @GetMapping("/event_photo/modify")
    public String modify(Integer page_now, Integer page_size, Integer photo_seq, Model m) {
        if(page_now == null) page_now=1;
        if (page_size == null) page_size=9;

        EventPhotoDto list = service.read(photo_seq);

        m.addAttribute("list",list);
        m.addAttribute("mode","modify");

        return "event_photo/eventPhotoAdmin";
    }

    //수정버튼 누르면 나오는거(2)
    @PostMapping("/event_photo/update")
    public String update(EventPhotoDto dto, @RequestParam("photo_seq") Integer photoSeq,@RequestParam(value="f_file", required = false) MultipartFile mf, HttpServletRequest req, String page_now, String page_size) {

//        dto.setPhoto_uploader(dto.getPhoto_uploader());
//        dto.setPhoto_title(dto.getPhoto_title());
        dto.setPhoto_seq(photoSeq);
//        dto.setPhoto_new_name(dto.getPhoto_new_name());

        if (mf != null && !mf.isEmpty()) {
            /* 파일 업로드 */
            String originalFileName = mf.getOriginalFilename();
            long current = System.currentTimeMillis();
            String photo_new_name = current + originalFileName;
            String saveFile = F_PATH + photo_new_name; // 파일 경로에는 photo_new_name 사용

            dto.setPhoto_new_name(photo_new_name);

            try {
                mf.transferTo(new File(saveFile));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // 사진이 수정되지 않은 경우 기존 사진 이름 유지
            EventPhotoDto existingPhoto = service.read(photoSeq); // 기존 사진 정보 가져오기
            dto.setPhoto_new_name(existingPhoto.getPhoto_new_name());
        }

        System.out.println("dto2" + dto);

        service.update(dto);

        return "redirect:/event_photo?page_now="+page_now+"&page_size="+page_size;
    }




    // 저장버튼 누르면 나오는거
    private static final String F_PATH = "C:/Users/user1/Desktop/muhan_hmkesymh/muhan/src/main/webapp/resources/img/";
    @PostMapping("/event_photo")
    public String write(@RequestParam(value="f_file", required = false) MultipartFile mf, EventPhotoDto dto, HttpSession session ) {

        System.out.println("저장버튼 나오는거");
//        Integer photo_uploader = 10002;
//
//        dto.setPhoto_uploader(photo_uploader);
        dto.setPhoto_title(dto.getPhoto_title());


        /* 파일 업로드 */
        String originalFileName = mf.getOriginalFilename();
        long current = System.currentTimeMillis();
        String photo_new_name = current + originalFileName;

        String saveFile = F_PATH + photo_new_name; // 파일 경로에는 photo_new_name 사용


        dto.setPhoto_new_name(photo_new_name);

        try {
            mf.transferTo(new File(saveFile));
        } catch (Exception e) {
            e.printStackTrace();
        }

        service.write(dto);

        return "redirect:/event_photo?page_now=1&page_size=9";
    }


    // 삭제
    @PostMapping("/event_photo/admin")
    public String delete(EventPhotoDto dto, HttpSession session) {
        System.out.println("삭제버튼 나오는거");


        dto.setPhoto_seq(dto.getPhoto_seq());

        service.remove(dto.getPhoto_seq(), dto.getPhoto_uploader());

        return "redirect:/event_photo?page_now=1&page_size=9";

    }


    @GetMapping("/event_photo")
    public String event_photo(Integer totalCount, Integer page_now, Integer page_size, Integer nav_page, EventPhotoDto ep, UserDto ud, Model m, HttpServletRequest request) {
        System.out.println("게시판 로딩 페이지 나오는거");
        HttpSession session = request.getSession();

        totalCount = service.getPhotoCount();


        if(page_now == null) page_now=1;
        if (page_size == null) page_size=9;
        if(nav_page == null) nav_page=5;


        PageHandler pageHandler = new PageHandler(totalCount, page_now, page_size, nav_page);



        Map map = new HashMap<>();
        map.put("photo_offset", (page_now - 1) * page_size);
        map.put("photo_page_size", page_size);


        List<EventPhotoDto> list = service.getList(map);
        System.out.println("listlist:" + list);
        System.out.println("유져번호: " + session.getAttribute("user_num"));
        m.addAttribute("list", list);
        m.addAttribute("ph", pageHandler);
        m.addAttribute("eventPhotoDto", ep);

        m.addAttribute("user_num", session.getAttribute("user_num"));

        System.out.println("********************************");
        System.out.println(ep);
        return "event_photo/eventPhoto";
    }

}

