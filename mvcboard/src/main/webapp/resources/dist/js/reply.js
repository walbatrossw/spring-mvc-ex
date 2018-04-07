// 사용자


// 댓글 내용 : 줄바꿈/공백처리
Handlebars.registerHelper("escape", function (replyText) {
    var text = Handlebars.Utils.escapeExpression(replyText);
    text = text.replace(/(\r\n|\n|\r)/gm, "<br/>");
    text = text.replace(/( )/gm, "&nbsp;");
    return new Handlebars.SafeString(text);
});

// 댓글 등록일자 : 날짜/시간 2자리로 맞추기
Handlebars.registerHelper("prettifyDate", function (timeValue) {
    var dateObj = new Date(timeValue);
    var year = dateObj.getFullYear();
    var month = dateObj.getMonth() + 1;
    var date = dateObj.getDate();
    var hours = dateObj.getHours();
    var minutes = dateObj.getMinutes();
    // 2자리 숫자로 변환
    month < 10 ? month = '0' + month : month;
    date < 10 ? date = '0' + date : date;
    hours < 10 ? hours = '0' + hours : hours;
    minutes < 10 ? minutes = '0' + minutes : minutes;
    return year + "-" + month + "-" + date + " " + hours + ":" + minutes;
});

// 댓글 목록 함수
function getReplies(repliesUri) {
    $.getJSON(repliesUri, function (data) {
        printReplyCount(data.pageMaker.totalCount);
        printReplies(data.replies, $(".repliesDiv"), $("#replyTemplate"));
        printReplyPaging(data.pageMaker, $(".pagination"));
    });
}

// 댓글 갯수 출력 함수
function printReplyCount(totalCount) {

    var replyCount = $(".replyCount");
    var collapsedBox = $(".collapsed-box");

    // 댓글이 없으면
    if (totalCount === 0) {
        replyCount.html(" 댓글이 없습니다. 의견을 남겨주세요");
        collapsedBox.find(".btn-box-tool").remove();
        return;
    }

    // 댓글이 존재하면
    replyCount.html(" 댓글목록 (" + totalCount + ")");
    collapsedBox.find(".box-tools").html(
        "<button type='button' class='btn btn-box-tool' data-widget='collapse'>"
        + "<i class='fa fa-plus'></i>"
        + "</button>"
    );

}

// 댓글 목록 출력 함수
function printReplies(replyArr, targetArea, templateObj) {
    var replyTemplate = Handlebars.compile(templateObj.html());
    var html = replyTemplate(replyArr);
    $(".replyDiv").remove();
    targetArea.html(html);
}

// 댓글 페이징 출력 함수
function printReplyPaging(pageMaker, targetArea) {
    var str = "";
    if (pageMaker.prev) {
        str += "<li><a href='" + (pageMaker.startPage - 1) + "'>이전</a></li>";
    }
    for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
        var strClass = pageMaker.criteria.page == i ? "class=active" : "";
        str += "<li " + strClass + "><a href='" + i + "'>" + i + "</a></li>";
    }
    if (pageMaker.next) {
        str += "<li><a href='" + (pageMaker.endPage + 1) + "'>다음</a></li>";
    }
    targetArea.html(str);
}