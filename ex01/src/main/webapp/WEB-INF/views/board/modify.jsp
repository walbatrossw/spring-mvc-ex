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
                <small>수정페이지</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 게시판</a></li>
                <li class="active">수정</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">

            <div class="col-lg-12">

                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">게시글 수정</h3>
                        </div>
                        <form role="form" method="post">
                        <div class="box-body">

                            <div class="form-group">
                                <label for="bno">게시글 번호</label>
                                <input type="text" id="bno" name="bno" class="form-control" value="${boardVO.bno}" readonly="readonly">
                            </div>

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
                            <button type="submit" class="btn btn-warning saveBtn">저장</button>
                            <button type="submit" class="btn btn-danger cancelBtn">취소</button>
                            <button type="submit" class="btn btn-primary listBtn pull-right">목록</button>
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

        var formObj = $("form[role='form']");
        console.log(formObj);

        $(".saveBtn").on("click", function () {
            formObj.submit();
        });
        
        $(".cancelBtn").on("click", function () {
            self.location = "/board/read?bno="+"${boardVO.bno}";
        });
        
        $(".listBtn").on("click", function () {
            self.location = "/board/listAll";
        });

    })
</script>
</body>
</html>