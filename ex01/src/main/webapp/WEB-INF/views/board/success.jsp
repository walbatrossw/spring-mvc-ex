<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>

<html>

<%--head.jsp--%>
<jsp:include page="../include/head.jsp"/>

<body class="hold-transition skin-blue sidebar-mini layout-boxed">
<div class="wrapper">

    <%--main_header.jsp--%>
    <%-- Main Header --%>
    <jsp:include page="../include/main_header.jsp"/>

    <%--left_column.jsp--%>
    <%-- Left side column. contains the logo and sidebar --%>
    <jsp:include page="../include/left_column.jsp"/>
    
    <%-- Content Wrapper. Contains page content --%>
    <div class="content-wrapper">
        <%-- Content Header (Page header) --%>
        <section class="content-header">
            <h1>
                게시판 예제
                <small>입력확인</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 게시판</a></li>
                <li class="active">입력성공</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">

            <div class="col-lg-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">게시글 입력 성공확인</h3>
                    </div>
                    <div class="box-body">
                        <h4>게시글 입력 성공 메시지 : ${msg}</h4>
                    </div>
                </div>
            </div>

        </section>
        <%-- /.content --%>
    </div>
    <%-- /.content-wrapper --%>

    <%--main_footer.jsp--%>
    <%-- Main Footer --%>
    <jsp:include page="../include/main_footer.jsp"/>

</div>
<%-- ./wrapper --%>

<%--plugin_js.jsp--%>
<jsp:include page="../include/plugin_js.jsp"/>

</body>
</html>