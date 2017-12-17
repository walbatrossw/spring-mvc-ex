<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("newLine", "\n"); %>
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
                게시글 조회
                <small>스프링연습예제</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> board</a></li>
                <li class="active">read</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">

            <div class="col-lg-12">

                <%--게시글 내용 영역--%>
                <div class="box box-primary">

                    <%--게시글 제목 영역--%>
                    <div class="box-header with-border">
                        <h3 class="box-title">글제목 : ${boardVO.title}</h3>
                        <ul class="list-inline pull-right">
                            <li><a href="#" class="link-black text-lg"><i class="fa fa-share margin-r-5"></i>공유</a></li>
                            <li><a href="#" class="link-black text-lg"><i class="fa fa-bookmark-o margin-r-5"></i>북마크</a></li>
                            <li><a href="#" class="link-black text-lg"><i class="fa fa-thumbs-o-up margin-r-5"></i>추천 (0)</a></li>
                            <li><a href="#" class="link-black text-lg"><i class="fa fa-eye margin-r-5"></i>조회수 (${boardVO.viewcnt})</a></li>
                        </ul>
                    </div>

                    <%--게시글 내용 영역--%>
                    <div class="box-body" style="height: 700px">
                        ${fn:replace(fn:replace(fn:escapeXml(boardVO.content), newLine, "<br/>") , " ", "&nbsp;")}
                    </div>

                    <%--업로드 파일 정보 영역--%>
                    <div class="box-footer">
                        <p>파일첨부 목록영역</p>
                    </div>

                    <%--작성자 정보 영역--%>
                    <div class="box-footer">
                        <div class="user-block">
                            <img class="img-circle img-bordered-sm" src="/dist/img/default-user-image.jpg" alt="user image">
                            <span class="username">
                                <a href="#">${boardVO.writer}</a>
                            </span>
                            <span class="description"><fmt:formatDate pattern="yyyy-MM-dd a HH:mm" value="${boardVO.regdate}"/></span>
                        </div>
                    </div>

                    <%--페이지 이동 버튼 영역--%>
                    <div class="box-footer">
                        <form role="form" method="post">
                            <input type="hidden" name="bno" value="${boardVO.bno}">
                        </form>
                        <button type="submit" class="btn btn-primary listBtn"><i class="fa fa-list"></i> 목록</button>
                        <div class="pull-right">
                            <button type="submit" class="btn btn-warning modBtn"><i class="fa fa-edit"></i> 수정</button>
                            <button type="submit" class="btn btn-danger delBtn"><i class="fa fa-trash"></i> 삭제</button>
                        </div>
                    </div>

                </div>

                <%--댓글 입력 영역--%>
                <div class="box box-warning">
                    <div class="box-header with-border">
                        <h3 class="box-title">댓글 입력창</h3>
                    </div>
                    <div class="box-body">

                    </div>
                    <div class="box-footer">

                    </div>
                </div>

                <%--댓글 목록 영역--%>
                <div class="box box-success">
                    <div class="box-header with-border">
                        <h3 class="box-title">댓글 목록</h3>
                    </div>
                    <div class="box-body">
                        <p>댓글 목록</p>
                    </div>
                    <div class="box-footer">
                        <p>댓글 페이징</p>
                    </div>
                </div>

                <%--댓글 수정 modal 영역--%>

                <%--댓글 삭제 modal 영역--%>

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

        /*================================================게시판 첨부파일 업로드 목록 관련==================================*/


        /*================================================댓글 관련======================================================*/


        /*================================================게시판 페이지 이동관련===========================================*/

        // 페이지 이동 form 선택자
        var formObj = $("form[role='form']");
        console.log(formObj);

        // 수정버튼 클릭 이벤트
        $(".modBtn").on("click", function () {
            formObj.attr("action", "/board/modify");
            formObj.attr("method", "get");
            formObj.submit();
        });

        // 삭제 버튼 클릭 이벤트
        $(".delBtn").on("click", function () {
            formObj.attr("action", "/board/remove");
            formObj.submit();
        });

        // 목록 버튼 클릭 이벤트
        $(".listBtn").on("click", function () {
            $("input[name=bno]").remove();
            formObj.attr("method", "get");
            formObj.attr("action", "/board/list");
            formObj.submit();
        });

        // 수정 완료시 알림
        var result = "${msg}";
        if (result == "UPDATED") {
            alert("게시글이 수정되었습니다.");
        }

    });
    
</script>
</body>
</html>