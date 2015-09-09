<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<div class="main_links">
  <a href="/title" style="margin-right: 2em;">На главную</a>
  <a href="/disciplines">Назад</a>
</div>


<h4 style="margin-bottom: 2px;">
  <c:choose>
    <c:when test="${variantOfJsp eq 1}">
      Для того, чтобы создать новую дисциплину, заполните все поля и нажмите кнопку "Создать":
    </c:when>
    <c:when test="${variantOfJsp eq 2}">
      Для того, чтобы модифицировать дисциплину, введите новое значение поля и нажмите кнопку "Применить":
    </c:when>
  </c:choose>
</h4>

<form  action="/disciplines" method="post">
  <table class="table_create_discipline">
    <tr>
      <td align="right">Название</td>
      <td><input type="text" name="discipline_name" value="<c:out value="${discipline_name_old}"/>"></td>
    </tr>
    <tr><input type="hidden" name="discipline_Id" value="<c:out value="${discipline_Id_old}"/>"></tr>
    <tr>
      <td></td>
      <td>
        <c:choose>
          <c:when test="${variantOfJsp eq 1}">
            <input type="submit" value="Создать" name="create_new_discipline_action" class="buttons_disc" style="width: 50%;font-size: 1.2em;">
          </c:when>
          <c:when test="${variantOfJsp eq 2}">
            <input type="submit" value="Применить" name="modify_discipline_action" class="buttons_disc" style="width: 50%;font-size: 1.2em;">
          </c:when>
        </c:choose>
       </td>
    </tr>
  </table>
</form>

<div style="color: red"><c:out value="${statusOperation}"/></div>