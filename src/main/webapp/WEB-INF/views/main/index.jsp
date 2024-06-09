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
    <link rel="stylesheet" href="<c:url value='/css/index.css'/>">
</head>
<body>

<jsp:include page="../common/header.jsp" flush="false"/>

<main>
    <jsp:include page="../common/menu_left.jsp" flush="false"/>

    <div class="main_container">
        <div class="main_header">
            <h2 class="main_header_title">메인게시판</h2>
        </div>
        <div class="main_body">
            <a href="/board/list" class="main_box">
                <div class="board_box">
                    <div><span class="span_squ">■</span> 공지사항</div>
                    <div class="main_box_content">
                        <table border="1" cellspacing="0">
                            <thead>
                                <tr>
                                    <th colspan="3">제목</th>
                                    <th >등록일</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="boardDto" items="${noticeList}">
                                <tr>
                                    <td colspan="3">${boardDto.board_title}</td>
                                    <td><fmt:formatDate value="${boardDto.board_date}" pattern="yy-MM-dd"/></td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </a>
            <a href="/attend/checkpage" class="main_box">
                <div class="meetingRoom_box ">
                    <div><span class="span_squ">■</span> 근태 관리</div>
                    <div class="main_box_content att_main">
                                <div class="att_week_work_time">
                                    <h3>금주 근로시간: ${wk_availtime}시간</h3>
                                    <progress id="att_progress_bar" value="${totalWorkTime}" min="0" max="${wk_availtime}"></progress>
                                    <div>지금까지의 근로시간은<br>총 ${totalWorkTime}시간입니다.</div>
                                </div>
                            <div id="att_timer_now_box">
                                <h3>현재 시간</h3>
                                <div id="att_timer_date"></div>
                                <div id="att_timer_time"></div>
                            </div>
                    </div>
                </div>
            </a>
            <a href="/schedule" class="main_box">
                <div class="schedule_box ">
                    <div><span class="span_squ">■</span> 개인일정</div>
                    <div class="main_box_content">
                        <table border="1" cellspacing="0">
                            <thead>
                            <tr>
                                <th>제목</th>
                                <th>시간</th>
                                <th>날짜</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="ScheduleDto" items="${selectList}">
                                <tr class="sche_tr">
                                    <td>${ScheduleDto.sche_title}</td>
                                    <td class="sche_date">${ScheduleDto.sche_start_time}</td>
                                    <td class="sche_time">${ScheduleDto.sche_start_time}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </a>
            <a href="/event_photo" class="main_box">
                <div class="main_photo_box ">
                    <div><span class="span_squ">■</span> 사진게시판</div>
                    <div class="main_box_content photo_board">
                        <c:forEach var="photo" items="${list}" varStatus="status">
                            <div class="photo_board_box">
                                <img src="<c:url value='/img/${photo.photo_new_name}'/>">
                            </div>
                        </c:forEach>


                    </div>
                </div>
            </a>
        </div>
    </div>

</main>
<script>
    $(document).ready(function (){
        $('.sche_tr').each(function(){
            var scheduleStartDate = $(this).find('.sche_date').text();
            var scheduleStartTime = $(this).find('.sche_time').text();
            var formattedTime = scheduleStartTime.split("T")[0];
            var formattedDate = scheduleStartTime.split("T")[1];

            $(this).find('.sche_date').text(formattedDate);
            $(this).find('.sche_time').text(formattedTime);




            function zero(num) {
                if(num < 10) {
                    return "0" + num;
                }
                return num;
            }
            function dateTimer(){
                let string_date = zero(month) + "월 " + zero(date)+"일";
                let el_date = document.querySelector('#att_timer_date');
                putTimer(string_date, el_date);
            }
            function timeTimer(){
                today = new Date();
                let hour = today.getHours();
                let min = today.getMinutes();
                let sec = today.getSeconds();
                let string_time =zero(hour)+":"+zero(min)+":"+zero(sec);
                let el_time = document.querySelector('#att_timer_time');
                putTimer(string_time, el_time);
            }
            function putTimer(string, el){
                el.innerHTML = string;
            }
            let today = new Date();
            let month = today.getMonth()+1;
            let date = today.getDate();

            dateTimer();
            timeTimer();
            setInterval(timeTimer, 1000);


        });
    });
</script>
</body>
</html>
