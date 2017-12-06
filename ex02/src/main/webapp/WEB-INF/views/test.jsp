<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AJAX TEST PAGE</title>
    <style>

        #modDiv {
            width: 300px;
            height: 100px;
            background-color: #0b58a2;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: -50px;
            margin-left: -150px;
            padding: 10px;
            z-index: 1000;
        }

        .pagination {
            width: 100%;
        }

        .pagination li {
            list-style: none;
            float: left;
            padding: 3px;
            border: 1px solid #0a0a0a;
            margin: 3px;
        }

        .pagination li a {
            margin: 3px;
            text-decoration: none;
        }

    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script>

        $(document).ready(function () {
            // 댓글 등록
            $("#replyAddBtn").on("click", function () {

                var replyer = $("#newReplyWriter").val();
                var replytext = $("#newReplyText").val();

                $.ajax({
                    type: "post",
                    url: "/replies",
                    headers: {
                        "Content-Type": "application/json",
                        "X-HTTP-Method-Override": "post"
                    },
                    dataType: "text",
                    data: JSON.stringify({
                        bno: bno,
                        replyer: replyer,
                        replytext: replytext
                    }),
                    success: function (result) {
                        console.log("result : " + result);
                        if (result == "INSERTED") {
                            alert("등록되었습니다.");
                        }
                        //getAllList();
                        getPageList(1);
                    }
                });

            });

            // 댓글 수정, 삭제 영역 생성
            $("#replies").on("click", ".replyLi button", function () {

                var reply = $(this).parent();
                var rno = reply.attr("data-rno");
                var replytext = reply.text();

                $(".modal-title").html(rno);
                $("#replytext").val(replytext);
                $("#modDiv").show("slow");

            });

            // 댓글 삭제
            $("#replyDelBtn").on("click", function () {

                var rno = $(".modal-title").html();
                var replytext = $("#replytext").val();

                $.ajax({
                    type: "delete",
                    url: "/replies/" + rno,
                    headers: {
                        "Content-Type": "application/json",
                        "X-HTTP-Method-Override": "DELETE"
                    },
                    dataType: "text",
                    success: function (result) {
                        console.log("result : " + result);
                        if (result == "DELETED") {
                            alert("삭제되었습니다.");
                            $("#modDiv").hide("slow");
                            //getAllList();
                            getPageList(1);
                        }
                    }
                });

            });

            // 댓글 수정
            $("#replyModBtn").on("click", function () {

                var rno = $(".modal-title").html();
                var replytext = $("#replytext").val();

                $.ajax({
                    type: "put",
                    url: "/replies/" + rno,
                    headers: {
                        "Content-Type": "application/json",
                        "X-HTTP-Method-Override": "PUT"
                    },
                    data: JSON.stringify({replytext: replytext}),
                    dataType: "text",
                    success: function (result) {
                        console.log("result : " + result);
                        if (result == "MODIFIED") {
                            alert("수정되었습니다.");
                            $("#modDiv").hide("slow");
                            //getAllList();
                            getPageList(1);
                        }
                    }
                });
            });

            var bno = 100;

            //getAllList();
            getPageList(1);

            // 전체 목록
            function getAllList() {

                $.getJSON("/replies/all/" + bno, function (data) {

                    var str = "";
                    console.log(data.length);

                    $(data).each(function () {
                        str += "<li data-rno='" + this.rno + "' class='replyLi'>"
                            + this.rno + " : " + this.replytext + " <button>수정</button></li>";
                    });

                    $("#replies").html(str);

                });

            }

            // 목록 : 페이징처리
            function getPageList(page) {

                $.getJSON("/replies/" + bno + "/" + page, function (data) {
                    console.log(data.list.length);
                    var str = "";
                    $(data.list).each(function () {
                        str += "<li data-rno'" + this.rno + "' class='replyList'>"
                            + this.rno + " : " + this.replytext + "<button>수정</button></li>"
                    });
                    $("#replies").html(str);
                    printPaging(data.pageMaker);
                });

            }

            // 하단 페이징 출력
            function printPaging(pageMaker) {
                var str = "";

                if (pageMaker.prev) {
                    str += "<li><a href='" + (pageMaker.startPage - 1) + "'> << </a>";
                }

                for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
                    var strClass = pageMaker.criteria.page == i ? 'class=active' : '';
                    str += "<li " + strClass + "><a href='" + i + "'>" + i + "</a></li>";
                }

                if (pageMaker.next) {
                    str += "<li><a href='" + (pageMaker.endPage + 1) + "'> >> </a>";
                }

                $(".pagination").html(str);
            }

            // 페이지 이동
            var replyPage = 1;
            $(".pagination").on("click", "li a", function (event) {

                event.preventDefault();
                replyPage = $(this).attr("href");
                getPageList(replyPage);

            });
        });
    </script>
</head>
<body>
<h2>AJAX test page</h2>

<h4>댓글 작성</h4>
<div>
    <div>
        작성자 : <input type="text" name="replyer" id="newReplyWriter">
    </div>
    <div>
        댓글내용 : <input type="text" name="replytext" id="newReplyText">
    </div>
    <button id="replyAddBtn">저장</button>
</div>

<hr/>

<h4>댓글 리스트</h4>
<ul id="replies">

</ul>

<hr/>
<ul class="pagination">

</ul>

<div id="modDiv" style="display: none">
    <div class="modal-title"></div>
    <div>
        <input type="text" id="replytext">
    </div>
    <div>
        <button type="button" id="replyModBtn">수정</button>
        <button type="button" id="replyDelBtn">삭제</button>
        <button type="button" id="closeBtn">닫기</button>
    </div>
</div>

</body>

</html>
