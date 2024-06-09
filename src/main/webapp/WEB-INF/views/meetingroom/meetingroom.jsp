<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value='../css/meetingroom/reservation.css'/> ">
    <script defer src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script defer src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<jsp:include page="../common/header.jsp" flush="false"/>
<main>
    <jsp:include page="../common/menu_left.jsp" flush="false"/>
    <div id="mtr_container">
        <h2 class="mtr_header">회의실 예약</h2>
        <div class="mtr_body">
            <div id="mtr_reserve_box">
                <div class="mtr_r_box_head">
                    <h3>회의실 예약하기</h3>
                    <form action="" id="mtr_form">
                        <input type="hidden" name="mr_time" id="mtr_time">
                        <div class="select">
                            <input type="radio" id="select" name="mtr_room_num" class="sel_room_num" value="1" checked><label class="label_sel_mr_room" for="select">101호</label>
                            <input type="radio" id="select2" name="mtr_room_num" value="2"><label class="label_sel_mr_room" for="select2">102호</label>
                            <input type="radio" id="select3" name="mtr_room_num" value="3"><label class="label_sel_mr_room" for="select3">103호</label>
                        </div>

                        <input type="text" name="meet_date" id="mtr_datepicker">

                        <%--                            <button id="mtr_btn_get_timetable">확인</button>--%>
                        <div class="meet_btn_box">
                            <button type="button" id="mtr_btn_get_timetable">검색</button>
                            <button type="button" id="mtr_btn_submit">확인</button>
                        </div>

                    </form>
                </div>
                <div id="mtr_timetable">
                    <input type="radio" id="mr_sel_time1" class="mtr_btn_sel_time" name="mr_time" value="1">
                    <label class="label_sel_mr_time" for="mr_sel_time1">9:00 am</label>
                    <input type="radio" id="mr_sel_time2" class="mtr_btn_sel_time" name="mr_time" value="2">
                    <label class="label_sel_mr_time" for="mr_sel_time2">10:00 am</label>
                    <input type="radio" id="mr_sel_time3" class="mtr_btn_sel_time" name="mr_time" value="3">
                    <label class="label_sel_mr_time" for="mr_sel_time3">11:00 am</label>
                    <input type="radio" id="mr_sel_time4" class="mtr_btn_sel_time" name="mr_time" value="4">
                    <label class="label_sel_mr_time" for="mr_sel_time4">12:00 pm</label>
                    <input type="radio" id="mr_sel_time5" class="mtr_btn_sel_time" name="mr_time" value="5">
                    <label class="label_sel_mr_time" for="mr_sel_time5">13:00 pm</label>
                    <input type="radio" id="mr_sel_time6" class="mtr_btn_sel_time" name="mr_time" value="6">
                    <label class="label_sel_mr_time" for="mr_sel_time6">14:00 pm</label>
                    <input type="radio" id="mr_sel_time7" class="mtr_btn_sel_time" name="mr_time" value="7">
                    <label class="label_sel_mr_time" for="mr_sel_time7">15:00 pm</label>
                    <input type="radio" id="mr_sel_time8" class="mtr_btn_sel_time" name="mr_time" value="8">
                    <label class="label_sel_mr_time" for="mr_sel_time8">16:00 pm</label>
                    <input type="radio" id="mr_sel_time9" class="mtr_btn_sel_time" name="mr_time" value="9">
                    <label class="label_sel_mr_time" for="mr_sel_time9">17:00 pm</label>
                    <input type="radio" id="mr_sel_time10" class="mtr_btn_sel_time" name="mr_time" value="10">
                    <label class="label_sel_mr_time" for="mr_sel_time10">18:00 pm</label>
                </div>
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function(){
            /* 초기 틀 */
            $('#mtr_timetable').css({"display":"none"});
            $("button").on("dblclick", function(e) {
                // 더블 클릭 이벤트를 취소합니다.
                e.preventDefault();
            });


            $('.label_sel_mr_room').click(function(){// 클릭한 라벨과 세트인 라디오에 특정 클래스 삽입하기
                $('input[name=mtr_room_num]').removeClass('sel_room_num');
                $(this).prev().addClass('sel_room_num');
            })


            /*데이트 픽커*/
            $("#mtr_datepicker").datepicker({
                showOn:"both",
                buttonImage:"http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
                buttonImageOnly:true,
                changeYear:true,
                changeMonth: true,
                dateFormat:"yy-mm-dd",
                dayNames : ['월요일','화요일','수요일','목요일','금요일','토요일','일요일'],
                dayNamesMin : ['월','화','수','목','금','토','일'],
                monthNamesShort:["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
                showMonthAfterYear:true,
                minDate:0,
                yearSuffix:"년"
            });
            let input_datepicker = $("#mtr_datepicker");
            let datepicker_o_top = input_datepicker.offset().top;
            let datepicker_o_left = input_datepicker.offset().left;
            $(window).resize(function(){
                datepicker_o_top = input_datepicker.offset().top;
                datepicker_o_left = input_datepicker.offset().left;

                $("#ui-datepicker-div").css({
                    "top":datepicker_o_top + input_datepicker.outerHeight(),
                    "left":datepicker_o_left
                })
            })
            $("#mtr_datepicker").datepicker("setDate", new Date());



            $('#mtr_btn_get_timetable').click(function(){
                for(let i=0;i<$('.mtr_btn_sel_time').length;i++){
                    $('.label_sel_mr_time').eq(i).css({"display":"inline-block"});
                }

                let mtr_room_num=$(".sel_room_num").val();
                let meet_date=$('#mtr_datepicker').val();
                let data = {"mtr_room_num":mtr_room_num,"meet_date":meet_date};

                $.ajax({
                    type: 'POST',
                    url: '/meetingroom',
                    headers : {
                        "content-type": "application/json"
                    },
                    // url: '/',
                    data : JSON.stringify(data),
                    success: function(response){
                        $('#mtr_timetable').css({"display":"block"});
                        for(let i=0;i<$('.mtr_btn_sel_time').length;i++){
                            for(let j=0;j<response.length;j++){
                                if(response[j].mr_time == $('.mtr_btn_sel_time').eq(i).val()){
                                    $('.label_sel_mr_time').eq(i).css({"display":"none"});
                                }
                            }

                        }

                        },
                    error:function(request, status, error){
                        console.log(request.status);
                        console.log(request.responseText);
                        console.log(error);
                    }
                });

                // 시간 선택하면 -> 히든에 밸류값 넣어줌.
                /* 라벨을 클릭하면 라벨의 for 값(id 요소)을 가져와서.
                * 클릭과 연동된 라디오의 val()을
                * hidden input의 val() 에 넣어준다. */

            $('.label_sel_mr_time').click(function(){
                let el = $(this).prev();
                $('#mtr_time').val(el.val());
                $('.label_sel_mr_time').removeClass('selected_mr_time');
                $(this).addClass('selected_mr_time');
            })

            $('#mtr_btn_submit').click(function(){
                // let mtr_room_num=$(".sel_room_num").val();
                // let meet_date=$('#mtr_datepicker').val();
                let mr_time=$('#mtr_time').val();


                if(mr_time.length != 0){
                    var chkConfirm = confirm("회의실을 예약하시겠습니까?");
                    if(chkConfirm) {
                        $('#mtr_form').attr("action", "/reserve/save");
                        $('#mtr_form').attr("method", "POST");
                        $('#mtr_form').submit();
                        return;
                    }

                }

            });
            })






            // $('.mtr_btn_sel_time').click(function(){
            //     // let tmp_sel = $(this).data("time");
            //     $('#mtr_time').val($(this).val());
            //
            //     var chkConfirm = confirm("회의실을 예약하시겠습니까?");
            //     if(chkConfirm){
            //         alert("예약성공");
            //         $(this).addClass('mtr_blur');
            //         location.href="/reserve/save";
            //     }
            //     else {
            //         alert("예약을 취소하였습니다.");
            //     }
            // });

        })
    </script>

















        <%--  *************************붙여넣기끝붙여넣기끝붙여넣기끝붙여넣기끝붙여넣기끝붙여넣기끝*************************  --%>
    </div>
</main>
</body>
</html>
