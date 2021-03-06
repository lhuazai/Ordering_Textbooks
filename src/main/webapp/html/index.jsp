<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sjj
  Date: 2015/10/24 0024
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>教材征订系统</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <link rel="stylesheet" href="/css/base.css">
    <![endif]-->
    <style>
        .mid-main {
            position: absolute;
            top: 50px;
            bottom: 30px;
            left: 0;
            right: 0;
        }
        .nav-group{
            position: absolute;
            left: 0;
            top:0;
            bottom: 0;
            width: 120px;
            padding-top: 16px;
        }
        .content-group{
            position: absolute;
            left: 120px;
            top: 0;
            bottom: 0;
            right: 0;
            padding: 16px;
        }
        #main-iframe{
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default" style="height: 50px">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">教材征订系统</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">你好，${user.name} <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li data-url="/showUser/${user.id}" class="data-url"><a href="#/showUser/${user.id}">个人资料</a></li>
                        <%--<li><a href="#">更改密码</a></li>--%>
                        <li role="separator" class="divider"></li>
                        <li><a href="/userLoginout">退出登陆</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="mid-main">
    <div class="relative">
        <div class="nav-group">
            <ul class="nav nav-pills nav-stacked">
                <c:forEach items="${menus}" var="menu">
                    <li role="presentation" data-url="${menu.url}" class="data-url"><a href="#${menu.url}">${menu.name}</a></li>
                </c:forEach>
                <%--<li role="presentation" data-url="/users" class="active data-url"><a href="#/users">用户管理</a></li>--%>
                <%--<li role="presentation" data-url="/role/userRolePage" class="data-url"><a href="#/role/userRolePage">角色管理</a></li>--%>
                <%--<li role="presentation" data-url="/book/list" class="data-url"><a href="#/book/list">教材管理</a></li>--%>
                <%--<li role="presentation" data-url="/clazz/list" class="data-url"><a href="#/clazz/list">班级管理</a></li>--%>
                <%--<li role="presentation" data-url="/plan/list" class="data-url"><a href="#/plan/list">计划管理</a></li>--%>
            </ul>

        </div>
        <div class="content-group">
            <iframe src="/users" id="main-iframe">
            </iframe>
        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(function () {
        $('[role=presentation]').click(function (event) {
            var e=event.currentTarget;
            $(e).siblings('[role=presentation]').removeClass("active");
            $(e).addClass("active");
        })
        $('.data-url').click(function (event) {
            var e=event.currentTarget;
            $('#main-iframe').attr("src",$(e).attr("data-url"))
        })
        $('[role=presentation]').first().click();
    })

</script>
</body>
</html>
