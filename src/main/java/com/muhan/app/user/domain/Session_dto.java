package com.muhan.app.user.domain;

import java.util.Date;

public class Session_dto {
    private int user_num;
    private String user_name;
    private String user_photo;
    private String level_name;
    private Date att_start;
    private Date att_end;
    private int user_dept;

    private int user_level;

    public Session_dto(int user_num, String user_name, String user_photo, String level_name, Date att_start, Date att_end, int user_dept, int user_level, int appr_count) {
        this.user_num = user_num;
        this.user_name = user_name;
        this.user_photo = user_photo;
        this.level_name = level_name;
        this.att_start = att_start;
        this.att_end = att_end;
        this.user_dept = user_dept;
        this.user_level = user_level;
        this.appr_count = appr_count;
    }
    private int appr_count;




    //기본 생성자
    public Session_dto(){}
    // 결재 상신시 상신 대상 목록 가져올 생성자
    public Session_dto(String user_name, String level_name, int user_num){
//       게시판 담당자 가져와야할 dto
        System.out.println("게시판 결재 대상 담당 dto");
        this.user_name = user_name;
        this.level_name = level_name;
        this.user_num = user_num;
    }
    public Session_dto(int user_level, int user_dept, int user_num, String user_name, String user_photo, String level_name, Date att_start, Date att_end, int appr_count) {
        this.user_level = user_level;
        this.user_dept = user_dept;
        this.user_num = user_num;
        this.user_name = user_name;
        this.user_photo = user_photo;
        this.level_name = level_name;
        this.att_start = att_start;
        this.att_end = att_end;
        this.appr_count = appr_count;

    }

    public int getUser_level() {
        return user_level;
    }

    public void setUser_level(int user_level) {
        this.user_level = user_level;
    }

    public int getUser_dept() {
        return user_dept;
    }

    public void setUser_dept(int user_dept) {
        this.user_dept = user_dept;
    }
    public int getUser_num() {
        return user_num;
    }

    public void setUser_num(int user_num) {
        this.user_num = user_num;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public Date getAtt_start() {
        return att_start;
    }

    public void setAtt_start(Date att_start) {
        this.att_start = att_start;
    }

    public Date getAtt_end() {
        return att_end;
    }

    public void setAtt_end(Date att_end) {
        this.att_end = att_end;
    }

    public int getAppr_count() {
        return appr_count;
    }

    public void setAppr_count(int appr_count) {
        this.appr_count = appr_count;
    }
}
