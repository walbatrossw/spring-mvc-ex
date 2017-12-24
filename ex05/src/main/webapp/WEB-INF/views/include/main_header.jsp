<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--main_header.jsp--%>
<%-- Main Header --%>
<header class="main-header">

    <%-- Logo --%>
    <a href="/" class="logo">
        <%-- mini logo for sidebar mini 50x50 pixels --%>
        <span class="logo-mini"><b>S</b>E</span>
        <%-- logo for regular state and mobile devices --%>
        <span class="logo-lg"><b>Spring</b> - Examples</span>
    </a>

    <%-- Header Navbar --%>
    <nav class="navbar navbar-static-top" role="navigation">
        <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <c:if test="${not empty login}">
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="${path}/dist/img/profile${login.uimage}" class="user-image" alt="User Image">
                        <span class="hidden-xs">${login.uname}</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="user-header">
                            <img src="${path}/dist/img/profile/${login.uimage}" class="img-circle" alt="User Image">
                            <p>
                                <small>
                                    가입일자 : <fmt:formatDate value="${login.regdate}" pattern="yyyy-MM-dd"/>
                                </small>
                                <small>
                                    최근로그인일자 : <fmt:formatDate value="${login.logdate}" pattern="yyyy-MM-dd a HH:mm"/>
                                </small>
                            </p>
                        </li>
                        <li class="user-body">
                            <div class="row">
                                <div class="col-xs-4 text-center">
                                    <a href="#">게시글</a>
                                </div>
                                <div class="col-xs-4 text-center">
                                    <a href="#">추천글</a>
                                </div>
                                <div class="col-xs-4 text-center">
                                    <a href="#">북마크</a>
                                </div>
                            </div>
                        </li>
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="${path}/user/profile" class="btn btn-default btn-flat"><i
                                        class="fa fa-info-circle"></i><b> 내 프로필</b></a>
                            </div>
                            <div class="pull-right">
                                <a href="${path}/user/logout" class="btn btn-default btn-flat"><i
                                        class="glyphicon glyphicon-log-out"></i><b> 로그아웃</b></a>
                            </div>
                        </li>
                    </ul>
                </li>
                </c:if>
                <c:if test="${empty login}">
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="/dist/img/default-user-image.jpg" class="user-image" alt="User Image">
                        <span class="hidden-xs">회원가입 또는 로그인</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="user-header">
                            <img src="/dist/img/default-user-image.jpg" class="img-circle" alt="User Image">
                            <p>
                                <b>회원가입 또는 로그인해주세요</b>
                                <small></small>
                            </p>
                        </li>
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="${path}/user/register" class="btn btn-default btn-flat"><i
                                        class="fa fa-user-plus"></i><b> 회원가입</b></a>
                            </div>
                            <div class="pull-right">
                                <a href="${path}/user/login" class="btn btn-default btn-flat"><i
                                        class="glyphicon glyphicon-log-in"></i><b> 로그인</b></a>
                            </div>
                        </li>
                    </ul>
                </li>
                </c:if>
            </ul>
        </div>
    </nav>
</header>