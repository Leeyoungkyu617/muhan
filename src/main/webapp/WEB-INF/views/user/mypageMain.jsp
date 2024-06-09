<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Muhan</title>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <link rel="stylesheet" href="<c:url value='/css/user/mypageMain.css'/> ">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>

<body>
<jsp:include page="../common/header.jsp" flush="false"/>
<main>
    <jsp:include page="../common/menu_left.jsp" flush="false"/>
    <div class="main_right_area">
        <div class="main_container">
            <div class="choose_main">
                <div class="cho_main_a">
                    <form action="<c:url value='/infoChange'/>" method="GET">
                    <button class="big_btn">회원정보 수정</button>
                    </form>
                    <form action="<c:url value='/pwChange'/>" method="GET">
                    <button class="big_btn">비밀번호 변경</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
