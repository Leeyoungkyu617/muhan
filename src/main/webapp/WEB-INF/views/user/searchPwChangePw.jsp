<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Muhan</title>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <link rel="stylesheet" href="<c:url value='../css/user/findPw2.css'/> ">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>

    <style>
        body {
            margin: 0;
            font-family: 'IBMPlexSansKR-Regular';
            overflow: hidden;
        }
        a {
            color: inherit;
            text-decoration: none;
        }
        ul {
            padding: 0;
            margin: 0;
            list-style: none;
        }
        .center {
            width: 80%;
            margin: 0 auto;
        }
        @font-face {
            font-family: 'IBMPlexSansKR-Regular';
            src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/IBMPlexSansKR-Regular.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
        /*============================================================== */

    </style>
</head>

<body>
<%--<jsp:include page="common/header.jsp" flush="false"/>--%>

<main>
    <div class="main_right_area">
        <div class="changePW_container">
            <form action="" id="frm">
                <div class="title">새 비밀번호 입력</div>
                <div id="msg" class="msg"></div>

                <div class="changePw_form">
                    <div class="change_pw">
                        <input type="hidden" name="user_num" value="${user_num}">

                        <label for="">새 비밀번호</label>
                        <input class="txt newPw" type="password" id="newPw" name="user_pw" placeholder="8자리 이상" value="">
                    </div>
                    <div class="change_pw">
                        <label for="">새 비밀번호 확인</label>
                        <input class="txt newPwChk" type="password" id="newPwChk" placeholder="8자리 이상" value="">
                    </div>
                </div>
                <div class="btn_box">
                    <button id="btn_check" class="btn_save">변경</button>
                    <button class="btn_cancle" id="btn_cancle_go_login">취소</button>
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

            if($('#newPw').val().length < 8) {
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
                frm.attr("action", "<c:url value='/changePw/save'/>")
                frm.attr("method", "POST");

                frm.submit();
            }
            else{
                alert("취소되었습니다.");
            }
        });
        $('#btn_cancle_go_login').on('click',function(){
            let frm = $("#frm");
            frm.attr("action", "<c:url value='/login'/>")
            frm.attr("method", "get");

            frm.submit();
        })
    });
</script>
</html>
