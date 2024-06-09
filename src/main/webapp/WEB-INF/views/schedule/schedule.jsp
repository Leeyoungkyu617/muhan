<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset='utf-8' />
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.13/index.global.min.js'></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <link rel="stylesheet" href="<c:url value='/css/schedule/schedule.css'/> ">

</head>
<body>
<jsp:include page="../common/header.jsp" flush="false"/>
<main>
    <jsp:include page="../common/menu_left.jsp" flush="false"/>
    <div class="main_right_area">
        <div class="schedule_wrap">
            <!-- 풀캘린더 -->
            <div class="calendar-wrap">
                <div id='calendar'></div>
            </div>

            <!-- 팝업 -->
            <div id="calendarPopup" class="popup">

                <div class = calendar_title>일정 등록</div>
                <form action="" method="post" id="scheduleForm">

                    <ul class="popup-form">
                        <li class="seq_hideen">
                            <label for="content">번호</label>
                            <input type="text" id="seq_id" name="sche_seq"  value="${ScheduleDto.sche_seq}">
                        </li>
                        <li>
                            <label for="content">일정</label>
                            <input type="text" id="content" name="sche_title" placeholder="일정을 입력하세요." value="${ScheduleDto.sche_title}">
                        </li>
                        <li>
                            <label for="datetimeStart">시작일</label>
                            <input type="datetime-local" id="datetimeStart" name="sche_start_time" class="datetimeStart" value="${ScheduleDto.sche_start_time}">
                        </li>
                        <li>
                            <label for="datetimeEnd">종료일</label>
                            <input type="datetime-local" id="datetimeEnd" name="sche_end_time" value="${ScheduleDto.sche_end_time}">
                        </li>
                        <li>
                            <label for="datetimeColor">배경색</label>
                            <input type="color" id="datetimeColor" name="sche_color" value="${ScheduleDto.sche_color}">
                        </li>
                        <li>
                            <label for="datetimeContents">내용</label>
                            <input type="text" id="datetimeContents" name="sche_contents" value="${ScheduleDto.sche_contents}">
                        </li>
                    </ul>
                    <div class="popup_btn_wrap">
                        <button type="button" id="btnPopupClose" class="btn-popup-close">닫기</button>
                        <button type="button" id="btnPopupSave" class="btn-popup-save" data-update="">저장</button>
                        <button type="button" id="btnPopupModify" class="btn-popup-modify">수정</button>
                        <button type="button" id="btnPopupDel" class="btn-popup-del">삭제</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>
</body>
<script>


