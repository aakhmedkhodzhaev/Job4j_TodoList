<%--
  Created by IntelliJ IDEA.
  User: Akhmedkhodzhaev A.A.
  Date: 24.11.2020
  Time: 21:27
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="personal-style.css">

    <title>Работа HelpDesk</title>
</head>
<body>
<style>
    body {
        background-image: url(https://wallpapertag.com/wallpaper/full/c/a/a/157415-simplistic-wallpapers-1920x1080-lockscreen.jpg)
    }

    #window-header {
        font-size: 55px;
        font-family: "Times New Roman";
        border-bottom-style: ridge;
        color: white;
    }

    #autho {
        font-size: 47px;
        font-family: arabic typesetting;
        border-bottom-style: ridge;
        color: white;
    }

    #login {
        font-size: 65px;
        font-family: arabic typesetting;
        border-bottom-style: ridge;
        color: white;
    }

    #iconn {
        background-color: #5cb85c;
        border-color: #4cae4c;
        color: white;
    }

    #iconn1 {
        background-color: #5cb85c;
        border-color: #4cae4c;
        color: white;
    }

    .user {
        font-size: 25px;
        font-family: arabic typesetting;
        color: white;
    }

</style>
<script>
    var vEmail = $('#email').val(),
        vPassword = $('#pwd').val(),
        result = false;

    function validate() {
        if (vEmail.length < 5 || vPassword.length < 1) {
            alert("пожалуйста заполните поля")
        } else if (vEmail == '') {
            alert(vEmail.attr('title'));
        } else if (vPassword == '') {
            alert(vPassword.attr('title'));
        } else {
            result = true;
        }
        return result;
    }
</script>
<div class="container">
    <div class="card-header">
        <center><h1 id="window-header"> Помощник по работе с заявками </h1></center>
        <center><b id="autho"> Авторизация</b></center>
    </div>
    <div class="card-body">
        <div class="col-md-6 col-md-offset-3" id="login">
            <form action="<%=request.getContextPath()%>/auth.do" method="post">
                <div class="form-group">
                    <label class="user">Email:</label>
                    <div class="input-group">
                        <span class="input-group-addon" id="iconn"> <i class="glyphicon glyphicon-user"></i></span>
                        <input type="email" class="form-control" id="email" name="email" title="Enter Email"
                               placeholder="Enter Email">
                    </div>
                </div>
                <div class="form-group">
                    <label class="user">Password:</label>
                    <div class="input-group">
                        <span class="input-group-addon" id="iconn1"> <i class="glyphicon glyphicon-lock"></i></span>
                        <input type="password" class="form-control" id="pwd" name="password" title="Enter Password"
                               placeholder="Enter Password">
                    </div>
                    <div class="input-group">
                        <tr>
                            <td><input type="checkbox" name="rememberMe" class="user" value="Y">&nbsp;<font size="3"
                                                                                                      face="Times New Roman">Запомнить</font>
                            </td>
                        </tr>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-success" onclick="validate()" style="border-radius:5px;">
                        Войти
                    </button>
                    <button type="reset" class="btn btn-danger" style="border-radius:5px;">
                        Очистить
                    </button>
                    <div class="row">
                        <ul class="nav">
                            <li class="nav-item">
                                <a class="nav-link" href="<%=request.getContextPath()%>/reg.do"
                                   style="color: black; font-size: 15px; float: right; margin-right: 10px;">
                                    Регистрация </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<%=request.getContextPath()%>/rest.do"
                                   style="color: black; font-size: 15px; float: right; margin-right: 10px;">
                                    Восстановления пароля </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <br/>
                <br/>
            </form>
        </div>
    </div>
</div>
</body>
</html>