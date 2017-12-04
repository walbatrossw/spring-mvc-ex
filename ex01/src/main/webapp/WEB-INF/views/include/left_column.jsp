<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--left_column.jsp--%>
<%-- Left side column. contains the logo and sidebar --%>
<aside class="main-sidebar">

    <%-- sidebar: style can be found in sidebar.less --%>
    <section class="sidebar">

        <%-- Sidebar user panel (optional) --%>
        <div class="user-panel">
            <div class="pull-left image">
                <img src="/dist/img/default-user-image.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>Guest</p>
                <%-- Status --%>
                <a href="#"><i class="fa fa-circle text-success"></i> offline</a>
            </div>
        </div>

        <%-- search form (Optional) --%>
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="게시글 검색">
                <span class="input-group-btn">
              <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
            </div>
        </form>
        <%-- /.search form --%>

        <%-- Sidebar Menu --%>
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">메뉴</li>
            <%-- Optionally, you can add icons to the links --%>
            <li class="treeview active">
                <a href="#">
                    <i class="fa fa-clipboard"></i>
                    <span>게시판</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${path}/board/register"><i class="fa fa-edit"></i> <span>글쓰기(페이징 X)</span></a></li>
                    <li><a href="${path}/board/list"><i class="fa fa-list"></i> <span>목록1 (전체)</span></a></li>
                    <li><a href="${path}/board/paging/listCriteria"><i class="fa fa-list"></i> <span>목록2 (일부목록, 페이징 X)</span></a></li>
                    <li><a href="${path}/board/paging/list"><i class="fa fa-list"></i> <span>목록3 (페이징 O)</span></a></li>
                    <li><a href="${path}/board/paging/search/list"><i class="fa fa-list"></i> <span>목록4 (페이징/검색처리)</span></a></li>
                </ul>
            </li>
        </ul>
        <%-- /.sidebar-menu --%>
    </section>
    <%-- /.sidebar --%>
</aside>
