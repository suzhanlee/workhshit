<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style type="text/css">
        a {
            text-decoration: none
        }
    </style>
</head>
<body>

<%@include file="menu.jsp" %>

<br> <br>

<form action="/wifiNear.do" class="location-form">

    <label>LAT:</label>
    <label>
        <input id="x-coordinate" name="LAT" type="number" step="any" placeholder="X좌표"/>
    </label>

    <label>, LNT:</label>
    <label>
        <input id="y-coordinate" name="LNT" type="number" step="any" placeholder="Y좌표"/>
    </label>

    <button id="myLocationButton" type="button">내 위치 가져오기</button>
    <button id="nearWifiButton" type="submit">근처 WIFI 정보 보기</button>
</form>

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
        <col style="width: 90px">
        <col style="width: 100px">
        <col style="width: 90px">
        <col style="width: 98px">
        <col style="width: 127px">
        <col style="width: 100px">
        <col style="width: 106px">
        <col style="width: 90px">
        <col style="width: 102px">
        <col style="width: 89px">
        <col style="width: 91px">
        <col style="width: 112px">
        <col style="width: 110px">
        <col style="width: 118px">
        <col style="width: 78px">
        <col style="width: 78px">
        <col style="width: 100px">
    </colgroup>
    <thead>
    <tr bgcolor = green>
        <td class="tg-c3ow" style="color: white">거리(Km)</td>
        <td class="tg-c3ow" style="color: white">관리번호</td>
        <td class="tg-c3ow" style="color: white">자치구</td>
        <td class="tg-c3ow" style="color: white">와이파이명</td>
        <td class="tg-c3ow" style="color: white">도로명주소</td>
        <td class="tg-c3ow" style="color: white">상세주소</td>
        <td class="tg-c3ow" style="color: white">설치위치(층)</td>
        <td class="tg-c3ow" style="color: white">설치유형</td>
        <td class="tg-c3ow" style="color: white">설치기관</td>
        <td class="tg-c3ow" style="color: white">서비스구분</td>
        <td class="tg-c3ow" style="color: white">망종류</td>
        <td class="tg-c3ow" style="color: white">설치년도</td>
        <td class="tg-c3ow" style="color: white">실내외구분</td>
        <td class="tg-c3ow" style="color: white">WIFI접속환경</td>
        <td class="tg-c3ow" style="color: white">X좌표</td>
        <td class="tg-c3ow" style="color: white">Y좌표</td>
        <td class="tg-c3ow" style="color: white">작업일자</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${searchList}" var="wifi">
        <tr>
            <td><c:out value="${wifi.distance}"/></td>
            <td><c:out value="${wifi.mgrNo}"/></td>
            <td><c:out value="${wifi.wrdofc}"/></td>
            <td><c:out value="${wifi.mainNm}"/></td>
            <td><c:out value="${wifi.adres1}"/></td>
            <td><c:out value="${wifi.adres2}"/></td>
            <td><c:out value="${wifi.floor}"/></td>
            <td><c:out value="${wifi.ty}"/></td>
            <td><c:out value="${wifi.mby}"/></td>
            <td><c:out value="${wifi.svcSe}"/></td>
            <td><c:out value="${wifi.cmcwr}"/></td>
            <td><c:out value="${wifi.year}"/></td>
            <td><c:out value="${wifi.door}"/></td>
            <td><c:out value="${wifi.remars3}"/></td>
            <td><c:out value="${wifi.lat}"/></td>
            <td><c:out value="${wifi.lnt}"/></td>
            <td><c:out value="${wifi.dttm}"/></td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
<script type="text/javascript">
    class MainFunction {
        constructor() {
            this.locationForm = document.querySelector(".location-form")
            this.myLocationButton = document.querySelector("#myLocationButton")
        }

        getMyLocation() {
            const myLocationButton = this.myLocationButton;

            myLocationButton.addEventListener("click", () => {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(pos => {
                        this.locationForm["x-coordinate"].value = pos.coords.longitude;
                        this.locationForm["y-coordinate"].value = pos.coords.latitude;
                        alert('위치 정보를 불러왔습니다.')
                    })
                } else {
                    alert('Sorry, no position available.');
                }
            });
        }
    }

    document.addEventListener("DOMContentLoaded", () => {
        new MainFunction().getMyLocation();
    })
</script>
</html>
