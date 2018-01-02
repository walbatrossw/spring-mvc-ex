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
                회원 프로필 페이지
                <small>스프링연습예제</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> user</a></li>
                <li class="active">profile</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">

            <div class="row">
                <div class="col-md-5">
                    <div class="box box-primary">
                        <div class="box-body box-profile">
                            <img class="profile-user-img img-responsive img-circle"
                                 src="${path}/dist/img/profile/${login.uimage}" alt="User profile picture">

                            <h3 class="profile-username text-center">${login.uname}</h3>
                            <div class="text-center">
                                <a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#userPhotoModal">
                                    <i class="fa fa-photo"> 프로필사진 수정</i>
                                </a>
                            </div>
                            <ul class="list-group list-group-unbordered">
                                <li class="list-group-item">
                                    <b>아이디</b> <a class="pull-right">${login.uid}</a>
                                </li>
                                <li class="list-group-item">
                                    <b>이메일</b> <a class="pull-right">${login.uemail}</a>
                                </li>
                                <li class="list-group-item">
                                    <b>가입일자</b>
                                    <a class="pull-right">
                                        <fmt:formatDate value="${login.regdate}" pattern="yyyy-MM-dd a hh:mm"/>
                                    </a>
                                </li>
                                <li class="list-group-item">
                                    <b>최근 로그인 일자</b>
                                    <a class="pull-right">
                                        <fmt:formatDate value="${login.logdate}" pattern="yyyy-MM-dd a hh:mm"/>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="box-footer text-center">
                            <a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#userInfoModal">
                                <i class="fa fa-info-circle"> 회원정보 수정</i>
                            </a>
                            <a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#userPwModal">
                                <i class="fa fa-question-circle"> 비밀번호 수정</i>
                            </a>
                            <a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#userOutModal">
                                <i class="fa fa-user-times"> 회원 탈퇴</i>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="col-md-7">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#myPosts" data-toggle="tab"><i class="fa fa-pencil-square-o"></i> 나의 게시물</a></li>
                            <li><a href="#myReplies" data-toggle="tab"><i class="fa fa-comment-o"></i> 나의 댓글</a></li>
                            <li><a href="#myBookmarks" data-toggle="tab"><i class="fa fa-bookmark-o"></i> 나의 스크랩</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="active tab-pane" id="myPosts">
                                <table id="myPostsTable" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th style="width: 20px">번호</th>
                                            <th>제목</th>
                                            <th style="width: 150px">작성시간</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="boardVO" varStatus="i" items="${userBoardList}">
                                    <tr>
                                        <td>${i.index + 1}</td>
                                        <td>
                                            <a href="${path}/board/read?bno=${boardVO.bno}">
                                                <c:choose>
                                                    <c:when test="${fn:length(boardVO.title) > 30}">
                                                        <c:out value="${fn:substring(boardVO.title, 0, 29)}"/>....
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${boardVO.title}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </a>
                                        </td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd a HH:mm" value="${boardVO.regdate}"/></td>
                                    </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane" id="myReplies">
                                <table id="myRepliesTable" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th style="width: 10px">번호</th>
                                            <th style="width: 250px">게시글 제목</th>
                                            <th style="width: 250px">내용</th>
                                            <th style="width: 150px">작성시간</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="userReply" varStatus="i" items="${userReplies}">
                                        <tr>
                                            <td>${i.index + 1}</td>
                                            <td>
                                                <a href="${path}/board/read?bno=${userReply.boardVO.bno}">
                                                    <c:choose>
                                                        <c:when test="${fn:length(userReply.boardVO.title) > 10}">
                                                            <c:out value="${fn:substring(userReply.boardVO.title, 0, 9)}"/>....
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${userReply.boardVO.title}"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </a>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${fn:length(userReply.replytext) > 10}">
                                                        <c:out value="${fn:substring(userReply.replytext, 0, 9)}"/>....
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${userReply.replytext}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td><fmt:formatDate pattern="yyyy-MM-dd a HH:mm" value="${userReply.regdate}"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane" id="myBookmarks">
                                <table id="myBookmarksTable" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th style="width: 10px">번호</th>
                                            <th>제목</th>
                                            <th>작성자</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="bookmark" varStatus="i" items="${bookmarkList}">
                                        <tr>
                                            <td>${i.index + 1}</td>
                                            <td>
                                                <a href="${path}/board/read?bno=${bookmark.boardVO.bno}">
                                                    <c:choose>
                                                        <c:when test="${fn:length(bookmark.boardVO.title) > 30}">
                                                            <c:out value="${fn:substring(bookmark.boardVO.title, 0, 29)}"/>....
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${bookmark.boardVO.title}"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </a>
                                            </td>
                                            <td>${bookmark.boardVO.writer}</td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </section>
    </div>
    <%-- /.content-wrapper --%>

    <%--main_footer.jsp--%>
    <%-- Main Footer --%>
    <%@ include file="../include/main_footer.jsp" %>

        <div class="modal fade" id="userPhotoModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">프로필 사진을 수정하시겠습니까?</h4>
                    </div>
                    <div class="modal-body" align="center">
                        <form action="${path}/user/modify/image" id="userImageForm" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="uid" value="${login.uid}">
                            <div class="fileinput fileinput-new" data-provides="fileinput">
                                <div class="fileinput-new thumbnail" style="width: 200px; height: 210px;">
                                    <img src="${path}/dist/img/profile${login.uimage}" >
                                </div>
                                <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 300px; max-height: 300px;"></div>
                                <div>
                                <span class="btn btn-default btn-file">
                                    <span class="fileinput-new">이미지 선택</span>
                                    <span class="fileinput-exists">변경</span>
                                    <input type="file" name="file" id="file">
                                </span>
                                    <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">삭제</a>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                        <button type="button" class="btn btn-primary imgModBtn">수정 저장</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="userInfoModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">회원정보를 수정하시겠습니까?</h4>
                    </div>
                    <div class="modal-body" data-rno>
                        <form action="${path}/user/modify/info" id="userInfoForm" method="post">
                            <div class="form-group has-feedback">
                                <label for="uid">아이디</label>
                                <input type="text" name="uid" id="uid" class="form-control" placeholder="아아디" value="${login.uid}" readonly>
                                <span class="glyphicon glyphicon-exclamation-sign form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <label for="uname">이름</label>
                                <input type="text" name="uname" id="uname" class="form-control" placeholder="이름" value="${login.uname}">
                                <span class="glyphicon glyphicon-user form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <label for="uemail">이메일</label>
                                <input type="email" name="uemail" id="uemail" class="form-control" placeholder="이메일" value="${login.uemail}">
                                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <label for="upw">비밀번호</label>
                                <input type="password" name="upw" id="upw" class="form-control" placeholder="비밀번호">
                                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                        <button type="button" class="btn btn-primary infoModBtn">수정 저장</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="userPwModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">비밀번호를 수정하시겠습니까?</h4>
                    </div>
                    <div class="modal-body" data-rno>
                        <form action="${path}/user/modify/pw" id="userPwForm" method="post">
                            <div class="form-group has-feedback">
                                <label for="oldPw">현재 비밀번호</label>
                                <input type="hidden" name="uid" value="${login.uid}">
                                <input type="password" name="oldPw" id="oldPw" class="form-control" placeholder="비밀번호">
                                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <label for="newPw">새로운 비밀번호</label>
                                <input type="password" name="newPw" id="newPw" class="form-control" placeholder="비밀번호">
                                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <label for="upw">새로운 비밀번호 확인</label>
                                <input type="password" id="newPwCheck" class="form-control" placeholder="비밀번호확인">
                                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                        <button type="button" class="btn btn-primary pwModBtn">수정 저장</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="userOutModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">회원을 탈퇴하시겠습니까?</h4>
                    </div>
                    <div class="modal-body" data-rno>
                        모든정보를 삭제합니다.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">아니요.</button>
                        <button type="button" class="btn btn-primary myInfoModModalBtn">예 탈퇴하겠습니다.</button>
                    </div>
                </div>
            </div>
        </div>

