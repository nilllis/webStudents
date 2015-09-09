<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<div class="main_links">
  <a href="/title" style="margin-right: 2em;">На главную</a>
  <a href="/semesters">Назад</a>
</div>

<h4 style="margin-bottom: 2px;">
  <c:choose>
    <c:when test="${variantOfJsp eq 1}">
      Для создания семестра заполните следующие данные и нажмите кнопку "Создать".
    </c:when>
    <c:when test="${variantOfJsp eq 2}">
      Для модификации семестра отредактируйте данные и нажмите кнопку "Применить".
    </c:when>
  </c:choose>
</h4>

<form action="/semesters" method="post">
  <table class="table_create_semesters">
    <tr><input type="hidden" name="semester_id" value="<c:out value="${semester_id_old}"/>"></tr>
    <tr>
      <td align="right">Длительность (в неделях)</td>
      <td><input type="text" name="semester_duration" value="<c:out value="${semester_duration_old}"/>"></td>
    </tr>
    <tr>
      <td align="right">Дисциплины в семестре</td>
      <td><c:out value="${allDisciplineLabel}" escapeXml="false"/></td>
    </tr>
    <tr>
      <td></td>
      <td>
        <c:choose>
          <c:when test="${variantOfJsp eq 1}">
            <input type="submit" value="Создать" name="create_new_semester_action" class="buttons_disc" style="width: 50%;font-size: 1.2em;">
          </c:when>
          <c:when test="${variantOfJsp eq 2}">
            <input type="submit" value="Применить" name="modify_semester_action" class="buttons_disc" style="width: 50%;font-size: 1.2em;">
          </c:when>
        </c:choose>
      </td>
    </tr>
  </table>
</form>

<div style="color: red"><c:out value="${statusOperation}"/></div>