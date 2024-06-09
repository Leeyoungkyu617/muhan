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
    <link rel="stylesheet" href="<c:url value='/css/event_photo/eventPhotoAdmin.css'/> ">
</head>
<body>

<jsp:include page="../common/header.jsp" flush="false"/>

<main>
    <jsp:include page="../common/menu_left.jsp" flush="false"/>

    <div class="photo_container">
        <div class="photo_header">
            <h2 class="photo_header_title">사진 게시판 ${mode=="new"?"추가":"수정"}</h2>
        </div>
        <div class="photo_body">
            <form action="" id="photo_board_admin" method="post" enctype="multipart/form-data">
                <input type="hidden" name="photo_seq" value="${param.photo_seq}">

                <c:if test="${mode == 'new'}" >
                    <input type="hidden" name="photo_uploader" value="${user_num}">
                </c:if>
                <c:if test="${mode == 'modify'}" >
                    <input type="hidden" name="photo_uploader" value="${list.photo_uploader}">
                </c:if>

                <input type="hidden" name="photo_new_name" value="${list.photo_new_name}">
                <%--                히든넣으세요--%>
                <div class="photo_title_box">
                    <div class="photo_title">제목:</div>
                    <input type="text"  id="photo_title" class="photo_title_input" name="photo_title" value="${list.photo_title}">
                </div>
                <div class="photo_text">이미지 파일 첨부</div>
                <input type="file" name="f_file" multiple="multiple" onchange="readURL(this);"/>
                <div class="img_box">
                    <img id="preview" src="<c:url value='/img/${list.photo_new_name}"'/>" alt="선택된 이미지가 없습니다" >
                </div>
                <c:if test="${mode == 'new'}" >
                    <button class="photo_btn" id="modal_close">저장</button>
                </c:if>
                <c:if test="${mode == 'modify'}" >
                    <button class="photo_btn" id="modal_update">수정</button>
                </c:if>
                <input type="button" class="photo_btn" id="go_back" value="목록으로" />
            </form>
        </div>
    </div>
</main>
<script>
    $(document).ready(function (){

        window.readURL = function(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    $('#preview').attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }

        $(document).on('click', '#modal_close', function(event) {
            event.preventDefault(); // 기본 폼 제출 방지
            if($('#photo_title').val().trim() === "") {
                alert("제목을 입력하시오");
                $('#photo_title').focus();
                return;
            }
            var previewSrc = $('#preview').attr('src');
            var defaultSrc = "<c:url value='/img/${list.photo_new_name}'/>";

            if (previewSrc === "" || previewSrc === defaultSrc) {
                alert("사진을 추가하시오");
                return;
            }
            else {
                alert("저장");
                $('#photo_board_admin').attr('action', '<c:url value="/event_photo"/>?page_now=1&page_size=9');
                $('#photo_board_admin').submit(); // 폼 제출
            }
        });

        $(document).on('click','#go_back', function(){
            const urlParams = new URL(location.href).searchParams;
            const page_now = urlParams.get('page_now');
            const page_size = urlParams.get('page_size');

            location.href="<c:url value="/event_photo"/>?page_now="+page_now+"&page_size="+page_size;
        })

        $(document).on('click','#modal_update', function(){
            alert("업데이트")
            $('#photo_board_admin').attr('action', '<c:url value="/event_photo/update"/>?page_now=1&page_size=9');
            $('#photo_board_admin').submit(); // 폼 제출

        })





    });
</script>
</body>
</html>
