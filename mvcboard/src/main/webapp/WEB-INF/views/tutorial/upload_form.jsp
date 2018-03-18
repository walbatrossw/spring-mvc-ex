<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%--head.jsp--%>
<%@ include file="../include/head.jsp" %>
<style>
    iframe {
        width: 0px;
        height: 0px;
        border: 0px;
    }
</style>
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
                파일 업로드(일반) 테스트 페이지
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> fileupload</a></li>
                <li class="active">general form</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">

            <div class="col-lg-12">
                <form role="form" id="uploadForm" method="post" action="${path}/file/form/upload" enctype="multipart/form-data" target="imgFrame">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">파일 업로드(일반) 입력폼</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">

                            <div class="fileinput fileinput-new input-group" data-provides="fileinput">
                                <div class="form-control" data-trigger="fileinput">
                                    <i class="glyphicon glyphicon-file fileinput-exists"></i>
                                    <span class="fileinput-filename"></span>
                                </div>
                                <span class="input-group-addon btn btn-default btn-file">
                                    <span class="fileinput-new">파일 선택</span>
                                    <span class="fileinput-exists">변경</span>
                                    <input type="file" name="file"></span>
                                <a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">삭제</a>
                            </div>

                            <iframe name="imgFrame"></iframe>

                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer">
                            <div class="form-group pull-right">
                                <button type="submit" class="btn btn-primary"><i class="fa fa-save"></i> 저장</button>
                            </div>
                        </div>
                        <!-- /.box-footer -->
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
<script>
    function addFilePath(msg) {

        alert(msg);

        $("#uploadForm").each(function(){
            this.reset();
        });

    }
</script>
</body>
</html>