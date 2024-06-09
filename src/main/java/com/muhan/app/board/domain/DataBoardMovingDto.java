package com.muhan.app.board.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class DataBoardMovingDto {
  private Integer page_now = 1;
  private Integer page_size = 7;
  private Integer nav_page = 10;

  public DataBoardMovingDto() {
  }


  public DataBoardMovingDto(Integer page_now, Integer page_size, Integer nav_page) {
    this.page_now = page_now;
    this.page_size = page_size;
    this.nav_page = nav_page;
  }


  public String get_query_string() {
    return get_query_string(page_now);
  }
  public String get_query_string(Integer page) {
    return UriComponentsBuilder.newInstance()
            .queryParam("page_now", page_now)
            .queryParam("page_size", page_size)
            .queryParam("nav_page", nav_page)
            .build().toString();
  }
  public Integer getPage_now() {
    return page_now;
  }

  public void setPage_now(Integer page_now) {
    this.page_now = page_now;
  }

  public Integer getPage_size() {
    return page_size;
  }

  public void setPage_size(Integer page_size) {
    this.page_size = page_size;
  }

  public Integer getNav_page() {
    return nav_page;
  }

  public void setNav_page(Integer nav_page) {
    this.nav_page = nav_page;
  }

  @Override
  public String toString() {
    return "DataBoardMovingDto{" +
            "page_now=" + page_now +
            ", page_size=" + page_size +
            ", nav_page=" + nav_page +
            '}';
  }
}
