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
                <small>목록페이지</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 게시판</a></li>
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

                    <%--게시글 목록 영역--%>
                    <div class="box-body">
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
                                <%--<td><a href="${path}/board/read?bno=${boardVO.bno}">${boardVO.title}</a></td>--%>
                                <td><a href="${path}/board/readPage${pageMaker.makeQuery(pageMaker.criteria.page)}&bno=${boardVO.bno}">${boardVO.title}</a></td>
                                <td>${boardVO.writer}</td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}"/></td>
                                <td><span class="badge bg-aqua">${boardVO.viewcnt}</span></td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <%--페이징영역--%>
                    <div class="box-footer">

                        <div class="text-center">
                            <ul class="pagination">

                                <%--prev가 true 일 경우--%>
                                <c:if test="${pageMaker.prev}">
                                    <%--<li><a href="${path}/board/listPage?page=${pageMaker.startPage-1}">&laquo;</a></li>--%>

                                    <%--UriComponents를 사용--%>
                                    <%--<li><a href="${path}/board/listPage${pageMaker.makeQuery(pageMaker.startPage-1)}">&laquo;</a></li>--%>

                                    <%--javascript 사용--%>
                                    <li><a href="${pageMaker.startPage - 1}">&laquo;</a></li>
                                </c:if>

                                <%--시작 페이지 ~ 마지막 페이지까지 반복--%>
                                <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">

                                    <%--현재 목록 페이지가 idx와 일치하면 현재페이지 표시--%>
                                    <li <c:out value="${pageMaker.criteria.page == idx? 'class=active':''}"/>>
                                        <%--<a href="${path}/board/listPage?page=${idx}">${idx}</a>--%>

                                        <%--UriComponents를 사용--%>
                                        <%--<a href="${path}/board/listPage${pageMaker.makeQuery(idx)}">${idx}</a>--%>

                                        <%--javascript 사용--%>
                                        <a href="${idx}">${idx}</a>
                                    </li>

                                </c:forEach>

                                <%--next가 true이고 endPage가 0이상일 경우--%>
                                <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                                    <%--<li><a href="${path}/board/listPage?page=${pageMaker.endPage + 1}">&raquo;</a></li>--%>

                                    <%--UriComponents를 사용--%>
                                    <%--<li><a href="${path}/board/listPage${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a></li>--%>

                                    <%--javascript 사용--%>
                                    <li><a href="${pageMaker.endPage +1}">&raquo;</a></li>
                                </c:if>

                            </ul>
                        </div>

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

    <%--javascript를 이용한 링크처리 form 태그--%>
    <form id="linkForm">
        <input type="hidden" name="page" value="${pageMaker.criteria.perPageNum}">
        <input type="hidden" name="perPageNum" value="${pageMaker.criteria.perPageNum}">
    </form>

</div>
<%-- ./wrapper --%>

<%--plugin_js.jsp--%>
<%@ include file="../include/plugin_js.jsp" %>
<script>

    var result = "${msg}";

    if (result == "INSERT") {
        alert("게시글이 등록되었습니다.");
    } else if(result == "MODIFY") {
        alert("게시글이 수정되었습니다.");
    } else if(result == "DELETE") {
        alert("게시글이 삭제되었습니다.");
    }

    $(".pagination li a").on("click", function (event) {

        event.preventDefault();

        var targetPage = $(this).attr("href");
        var linkForm = $("#linkForm");
        linkForm.find("[name='page']").val(targetPage);
        linkForm.attr("action", "/board/listPage").attr("method", "get");
        linkForm.submit();

    });
</script>
</body>
</html>