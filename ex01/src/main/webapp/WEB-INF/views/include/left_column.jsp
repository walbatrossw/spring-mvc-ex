<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="main-sidebar">
    <%-- 사이드바 --%>
    <section class="sidebar">
        <%-- 사이드바 회원 패널 --%>
        <div class="user-panel">
            <c:choose>

                <%--관리자--%>
                <c:when test="${sessionScope.adminNickName != null}">
                    <c:choose>
                        <c:when test="${sessionScope.adminProfileImageName == ''}">
                            <div class="pull-left image">
                                <img class="img-circle" src="${path}/dist/img/default-user-image.jpg">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="pull-left image">
                                <img class="img-circle"
                                     src="${path}/dist/img/admins/profile/${sessionScope.adminProfileImageName}">
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <div class="pull-left info">
                        <p>${sessionScope.adminNickName} 관리자 님</p>
                        <a href="#"><i class="fa fa-circle text-success"></i> ONLINE</a>
                    </div>
                </c:when>

                <%--회원--%>
                <c:when test="${sessionScope.userNickName != null}">
                    <c:choose>
                        <c:when test="${sessionScope.userProfileImageName == ''}">
                            <div class="pull-left image">
                                <img class="img-circle" src="${path}/dist/img/default-user-image.jpg">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="pull-left image">
                                <img class="img-circle"
                                     src="${path}/dist/img/users/profile/${sessionScope.userProfileImageName}">
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <div class="pull-left info">
                        <p>${sessionScope.userNickName} 님</p>
                        <a href="#"><i class="fa fa-circle text-success"></i> ONLINE</a>
                    </div>
                </c:when>

                <%--비회원--%>
                <c:otherwise>
                    <div class="pull-left image">
                        <img src="${path}/dist/img/default-user-image.jpg" class="img-circle" alt="User Image">
                    </div>
                    <div class="pull-left info">
                        <p>GUEST</p>
                        <a href="#"><i class="fa fa-circle text-danger"></i> OFFLINE</a>
                    </div>
                </c:otherwise>

            </c:choose>
        </div>

        <%-- 빠른 채용 검색폼 --%>
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="빠른 채용 검색...">
                <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
            </div>
        </form>

        <%-- 사이드바 메뉴 --%>
        <ul class="sidebar-menu">

            <%--관리자 메뉴--%>
            <c:if test="${sessionScope.adminId != null}">
                <li class="header">관리자 메뉴</li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-users"></i>
                        <span>관리자 관리</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${path}/admin/list"><i class="fa fa-list"></i> 관리자 목록</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-users"></i>
                        <span>회원 관리</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${path}/user/list"><i class="fa fa-list"></i> 회원목록</a></li>
                    </ul>
                </li>

                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-industry"></i>
                        <span>기업 관련 정보관리</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${path}/company/info/create"><i class="fa fa-pencil"></i> 기업 등록</a></li>
                        <li><a href="${path}/company/info/list"><i class="fa fa-list"></i> 기업 목록</a></li>
                        <li><a href="${path}/company/info/list"><i class="fa fa-list"></i> 기업리뷰 승인대기 목록</a></li>
                        <li><a href="${path}/company/info/list"><i class="fa fa-list"></i> 기업연봉 승인대기 목록</a></li>
                        <li><a href="${path}/company/info/list"><i class="fa fa-list"></i> 기업면접 후기 승인대기 목록</a></li>
                    </ul>
                </li>

                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-briefcase"></i>
                        <span>채용 관리</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${path}/recruit/create"><i class="fa fa-pencil"></i> 채용 등록</a></li>
                        <li><a href="${path}/recruit/list"><i class="fa fa-list"></i> 채용 목록</a></li>
                    </ul>
                </li>
            </c:if>

            <%--회원 메뉴--%>

            <li class="header">메뉴</li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-table"></i>
                    <span>이력서</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${path}/resume/create"><i class="fa fa-edit"></i> 이력서 작성</a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-clipboard"></i>
                    <span>채용공고</span>
                    <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${path}/recruit/calendar"><i class="fa fa-calendar"></i> 채용달력</a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-file-text-o"></i>
                    <span>자기소개서</span>
                    <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${path}/coverletter/create/default"><i class="fa fa-pencil"></i> 기본 자기소개서</a></li>
                    <li><a href="${path}/coverletter/create/default"><i class="fa fa-pencil"></i> 채용직무별 자기소개서</a></li>
                    <li><a href="${path}/coverletter/list"><i class="fa fa-list"></i>나의 자기소개서 목록</a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-building"></i>
                    <span>기업정보</span>
                    <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${path}/company/review/list"><i class="fa fa-list"></i> 기업 목록</a></li>
                    <li><a href="${path}/company/review/list"><i class="fa fa-check-square-o"></i> 기업 리뷰</a></li>
                    <li><a href="${path}/company/salary/list"><i class="fa fa-krw"></i> 연봉 정보</a></li>
                    <li><a href="${path}/company/interview/list"><i class="fa fa-commenting"></i> 면접 후기</a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-comments-o"></i>
                    <span>자유게시판</span>
                    <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="${path}/freeboard/list/all"><i class="fa fa-comments"></i> 전체글</a></li>
                    <li><a href="${path}/freeboard/list/free"><i class="fa fa-commenting-o"></i> 잡담</a></li>
                    <li><a href="${path}/freeboard/list/condition"><i class="fa fa-child"></i> 근무환경</a></li>
                    <li><a href="${path}/freeboard/list/coverletter"><i class="fa fa-exclamation"></i> 자소서</a>
                    </li>
                    <li><a href="${path}/freeboard/list/test"><i class="fa fa-book"></i> 인적성</a></li>
                    <li><a href="${path}/freeboard/list/certificate"><i class="fa fa-institution"></i> 자격증</a>
                    </li>
                    <li><a href="${path}/freeboard/list/language"><i class="fa fa-language"></i> 어학</a></li>
                </ul>
            </li>
        </ul>
    </section>
</aside>