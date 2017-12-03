<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<%--head.jsp--%>
<%@ include file="../../include/head.jsp" %>

<body class="hold-transition skin-blue sidebar-mini layout-boxed">
<div class="wrapper">

    <%--main_header.jsp--%>
    <%-- Main Header --%>
    <%@ include file="../../include/main_header.jsp" %>

    <%--left_column.jsp--%>
    <%-- Left side column. contains the logo and sidebar --%>
    <%@ include file="../../include/left_column.jsp" %>
    <%-- Content Wrapper. Contains page content --%>
    <div class="content-wrapper">
        <%-- Content Header (Page header) --%>
        <section class="content-header">
            <h1>
                게시판 예제
                <small>페이징 처리된 목록페이지</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 게시판</a></li>
                <li class="active">페이징</li>
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
                                <td><a href="${path}/board/paging/read${pageMaker.makeQuery(pageMaker.criteria.page)}&bno=${boardVO.bno}">${boardVO.title}</a></td>
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

                                <%--========================일반적인 페이징 링크처리========================--%>

                                <%--&lt;%&ndash;prev가 true 일 경우&ndash;%&gt;--%>
                                <%--<c:if test="${pageMaker.prev}">--%>
                                    <%--<li><a href="${path}/board/paging/list?page=${pageMaker.startPage-1}">&laquo;</a></li>--%>
                                <%--</c:if>--%>

                                <%--&lt;%&ndash;시작 페이지 ~ 마지막 페이지까지 반복&ndash;%&gt;--%>
                                <%--<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">--%>
                                    <%--&lt;%&ndash;현재 목록 페이지가 idx와 일치하면 현재페이지 표시&ndash;%&gt;--%>
                                    <%--<li <c:out value="${pageMaker.criteria.page == idx? 'class=active':''}"/>>--%>
                                        <%--<a href="${path}/board/paging/list?page=${idx}">${idx}</a>--%>
                                    <%--</li>--%>
                                <%--</c:forEach>--%>

                                <%--&lt;%&ndash;next가 true이고 endPage가 0이상일 경우&ndash;%&gt;--%>
                                <%--<c:if test="${pageMaker.next && pageMaker.endPage > 0}">--%>
                                    <%--<li><a href="${path}/board/paging/list?page=${pageMaker.endPage + 1}">&raquo;</a></li>--%>
                                <%--</c:if>--%>

                                <%--========================UriComponentBuilder를 사용한 페이징 링크 처리========================--%>

                                <%--&lt;%&ndash;prev가 true 일 경우&ndash;%&gt;--%>
                                <%--<c:if test="${pageMaker.prev}">--%>
                                    <%--&lt;%&ndash;UriComponents를 사용&ndash;%&gt;--%>
                                    <%--<li><a href="${path}/board/paging/list${pageMaker.makeQuery(pageMaker.startPage-1)}">&laquo;</a></li>--%>
                                <%--</c:if>--%>

                                <%--&lt;%&ndash;시작 페이지 ~ 마지막 페이지까지 반복&ndash;%&gt;--%>
                                <%--<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">--%>
                                    <%--&lt;%&ndash;현재 목록 페이지가 idx와 일치하면 현재페이지 표시&ndash;%&gt;--%>
                                    <%--<li <c:out value="${pageMaker.criteria.page == idx? 'class=active':''}"/>>--%>
                                        <%--&lt;%&ndash;UriComponents를 사용&ndash;%&gt;--%>
                                        <%--<a href="${path}/board/paging/list${pageMaker.makeQuery(idx)}">${idx}</a>--%>
                                    <%--</li>--%>
                                <%--</c:forEach>--%>

                                <%--&lt;%&ndash;next가 true이고 endPage가 0이상일 경우&ndash;%&gt;--%>
                                <%--<c:if test="${pageMaker.next && pageMaker.endPage > 0}">--%>
                                    <%--&lt;%&ndash;UriComponents를 사용&ndash;%&gt;--%>
                                    <%--<li><a href="${path}/board/paging/list${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a></li>--%>
                                <%--</c:if>--%>

                                <%--========================JavaScript를 이용한 페이징 링크 처리========================--%>

                                <%--prev가 true 일 경우--%>
                                <c:if test="${pageMaker.prev}">
                                    <%--javascript 사용--%>
                                    <li><a href="${pageMaker.startPage - 1}">&laquo;</a></li>
                                </c:if>

                                <%--시작 페이지 ~ 마지막 페이지까지 반복--%>
                                <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                                    <%--현재 목록 페이지가 idx와 일치하면 현재페이지 표시--%>
                                    <li <c:out value="${pageMaker.criteria.page == idx? 'class=active':''}"/>>
                                        <%--javascript 사용--%>
                                        <a href="${idx}">${idx}</a>
                                    </li>

                                </c:forEach>

                                <%--next가 true이고 endPage가 0이상일 경우--%>
                                <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                                    <%--javascript 사용--%>
                                    <li><a href="${pageMaker.endPage +1}">&raquo;</a></li>
                                </c:if>

                            </ul>
                        </div>

                    </div>
                </div>
                
                <div class="pull-right">
                    <button type="button" class="btn btn-success btn-flat" id="writeBtn">
                        <i class="fa fa-edit"></i> 글쓰기
                    </button>
                </div>
            </div>

        </section>
        <%-- /.content --%>
    </div>
    <%-- /.content-wrapper --%>

    <%--main_footer.jsp--%>
    <%-- Main Footer --%>
    <%@ include file="../../include/main_footer.jsp" %>

    <%--javascript를 이용한 페이징 링크처리 form 태그--%>
    <form id="linkForm">
        <input type="hidden" name="page" value="${pageMaker.criteria.page}">
        <input type="hidden" name="perPageNum" value="${pageMaker.criteria.perPageNum}">
    </form>

</div>
<%-- ./wrapper --%>

<%--plugin_js.jsp--%>
<%@ include file="../../include/plugin_js.jsp" %>
<script>

    var result = "${msg}";

    if (result == "INSERT") {
        alert("게시글이 등록되었습니다.");
    } else if(result == "MODIFY") {
        alert("게시글이 수정되었습니다.");
    } else if(result == "DELETE") {
        alert("게시글이 삭제되었습니다.");
    }

    $(document).ready(function () {

        // javascript를 이용한 페이징 링크처리
        $(".pagination li a").on("click", function (event) {

            // 기본 이벤트 X
            event.preventDefault();

            // 이동할 페이지 선택자
            var targetPage = $(this).attr("href");

            // form 태그 선택자
            var linkForm = $("#linkForm");

            // value 값을 이동할 페이지로 저장
            linkForm.find("[name='page']").val(targetPage);
            // form 태그 속성 추가
            linkForm.attr("action", "/board/paging/list").attr("method", "get");
            linkForm.submit();

        });

        $("#writeBtn").on("click", function () {
            location.href = "/board/paging/register";
        });

    });



</script>
</body>
</html>