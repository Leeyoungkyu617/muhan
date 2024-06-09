<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
<ul class="menu">
    <li class="menu_li">
        <a href="/mypageMain">마이페이지</a></li>
    <li class="menu_li">
        <a href="/approval">결재</a></li>
    <li class="menu_li">
        <a href="/schedule">일정</a></li>
    <li class="menu_li">
        <a href="/board/list">사내</a></li>
    <li class="menu_li">
        <a href="/attend/checkpage">근태</a></li>
    <li class="menu_li">
        <a href="/meetingroom">회의실예약</a></li>
    <li class="menu_li">
        <a href="/event_photo">사진</a></li>
    <li class="menu_user_box">
        <div class="menu_user_circle">
            <div class="menu_user_photo"></div>
        </div>
        <div class="menu_user_info">
            <span class="menu_user_level">${level_name} - </span>
            <span class="menu_user_name">${user_name}</span>
        </div>
    </li>

    <a href=""><span class="header_logout2">로그아웃</span></a>
</ul>
</body>
<script>
    $(document).ready(function(){
        $('.menu_user_photo').css({
            backgroundImage: "url('/img/${user_photo}')"
            // backgroundImage: "url('/img/Default-Profile.png')"
        })
    })
</script>
</html>