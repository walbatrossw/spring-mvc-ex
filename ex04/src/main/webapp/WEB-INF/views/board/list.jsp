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
                게시글 목록
                <small>스프링연습예제</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> board</a></li>
                <li class="active">list</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">

            <div class="col-lg-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">게시글 목록 (게시글 갯수)</h3>
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
                                        <td>
                                            <a href="${path}/board/read${pageMaker.makeSearch(pageMaker.criteria.page)}&bno=${boardVO.bno}">${boardVO.title}
                                                <span class="label label-warning">${boardVO.replycnt}</span>
                                            </a>
                                        </td>
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
                            </ul>
                        </div>

                    </div>

                    <%--검색 처리 영역--%>
                    <div class="box-footer">
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
                        <i class="fa fa-pencil"></i> 글쓰기
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
    $(document).ready(function () {

        /*=================================== 게시글 페이지 이동 관련 / 등록,삭제 알림 ===================================*/

        var result = "${msg}";

        // 게시글 등록, 삭제 알림
        if (result == "INSERTED") {
            alert("게시글이 등록되었습니다.");
        } else if(result == "DELETED") {
            alert("게시글이 삭제되었습니다.");
        }

        // 글쓰기 버튼 클릭시
        $("#writeBtn").on("click", function (event) {
            self.location = "write";
        });

        /*=========================================== 게시글 검색 관련 관련  ===========================================*/
        // 검색 버튼 클릭시
        $("#searchBtn").on("click", function () {
            self.location = "list${pageMaker.makeQuery(1)}"
                            + "&searchType=" + $("select option:selected").val()
                            + "&keyword=" + encodeURIComponent($("#keywordInput").val());
        });

    });
</script>
</body>
</html>