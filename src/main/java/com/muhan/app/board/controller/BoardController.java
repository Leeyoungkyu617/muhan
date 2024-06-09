package com.muhan.app.board.controller;

import com.muhan.app.board.domain.BoardDto;
import com.muhan.app.board.domain.DataBoardMovingDto;
import com.muhan.app.board.service.BoardService;
import com.muhan.app.common.PageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
  @Autowired
  BoardService boardService;

  @GetMapping("/list")//게시판 화면
  public String testList(BoardDto boardDto, Integer page_now, Integer page_size, Integer nav_page, Model m, HttpSession session, HttpServletRequest request){

    if(!loginChk(session)) { // 세션 만료되어 있으면 다시 로그인 페이지로 보낼게요 - 메서드 0531 추가
      session.setAttribute("prevPage", request.getServletPath());
      return "redirect:/login";
    }
    
    int user_num = (int) session.getAttribute("user_num");


System.out.println("리스트 화면 user_num: " + user_num);

    try {
      if(page_now == null) page_now=1;
      if(page_size == null) page_size=7;
      if(nav_page == null || nav_page == 0) nav_page = 10;
      // 총 게시글 수 구하기
      int total_count = boardService.getCountAll();

      PageHandler pageHandler = new PageHandler(total_count,page_now,page_size,nav_page);

      Map map = new HashMap();
      map.put("offset", (page_now - 1) * page_size); // startPage
      map.put("page_size", page_size);

      List<BoardDto> list = boardService.getRecent(map);

      for(int i=0;i<list.size();i++){
        if(list.get(i).getBoard_class()==0){
          list.get(i).setBoard_class_name("중요");
        } else if (list.get(i).getBoard_class()==1) {
          list.get(i).setBoard_class_name("일반");
        }else {
          list.get(i).setBoard_class_name("경조사");
        }
      }
      m.addAttribute("list", list);
      m.addAttribute("ph", pageHandler);

      List<BoardDto> listNotice = boardService.getTopNotice(0);

      for(int i=0;i<listNotice.size();i++){
        listNotice.get(i).setBoard_class_name("중요");
      }

      m.addAttribute("listNotice", listNotice);
      return "/board/list";
    } catch (Exception e) {
      System.out.println("에러 발생");
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/read")//게시판 상세 페이지
  public String testPostRead(Integer board_seq, Integer page_now, Integer page_size, Integer nav_page, Model m, HttpSession session){
//    session.setAttribute("user_num", 10000);

    System.out.println("게시글 상세 화면 user_num: " + session.getAttribute("user_num"));
//    session.setAttribute("user_dept",dto.getUser_detp);
//    session.getAttribute("user_dept");
    // 유저 정보를 가져올수있는 dto
    //dto -> getUser_dept
    BoardDto boardDto = boardService.read(board_seq);
    if(boardDto.getBoard_class() == 0) {
      boardDto.setBoard_class_name("중요");
    } else if (boardDto.getBoard_class()==1) {
      boardDto.setBoard_class_name("일반");
    } else {
      boardDto.setBoard_class_name("경조사");
    }
    if(boardDto!= null){

      m.addAttribute("boardDto", boardDto);

      m.addAttribute("page_now", page_now);
      m.addAttribute("page_size", page_size);
      m.addAttribute("nav_page", nav_page);

      return "/board/post";
    }
    else {
      return "";
    }
  }

  @GetMapping("/write")
  public String testLoadWrite(Model m){
    
    /* 글쓰기 화면 불러오기 - post는 모델의 mode 가 new 인지 아닌지로 글쓰기인지 일반 게시글 조회인지 파악한다. */
    m.addAttribute("mode", "new");
    return "/board/post";
  }


  private static final String F_PATH = "C:/Users/user/Desktop/KH/muhan/job/muhan/src/main/webapp/resources/img/";
  @PostMapping("/write")
  public String testWrite(@RequestParam(value="f_file", required = false) MultipartFile mf, BoardDto boardDto, HttpSession session){
//    session.setAttribute("user_num", 10000);
System.out.println("글쓰기 화면 user_num: " + session.getAttribute("user_num"));
System.out.println(boardDto);

    if (mf != null && !mf.isEmpty()) {
      String originalFileName = mf.getOriginalFilename();
      String safeFile = F_PATH + System.currentTimeMillis() + originalFileName;
      System.out.println("originalFileName: " + originalFileName);
      System.out.println("safeFile: " + safeFile);

      try {
        mf.transferTo(new File(safeFile));// 물리적 파일 저장하는 단계

        safeFile = System.currentTimeMillis() + originalFileName;
        boardDto.setBoard_file(safeFile);
        boardDto.setBoard_ori_file(originalFileName);
      } catch (Exception e) {
        e.printStackTrace();
        // Optional: Add custom error handling or logging here
      }
    } else {
      System.out.println("No file uploaded");
    }

    boardService.write(boardDto);
    return "redirect:/board/list";
  }

  @RequestMapping(value="/modify" , method = {RequestMethod.GET, RequestMethod.POST})
  public String testModify(@RequestParam(value="f_file", required = false) MultipartFile mf, BoardDto boardDto, DataBoardMovingDto movingDto, Model m, HttpSession session){
System.out.println("수정 시 boardDto: "+ boardDto);
System.out.println("수정 시 movingDto: "+ movingDto);

    if (mf != null && !mf.isEmpty()) {
      String originalFileName = mf.getOriginalFilename();
      String safeFile = F_PATH + System.currentTimeMillis() + originalFileName;
      System.out.println("originalFileName: " + originalFileName);
      System.out.println("safeFile: " + safeFile);

      try {
        mf.transferTo(new File(safeFile));// 물리적 파일 저장하는 단계

        safeFile = System.currentTimeMillis() + originalFileName;
        boardDto.setBoard_file(safeFile);
        boardDto.setBoard_ori_file(originalFileName);
      } catch (Exception e) {
        e.printStackTrace();
        // Optional: Add custom error handling or logging here
      }
    } else {
      System.out.println("No file uploaded");
    }
    boardDto.setBoard_seq(boardDto.getBoard_seq());
    boardService.modify(boardDto);

    return "redirect:/board/list"+movingDto.get_query_string();
  }


  @RequestMapping("/remove")
  public String testRemove(Integer board_seq, Integer page_now, Integer page_size, Integer nav_page, HttpSession session){
//    session.setAttribute("user_num", 10000);
    Integer user_num = (Integer)session.getAttribute("user_num");
System.out.println("삭제 시 가져올 값"+ board_seq + ", " + user_num);
    int rowCount = boardService.removeOne(board_seq, user_num);
    if(rowCount == 1) {
      System.out.println("삭제성공");
    }
    else {
      System.out.println("삭제실패");
    }
    return "redirect:/board/list";
  }


  private boolean loginChk(HttpSession session) {
    return session.getAttribute("user_num") != null;
    // 세션에 user_num 이 있으면 결과는 true, 없으면 false 나옴
  }
}
