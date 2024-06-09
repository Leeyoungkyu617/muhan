package com.muhan.app.meetingroom.domain;

import java.util.Date;

public class MeetingDto {
  private int mr_seq;
  private int mr_user;
  private Date mr_date;
  private int mr_room;
  private int mr_time;
  private Date mr_reg_date;

  private String mtr_room_num;//ajax로 받아오는 value값
  private String meet_date;//ajax로 받아오는 value(날짜값)

  public MeetingDto(){
    System.out.println("*********기본 생성자*************");
  }

  public MeetingDto(String mtr_room_num, String meet_date) {
    System.out.println("내타켓");
    this.mtr_room_num = mtr_room_num;
    this.meet_date = meet_date;
  }

  public MeetingDto(int mr_user, Date mr_date, int mr_room, int mr_time) {
    System.out.println("!11111111111");
    this.mr_user = mr_user;
    this.mr_date = mr_date;
    this.mr_room = mr_room;
    this.mr_time = mr_time;
  }

  public MeetingDto(int mr_seq, int mr_user, Date mr_date, int mr_room, int mr_time, Date mr_reg_date) {
    System.out.println("22222222222");
    this.mr_seq = mr_seq;
    this.mr_user = mr_user;
    this.mr_date = mr_date;
    this.mr_room = mr_room;
    this.mr_time = mr_time;
    this.mr_reg_date = mr_reg_date;
  }


  public String getMtr_room_num() {
    return mtr_room_num;
  }

  public void setMtr_room_num(String mtr_room_num) {
    this.mtr_room_num = mtr_room_num;
  }

  public String getMeet_date() {
    return meet_date;
  }

  public void setMeet_date(String meet_date) {
    this.meet_date = meet_date;
  }

  public int getMr_seq() {
    return mr_seq;
  }

  public void setMr_seq(int mr_seq) {
    this.mr_seq = mr_seq;
  }

  public int getMr_user() {
    return mr_user;
  }

  public void setMr_user(int mr_user) {
    this.mr_user = mr_user;
  }

  public Date getMr_date() {
    return mr_date;
  }

  public void setMr_date(Date mr_date) {
    this.mr_date = mr_date;
  }

  public int getMr_room() {
    return mr_room;
  }

  public void setMr_room(int mr_room) {
    this.mr_room = mr_room;
  }

  public int getMr_time() {
    return mr_time;
  }

  public void setMr_time(int mr_time) {
    this.mr_time = mr_time;
  }

  public Date getMr_reg_date() {
    return mr_reg_date;
  }

  public void setMr_reg_date(Date mr_reg_date) {
    this.mr_reg_date = mr_reg_date;
  }


  @Override
  public String toString() {
    return "MeetingDto{" +
            "mr_seq=" + mr_seq +
            ", mr_user=" + mr_user +
            ", mr_date=" + mr_date +
            ", mr_room=" + mr_room +
            ", mr_time=" + mr_time +
            ", mr_reg_date=" + mr_reg_date +
            '}';
  }

}
