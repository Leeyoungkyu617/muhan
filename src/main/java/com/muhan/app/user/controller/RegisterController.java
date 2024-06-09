package com.muhan.app.user.controller;

import com.muhan.app.user.dao.UserDao;
import com.muhan.app.user.domain.UserDto;
import com.muhan.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    UserDao userDao;

    @Autowired
    private UserService userService; // 사용자 관련 비즈니스 로직을 처리하는 클래스

    @GetMapping("/register/join")
    public String loginForm(Model model) {

        //
        int uno = userService.selectUserNum(); // 10002
        int newUno = uno + 1; // 10002 (새로 입력할 번호)
        System.out.println(uno + "," + (uno + 1));
        String email = newUno + "@muhan.com";

        model.addAttribute("newUno", newUno); //화면에 보내줄 +1된 사번
        model.addAttribute("email", email); //화면에 보내줄 위의 사번+도메인



        return "user/registerForm";
    }

    private static final String F_PATH = "C:/Users/user/Desktop/KH/muhan/job/muhan/src/main/webapp/resources/img/";
    @PostMapping("/register/save")
    public String save(UserDto userDto, @RequestParam(value="f_file", required = false) MultipartFile mf, HttpSession session) throws Exception {
//        System.out.println("userDto: " + userDto);

        if (mf != null && !mf.isEmpty()) {
            String originalFileName = mf.getOriginalFilename();
            String safeFile = System.currentTimeMillis() + originalFileName;
            System.out.println("originalFileName: " + originalFileName);
            System.out.println("safeFile: " + safeFile);

            try {
                mf.transferTo(new File(F_PATH + safeFile));// 물리적 파일 저장하는 단계
                userDto.setUser_photo(safeFile);

            } catch (Exception e) {
                e.printStackTrace();
                // Optional: Add custom error handling or logging here
            }
        } else {
            System.out.println("No file uploaded");
        }


        // 저장
        userService.insertUser(userDto);
//        System.out.println("userDto: " + userDto);


        String msg = URLEncoder.encode("생성 성공", "UTF-8");
        return "redirect:/register/join?msg="+msg;

    }
}
