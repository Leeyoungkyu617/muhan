<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="logInOutLink" value="${ sessionScope.user_num==null ? '/login':'/logout' }" />
<c:set var="logInOutTxt" value="${ sessionScope.user_num==null ? 'login':'logout' }" />
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Muhan</title>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <link rel="stylesheet" href="<c:url value='../css/user/loginForm.css'/> ">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<main>
    <div class="main_right_area">
        <div class="login_container">
            <form action="<c:url value='/login'/>" name="formAllCheck"  method="POST" onsubmit="return allcheck()">
                <div class="title">Login</div>
                <div class="msg">
                    <c:if test="${not empty param.msg}">
                        <i class="fa fa-exclamation-circle"> 일치하는 회원정보가 없습니다.</i>
                    </c:if>
                </div>
                <div class="login_form">
                    <div class="login_box">
                        <input class="txt " type="text" name="user_num" value="${ cookie.user_num.value }" placeholder="사번" autofocus>
                    </div>
                    <div class="login_box">
                        <input class="txt" type="password" name="user_pw" placeholder="비밀번호" value="">
                    </div>

                    <div class="chkInfo">
                        <label><input type="checkbox" name="rememberId" ${empty cookie.user_num.value?"":"checked" }>사번 기억하기</label>


                        <a href="/findPw">비밀번호 찾기</a>
                    </div>
                </div>
                <input class="btn" type="submit" value="로그인">
            </form>
        </div>
    </div>
</main>
</body>
<script>

    // 사번, 비밀번호 유효성 검사
    function allCheck() {
        if(formAllCheck.user_num.value.length == 0) {
            alert("사번을 입력해주세요.")
            return false;
        }

        if(formAllCheck.user_pw.value.length == 0) {
            alert("비밀번호를 입력해주세요.")
            return false;
        }

        return true;
    }

    function setMessage(msg, element){
        document.getElementById("msg").innerHTML = ` ${'${msg}'}`;

        if(element) {
            element.select();
        }
    }

</script>
</html>

