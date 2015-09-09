<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<div class="main_links">
    <a href="/title" style="margin-right: 2em;">На главную</a>
</div>


<div style="margin: 20px 0">
    <table class="table_students_buttons">
        <tr>
            <td align="left" width="60%">
                <input type="submit" value="Просмотреть успеваемость выбранных студентов" onclick="ruleForStudentsMarks()"
                       class="buttons"></td>
            <td align="left">
                <c:if test="${currentRole eq 1 or currentRole eq 3}">
                <input type="submit" value="Создать студента" onclick="ruleForCreateStudent()"
                       class="buttons"></td>
            </c:if>
        </tr>
        <tr>
            <td align="left">
                <c:if test="${currentRole eq 1 or currentRole eq 3}">
                    <input type="submit" value="Модифицировать выбранного студента" onclick="ruleForModifyStudents()"
                           class="buttons">
                </c:if>
            </td>
            <td align="left">
                <c:if test="${currentRole eq 1 or currentRole eq 3}">
                    <input type="submit" value="Удалить выбранных студентов" onclick="ruleForDeletingStudents()" class="buttons">
                </c:if>
            </td>

        </tr>
    </table>
</div>

<div id="errorMessage"></div>

<form action="/students" method="post" id="allStudents">
    <h4 style="margin-bottom: 2px;">Список студентов</h4>
    <table class="table_students_data">
        <tr>
            <td align="left" width="6%"></td>
            <td align="left" width="35%">Фамилия</td>
            <td align="left" width="35%">Имя</td>
            <td align="left" width="12%">Группа</td>
            <td align="left" width="12%">Дата поступления</td>
        </tr>
    </table>

    <div><c:out value="${allStudentsTable}" escapeXml="false"/></div>
</form>