</div>
<%-- ./wrapper --%>
<%--plugin_js.jsp--%>
<%@ include file="../include/plugin_js.jsp" %>
<script>
    $(document).ready(function () {
        var msg = "${msg}";

        if (msg == "FAILURE") {
            alert("비밀번호가 일치하지 않습니다. 비밀번호를 확인해주세요");
        } else if (msg == "FAIL") {
            alert("이미지가 존재하지 않습니다.");
        } else if (msg == "SUCCESS") {
            alert("수정되었습니다.");
        }

        // 회원정보 수정
        $(".infoModBtn").on("click", function () {
            $("#userInfoForm").submit();
        });

        // 회원비밀번호 수정
        $(".pwModBtn").on("click", function () {
            $("#userPwForm").submit();
        });

        // 회원 프로필 이미지 수정
        $(".imgModBtn").on("click", function () {
            var file = $("#file").val();
            if (file == "") {
                alert("파일을 선택해주세요.");
                return;
            }
            $("#userImageForm").submit();
        });

        var param = {
            "language": {
                "lengthMenu": "_MENU_ 개씩 보기",
                "zeroRecords": "내용이 없습니다.",
                "info": "현재 _PAGE_ 페이지 / 전체 _PAGES_ 페이지",
                "infoEmpty": "내용이 없습니다.",
                "infoFiltered": "( _MAX_개의 전체 목록 중에서 검색된 결과)",
                "search": "검색:",
                "paginate": {
                    "first": "처음",
                    "last": "마지막",
                    "next": "다음",
                    "previous": "이전"
                }
            }
        };

        $("#myPostsTable").DataTable(param);
        $("#myRepliesTable").DataTable(param);
        $("#myBookmarksTable").DataTable(param);


    });
</script>
</body>
</html>