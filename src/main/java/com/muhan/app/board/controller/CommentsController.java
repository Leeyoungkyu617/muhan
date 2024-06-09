package com.muhan.app.board.controller;


import com.muhan.app.board.domain.CommentDto;
import com.muhan.app.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentsController {
  @Autowired
  CommentService commentService;

  @GetMapping("/comments")
  @ResponseBody
  public ResponseEntity<List<CommentDto>> commentList(Integer board_seq, HttpSession session) {
//    session.setAttribute("user_num", 10000);//관리자 세션 생성하기?
    Integer user_num = (Integer) session.getAttribute("user_num");
    try {

      List<CommentDto> list = commentService.getAllComments(board_seq);
      int commentCount = commentService.getCountComments(board_seq);
      System.out.println("개수: " + list.size());
      System.out.println("list:" + list);

      return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/comments")
  public ResponseEntity<String> writeComment(@RequestBody CommentDto commentDto, Integer board_seq, HttpSession session) {
//    session.setAttribute("user_num", 10000);//관리자 세션 생성하기(로그인한 상태)
    Integer user_num = (Integer) session.getAttribute("user_num");
System.out.println("코멘트란1: " + commentDto);
    try {
      Integer commenter = (Integer) session.getAttribute("user_num");
      commentDto.setCom_commenter(commenter);
      commentDto.setCom_board_num(commentDto.getCom_board_num());
System.out.println("코멘ㄷ트란2: " + commentDto);
      if(commentService.writeComment(commentDto) !=1) {
        throw new Exception("실패");
      }
      System.out.println("commentDto: "+ commentDto);
      HttpHeaders responseHeader = new HttpHeaders();
      responseHeader.add("content-type", "text/html; charset=UTF-8");

      return new ResponseEntity<String>("댓글 등록 성공", responseHeader, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }
  }


  @PatchMapping("/comments/{com_seq}")
  public ResponseEntity<String> modifyComment(@PathVariable Integer com_seq, @RequestBody CommentDto commentDto, HttpSession session){
//    session.setAttribute("user_num", 10000);//관리자 세션 생성하기?
    Integer user_num = (Integer) session.getAttribute("user_num");

    try {
      Integer commenter = (Integer) session.getAttribute("user_num");
      commentDto.setCom_commenter(commenter);
      commentDto.setCom_seq(com_seq);

      if(commentService.modifyComment(commentDto) != 1) {
        throw new Exception("실패");
      }

      HttpHeaders responseHeader = new HttpHeaders();
      responseHeader.add("content-type", "text/html;charset=UTF-8");
      return  new ResponseEntity<String>("댓글 수정 성공", responseHeader, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }
  }


  @DeleteMapping("/comments/{com_seq}")
  public ResponseEntity<String> removeComment(@PathVariable Integer com_seq, CommentDto commentDto, HttpSession session){
//    session.setAttribute("user_num", 10000);//관리자 세션 생성하기?
    Integer user_num = (Integer) session.getAttribute("user_num");
    try {
      Integer com_commenter = (Integer) session.getAttribute("user_num");
System.out.println("여기 들어가나?"+ com_seq+", "+ com_commenter + ", "+(com_commenter+1));

System.out.println("댓삭 확인용1" + com_seq+", "+ com_commenter);
      if(commentService.removeOne(com_seq, com_commenter) != 1) {
        System.out.println("단계 111111");
        throw new Exception("삭제 실패");
      }
      HttpHeaders responseHeader = new HttpHeaders();
      responseHeader.add("content-type", "text/html;charset=UTF-8");
      return new ResponseEntity<String>("댓글 삭제 성공", responseHeader, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }
  }
}
