package com.muhan.app.board.dao;

import com.muhan.app.board.domain.CommentDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentDaoImpl implements CommentDao {
  @Autowired
  private SqlSession sqlSession;

  private String namespace = "com.muhan.app.board.dao.commentsMapper.";

  @Override
  public int countComments(int board_seq){
    return sqlSession.selectOne(namespace+"countComments", board_seq);
  }
  @Override
  public List<CommentDto> selectCommentsAll(int board_seq){
    return sqlSession.selectList(namespace+"selectCommentsAll", board_seq);
  }

  @Override
  public int deleteAll(){
    return sqlSession.delete(namespace+"deleteAll");
  }
  @Override
  public int deleteOne(Integer com_seq, Integer com_commenter){
    System.out.println("단계 33333");
    Map map = new HashMap();
    map.put("com_seq", com_seq);
    map.put("com_commenter", com_commenter);
    System.out.println("단계 444444");
    return sqlSession.delete(namespace+"deleteOne", map);
  }
  @Override
  public int insert(CommentDto commentDto){
    return sqlSession.insert(namespace+"insert", commentDto);
  }
  @Override
  public int update(CommentDto commentDto){
    return sqlSession.update(namespace+"update", commentDto);
  }
}
