package com.muhan.app.event_photo.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EventPhotoDto {
    private Integer photo_seq;
    private Integer photo_uploader;
    private String photo_new_name;
    private String photo_title;
    private String user_name;


    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date photo_reg_date;

    public EventPhotoDto() {
    }

    public EventPhotoDto(int photo_uploader, String photo_new_name, String photo_title) {
        this.photo_uploader = photo_uploader;
        this.photo_new_name = photo_new_name;
        this.photo_title = photo_title;
    }

    public Integer getPhoto_seq() {
        return photo_seq;
    }

    public void setPhoto_seq(Integer photo_seq) {
        this.photo_seq = photo_seq;
    }

    public Integer getPhoto_uploader() {
        return photo_uploader;
    }

    public void setPhoto_uploader(Integer photo_uploader) {
        this.photo_uploader = photo_uploader;
    }

    public String getPhoto_new_name() {
        return photo_new_name;
    }

    public void setPhoto_new_name(String photo_new_name) {
        this.photo_new_name = photo_new_name;
    }

    public String getPhoto_title() {
        return photo_title;
    }

    public void setPhoto_title(String photo_title) {
        this.photo_title = photo_title;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Date getPhoto_reg_date() {
        return photo_reg_date;
    }

    public void setPhoto_reg_date(Date photo_reg_date) {
        this.photo_reg_date = photo_reg_date;
    }

    @Override
    public String toString() {
        return "eventPhotoDto{" +
                "photo_seq=" + photo_seq +
                ", photo_uploader=" + photo_uploader +
                ", photo_new_name='" + photo_new_name + '\'' +
                ", photo_title='" + photo_title + '\'' +
                ", photo_reg_date=" + photo_reg_date +
                '}';
    }
}


