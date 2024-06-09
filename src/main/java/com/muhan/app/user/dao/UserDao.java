package com.muhan.app.user.dao;

import com.muhan.app.user.domain.Session_dto;
import com.muhan.app.user.domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {
    @Autowired
    SqlSession session;

    String namespace="com.muhan.app.user.dao.UserInfoMapper.";


    /******************유저 정보 가져오기********************/
    public Session_dto get_user_info(Integer user_num){
        return session.selectOne(namespace+"get_user_info",user_num);
    }


    /***************** INSERT *****************/
    public int insertUser(UserDto userDto) {
        return session.insert(namespace+"insertUser", userDto);
    }



    /***************** SELECT *****************/
    public int selectUserNum() {
        return session.selectOne(namespace+"selectUserNum");
    } //회원가입용(사번자동불러오기)

    public UserDto selectUser(Integer user_num) {
        return session.selectOne(namespace+"selectUser", user_num);
    } //로그인용(사용자 아이디 가져와서 사용자 정보 뽑아내기)

    public UserDto getLoginUserInfo(int userNum) {
        return session.selectOne(namespace+"getLoginUserInfo", userNum);
    }// 저장된 세션 아이디 값으로 로그인 한 유저 정보 조회

    public UserDto findPwChk (UserDto userDto) {
        return session.selectOne(namespace+"findPwChk" , userDto);
    } //비밀번호 찾기용(사번, 이름, 연락처 db에 정보있는지 확인하는 용)


    /***************** UPDATE *****************/
    public int updateInfo(UserDto userDto) {
//        System.out.println("userDto3 = " + userDto);

        Map map = new HashMap();
        map.put("user_phone", userDto.getUser_phone());
        map.put("user_addr", userDto.getUser_addr());
        map.put("user_photo", userDto.getUser_photo());
        map.put("user_num", userDto.getUser_num());

        return session.update(namespace+"updateInfo", map);
    }

    public int updatePw(UserDto userDto) {
//        System.out.println("userDtouserDto: " + userDto);
//        System.out.println("여기다: " + userDto.getUser_pw());

        return session.update(namespace+"updatePw", userDto);
    }

    public int changePw(Map map) {
        return session.update(namespace+"changePw", map);
    }



    //    이영규2024-05-31 추가 내역
    public List<Session_dto> get_dept_people(Map map){
        return session.selectList(namespace+"get_dept_people",map);
    }

}

