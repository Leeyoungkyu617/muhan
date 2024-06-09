package com.muhan.app.approval.domain;

import org.checkerframework.checker.units.qual.C;

import java.util.Date;


public class Appr_boardList_Dto {
    private Integer apr_seq;
    private int apr_writer;
    private int apr_upper;
    private String apr_title;
    private String apr_contents;
    private String apr_attach;
    private String apr_ori_attach;
    private Date apr_u_time;
    private Date apr_p_time;
    private int apr_chk;
    private Date apr_reg_date;
    //상신자 이름
    private String user_name;
    // 결재담당자 이름
    private String upper_user_name;
    //상신자 직급
    private String upper_level_name;

    // 결재자 이름
    private String writer_name;
    // 결재자 레벨
    private int write_user_level;



    //수정
    public Appr_boardList_Dto(String upper_user_name, String upper_level_name, String writer_name, int write_user_level, String user_name, Integer apr_seq, int apr_writer, int apr_upper, String apr_title, String apr_contents, String apr_attach, String apr_ori_attach, Date apr_u_time, Date apr_p_time, int apr_chk, Date apr_reg_date) {
        System.out.println("*************넘어올때 이거타야하는대***********");
        this.apr_seq = apr_seq;
        this.apr_writer = apr_writer;
        this.apr_upper = apr_upper;
        this.apr_title = apr_title;
        this.apr_contents = apr_contents;
        this.apr_attach = apr_attach;
        this.apr_ori_attach = apr_ori_attach;
        this.apr_u_time = apr_u_time;
        this.apr_p_time = apr_p_time;
        this.apr_chk = apr_chk;
        this.apr_reg_date = apr_reg_date;
        this.user_name = user_name;
        this.upper_user_name = upper_user_name;
        this.upper_level_name = upper_level_name;
        this.writer_name = writer_name;
        this.write_user_level = write_user_level;
    }

    //   리스트시 가져올 dto
    public Appr_boardList_Dto(int apr_seq, int apr_writer, int apr_upper, String apr_title, String apr_contents, String apr_attach, String apr_ori_attach, Date apr_u_time, Date apr_p_time, int apr_chk, Date apr_reg_date, String user_name) {
        System.out.println("***************list 가져올 dto*************");
        this.apr_seq = apr_seq;
        this.apr_writer = apr_writer;
        this.apr_upper = apr_upper;
        this.apr_title = apr_title;
        this.apr_contents = apr_contents;
        this.apr_attach = apr_attach;
        this.apr_ori_attach = apr_ori_attach;
        this.apr_u_time = apr_u_time;
        this.apr_p_time = apr_p_time;
        this.apr_chk = apr_chk;
        this.apr_reg_date = apr_reg_date;
        this.user_name = user_name;
    }

//    기본 생성자
//    public Appr_boardList_Dto(){
//        System.out.println("*****************기본생성자*************");
//    }



//   수정시 가져올 데이터

    public Appr_boardList_Dto(Integer apr_seq, int apr_upper, String apr_title, int apr_writer, String apr_contents, String apr_attach, String apr_ori_attach) {
        System.out.println("******수정 시 dto***********");
        this.apr_seq = apr_seq;
        this.apr_writer = apr_writer;
        this.apr_upper = apr_upper;
        this.apr_title = apr_title;
        this.apr_contents = apr_contents;
        this.apr_attach = apr_attach;
        this.apr_ori_attach = apr_ori_attach;
    }

    // write 할때 필요한 생성자
    public Appr_boardList_Dto(Integer apr_seq, int apr_upper, String apr_title, int apr_writer, String apr_contents) {
        System.out.println("****************write 생성자!!***********");
        this.apr_seq = apr_seq;
        this.apr_writer = apr_writer;
        this.apr_upper = apr_upper;
        this.apr_title = apr_title;
        this.apr_contents = apr_contents;
    }

    public String getUpper_user_name() {
        return upper_user_name;
    }

    public void setUpper_user_name(String upper_user_name) {
        this.upper_user_name = upper_user_name;
    }

    public String getWriter_name() {
        return writer_name;
    }

    public void setWriter_name(String writer_name) {
        this.writer_name = writer_name;
    }

    public int getWrite_user_level() {
        return write_user_level;
    }

    public void setWrite_user_level(int write_user_level) {
        this.write_user_level = write_user_level;
    }

    @Override
    public String toString() {
        return "Appr_boardList_Dto{" +
                "apr_seq=" + apr_seq +
                ", apr_writer=" + apr_writer +
                ", apr_upper=" + apr_upper +
                ", apr_title='" + apr_title + '\'' +
                ", apr_contents='" + apr_contents + '\'' +
                ", apr_attach='" + apr_attach + '\'' +
                ", apr_ori_attach='" + apr_ori_attach + '\'' +
                ", apr_u_time=" + apr_u_time +
                ", apr_p_time=" + apr_p_time +
                ", apr_chk=" + apr_chk +
                ", apr_reg_date=" + apr_reg_date +
                '}';
    }

    public void setApr_seq(Integer apr_seq) {
        this.apr_seq = apr_seq;
    }

    public String getUpper_level_name() {
        return upper_level_name;
    }

    public void setUpper_level_name(String upper_level_name) {
        this.upper_level_name = upper_level_name;
    }

    public int getApr_writer() {
        return apr_writer;
    }

    public void setApr_writer(int apr_writer) {
        this.apr_writer = apr_writer;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getApr_seq() {
        return apr_seq;
    }

    public void setApr_seq(int apr_seq) {
        this.apr_seq = apr_seq;
    }

    public int getWriter() {
        return apr_writer;
    }

    public void setWriter(int apr_writer) {
        this.apr_writer = apr_writer;
    }

    public int getApr_upper() {
        return apr_upper;
    }

    public void setApr_upper(int apr_upper) {
        this.apr_upper = apr_upper;
    }

    public String getApr_title() {
        return apr_title;
    }

    public void setApr_title(String apr_title) {
        this.apr_title = apr_title;
    }

    public String getApr_contents() {
        return apr_contents;
    }

    public void setApr_contents(String apr_contents) {
        this.apr_contents = apr_contents;
    }

    public String getApr_attach() {
        return apr_attach;
    }

    public void setApr_attach(String apr_attach) {
        this.apr_attach = apr_attach;
    }

    public String getApr_ori_attach() {
        return apr_ori_attach;
    }

    public void setApr_ori_attach(String apr_ori_attach) {
        this.apr_ori_attach = apr_ori_attach;
    }

    public Date getApr_u_time() {
        return apr_u_time;
    }

    public void setApr_u_time(Date apr_u_time) {
        this.apr_u_time = apr_u_time;
    }

    public Date getApr_p_time() {
        return apr_p_time;
    }

    public void setApr_p_time(Date apr_p_time) {
        this.apr_p_time = apr_p_time;
    }

    public int getApr_chk() {
        return apr_chk;
    }

    public void setApr_chk(int apr_chk) {
        this.apr_chk = apr_chk;
    }

    public Date getApr_reg_date() {
        return apr_reg_date;
    }

    public void setApr_reg_date(Date apr_reg_date) {
        this.apr_reg_date = apr_reg_date;
    }


}
