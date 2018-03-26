// Handlebars file template
var fileTemplate = Handlebars.compile($("#fileTemplate").html());

// 전체 페이지 file drag & drop 이벤트 처리 : 지정된 영역외 이벤트 발생 금지
$(".content-wrapper").on("dragenter dragover drop", function (event) {
    event.preventDefault();
});

// file drag & drop 영역 선택자
var fileDropDiv = $(".fileDrop");

// file drag & drop 이벤트 처리
fileDropDiv.on("dragenter dragover", function (event) {
    event.preventDefault();
});

// file drag & drop 이벤트 처리 : 파일업로드 처리 -> 파일 출력
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
            printFiles(data);
            $(".noAttach").remove();
        }
    });
});

// 업로드된 파일 출력
function printFiles(data) {
    var fileInfo = getFileInfo(data);
    var html = fileTemplate(fileInfo);
    $(".uploadedList").append(html);

    if (fileInfo.fullName.substr(12, 2) === "s_") {
        var that = $(".uploadedList li").last();
        that.find(".mailbox-attachment-name").attr("data-lightbox", "uploadImages");
        that.find(".fa-paperclip").attr("class", "fa fa-camera");
    }

}

// 파일 목록 : 게시글 조회, 수정페이지
function getFiles(articleNo) {
    $.getJSON("/article/file/list/" + articleNo, function (list) {
        if (list.length === 0) {
            $(".uploadedList").html("<span class='noAttach'>첨부파일이 없습니다.</span>");
        }
        $(list).each(function () {
            printFiles(this);
        })
    });
}

// 파일명 DB저장
function filesSubmit(that) {
    var str = "";
    $(".uploadedList .delBtn").each(function (index) {
        str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr("href") + "'>"
    });
    that.append(str);
    that.get(0).submit();
}

// 입력페이지 파일 삭제 : 파일만 삭제처리
function deleteFileWrtPage(that) {
    var url = "/article/file/delete";
    deleteFile(url, that);
}

// 수정페이지 파일 삭제 : 파일, DB에 저장된 파일명 함께 삭제처리
function deleteFileModPage(that, articleNo) {
    var url = "/article/file/delete/" + articleNo;
    deleteFile(url, that);
}

// 파일 삭제 AJAX 통신
function deleteFile(url, that) {
    $.ajax({
        url: url,
        type: "post",
        data: {fileName: that.attr("href")},
        dataType: "text",
        success: function (result) {
            if (result === "DELETED") {
                alert("삭제되었습니다.");
                that.parents("li").remove();
            }
        }
    });
}

// 파일명 가공
function getFileInfo(fullName) {

    var fileName;   // 화면에 출력할 파일명
    var imgSrc;     // 썸네일 or 파일아이콘 이미지 파일 요청 URL
    var getLink;    // 원본파일 요청 URL
    var fileLink;   // 날짜경로를 제외한 나머지 파일명 (UUID_파일명.확장자)

    // 이미지 파일 여부 판별
    if (checkImageType(fullName)) {
        imgSrc = "/article/file/display?fileName=" + fullName;
        fileLink = fullName.substr(14);
        var datePath = fullName.substr(0, 12);
        var originalFileName = fullName.substr(14);
        getLink = "/article/file/display?fileName=" + datePath + originalFileName;
    } else {
        imgSrc = "/resources/upload/files/file-icon.png";
        fileLink = fullName.substr(12);
        getLink = "/article/file/display?fileName=" + fullName;
    }
    fileName = fileLink.substr(fileLink.indexOf("_") + 1);

    return {fileName: fileName, imgSrc: imgSrc, getLink: getLink, fullName: fullName};
}

// 이미지 파일 유무 확인
function checkImageType(fileName) {
    var pattern = /jpg$|gif$|png$|jpge$/i;
    return fileName.match(pattern);
}
