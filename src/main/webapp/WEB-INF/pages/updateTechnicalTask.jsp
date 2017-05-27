<%--
Created by IntelliJ IDEA.
User: Kirill
Date: 05.03.2017
Time: 14:12
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" xmlns:c="http://www.w3.org/1999/html">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Создание нового технического задания</title>

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="
    /resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="
    /resources/css/starter-template.css" />" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css"/>
    <!-- Custom CSS -->
    <style>
        body {
            padding-top: 70px;
            /* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
        }

        .error {
            border-color: red;
        }

        .borderless td, .borderless th {
            border: none;
        }


    </style>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
<!--    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>-->
    <![endif]-->

</head>

<body>

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
                    <a href="/home">На главную</a>
                </li>
                <li>
                    <a href="/logout">Выход</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<!-- Page Content -->
<div id="container">
    <div class="col-md-8 col-md-offset-2">
        <input id="idTechnicalTask" type="hidden" value="${id}">
        <label>Название работы</label>
        <br>
        <textarea class="form-control" id="name" maxlength=8000> ${name}</textarea>
        <br>
        <label>Тема работы</label>
        <br>
        <textarea class="form-control" id="theme">${theme}</textarea>
        <br>
        <label>Цель работы</label>
        <br>
        <textarea class="form-control" id="target">${target}</textarea>
        <br>
        <label>Основные Задачи</label>
        <br>
        <div id="tasksContainer">
            <div class="col-md-12" style="padding-left:0px; padding-right: 0px;">
                <table class=" table table-hover  table-bordered  " style="" id="mainTasks">
                    <thead>
                    </thead>
                    <tbody id="mainTaskBody">
                    <c:forEach items="${tasks}" var="task">
                        <tr>
                            <td style="border: none;"><textarea class="form-control" type="text">${task.getDescription()}</textarea></td>
                            <td style="min-width: 120px;max-width: 120px; width: 120px;" ><input placeholder="Срок" class="form-control datePicker" value="${task.getDate()}"/></td>
                            <td style="width:50px; border: none;">
                                <button class="btn btn-addTask" style=" margin-top: 15px;float:left;"
                                        id="buttonTask">
                                    <span class="glyphicon glyphicon-plus"></span>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
            <div>

            </div>

        </div>
        <div><label>Тип Работы</label></div>
        <select size="1" class="form-control selectpicker" id="type">
            <c:forEach items="${types}" var="type">
                <option value=<c:out value="${type.getId()}"/>
                <c:if test="${type.getId() == techncalTask.getTypeTechnicalTask()}">
                    <c:out value="selected"></c:out>
                </c:if>

                > ${type.getType()} </option>
            </c:forEach>
        </select>
        <br>
        <label>Дисциплина</label>
        <br>
        <select class="form-control" id="discipline">
            <c:forEach items="${disciplines}" var="discipline">
                <option value=
                                <c:out value="${discipline.getId()}"/>
                <c:if test="${discipline.getId()} == ${techncalTask.getDiscipline()}">
                    <c:out value="selected"></c:out>
                </c:if>
                >
                <c:out value="${discipline.getName()}"/>
                </option>
            </c:forEach>
        </select>
        <br>
        <div class="col-md-12" id="claimContainer" style="padding-left:0px;     padding-right: 0px;">
            <div class="col-md-12 claim" id="firstClaim" style="padding-left:0px;     padding-right: 0px;">
                <c:forEach items="${demands}" var="demand">
                    <label>Новый раздел ТЗ </label>
                    <table class=" table table-hover  table-bordered " style="" id="claim">
                        <thead>
                        </thead>
                        <tbody id="claimBody" class="claimBody">
                        <tr>
                            <td style="border: none;"><input class="form-control" type="text"
                                                             value="${demand.getKey()}" placeholder="Название"></input>
                            </td>
                            <td style="width:50px; border: none;">
                                <button class="btn btn-addClaim" style=" float:left;" id="claimAdd">
                                    <span class="glyphicon glyphicon-plus"></span>
                                </button>
                            </td>
                        </tr>
                        <c:forEach items="${demand.getValue()}" var="value">
                            <tr>
                                <td style="border: none;"><textarea class="form-control"
                                                                    placeholder="Подраздел">${value}</textarea>
                                </td>
                                <td style="border: none;">
                                    <button class="btn btn-addSubClaim" style=" margin-top: 15px;float:left;"
                                            id="subClaimAdd">
                                        <span class="glyphicon glyphicon-plus"></span>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:forEach>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-3">
                <button type="submit" value="Отправить" class="btn btn-secondary btn-lg"
                        id="submit" style="margin-top:30px;"> Отправить
                </button>
            </div>
            <div id="spinner" class="col-sm-4" style="">

            </div>
        </div>
    </div>

</div>
<!-- /.container -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">New</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="message-text" class="form-control-label"></label>
                        <textarea maxlength=100 class="form-control" id="message-text"></textarea>
                    </div>
                </form>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>

    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Modal Header</h4>
                </div>
                <div class="modal-body">
                    <p>Some text in the modal.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>
    <!-- jQuery Version 1.11.1 -->
    <script src="<c:url value="
    /resources/js/lib/jquery-3.2.0.js" />"></script>

    <!-- Bootstrap Core JavaScript -->
    <
    script
    src = "<c:url value=" / resources / js / bootstrap.min.js
    " />" ></script>
    <script src="<c:url value="
    /resources/js/update.js" />"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js"></script>
    <script src = "<c:url value="/resources/js/spiner.min.js" />" ></script>

</
body >

< / html >

