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
                회원 정보
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> user</a></li>
                <li class="active">profile</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">
            <div class="col-md-8">

                <!-- Profile Image -->
                <div class="box box-primary">
                    <div class="box-body box-profile">
                        <img class="profile-user-img img-responsive img-circle"
                             src="${path}/dist/img/default-user.png" alt="User profile picture">
                        <h3 class="profile-username text-center">더블에스</h3>
                        <ul class="list-group list-group-unbordered">
                            <li class="list-group-item">
                                <div class="progress active">
                                    <div class="progress-bar progress-bar-success progress-bar-striped"
                                         role="progressbar"
                                         aria-valuenow="20"
                                         aria-valuemin="0"
                                         aria-valuemax="100" style="width: 20%">
                                        <span class="sr-only">20% Complete</span>
                                    </div>
                                </div>
                                <b>활동 포인트 / 레벨</b> <a class="pull-right">1,322 / lv. 10</a>
                            </li>
                            <li class="list-group-item">
                                <b>팔로잉</b> <a class="pull-right">543</a>
                            </li>
                            <li class="list-group-item">
                                <b>팔로우</b> <a class="pull-right">13,287</a>
                            </li>
                        </ul>
                        <div class="text-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myInfoMod">
                                    회원 정보 수정
                                </button>
                                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myPwMod">
                                    비밀번호 수정
                                </button>
                                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myProfileImgMod">
                                    프로필 이미지 수정
                                </button>
                                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#Withdrawal">
                                    회원 탈퇴
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
            <!-- /.col -->
            <div class="col-md-4">

                <!-- About Me Box -->
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">가입 정보</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <strong><i class="fa fa-user-plus margin-r-5"></i> 회원가입일</strong>
                        <p class="text-muted">
                            2018년 4월 5일
                        </p>

                        <hr>

                        <strong><i class="fa fa-sign-in margin-r-5"></i> 최종 접속일</strong>
                        <p class="text-muted">
                            2018년 4월 5일 20시 30분
                        </p>

                        <hr>

                        <strong><i class="fa fa-envelope-o margin-r-5"></i> 이메일</strong>
                        <p class="text-muted">
                            walbatrossw@gmail.com
                        </p>

                        <hr>

                        <strong><i class="fa fa-file-text-o margin-r-5"></i> 자기소개</strong>

                        <p>안녕하세요. 반갑습니다.</p>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
            <!-- /.col -->
            <div class="col-md-12">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#myActivities" data-toggle="tab">나의 활동</a></li>
                        <li><a href="#myArticle" data-toggle="tab">나의 게시글</a></li>
                        <li><a href="#myBookmark" data-toggle="tab">나의 북마크</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="active tab-pane" id="myActivities">
                            <!-- The timeline -->
                            <ul class="timeline timeline-inverse">
                                <!-- timeline time label -->
                                <li class="time-label">
                                    <span class="bg-red">2018년 4월 5일</span>
                                </li>
                                <!-- /.timeline-label -->
                                <!-- timeline item -->
                                <li>
                                    <i class="fa fa-envelope bg-blue"></i>
                                    <div class="timeline-item">
                                        <span class="time"><i class="fa fa-clock-o"></i> 12시 30분</span>
                                        <h3 class="timeline-header"><a href="#">Support Team</a> sent you an email
                                        </h3>
                                        <div class="timeline-body">
                                            Etsy doostang zoodles disqus groupon greplin oooj voxy zoodles,
                                            weebly ning heekya handango imeem plugg dopplr jibjab, movity
                                            jajah plickers sifteo edmodo ifttt zimbra. Babblely odeo kaboodle
                                            quora plaxo ideeli hulu weebly balihoo...
                                        </div>
                                        <div class="timeline-footer">
                                            <a class="btn btn-primary btn-xs">Read more</a>
                                            <a class="btn btn-danger btn-xs">Delete</a>
                                        </div>
                                    </div>
                                </li>
                                <!-- END timeline item -->
                                <!-- timeline item -->
                                <li>
                                    <i class="fa fa-user bg-aqua"></i>

                                    <div class="timeline-item">
                                        <span class="time"><i class="fa fa-clock-o"></i> 5 mins ago</span>

                                        <h3 class="timeline-header no-border"><a href="#">Sarah Young</a> accepted
                                            your friend request
                                        </h3>
                                    </div>
                                </li>
                                <!-- END timeline item -->
                                <!-- timeline item -->
                                <li>
                                    <i class="fa fa-comments bg-yellow"></i>

                                    <div class="timeline-item">
                                        <span class="time"><i class="fa fa-clock-o"></i> 27 mins ago</span>

                                        <h3 class="timeline-header"><a href="#">Jay White</a> commented on your post
                                        </h3>

                                        <div class="timeline-body">
                                            Take me to your leader!
                                            Switzerland is small and neutral!
                                            We are more like Germany, ambitious and misunderstood!
                                        </div>
                                        <div class="timeline-footer">
                                            <a class="btn btn-warning btn-flat btn-xs">View comment</a>
                                        </div>
                                    </div>
                                </li>
                                <!-- END timeline item -->
                                <!-- timeline time label -->
                                <li class="time-label">
                                    <span class="bg-green">2018년 4월 1일</span>
                                </li>
                                <!-- /.timeline-label -->
                                <!-- timeline item -->
                                <li>
                                    <i class="fa fa-camera bg-purple"></i>

                                    <div class="timeline-item">
                                        <span class="time"><i class="fa fa-clock-o"></i> 2 days ago</span>

                                        <h3 class="timeline-header"><a href="#">Mina Lee</a> uploaded new photos
                                        </h3>

                                        <div class="timeline-body">
                                            <img src="http://placehold.it/150x100" alt="..." class="margin">
                                            <img src="http://placehold.it/150x100" alt="..." class="margin">
                                            <img src="http://placehold.it/150x100" alt="..." class="margin">
                                            <img src="http://placehold.it/150x100" alt="..." class="margin">
                                        </div>
                                    </div>
                                </li>
                                <!-- END timeline item -->
                                <li>
                                    <i class="fa fa-clock-o bg-gray"></i>
                                </li>
                            </ul>
                        </div>

                        <div class="tab-pane" id="myArticle">
                            <table class="table table-bordered">
                                <tbody>
                                <tr>
                                    <th class="col-xs-1">번호</th>
                                    <th>제목</th>
                                    <th class="col-xs-2">작성일자</th>
                                    <th class="col-xs-1">파일</th>
                                    <th class="col-xs-1">조회</th>
                                </tr>
                                <%--<c:forEach items="${articles}" var="article">--%>
                                    <%--<tr>--%>
                                        <%--<td>${article.articleNo}</td>--%>
                                        <%--<td>--%>
                                            <%--<a href="${path}/article/paging/search/read${pageMaker.makeSearch(pageMaker.criteria.page)}&articleNo=${article.articleNo}">--%>
                                                    <%--${article.title}--%>
                                            <%--</a>--%>
                                            <%--<span class="badge bg-teal"><i class="fa fa-comment-o"></i> + ${article.replyCnt}</span>--%>
                                        <%--</td>--%>
                                        <%--<td>${article.writer}</td>--%>
                                        <%--<td><fmt:formatDate value="${article.regDate}" pattern="yyyy-MM-dd"/></td>--%>
                                        <%--<td><span class="badge bg-teal"><i class="fa fa-file"></i> ${article.fileCnt}</span></td>--%>
                                        <%--<td><span class="badge bg-red">${article.viewCnt}</span></td>--%>
                                    <%--</tr>--%>
                                <%--</c:forEach>--%>
                                </tbody>
                            </table>
                            <div class="text-center">
                                <ul class="pagination">
                                    <li class="active"><a>1</a></li>
                                    <li><a>2</a></li>
                                    <li><a>3</a></li>
                                    <li><a>4</a></li>
                                    <%--<c:if test="${pageMaker.prev}">--%>
                                        <%--<li><a href="${path}/article/paging/search/list${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>--%>
                                    <%--</c:if>--%>
                                    <%--<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">--%>
                                        <%--<li <c:out value="${pageMaker.criteria.page == idx ? 'class=active' : ''}"/>>--%>
                                            <%--<a href="${path}/article/paging/search/list${pageMaker.makeSearch(idx)}">${idx}</a>--%>
                                        <%--</li>--%>
                                    <%--</c:forEach>--%>
                                    <%--<c:if test="${pageMaker.next && pageMaker.endPage > 0}">--%>
                                        <%--<li><a href="${path}/article/paging/search/list${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>--%>
                                    <%--</c:if>--%>
                                </ul>
                            </div>
                        </div>
                        <!-- /.tab-pane -->

                        <!-- /.tab-pane -->

                        <div class="tab-pane" id="myBookmark">
                            <table class="table table-bordered">
                                <tbody>
                                <tr>
                                    <th class="col-xs-1">번호</th>
                                    <th>제목</th>
                                    <th class="col-xs-2">작성일자</th>
                                    <th class="col-xs-1">파일</th>
                                    <th class="col-xs-1">조회</th>
                                </tr>
                                <%--<c:forEach items="${articles}" var="article">--%>
                                <%--<tr>--%>
                                <%--<td>${article.articleNo}</td>--%>
                                <%--<td>--%>
                                <%--<a href="${path}/article/paging/search/read${pageMaker.makeSearch(pageMaker.criteria.page)}&articleNo=${article.articleNo}">--%>
                                <%--${article.title}--%>
                                <%--</a>--%>
                                <%--<span class="badge bg-teal"><i class="fa fa-comment-o"></i> + ${article.replyCnt}</span>--%>
                                <%--</td>--%>
                                <%--<td>${article.writer}</td>--%>
                                <%--<td><fmt:formatDate value="${article.regDate}" pattern="yyyy-MM-dd"/></td>--%>
                                <%--<td><span class="badge bg-teal"><i class="fa fa-file"></i> ${article.fileCnt}</span></td>--%>
                                <%--<td><span class="badge bg-red">${article.viewCnt}</span></td>--%>
                                <%--</tr>--%>
                                <%--</c:forEach>--%>
                                </tbody>
                            </table>
                            <div class="text-center">
                                <ul class="pagination">
                                    <li class="active"><a>1</a></li>
                                    <li><a>2</a></li>
                                    <li><a>3</a></li>
                                    <li><a>4</a></li>
                                    <%--<c:if test="${pageMaker.prev}">--%>
                                    <%--<li><a href="${path}/article/paging/search/list${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>--%>
                                    <%--</c:if>--%>
                                    <%--<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">--%>
                                    <%--<li <c:out value="${pageMaker.criteria.page == idx ? 'class=active' : ''}"/>>--%>
                                    <%--<a href="${path}/article/paging/search/list${pageMaker.makeSearch(idx)}">${idx}</a>--%>
                                    <%--</li>--%>
                                    <%--</c:forEach>--%>
                                    <%--<c:if test="${pageMaker.next && pageMaker.endPage > 0}">--%>
                                    <%--<li><a href="${path}/article/paging/search/list${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>--%>
                                    <%--</c:if>--%>
                                </ul>
                            </div>
                        </div>
                        <!-- /.tab-pane -->
                    </div>
                    <!-- /.tab-content -->
                </div>
                <!-- /.nav-tabs-custom -->
            </div>
            <!-- /.col -->

        </section>
    </div>
    <%-- /.content-wrapper --%>

        <div class="modal fade" id="myInfoMod">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span></button>
                        <h4 class="modal-title">회원정보 수정</h4>
                    </div>
                    <div class="modal-body">
                        <form action="${path}/user/register" method="post">
                            <div class="form-group has-feedback">
                                <input type="text" name="userId" class="form-control" placeholder="아아디" readonly>
                                <span class="glyphicon glyphicon-exclamation-sign form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <input type="text" name="userName" class="form-control" placeholder="이름">
                                <span class="glyphicon glyphicon-user form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                            <input type="email" name="userEmail" class="form-control" placeholder="이메일">
                            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <input type="password" name="userPw" class="form-control" placeholder="비밀번호">
                                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <textarea class="form-control" id="userIntroduction" rows="3" placeholder="자기소개" style="resize: none"></textarea>
                                <span class="glyphicon glyphicon-pencil form-control-feedback"></span>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                        <button type="button" class="btn btn-primary">수정 저장</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="myPwMod">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span></button>
                        <h4 class="modal-title">비밀번호 수정</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group has-feedback">
                            <input type="password" class="form-control" placeholder="현재 비밀번호">
                            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <input type="password" name="userPw" class="form-control" placeholder="변경할 비밀번호">
                            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <input type="password" class="form-control" placeholder="변경할 비밀번호 확인">
                            <span class="glyphicon glyphicon-check form-control-feedback"></span>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                        <button type="button" class="btn btn-primary">비밀번호 수정</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="myProfileImgMod">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span></button>
                        <h4 class="modal-title">프로필 이미지 수정</h4>
                    </div>
                    <div class="modal-body">
                        <div class="text-center">
                            <div class="fileinput fileinput-new" data-provides="fileinput">
                                <div class="fileinput-new thumbnail" style="width: 150px; height: 150px;">
                                    <img src="${path}/resources/dist/img/default-user.png" alt="...">
                                </div>
                                <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 200px;"></div>
                                <div>
                                    <span class="btn btn-default btn-file">
                                        <span class="fileinput-new">이미지 선택</span>
                                        <span class="fileinput-exists">변경</span>
                                        <input type="file" name="...">
                                    </span>
                                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">제거</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                        <button type="button" class="btn btn-primary">수정 저장</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="Withdrawal" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span></button>
                        <h4 class="modal-title">회원 탈퇴</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group has-feedback">
                            <input type="password" class="form-control" placeholder="비밀번호를 입력해주세요">
                            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                        <button type="button" class="btn btn-primary">회원 탈퇴</button>
                    </div>
                </div>
            </div>
        </div>

    <%--main_footer.jsp--%>
    <%@ include file="../include/main_footer.jsp" %>
</div>
<%-- ./wrapper --%>
<%--plugin_js.jsp--%>
<%@ include file="../include/plugin_js.jsp" %>
<script>
    $(document).ready(function () {

    });
</script>
</body>
</html>