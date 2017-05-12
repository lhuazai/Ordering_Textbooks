<%--
  Created by IntelliJ IDEA.
  User: sjj
  Date: 2015/10/24 0024
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>订单教材管理</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/sweetalert.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        .c-gold{
            color: gold;
        }
        .c-gray{
            color: gray;
        }
        #usersList{
            max-height: 550px;
            overflow: auto;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>课程：${course.name}</h1>
    <h2>所属计划：<i>${plan.name}</i></h2>
    <hr/>
    <h3>教材种类：${course.count}种<c:if test="${isTeacher&&(plan.active==1)}"><a href="javascript:onAddBookClick()" type="button" class="btn btn-default btn-sm">添加</a></c:if></h3>

    <!-- 如果用户列表为空 -->
    <c:if test="${empty courseBookList}">
        <p class="bg-warning">
            <br/>
            订单中暂无教材;<c:if test="${isTeacher&&(plan.active==1)}">请<a href="javascript:onAddBookClick()" type="button" class="btn btn-default btn-sm">添加</a></c:if>
            <br/>
            <br/>
        </p>
    </c:if>

    <!-- 如果用户列表非空 -->
    <c:if test="${!empty courseBookList}">
        <table class="table table-bordered table-striped">
            <tr>
                <th>ID</th>
                <th>教材名称</th>
                <th>教材单价</th>
                <th>预定人数</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${courseBookList}" var="courseBook">
                <tr>
                    <td>${courseBook.id}</td>
                    <td>${courseBook.book.name}</td>
                    <td>￥<fmt:formatNumber type="number" value="${courseBook.book.price}"  pattern="#.00"/></td>
                    <td>${courseBook.count}</td>
                    <td>
                        <c:if test="${isTeachAdmin&&(plan.active==1)}"><a href="/clazz/user/del/${clazz.id}/${user.id}" type="button" class="btn btn-sm btn-danger">删除</a></c:if>
                        <c:if test="${isStudent}">
                            <a href="<c:if test="${false==courseBook.isChosen&&(plan.active==1)}">/plan/course/chooseBook/${courseBook.id}</c:if><c:if test="${true==courseBook.isChosen&&(plan.active==1)}">/plan/course/unChooseBook/${courseBook.id}</c:if>" style="font-size: 30px">
                            <span class="glyphicon <c:if test="${false==courseBook.isChosen}">glyphicon-star-empty c-gray</c:if><c:if test="${true==courseBook.isChosen}">glyphicon-star c-gold</c:if>"></span></a></c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="booksChosenModalLabel" id="booksChosenModal">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="booksChosenModalLabel">请选择要添加的教材</h4>
            </div>
            <div class="modal-body">
                <div class="h-500 list-group" id="usersList">

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="addBookSubmitClick()">确认保存</button>
            </div></div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="/js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/sweetalert.min.js"></script>
<script>
    var staticBooks=[]
    $(function () {
        setUserModelEvent()
    })

    function setUserModelEvent() {
        $("#booksChosenModal").on("show.bs.modal",function () {
            getBooks()
        })
    }

    function getBooks() {
        $.ajax({
            url:"/book/ajax/list",
            type:"POST",
            success:function (data) {
                if(data.result){
                    staticBooks=data.t;
                    renderBooks();
                }else {
                    swal(data.message)
                }
            }
        })
    }

    function renderBooks() {
        $("#usersList").empty();
        for(var i=0;i<staticBooks.length;i++){
            $("#usersList").append('<li class="list-group-item roleItem"><input type="checkbox" onchange="onUserCheckBoxChange(this,'+i+')"><span class="ml20">'+staticBooks[i].name+'</span></li>');
        }
    }

    function onUserCheckBoxChange(e,key) {
        var checked=$(e).is(":checked");
        staticBooks[key].checked=checked;
    }

    function onAddBookClick() {
        $("#booksChosenModal").modal("show")
    }

    function addBookSubmitClick() {
        var chson=[];
        for(var i=0;i<staticBooks.length;i++){
            if(staticBooks[i].checked){
                chson.push(staticBooks[i].id);
            }
        }
        $.ajax({
            url:"/plan/ajax/addBook",
            type:"POST",
            data:{bookIds:chson,courseId:${course.id}},
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
