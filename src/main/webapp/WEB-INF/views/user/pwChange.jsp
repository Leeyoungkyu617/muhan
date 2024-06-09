<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Muhan</title>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <link rel="stylesheet" href="<c:url value='/css/user/pwChange.css'/> ">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<body>
<jsp:include page="../common/header.jsp" flush="false"/>
<main>
    <jsp:include page="../common/menu_left.jsp" flush="false"/>
    <div class="main_right_area">
        <div class="pw_C_container">
            <form action="" id="frm">
                <div class="title">비밀번호 변경</div>
                <div class="msg">
                    <c:if test="${not empty param.msg}">
                        <i class="fa fa-exclamation-circle"> 일치하는 회원정보가 없습니다.</i>
                    </c:if>
                </div>
<%--                <!-- 리다이렉트 msg2: <div class="msg"> ${ param.msg } </div>--%>
<%--                    인코딩 msg3: <div class="msg"> ${ URLDecoder.decode(param.msg, "utf-8") } </div> -->--%>
<%--                <!-- <div id="msg"></div> -->--%>
                <div class="pw_C_form">
                    <div class="pw_change">
                        <label for="">현재 비밀번호</label>
                        <input class="txt currentPw" type="password" name="current_pw" placeholder="8자리" value="">
                    </div>
                    <div class="pw_change">
                        <label for="">새 비밀번호</label>
                        <input class="txt newPw" id="newPw" type="password" name="new_pw" placeholder="8자리 이상" value="">
                    </div>
                    <div class="pw_change">
                        <label for="">새 비밀번호 확인</label>
                        <input class="txt newPwChk" id="newPwChk" type="password" placeholder="8자리 이상" value="">
                    </div>
                </div>
                <div class="btn_box">
                    <input type="button" id="btn_check"  value="변경" class="btn btn_save">
                    <a href="<c:url value='/mypageMain'/>">
                        <input type="button" onclick="cancle()" class="btn btn_cancle" value="취소">
                    </a>
                </div>
            </form>
        </div>
    </div>
</main>
</body>
<script>
    $(document).ready(function(){

        $('#btn_check').on('click', function(){

            var p1 = document.getElementById('newPw').value;
            var p2 = document.getElementById('newPwChk').value;

            console.log(p1)
            console.log(p2)
            if($('.currentPw').val().length < 8) {
                alert("pw의 길이는 8이상이어야 합니다.");
                $('.currentPw').focus();
                // return;
            }
            else if($('#newPw').val().length < 8) {
                alert("pw의 길이는 8이상이어야 합니다.");
                $('#newPw').focus();
                // return;
            }
            else if($('#newPwChk').val().length < 8) {
                alert("pw의 길이는 8이상이어야 합니다.");
                $('#newPwChk').focus();
                // return;
            }

            else if(p1 != p2) {
                alert("새로운 비밀번호가 일치하지 않습니다. 다시 입력해주세요.")
                return false;
            }
            else if(confirm("비밀번호를 변경하시겠습니까?")) {
                    alert("변경되었습니다.");
                    // window.location = 'mypageMain.html';
                    let frm = $("#frm");
                    frm.attr("action", "<c:url value='/pwChange/save'/>")
                    frm.attr("method", "POST");

                    frm.submit();
            }
            else{
                    alert("취소되었습니다.");
            }
        });
    });

    // 새로운 비밀번호 유효성 검사
    // function allCheck() {
    //
    //     if (formAllCheck.new_pw.value.length < 8) {
    //         alert("pw의 길이는 8이상이어야 합니다.", formAllCheck.new_pw);
    //         return false;
    //     }
    //     return true;
    // }
    // function call_confirm() {
    //     var p1 = document.getElementsByClassName('newPw').value;
    //     var p2 = document.getElementsByClassName('newPwChk').value;
    //     if(p1 != p2) {
    //         alert("새로운 비밀번호가 일치하지 않습니다. 다시 입력해주세요.")
    //         return false;
    //     }else {
    //         alert("변경되었습니다.")
    //         frm.submit();
    //     }
    // }
    // function cancle() {
    //     alert("취소되었습니다.")
    // }

    <%--function setMessage(msg, element){--%>
    <%--    document.getElementById("msg").innerHTML = ` ${'${msg}'}`;--%>

    <%--    if(element) {--%>
    <%--        element.select();--%>
    <%--    }--%>
    <%--}--%>

    // function call_confirm(){
    //     var p1 = document.getElementsById('newPw').value;
    //     var p2 = document.getElementsById('newPwChk').value;
    //
    //     if(confirm("비밀번호를 변경하시겠습니까?")){
    //
    //         alert("수정되었습니다.");
    //
    //
    //         frm.submit();
    //     }else{
    //         alert("취소되었습니다.");
    //     }
    //
    // }




    // 1. 새 비밀번호랑 새비밀번호확인이랑 같은지 확인하렴
    // 2. 이제 다오로갑니다(맨위에 현재비밀번호랑 세션에 있는 아이디랑 다오로 보냉 -> 둘이 맞는거 있는지 확인해->있으면 dto 에 담아서 리턴(컨트롤러까지-해당 유져가 없으면 null 담겨서 올거고, 있으면 뭐든 담겨서 올거임))
    // 3. null 이면 '현재 비번' 틀려습니다. 메세지 띄우고
    // 4. null 아니면 일치하는 회원 있다는 말이니까 update 하러 다오에 '새 비번' , 세션아이디 보내기


    // 변경버튼 누르면 컨펌창 띄우기
    // function call_confirm(){
    //
    //     if(confirm("비밀번호를 변경하시겠습니까?")){
    //         alert("변경되었습니다.");
    //         // window.location = 'mypageMain.html'
    //         frm.submit();
    //
    //     }else{
    //         alert("취소되었습니다.");
    //     }
    // }
</script>
</html>
