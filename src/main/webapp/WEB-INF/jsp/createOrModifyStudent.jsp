<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $("#datepicker").datepicker({dateFormat: "dd/mm/yy"});
        $("#datepicker").datepicker();
    });
</script>

<div class="main_links">
    <a href="/title" style="margin-right: 2em;">На главную</a>
    <a href="/students">Назад</a>
</div>


<h4 style="margin-bottom: 2px;">
    <c:choose>
        <c:when test="${variantOfJsp eq 1}">
            Для создания студента, заполните все поля и нажмите кнопку "Создать".
        </c:when>
        <c:when test="${variantOfJsp eq 2}">
            Для модификации введите введите новые значения и нажмите кнопку "Применить".
        </c:when>
    </c:choose>
</h4>


<table class="table_create_student">
    <form action="/students" method="post" id="students_data">
        <input type="hidden" name="newStudent_id" value="<c:out value="${newStudent_id_old}"/>">
        <tr>
            <td align="right">Фамилия</td>
            <td><input type="text" name="newStudent_lastName" id="newStudent_lastName"
                       value="<c:out value="${newStudent_lastName_old}"/>"></td>
        </tr>
        <tr>
            <td align="right">Имя</td>
            <td><input type="text" name="newStudent_name" id="newStudent_name"
                       value="<c:out value="${newStudent_name_old}"/>"></td>
        </tr>
        <tr>
            <td align="right">Группа</td>
            <td><input type="text" name="newStudent_groupName" id="newStudent_groupName"
                       value="<c:out value="${newStudent_groupName_old}"/>">
            </td>
        </tr>
        <tr>
            <td align="right">Дата поступления</td>
            <td><input type="text" name="newStudent_date" id="datepicker"
                       value="<c:out value="${newStudent_date_old}"/>"></td>
        </tr>
    </form>
    <tr>
        <td align="right"></td>
        <td>
            <c:choose>
                <c:when test="${variantOfJsp eq 1}">
                    <input type="submit" value="Создать" onclick="checkDataForCreateStudent()" class="buttons"
                           style="width: 50%;font-size: 1.2em;">
                </c:when>
                <c:when test="${variantOfJsp eq 2}">
                    <input type="submit" value="Применить" onclick="checkDataForModifyStudent()" class="buttons"
                           style="width: 50%;font-size: 1.2em;">
                </c:when>
            </c:choose>

        </td>
    </tr>
</table>

<div id="errorMessage"></div>
<div style="color: red"><c:out value="${statusOperation}"/></div>