package com.muhan.app.user.service;

import com.muhan.app.user.dao.UserDao;
import com.muhan.app.user.domain.Session_dto;
import com.muhan.app.user.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public Session_dto get_user_info(Integer user_num){
        return userDao.get_user_info(user_num);
    }
    public int selectUserNum() { return userDao.selectUserNum(); }

    public UserDto selectUser(Integer user_num) {
        return userDao.selectUser(user_num);
    }

    public UserDto getLoginUserInfo(int userNum) {
        return userDao.getLoginUserInfo(userNum);
    }

    public int UpdateInfo(UserDto userDto) { return userDao.updateInfo(userDto); }

    public int insertUser(UserDto userDto) { return userDao.insertUser(userDto); }


    public int UpdatePw(UserDto userDto) { return userDao.updatePw(userDto); }


    public UserDto findPwChk(UserDto userDto) {
        return userDao.findPwChk(userDto);

    }

    public int changePw(Map map) {
        return userDao.changePw(map);
    }


//    이영규추가 2024-05-31
    public List<Session_dto> get_dept_people(Map map){
        return userDao.get_dept_people(map);
    }

}
