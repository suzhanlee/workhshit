<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2022-09-07
  Time: 오전 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Title</title>
</head>
<body>
<%@include file="menu.jsp" %>
<style type="text/css">
    .tg {
        border-collapse: collapse;
        border-spacing: 0;
    }

    .tg td {
        border-color: black;
        border-style: solid;
        border-width: 1px;
        font-family: Arial, sans-serif;
        font-size: 14px;
        overflow: hidden;
        padding: 10px 5px;
        word-break: normal;
    }

    .tg th {
        border-color: black;
        border-style: solid;
        border-width: 1px;
        font-family: Arial, sans-serif;
        font-size: 14px;
        font-weight: normal;
        overflow: hidden;
        padding: 10px 5px;
        word-break: normal;
    }

    .tg .tg-c3ow {
        border-color: inherit;
        text-align: center;
        vertical-align: top
    }
</style>
    <table class="tg" style=" table-layout: fixed; width: 1720px">
        <colgroup>
            <col style="width: 150px">
            <col style="width: 350px">
            <col style="width: 350px">
            <col style="width: 527px">
            <col style="width: 250px">
        </colgroup>
        <thead>
        <tr bgcolor = green>
            <td class="tg-c3ow" align="center" style="color: white">ID</td>
            <td class="tg-c3ow" align="center" style="color: white">X좌표</td>
            <td class="tg-c3ow" align="center" style="color: white">Y좌표</td>
            <td class="tg-c3ow" align="center" style="color: white">조회일자</td>
            <td class="tg-c3ow" align="center" style="color: white">비고</td>
        </tr>
        </thead>
    <tbody>
    <c:forEach items="${list}" var="history">
        <tr>
            <td><c:out value="${history.id}"/></td>
            <td><c:out value="${history.lat}"/></td>
            <td><c:out value="${history.lnt}"/></td>
            <td><c:out value="${history.date}"/></td>
            <td align="center">
                <button type="button" class="button">삭제</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script>
    $(".button").click(function () {
        var checkBtn = $(this);
        var tr = checkBtn.parent().parent();
        var td = tr.children();
        var deleteId = td.eq(0).text();

        //서블릿 호출
        $.ajax({
            type: "post",
            url: "http://localhost:8080/historyDelete.do?deleteId=" + deleteId

        }).done(function () {
            location.reload();
        })
    })
</script>
</body>
</html>
