package com.muhan.app.attend.domain;

import java.util.Date;

public class AttendDto {
  private int att_seq;
  private int att_user;
  private Date att_start;
  private Date att_end;
  private int att_chk;
  private Date att_reg_date;


  private String att_chk_name;
//  private Date att_work_time;
  private int user_seqno;
  private int user_num;
  private String user_name;


  public AttendDto() {
  }

  public AttendDto(Date att_start, Date att_end, int att_user) {
    this.att_start = att_start;
    this.att_end = att_end;
    this.att_user = att_user;

    /*att_work_time += (att_end - att_start);*/
  }

  public int getAtt_seq() {
    return att_seq;
  }

  public void setAtt_seq(int att_seq) {
    this.att_seq = att_seq;
  }

  public int getAtt_user() {
    return att_user;
  }

  public void setAtt_user(int att_user) {
    this.att_user = att_user;
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

  public int getAtt_chk() {
    return att_chk;
  }

  public void setAtt_chk(int att_chk) {
    this.att_chk = att_chk;
  }

  public String getAtt_chk_name() {
    return att_chk_name;
  }

  public void setAtt_chk_name(String att_chk_name) {
    this.att_chk_name = att_chk_name;
  }

  public Date getAtt_reg_date() {
    return att_reg_date;
  }

  public void setAtt_reg_date(Date att_reg_date) {
    this.att_reg_date = att_reg_date;
  }

  @Override
  public String toString() {
    return "AttendDto{" +
            "att_seq=" + att_seq +
            ", att_user=" + att_user +
            ", att_start=" + att_start +
            ", att_end=" + att_end +
            ", att_chk=" + att_chk +
            ", att_reg_date=" + att_reg_date +
            ", user_seqno=" + user_seqno +
            ", user_num=" + user_num +
            ", user_name='" + user_name + '\'' +
            '}';
  }
}
