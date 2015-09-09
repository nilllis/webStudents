/**
 * Created by пк on 09.08.2015.
 */

function ruleForStudentsMarks() {
    var items = $("input[type=checkbox]:checked");
    if (items.length == 0) {
        var errorMsg = "<p>Не выбран студент!</p>";
        document.getElementById("errorMessage").innerHTML = errorMsg;
        document.getElementById("errorMessage").style.display = 'block';
        return;
    }
    if (items.length > 1) {
        var errorMsg = "<p>Для просмотра выберите только одного студента.</p>";
        document.getElementById("errorMessage").innerHTML = errorMsg;
        document.getElementById("errorMessage").style.display = 'block';
        return;
    }

    var inputForm = '<input type="hidden" name="students_marks" />';
    $("#allStudents").append(inputForm);
    $("#allStudents").submit();
}


function ruleForCreateStudent() {
    var inputForm = '<input type="hidden" name="create_student" />';
    $("#allStudents").append(inputForm);
    $("#allStudents").submit();
}


function ruleForDeletingStudents() {
    var items = $("input[type=checkbox]:checked");
    if (items.length == 0) {
        var errorMsg = "<p>Не выбраны студенты для удаления!</p>";
        document.getElementById("errorMessage").innerHTML = errorMsg;
        document.getElementById("errorMessage").style.display = 'block';
        return;
    }

    var inputForm = '<input type="hidden" name="delete_student" />';
    $("#allStudents").append(inputForm);
    $("#allStudents").submit();
}


function ruleForModifyStudents() {
    var items = $("input[type=checkbox]:checked");
    if (items.length == 0) {
        var errorMsg = "<p>Не выбран студент!</p>";
        document.getElementById("errorMessage").innerHTML = errorMsg;
        document.getElementById("errorMessage").style.display = 'block';
        return;
    }
    if (items.length > 1) {
        var errorMsg = "<p>Выберите только одного студента для модифицирования.</p>";
        document.getElementById("errorMessage").innerHTML = errorMsg;
        document.getElementById("errorMessage").style.display = 'block';
        return;
    }
    var inputForm = '<input type="hidden" name="modify_student" />';
    $("#allStudents").append(inputForm);
    $("#allStudents").submit();
}


function checkDataForCreateStudent() {
    var inputLabel1 = document.getElementById('newStudent_lastName').value;
    var inputLabel2 = document.getElementById('newStudent_name').value;
    var inputLabel3 = document.getElementById('newStudent_groupName').value;
    var inputLabel4 = document.getElementById('datepicker').value;
    if (inputLabel1.length == 0 || inputLabel2.length == 0 ||
        inputLabel3.length == 0 || inputLabel4.length == 0) {
        var errorMsg = "<p>Поля не должны быть пустыми</p>";
        document.getElementById("errorMessage").innerHTML = errorMsg;
        document.getElementById("errorMessage").style.display = 'block';
        return;
    }
    var inputForm = '<input type="hidden" name="create_new_student_action" />';
    $("#students_data").append(inputForm);
    $("#students_data").submit();
}

function checkDataForModifyStudent() {
    var inputLabel1 = document.getElementById('newStudent_lastName').value;
    var inputLabel2 = document.getElementById('newStudent_name').value;
    var inputLabel3 = document.getElementById('newStudent_groupName').value;
    var inputLabel4 = document.getElementById('datepicker').value;
    if (inputLabel1.length == 0 || inputLabel2.length == 0 ||
        inputLabel3.length == 0 || inputLabel4.length == 0) {
        var errorMsg = "<p>Поля не должны быть пустыми</p>";
        document.getElementById("errorMessage").innerHTML = errorMsg;
        document.getElementById("errorMessage").style.display = 'block';
        return;
    }
    var inputForm = '<input type="hidden" name="modify_student_action" />';
    $("#students_data").append(inputForm);
    $("#students_data").submit();
}


function showHideLines() {
    var semester = document.getElementById("semester").value;
    var rows = document.getElementsByClassName("row");
    for (var i = 0; i < rows.length; i++) {
        console.log(rows[i].id)
        if (semester != rows[i].id) {
            rows[i].style.display = 'none';
        } else {
            rows[i].style.display = 'table-row';
        }
    }
}

function ruleForModifyDiscipline() {
    var items = $("input[type=checkbox]:checked");
    if (items.length == 0) {
        var errorMsg = "<p>Не выбрана дисциплина.</p>";
        document.getElementById("errorMessage").innerHTML = errorMsg;
        document.getElementById("errorMessage").style.display = 'block';
        return;
    }
    if (items.length > 1) {
        var errorMsg = "<p>Выберите только одну дисциплину для модифицирования.</p>";
        document.getElementById("errorMessage").innerHTML = errorMsg;
        document.getElementById("errorMessage").style.display = 'block';
        return;
    }
    var inputForm = '<input type="hidden" name="modify_discipline" />';
    $("#allDisciplines").append(inputForm);
    $("#allDisciplines").submit();
}


function ruleForDeleteDiscipline() {
    var items = $("input[type=checkbox]:checked");
    if (items.length == 0) {
        var errorMsg = "<p>Не выбрана дисциплина.</p>";
        document.getElementById("errorMessage").innerHTML = errorMsg;
        document.getElementById("errorMessage").style.display = 'block';
        return;
    }
    if (items.length > 1) {
        var errorMsg = "<p>Выберите только одну дисциплину для удаления.</p>";
        document.getElementById("errorMessage").innerHTML = errorMsg;
        document.getElementById("errorMessage").style.display = 'block';
        return;
    }
    var inputForm = '<input type="hidden" name="delete_discipline" />';
    $("#allDisciplines").append(inputForm);
    $("#allDisciplines").submit();
}
