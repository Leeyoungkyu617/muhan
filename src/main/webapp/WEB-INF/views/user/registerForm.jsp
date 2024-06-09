<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Muhan</title>
    <link rel="stylesheet" href="<c:url value='../css/user/registerForm.css'/> ">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<jsp:include page="../common/header.jsp" flush="false"/>
<main>
    <jsp:include page="../common/menu_left.jsp" flush="false"/>

    <div class="main_right_area">
        <div class="rg_container">
<%--            <form action="<c:url value='/register/save' />" name="formAllCheck" method="POST" onsubmit="return allcheck()">--%>
            <form enctype="multipart/form-data" action="<c:url value='/register/save'/>" name="formAllCheck" method="POST" onsubmit="return allcheck(this)">
                <div class="title">계정 생성</div>
<%--                <h1>${param.msg}</h1>--%>

                <ul class="rg_form">
                    <li><label for="">사번</label>
                        <div class="txt_box">
                            <input class="txt" type="text" name="user_num" placeholder="사번이 아이디입니다." autofocus value="${newUno}" readonly>
                        </div>
                    </li>
                    <li><label for="">비밀번호</label>
                        <div class="txt_box">
                            <input class="txt" type="text" name="user_pw" placeholder="12345678" value="12345678" readonly>
                        </div>
                    </li>
                    <li><label for="">부서</label>
                        <div class="select_box">
                            <select class="select" name="user_dept">
                                <option value="" selected="selected">선택</option>
                                <option value="1">오너</option>
                                <option value="2">총무부</option>
                                <option value="3">인사과</option>
                                <option value="4">기획팀</option>
                                <option value="5">개발부</option>
                                <option value="6">영업팀</option>
                                <option value="7">경영지원</option>
                                <option value="8">영업지원</option>
                                <option value="9">품질관리</option>
                                <option value="10">생산부</option>
                                <option value="11">R&D</option>
                                <option value="12">관리부</option>
                            </select>
                        </div>
                    </li>
                    <li><label for="">직급</label>
                        <div class="select_box">
                            <select class="select" name="user_level">
                                <option value="" selected="selected">선택</option>
                                <option value="1">사장</option>
                                <option value="2">부사장</option>
                                <option value="3">전무</option>
                                <option value="4">이사</option>
                                <option value="5">부장</option>
                                <option value="6">차장</option>
                                <option value="7">과장</option>
                                <option value="8">대리</option>
                                <option value="9">주임</option>
                                <option value="10">사원</option>
                            </select>
                        </div>
                    </li>
                    <li><label for="">입사일</label>
                        <div class="txt_box">
                            <input class="txt" type="date" id="currentDate" name="user_in_date" placeholder="">
                        </div>
                    </li>
                    <li><label for="">이름</label>
                        <div class="txt_box">
                            <input class="txt" type="text" name="user_name" placeholder="구혜민">
                        </div>
                    </li>
                    <li><label for="">연락처</label>
                        <div class="txt_box">
                            <input class="txt" type="text" name="user_phone" id="user_phone" placeholder="01012345678">
                        </div>
                    </li>
                    <li><label for="">주소</label>
                        <div class="txt_box">
                            <input class="txt" type="text" name="user_addr" placeholder="서울특별시 강남구 테헤란로 14길 6 남도빌딩">
                        </div>
                    </li>
                    <li><label for="">이메일</label>
                        <div class="txt_box">
                            <input class="txt" type="text" name="user_email" placeholder="사번@muhan.com" value="${email}" readonly>
                        </div>
                    </li>
                    <li><label for="">생년월일</label>
                        <div class="txt_box">
                            <input class="txt" type="date" max="${maxDate}" id="end" name="user_birth" placeholder="">
                        </div>
                    </li>
                    <li><label for="">사진</label>
                        <input type="file" name="f_file"  multiple="multiple" onchange="readURL(this);"/>

                        <%--<div class="img_box">
                            <div class="profile-image-area">
                                <img src="./img/Default-Profile.png" id="profileImage">
                                <input type="text" value="Default-Profile.png"  name="user_photo"/>
                            </div>
                        </div>--%>

                    </li>
                </ul>
                <input class="btn" type="submit" value="계정 생성하기">
            </form>
        </div>
    </div>
</main>
</body>
<script>
    const searchParams = new URLSearchParams(location.search);
    for (const param of searchParams) {
        if(param[0] == "msg") {
            alert(param[1])
        }
    }



    //빈칸 유효성 검사
    function allcheck() {
        if(formAllCheck.user_dept.value.length == 0){
            alert("부서를 선택해주세요.");
            formAllCheck.user_dept.focus();
            return false;
        }

        if(formAllCheck.user_level.value.length == 0){
            alert("직급을 선택해주세요.");
            formAllCheck.user_level.focus();
            return false;
        }

        if(formAllCheck.user_in_date.value.length < 10){
            alert("입사일을 입력해주세요.");
            formAllCheck.user_in_date.focus();
            return false;
        }

        if(formAllCheck.user_name.value.length == 0){
            alert("이름을 입력해주세요.");
            formAllCheck.user_name.focus();
            return false;
        }

        if(formAllCheck.user_phone.value.length == 0){
            alert("연락처를 입력해주세요.");
            formAllCheck.user_phone.focus();
            return false;
        }

        if(formAllCheck.user_addr.value.length == 0){
            alert("주소를 입력해주세요.");
            formAllCheck.user_addr.focus();
            return false;
        }

        if(formAllCheck.user_birth.value.length < 10){
            alert("생년월일을 입력해주세요.");
            formAllCheck.user_birth.focus();
            return false;
        }
        return true;
    }

    // 생일 미래날짜 선택 막아두기(오늘날짜 이후 선택 불가)
    $(function(){//오늘 날짜 가져오기
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth()+1;
        //1월이 0이라 +1
        var yyyy = today.getFullYear();
        if(dd<10){
            dd='0'+dd
        }
        if(mm<10){
            mm='0'+mm
        }
        today = yyyy+'-'+mm+'-'+dd;
        document.getElementById("end").setAttribute("max", today);
    })

    //when startdate is set, set start date as min of end date
    // function setendmin(e){
    // console.log(e);
    // document.getElementById("end").setAttribute("min", e);
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
    //비밀번호 유효성 검사 - 비밀번호 기본설정으로 필요없음
    // function formCheck(frm) {
    //
    //     var msg = '';
    //
    //     if (frm.user_pw.value.length < 8) {
    //         setMessage('pw의 길이는 8이상이어야 합니다.', frm.user_pw);
    //         return false;
    //     }
    //
    //     return true;
    // }

    <%--function setMessage(msg, element) {--%>
    <%--    document.getElementById("msg").innerHTML = `<b class="fa"> ${'${msg}'} </b>`;--%>

    <%--    if (element) {--%>
    <%--        // 해당 요소로 focus--%>
    <%--        element.select();--%>
    <%--    }--%>
    <%--}--%>

    // 입사일 현재 일자로 세팅
    document.getElementById('currentDate').value = new Date().toISOString().substring(0, 10);

</script>
</html>
