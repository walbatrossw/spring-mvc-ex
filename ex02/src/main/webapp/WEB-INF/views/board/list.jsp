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
                        <h3 class="box-title">게시글 목록 (${totalCount})</h3>
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
                                        <td><a href="${path}/board/read${pageMaker.makeQuery(pageMaker.criteria.page)}&bno=${boardVO.bno}">${boardVO.title}</a></td>
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
                                <%--자바스크립트를 이용한 페이지링크 처리를 위한 form 태그--%>
                                <%--<form id="pageLinkForm">--%>
                                    <%--<input type="hidden" name="page" value="${pageMaker.criteria.page}">--%>
                                    <%--<input type="hidden" name="perPageNum" value="${pageMaker.criteria.perPageNum}">--%>
                                <%--</form>--%>

                                <%--UriComponentBuilder를 이용한 페이지링크 처리--%>
                                <c:if test="${pageMaker.prev}">
                                    <li>
                                        <a href="${path}/board/list${pageMaker.makeSearch(pageMaker.startPage - 1)}">&laquo;</a>
                                    </li>
                                </c:if>
                                <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                                    <li <c:out value="${pageMaker.criteria.page == idx? 'class=active':''}"/>>
                                        <a href="${path}/board/list${pageMaker.makeSearch(idx)}">${idx}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                                    <li>
                                        <a href="${path}/board/list${pageMaker.makeSearch(pageMaker.endPage + 1)}">&raquo;</a>
                                    </li>
                                </c:if>

                                <%--자바스크립트를 이용한 페이지링크 처리--%>
                                <%--<c:if test="${pageMaker.prev}">--%>
                                    <%--<li><a href="${pageMaker.startPage - 1}">&laquo;</a></li>--%>
                                <%--</c:if>--%>
                                <%--<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">--%>
                                    <%--<li <c:out value="${pageMaker.criteria.page == idx? 'class=active':''}"/>>--%>
                                        <%--<a href="${idx}">${idx}</a>--%>
                                    <%--</li>--%>
                                <%--</c:forEach>--%>
                                <%--<c:if test="${pageMaker.next && pageMaker.endPage > 0}">--%>
                                    <%--<li><a href="${pageMaker.endPage + 1}">&raquo;</a></li>--%>
                                <%--</c:if>--%>
                            </ul>
                        </div>
                    </div>

                    <div class="box-footer">
                        <br/>
                        <div class="form-group col-sm-2">
                            <select class="form-control" name="searchType" id="searchType">
                                <option value="n" <c:out value="${criteria.searchType == null ? 'selected' : ''}"/>>:::::: 선택 ::::::</option>
                                <option value="t" <c:out value="${criteria.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
                                <option value="c" <c:out value="${criteria.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
                                <option value="w" <c:out value="${criteria.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
                                <option value="tc" <c:out value="${criteria.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
                                <option value="cw" <c:out value="${criteria.searchType eq 'cw' ? 'selected' : ''}"/>>내용+작성자</option>
                                <option value="tcw" <c:out value="${criteria.searchType eq 'tcw' ? 'selected' : ''}"/>>제목+내용+작성자</option>
                            </select>
                        </div>
                        <div class="form-group col-sm-10">
                            <div class="input-group">
                                <input type="text" class="form-control" name="keyword" id="keywordInput" value="${criteria.keyword}" placeholder="검색어">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-primary btn-flat" id="searchBtn">
                                        <i class="fa fa-search"></i> 검색
                                    </button>
                                </span>
                            </div>
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

    // 페이지링크 처리
    // $(".pagination li a").on("click", function (event) {
    //     event.preventDefault();
    //     var targetPage = $(this).attr("href");
    //     var pageLinkForm = $("#pageLinkForm");
    //     pageLinkForm.find("[name='page']").val(targetPage);
    //     pageLinkForm.attr("action", "/board/list").attr("method", "get");
    //     pageLinkForm.submit();
    // })


    $(document).ready(function () {
        // 검색 버튼 클릭시
        $("#searchBtn").on("click", function (event) {
            self.location = "list${pageMaker.makeQuery(1)}"
                + "&searchType=" + $("select option:selected").val()
                + "&keyword=" + encodeURIComponent($("#keywordInput").val());
        });

        // 글쓰기 버튼 클릭시
        $("#writeBtn").on("click", function (event) {
           self.location = "register";
        });
    });
    
    

</script>
</body>
</html>