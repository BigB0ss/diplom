<%--
Created by IntelliJ IDEA.
User: Kirill
Date: 15.03.2017
Time: 23:43
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
</style>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Latest compiled and minified CSS -->
    <!-- Latest compiled and minified CSS -->
    <!-- Latest compiled JavaScript -->
    <link href="<c:url value="
    /resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="
    /resources/css/starter-template.css" />" rel="stylesheet">
</head>

<body>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Об приложении</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/home/create-technical-task">Создать Техническое задание</a></li>
                <li><a href="/logout">Выход</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>
<div class="container">
    <div class="row">
        <sec:authorize access="hasRole('ROLE_STUDENT')">
            <div class="col-lg-12 col-lg-offset-2">
                <div class="col-xs-8 col-xs-offset-3"><h4>Ваши технические задания</h4></div>
            </div>
        </sec:authorize>
    </div>
    <div class="row" style="">
        <sec:authorize access="hasRole('ROLE_STUDENT')">
            <div id="studentTechTask" class="col-lg-12 ">
                <c:if test="${technicalTasks.isEmpty()}">
                    <p>Пока список ваших заданий пуст. </p>
                </c:if>
                <c:if test="${!technicalTasks.isEmpty()}">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Название</th>
                            <th>Дата Создания</th>
                            <th>Тип</th>
                            <th>Дисциплина</th>
                            <th>Опции</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${technicalTasks}" var="technicalTasks">
                            <tr>
                                <td>${technicalTasks.getName()}</td>
                                <td>${technicalTasks.getDateCreated()}</td>
                                <td>${technicalTasks.getTypeTechnicalTask()}</td>
                                <td>${technicalTasks.getDiscipline()}</td>
                                <td>
                                    <button type="button" class="btn btn-secondary btn-sm">Редактировать</button>
                                    <button type="button" class="btn btn-secondary btn-sm">Копровать</button>
                                    <button type="button" class="btn btn-secondary btn-sm">Удалить</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                </c:if>

            </div>
        </sec:authorize>
    </div>
    <sec:authorize access="hasRole('ROLE_TEACHER')">
        <div class="row">
            <div class="col-lg-12">
                <div class="col-lg-12 col-lg-offset-2">
                    <div class="col-xs-8 col-xs-offset-3"><h4>Ваши технические задания</h4></div>
                </div>
                <div id="teachersTechTask" class="col-lg-12 ">
                    <c:if test="${technicalTasks.isEmpty()}">
                        <p>Пока список ваших заданий пуст. </p>
                    </c:if>
                    <c:if test="${!technicalTasks.isEmpty()}">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Название</th>
                                <th>Дата Создания</th>
                                <th>Тип</th>
                                <th>Дисциплина</th>
                                <th>Опции</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${technicalTasks}" var="technicalTasks">
                                <tr>
                                    <td>${technicalTasks.getName()}</td>
                                    <td>${technicalTasks.getDateCreated()}</td>
                                    <td>${technicalTasks.getTypeTechnicalTask()}</td>
                                    <td>${technicalTasks.getDiscipline()}</td>
                                    <td id="${technicalTasks.getId()}">

                                        <a href="/home/update-technical-task?id=${technicalTasks.getId()}"
                                           class="btn btn-secondary btn-sm updateTechTask"
                                           value="${technicalTasks.getId()}">Редактировать
                                        </a>

                                        <input id="idTechnicalTask" class="" type="hidden"
                                               value="${technicalTasks.getId()}"/>
                                        <button type="button" class="btn btn-secondary btn-sm addTTForStudent">
                                            Назначить
                                        </button>

                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>

                        </table>
                    </c:if>

                </div>
            </div>
        </div>
    </sec:authorize>
</div><!-- /.container -->

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="width:100%;">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Список студентов</h4>
            </div>
            <div class="modal-body">
                <c:forEach items="${students}" var="student">
                    ${student.getName()}

                </c:forEach>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<!-- jQuery Version 1.11.1 -->
<script src="<c:url value="
/resources/js/lib/jquery-3.2.0.js" />"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="
/resources/js/home.js" />"></script>
</body>
</html>
