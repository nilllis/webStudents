<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/resources/css/global.css">
    <title>login</title>
</head>
<body>

<div class="login_preform_left"><br></div>
<div class="login_form">
    <form action="/login" method="post">
        Имя:
        <input type="text" name="username" class="login_input">
        Пароль:
        <input type="password" name="password" class="login_input">

        <p></p>

        <select name="role" id="select_role" class="login_input">
            <c:forEach items="${allRoles}" var="role">
                <option value="<c:out value="${role.id}"/>"><c:out value="${role.roleLangRu}"/></option>
            </c:forEach>
        </select>

        <input type="submit" value="Войти" id="button" class="login_button">
    </form>
    <jsp:include page="modules/errorValidation.jsp"/>
</div>

<div style="float: right; font-size: 0.7em">
    Тестовые аккаунты:<br>
    <p>admin - pass - any role</p>
    <p>easyIT - 111 - студент</p>
</div>
</body>
</html>
