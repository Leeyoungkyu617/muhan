<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="userName" value="${ pageContext.request.getSession(false).getAttribute('user_name')==null ? '':pageContext.request.getSession(false).getAttribute('user_name') }" />



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <link rel="stylesheet" href="/css/common/header.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<body>

<header>
    <div class="header_logo">
        <a href="/">로고</a>
    </div>
    <div class="header_r">
        <div class="header_att_chk">
            <div class="header_start">
                출근시간: <div class="time_box"><span class="header_under_line"><fmt:formatDate value="${att_start}" pattern="HH:mm:ss"/></span></div>
                <button class="header_btn">출근</button>
            </div>
            <div class="header_end">
                퇴근시간: <div class="time_box"><span class="header_under_line line_bottom"><fmt:formatDate pattern="HH:mm:ss" value="${att_end}"/></span></div>
                <button class="header_btn btn_bottom">퇴근</button>
            </div>
        </div>
        <div class="_box">
            <c:if test="${user_dept == 12}">
            <a href="/register/join" class="header_user_name">${userName}</a>님
            </c:if>
            <c:if test="${user_dept != 12}">
                <a href="/infoChange" class="header_user_name">${userName}</a>님
            </c:if>

        </div>
        <a href="/approval/selected?option=M">
            <div class="xi-bell-o">
                <div class="header_bell_circle"><span class="header_alert_qty">${appr_count}</span></div>
            </div>
        </a>
        <a href="<c:url value='/logout'/>"><span class="header_logout">로그아웃</span></a>
        <div class="header_ham_btn xi-bars xi-2x"></div>
    </div>
</header>

</body>
<script>

    $(document).ready(function() {

        let chk = false;
        $('.header_ham_btn').on('click', function(){
            if(!chk) {
                $('.menu').css({
                    display: 'block',
                    right: 0
                })
            }
            else {
                $('.menu').css({
                    display: 'none',
                    right: '100%'
                })
            }
            chk = !chk;
        })


        $('.btn_bottom').on('click', function() {
            <c:if test="${att_end == null}">
                location.href="<c:url value='/attend/updatetime'/>";
            </c:if>
            // 아래는 form일 경우에만 사용가능
            <%--$('#att_btn_box').attr("method", "POST");--%>
            <%--$('#att_btn_box').attr("action", "<c:url value='/attend/updatetime'/>");--%>
            <%--$("#att_btn_box").submit();--%>


        });

    });
</script>
</html>