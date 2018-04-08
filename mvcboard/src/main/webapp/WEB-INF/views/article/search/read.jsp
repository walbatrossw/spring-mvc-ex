<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@ include file="../../include/head.jsp" %>

<body class="hold-transition skin-blue sidebar-mini layout-boxed">

<div class="wrapper">

    <!-- Main Header -->
    <%@ include file="../../include/main_header.jsp" %>

    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="../../include/left_column.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                게시판
                <small>조회페이지(페이징+검색)</small>
            </h1>
            <ol class="breadcrumb">
                <li><i class="fa fa-edit"></i> article</li>
                <li class="active"> read</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">

            <div class="col-lg-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">글제목 : ${article.title}</h3>
                    </div>
                    <div class="box-body" style="height: 700px">
                        ${article.content}
                    </div>

                    <%--업로드 파일 정보 영역--%>
                    <div class="box-footer uploadFiles">
                        <ul class="mailbox-attachments clearfix uploadedFileList"></ul>
                    </div>

                    <div class="box-footer">
                        <div class="user-block">
                            <img class="img-circle img-bordered-sm" src="${path}/dist/img/default-user.png" alt="user image">
                            <span class="username">
                                <a href="#">${article.writer}</a>
                            </span>
                            <span class="description"><fmt:formatDate pattern="yyyy-MM-dd a HH:mm"
                                                                      value="${article.regDate}"/></span>
                        </div>
                    </div>
                    <div class="box-footer">
                        <form role="form" method="post">
                            <input type="hidden" name="articleNo" value="${article.articleNo}">
                            <input type="hidden" name="page" value="${searchCriteria.page}">
                            <input type="hidden" name="perPageNum" value="${searchCriteria.perPageNum}">
                            <input type="hidden" name="searchType" value="${searchCriteria.searchType}">
                            <input type="hidden" name="keyword" value="${searchCriteria.keyword}">
                        </form>
                        <button type="submit" class="btn btn-primary listBtn"><i class="fa fa-list"></i> 목록</button>
                        <c:if test="${login.userId == article.writer}">
                            <div class="pull-right">
                                <button type="submit" class="btn btn-warning modBtn"><i class="fa fa-edit"></i> 수정</button>
                                <button type="submit" class="btn btn-danger delBtn"><i class="fa fa-trash"></i> 삭제</button>
                            </div>
                        </c:if>
                    </div>
                </div>

                <div class="box box-warning">
                    <div class="box-header with-border">
                        <a class="link-black text-lg"><i class="fa fa-pencil margin-r-5"></i> 댓글 쓰기</a>
                    </div>
                    <div class="box-body">
                        <c:if test="${not empty login}">
                            <form>
                                <div class="form-group">
                                    <textarea class="form-control" id="newReplyText" rows="3" placeholder="댓글내용..."style="resize: none"></textarea>
                                </div>
                                <div class="col-sm-2" hidden>
                                    <input class="form-control" id="newReplyWriter" type="text" value="${login.userId}" readonly>
                                </div>
                                <button type="button" class="btn btn-default btn-block replyAddBtn">
                                    <i class="fa fa-save"></i> 댓글 저장
                                </button>
                            </form>
                        </c:if>
                        <c:if test="${empty login}">
                            <a href="${path}/user/login" class="btn btn-default btn-block" role="button">
                                <i class="fa fa-edit"></i> 로그인 한 사용자만 댓글 등록이 가능합니다.
                            </a>
                        </c:if>
                    </div>
                </div>

                <div class="box box-success collapsed-box">
                    <%--댓글 유무 / 댓글 갯수 / 댓글 펼치기, 접기--%>
                    <div class="box-header with-border">
                        <a class="link-black text-lg"><i class="fa fa-comments-o margin-r-5 replyCount"></i> </a>
                        <div class="box-tools">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse">
                                <i class="fa fa-plus"></i>
                            </button>
                        </div>
                    </div>
                    <%--댓글 목록--%>
                    <div class="box-body repliesDiv">

                    </div>
                    <%--댓글 페이징--%>
                    <div class="box-footer">
                        <div class="text-center">
                            <ul class="pagination pagination-sm no-margin">

                            </ul>
                        </div>
                    </div>
                </div>

                <%--댓글 수정 modal 영역--%>
                <div class="modal fade" id="modModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">댓글수정</h4>
                            </div>
                            <div class="modal-body" data-rno>
                                <input type="hidden" class="replyNo"/>
                                <%--<input type="text" id="replytext" class="form-control"/>--%>
                                <textarea class="form-control" id="replyText" rows="3" style="resize: none"></textarea>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                                <button type="button" class="btn btn-primary modalModBtn">수정</button>
                            </div>
                        </div>
                    </div>
                </div>

                <%--댓글 삭제 modal 영역--%>
                <div class="modal fade" id="delModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">댓글 삭제</h4>
                                <input type="hidden" class="rno"/>
                            </div>
                            <div class="modal-body" data-rno>
                                <p>댓글을 삭제하시겠습니까?</p>
                                <input type="hidden" class="rno"/>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">아니요.
                                </button>
                                <button type="button" class="btn btn-primary modalDelBtn">네. 삭제합니다.</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <%@ include file="../../include/main_footer.jsp" %>

