<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>결제상신</title>
    <link rel="stylesheet" href="../css/approval/appr_board.css ">
</head>
<body>
<jsp:include page="../common/header.jsp" flush="false"/>
<main>
    <jsp:include page="../common/menu_left.jsp" flush="false"/>
    <div class="main_right_area">
        <div class="appr_insert_container">
            <h2 class="insert_title">결제${mode =="new"?"상신":option=="M"?"하기":"수정"}</h2>

            <div class="appr_target">
                <div class="appr_target_left">
                    <div class="appr_team_name">${appr_dto.upper_level_name}</div>
                    <div class="appr_user_name">${appr_dto.upper_user_name}</div>
                </div>
                <c:if test="${mode == 'new'}">
                    <div class="appr_target_btn">
                        <button class="appr_select appr_btn" id="appr_select">결재선</button>
                    </div>
                </c:if>
            </div>

            <form action="" id="appr_frm">
                <input type="hidden" name="apr_seq" id="apr_seq" value="<c:if test="${!empty appr_dto}">${appr_dto.apr_seq}</c:if>">
                <input type="hidden" name="apr_upper" id="apr_upper" value="<c:if test="${!empty appr_dto}">${appr_dto.apr_upper}</c:if>">
                <div class="apr_div_title">
                    <div class="write_title">
                        <div class="div_left">제목</div>
                        <div class="div_right">
                            <input type="text" class="title_intput" name="apr_title" id="apr_title" value="<c:if test="${not empty appr_dto}">${appr_dto.apr_title}</c:if>"${param.option=="A"?(mode=="new"?"":(appr_dto.apr_chk==0?"":"readonly")):"readonly"}>
                        </div>

                    </div>
                    <div class="notmodify_div">
                        <div class="write_writer">
                            <div>작성자 :: </div>
                            <div>
                                ${appr_dto != null ? appr_dto.writer_name:user_name}
                            </div>
                            <input type="hidden" name="apr_writer" id="apr_writer"  value="${appr_dto != null ?appr_dto.apr_writer:user_num}">
                        </div>
                        <div class ="write_date">
                            <div>작성일 :: </div>
                            <div>
                                <c:set var="now" value="<%=new java.util.Date()%>"/>
                                <fmt:formatDate value="${now}" pattern="yyyy-MM-dd" type="date" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="apr_div_contents">
                    <textarea name="apr_contents" id="apr_contents" ${param.option=="A"?(mode=="new"?"":(appr_dto.apr_chk==0?"":"readonly")):"readonly"}><c:if test="${!empty appr_dto}">${appr_dto.apr_contents}</c:if></textarea>
                </div>
<%--                <div class="appr_files">--%>
                <c:choose>
                    <c:when test="${mode=='new'}">
                        <input type="file" name="f_file" multiple="multiple">
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${not empty appr_dto.apr_ori_attach}">
                                <div class="post_file_attached">${appr_dto.apr_ori_attach}</div>
                                <input type="hidden" value="${appr_dto.apr_attach}" name="apr_attach">
                                <input type="hidden" value="${appr_dto.apr_ori_attach}" name="apr_ori_attach">
                                <input type="file" name="f_file" multiple="multiple">
                            </c:when>
                            <c:otherwise>
                                <input type="file" name="f_file" multiple="multiple">
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>

<%--                </div>--%>
                <div class="select_btn">
                    <c:if test="${param.option =='A'}">
                        <div class="appr_target_btn">
                            <c:if test="${mode == 'new'}">
                                <button type="button" class="appr_btn" id="wrtie_btn">상신</button>
                            </c:if>
                            <c:if test="${mode == 'modify' && appr_dto.apr_chk == 0}">
                                <button type="button" class="appr_btn" id="modify_btn">수정</button>
                            </c:if>
                        </div>
                    </c:if>
                    <c:if test="${param.option == 'M' && appr_dto.apr_chk == 0 && mode == 'modify'}">
                        <div class="appr_target_btn">
                            <button type="button" class="appr_btn" id="accept_btn">승인</button>
                        </div>
                        <div class="appr_target_btn">
                            <button type="button" class="appr_btn" id="reject_btn">반려</button>
                        </div>
                    </c:if>


<%--                    수정이면서 상신상태일 경우에만 삭제 가능--%>
                    <c:if test="${mode == 'modify' && appr_dto.apr_chk == 0 && param.option=='A' }">
                        <div class="appr_target_btn">
                            <button type="button" class="appr_btn" id="delete_btn">삭제</button>
                        </div>
                    </c:if>
                    <div class="appr_target_btn">
                        <button type="button" class="appr_btn" id="return_btn" >취소</button>
                    </div>

                </div>
            </form>
        </div>
        <div class="black_area"></div>
        <div class="chk_approval">
            <p>결재대상 고르기</p>
            <div class="chk_approval_nav" >
                <div class="chk_approval_left chk_approval_common">
                    <div class="chk_approval_select">결재자 선택</div>
                        <div class="chk_approval_group">
                            <div class="chk_approval_department">부서명</div>
                            <div class="employee_group select_box2">
                                <c:forEach var="target" items="${dept_peoples}">
                                    <div class="employye"><span class="emplo_span">└${target.user_name} ${target.level_name}</span></div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                <div class="button_area">
                    <div class="approval_btn btn_right"> &gt;</div>
                    <div class="approval_btn btn_left">&lt;</div>
                </div>
                <div class="chk_approval_righ chk_approval_common">
                    <div class="chk_approval_select">선택한 결재자</div>
                        <div class="chk_approval_group">
                            <div class="chk_approval_department">부서명</div>
                            <div class="employee_group select_box">
                                <c:forEach var="target" items="${dept_peoples}">

                                    <div class="employye2">
                                        <div class="hidden_target">${target.user_num}</div>
                                        └
                                        <span class="user_name">${target.user_name}</span>
                                        <span class="level_name">${target.level_name}</span>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                </div>
            </div>
            <div class="select_target_btn">
                <button class="select_target appr_btn_select">선택</button>
                <button class="select_target appr_btn_close">취소</button>
            </div>
        </div>
    </div>
