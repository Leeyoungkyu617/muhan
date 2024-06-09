package com.muhan.app.schedule.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ScheduleDto {
    private Integer sche_seq;
    private Integer sche_writer;
    private String sche_start_time;
    private String sche_end_time;
    private String sche_title;
    private String sche_contents;
    private String sche_color;
    private boolean sche_del;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sche_reg_date;

    public ScheduleDto(){
    }

    public ScheduleDto(String sche_start_time , String sche_end_time , String sche_title , String sche_contents , String sche_color) {
        this.sche_start_time = sche_start_time;
        this.sche_end_time = sche_end_time;
        this.sche_title = sche_title;
        this.sche_contents = sche_contents;
        this.sche_color = sche_color;
    }


    public ScheduleDto(Integer sche_seq, String sche_start_time , String sche_end_time , String sche_title , String sche_contents , String sche_color) {
        this.sche_seq = sche_seq;
        this.sche_start_time = sche_start_time;
        this.sche_end_time = sche_end_time;
        this.sche_title = sche_title;
        this.sche_contents = sche_contents;
        this.sche_color = sche_color;
    }

    public Integer getSche_seq() {return sche_seq;}

    public void setSche_seq(Integer sche_seq) {this.sche_seq = sche_seq;}

    public Integer getSche_writer() {return sche_writer;}

    public void setSche_writer(Integer sche_writer) {this.sche_writer = sche_writer;}

    public String getSche_start_time() {return sche_start_time;}

    public void setSche_start_time(String sche_start_time) {this.sche_start_time = sche_start_time;}

    public String getSche_end_time() {return sche_end_time;}

    public void setSche_end_time(String sche_end_time) {this.sche_end_time = sche_end_time;}

    public String getSche_title() {return sche_title;}

    public void setSche_title(String sche_title) {this.sche_title = sche_title;}

    public String getSche_contents() {return sche_contents;}

    public void setSche_contents(String sche_contents) {this.sche_contents = sche_contents;}

    public String getSche_color() {return sche_color;}

    public void setSche_color(String sche_color) {this.sche_color = sche_color;}

    public Date getSche_reg_date() {return sche_reg_date;}

    public void setSche_reg_date(Date sche_reg_date) {this.sche_reg_date = sche_reg_date;}

    public boolean isSche_del() {return sche_del;}

    public void setSche_del(boolean sche_del) {this.sche_del = sche_del;}

    @Override
    public String toString() {
        return "ScheduleDto{" +
                "sche_seq=" + sche_seq +
                ", sche_writer='" + sche_writer + '\'' +
                ", sche_start_time='" + sche_start_time + '\'' +
                ", sche_end_time='" + sche_end_time + '\'' +
                ", sche_title='" + sche_title + '\'' +
                ", sche_contents='" + sche_contents + '\'' +
                ", sche_color='" + sche_color + '\'' +
                ", sche_reg_date=" + sche_reg_date +
                '}';
    }
}
