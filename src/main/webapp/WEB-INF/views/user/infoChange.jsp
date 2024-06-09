<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--<c:set var="logInOutLink" value="${ sessionScope.user_num==null ? '/login/login':'/login/logout' }" />--%>
<%--<c:set var="logInOutTxt" value="${ sessionScope.user_num==null ? 'login':'logout' }" />--%>

<%--<c:set var="user_num" value="${ pageContext.request.getSession(false).getAttribute('user_num')==null ? '':pageContext.request.getSession(false).getAttribute('user_num') }" />--%>


<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Muhan</title>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <link rel="stylesheet" href="<c:url value='/css/user/infoChange.css'/> ">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>

<body>
<jsp:include page="../common/header.jsp" flush="false"/>
<main>
    <jsp:include page="../common/menu_left.jsp" flush="false"/>
    <div class="main_right_area">
        <div class="info_C_container">
            <form action="" id="frm">
                <div class="title">회원정보 수정</div>
                <div id="msg" class="msg"></div>

                <ul class="info_form">
                    <li><label for="">사번</label>
                        <div class="txt_box">
                            <input class="txt txt_readonly" type="text" name="user_num" value="${user_num}" readonly>
                        </div>
                    </li>
                    <li><label for="">부서</label>
                        <div class="txt_box">
                            <input class="txt txt_readonly" type="text" name="dept_name" value="${user.dept_name}" readonly>
                            <input type="hidden" name="user_dept" value="${user.user_dept}">
                        </div>
                    </li>
                    <li><label for="">직급</label>
                        <div class="txt_box">
                            <input class="txt txt_readonly" type="text" name="level_name" value="${user.level_name}" readonly>
                            <input type="hidden" name="user_level" value="${user.user_level}">
                        </div>
                    </li>
                    <li><label for="">입사일</label>
                        <div class="txt_box">
                            <input class="txt txt_readonly" type="date" id="currentDate" name="user_in_date" value="${user.user_in_date}" readonly>
                        </div>
                    </li>
                    <li><label for="">이름</label>
                        <div class="txt_box">
                            <input class="txt txt_readonly" type="text" name="user_name" value="${user.user_name}" readonly>
                        </div>
                    </li>
                    <li><label for="">연락처</label>
                        <div class="txt_box">
                            <input id="user_phone" class="txt txt_able" type="text" id="user_phone" name="user_phone" placeholder="${user.user_phone}" value="${user.user_phone}">
                            <!-- <button class="change_button" onclick='btnClick()'>수정</button> -->
                        </div>
                    </li>
                    <li><label for="">주소</label>
                        <div class="txt_box">
                            <input class="txt txt_able" type="text" id="user_addr" name="user_addr" placeholder="${user.user_addr}" value="${user.user_addr}">
                            <!-- <button class="change_button" onclick='btnClick()'>수정</button> -->
                        </div>
                    </li>
                    <li><label for="">이메일</label>
                        <div class="txt_box">
                            <input class="txt txt_readonly" type="text" name="user_email" value="${user.user_email}" readonly>
                        </div>
                    </li>
                    <li><label for="">생년월일</label>
                        <div class="txt_box">
                            <input class="txt txt_readonly" type="date" name="user_birth" data-placeholder="날짜 선택" value="${user.user_birth}" readonly>
                        </div>
                    </li>
                    <li><label for="">사진</label>
                        <div class="img_box">
                            <div class="profile-image-area">
                                <c:choose>
                                    <c:when test="${user.user_photo == null}">
                                        <!-- 프로필 이미지가 없으면 기본 이미지 -->
                                        <img src="./img/Default-Profile.png" id="profileImage">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="<c:url value='./img/${user.user_photo}'/>" id="profileImage">
                                    </c:otherwise>
                                </c:choose>
                                <input type="file" name="f_file" multiple="multiple">
                            </div>

