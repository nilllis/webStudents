<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<div class="main_links">
    <a href="/title" style="margin-right: 2em;">На главную</a>
    <a href="/students">Назад</a>
</div>

<h4 style="margin-bottom: 2px;">Отображена успеваемость для следующего студента:</h4>


<div style="float: left; width: 100%">
    <table class="table_current_student">
        <tr><input type="hidden" name="student_id" value="<c:out value="${student_id}"/>"/></tr>
        <tr style="background: #CCC">
            <td>Фамилия</td>
            <td>Имя</td>
            <td>Группа</td>
            <td>Дата поступления</td>
        </tr>
        <tr>
            <td><c:out value="${student_name}"/></td>
            <td><c:out value="${student_last_name}"/></td>
            <td><c:out value="${student_group}"/></td>
            <td><c:out value="${student_date}"/></td>
        </tr>
    </table>
</div>

<div style="float: left; width: 45%">
    <div><c:out value="${allMarksInSemesterTable}" escapeXml="false"/></div>
</div>

<div style="float: left; margin-left: 30px;">
    <table>
        <tr>
            <td><h4 style="margin-bottom: 2px;">Выбрать семестр</h4></td>
            <td style="width: 40%;"><c:out value="${allSemestersLabel}" escapeXml="false"/></td>
            <td style="width: 20%;"><input type="button" value="Выбрать" class="buttons_disc" onclick="showHideLines()">
            </td>
        </tr>
    </table>
</div>

<div onload="showHideLines()"></div>