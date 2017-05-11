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
    <h1>班级人员管理</h1>
    <hr/>
    <h2>${clazz.name}</h2>
    <h3>班级人数：${clazz.count}人<a href="javascript:onAddClazzClick()" type="button" class="btn btn-default btn-sm">添加</a></h3>

    <!-- 如果用户列表为空 -->
    <c:if test="${empty userList}">
        <p class="bg-warning">
            <br/>
            班级暂时无人，请<a href="javascript:onAddClazzClick()" type="button" class="btn btn-default btn-sm">添加</a>
            <br/>
            <br/>
        </p>
    </c:if>

    <!-- 如果用户列表非空 -->
    <c:if test="${!empty userList}">
        <table class="table table-bordered table-striped">
            <tr>
                <th>ID</th>
                <th>班级名称</th>
                <th>性别</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.userEntity.name}</td>
                    <td>${user.userEntity.sex}</td>
                    <td>
                        <a href="/clazz/user/del/${clazz.id}/${user.id}" type="button" class="btn btn-sm btn-danger">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="usersChosenModalLabel" id="usersChosenModal">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="usersChosenModalLabel">选择班级为空的学生</h4>
            </div>
            <div class="modal-body">
                <div class="h-500 list-group" id="usersList">

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="addUserSubmitClick()">确认保存</button>
            </div></div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/sweetalert.min.js"></script>
<script>
    var staticUsers=[]
    $(function () {
        setUserModelEvent()
    })

    function setUserModelEvent() {
        $("#usersChosenModal").on("show.bs.modal",function () {
            getUsers()
        })
    }

    function getUsers() {
        $.ajax({
            url:"/role/ajax/getStudentsFree",
            type:"POST",
            success:function (data) {
                if(data.result){
                    staticUsers=data.t;
                    renderUsers();
                }else {
                    swal(data.message)
                }
            }
        })
    }

    function renderUsers() {
        $("#usersList").empty();
        for(var i=0;i<staticUsers.length;i++){
            $("#usersList").append('<li class="list-group-item roleItem"><input type="checkbox" onchange="onUserCheckBoxChange(this,'+i+')"><span class="ml20">'+staticUsers[i].name+'</span></li>');
        }
    }

    function onUserCheckBoxChange(e,key) {
        var checked=$(e).is(":checked");
        staticUsers[key].checked=checked;
    }

    function onAddClazzClick() {
        $("#usersChosenModal").modal("show")
    }

    function addUserSubmitClick() {
        var chson=[];
        for(var i=0;i<staticUsers.length;i++){
            if(staticUsers[i].checked){
                chson.push(staticUsers[i].id);
            }
        }
        $.ajax({
            url:"/clazz/ajax/addUser",
            type:"POST",
            data:{userIds:chson,clazzId:${clazz.id}},
            traditional:true,
            success:function (data) {
                if(data.result){
                    location.reload()
                }else {
                    swal(data.message)
                }
            }
        })
    }
</script>
</body>
</html>
