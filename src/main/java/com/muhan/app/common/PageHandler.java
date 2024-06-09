package com.muhan.app.common;

//게시판에 페이징 할 처리 공통사항으로 사용 하려고 만듬
public class PageHandler {
    private int page_now;

    //한 페이지에 보여줄 숫자
    private int page_size;

    private int total_count;
    private int total_page;
//    한 네비 표시 할 표현할 숫자
    private int nav_page;
    private int start_page;
    private int end_page;
    private boolean show_pre_btn;
    private boolean show_next_btn;

    //기본 생성자 생성
    public PageHandler(){}

    public PageHandler(int total_count, int page_now, int page_size, int nav_page){
        this.nav_page = nav_page;
        this.total_count=total_count;
        this.page_now = page_now;
        this.page_size = page_size;


        //표시할 페이지 숫자!
        total_page = (int) Math.ceil(total_count / (double)page_size);

        // 시작 페이지 수 설정
        start_page = (page_now - 1) / nav_page * nav_page + 1;
        // 종료 페이지 수 설정
        end_page = Math.min((start_page + nav_page-1), total_page);

        show_pre_btn = start_page != 1;
        show_next_btn = end_page != total_page;
    }
    @Override
    public String toString() {
        return "PageHandler{" +
                "page_now=" + page_now +
                ", page_size=" + page_size +
                ", total_count=" + total_count +
                ", total_page=" + total_page +
                ", nav_page=" + nav_page +
                ", start_page=" + start_page +
                ", end_page=" + end_page +
                ", show_pre_btn=" + show_pre_btn +
                ", show_next_btn=" + show_next_btn +
                '}';
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public int getNav_page() {
        return nav_page;
    }

    public void setNav_page(int nav_page) {
        this.nav_page = nav_page;
    }

    public int getStart_page() {
        return start_page;
    }

    public void setStart_page(int start_page) {
        this.start_page = start_page;
    }

    public int getEnd_page() {
        return end_page;
    }

    public void setEnd_page(int end_page) {
        this.end_page = end_page;
    }

    public boolean isShow_pre_btn() {
        return show_pre_btn;
    }

    public void setShow_pre_btn(boolean show_pre_btn) {
        this.show_pre_btn = show_pre_btn;
    }

    public boolean isShow_next_btn() {
        return show_next_btn;
    }

    public void setShow_next_btn(boolean show_next_btn) {
        this.show_next_btn = show_next_btn;
    }

    public int getPage_now() {
        return page_now;
    }

    public void setPage_now(int page_now) {
        this.page_now = page_now;
    }


}
