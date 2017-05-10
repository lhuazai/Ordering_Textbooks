<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/1
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/base.css">
    <style>
        .input-group{
        }
        body{
            background: #33FFFF;
        }
        .main{
            border-radius: 25px;
            background-color: white;
            padding: 50px 100px;
            width: 600px;
            height: auto;
            position: absolute;
            top:50%;
            margin-top: -200px;
            left: 50%;
            margin-left: -300px;
        }
        .center{
            text-align: center;
        }
    </style>
</head>
<body>
<div class="main">
    <h1 class="center">教材管理系统登录</h1>
    <form class="input-group" action="/userLoginPost" method="post">
        <div class="form-group">
            <label for="exampleInputEmail1">用户名</label>
            <input type="text" name="userName" class="form-control"
                   id="exampleInputEmail1" placeholder="用户名" value="${user.userName}" style="width: 400px">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">密码</label>
            <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="密码" style="width: 400px">
        </div>
        <br>
        <c:if test="${not empty message}">
            <div class="alert alert-danger" role="alert">
                    ${message}
            </div>
        </c:if>
        <br>
        <div class="center">
            <input type="submit" class="btn btn-default" value="登录">
        </div>

    </form>
</div>
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script></script>
</body>
</html>
