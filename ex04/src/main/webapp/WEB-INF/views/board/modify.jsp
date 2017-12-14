<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%--head.jsp--%>
<%@ include file="../include/head.jsp" %>
<style>
    .fileDrop {
        width: 100%;
        height: 200px;
        border: 2px dotted #0b58a2;
    }
</style>
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
                <form role="form" id="modForm" method="post" action="${path}/board/modify">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">게시글 수정 - <small>게시글번호(${boardVO.bno})</small></h3>
                        </div>
                            <div class="box-body">
                                <%--페이지 이동을 위한 값 세팅--%>
                                <input type="hidden" name="bno" value="${boardVO.bno}">
                                <input type="hidden" name="page" value="${criteria.page}">
                                <input type="hidden" name="perPageNum" value="${criteria.perPageNum}">
                                <input type="hidden" name="searchType" value="${criteria.searchType}">
                                <input type="hidden" name="keyword" value="${criteria.keyword}">
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
                                    <div class="fileDrop">
                                        <br/>
                                        <br/>
                                        <br/>
                                        <br/>
                                        <p class="text-center"><i class="fa fa-paperclip"></i> 첨부파일을 드래그해주세요.</p>
                                    </div>
                                </div>
                            </div>
                        <div class="box-footer">
                            <ul class="mailbox-attachments clearfix uploadedList"></ul>
                        </div>
                        <div class="box-footer">
                            <button type="button" class="btn btn-primary listBtn"><i class="fa fa-list"></i> 목록</button>
                            <div class="pull-right">
                                <button type="button" class="btn btn-warning cancelBtn"><i class="fa fa-reply"></i> 취소</button>
                                <button type="submit" class="btn btn-success modifyBtn"><i class="fa fa-edit"></i> 수정</button>
                            </div>
                        </div>
                    </div>
                </form>
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
<%--Handlebars JS--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>
<%--업로드 JS--%>
<script type="text/javascript" src="/resources/dist/js/upload.js"></script>

<%--첨부파일 하나의 영역--%>
<%--이미지--%>
<script id="templatePhotoAttach" type="text/x-handlebars-template">
    <li>
        <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
        <div class="mailbox-attachment-info">
            <a href="{{getLink}}" class="mailbox-attachment-name" data-lightbox="uploadImages"><i class="fa fa-camera"></i> {{fileName}}</a>
            <a href="{{fullName}}" class="btn btn-default btn-xs pull-right delBtn"><i class="fa fa-fw fa-remove"></i></a>
        </div>
    </li>
</script>
<%--일반 파일--%>
<script id="templateFileAttach" type="text/x-handlebars-template">
    <li>
        <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
        <div class="mailbox-attachment-info">
            <a href="{{getLink}}" class="mailbox-attachment-name"><i class="fa fa-paperclip"></i> {{fileName}}</a>
            <a href="{{fullName}}" class="btn btn-default btn-xs pull-right delBtn"><i class="fa fa-fw fa-remove"></i></a>
        </div>
    </li>
</script>
<script>
    $(document).ready(function () {

        // 전역 변수 선언
        var bno = ${boardVO.bno}; // 현재 게시글 번호
        var templatePhotoAttach = Handlebars.compile($("#templatePhotoAttach").html()); // 이미지 Template
        var templateFileAttach = Handlebars.compile($("#templateFileAttach").html());   // 일반파일 Template

        /*======================================== 첨부파일 입력 처리 ========================================*/

        // 전체 페이지 파일 끌어 놓기 기본 이벤트 방지 : 지정된 영역외에 파일 드래그 드랍시 페이지 이동방지
        $(".content-wrapper").on("dragenter dragover drop", function (event) {
            event.preventDefault();
        });

        // 파일 끌어 놓기 기본 이벤트 방지
        $(".fileDrop").on("dragenter dragover", function (event) {
            event.preventDefault();
        });

        // 파일 드랍 이벤트 : 파일 전송 처리
        $(".fileDrop").on("drop", function (event) {
            event.preventDefault();
            var files = event.originalEvent.dataTransfer.files;
            var file = files[0];
            var formData = new FormData();
            formData.append("file", file);
            $.ajax({
                url: "/fileupload/uploadAjax",
                data: formData,
                dataType: "text",
                processData: false,
                contentType: false,
                type: "POST",
                success: function (data) {
                    // 파일정보 가공
                    var fileInfo = getFileInfo(data);
                    // 이미지 파일일 경우
                    if (fileInfo.fullName.substr(12, 2) == "s_") {
                        var html = templatePhotoAttach(fileInfo);
                    // 이미지 파일이 아닐경우
                    } else {
                        html = templateFileAttach(fileInfo);
                    }
                    // 목록에 출력
                    $(".uploadedList").append(html);
                }
            });
        });

        // 수정 처리시 파일 정보도 함께 처리
        $("#modForm").submit(function (event) {
            event.preventDefault();
            var that = $(this);
            var str = "";
            $(".uploadedList .delBtn").each(function (index) {
                str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href")+"'>"
            });
            that.append(str);
            that.get(0).submit();
        });

        // 파일 삭제 버튼 클릭 이벤트
        $(document).on("click", ".delBtn", function (event) {
            event.preventDefault();
            if (confirm("삭제하시겠습니까? 삭제된 파일은 복구할 수 없습니다.")) {
                var that = $(this);
                $.ajax({
                    url: "/fileupload/deleteFile/" + bno,
                    type: "post",
                    data: {fileName:$(this).attr("href")},
                    dataType: "text",
                    success: function (result) {
                        if (result == "DELETED") {
                            alert("삭제되었습니다.");
                            that.parents("li").remove();
                        }

                    }
                });
            }
        });

        /*======================================== 첨부파일 목록 ========================================*/

        $.getJSON("/board/getAttach/" + bno, function (list) {
            $(list).each(function () {
                var fileInfo = getFileInfo(this);
                // 이미지 파일일 경우
                if (fileInfo.fullName.substr(12, 2) == "s_") {
                    var html = templatePhotoAttach(fileInfo);
                // 이미지 파일이 아닐 경우
                } else {
                    html = templateFileAttach(fileInfo);
                }

                $(".uploadedList").append(html);
            })
        });

        /*======================================== 게시글 페이지 이동 관련 ========================================*/

        // form 선택자
        var formObj = $("form[role='form']");
        console.log(formObj);

        // 수정버튼 클릭시
        // $(".modifyBtn").on("click", function () {
        //     formObj.submit();
        // });

        // 취소버튼 클릭시
        $(".cancelBtn").on("click", function () {
            history.go(-1); // <iframe>의 내부에 들어가 있을 경우 사용 X
            //self.location = "/board/read?bno="+"${boardVO.bno}";
        });

        // 목록버튼 클릭시
        $(".listBtn").on("click", function () {
            self.location = "/board/list?page=${criteria.page}&perPageNum=${criteria.perPageNum}"
                            + "&searchType=${criteria.searchType}"
                            + "&keyword=${criteria.keyword}";
        });

    });
</script>
</body>
</html>