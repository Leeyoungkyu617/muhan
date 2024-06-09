package com.muhan.app.user.domain;

public class UserLoginDto {
    private Integer user_num;
    private String user_pw;
    private boolean rememberId;

    public Integer getUser_num() { return user_num; }

    public void setUser_num(Integer user_num) { this.user_num = user_num; }

    public String getUser_pw() { return user_pw; }

    public void setUser_pw(String user_pw) { this.user_pw = user_pw; }

    public boolean isRememberId() { return rememberId; }

    public void setRememberId(boolean rememberId) { this.rememberId = rememberId; }

    @Override
    public String toString() {
        return "UserLogin{" +
                "user_num='" + user_num + '\'' +
                ", user_pw='" + user_pw + '\'' +
                ", rememberId=" + rememberId +
                '}';
    }
}
