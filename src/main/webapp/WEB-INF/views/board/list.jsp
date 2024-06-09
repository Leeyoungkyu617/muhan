<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="userId" value="${ pageContext.request.getSession(false).getAttribute('user_num')==null ? '':pageContext.request.getSession(false).getAttribute('user_num') }" />
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <link rel="stylesheet" href="<c:url value='/css/board/boardList.css'/> ">
</head>
<body>

<jsp:include page="../common/header.jsp" flush="false"/>

<main>
    <jsp:include page="../common/menu_left.jsp" flush="false"/>

    <div class="brd_container">
        <div class="brd_header">
            <h2 class="brd_header_title">공지사항</h2>
            <c:if test="${user_dept == 12}">
                <div id="brd_write_btn"><a href="<c:url value='/board/write'/>">글쓰기</a></div>
            </c:if>
        </div>
        <table class="brd_body">
            <thead>
            <tr>
                <td class="brd_index">no.</td>
                <td class="brd_class">분류</td>
                <td class="brd_title">제목</td>
                <td class="brd_reg_date">등록일</td>
                <td class="brd_vcount">조회수</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="boardDto" items="${listNotice}">
                <tr>
                    <td class="brd_index">${boardDto.board_seq}</td>
                    <td class="brd_class brd_notice"><span>${boardDto.board_class_name}</span></td>
                    <td class="brd_title"><a href="<c:url value="/board/read?board_seq=${boardDto.board_seq}&page_now=${ph.page_now}&page_size=${ph.page_size}&nav_page=${ph.nav_page}"/>" class="brd_notice_title">${boardDto.board_title}</a></td>
                    <td class="brd_reg_date">
                        <c:set var="today" value="<%= new java.util.Date()%>"/>
                        <c:set var="date">
                            <fmt:formatDate value="${today}" pattern="yyyy-MM-dd" type="date"/>
                        </c:set>
                        <c:set var="date2">
                            <fmt:formatDate value="${boardDto.board_date}" pattern="yyyy-MM-dd" type="date"/>
                        </c:set>
                        <c:choose>
                            <c:when test="${date==date2}">
                                <fmt:formatDate value="${boardDto.board_date}" pattern="HH:mm" type="date"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:formatDate value="${boardDto.board_date}" pattern="yyyy-MM-dd" type="date"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="brd_vcount">${boardDto.board_view_count}</td>
                </tr>
            </c:forEach>
            <c:forEach var="boardDto" items="${list}">
                <tr>
                    <td class="brd_index">${boardDto.board_seq}</td>
                    <td class="brd_class"><span>${boardDto.board_class_name}</span></td>
                    <td class="brd_title"><a href="<c:url value="/board/read?board_seq=${boardDto.board_seq}&page_now=${ph.page_now}&page_size=${ph.page_size}&nav_page=${ph.nav_page}"/>" class="brd_notice_title">${boardDto.board_title}</a></td>
                    <td class="brd_reg_date">
                        <c:set var="today" value="<%= new java.util.Date()%>"/>
                        <c:set var="date">
                            <fmt:formatDate value="${today}" pattern="yyyy-MM-dd" type="date"/>
                        </c:set>
                        <c:set var="date2">
                            <fmt:formatDate value="${boardDto.board_date}" pattern="yyyy-MM-dd" type="date"/>
                        </c:set>
                        <c:choose>
                            <c:when test="${date==date2}">
                                <fmt:formatDate value="${boardDto.board_date}" pattern="HH:mm" type="date"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:formatDate value="${boardDto.board_date}" pattern="yyyy-MM-dd" type="date"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="brd_vcount">${boardDto.board_view_count}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="brd_pagination">
            <%-- c:if로 show_pre_btn / show_next_btn 보일지 여부 결정하기--%>
            <c:if test="${ph.show_pre_btn}">
                <a href="<c:url value='/board/list?page_now=${ph.start_page - 1}&page_size=${ph.page_size}'/>">이전</a>
            </c:if>
            <c:forEach var="i" begin="${ph.start_page}" end="${ph.end_page}">
                <a href="<c:url value='/board/list?page_now=${i}&page_size=${ph.page_size}&nav_page=${ph.nav_page}'/>" class="${i==ph.page_now?"brd_pageActive":""}">${i}</a>
            </c:forEach>
            <c:if test='${ph.show_next_btn}'>
                <a href="<c:url value='/board/list?page_now=${ph.end_page + 1}&page_size=${ph.page_size}'/>">다음</a>
            </c:if>
        </div>
    </div>
</main>
<script>
    $(document).ready(function(){
        $('#brd_write_btn').click(function(){
            location.href="<c:url value="/board/write"/>";
        })
    })
</script>
</body>
</html>
