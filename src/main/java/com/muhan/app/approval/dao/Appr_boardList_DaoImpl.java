package com.muhan.app.approval.dao;

import com.muhan.app.approval.domain.Appr_appro_target_dto;
import com.muhan.app.approval.domain.Appr_boardList_Dto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class Appr_boardList_DaoImpl implements Appr_boardList_Dao {
    @Autowired
    SqlSession session;

    String namespace = "com.muhan.app.approval.dao.";



    @Override
    public int get_all_count(Map sql_map) {
        System.out.println("카운트 가져오기");
        return session.selectOne(namespace+"get_all_count",sql_map);
    }

    @Override
    public List<Appr_boardList_Dto> selectPage(Map map) {
        System.out.println("전체 페이지 가져오기");
        return session.selectList(namespace+"select_page",map);
    }

    @Override
    public List<Appr_appro_target_dto> select_target() {
        return session.selectList(namespace+"select_target_approval");
    }

    @Override
    public int get_approval_count(Integer apr_upper) {
        return session.selectOne(namespace+"get_approval_count",apr_upper);
    }

    @Override
    public int write(Appr_boardList_Dto dto) {
        return session.insert(namespace+"write", dto);
    }

    @Override
    public Appr_boardList_Dto read(int apr_seq) {
        return session.selectOne(namespace+"read", apr_seq);
    }

    @Override
    public int modify(Appr_boardList_Dto dto) {
        return session.update(namespace+"modify",dto);
    }

    @Override
    public int remove(int apr_seq) {
        return session.delete(namespace+"delete",apr_seq);
    }

    @Override
    public int accept_is_not(Map map) {
        return session.update(namespace+"accept_is_not",map);
    }

}
