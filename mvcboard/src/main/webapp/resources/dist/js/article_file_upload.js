

// 파일 템플릿
var templateImage = Handlebars.compile($("#templateImage").html());
var templateFile = Handlebars.compile($("#templateFile").html());

// 전체 페이지 파일 드랍 드래그 이벤트 처리 : 지정된 영역외 이벤트 발생 금지
$(".content-wrapper").on("dragenter dragover drop", function (event) {
    event.preventDefault();
});

var fileDropDiv = $(".fileDrop"); // 파일 드랍 영역

// 파일 끌어 놓기 기본 이벤트 방지
fileDropDiv.on("dragenter dragover", function (event) {
    event.preventDefault();
});

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
            printFiles(data);
            $(".noAttach").remove();
        }
    });
});

// 파일 목록
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

// 파일 출력
function printFiles(data) {
    var fileInfo = getFileInfo(data);
    if (fileInfo.fullName.substr(12, 2) === "s_") {
        var html = templateImage(fileInfo);
    } else {
        html = templateFile(fileInfo);
    }
    $(".uploadedList").append(html);
}

// 입력페이지 파일 삭제
function deleteFileWrtPage(that) {
    var url = "/article/file/delete";
    deleteFile(url, that);
}

// 수정페이지 파일 삭제 : 파일삭제, 파일명 DB 삭제 처리
function deleteFileModPage(that, articleNo) {
    var url = "/article/file/delete/" + articleNo;
    deleteFile(url, that);
}

function deleteFile(url, that) {
    $.ajax({
        url: url,
        type: "post",
        data: {fileName:that.attr("href")},
        dataType: "text",
        success: function (result) {
            if (result === "DELETED") {
                alert("삭제되었습니다.");
                that.parents("li").remove();
            }
        }
    });
}

// 파일 정보 가공
function getFileInfo(fullName) {

    var fileName;   // 화면에 출력할 파일명
    var imgSrc;     // 썸네일 or 파일아이콘 이미지 파일 요청 URL
    var getLink;    // 원본파일 요청 URL

    var fileLink;   // 날짜경로를 제외한 나머지 파일명 (UUID_파일명.확장자)

    // 이미지 파일일 경우
    if (checkImageType(fullName)) {
        // 썸네일 파일 이미지 URL
        imgSrc = "/article/file/display?fileName=" + fullName;
        // UUID_파일명.확장자 (s_ 제외 : 원본이미지)
        fileLink = fullName.substr(14);
        // 원본파일 요청 URL
        var datePath = fullName.substr(0, 12); // 날짜 경로
        var originalFileName = fullName.substr(14);      // 파일명(s_ 제외)
        getLink = "/article/file/display?fileName=" + datePath + originalFileName;

        // 이미지 파일이 아닐 경우
    } else {
        // 파일 아이콘 이미지 URL
        imgSrc = "/resources/upload/files/file-icon.png";
        // UUID_파일명.확장자
        fileLink = fullName.substr(12);
        // 파일 요청 url
        getLink = "/article/file/display?fileName=" + fullName;
    }
    // 화면에 출력할 파일명
    fileName = fileLink.substr(fileLink.indexOf("_") + 1);

    return {fileName : fileName, imgSrc : imgSrc, getLink : getLink, fullName : fullName};
}

// 이미지 파일 유무 확인
function checkImageType(fileName) {
    // 정규 표현식을 통해 이미지 파일 유무 확인
    var pattern = /jpg$|gif$|png$|jpge$/i;
    return fileName.match(pattern);
}
