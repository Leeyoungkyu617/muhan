package com.muhan.app.user.controller;

import com.muhan.app.user.domain.UserDto;
import com.muhan.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.URLEncoder;


@Controller
public class MypageController {

    @Autowired
    UserService userService;

    @GetMapping("/infoChange")
    public String info(Model model, HttpSession session) {
        int userNum = Integer.parseInt(session.getAttribute("user_num").toString());
        UserDto updateUser = userService.getLoginUserInfo(userNum);
//            System.out.println(updateUser.toString());
        model.addAttribute("user", updateUser);
        System.out.println(updateUser);
        return "user/infoChange";
    }

    @GetMapping("/pwChange")
    public String pwChange(Model model, HttpSession session) {

        return "user/pwChange";
    }

    private static final String F_PATH = "C:/Users/user/Desktop/KH/muhan/job/muhan/src/main/webapp/resources/img/";
    @PostMapping("/infoChange/save")
    public String saveInfo(Model model, UserDto userDto, HttpSession session, @RequestParam(value="f_file", required = false) MultipartFile mf) {
//        Map map = new HashMap();
//        map.put("user_phone", user_phone);
//        map.put("user_addr", user_addr);
//        map.put("user_photo", user_photo);
//        System.out.println("userDto1 = " + userDto);
        if (mf != null && !mf.isEmpty()) {
            String originalFileName = mf.getOriginalFilename();
            String safeFile = System.currentTimeMillis() + originalFileName;
            System.out.println("originalFileName: " + originalFileName);
            System.out.println("safeFile: " + safeFile);

            try {
                mf.transferTo(new File(F_PATH + safeFile));// 물리적 파일 저장하는 단계
                session.setAttribute("user_photo",safeFile);
                userDto.setUser_photo(safeFile);

            } catch (Exception e) {
                e.printStackTrace();
                // Optional: Add custom error handling or logging here
            }
        } else {
            System.out.println("No file uploaded");
        }


        userService.UpdateInfo(userDto);

        return "user/mypageMain";
    }



    @PostMapping("/pwChange/save")
    public String test(HttpSession session, UserDto userDto, String current_pw, String new_pw) throws Exception {

        int userNum = Integer.parseInt(session.getAttribute("user_num").toString());

        UserDto user = userService.selectUser(userNum); // 서버에 현재 세션 user_num 보내서 해당 유저 정보 싹 다 가져오기


        String CurrentPw = userDto.getCurrent_pw(); // 현재 뷰 화면에 사용자가 입력한 '현재 비밀번호' 저장

        String NewPw = userDto.getNew_pw(); // 현재 뷰 화면에 사용자가 입력한 '새로운 비밀번호' 저장

        if (user.getUser_pw().equals(CurrentPw)) { // '세션의 비밀번호'와 입력한 '현재 비밀번호'가 같으면 비번 업뎃 하러 가기
            user.setUser_pw(NewPw);
            //System.out.println("user111: " + user);
            userService.UpdatePw(user);
            return "redirect:/mypageMain";
        } else { // 같지 않으면 현재 비밀번호 틀리다고 메세지 띄우기
            String msg = URLEncoder.encode("현재 비밀번호가 맞지 않습니다.", "UTF-8");
            return "redirect:/pwChange?msg=" + msg;
        }
    }
}
