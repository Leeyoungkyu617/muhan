package com.muhan.app.user.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.w3c.dom.Text;

import java.util.Date;

//UserDto 클래스 선언, Private 변수 생성
public class UserDto {
    private String dept_name;
    private String level_name;
    private Integer user_seqno;
    private Integer  user_num;
    private String user_pw;

    private String current_pw;
    private String new_pw;
    private Integer user_dept;
    private Integer user_level;
    private String user_in_date;
    private String user_name;
    private String user_phone;
    private String user_addr;
    private String user_email;
    private String user_birth;
    private String user_photo;
    private Date user_reg_date;
    private Integer user_out;
    @DateTimeFormat(pattern = "yyyy-MM-dd") private Date user_out_date;
    private Integer user_work_date;

    public UserDto() {


    }

    public UserDto(String dept_name, String level_name, Integer user_seqno, Integer user_num, String user_pw, Integer user_dept, Integer user_level, String user_in_date, String user_name, String user_phone, String user_addr, String user_email, String user_birth, String user_photo, Date user_reg_date, Integer user_out, Date user_out_date, Integer user_work_date) {
        this.dept_name = dept_name;
        this.level_name = level_name;
        this.user_seqno = user_seqno;
        this.user_num = user_num;
        this.user_pw = user_pw;
        this.user_dept = user_dept;
        this.user_level = user_level;
        this.user_in_date = user_in_date;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_addr = user_addr;
        this.user_email = user_email;
        this.user_birth = user_birth;
        this.user_photo = user_photo;
        this.user_reg_date = user_reg_date;
        this.user_out = user_out;
        this.user_out_date = user_out_date;
        this.user_work_date = user_work_date;
    }

    public UserDto(Integer user_seqno, Integer user_num, String user_pw, String current_pw, String new_pw, Integer user_dept, Integer user_level, String user_in_date, String user_name, String user_phone, String user_addr, String user_email, String user_birth, String user_photo, Date user_reg_date, Integer user_out, Date user_out_date, Integer user_work_date) {
        this.current_pw = current_pw;
        this.new_pw = new_pw;
        this.user_seqno = user_seqno;
        this.user_num = user_num;
        this.user_pw = user_pw;
        this.user_dept = user_dept;
        this.user_level = user_level;
        this.user_in_date = user_in_date;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_addr = user_addr;
        this.user_email = user_email;
        this.user_birth = user_birth;
        this.user_photo = user_photo;
        this.user_reg_date = user_reg_date;
        this.user_out = user_out;
        this.user_out_date = user_out_date;
        this.user_work_date = user_work_date;
    }


//    public UserDto(String user_pw) {
//        System.out.println(999);
//        this.user_pw = user_pw;
//    }

    public String getCurrent_pw() {
        return current_pw;
    }

    public void setCurrent_pw(String current_pw) {
        this.current_pw = current_pw;
    }

    public String getNew_pw() {
        return new_pw;
    }

    public void setNew_pw(String new_pw) {
        this.new_pw = new_pw;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public Integer getUser_seqno() {
        return user_seqno;
    }

    public void setUser_seqno(Integer user_seqno) {
        this.user_seqno = user_seqno;
    }

    public Integer getUser_num() {
        return user_num;
    }

    public void setUser_num(Integer user_num) {
        this.user_num = user_num;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public Integer getUser_dept() {
        return user_dept;
    }

    public void setUser_dept(Integer user_dept) {
        this.user_dept = user_dept;
    }

    public Integer getUser_level() {
        return user_level;
    }

    public void setUser_level(Integer user_level) {
        this.user_level = user_level;
    }

    public String getUser_in_date() {
        return user_in_date;
    }

    public void setUser_in_date(String user_in_date) {
        this.user_in_date = user_in_date;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_addr() {
        return user_addr;
    }

    public void setUser_addr(String user_addr) {
        this.user_addr = user_addr;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_birth() {
        return user_birth;
    }

    public void setUser_birth(String user_birth) {
        this.user_birth = user_birth;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String  user_photo) {
        this.user_photo = user_photo;
    }

    public Date getUser_reg_date() {
        return user_reg_date;
    }

    public void setUser_reg_date(Date user_reg_date) {
        this.user_reg_date = user_reg_date;
    }

    public Integer getUser_out() {
        return user_out;
    }

    public void setUser_out(Integer user_out) {
        this.user_out = user_out;
    }

    public Date getUser_out_date() {
        return user_out_date;
    }

    public void setUser_out_date(Date user_out_date) {
        this.user_out_date = user_out_date;
    }

    public Integer getUser_work_date() {
        return user_work_date;
    }

    public void setUser_work_date(Integer user_work_date) {
        this.user_work_date = user_work_date;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "user_seqno=" + user_seqno +
                ", user_num=" + user_num + "" +
                ", user_pw='" + user_pw + "" +
                ", user_dept=" + user_dept + "" +
                ", user_level=" + user_level + "" +
                ", user_in_date=" + user_in_date +
                ", user_name='" + user_name + "" +
                ", user_phone='" + user_phone + "" +
                ", user_addr='" + user_addr + "" +
                ", user_email='" + user_email + "" +
                ", user_birth=" + user_birth + "" +
                ", user_photo=" + user_photo + "" +
                ", user_reg_date=" + user_reg_date +
                '}';
    }

}