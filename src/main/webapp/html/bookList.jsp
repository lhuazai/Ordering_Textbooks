<%--
  Created by IntelliJ IDEA.
  User: sjj
  Date: 2015/10/24 0024
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>教材管理</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/base.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        #searchBtn{
            width: 200px;
        }
        .h-34{
            height: 34px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>教材管理</h1>
    <hr/>

    <h3>全部教材 <a href="/book/add" type="button" class="btn btn-default btn-sm">添加</a></h3>

    <!-- 如果用户列表为空 -->
    <c:if test="${empty bookList}">
        <p class="bg-warning">
            <br/>
            列表为空，请<a href="/book/add" type="button" class="btn btn-default btn-sm">添加</a>
            <br/>
            <br/>
        </p>
    </c:if>


        <table class="table table-bordered table-striped">
            <tr>
            <td colspan="12">
                    <form class="m5 pull-right" action="/book/list" method="get" accept-charset="UTF-8">
                        <label for="searchBtn" class="h-34">关键字搜索</label>
                        <input type="text" class="form-control inline h-34 ml10" id="searchBtn" name="word" value="${word}" placeholder="输入课程关键字">
                        <button type="submit" class="btn btn-default list-inline h-34 ml10">搜索</button>
                    </form></td></tr>
            <!-- 如果用户列表非空 -->
            <c:if test="${!empty bookList}">
            <tr>
                <th>ID</th>
                <th>教材名称</th>
                <th>出版社</th>
                <th>价格</th>
                <th>添加人</th>
                <th>添加时间</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${bookList}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>${book.publisher}</td>
                    <td>￥<fmt:formatNumber type="number" value="${book.price}"  pattern="#.00"/></td>
                    <td>${book.user.name}</td>
                    <td>${book.addTime}</td>
                    <td>
                        <a href="/book/del/${book.id}" type="button" class="btn btn-sm btn-danger">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </c:if>
        </table>

</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="/js/bootstrap.min.js"></script>
</body>
</html>