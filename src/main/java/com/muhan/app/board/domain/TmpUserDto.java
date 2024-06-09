package com.muhan.app.board.domain;

import java.util.Date;

public class TmpUserDto {
    private int user_seqno;
    private int user_num;
    private String user_pw;
    private int user_dept;
    private int user_level;
    private String user_in_date;
    private String user_name;
    private String user_phone;
    private String user_addr;
    private String user_email;
    private String user_birth;
    private String user_photo;
    private Date user_reg_date;
    private int user_out;
    private Date user_out_date;
    private int user_work_date;


  public TmpUserDto(int user_num, int user_dept, int user_level, String user_in_date, String user_name, String user_phone, String user_addr, String user_email, String user_birth, Date user_reg_date) {
    this.user_num = user_num;
    this.user_dept = user_dept;
    this.user_level = user_level;
    this.user_in_date = user_in_date;
    this.user_name = user_name;
    this.user_phone = user_phone;
    this.user_addr = user_addr;
    this.user_email = user_email;
    this.user_birth = user_birth;
    this.user_reg_date = user_reg_date;
  }

  public int getUser_seqno() {
    return user_seqno;
  }

  public void setUser_seqno(int user_seqno) {
    this.user_seqno = user_seqno;
  }

  public int getUser_num() {
    return user_num;
  }

  public void setUser_num(int user_num) {
    this.user_num = user_num;
  }

  public String getUser_pw() {
    return user_pw;
  }

  public void setUser_pw(String user_pw) {
    this.user_pw = user_pw;
  }

  public int getUser_dept() {
    return user_dept;
  }

  public void setUser_dept(int user_dept) {
    this.user_dept = user_dept;
  }

  public int getUser_level() {
    return user_level;
  }

  public void setUser_level(int user_level) {
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

  public void setUser_photo(String user_photo) {
    this.user_photo = user_photo;
  }

  public Date getUser_reg_date() {
    return user_reg_date;
  }

  public void setUser_reg_date(Date user_reg_date) {
    this.user_reg_date = user_reg_date;
  }

  public int getUser_out() {
    return user_out;
  }

  public void setUser_out(int user_out) {
    this.user_out = user_out;
  }

  public Date getUser_out_date() {
    return user_out_date;
  }

  public void setUser_out_date(Date user_out_date) {
    this.user_out_date = user_out_date;
  }

  public int getUser_work_date() {
    return user_work_date;
  }

  public void setUser_work_date(int user_work_date) {
    this.user_work_date = user_work_date;
  }

  @Override
  public String toString() {
    return "TmpUserDto{" +
            "user_seqno=" + user_seqno +
            ", user_num=" + user_num +
            ", user_pw='" + user_pw + '\'' +
            ", user_dept=" + user_dept +
            ", user_level=" + user_level +
            ", user_in_date='" + user_in_date + '\'' +
            ", user_name='" + user_name + '\'' +
            ", user_phone='" + user_phone + '\'' +
            ", user_addr='" + user_addr + '\'' +
            ", user_email='" + user_email + '\'' +
            ", user_birth='" + user_birth + '\'' +
            ", user_photo='" + user_photo + '\'' +
            ", user_reg_date=" + user_reg_date +
            ", user_out=" + user_out +
            ", user_out_date=" + user_out_date +
            ", user_work_date=" + user_work_date +
            '}';
  }
}
