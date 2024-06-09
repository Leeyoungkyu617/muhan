package com.muhan.app.board.domain;

import java.util.Date;

public class BoardDto {
  private int board_seq;
  private int board_class;//0-중요, 1-일반, 2-경조사
  private String board_class_name;

  private String board_title;
  private String board_contents;
  private String board_file;
  private String board_ori_file;
  private Date board_date;
  private int board_writer;
  private int board_view_count;

  private String user_name;
  private String user_seqno;
  private String user_num;



  public BoardDto() {
    this(0, "", "");
  }

//  첨부파일 없을 때
  public BoardDto(int board_class, String board_title, String board_contents) {
    this.board_class = board_class;
    this.board_title = board_title;
    this.board_contents = board_contents;
  }

//  첨부파일 있을 때
  public BoardDto(int board_class, String board_title, String board_contents, String board_file, String board_ori_file) {
    this.board_class = board_class;
    this.board_title = board_title;
    this.board_contents = board_contents;
    this.board_file = board_file;
    this.board_ori_file = board_ori_file;
  }

  public int getBoard_seq() {return board_seq;}
  public void setBoard_seq(int board_seq) {this.board_seq = board_seq;}
  public int getBoard_class() {return board_class;}
  public void setBoard_class(int board_class) {this.board_class = board_class;}
  public String getBoard_title() {return board_title;}
  public String getBoard_class_name() {return board_class_name;}
  public void setBoard_class_name(String board_class_name) {this.board_class_name = board_class_name;}

  public void setBoard_title(String board_title) {this.board_title = board_title;}
  public String getBoard_contents() {return board_contents;}
  public void setBoard_contents(String board_contents) {this.board_contents = board_contents;}
  public String getBoard_file() {return board_file;}
  public void setBoard_file(String board_file) {this.board_file = board_file;}
  public String getBoard_ori_file() {return board_ori_file;}
  public void setBoard_ori_file(String board_ori_file) {this.board_ori_file = board_ori_file;}
  public Date getBoard_date() {return board_date;}
  public void setBoard_date(Date board_date) {this.board_date = board_date;}
  public int getBoard_writer() {return board_writer;}
  public void setBoard_writer(int board_writer) {this.board_writer = board_writer;}
  public int getBoard_view_count() {return board_view_count;}
  public void setBoard_view_count(int board_view_count) {this.board_view_count = board_view_count;}
  public String getUser_name() {return user_name;}
  public void setUser_name(String user_name) {this.user_name = user_name;}

  public String getUser_seqno() {return user_seqno;}
  public void setUser_seqno(String user_seqno) {this.user_seqno = user_seqno;}

  public String getUser_num() {
    return user_num;
  }

  public void setUser_num(String user_num) {
    this.user_num = user_num;
  }

  @Override
  public String toString() {
    return "BoardDto{" +
            "board_seq=" + board_seq +
            ", board_class=" + board_class +
            ", board_class_name='" + board_class_name + '\'' +
            ", board_title='" + board_title + '\'' +
            ", board_contents='" + board_contents + '\'' +
            ", board_file='" + board_file + '\'' +
            ", board_ori_file='" + board_ori_file + '\'' +
            ", board_date=" + board_date +
            ", board_writer=" + board_writer +
            ", board_view_count=" + board_view_count +
            ", user_name='" + user_name + '\'' +
            ", user_seqno='" + user_seqno + '\'' +
            ", user_num='" + user_num + '\'' +
            '}';
  }
}
