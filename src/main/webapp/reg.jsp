<%--
  Created by IntelliJ IDEA.
  User: Akhmedkhodzhaev A.A.
  Date: 28.11.2020
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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

    <title>Регистрация нового пользователя</title>
</head>
<body>
<style>
    body {
        background-image: url(https://wallpapertag.com/wallpaper/full/c/a/a/157415-simplistic-wallpapers-1920x1080-lockscreen.jpg)
    }

    a {
        text-decoration: none; /* Отменяем подчеркивание у ссылки */
    }

    #autho {
        font-size: 47px;
        font-family: "Times New Roman";
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

    #iconns {
        background-color: #5cb85c;
        border-color: #4cae4c;
        color: white;
    }

    #iconn1 {
        background-color: #5cb85c;
        border-color: #4cae4c;
        color: white;
    }

    #iconn2 {
        background-color: #5cb85c;
        border-color: #4cae4c;
        color: white;
    }

    .user {
        font-size: 20px;
        font-family: arabic typesetting;
        color: white;
    }

</style>
<script>
    var vEmail = $('#email').val(),
        vName = $('#name').val(),
        vPassword = $('#pwd').val(),
        vConfirmPassword = $('#pwdconf').val(),
        result = false;

    function validate() {
        if (vEmail.length < 5 || vName.length < 1 || vPassword.length < 1) {
            alert("пожалуйста заполните поля")
        } else if (vEmail == '') {
            alert(vEmail.attr('title'));
        } else if (vName == '') {
            alert(vName.attr('title'));
        } else if (vPassword == '') {
            alert(vPassword.attr('title'));
        } else if (vConfirmPassword != vPassword) {
            alert(vConfirmPassword.attr('title'));
        }
        else {
            result = true;
        }
        return result;
    }
</script>
<div class="container">
    <div class="card-header">
        <center><h2 id="autho">Регистрация</h2></center>
    </div>
    <div class="card-body">
        <div class="col-md-6 col-md-offset-3" id="login">
            <form action="<%=request.getContextPath()%>/reg.do" method="post">
                <div class="form-group">
                    <label class="user">Email:</label>
                    <div class="input-group">
                        <span class="input-group-addon" id="iconn"> <i class="glyphicon glyphicon-user"></i></span>
                        <input type="email" class="form-control" id="email" name="email" title="Enter Email"
                               placeholder="Example@email.org">
                    </div>
                </div>
                <div class="form-group">
                    <label class="user">Name:</label>
                    <div class="input-group">
                        <span class="input-group-addon" id="iconns"> <i class="glyphicon glyphicon-user"></i></span>
                        <input type="text" class="form-control" id="name" name="name" title="Enter Your Name"
                               placeholder="Example Name">
                    </div>
                </div>
                <div class="form-group">
                    <label class="user">Password:</label>
                    <div class="input-group">
                        <span class="input-group-addon" id="iconn1"> <i class="glyphicon glyphicon-lock"></i></span>
                        <input type="password" class="form-control" id="pwd" name="password" title="Enter Password"
                               placeholder="Enter Password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="user">Confirm Password:</label>
                    <div class="input-group">
                        <span class="input-group-addon" id="iconn2"> <i class="glyphicon glyphicon-lock"></i></span>
                        <input type="password" class="form-control" id="pwdconf" name="pwdconf" title="Confirm Password"
                               placeholder="Confirm Password">
                    </div>
                </div>
                <button type="submit" class="btn btn-success" onclick="validate()"
                        style="border-radius:5px;">
                    Создать
                </button>
                <button type="reset" class="btn btn-danger" style="border-radius:5px;">
                    Очистить
                </button>
                <br/>
                <button type="submit" class="btn btn-default" style="border-radius:5px;">
                    <a href="<%=request.getContextPath()%>/auth.do"
                       style="color: black; font-size: 15px; float: right; margin-right: 10px;">
                        Отмена</a>
                </button>
            </form>
        </div>
        </center>
    </div>
</div>
</div>
</body>
</html>