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


<body style = "background-image: url(resources/img/login.jpg);">

<div class="container">

    <form class="form-signin" method="Post" style="background-color: #DEDEDE ; border-radius: 30px; border: 20px solid #B5B5B5;">
        <h2 class="form-signin-heading">Вход</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="text" id="login" class="form-control" placeholder="Email address" required autofocus
               name="username">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="password" class="form-control" placeholder="Password" required name="password">
        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
              <font color="red">
                Your login attempt was not successful due to <br/><br/>
                <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
              </font>
        </c:if>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Ок</button>
            <div style="margin-top: 10px;"><a href="/registration-student" class="btn btn-lg btn-primary form-control" >Регистрация студента</a></div>
            <div style="margin-top: 10px;"><a href="/registration-teacher" class="btn btn-lg  btn-primary form-control">Регистрация преподавателя</a></div>

    </form>

</div> <!-- /container -->

</body>
</html>

