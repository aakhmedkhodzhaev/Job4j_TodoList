<%--
  Created by IntelliJ IDEA.
  User: Akhmedkhodzhaev A.A.
  Date: 24.11.2020
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>

    <title>Работа мечты</title>
</head>
<body>
<!-- Optional JavaScript -->
<!-- jQuery,  Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

<!--get items from db-->
<script>
    setInterval(getTodoList, 25000);

    function getTodoList(id) {
        var urlGetQuery = './json?status=' + id;
        $.ajax(urlGetQuery).done(function (data) {
            var refreshBody = '';
            var json = JSON.parse(data);
            for (item in json) {
                refreshBody += '<tr><td>' + json[item].id.toString() + '</td><td>' + json[item].description
                    + '</td><td>' + json[item].created + '</td><td>';
                if (json[item].done.toString() === 'true') {
                    refreshBody += '<input type="checkbox" id="' + json[item].id.toString() + '" onchange="changeStatus(this.id)" checked>' + '</td>';
                } else {
                    refreshBody += '<input type="checkbox" id="' + json[item].id.toString() + '" onchange="changeStatus(this.id)">' + '</td>';
                }
                refreshBody += '</tr>';

            }
            $("#todo_list").html(refreshBody);
        })
    }

    <!-- add new task-->
    function addTask() {
        var desc = $('#newItemDescription').val();
        if (!/[^ ]/.test(desc)) {
            alert("Input description of task");
            return;
        }
        $.ajax("./json", {
            type: "post",
            data: JSON.stringify({
                description: desc
            }),
            dataType: "json"
        }).done(function (data) {
            if (data.success === true) {
                alert("Task was added");
                alert(data.created);
                var rowToAdd = '<tr><td>' + data.id + '</td><td>' + data.description + '</td><td>'
                    + data.created + '</td><td>'
                    + '<input type="checkbox" id="' + data.id + '" onchange="changeStatus(this.id)">' + '</td></tr>';
                $("#todo_list").append(rowToAdd);
            }
            if (data.success === false) {
                alert("Error adding task");
            }
        })
    }

    <!-- change Status -->
    function changeStatus(id) {
        $.ajax("./json", {
                type: "post",
                data: JSON.stringify({
                    id: id
                }),
                dataType: "json"
            }
        ).done(function (data) {
            if (data.success === true) {

            } else {

            }
        })
    }

    function statusBox(id) {
        if (id === "true") {
            getTodoList("true");
        }
        else if (id === "false") {
            getTodoList("false");
        }
        else {
            getTodoList("all");
        }
    }

    $(
        getTodoList("all")
    )
</script>

<div class="container">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/index.html">Текущие общие заявки</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>">Исполненые заявки</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>">Добавить заявку</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>">Удалённые заявки</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/auth.do">Выйти</a>
            </li>
        </ul>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <h3>Hello: ${user.name}</h3>
            </div>
            <div class="card-body">


                User Name: <b>${user.name}</b>
                <br/>
            </div>
        </div>
    </div>
    <div class="row pt-3">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Сегодняшние задачи.
            </div>
            <div class="card-body">
                <h5>
                    <form>
                        <input type="text" id="newItemDescription" required pattern="^[a-zA-Z]+$">
                        <input type="button" value="Добавление новой заявки" onclick="addTask()">
                    </form>
                </h5>
                <h5> Текущий статус заявки:
                    <td>
                        <select onchange="statusBox(this.value)">
                            <option value="all">All</option>
                            <option value="false">Working</option>
                            <option value="true">Solved</option>
                        </select>
                    </td>
                </h5>
                <div class="row pt-3">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th width="125px">Номер заявки</th>
                            <th width="550px">Описание заявки</th>
                            <th width="125px">Дата создания</th>
                            <th width="125px">Статус</th>
                        </tr>
                        </thead>
                        <tbody id="todo_list">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>