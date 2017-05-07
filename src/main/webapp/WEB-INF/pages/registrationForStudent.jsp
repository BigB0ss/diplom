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
    <script src="<c:url value="/resources/js/registrationForStudent.js" />"></script>

  <script src="/resources/js/bootstrap.min.js"></script>

    <style>
        .error {
            border-color: red;
        }
    </style>
</head>
<body>
<form class="form-horizontal">
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
                <input id="email" name="email" type="email" placeholder="email" class="form-control input-md"
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

            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="selectbasic">Группа</label>
            <div class="col-md-4">
                <select id="group" name="group" class="form-control input-md">
                    <c:forEach items="${groups}" var="group">
                        <option value=<c:out value="${group.getId()}"/>> ${group.getName()} </option>
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
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">

      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>

    </div>
  </div>

</body>
</html>
