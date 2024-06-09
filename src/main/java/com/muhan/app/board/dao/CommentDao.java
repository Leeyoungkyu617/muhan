package com.muhan.app.board.dao;

import com.muhan.app.board.domain.CommentDto;

import java.util.List;

public interface CommentDao {
  int countComments(int com_board_num);

  List<CommentDto> selectCommentsAll(int com_board_num);


  int deleteAll();

  int deleteOne(Integer com_seq, Integer com_commenter);

  int insert(CommentDto commentDto);

  int update(CommentDto commentDto);
}
