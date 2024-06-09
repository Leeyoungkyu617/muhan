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
    <link rel="stylesheet" href="<c:url value='/css/board/comment.css'/> ">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<div class="cmt_start_tab">댓글 (<span class="cmt_qty"><%--count comment row--%></span>)</div>
<form action="" id="cmt_reg_comment">
    <textarea rows="1" onkeyup="autoResize(this)" onkeydown="autoResize(this)" id="cmt_txt_comment" name="com_comment"></textarea>
    <button type="button" id="cmt_submit_btn">등록</button>
</form>
<div id="cmt_list">

</div>
<script>
    function zero(num) {
        if(num < 10) {
            return "0" + num;
        }
        return num;
    }
    function autoResize(textarea){
        textarea.style.overflow='hidden';
        textarea.style.height='auto';
        textarea.style.height = textarea.scrollHeight+'px';
    }
    // let cmtComment = document.querySelector('.cmt_comment');
    // if(cmtComment.className === 'cmt_comment'){
    //     console.log(333)
    //     // cmtComment.style.overflow='hidden';
    //     // cmtComment.style.height='auto';
    //     // cmtComment.style.height = cmtComment.scrollHeight+'px';
    // }
    $(document).ready(function(){

        let com_board_num = ${param.board_seq};
        let userId = <%= session.getAttribute("user_num")%>;

        let show_comment_list = function(com_board_num){
            $.ajax({
                url: '/comments?board_seq='+com_board_num,
                type: 'GET',
                dataType:"json",

                success: function(result){
                    let tmp = "";

                    result.forEach(function(rowResult){
                        let today = new Date(rowResult.com_reg_date);
                        let year = today.getFullYear();
                        let month = today.getMonth()+1;
                        let date = today.getDate();
                        let hour = today.getHours();
                        let min = today.getMinutes();
                        let sec = today.getSeconds();

                        tmp += `
                            <div class="cmt_row" <%--data-cno="${cno}" data-bno="${bno}"--%>>
                                <div class="cmt_row_content_box">
                                    <textarea rows="1" onkeyup="autoResize(this)" onkeydown="autoResize(this)" name="com_comment" class="cmt_comment" readonly>${"${rowResult.com_comment}"}</textarea>
                                    <div class="cmt_comment">
                                    </div>
                                    <div class="cmt_info_tab">
                                        <div class="cmt_commenter">${"${rowResult.user_name}"}</div>
                                        <div class="cmt_reg_date">${'${year}'}-${'${zero(month)}'}-${'${zero(date)}'} ${'${zero(hour)}'}:${'${zero(min)}'}:${'${zero(sec)}'}</div>
                                    </div>
                                </div>`
                        if(userId === rowResult.com_commenter){
                            tmp+=`<ul class="cmt_row_info_box">
                                    <li data-comseq="${'${rowResult.com_seq}'}" data-boardseq="${'${rowResult.com_board_num}'}" class="cmt_util_tab">
                                        <div class="cmt_modify_btn"><span>수정</span></div>
                                        <div class="cmt_modify_save_btn display_none"><span>저장</span></div>
                                        <div class="cmt_modify_cancel_btn display_none"><span>취소</span></div>
                                        <div class="cmt_delete_btn"><span>삭제</span></div>
                                    </li>
                                </ul>`
                        }
                        tmp+=`</div>`;




                    })
                    $('.cmt_qty').text(result.length);
                    $('#cmt_list').html(tmp);
                },
                error:function(){
                    alert("댓글을 불러오지 못했습니다.");
                }
            });
        }
        show_comment_list(com_board_num);

        $(document).on('click', '#cmt_submit_btn', function(){
            let comment = $('#cmt_txt_comment').val().trim();
            if(comment==""){
                alert("댓글을 입력하세요");
                $('.cmt_txt_comment').focus();
                return;
            }
            $.ajax({
                type: 'POST',
                url:"/comments?board_seq="+com_board_num,
                headers: {"content-type":"application/json"},
                data:JSON.stringify({com_board_num:com_board_num, com_comment:comment}),
                success:function(result){
                    $('#cmt_txt_comment').val('');
                    show_comment_list(com_board_num);
                },
                error:function (request, status, error){
                    console.log(request.status);
                    console.log(request.responseText);
                    console.log(error);
                    alert("등록실패 - 관리자에게 문의 요망")
                }
            })
        })

        let curr_comment = "";
        $(document).on('click', '.cmt_modify_btn', function(){//수정버튼 눌렀을 때 저장/취소 버튼 나오게하기
            let el = $(this).parents('.cmt_row').find($('textarea'));
            curr_comment =el.val();
            console.log("curr_comment : "+ curr_comment);
            $(this).toggleClass('display_none');
            $(this).nextAll().toggleClass('display_none');
            el.addClass('active_edit');
            el.attr("readonly", false).focus();

        })
        /* 취소 버튼 - 수정 누르고 나타난 취소 버튼 */
        $(document).on('click', '.cmt_modify_cancel_btn', function(){
            let el = $(this).parents('.cmt_row').find($('textarea'));
            el.attr('readonly', true);
            el.removeClass('active_edit');
            el.val(curr_comment);
            $(this).prevAll().toggleClass('display_none');
            $(this).addClass('display_none');
            $(this).next().removeClass('display_none');
        })

        $(document).on('click', '.cmt_modify_save_btn', function(){//저장 누르면 원래대로 돌아오기
            $(this).toggleClass('display_none');
            $(this).siblings().toggleClass('display_none');
            let tmp_el = $(this).parents('.cmt_row_info_box').prev();//$('.cmt_row_content_box')
            // tmp_el.children('div').removeClass('display_none');
            // tmp_el.children('textarea').addClass('display_none');
            let comseq = $(this).parent().attr("data-comseq");
            let board_seq = $(this).parent().attr("data-boardseq");
            let el = $(this).parents('.cmt_row').find($('textarea'));
            let comment = el.val();
            console.log(comseq, board_seq, el, comment);

            $.ajax({
                type: 'PATCH',
                url:'/comments/'+comseq,
                headers: { "content-type": "application/json"},
                data: JSON.stringify({com_seq: comseq, com_comment:comment}),
                success: function(result){
                    show_comment_list(com_board_num);
                },
                error: function(){alert("error")}
            })
        })

        $(document).on('click', '.cmt_delete_btn', function(){
            let comseq = $(this).parent().attr("data-comseq");
            let board_seq = $(this).parent().attr("data-boardseq");

            if(confirm("정말 삭제하시겠습니까?")){
                $.ajax({
                    type:"DELETE",
                    url:"/comments/"+comseq+"?board_seq="+board_seq,
                    success:function(){
                        show_comment_list(board_seq)
                    },
                    error:function(){
                        alert("삭제 실패")
                    }
                })

            }

        })
    })
</script>
</body>
</html>