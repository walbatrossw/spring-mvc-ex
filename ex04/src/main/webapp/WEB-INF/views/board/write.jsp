<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%--head.jsp--%>
<%@ include file="../include/head.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>
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
                게시글 쓰기
                <small>스프링연습예제</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> board</a></li>
                <li class="active">write</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">
            <div class="col-lg-12">
                <form role="form" id="regForm" method="post" action="${path}/board/write">
                    <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">게시글 쓰기</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <div class="form-group">
                            <label for="title">제목</label>
                            <input class="form-control" id="title" name="title" placeholder="제목을 입력해주세요">
                        </div>
                        <div class="form-group">
                            <label for="content">내용</label>
                            <textarea class="form-control" id="content" name="content" rows="30"
                                      placeholder="내용을 입력해주세요" style="resize: none;"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="writer">작성자</label>
                            <input class="form-control" id="writer" name="writer" placeholder="작성자를 입력해주세요">
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
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <div>
                            <hr>
                        </div>
                        <ul class="mailbox-attachments clearfix uploadedList"></ul>
                    </div>
                    <!-- /.box-footer -->
                </div>
                    <div>
                        <button type="button" class="btn btn-primary"><i class="fa fa-list"></i> 목록</button>
                        <div class="pull-right">
                            <button type="reset" class="btn btn-warning"><i class="fa fa-reply"></i> 초기화</button>
                            <button type="submit" class="btn btn-success"><i class="fa fa-save"></i> 저장</button>
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
<script type="text/javascript" src="/resources/dist/js/upload.js"></script>
<script id="template" type="text/x-handlebars-template">
    <li>
        <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
        <div class="mailbox-attachment-info">
            <a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
            <a href="{{fullName}}" class="btn btn-default btn-xs pull-right delBtn"><i class="fa fa-fw fa-remove"></i></a>
        </div>
    </li>
</script>
<script>
    $(document).ready(function () {

        var template = Handlebars.compile($("#template").html());

        $(".fileDrop").on("dragenter dragover", function (event) {
            event.preventDefault();
        });

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
                    var fileInfo = getFileInfo(data);
                    var html = template(fileInfo);
                    $(".uploadedList").append(html);
                }
            });
        });

        $("#regForm").submit(function (event) {
            event.preventDefault();
            var that = $(this);
            var str = "";
            $(".uploadedList .delBtn").each(function (index) {
                str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href")+"'>"
            });
            that.append(str);
            that.get(0).submit();
        });

    });
</script>
</body>
</html>