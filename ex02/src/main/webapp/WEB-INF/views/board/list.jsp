<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<%--head.jsp--%>
<%@ include file="../include/head.jsp" %>

<body class="hold-transition skin-blue sidebar-mini layout-boxed">
<div class="wrapper">

    <%--main_header.jsp--%>
    <%-- Main Header --%>
    <%@ include file="../include/main_header.jsp" %>

    <%--left_column.jsp--%>
    <%-- Left side column. contains the logo and sidebar --%>
    <%@ include file="../include/left_column.jsp" %>
    <%-- Content Wrapper. Contains page content --%>
    <div class="content-wrapper">
        <%-- Content Header (Page header) --%>
        <section class="content-header">
            <h1>
                게시판 예제
                <small>일반적인 목록페이지</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 게시판</a></li>
                <li class="active">일반</li>
                <li class="active">전체목록</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">

            <div class="col-lg-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">게시글 목록</h3>
                    </div>
                    <div class="box-body">
                        <%--게시글 목록 영역--%>
                        <div>
                            <table class="table table-bordered">
                                <tbody>
                                <tr>
                                    <th style="width: 10px">NO</th>
                                    <th>제목</th>
                                    <th style="width: 100px">작성자</th>
                                    <th style="width: 150px">작성시간</th>
                                    <th style="width: 50px">조회</th>
                                </tr>
                                <c:forEach var="boardVO" varStatus="i" items="${list}">
                                    <tr>
                                        <td>${boardVO.bno}</td>
                                        <td><a href="${path}/board/read?bno=${boardVO.bno}">${boardVO.title}</a></td>
                                        <td>${boardVO.writer}</td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}"/></td>
                                        <td><span class="badge bg-aqua">${boardVO.viewcnt}</span></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <%--페이징 처리 영역--%>
                        <div class="text-center">
                            <ul class="pagination">
                                <c:if test="${pageMaker.prev}">
                                    <li>
                                        <a href="${path}/board/list?page=${pageMaker.startPage - 1}">&laquo;</a>
                                    </li>
                                </c:if>
                                <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                                    <li <c:out value="${pageMaker.criteria.page == idx? 'class=active':''}"/>>
                                        <a href="${path}/board/list?page=${idx}">${idx}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                                    <li>
                                        <a href="${path}/board/list?page=${pageMaker.endPage + 1}">&raquo;</a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                    <div class="box-footer">

                        <%--검색 기능 추가 영역--%>

                    </div>
                </div>
            </div>

        </section>
        <%-- /.content --%>
    </div>
    <%-- /.content-wrapper --%>

    <%--main_footer.jsp--%>
    <%-- Main Footer --%>
    <%@ include file="../include/main_footer.jsp" %>

</div>
<%-- ./wrapper --%>

<%--plugin_js.jsp--%>
<%@ include file="../include/plugin_js.jsp" %>
<script>

    var result = "${msg}";

    // 게시글 등록, 삭제 알림
    if (result == "INSERTED") {
        alert("게시글이 등록되었습니다.");
    } else if(result == "REMOVED") {
        alert("게시글이 삭제되었습니다.");
    }

</script>
</body>
</html>