<%--                            <div class="profile-btn-area">--%>
<%--                                <label for="imageInput">이미지 선택</label>--%>
<%--                                <input type="file" name="profileImage" id="imageInput" >--%>
<%--                            </div>--%>
                        </div>
                    </li>
                </ul>
                <div class="btn_box">
                    <input type="button" id="btn_check" value="수정" class="btn btn_save">
                    <a href="<c:url value='/mypageMain'/>">
                        <input type="button" onclick="cancle()" class="btn btn_cancle" value="취소">
                    </a>
                </div>
            </form>
        </div>
    </div>
</main>
</body>
    <script>
        // const searchParams = new URLSearchParams(location.search);
        // for (const param of searchParams) {
        //     if(param[0] == "msg") {
        //         alert(param[1])
        //     }
        // }

        // 수정버튼 누르면 컨펌창 띄우기
        // function call_confirm(){
        //     if(confirm("정보를 수정하시겠습니까?")){
        //         alert("수정되었습니다.");
        //         // window.location = 'mypageMain.html';
        //         frm.submit();
        //     }else{
        //         alert("취소되었습니다.");
        //     }
        //
        // }

        $(document).ready(function(){
            $('#btn_check').on('click', function(){
                if($('#user_phone').val().trim() == "") {
                    alert("연락처를 입력해주세요.");
                    $('#user_phone').focus();
                    // return;
                }
                else if($('#user_addr').val().trim() == "") {
                    alert("주소를 입력해주세요.");
                    $('##user_addr').focus();
                    // return;
                }
                else {
                    if(confirm("정보를 수정하시겠습니까?")){
                        alert("수정되었습니다.");
                        // window.location = 'mypageMain.html';
                        let frm = $("#frm");
                        frm.attr("action", "<c:url value='/infoChange/save'/>")
                        frm.attr("method", "POST");
                        frm.attr("enctype","multipart/form-data");
                        frm.submit();

                    }else{
                        alert("취소되었습니다.");
                    }
                }
            });
        });




            // 수정 가능한 목록 빈값 확인
        // function allcheck() {
        //     if(formAllCheck.user_phone.value.length == 0){
        //         alert("연락처를 입력해주세요.");
        //         formAllCheck.user_phone.focus();
        //         return false;
        //     }
        //
        //     if(formAllCheck.user_addr.value.length == 0){
        //         alert("주소를 입력해주세요.");
        //         formAllCheck.user_addr.focus();
        //         return false;
        //     }
        //     return true;
        // }

        // user_phone 연락처 유효성 검사
        $(document).ready(function() {
            $("#user_phone").blur(function() {
                // 입력된 전화번호 가져오기
                var phoneNumber = $("#user_phone").val();
                if(phoneNumber) {
                    // 정규식을 사용하여 형식 검사
                    var regex = /^(01[0-9]{1}-?[0-9]{4}-?[0-9]{4}|01[0-9]{8})$/;

                    if (regex.test(phoneNumber)) {
                        // 올바른 형식일 경우
                    } else {
                        // 잘못된 형식일 경우
                        alert("잘못된 형식의 전화번호 입니다.");
                        $("#user_phone").val("");
                        return false;
                    }

                    var pcs = phoneNumber;

                    // 입력된 문자열에서 하이픈('-')을 제거하여 숫자만 추출
                    var pcs = pcs.replace(/[^0-9]/g, '');

                    // 전화번호 형식 (010-1234-5678)으로 변환
                    if (pcs.length === 10) {
                        pcs = pcs.substring(0, 3) + '-' + pcs.substring(3, 7) + '-' + pcs.substring(7, 11);
                    } else if (pcs.length === 11) {
                        pcs = pcs.substring(0, 3) + '-' + pcs.substring(3, 7) + '-' + pcs.substring(7, 11);
                    }

                    $("#user_phone").val(pcs);
                }
            });
        });

        // 사진 첨부 변경 버튼 클릭시 input value 값 변경
        <%--function btnClick() {--%>
        <%--    const myinput = document.getElementById('imageInput');--%>

        <%--    myinput.value = "${user.user_photo}";--%>
        <%--}--%>


        //
        // function cancle() {
        //     alert("취소되었습니다.")
        // }

        // 정보 수정 버튼 클릭시 input value 값 변경
        // function btnClick() {
        // const myinput = document.getElementsByClassName('change_button');

        // myinput.value = "";
        // }
    </script>
</html>
