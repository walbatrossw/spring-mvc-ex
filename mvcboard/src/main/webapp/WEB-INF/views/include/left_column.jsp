<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- Sidebar user panel (optional) -->
        <div class="user-panel">
            <c:if test="${empty login}">
                <div class="pull-left image">
                    <img src="${path}/user/default-user.png" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>Guest</p>
                        <%-- Status --%>
                    <a href="#"><i class="fa fa-circle text-danger"></i> OFFLINE</a>
                </div>
            </c:if>
            <c:if test="${not empty login}">
                <div class="pull-left image">
                    <img src="${path}/user/${login.userImg}" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>${login.userName}</p>
                        <%-- Status --%>
                    <a href="#"><i class="fa fa-circle text-success"></i> ONLINE</a>
                </div>
            </c:if>
        </div>

        <!-- search form (Optional) -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                    <button type="submit" name="search" id="search-btn" class="btn btn-flat">
                        <i class="fa fa-search"></i>
                    </button>
                </span>
            </div>
        </form>
        <!-- /.search form -->

        <!-- Sidebar Menu -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">테스트 예제</li>
            <li class="treeview">
                <a href="#"><i class="fa fa-folder"></i> <span>예제</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${path}/reply/test"><i class="fa fa-comment"></i> 댓글 </a></li>
                    <li><a href="${path}/file/form/uploadPage"><i class="fa fa-file"></i> 파일 업로드(Form)</a></li>
                    <li><a href="${path}/file/ajax/uploadPage"><i class="fa fa-file"></i> 파일 업로드(Ajax Drop)</a></li>
                    <li><a href="${path}/interceptor/doA"><i class="fa fa-mail-forward"></i> 인터셉터</a></li>
                </ul>
            </li>
            <li class="header">게시판</li>
            <li class="treeview">
                <a href="#"><i class="fa fa-edit"></i> <span>게시판(기본)</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${path}/article/write"><i class="fa fa-pencil"></i> 게시글 쓰기</a></li>
                    <li><a href="${path}/article/list"><i class="fa fa-list"></i> 게시글 목록</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#"><i class="fa fa-edit"></i> <span>게시판(페이징)</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${path}/article/paging/write"><i class="fa fa-pencil"></i> 게시글 쓰기</a></li>
                    <li><a href="${path}/article/paging/list"><i class="fa fa-list"></i> 게시글 목록</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#"><i class="fa fa-edit"></i> <span>게시판(페이징+검색)</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${path}/article/paging/search/write"><i class="fa fa-pencil"></i> 게시글 쓰기</a></li>
                    <li><a href="${path}/article/paging/search/list"><i class="fa fa-list"></i> 게시글 목록</a></li>
                </ul>
            </li>

        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>