<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <link rel="stylesheet" href="/css/approval/appr_boardList.css ">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<jsp:include page="../common/header.jsp" flush="false"/>
<main>
    <jsp:include page="../common/menu_left.jsp" flush="false"/>
    <div class="main_right_area">
        <div class="appr_container">
            <div class="appr_top">
                <h2 class="appr_title">
                    ${option=="A"?"결재올린문서":"결재해야할문서"}
                </h2>
                <c:if test="${param.option == 'A'}">
                    <div class="appr_write_btn">
                        <a href="appr_insert?option=${option}" class="appr_write_btn_a">전자결재</a>
                    </div>
                </c:if>
            </div>
            <div class="appr_body">
                <div class="appr_body_row">
                    <div class="appr_td appr_no">No.</div>
                    <div class="appr_td appr_title">제목</div>
                    <div class="appr_td appr_upper">승인자</div>
                    <div class="appr_td appr_chk">승인여부</div>
                    <div class="appr_td appr_time">시간</div>
                </div>
                <c:choose>
                    <c:when test="${empty list}">
                        <h1 class="no_approval">결재 내용이 없습니다.</h1>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="listDto" items="${list}">
                            <div class="appr_body_row">
                                <div class="appr_td appr_no">${listDto.apr_seq}</div>
                                <div class="appr_td appr_title">
                                        <a href="<c:url value='/approval/read?apr_seq=${listDto.apr_seq}&page_now=${ph.page_now}&page_size=${ph.page_size}&nav_page=${ph.nav_page}&option=${option}'/>">${listDto.apr_title}</a>
                                </div>
                                <div class="appr_td appr_upper">${listDto.user_name}</div>
                                <div class="appr_td appr_chk">
                                    <c:choose>
                                        <c:when test="${listDto.apr_chk==0}">상신</c:when>
                                        <c:when test="${listDto.apr_chk==1}">승인</c:when>
                                        <c:otherwise>반려</c:otherwise>
                                    </c:choose>
                                </div>
                                    <%--  추후 결제 내역 확인--%>
                                <div class="appr_td appr_time appr_chk_c">
                                    <c:set var="appr_date">
                                        <fmt:formatDate value="${listDto.apr_u_time}" pattern="yyyy-MM-dd"></fmt:formatDate>
                                    </c:set>
                                        ${appr_date}
                                </div>
                                <div class="appr_td appr_time appr_chk_m">
                                    <c:set var="appr_date">
                                        <fmt:formatDate value="${listDto.apr_p_time}" pattern="MM-dd"></fmt:formatDate>
                                    </c:set>
                                        ${appr_date}
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="appr_paging">
                <c:if test="${ph.show_pre_btn}">
                    <a href="<c:url value="/approval/selected?page=${ph.end_page -1}&pageSize=${ph.page_size}&option=${option}" />">[이전]</a>
                </c:if>
                <c:forEach var="i" begin="${ph.start_page}" end="${ph.end_page}">
                    <a href="<c:url value="/approval/selected?page_now=${i}&pageSize=${ph.page_size}&option=${option}" />" class="${i==page_now?"pageActive":""}">${i}</a>
                </c:forEach>
                <c:if test="${ph.show_next_btn}">
                    <a href="<c:url value="/approval/selected?page=${ph.end_page + 1}&pageSize=${ph.page_size}&option=${option}" />">[다음]</a>
                </c:if>
            </div>
        </div>
    </div>
</main>
</body>

<script>
    $(document).ready(function (){

    })

</script>
</html>