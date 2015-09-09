<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<div class="main_links">
    <a href="/title" style="margin-right: 2em;">На главную</a>
</div>


<table class="table_semester_choose">
    <tr>
        <td style="width: 30%;"><h4 style="margin-bottom: 2px;">Выбрать семестр</h4></td>
        <form action="/semesters" method="get">
            <td style="width: 40%;"><c:out value="${allSemestersLabel}" escapeXml="false"/></td>
            <td><input type="submit" value="Выбрать" name="choose_semester" class="buttons_disc"></td>
        </form>
    </tr>
</table>

<div style="color: red"><c:out value="${errorMessage}"/></div>


<form action="/semesters" method="post">
    <c:if test="${semesterId gt 0}">
        <div><h4 style="margin-bottom: 2px;">Длительность семестра: <c:out value="${semesterDuration}"/> недели</h4>
        </div>
        <div><h4 style="margin-bottom: 2px;">Список дисциплин семестра</h4></div>
    </c:if>
        <div style="float: left; width: 50%; height: 100%">
            <div><c:out value="${disciplinesListTable}" escapeXml="false"/></div>
        </div>

    <c:if test="${currentRole eq 1 or currentRole eq 3}">
        <div>
            <table>
                <tr>
                    <td><input type="hidden" value="<c:out value="${semesterId}"/>" name="current_semester"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Создать семестр..." name="create_semester"
                               class="buttons_disc"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Модифицировать текущий семестр..." name="modify_semester"
                               class="buttons_disc"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Удалить текущий семестр" name="delete_semester"
                               class="buttons_disc"></td>
                </tr>
            </table>
        </div>
    </c:if>
</form>
