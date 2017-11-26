<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <!--헤드(CSS) include-->
    <%@ include file="include/head.jsp" %>
</head>
<body class="fixed sidebar-mini skin-green">
<div class="wrapper">

    <!--헤더 네비바 include-->
    <%@ include file="include/navbar.jsp" %>

    <!--사이드 메뉴 include-->
    <%@ include file="include/left_column.jsp" %>

    <!--본문 페이지-->
    <div class="content-wrapper">
        <!--본문 페이지 헤더-->
        <section class="content-header">
            <h1>
                메인페이지
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">Dashboard</li>
            </ol>
        </section>

        <!--본문 페이지 내용-->
        <section class="content">
            <!-- 페이지 내용 -->
            <div class="row">
                <section class="col-lg-12">
                    메인 페이지 입니다....
                </section>
            </div>
        </section>

    </div>

    <!--풋터 include-->
    <%@ include file="include/footer.jsp" %>

    <!--컨트롤 사이드바 -->
    <%@ include file="include/control_sidebar.jsp" %>

</div>

<!-- 풋(JS) include-->
<%@ include file="include/js.jsp" %>
</body>
</html>
