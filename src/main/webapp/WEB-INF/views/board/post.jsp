<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="userId" value="${ pageContext.request.getSession(false).getAttribute('user_num')==null ? '':pageContext.request.getSession(false).getAttribute('user_num') }" />
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value='/css/board/boardPost.css'/> ">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<jsp:include page="../common/header.jsp" flush="false"/>
<main>
    <jsp:include page="../common/menu_left.jsp" flush="false"/>

    <div class="post_container">
        <%-- 필요한 것: 글로 들어오기 전 주소(url), 글번호, 제목, 글쓴이, 내용, 작성날짜, 코멘트(별도의 파일로 생성해서 ajax로 가져오기)
        --%>
        <h2 class="post_mode">공지사항 ${mode=="new"?"등록":""}</h2>

        <form action="" method="post" class="post_form">
            <div class="post_frm_head">
                <span>제목</span>
                <c:choose>
                    <c:when test="${mode!='new'}">
                        <input type="text" class="post_title" name="board_title" value="${boardDto.board_title}" readonly>
                    </c:when>
                    <c:otherwise><%--mode == new => 삽입 --%>
                        <input type="text" class="post_title_input" name="board_title" value="${boardDto.board_title}" readonly>
                        <select name="board_class" class="post_class_sel">
                            <option value="0">중요공지</option>
                            <option value="1">일반공지</option>
                            <option value="2">경조사</option>
                        </select>
                    </c:otherwise>
                </c:choose>
            </div>
            <c:if test="${mode != 'new'}">
                <ul class="post_tag_box">
                    <li>
                            ${boardDto.board_class_name}
                    </li>
                    <li>
                            ${boardDto.user_name}
                    </li>
                    <li>
                        <c:set var="today" value="<%= new java.util.Date()%>"/>
                        <c:set var="date">
                            <fmt:formatDate value="${today}" pattern="yyyy-MM-dd" type="date"/>
                        </c:set>
                        <c:set var="date2">
                            <fmt:formatDate value="${boardDto.board_date}" pattern="yyyy-MM-dd" type="date"/>
                        </c:set>
                        <c:choose>
                            <c:when test="${date==date2}">
                                <fmt:formatDate value="${boardDto.board_date}" pattern="HH:mm" type="date"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:formatDate value="${boardDto.board_date}" pattern="yyyy-MM-dd" type="date"/>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </c:if>
            <div class="post_frm_body">
                <textarea name="board_contents" id="post_content" ${mode == 'new'?"autofocus":"readonly"}><c:if test="${mode == 'new'}"></c:if><c:if test="${mode != 'new'}">${boardDto.board_contents}</c:if></textarea>
                <c:choose>
                    <c:when test="${mode=='new'}">
                        <input type="file" name="f_file" multiple="multiple">
                    </c:when>
                    <c:otherwise>
                        <%--<c:if test="${userId == 10000 or userId == 10001 and mode != 'new'}">
                            <input type="file" name="f_file" multiple="multiple" value="${boardDto.board_ori_file}">
                        </c:if>--%>
                        <c:if test="${not empty boardDto.board_file}">
                            <div class="post_file_attached">${boardDto.board_ori_file}</div>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="post_frm_footer">
                <c:if test="${mode == 'new'}">
                    <button class="post_upload_btn">등록</button>
                </c:if>
                <c:if test="${mode != 'new' and userId == 10000}">
                    <button type="button" class="post_modify_btn">수정</button>
                    <button type="button" class="post_delete_btn">삭제</button>
                </c:if>
                <div class="post_return_btn">돌아가기</div>
            </div>
        </form>
        <c:if test="${mode != 'new'}">
            <div class="post_cmt_area">
                    <%-- 댓글 영역 --%>
                <jsp:include page="comment.jsp" flush="false"/>
            </div>
        </c:if>
    </div>
    <script>
        $(document).ready(function(){
            if($('.post_mode').text() == "공지사항 등록"){//신규글쓰기
                $('.post_title_input').val("");//제목란 비우기
                $('.post_title_input').attr('readonly', false);//작성가능하게
            }
            /*등록 하기 (새글) */
            $('.post_upload_btn').on('click', function(){
                //     제목, 내용 빈칸 체크하기
                if($('input[class="post_title_input"]').val().trim() == ""){
                    alert("제목은 빈칸일 수 없습니다.");
                    $('input[class="post_title_input"]').focus();
                    return;
                }
                else if($('#post_content').val().trim() == ""){
                    alert("내용을 추가하세요");
                    $('#post_content').focus();
                    return;
                }
                else {
                    let form = $('.post_form');
                    form.attr("action", "<c:url value='/board/write'/>");
                    form.attr("method", "POST");
                    form.attr('enctype','multipart/form-data');
                    form.submit();
                }
            })

            /* 돌아가기 */
            $('.post_return_btn').on('click', function(){
                location.href = "<c:url value='/board/list'/>?page_now=${page_now}&page_size=${page_size}&nav_page=${nav_page}";
            })

            /* 수정하기 */
            $('.post_modify_btn').on('click', function(){
                let select_box  = `<select name="board_class" class="post_class_sel">
                            <option value="0">중요공지</option>
                            <option value="1">일반공지</option>
                            <option value="2">경조사</option>
                        </select>`;
                let input_file_box = `<input type="file" name="f_file" multiple="multiple">`

                let is_read_only = $('.post_title').attr("readonly");
                if(is_read_only == "readonly"){
                    $('.post_title').attr("readonly", false).focus();
                    $('#post_content').attr("readonly", false);
                    $('.post_title').after(select_box);
                    $('#post_content').after(input_file_box);
                    $('.post_tag_box').css({"display":"none"})
                    $('.post_modify_btn').text("확인");
                    $('.post_cmt_area').css({"display":"none"})
                    return;
                }
                let tmp_param = get_uri_info().toString();
                console.log(tmp_param);
                let form = $('.post_form');
                form.attr("action", "<c:url value='/board/modify'/>?"+tmp_param);
                form.attr("method", "POST");
                form.attr('enctype','multipart/form-data');
                form.submit();
            })

            /* 삭제하기 */
            $('.post_delete_btn').on('click', function(){
                if(!confirm("삭제하시겠습니까?")) return;
                let tmp_param = get_uri_info();
                let form = $(".post_form");
                form.attr("action", "<c:url value='/board/remove'/>?"+tmp_param)
                // /app/board/remove?tmp_param
                form.attr("method", "post");
                // frm.attr("method", "get");
                form.submit();
            })

            function get_uri_info(){
                return new URLSearchParams(location.search);
            }
        })
    </script>
</main>
</body>
</html>
