
$(document).ready(function () {

    // 전역변수
    var fileDropDiv = $(".fileDrop");
    var templatePhotoAttach = Handlebars.compile($("#templatePhotoAttach").html()); // 이미지 template
    var templateFileAttach = Handlebars.compile($("#templateFileAttach").html());   // 일반파일 template

    // 전체 페이지 파일 끌어 놓기 기본 이벤트 방지 : 지정된 영역외에 파일 드래그 드랍시 페이지 이동방지
    $(".content-wrapper").on("dragenter dragover drop", function (event) {
        event.preventDefault();
    });


    // 파일 끌어 놓기 기본 이벤트 방지
    fileDropDiv.on("dragenter dragover", function (event) {
        event.preventDefault();
    });

    // 파일 업로드
    // 파일 드랍 이벤트 : 파일 전송 처리, 파일 화면 출력
    fileDropDiv.on("drop", function (event) {
        event.preventDefault();
        var files = event.originalEvent.dataTransfer.files;
        var file = files[0];
        var formData = new FormData();
        formData.append("file", file);
        $.ajax({
            url: "/article/file/upload",
            data: formData,
            dataType: "text",
            processData: false,
            contentType: false,
            type: "POST",
            success: function (data) {
                // 파일정보 가공
                var fileInfo = getFileInfo(data);
                // 이미지 파일일 경우
                if (data.substr(12, 2) === "s_") {
                    var html = templatePhotoAttach(fileInfo);

                    // 이미지 파일이 아닐 경우
                } else {
                    html = templateFileAttach(fileInfo);
                }
                $(".uploadedList").append(html);
            }
        });
    });

    // 파일 출력
    $.getJSON("/article/file/list/" + articleNo, function (list) {
        if (list.length === 0) {
            $(".uploadedList").html("첨부파일이 없습니다.");
        }
        $(list).each(function () {
            // 파일정보 가공
            var fileInfo = getFileInfo(this);
            // 이미지 파일일 경우
            if (fileInfo.fullName.substr(12, 2) === "s_") {
                var html = templatePhotoAttach(fileInfo);
                // 이미지 파일이 아닐 경우
            } else {
                html = templateFileAttach(fileInfo);
            }
            $(".uploadedList").append(html);
        })
    });

    // 파일 삭제 : 게시글 입력페이지
    $(document).on("click", ".delBtn", function (event) {
        event.preventDefault();
        var that = $(this);
        $.ajax({
            url: "/article/file/delete",
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
    });

    // 파일 삭제 : 게시글 삭제페이지
    $(document).on("click", ".delBtn", function (event) {
        event.preventDefault();
        if (confirm("삭제하시겠습니까? 삭제된 파일은 복구할 수 없습니다.")) {
            var that = $(this);
            $.ajax({
                url: "/article/file/delete/" + articleNo,
                type: "post",
                data: {fileName:$(this).attr("href")},
                dataType: "text",
                success: function (result) {
                    if (result === "DELETED") {
                        alert("삭제되었습니다.");
                        that.parents("li").remove();

                    }

                }
            });
        }
    });

});