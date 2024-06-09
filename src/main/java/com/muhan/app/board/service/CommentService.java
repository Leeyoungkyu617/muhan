package com.muhan.app.board.service;

import com.muhan.app.board.domain.CommentDto;

import java.util.List;

public interface CommentService {
  int getCountComments(int com_board_num);

  List<CommentDto> getAllComments(int com_board_num);


  int removeAll();

  int removeOne(Integer com_seq, Integer com_commenter);

  int writeComment(CommentDto commentDto);

  int modifyComment(CommentDto commentDto);
}
