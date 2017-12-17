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
                        <h3 class="box-title">게시글 목록</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <th style="width: 30px">#</th>
                                    <th>제목</th>
                                    <th style="width: 120px">작성자</th>
                                    <th style="width: 150px">작성시간</th>
                                    <th style="width: 60px">파일</th>
                                    <th style="width: 60px">조회</th>
                                </tr>
                                <c:forEach var="boardVO" varStatus="i" items="${list}">
                                <tr>
                                    <td>${boardVO.bno}</td>
                                    <td><a href="${path}/board/read?bno=${boardVO.bno}">${boardVO.title}</a></td>
                                    <td>${boardVO.writer}</td>
                                    <td><fmt:formatDate pattern="yyyy-MM-dd a HH:mm" value="${boardVO.regdate}"/></td>
                                    <td>${boardVO.attachcnt}</td>
                                    <td>${boardVO.viewcnt}</td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="box-footer">
                        <div class="text-center">
                            <ul class="pagination">
                                <c:if test="${pageMaker.prev}">
                                    <li><a href="${path}/board/list?page=${pageMaker.startPage - 1}">이전</a></li>
                                </c:if>
                                <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                                    <li <c:out value="${pageMaker.criteria.page == idx ? 'class=active' : ''}"/>>
                                        <a href="${path}/board/list?page=${idx}">${idx}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                                    <li><a href="${path}/board/list?page=${pageMaker.endPage + 1}">다음</a></li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                    <div class="box-footer">
                        <p>검색처리 영역</p>
                    </div>
                    <div class="box-footer">
                        <div class="pull-right">
                            <button type="button" class="btn btn-success btn-flat" id="writeBtn">
                                <i class="fa fa-pencil"></i> 글쓰기
                            </button>
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

</div>
<%-- ./wrapper --%>

<%--plugin_js.jsp--%>
<%@ include file="../include/plugin_js.jsp" %>
<script>
    $(document).ready(function () {

        /*====================================게시글 등록, 삭제 알림 / 게시글 페이지 이동====================================*/

        var result = "${msg}";

        // 게시글 등록, 삭제 성공 알림
        if (result == "INSERT") {
            alert("게시글이 등록되었습니다.");
        } else if (result == "DELETED") {
            alert("게시글이 삭제되었습니다.");
        }

        // 글쓰기 버튼 클릭 이벤트
        $("#writeBtn").on("click", function (event) {
            self.location = "write";
        });

        /*=================================================게시글 검색=================================================*/
    });
</script>
</body>
</html>