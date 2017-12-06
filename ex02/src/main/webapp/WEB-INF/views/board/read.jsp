<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<%--head.jsp--%>
<%@ include file="../include/head.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>

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
                게시판 예제
                <small>일반적인 조회페이지</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 게시판</a></li>
                <li class="active">일반</li>
                <li class="active">조회</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">

            <div class="col-lg-12">
                <%--게시글 영역--%>
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">제목 : ${boardVO.title}</h3>
                        <ul class="list-inline pull-right">
                            <li><a href="#" class="link-black text-lg"><i class="fa fa-share margin-r-5"></i>게시글 공유</a></li>
                            <li><a href="#" class="link-black text-lg"><i class="fa fa-bookmark margin-r-5"></i>게시글 북마크</a></li>
                            <li><a href="#" class="link-black text-lg"><i class="fa fa-thumbs-o-up margin-r-5"></i>게시글 추천(0)</a></li>
                        </ul>
                    </div>

                    <div class="box-body" style="height: 600px">
                        <p>
                            ${boardVO.content}
                        </p>
                    </div>
                    <div class="box-footer">
                        <div class="user-block">
                            <img class="img-circle img-bordered-sm" src="/dist/img/default-user-image.jpg" alt="user image">
                            <span class="username">
                                <a href="#">${boardVO.writer}</a>
                            </span>
                            <span class="description"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}"/></span>
                        </div>
                    </div>
                </div>

                <%--버튼 영역--%>
                <div>
                    <form role="form" method="post">
                        <%--페이징 정보 유지를 위한 입력 form--%>
                        <input type="hidden" name="bno" value="${boardVO.bno}">
                        <input type="hidden" name="page" value="${criteria.page}">
                        <input type="hidden" name="perPageNum" value="${criteria.perPageNum}">
                        <input type="hidden" name="searchType" value="${criteria.searchType}">
                        <input type="hidden" name="keyword" value="${criteria.keyword}">
                    </form>

                    <button type="submit" class="btn btn-warning modBtn"><i class="fa fa-edit"></i> 수정</button>
                    <button type="submit" class="btn btn-danger delBtn"><i class="fa fa-trash"></i> 삭제</button>
                    <button type="submit" class="btn btn-primary listBtn pull-right"><i class="fa fa-list"></i> 목록</button>
                </div>
                <br/>

                <%--댓글 등록 영역--%>
                <div class="box box-warning">
                    <div class="box-header with-border">
                        <a href="#" class="link-black text-lg"><i class="fa fa-pencil"></i> 댓글작성</a>
                    </div>
                    <div class="box-body">
                        <form class="form-horizontal">
                            <div class="form-group margin-bottom-none">
                                <div class="col-sm-8">
                                    <input class="form-control input-sm" type="text" placeholder="댓글 입력...">
                                </div>
                                <div class="col-sm-2">
                                    <input class="form-control input-sm" type="text" placeholder="작성자">
                                </div>
                                <div class="col-sm-2">
                                    <button type="button" class="btn btn-primary btn-sm btn-block"><i class="fa fa-save"></i> 저장</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <%--댓글 목록 영역--%>
                <div class="box box-success collapsed-box">
                    <div class="box-header with-border">
                        <a href="" class="link-black text-lg"><i class="fa fa-comments-o margin-r-5 replyCount"></i> </a>
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
    $(document).ready(function () {

        var formObj = $("form[role='form']");
        console.log(formObj);

        // 수정버튼 클릭시
        $(".modBtn").on("click", function () {
            formObj.attr("action", "/board/modify");
            formObj.attr("method", "get");
            formObj.submit();
        });

        // 삭제 버튼 클릭시
        $(".delBtn").on("click", function () {
            formObj.attr("action", "/board/remove");
            formObj.submit();
        });

        // 목록 버튼 클릭시
        $(".listBtn").on("click", function () {
            //self.location = "/board/list";
            $("input[name=bno]").remove();
            formObj.attr("method", "get");
            formObj.attr("action", "/board/list");
            formObj.submit();
        });

        // 수정완료시
        var result = "${msg}";
        if (result == "MODIFIED") {
            alert("게시글이 수정되었습니다.");
        }

        // 댓글 등록일자 출력을 위한 날짜/시간 문자열 처리
        Handlebars.registerHelper("prettifyDate", function (timeValue) {

            var dateObj = new Date(timeValue);
            var year = dateObj.getFullYear();
            var month = dateObj.getMonth() + 1;
            var date = dateObj.getDate();
            var hour = dateObj.getHours();
            var minutes = dateObj.getMinutes();
            return year+"-"+month+"-"+date + " " + hour + ":" + minutes;
        });

        // 댓글 리스트 출력 처리
        var printData = function (replyArr, target, templateObject) {

            var template = Handlebars.compile(templateObject.html());
            var html = template(replyArr);
            $(".replyDiv").remove();
            target.html(html);

        };

        // 게시글 번호
        var bno = ${boardVO.bno};
        // 댓글 페이지 초기화
        var replyPage = 1;

        getPage("/replies/" + bno + "/1");

        $(".pagination").on("click", "li a", function (event) {
            event.preventDefault();
            replyPage = $(this).attr("href");
            getPage("/replies/"+bno+"/"+replyPage);
        });

        // 댓글 페이징 처리
        function getPage(pageInfo) {
            
            $.getJSON(pageInfo, function (data) {
                printReplyCount(data.replyCount);
                printData(data.list, $(".repliesDiv"), $("#template"));
                printPaging(data.pageMaker, $(".pagination"))
            });
        }

        // 댓글 갯수 출력, 댓글 보기 버튼 활성/비활성
        var printReplyCount = function (replyCount) {

            if (replyCount > 0) {
                $(".replyCount").html(" 댓글목록 ("+replyCount+")");
            } else if (replyCount == 0) {
                $(".replyCount").html(" 댓글이 없습니다. 의견을 남겨주세요.");
                $(".collapsed-box").find(".box-tools").remove();
            }

        };

        // 하단페이징
        var printPaging = function (pageMaker, target) {

            var str = "";
            if (pageMaker.prev) {
                str += "<li><a href='"+(pageMaker.startPage - 1)+"'>&laquo;</a></li>"
            }
            for (var i =  pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
                var strClass = pageMaker.criteria.page == i ? "class=active": "";
                str += "<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>"
            }
            if (pageMaker.next) {
                str += "<li><a href='"+(pageMaker.endPage + 1)+"'>&raquo;</a></li>"
            }
            target.html(str);
        }

    });

</script>
<script id="template" type="text/x-handlebars-template">
    {{#each.}}
    <div class="post replyDiv">
        <div class="user-block">
            <img class="img-circle img-bordered-sm" src="/dist/img/default-user-image.jpg" alt="user image">
            <span class="username">
                <a href="#">{{replyer}}</a>
                <a href="#" class="pull-right btn-box-tool"><i class="fa fa-times"> 삭제</i></a>
                <a href="#" class="pull-right btn-box-tool"><i class="fa fa-edit"> 수정</i></a>
            </span>
            <span class="description">{{prettifyDate regdate}}</span>
        </div>
        <p>{{replytext}}</p>
        <ul class="list-inline">
            <li>
                <a href="#" class="link-black text-sm"><i class="fa fa-thumbs-o-up margin-r-5"></i> 댓글 추천(0)</a>
            </li>
        </ul>
    </div>
    {{/each}}
</script>
</body>
</html>