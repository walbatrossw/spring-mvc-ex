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
                수정페이지
                <small>스프링연습예제</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> board</a></li>
                <li class="active">modify</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">
            <div class="col-lg-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">게시글 수정 - <small>게시글번호(${boardVO.bno})</small></h3>
                    </div>
                    <form role="form" method="post" action="${path}/board/modify">
                        <div class="box-body">
                            <%--페이지 이동을 위한 값 세팅--%>
                            <input type="hidden" name="bno" value="${boardVO.bno}">
                            <input type="hidden" name="page" value="${criteria.page}">
                            <input type="hidden" name="perPageNum" value="${criteria.perPageNum}">
                            <input type="hidden" name="searchType" value="${criteria.searchType}">
                            <input type="hidden" name="keyword" value="${criteria.keyword}">

                            <div class="form-group">
                                <label for="title">제목</label>
                                <input type="text" id="title" name="title" class="form-control" value="${boardVO.title}" >
                            </div>
                            <div class="form-group">
                                <label for="content">내용</label>
                                <textarea class="form-control" id="content" name="content" rows="10" style="resize: none;">${boardVO.content}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="writer">작성자</label>
                                <input type="text" class="form-control" id="writer" name="writer" value="${boardVO.writer}" readonly="readonly">
                            </div>
                        </div>
                    </form>
                    <div class="box-footer">
                        <button type="submit" class="btn btn-warning modifyBtn"><i class="fa fa-edit"></i> 수정</button>
                        <button type="submit" class="btn btn-danger cancelBtn"><i class="fa fa-arrow-left"></i> 취소</button>
                        <button type="submit" class="btn btn-primary listBtn pull-right"><i class="fa fa-list"></i> 목록</button>
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
<script src="/web_js/modify.js"></script>
<script>
    $(document).ready(function () {

        // form 선택자
        var formObj = $("form[role='form']");
        console.log(formObj);

        // 수정버튼 클릭시
        $(".modifyBtn").on("click", function () {
            formObj.submit();
        });

        // 취소버튼 클릭시
        $(".cancelBtn").on("click", function () {
            history.go(-1); // <iframe>의 내부에 들어가 있을 경우 사용 X
            //self.location = "/board/read?bno="+"${boardVO.bno}";
        });

        // 목록버튼 클릭시
        $(".listBtn").on("click", function () {
            self.location = "/board/list?page=${criteria.page}&perPageNum=${criteria.perPageNum}";
        });

    });
</script>
</body>
</html>