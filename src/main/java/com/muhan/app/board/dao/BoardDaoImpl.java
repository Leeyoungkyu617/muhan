package com.muhan.app.board.dao;

import com.muhan.app.board.domain.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardDaoImpl implements BoardDao {
  @Autowired
  private SqlSession session;

  private String namespace = "com.muhan.app.board.dao.BoardMapper.";

  @Override
  public int countAll(){
    return session.selectOne(namespace+"countAll");
  }

  @Override
  public BoardDto select(int board_seq){
    return session.selectOne(namespace+"select", board_seq);
  }

  @Override
  public List<BoardDto> selectAll(){
    return session.selectList(namespace+"selectAll");
  }

  @Override
  public List<BoardDto> selectPage(Map map){
    return session.selectList(namespace+"selectPage", map);
  }

  @Override
  public List<BoardDto> selectClass(int board_class){
    return session.selectList(namespace + "selectClass");
  }

  @Override
  public List<BoardDto> selectRecent(Map map){
    return session.selectList(namespace+"selectRecent", map);
  }

  @Override
  public int deleteAll(){
    return session.delete(namespace+"deleteAll");
  }

  @Override
  public int deleteEach(Map map){
    return session.delete(namespace+"deleteEach", map);
  }

  @Override
  public int insert(BoardDto boardDto){
    return session.insert(namespace+"insert", boardDto);
  }

  @Override
  public int update(BoardDto boardDto){
    return session.update(namespace+"update", boardDto);
  }

  @Override
  public int updateViewCount(int board_seq){
    return session.update(namespace+"updateViewCount", board_seq);
  }
}