<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <link rel="stylesheet" href="../css/approval/appr_select_list.css ">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<jsp:include page="../common/header.jsp" flush="false"/>
<main>
    <jsp:include page="../common/menu_left.jsp" flush="false"/>
        <div class="main_right_area">
            <div class="main_container">
                <div class="choose_main">
                    <div class="cho_main_a">
                        <%--                option m -> 내 결제 상신함, a -> 결재해야할문서--%>
                        <a href="/approval/selected?option=A"><button>올린문서</button></a>
                        <a href="/approval/selected?option=M"><button>결재 해야할문서</button> </a>
                    </div>
                </div>
            </div>
        </div>
    </main>
<script>
    $(document).ready(function (){

    })
</script>

</body>
</html>
