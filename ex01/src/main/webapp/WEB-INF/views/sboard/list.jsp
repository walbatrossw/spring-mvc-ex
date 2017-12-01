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

                <%--검색영역--%>
                <%--<div class="box box-info">--%>
                    <%--<div class="box-header with-border">--%>
                        <%--<h3 class="box-title">게시글 검색 / 쓰기</h3>--%>
                    <%--</div>--%>
                    <%--<div class="box-body">--%>
                        <%--<br/>--%>
                        <%--<div class="form-group col-sm-3">--%>
                            <%--<select class="form-control" name="searchType" id="searchType">--%>
                                <%--<option value="n" <c:out value="${criteria.searchType == null ? 'selected' : ''}"/>>:::::: 선택 ::::::</option>--%>
                                <%--<option value="t" <c:out value="${criteria.searchType eq 't' ? 'selected' : ''}"/>>제목</option>--%>
                                <%--<option value="c" <c:out value="${criteria.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>--%>
                                <%--<option value="w" <c:out value="${criteria.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>--%>
                                <%--<option value="tc" <c:out value="${criteria.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>--%>
                                <%--<option value="cw" <c:out value="${criteria.searchType eq 'cw' ? 'selected' : ''}"/>>내용+작성자</option>--%>
                                <%--<option value="tcw" <c:out value="${criteria.searchType eq 'tcw' ? 'selected' : ''}"/>>제목+내용+작성자</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>

                        <%--<div class="form-group col-sm-8">--%>
                            <%--<div class="input-group">--%>
                                <%--<input type="text" class="form-control" name="keyword" id="keywordInput" value="${criteria.keyword}" placeholder="검색어">--%>
                                <%--<span class="input-group-btn">--%>
                                    <%--<button type="button" class="btn btn-primary btn-flat" id="searchBtn">--%>
                                        <%--<i class="fa fa-search"></i> 검색--%>
                                    <%--</button>--%>
                                    <%--<button type="button" class="btn btn-success btn-flat" id="writeBtn">--%>
                                        <%--<i class="fa fa-edit"></i> 글쓰기--%>
                                    <%--</button>--%>
                                <%--</span>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>

                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">게시글 목록 (${boardCount})</h3>
                    </div>
                    <div class="box-body">
                        <%--게시글 목록 영역--%>
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
                                <td><a href="${path}/sboard/readPage${pageMaker.makeSearch(pageMaker.criteria.page)}&bno=${boardVO.bno}">${boardVO.title}</a></td>
                                <td>${boardVO.writer}</td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}"/></td>
                                <td><span class="badge bg-aqua">${boardVO.viewcnt}</span></td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <%--페이징영역--%>
                        <div class="text-center">
                            <ul class="pagination">
                                <%--prev가 true 일 경우--%>
                                <c:if test="${pageMaker.prev}">
                                    <%--UriComponents를 사용--%>
                                    <li><a href="${path}/sboard/list${pageMaker.makeSearch(pageMaker.startPage-1)}">&laquo;</a></li>
                                </c:if>
                                <%--시작 페이지 ~ 마지막 페이지까지 반복--%>
                                <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                                    <%--현재 목록 페이지가 idx와 일치하면 현재페이지 표시--%>
                                    <li <c:out value="${pageMaker.criteria.page == idx? 'class=active':''}"/>>
                                            <%--UriComponents를 사용--%>
                                        <a href="${path}/sboard/list${pageMaker.makeSearch(idx)}">${idx}</a>
                                    </li>
                                </c:forEach>
                                <%--next가 true이고 endPage가 0이상일 경우--%>
                                <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                                    <%--UriComponents를 사용--%>
                                    <li><a href="${path}/sboard/list${pageMaker.makeSearch(pageMaker.endPage + 1)}">&raquo;</a></li>
                                </c:if>
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

    if (result == "INSERT") {
        alert("게시글이 등록되었습니다.");
    } else if(result == "MODIFY") {
        alert("게시글이 수정되었습니다.");
    } else if(result == "DELETE") {
        alert("게시글이 삭제되었습니다.");
    }

    $(document).ready(function () {

        $("#searchBtn").on("click", function (event) {

            self.location = "list${pageMaker.makeQuery(1)}"
                + "&searchType="+$("select option:selected").val()
                + "&keyword=" + encodeURIComponent($('#keywordInput').val());

        });

        $("#writeBtn").on("click", function (event) {

            self.location = "${path}/sboard/register";

        })
    });

</script>
</body>
</html>