document.addEventListener('DOMContentLoaded', function () {


    var request = $.ajax({
        url: '/scheduleAjax',
        type: 'GET',
        // dataType: "json"
    });

    request.done(function(data) {
        let data2=JSON.stringify(data)

        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            // 새로 넣은 코드
            displayEventTime: false, // 시간 표시 제거
            selectable: true, // 달력 셀 선택 활성화
            dayMaxEvents: true, // +more 표시
            // locale:'kr',
            editable: true,
            height:"100%",

            customButtons: { // 커스텀 버튼 생성 시 필요
                schedule_btn: { // 버튼이름 생성 -> headerToolbar에서 이 버튼이름으로 사용
                    text: '일정추가', // 버튼에 출력될 글자
                    click: function () { // 버튼 클릭 시 이벤트
                        popupOpen();
                    }
                }
            },
            initialView: 'dayGridMonth', // 초기에 보여질 달력 형태 : dayGridYear(일년치달력), dayGridMonth(한달치달력), dayGridWeek(한주치달력) 등
            headerToolbar: {
                // 요소 종류
                // title(현재날짜 월,/연도) prev,next버튼(이전달,다음달), today(오늘로이동),
                // dayGridYear, dayGridMonth, dayGridWeek, dayGridDay(해당 달력 형태로 변경 버튼)
                start: 'prev next', // 달력헤더 왼쪽에 넣을 요소
                center: 'title', // 달력헤더 가운데 넣을 요소
                end: 'today schedule_btn' // 달력헤더 오른쪽 넣을 요소
            },
            events: data , // 이벤트 정보가 담긴 배열




            dateClick: function (date_info) { // 날짜 칸 클릭 시 실행
                document.getElementById('content').value = "";
                document.getElementById('datetimeStart').value = date_info.dateStr + " 00:00:00";
                document.getElementById('datetimeEnd').value = "";
                document.getElementById('datetimeColor').value = "";
                document.getElementById('datetimeContents').value = "";
                popupOpen();

            },
            windowResize: function (arg) { // 브라우저 리사이즈 된 후 한 번 실행
            },
            eventClick: function (cal_click) { //추가한 스케줄 내용 클릭 함수
                let seq_id = cal_click.event.id;
                let titleStr = cal_click.event.title;
                let startStr = cal_click.event.startStr;
                startStr = startStr.slice(0, 10) + " " + startStr.slice(11, 19);
                let endStr = cal_click.event.endStr;
                endStr = endStr.slice(0, 10) + " " + endStr.slice(11, 19);
                let bg_color = cal_click.event.backgroundColor;
                let cal_content = cal_click.event.extendedProps.content;

                //
                // console.log("------------------------------------------여기서")
                // console.log("======================cal_content :" + cal_content);
                //
                // console.log("======================cal_click : " + JSON.stringify(cal_click));


                eventPopupOpen(seq_id, startStr, endStr, titleStr, bg_color, cal_content);



            }
        });
        calendar.render(); // 캘린더 그리기

    })

    request.fail(function( jqXHR, textStatus ) {
        alert( "Request failed: " + textStatus );
    });
        /****** 팝업이벤트 ******/

            // Func: 팝업 열기 함수
        const popupOpen = () => {

                document.getElementById('calendarPopup').classList.add('open');
                document.getElementById('btnPopupDel').classList.remove('open');
                document.getElementById('btnPopupModify').classList.remove('open');
                document.getElementById('btnPopupSave').dataset.update = "insert";

            }

        // Func: 내용 있는 일정 팝업 열기 함수
        const eventPopupOpen = (seq_id , startDate, endDate, datetitle, bg_color, cal_content) => {
            document.getElementById('calendarPopup').classList.add('open');

            if (seq_id) {
                document.getElementById('seq_id').value = seq_id;
            }
            if (startDate) {
                document.getElementById('datetimeStart').value = startDate;
            }

            if (endDate) {
                document.getElementById('datetimeEnd').value = endDate;
            }

            if (datetitle) {
                document.getElementById('content').value = datetitle;
            }

            if (bg_color) {
                document.getElementById('datetimeColor').value = bg_color;
            }

            if (cal_content) {
                document.getElementById('datetimeContents').value = cal_content;
            }


            // 저장 버튼 데이터셋 업데이트 설정
            document.getElementById('btnPopupSave').dataset.update = "update";

            // 삭제 버튼 보이게
            document.getElementById('btnPopupDel').classList.add('open');

            // 수정 버튼 보이게
            document.getElementById('btnPopupModify').classList.add('open')

        }

        // Event: 팝업 저장 버튼
        document.getElementById('btnPopupSave').addEventListener('click', (e) => {
            e.preventDefault(); //기본 동작 방지

            // let title = document.getElementById('content').value;
            // let c_start = document.getElementById('datetimeStart').value;
            // let c_end = document.getElementById('datetimeEnd').value;
            // let bg_color = document.getElementById('datetimeColor').value;
            // let c_content = document.getElementById('datetimeContents').value;
            // calendar.addEvent( // 이벤트 추가 함수
            //     {
            //         // id: 'new_1',
            //         title: title,
            //         start: c_start,
            //         end: c_end,
            //         backgroundColor: bg_color,
            //         content : c_content
            //     }
            // )

            let form = document.getElementById('scheduleForm');
            form.setAttribute('action','<c:url value="/schedule/save"/>');
            form.submit();

            popupClose();
        })

        // Event: 팝업 닫기 버튼
        document.getElementById('btnPopupClose').addEventListener('click', (e) => {
            e.preventDefault();
            popupClose();
        })

        // Event: 팝업 삭제 버튼
        document.getElementById('btnPopupDel').addEventListener('click', (e) => {
            e.preventDefault();
            let chk = confirm("일정 삭제 하시겠습니까?");
            if (chk) {
                // DB삭제 기능 연결
                let form = document.getElementById('scheduleForm');
                form.setAttribute('action','<c:url value="/schedule/delete"/>');
                form.submit();

                popupClose();
            }
        })

        // Event: 팝업 수정 버튼
        document.getElementById('btnPopupModify').addEventListener('click', (e) => {
            e.preventDefault();
            let chk = confirm("일정 수정 하시겠습니까?");
            if (chk) {
                // DB수정 기능 연결
                let form = document.getElementById('scheduleForm');
                form.setAttribute('action','<c:url value="/schedule/modify"/>');
                form.submit();

                popupClose();
            }
        })
        // Func: 팝업 버튼함수
        const popupClose = () => {
            document.getElementById('calendarPopup').classList.remove('open');
        }



    });


</script>
</html>
