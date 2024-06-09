package com.muhan.app.approval.service;

import com.muhan.app.approval.dao.Appr_boardList_Dao;
import com.muhan.app.approval.dao.Appr_boardList_DaoImpl;
import com.muhan.app.approval.domain.Appr_appro_target_dto;
import com.muhan.app.approval.domain.Appr_boardList_Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Appr_boardList_Service {

    @Autowired
    Appr_boardList_Dao board_dao;

    public Appr_boardList_Dto read(int apr_seq){
        return board_dao.read(apr_seq);
    }

    public int get_all_count(Map sql_map){
        return board_dao.get_all_count(sql_map);
    }


    public List<Appr_boardList_Dto> get_page(Map map){
        return board_dao.selectPage(map);
    }

    public List<Appr_appro_target_dto> select_target(){return  board_dao.select_target();}

    public int get_approval_count(Integer apr_upper){
        return board_dao.get_approval_count(apr_upper);
    }
    public int write(Appr_boardList_Dto dto){
        return board_dao.write(dto);
    }
    public int modify(Appr_boardList_Dto dto){return board_dao.modify(dto);}
    public int remove(int apr_seq){return board_dao.remove(apr_seq);}
    public int accept_is_not(Map map){return board_dao.accept_is_not(map);}
}
