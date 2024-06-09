package com.muhan.app.board.domain;

import java.util.Date;

public class CommentDto {
  private int com_seq;
  private int com_board_num;
  private String com_comment;
  private int com_commenter;
  private Date com_reg_date;


  private int board_writer;
  private String user_name;


  public CommentDto() {
  }

  public CommentDto(int com_board_num, String com_comment, int com_commenter) {
    this.com_board_num = com_board_num;
    this.com_comment = com_comment;
    this.com_commenter = com_commenter;
  }

  public int getCom_seq() {
    return com_seq;
  }

  public void setCom_seq(int com_seq) {
    this.com_seq = com_seq;
  }

  public int getCom_board_num() {
    return com_board_num;
  }

  public void setCom_board_num(int com_board_num) {
    this.com_board_num = com_board_num;
  }

  public String getCom_comment() {
    return com_comment;
  }

  public void setCom_comment(String com_comment) {
    this.com_comment = com_comment;
  }

  public int getCom_commenter() {
    return com_commenter;
  }

  public void setCom_commenter(int com_commenter) {
    this.com_commenter = com_commenter;
  }

  public Date getCom_reg_date() {
    return com_reg_date;
  }

  public void setCom_reg_date(Date com_reg_date) {
    this.com_reg_date = com_reg_date;
  }

  public int getBoard_writer() {
    return board_writer;
  }

  public void setBoard_writer(int board_writer) {
    this.board_writer = board_writer;
  }

  public String getUser_name() {
    return user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  @Override
  public String toString() {
    return "CommentDto{" +
            "com_seq=" + com_seq +
            ", com_board_num=" + com_board_num +
            ", com_comment='" + com_comment + '\'' +
            ", com_commenter=" + com_commenter +
            ", com_reg_date=" + com_reg_date +
            ", board_writer=" + board_writer+
            ", user_name=" + user_name+
            '}';
  }
}
