package com.muhan.app.board.service;

import com.muhan.app.board.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardService {
  int getCountAll();

  BoardDto read(int board_seq);

  List<BoardDto> getListAll();

  List<BoardDto> getPage(Map map);

  List<BoardDto> getTopNotice(int board_class);

  List<BoardDto> getRecent(Map map);

  int removeAll();

  int removeOne(int board_seq, int user_num);

  int write(BoardDto boardDto);

  int modify(BoardDto boardDto);
}
