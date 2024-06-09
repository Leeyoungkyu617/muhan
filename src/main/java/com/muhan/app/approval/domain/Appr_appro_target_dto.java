package com.muhan.app.approval.domain;

public class Appr_appro_target_dto {
    private String level_name;
    private String user_name;
    private int user_seqno;

    public String getDept_name() {
        return level_name;
    }

    public void setDept_name(String level_name) {
        this.level_name = level_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_seqno() {
        return user_seqno;
    }

    public void setUser_seqno(int user_seqno) {
        this.user_seqno = user_seqno;
    }
}
