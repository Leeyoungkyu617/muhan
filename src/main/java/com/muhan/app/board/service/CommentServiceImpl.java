package com.muhan.app.board.service;

import com.muhan.app.board.dao.CommentDao;
import com.muhan.app.board.domain.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
  @Autowired
  CommentDao commentDao;

  @Override
  public int getCountComments(int board_seq){
    return commentDao.countComments(board_seq);
  }

  @Override
  public List<CommentDto> getAllComments(int com_board_num){
    return commentDao.selectCommentsAll(com_board_num);
  }


  @Override
  public int removeAll(){
    return commentDao.deleteAll();
  }

  @Override
  public int removeOne(Integer com_seq, Integer com_commenter){
    System.out.println("단계 22222222");
    return commentDao.deleteOne(com_seq, com_commenter);
  }

  @Override
  public int writeComment(CommentDto commentDto){
    return commentDao.insert(commentDto);
  }

  @Override
  public int modifyComment(CommentDto commentDto){
    return commentDao.update(commentDto);
  }
}
