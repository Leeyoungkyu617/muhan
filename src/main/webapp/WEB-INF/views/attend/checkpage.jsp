<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="userId" value="${ pageContext.request.getSession(false).getAttribute('user_num')==null ? '':pageContext.request.getSession(false).getAttribute('user_num') }" />
<c:set var ="att_start" value="${pageContext.request.getSession(false).getAttribute('att_start')}"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <link rel="stylesheet" href="<c:url value='/css/attend/checkpage.css'/> ">
</head>
<body>
    <jsp:include page="../common/header.jsp" flush="false"/>

    <main>
        <jsp:include page="../common/menu_left.jsp" flush="false"/>
        <div class="att_container">
            <h2>근태관리</h2>
            <div class="att_left_sec">
                <div id="att_timer_now_box">
                    <h3>현재 시간</h3>
                    <div id="att_timer_date"></div>
                    <div id="att_timer_time"></div>
                </div>
                <div id="att_attend_checker_box">
                    <h3>
                        출·퇴근 관리
                    </h3>
                    <form action="" id="att_btn_box">
                        <button type="button" id="att_btn_start" disabled>출근</button>
                        <div id="att_chked_start">
                            <fmt:formatDate pattern="HH:mm:ss" value="${att_start}"/>
                        </div>
                        <c:choose>
                            <c:when test="${empty todayDto.att_end}">
                                <button type="button" id="att_btn_end">퇴근</button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" id="att_btn_end" disabled>퇴근</button>
                            </c:otherwise>
                        </c:choose>

                        <div id="att_chked_end">
                            <fmt:formatDate pattern="HH:mm:ss" value="${todayDto.att_end}"/>
                        </div>
                    </form>
                </div>
                <div id="att_week_work_time_box">
                    <h3>금주 근로시간: ${wk_availtime}시간</h3>
                    <div class="att_week_work_time">
                        <progress id="att_progress_bar" value="${totalWorkTime}" min="0" max="${wk_availtime}"></progress>
                        <div>지금까지의 근로시간은<br>총 ${totalWorkTime}시간입니다.</div>
                    </div>
                </div>
            </div>
            <div class="att_right_sec">
                <table>
                    <thead>
                        <tr>
                            <th class="att_date">날짜</th>
                            <th class="att_start">출근시간</th>
                            <th class="att_end">퇴근시간</th>
                            <th class="att_chk">처리<br/>상태</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="AttendDto" items="${list}">
                        <tr>
                            <td class="att_date">
                                <fmt:formatDate value="${AttendDto.att_reg_date}" pattern="MM/dd" type="date"/>
                            </td>
                            <td class="att_start">
                                <fmt:formatDate value="${AttendDto.att_start}" pattern="HH:mm:ss" type="date"/>
                            </td>
                            <td class="att_end">
                                <fmt:formatDate value="${AttendDto.att_end}" pattern="HH:mm:ss" type="date"/>
                            </td>
                            <td class="att_chk">${AttendDto.att_chk_name}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="att_pagination">
                    <c:if test="${ph.show_pre_btn}">
                        <a href="<c:url value='/attend/checkpage?page_now=${ph.start_page - 1}&page_size=${ph.page_size}'/>">이전</a>
                    </c:if>
                    <c:forEach var="i" begin="${ph.start_page}" end="${ph.end_page}">
                        <a href="<c:url value='/attend/checkpage?page_now=${i}&page_size=${ph.page_size}&nav_page=${ph.nav_page}'/>" class="${i==ph.page_now?"att_pageActive":""}">${i}</a>
                    </c:forEach>
                    <c:if test='${ph.show_next_btn}'>
                        <a href="<c:url value='/attend/checkpage?page_now=${ph.end_page + 1}&page_size=${ph.page_size}'/>">다음</a>
                    </c:if>

                </div>
                <%--pagination 숫자는 forEach로 불러올 때 a태그로 감싸기--%>
            </div>
        </div>
        <script>
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

            $(document).ready(function(){
                $('#att_btn_end').click(function(){
                    $('#att_btn_box').attr("method", "POST");
                    $('#att_btn_box').attr("action", "<c:url value='/attend/updatetime'/>");
                    $("#att_btn_box").submit();
                })


                <%--$('#att_btn_end').click(function(){--%>
                <%--    location.href="<c:url value='/attend/updatetime'/>"--%>

                <%--})--%>

            })


        </script>
    </main>
</body>
</html>