</div>
<!-- ./wrapper -->
<%@ include file="../../include/plugin_js.jsp" %>
<script id="replyTemplate" type="text/x-handlebars-template">
    {{#each.}}
    <div class="post replyDiv" data-replyNo={{replyNo}}>
        <div class="user-block">
            <%--댓글 작성자 프로필사진 : 추후 이미지 업로드기능 구현 예정--%>
            <img class="img-circle img-bordered-sm" src="/dist/img/user1-128x128.jpg" alt="user image">
            <%--댓글 작성자--%>
            <span class="username">
                <%--작성자 이름--%>
                <a href="#">{{replyWriter}}</a>
                {{#eqReplyWriter replyWriter}}
                <%--댓글 삭제 버튼--%>
                <a href="#" class="pull-right btn-box-tool replyDelBtn" data-toggle="modal" data-target="#delModal">
                    <i class="fa fa-times"> 삭제</i>
                </a>
                <%--댓글 수정 버튼--%>
                <a href="#" class="pull-right btn-box-tool replyModBtn" data-toggle="modal" data-target="#modModal">
                    <i class="fa fa-edit"> 수정</i>
                </a>
                {{/eqReplyWriter}}
            </span>
            <%--댓글 작성일자--%>
            <span class="description">{{prettifyDate regDate}}</span>
        </div>
        <%--댓글 내용--%>
        <div class="oldReplyText">{{{escape replyText}}}</div>
        <br/>
    </div>
    {{/each}}
</script>
<script id="fileTemplate" type="text/x-handlebars-template">
    <li data-src="{{fullName}}">
        <span class="mailbox-attachment-icon has-img">
            <img src="{{imgSrc}}" alt="Attachment">
        </span>
        <div class="mailbox-attachment-info">
            <a href="{{originalFileUrl}}" class="mailbox-attachment-name">
                <i class="fa fa-paperclip"></i> {{originalFileName}}
            </a>
        </div>
    </li>
</script>
<script type="text/javascript" src="/resources/dist/js/article_file_upload.js"></script>
<script type="text/javascript" src="/resources/dist/js/reply.js"></script>
<script>
    $(document).ready(function () {

        Handlebars.registerHelper("eqReplyWriter", function (replyWriter, block) {
            var accum = "";
            if (replyWriter === "${login.userId}") {
                accum += block.fn();
            }
            return accum;
        });

        var articleNo = "${article.articleNo}";  // 현재 게시글 번호
        var replyPageNum = 1; // 댓글 페이지 번호 초기화

        // 첨부파일 목록
        getFiles(articleNo);

        // 댓글 목록 함수 호출
        getReplies("/replies/" + articleNo + "/" + replyPageNum);

        // 댓글 페이지 번호 클릭 이벤트
        $(".pagination").on("click", "li a", function (event) {
            event.preventDefault();
            replyPageNum = $(this).attr("href");
            getReplies("/replies/" + articleNo + "/" + replyPageNum);
        });

        // 댓글 저장 버튼 클릭 이벤트
        $(".replyAddBtn").on("click", function () {

            // 입력 form 선택자
            var replyWriterObj = $("#newReplyWriter");
            var replyTextObj = $("#newReplyText");
            var replyWriter = replyWriterObj.val();
            var replyText = replyTextObj.val();

            // 댓글 입력처리 수행
            $.ajax({
                type: "post",
                url: "/replies/",
                headers: {
                    "Content-Type": "application/json",
                    "X-HTTP-Method-Override": "POST"
                },
                dataType: "text",
                data: JSON.stringify({
                    articleNo: articleNo,
                    replyWriter: replyWriter,
                    replyText: replyText
                }),
                success: function (result) {
                    console.log("result : " + result);
                    if (result === "regSuccess") {
                        alert("댓글이 등록되었습니다.");
                        replyPageNum = 1;  // 페이지 1로 초기화
                        getReplies("/replies/" + articleNo + "/" + replyPageNum); // 댓글 목록 호출
                        replyTextObj.val("");   // 댓글 입력창 공백처리
                        replyWriterObj.val("");   // 댓글 입력창 공백처리
                    }
                }
            });
        });

        // 댓글 수정을 위해 modal창에 선택한 댓글의 값들을 세팅
        $(".repliesDiv").on("click", ".replyDiv", function (event) {
            var reply = $(this);
            $(".replyNo").val(reply.attr("data-replyNo"));
            $("#replyText").val(reply.find(".oldReplyText").text());
        });

        // modal 창의 댓글 수정버튼 클릭 이벤트
        $(".modalModBtn").on("click", function () {
            var replyNo = $(".replyNo").val();
            var replyText = $("#replyText").val();
            $.ajax({
                type: "put",
                url: "/replies/" + replyNo,
                headers: {
                    "Content-Type": "application/json",
                    "X-HTTP-Method-Override": "PUT"
                },
                dataType: "text",
                data: JSON.stringify({
                    replyText: replyText
                }),
                success: function (result) {
                    console.log("result : " + result);
                    if (result === "modSuccess") {
                        alert("댓글이 수정되었습니다.");
                        getReplies("/replies/" + articleNo + "/" + replyPageNum); // 댓글 목록 호출
                        $("#modModal").modal("hide"); // modal 창 닫기
                    }
                }
            })
        });

        // modal 창의 댓글 삭제버튼 클릭 이벤트
        $(".modalDelBtn").on("click", function () {
            var replyNo = $(".replyNo").val();
            $.ajax({
                type: "delete",
                url: "/replies/" + replyNo,
                headers: {
                    "Content-Type": "application/json",
                    "X-HTTP-Method-Override": "DELETE"
                },
                dataType: "text",
                success: function (result) {
                    console.log("result : " + result);
                    if (result === "delSuccess") {
                        alert("댓글이 삭제되었습니다.");
                        getReplies("/replies/" + articleNo + "/" + replyPageNum); // 댓글 목록 호출
                        $("#delModal").modal("hide"); // modal 창 닫기
                    }
                }
            });
        });

        var formObj = $("form[role='form']");
        console.log(formObj);

        $(".modBtn").on("click", function () {
            formObj.attr("action", "/article/paging/search/modify");
            formObj.attr("method", "get");
            formObj.submit();
        });

        $(".delBtn").on("click", function () {

            var replyCnt = $(".replyDiv").length;
            if (replyCnt > 0) {
                alert("댓글이 달린 게시글은 삭제할수 없습니다.");
                return;
            }

            var arr = [];
            $(".uploadedFileList li").each(function () {
                arr.push($(this).attr("data-src"));
            });

            if (arr.length > 0) {
                $.post("/article/file/deleteAll", {files: arr}, function () {

                });
            }

            formObj.attr("action", "/article/paging/search/remove");
            formObj.submit();
        });

        $(".listBtn").on("click", function () {
            formObj.attr("action", "/article/paging/search/list");
            formObj.attr("method", "get");
            formObj.submit();
        });

    });
</script>

</body>
</html>