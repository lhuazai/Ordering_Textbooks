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
    <title>SpringMVC Demo 首页</title>

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
    </style>
</head>
<body>
<nav class="navbar navbar-default" style="height: 50px">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Brand</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">你好，王家卫 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">个人设置</a></li>
                        <li><a href="#">更改密码</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">退出登陆</a></li>
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
                <li role="presentation" class="active"><a href="#">Home</a></li>
                <li role="presentation"><a href="#">Profile</a></li>
                <li role="presentation"><a href="#">Messages</a></li>
            </ul>

        </div>
        <div class="content-group">
            <h1>这里是图书采购系统的首页</h1>
            <h3>出现此页面，说明登陆成功。</h3>
            <h3>目前进度：</h3>
            <h4>主体框架：完成</h4>
            <h4>角色模块：开发中</h4>
        </div>

    </div>


</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>