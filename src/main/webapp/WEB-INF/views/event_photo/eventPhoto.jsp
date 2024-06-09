<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <link rel="stylesheet" href="<c:url value='/css/event_photo/eventPhoto.css'/> ">
</head>
<body>

<jsp:include page="../common/header.jsp" flush="false"/>

<main>
    <jsp:include page="../common/menu_left.jsp" flush="false"/>

    <div class="photo_container">
        <div class="photo_header">
            <h2 class="photo_header_title">사진 게시판</h2>
            <c:if test="${user_dept == 12}">


                <button id="photo_write_btn" onclick="location.href='<c:url value="/event_photo/write?page_now=${param.page_now}&page_size=${param.page_size}" />'">추가하기</button>
            </c:if>
        </div>
        <div class="photo_body">
            <c:forEach var="photoDto" items="${list}">
                <div class="photo_box">
                    <div class="photo">
                        <img src="<c:url value='/img/${photoDto.photo_new_name}'/>" class="photo_img">
                    </div>
                    <div class="photo_sub">${photoDto.photo_title}</div>

                    <span class="photo_uploader">${photoDto.photo_uploader}</span>
                    <span class="photo_seq" >${photoDto.photo_seq}</span>
                    <span class="photo_reg_date"><fmt:formatDate pattern = "yyyy-MM-dd" value="${photoDto.photo_reg_date }"/></span>

                </div>
            </c:forEach>

        </div>
    </div>
    <div class="page_nav">
        <c:if test="${ph.show_pre_btn}">
            <a href="<c:url value="/event_photo?page_now=${ph.start_page - 1}&page_size=${ph.page_size}"/>" class="start_page">[이전]</a>
        </c:if>

        <c:forEach var="i" begin="${ph.start_page}" end="${ph.end_page}">
            <a href="<c:url value="/event_photo?page_now=${i}&page_size=${ph.page_size}" />" class="${i==ph.page_now?"pageActive":""} page_nav_i">${i}</a>
        </c:forEach>

        <c:if test="${ph.show_next_btn}">
            <a href="<c:url value="/event_photo?page_now=${ph.end_page + 1}&page_size=${ph.page_size}" />" class="end_page">[다음]</a>
        </c:if>
    </div>

    <div class="modal_bg"></div>
    <div class="modal_wrap">
        <form method="post" action="" id="modal_form">
            <input id="photo_title" class="photo_title" name="photo_title"></input>
            <span class="number">NO.</span><input type="text" class="photo_seq" name="photo_seq" readonly/><br>

            작성자: <input class="photo_uploader" name="photo_uploader"><br>
            등록일: <b class="photo_reg_date"></b>

            <div class="photo_img_box">
                <img src="<c:url value=''/>" class="photo_img_box_img"/>
            </div>
            <div class="btn_box">
                <%--                <c:if test="${eventPhotoDto.photo_uploader==(user_num)}">--%>
                <%--                    <button type="button" class="photo_btn_del photo_btn" id="modal_del">삭제</button>--%>
                <%--                    <button type="button" class="photo_btn_update photo_btn" id="modal_update">수정</button>--%>
                <%--                </c:if>--%>
                <%--                <button class="photo_btn" id="modal_close" type="button">닫기</button>--%>
            </div>
        </form>
    </div>
</main>
<script>

    $(document).ready(function() {
        $(document).on('click','.photo_box', function(e){
            // $('.photo_box').on('click', function(e) {
            e.preventDefault();
            popOpen($(this));
        });


        $(document).on('click','.modal_bg, #modal_close', function(){
            // $('.modal_bg, #modal_close').on('click', function() {
            popClose();
        });


        //창 열릴 때
        function popOpen(el)
        {

            $('.modal_bg').css({
                display: 'block'
            });
            $('.modal_wrap').css({
                display: 'block'
            });

            $('.modal_wrap .photo_title').val( el.find('.photo_sub').text() );

            $('.modal_wrap .photo_img_box_img').attr('src', el.find('.photo_img').attr('src'));

            $('.modal_wrap .photo_seq').val( el.find('.photo_seq').text() );

            $('.modal_wrap .photo_uploader').val( el.find('.photo_uploader').text() );

            $('.modal_wrap .photo_reg_date').text( el.find('.photo_reg_date').text() );


            if(el.find('.photo_uploader').text() == ${user_num}) {

                $('.btn_box').html(`<button type="button" class="photo_btn_del photo_btn" id="modal_del">삭제</button>
                                    <button type="button" class="photo_btn_update photo_btn" id="modal_update">수정</button>
                                    <button class="photo_btn" id="modal_close" type="button">닫기</button>`);
            }
            else {
                console.log("바보")
                $('.btn_box').html(`<button class="photo_btn" id="modal_close" type="button">닫기</button>`);
            }

        }

        //창 닫힐 때
        function popClose() {
            $('.modal_bg').css({
                display: 'none'
            });
            $('.modal_wrap').css({
                display: 'none'
            });
        }

        $('#modal_update')




        //삭제버튼
        $(document).on('click','#modal_del', function(){
            // $('#modal_del').on('click', function() {

            let tmp = $('#photo_seq').val();

            $('#modal_form').attr('action', '<c:url value="/event_photo/admin"/>' );
            popClose();
            $('#modal_form').submit();
        });

        //수정버튼
        /*        $('#modal_update').on('click', function() {

                    let tmp = $('#photo_seq').val();

                    $('#modal_form').attr('action', '<c:url value="/event_photo/update"/>' );

            popClose();
            $('#modal_form').submit();
        });*/
        $(document).on('click','#modal_update', function(){
            let photoSeq = $('.modal_wrap .photo_seq').val();
            <%--$('#modal_form').attr('action', '<c:url value="/event_photo/update?photo_seq=' + photoSeq + '"/>' );--%>
            <%--$('#modal_form').submit();--%>

            const urlParams = new URL(location.href).searchParams;
            const page_now = urlParams.get('page_now');
            const page_size = urlParams.get('page_size');

            location.href="<c:url value="/event_photo/modify"/>?page_now="+page_now+"&page_size="+page_size+"&photo_seq="+photoSeq;

        });



        <%--$('#photo_write_btn').on('click',function(){--%>

        <%--    this.attr("action", "<c:url value='/event_photo'/>")--%>
        <%--    this.attr("method", "POST");--%>
        <%--});--%>


        <%--$('#modal_close').on('click',function(){--%>
        <%--    let  pb = $('#photo_board');--%>
        <%--    pb.attr("action", '<c:url value="/event_photo?page_now=${ph.page_now}&page_size=${ph.page_size}"/>');--%>
        <%--    pb.attr("method", "GET");--%>

        <%--});--%>

    });
</script>
</body>
</html>
