package com.muhan.app.board.dao;

import com.muhan.app.board.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardDao {
  int countAll();

  BoardDto select(int board_seq);

  List<BoardDto> selectAll();

  List<BoardDto> selectPage(Map map);

  List<BoardDto> selectClass(int board_class);

  List<BoardDto> selectRecent(Map map);

  int deleteAll();

  int deleteEach(Map map);

  int insert(BoardDto boardDto);

  int update(BoardDto boardDto);

  int updateViewCount(int board_seq);
}
