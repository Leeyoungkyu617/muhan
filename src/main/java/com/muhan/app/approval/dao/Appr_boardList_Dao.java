package com.muhan.app.approval.dao;

import com.muhan.app.approval.domain.Appr_appro_target_dto;
import com.muhan.app.approval.domain.Appr_boardList_Dto;

import java.util.List;
import java.util.Map;

public interface Appr_boardList_Dao {
    public int get_all_count(Map map);
    public List<Appr_boardList_Dto> selectPage(Map map);

    public List<Appr_appro_target_dto> select_target();

    public int get_approval_count(Integer apr_upper);

    public int write(Appr_boardList_Dto dto);

    public Appr_boardList_Dto read(int apr_seq);

    public int modify(Appr_boardList_Dto dto);
    public int remove(int apr_seq);
    public int accept_is_not(Map map);
}
