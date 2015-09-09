<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/resources/css/global.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/ui-lightness/jquery-ui-1.10.4.custom.css">
    <script type="text/javascript" src="/resources/js/jquery.ui.datepicker-ru.js"></script>
    <script type="text/javascript" src="/resources/js/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="/resources/js/jquery-ui-1.10.4.custom.js"></script>
    <script type="text/javascript" src="/resources/js/js_webStudents.js"></script>


    <title><c:out value="${pageTitle}"/></title>
</head>
<body>
<div class="left_column">
    <c:set var="ads" value="/WEB-INF/jsp/modules/ads.jsp"/>
    <c:import url="${ads}"/>
</div>

<div class="center_column">
    <div class="title_text">
        <p>Система управления студентами и их успеваемостью</p>
    </div>

    <c:set var="page" value="${pageInsertingIntoTemplate}"/>
    <c:import url="${page}"/>
</div>

<div class="right_column">
    <a href="/logout" title="Вы работаете как <c:out value="${Current_role_name}"/>">Logout</a>
</div>
</body>
</html>
