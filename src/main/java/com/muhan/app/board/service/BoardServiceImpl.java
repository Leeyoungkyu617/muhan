package com.muhan.app.board.service;

import com.muhan.app.board.dao.BoardDao;
import com.muhan.app.board.domain.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
  @Autowired
  BoardDao boardDao;

  @Override
  public int getCountAll(){
    return boardDao.countAll();
  }

  @Override
  public BoardDto read(int board_seq) {
    BoardDto readRow = boardDao.select(board_seq);
    boardDao.updateViewCount(board_seq);
    return readRow;
  }

  @Override
  public List<BoardDto> getListAll(){
    return boardDao.selectAll();
  }

  @Override
  public List<BoardDto> getPage(Map map){
    return boardDao.selectPage(map);
  }

  @Override
  public List<BoardDto> getTopNotice(int board_class){
    return boardDao.selectClass(board_class);
  }

  @Override
  public List<BoardDto> getRecent(Map map){
    return boardDao.selectRecent(map);
  }

  @Override
  public int removeAll(){
    return boardDao.deleteAll();
  }

  @Override
  public int removeOne(int board_seq, int user_num){
    Map map = new HashMap();
    map.put("board_seq", board_seq);
    map.put("user_num", user_num);
    return boardDao.deleteEach(map);
  }

  @Override
  public int write(BoardDto boardDto){
    return boardDao.insert(boardDto);
  }

  @Override
  public int modify(BoardDto boardDto) {
    return boardDao.update(boardDto);
  }
}
