<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Muhan</title>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <link rel="stylesheet" href="<c:url value='/css/user/findPw.css'/> ">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>

</head>
<main>
    <div class="main_right_area">
        <div class="searchPw_container">
            <form action="<c:url value='/findPw'/>" method="POST" onsubmit="return formCheck(this)">
                <div class="title">비밀번호 찾기</div>
                <div id="msg" class="msg">
                    <c:if test="${not empty param.msg}">
                        <i class="fa fa-exclamation-circle"> 일치하는 회원정보가 없습니다.</i>
                    </c:if>
                </div>
                <div class="info_certifi_form">
                    <div class="info_certifi">
                        <label for="">사번</label>
                        <input class="txt " type="text" name="user_num" placeholder="" value="">
                    </div>
                    <div class="info_certifi">
                        <label for="">이름</label>
                        <input class="txt" type="text" name="user_name" placeholder="" value="">
                    </div>
                    <div class="info_certifi">
                        <label for="">연락처</label>
                        <input class="txt" id="user_phone" type="text" name="user_phone" placeholder="   -제외(11자리)" value="">
                    </div>
                </div>
                <div class="btn_box">
                    <button class="btn_save">인증</button>
                </div>
            </form>
        </div>
    </div>
</main>
</body>
<script>
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
</script>
</html>
