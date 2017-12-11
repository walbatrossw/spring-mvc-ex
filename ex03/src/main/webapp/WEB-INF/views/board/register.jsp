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
                등록페이지
                <small>스프링연습예제</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> board</a></li>
                <li class="active">register</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">
            <div class="col-lg-12">
                <form role="form" method="post" action="${path}/board/register">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">게시글 작성하기</h3>
                        </div>
                        <div class="box-body">
                            <div class="form-group">
                                <label for="title">제목</label>
                                <input type="text" class="form-control" name="title" id="title" placeholder="제목 입력..">
                            </div>
                            <div class="form-group">
                                <label for="content">내용</label>
                                <textarea class="form-control" name="content" id="content" placeholder="내용 입력.." rows="15"
                                          style="resize: none"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="writer">작성자</label>
                                <input class="form-control" type="text" name="writer" id="writer" placeholder="작성자 입력..">
                            </div>
                        </div>
                        <div class="box-footer">
                            <button type="reset" class="btn btn-primary"><i class="fa fa-reply"></i> 초기화</button>
                            <button type="submit" class="btn btn-success"><i class="fa fa-save"></i> 저장</button>
                        </div>
                    </div>
                </form>
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
</body>
</html>