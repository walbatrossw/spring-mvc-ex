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
                게시글 수정
                <small>스프링연습예제</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> board</a></li>
                <li class="active">modify</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">

            <div class="col-lg-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">게시글 수정 - <small>게시글번호(${boardVO.bno})</small></h3>
                    </div>
                    <form role="form" method="post" action="${path}/board/modify">
                        <div class="box-body">
                            <%--페이지 이동을 위한 값 세팅--%>
                            <input type="hidden" name="bno" value="${boardVO.bno}">

                            <div class="form-group">
                                <label for="title">제목</label>
                                <input class="form-control"
                                       id="title"
                                       name="title"
                                       value="${boardVO.title}"
                                       placeholder="제목을 입력해주세요">
                            </div>
                            <div class="form-group">
                                <label for="content">내용</label>
                                <textarea class="form-control" id="content" name="content" rows="30"
                                          placeholder="내용을 입력해주세요" style="resize: none;">${boardVO.content}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="writer">작성자</label>
                                <input class="form-control" id="writer" name="writer"
                                       value="${boardVO.writer}"
                                       placeholder="작성자를 입력해주세요" readonly>
                            </div>
                            <div class="form-group">
                                <div class="btn btn-default btn-file">
                                    <i class="fa fa-paperclip"></i> 첨부파일
                                    <input type="file">
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="box-footer">
                        <button type="submit" class="btn btn-primary listBtn"><i class="fa fa-list"></i> 목록</button>
                        <div class="pull-right">
                            <button type="reset" class="btn btn-warning cancelBtn"><i class="fa fa-reply"></i> 취소</button>
                            <button type="submit" class="btn btn-success modifyBtn"><i class="fa fa-edit"></i> 수정</button>
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

        /*======================================== 게시글 페이지 이동 관련 ========================================*/

        // form 선택자
        var formObj = $("form[role='form']");
        console.log(formObj);

        // 수정버튼 클릭시
        $(".modifyBtn").on("click", function () {
            formObj.submit();
        });

        // 취소버튼 클릭시
        $(".cancelBtn").on("click", function () {
            history.go(-1); // <iframe>의 내부에 들어가 있을 경우 사용 X
            //self.location = "/board/read?bno="+"${boardVO.bno}";
        });

        // 목록버튼 클릭시
        $(".listBtn").on("click", function () {
            self.location = "/board/list";
        });

    });
</script>
</body>
</html>