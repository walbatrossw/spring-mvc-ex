<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<script>
    var result = "${savedFileName}";
    parent.addFilePath(result);
</script>