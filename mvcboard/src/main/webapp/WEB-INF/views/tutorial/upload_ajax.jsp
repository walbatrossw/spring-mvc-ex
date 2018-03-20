<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%--head.jsp--%>
<%@ include file="../include/head.jsp" %>
<script src="https://rawgit.com/enyo/dropzone/master/dist/dropzone.js"></script>
<link rel="stylesheet" href="https://rawgit.com/enyo/dropzone/master/dist/dropzone.css">
<style>
    .fileDrop {
        width: 100%;
        height: 200px;
        border: 4px dotted #0b58a2;
    }

    small {
        margin-left: 3px;
        font-weight: bold;
        color: #0b58a2;
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
                파일 업로드 (Ajax)
                <small>스프링연습예제</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> fileupload</a></li>
                <li class="active">ajax form</li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">
            <div class="col-lg-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">파일 업로드 (Ajax) 입력폼</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">

                        <div class="fileDrop">
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                            <p class="text-center">업로드 할 파일을 탐색기에서 드래그해주세요.</p>
                        </div>
                        <hr/>
                        <div class="uploadedList"></div>
                    </div>
                    <!-- /.box-body -->
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

    $(".fileDrop").on("dragenter dragover", function (event) {
        event.preventDefault();
    });

    $(".fileDrop").on("drop", function (event) {
        // 기본 이벤트 처리 방지
        event.preventDefault();
        // 이벤트와 같이 전달된 데이터들을 가져와 files에 저장
        var files = event.originalEvent.dataTransfer.files;
        // 단일 파일업로드이기 때문에 첫번째 파일을 file에 저장
        var file = files[0];
        // 콘솔에 출력
        console.log(file);

        // FormData객체를 통해 form태그처럼 파일데이터를 전송 : IE의 경우 버전이 10이상만 지원
        var formData = new FormData();
        // FormData에 데이터를 key, value의 형태로 저장
        formData.append("file", file);

        // $.ajax()를 이용해서 formData객체에 저장된 파일데이터를 전송하기 위해서는 processData, contentType의 속성을
        // false로 지정해야함
        $.ajax({
            url: "/file/ajax/upload",
            data: formData,
            dataType: "text",
            processData: false,
            contentType: false,
            type: "POST",
            success: function (data) {
                alert(data);
                var str = "";
                if (checkImageType(data)) {
                    str = "<div>"
                            + "<a href='display?fileName="+getImageLink(data)+"'>"
                                + "<img src='display?fileName=" + data + "'/>"
                            + "</a>"
                            + "<small data-src="+data+">X</small>"
                        + "</div>"
                        + "<hr/>";
                } else {
                    str = "<div>"
                            + "<a href='display?fileName="+data+"'>"
                                + "<i class='fa fa-file'></i> "
                                + getOriginalName(data)
                            + "</a><small data-src="+data+">X</small>"
                        + "</div>"
                        + "<hr/>";
                }
                $(".uploadedList").append(str);
            }
        });
    });
    
    function checkImageType(fileName) {

        var pattern = /jpg$|gif$|png$|jpge$/i;

        return fileName.match(pattern);
    }

    function getOriginalName(fileName) {
        if (checkImageType(fileName)) {
            return;
        }
        var idx = fileName.indexOf("_") + 1;
        return fileName.substr(idx);
    }

    function getImageLink(fileName) {
        if (!checkImageType(fileName)) {
            return;
        }
        var front = fileName.substr(0, 12); // /년/월/일 경로 추출
        var end = fileName.substr(14);      // _s 썸네일 표시 제거
        return front + end;
    }

    // 파일 삭제 처리
    $(".uploadedList").on("click", "small", function (event) {
        var that = $(this);
        $.ajax({
            url: "/file/ajax/delete",
            type: "post",
            data: {fileName:$(this).attr("data-src")},
            dataType: "text",
            success: function (result) {
                if (result == "DELETED") {
                    alert("삭제되었습니다.");
                    that.parent("div").remove();
                }

            }
        });
    })
    
</script>
</body>
</html>