</main>



<script>
    $(document).ready(function() {

        if (${mode != "new"}){
            $('.appr_target_left').css('display','block');

        }


            // 메인 화면 높이
            const main_height = $('.appr_insert_container').height();
        const main_width = $('.appr_insert_container').width();
        $('.appr_select').on('click', function () {
            // 가리는거 높이 구하기
            $('.black_area').css('height', main_height);

            // 기존 결재상신 화면 클릭막기
            $('.appr_insert_container').addClass('un_click');

            $('.black_area').css('display', 'block');
            $('.chk_approval').css('display', 'block');


            $('.chk_approval').css({
              "height":main_height/2,
                "width" : main_width/2
            })



            $('.select_box2 .employye').on('click', function() {
                $('.select_box2 .employye .emplo_span').removeClass('ckd_color');
                $(this).children('.emplo_span').addClass('ckd_color');
            });


            $('.btn_right').on('click', function() {
                var ttt = $('.select_box2 .employye .ckd_color').parent();
                if (ttt.length > 0) {
                    var index = $('.select_box2 .employye').index(ttt);
                    $('.employye2').css('display', 'none');
                    $('.employye2').eq(index).css('display', 'block');

                }
            });

            $('.btn_left').on('click', function() {
                var ttt = $('.select_box2 .employye .ckd_color').parent();
                if (ttt.length > 0) {
                    var index = $('.select_box2 .employye').index(ttt);
                    $('.employye2').css('display', 'none');
                }
            });

            $('.appr_btn_select').on('click', function () {
                $('.appr_target_left').css('display','block');
                var ttt = $('.select_box2 .employye .ckd_color').parent();
                if (ttt.length > 0) {
                    var index = $('.select_box2 .employye').index(ttt);



                    $('.appr_user_name').text($('.user_name').eq(index).text());
                    $('.appr_team_name').text($('.level_name').eq(index).text());
                    $('#apr_upper').val(Number($('.hidden_target').eq(index).text()));

                // 히든 apr_upper에 값 넣어주기
                <%--    $('#apr_upper').value(${dept_peoples[index].user_num});--%>

                }
            });

        });




            // 선택 완료 혹은 취소 후 원상 복귀
            $('.select_target').on('click', function () {
                $('.appr_insert_container').removeClass('un_click'); // Corrected this line
                $('.black_area').css('display', 'none');
                $('.chk_approval').css('display', 'none');
            });
        });

        // 취소를 눌럿을때 리스페이지로 이동하기
        $('#return_btn').on('click', function () {
            let tmp_param = get_uri_info();
            location.href = "/approval/selected?" + tmp_param;
        });

        // 상신 눌럿을 때 이동
        $('#wrtie_btn').on('click', function () {
            //유효성 검사 - writer, upper, title, contents  //어퍼 추후
            if ($('#apr_title').val().trim() == "") {
                alert("제목을 입력할래요?");
                $('#apr_title').focus();
                return;
            } else if ($('#apr_contents').val().trim() == "") {
                alert("내용을 입력할래요?");
                $('#apr_contents').focus();
                return;
            }else if($('#apr_upper').val().trim() == ""){
                alert("결재자 선택 할래요?");
            }else {
                let frm = $("#appr_frm");
                frm.attr("action", "<c:url value='/approval/write'/>")
                frm.attr("method", "post");
                frm.attr('enctype','multipart/form-data');
                frm.submit();
            }
        });

        // 수정 눌럿을때
        $('#modify_btn').on('click', function () {
            //유효성 검사 - writer, upper, title, contents  //어퍼 추후
            if ($('#apr_title').val().trim() == "") {
                alert("제목을 입력할래요?");
                $('#apr_title').focus();
                return;
            } else if ($('#apr_contents').val().trim() == "") {
                alert("내용을 입력할래요?");
                $('#apr_contents').focus();
                return;
            }else if($('#apr_upper').val().trim() == ""){
                alert("결재자 선택 할래요?");
            }else {
                let frm = $("#appr_frm");
                frm.attr("action", "<c:url value='/approval/modify'/>")
                frm.attr("method", "post");
                frm.attr('enctype','multipart/form-data');
                frm.submit();
            }
        });

        $('#accept_btn').on('click', function(){
            if (!confirm("승인하실래요????")) return;
            location.href="<c:url value='/approval/accept_is_not?check=1&apr_seq=${appr_dto.apr_seq}'/>";
        });

        //반려
        $('#reject_btn').on('click', function(){
            if (!confirm("반려하실래요????")) return;
            location.href="<c:url value='/approval/accept_is_not?check=2&apr_seq=${appr_dto.apr_seq}'/>";
        });




        $('#delete_btn').on('click', function (){
            // 삭제유무 확인
            if(!confirm("삭제하시겠습니까?")) return;
            let form = $("#appr_frm");
            form.attr("action", "<c:url value='/approval/remove'/>?")
            form.attr("method", "post");
            // form.attr("method", "get");
            form.submit();

        })
        function get_uri_info() {
            const searchParam = new URLSearchParams(location.search);
            let tmp_param = "";
            for (const param of searchParam) {
                tmp_param += param[0] + "=" + param[1] + "&";
            }
            //마지막 & 지우기
            if (tmp_param.charAt(tmp_param.length - 1) == "&") {
                tmp_param = tmp_param.substring(0, tmp_param.length - 1);
            }
            return tmp_param;
        }



</script>

</body>
</html>
