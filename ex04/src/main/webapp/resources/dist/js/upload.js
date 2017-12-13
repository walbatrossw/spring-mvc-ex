// 업로드 스크립트

// 이미지 파일 유무 확인
function checkImageType(fileName) {
    var pattern = /jpg$|gif$|png$|jpge$/i;
    return fileName.match(pattern);
}

// 파일 정보
function getFileInfo(fullName) {

    var fileName;   // 날짜경로, UUID 키값을 제외한 파일의 이름(화면상에 보여질 파일명)
    var imgscr;     // 실제 썸네일 or 파일 아이콘 이미지의 경로
    var getLink;    // 원본파일의 경로

    var fileLink;

    if (checkImageType(fullName)) {
        imgscr = "/fileupload/displayFile?fileName=" + fullName;
        fileLink = fullName.substr(14);
        var front = fullName.substr(0, 12); // 날짜 경로
        var end = fullName.substr(14);      // s_를 제외한 파일명
        getLink = "/fileupload/displayFile?fileName=" + front + end;
    } else {
        imgscr = "/resources/dist/img/file.png";
        fileLink = fullName.substr(12);
        getLink = "/fileupload/displayFile?fileName=" + fullName;
    }
    fileName = fileLink.substr(fileLink.indexOf("_") + 1);

    return {fileName: fileName, imgsrc: imgscr, getLink: getLink, fullName: fullName};
}