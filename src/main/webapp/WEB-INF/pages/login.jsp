<%--
  Created by IntelliJ IDEA.
  User: Kirill
  Date: 05.03.2017
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/signin.css"/>" rel="stylesheet">
</head>

<body>

<div class="container">

    <form class="form-signin" method="Post">
        <h2 class="form-signin-heading">Вход</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="text" id="login" class="form-control" placeholder="Email address" required autofocus
               name="username">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="password" class="form-control" placeholder="Password" required name="password">
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Ок</button>
    </form>
    <div class="col-md-2 col-md-offset-5">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/registration-student">Регистрация студента</a></li>
            <li><a href="/registration-teacher">Регистрация преподавателя</a></li>
        </ul>
    </div>
</div> <!-- /container -->

</body>
</html>

