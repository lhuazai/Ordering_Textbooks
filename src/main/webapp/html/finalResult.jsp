<%--
  Created by IntelliJ IDEA.
  User: sjj
  Date: 2015/10/24 0024
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>计划课程管理</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/sweetalert.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <h1>订单详情</h1>
    <hr/>
    <h2>计划：<i>${plan.name}</i></h2>
    <h3>合计价格：${finalPrice}人民币
    </h3>

    <!-- 如果用户列表为空 -->
    <c:if test="${empty courseList}">
        <p class="bg-warning">
            <br/>
            订单中暂无教材;
            <br/>
            <br/>
        </p>
    </c:if>

    <!-- 如果用户列表非空 -->
    <c:if test="${!empty courseList}">
        <table class="table table-bordered table-striped">
            <tr>
                <th>教材名称</th>
                <th>教材ID</th>
                <th>课程名称</th>
                <th>单价</th>
                <th>订购数量</th>
                <th>价格</th>
            </tr>

            <c:forEach items="${courseList}" var="course">
                <c:forEach items="${course.courseBookList}" var="courseBook">
                    <tr>
                        <td>${courseBook.book.name}</td>
                        <td>${courseBook.book.id}</td>
                        <td>${course.name}</td>

                        <td>${courseBook.book.price}</td>
                        <td>${courseBook.count}</td>
                        <td>${courseBook.totalPrice}</td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </table>
    </c:if>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/sweetalert.min.js"></script>
</body>
</html>


