<%--
  Created by IntelliJ IDEA.
  User: Kirill
  Date: 05.03.2017
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
<head>
    <title>registration</title>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/starter-template.css" rel="stylesheet">
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <script src="<c:url value="/resources/js/lib/jquery-3.2.0.js" />"></script>
    <script src ="/resources/js/registrationForTeacher.js"></script>
    <style>
        .error {
            border-color: red;
        }
    </style>
</head>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Об приложении</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a id="exit" href="/logout">Выход</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<body>
<form class="form-horizontal" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend>Форма Регистрации</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="fn">Имя</label>
            <div class="col-md-4">
                <input id="fn" name="firstName" type="text" placeholder="first name" class="form-control input-md"
                       required="">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="ln">Фамилия</label>
            <div class="col-md-4">
                <input id="ln" name="lastName" type="text" placeholder="last name" class="form-control input-md"
                       required="">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="ln">Отчество</label>
            <div class="col-md-4">
                <input id="patronymic" name="patronymic" type="text" placeholder="patronymic" class="form-control input-md"
                       required="">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="email">Email</label>
            <div class="col-md-4">
                <input id="email" name="email" type="text" placeholder="email" class="form-control input-md"
                       required="">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="cmpny">Логин</label>
            <div class="col-md-4">
                <input id="username" name="username" type="text" placeholder="username" class="form-control input-md"
                       required="">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="cmpny">Пароль</label>
            <div class="col-md-4">
                <input id="password" name="password" type="password" placeholder="password"
                       class="form-control input-md" required="">
            <div id="checkPasswordError"> </div>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="cmpny">Повторите пароль</label>
            <div class="col-md-4">
                <input id="passwordR" name="passwordR" type="password" placeholder="password"
                       class="form-control input-md" required="">
            <div id="checkRepeatPasswordError"> </div>
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="selectbasic">Должность</label>
            <div class="col-md-4">
                <select id="post" name="post" class="form-control input-md">
                    <option>Преподаватель</option>
                    <option>Заведующий кафедрой</option>
                </select>
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="selectbasic">Ученая степень</label>
            <div class="col-md-4">
                <select id="academic-degree" name="academic-degree" class="form-control input-md">
                    <option>нет</option>
                    <option>Кандидат Наук</option>
                    <option>Доктор Наук</option>
                </select>
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="selectbasic">Ученое звание</label>
            <div class="col-md-4">
                <select id="academic-title" name="academic-title" class="form-control input-md">
                    <option>нет</option>
                    <option>Доцент</option>
                    <option>Профессор</option>
                </select>
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="selectbasic">Кафедра</label>
            <div class="col-md-4">
                <select id="cathedra" name="cathedra" class="form-control input-md">
                    <c:forEach items="${cathedres}" var="cathedra">
                        <option value=<c:out value="${cathedra.getId()}"/>> ${cathedra.getName()} </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="submit"></label>
            <div class="col-md-4">
                <button id="submit" name="submit" class="btn btn-primary">Отправить</button>
            </div>
        </div>

    </fieldset>
</form>
</body>
</html>
