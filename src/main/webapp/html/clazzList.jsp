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
    <title>班级管理</title>

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
    <h1>班级管理</h1>
    <hr/>

    <h3>所有班级 <a href="javascript:onAddClazzClick()" type="button" class="btn btn-default btn-sm">添加</a></h3>

    <!-- 如果用户列表为空 -->
    <c:if test="${empty clazzList}">
        <p class="bg-warning">
            <br/>
            教材表为空，请<a href="/clazz/add" type="button" class="btn btn-default btn-sm">添加</a>
            <br/>
            <br/>
        </p>
    </c:if>

    <!-- 如果用户列表非空 -->
    <c:if test="${!empty clazzList}">
        <table class="table table-bordered table-striped">
            <tr>
                <th>ID</th>
                <th>班级名称</th>
                <th>人数</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${clazzList}" var="clazz">
                <tr>
                    <td>${clazz.id}</td>
                    <td>${clazz.name}</td>
                    <td>${clazz.count}</td>
                    <td>
                        <a href="/clazz/del/${clazz.id}" type="button" class="btn btn-sm btn-danger">删除</a>
                        <a href="/clazz/users/${clazz.id}" type="button" class="btn btn-sm btn-info">班级人员配置</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/sweetalert.min.js"></script>
<script>
    function onAddClazzClick() {
        swal({
                title: "新增班级",
                text: "请输入班级名称",
                type: "input",
                showCancelButton: true,
                closeOnConfirm: false,
                showLoaderOnConfirm: true,
            },
            function(inputValue){
                if (inputValue === false) return false;
                if (inputValue === "") {
                    swal({
                        title:"警告",
                        text:"请至少输入一个汉字",
                        type:"error"
                    })
                    return false
                }
                $.ajax({
                    url:"/clazz/add",
                    type:"POST",
                    data:{name:inputValue},
                    success:function (data) {
                        if(data.result){
                            swal({
                                title:"成功",
                                text:"添加成功",
                                type:"success"
                            },function () {
                                location.reload();
                            })
                        }else {
                            swal({
                                title:"错误",
                                text:data.message,
                                type:"error"
                            })
                        }
                    },
                    error:function () {
                        swal({
                            title:"错误",
                            text:"服务错误",
                            type:"error"
                        })
                    }
                })
            });
    }
</script>
</body>
</html>