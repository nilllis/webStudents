<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<div class="main_links">
    <a href="/title" style="margin-right: 2em;">На главную</a>
</div>

<h4 style="margin-bottom: 2px;">Список дисциплин</h4>
<br>

<form action="/disciplines" method="post" id="allDisciplines">
    <div style="float: left; width: 50%">
        <table class="table_discipline_data">
            <tr style="background:#CCC">
                <td></td>
                <td align="left" width="85%">Наименование дисциплины</td>
            </tr>
            <c:forEach items="${allDisciplines}" var="discipline">
                <tr>
                    <td align="left">
                        <c:if test="${currentRole == 1 || currentRole == 3}">
                            <input formaction="" type="checkbox" name="id_discipline" value="<c:out value="${discipline.id}"/>">
                        </c:if>
                    </td>
                    <td align="left"><c:out value="${discipline.name}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>

<c:if test="${currentRole eq 1 or currentRole eq 3}">
    <div>
        <table>
            <tr>
                <td><input type="submit" value="Создать дисциплину..." name="create_discipline"
                           class="buttons_disc"></td>
            </tr>
            <tr>
                <td><input type="button" value="Модифицировать выбранную дисциплину..."
                           onclick="ruleForModifyDiscipline()" class="buttons_disc"></td>
            </tr>
            <tr>
                <td><input type="button" value="Удалить выбранную дисциплину..."
                           onclick="ruleForDeleteDiscipline()" class="buttons_disc"></td>
            </tr>
        </table>
    </div>
</c:if>
</form>

<div id="errorMessage"></div>


