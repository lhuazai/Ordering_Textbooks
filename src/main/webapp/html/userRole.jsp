<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/25
  Time: 0:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>SpringMVC 用户管理</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/base.css">
    <link rel="stylesheet" href="/css/sweetalert.css">
    <link rel="stylesheet" href="/css/loading.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        a > span:hover {
            color: red;
        }

        .h-1000 {
            height: 1000px;
        }

        .h-500 {
            height: 500px;
        }

        .w-40 {
            width: 40px;
        }

        .c-gray {
            color: gray;
        }

        .c-gold {
            color: gold;
        }
    </style>
</head>
<body>

<div class="w-1300 margin-auto">
    <h1>角色管理</h1>
    <hr/>
    <div class="inline w-400 h-1000 p-10">
        <h3>所有角色 <a data-toggle="modal" data-target="#addRoleModal" type="button" class="btn btn-default btn-sm">添加</a>
        </h3>
        <div class="list-group">
            <c:forEach items="${roleList}" var="item">
                <a href="javascript:void(0)" onclick="onRoleItemClick(${item.id})" class="list-group-item roleItem"
                   role="presentation">${item.name}<span class="pull-right glyphicon glyphicon-remove"
                                                         onclick="onRoleItemDelClick(${item.id})"></span></a>
            </c:forEach>
        </div>
    </div>
    <div class="inline w-400 h-1000 ml20 p-10" style="border-left: 1px solid gray">
        <h3>角色成员 <a data-toggle="modal" data-target="#usersChosenModal" type="button" class="btn btn-default btn-sm"
                    id="addUserRoleButton" style="display: none">添加</a></h3>
        <div class="list-group" id="userRole-group">
        </div>
    </div>
    <div class="inline w-400 h-1000 ml20 p-10" style="border-left: 1px solid gray">
        <h3>角色目录 <a data-toggle="modal" data-target="#addMenuModal" type="button" class="btn btn-default btn-sm"
                    id="addMenuButton">添加</a></h3>
        <div class="list-group" id="menuList">
            <c:forEach items="${menuList}" var="item">
                <a href="javascript:void(0)"class="list-group-item roleItem"
                   key="${item.id}"
                   title="${item.url}">
                    <div class="inline w-40">
                        <span class="glyphicon glyphicon-ok hiden" onclick="onToggleMenuClick(this)" ></span>
                    </div>
                        ${item.name}<span class="pull-right glyphicon glyphicon-remove"
                                          onclick="onDelMenuClick(${item.id})"></span>
                </a>
            </c:forEach>
        </div>
    </div>
</div>

<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     id="addRoleModal">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="exampleModalLabel">添加角色</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label class="control-label">角色名称:</label>
                        <input type="text" name="roleName" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="control-label">角色说明:</label>
                        <textarea class="form-control" name="roleIntro"></textarea>
                    </div>
                </form>
                <div class="alert alert-danger" role="alert" id="addRoleError"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="addRoleSubmitClick()">确认保存</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="usersChosenModalLabel"
     id="usersChosenModal">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="usersChosenModalLabel">添加角色</h4>
            </div>
            <div class="modal-body">
                <div class="h-500 list-group" id="usersList">

                </div>
                <div class="alert alert-danger" role="alert" id="usersChosenError"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="addUserRoleSubmitClick()">确认保存</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="addMenuModalLabel"
     id="addMenuModal">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addMenuModalLabel">添加目录</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label class="control-label">目录名称:</label>
                        <input type="text" name="menuName" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="control-label">URL:</label>
                        <textarea class="form-control" name="menuURL"></textarea>
                    </div>
                </form>
                <div class="alert alert-danger" role="alert" id="addMenuError"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="onAddMenuSubmitClick()">确认新增</button>
            </div>
        </div>
    </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="/js/sweetalert.min.js"></script>
<script src="/js/loading.js"></script>
<script>
    var staticUserRole = [];
    var staticUsers = [];
    var chosenRoleId;
    var staticRoleMenuList = [];

    $(function () {
        setAddRoleModalEvent();
        setRoleItemClickEvent();
        setUsersChosenModalEvent();
        setAddMenuModalEvent();
    })

    function setAddMenuModalEvent() {
        $('#addMenuModal').on('show.bs.modal', function (e) {
            hideAddMenuError();
        })
    }

    function setRoleItemClickEvent() {
        $('[role=presentation]').click(function (event) {
            var e = event.currentTarget;
            $(e).siblings('[role=presentation]').removeClass("active");
            $(e).addClass("active");
        })
    }

    function setAddRoleModalEvent() {
        $('#addRoleModal').on('show.bs.modal', function (e) {
            addRoleErroHide();
        })
    }

    function onRoleItemClick(key) {
        getUserRoleWithRoleID(key)
    }

    function getUserRoleWithRoleID(roleId) {
        $('#userRole-group').empty().html("加载中……");
        $.ajax({
            url: "/role/ajax/RoleUser/" + roleId,
            success: function (data) {
                chosenRoleId = roleId;
                staticUserRole = data.t.userRoleList;
                $('#addUserRoleButton').show();
                refreshUserRoleList();
                staticRoleMenuList = data.t.roleMenuList;
                refreshRoleMenuList();
            },
            error: function () {
                swal({
                    title: "错误",
                    text: data.message,
                    type: "error"
                })
            }
        })
    }

    function refreshRoleMenuList() {
        var listE = $("#menuList>a");
        for (var i = 0; i < listE.length; i++) {
            var key = $(listE[i]).attr("key");
            var isExist = false;
            for (var j = 0; j < staticRoleMenuList.length; j++) {
                if (staticRoleMenuList[j].menuId == key) {
                    isExist = true;
                }
            }
            if (isExist) {
                $(listE[i]).children("div").children("span").removeClass("c-gray").addClass("c-gold").show();
            } else {
                $(listE[i]).children("div").children("span").removeClass("c-gold").addClass("c-gray").show();
            }
        }
    }

    function refreshUserRoleList() {
        $('#userRole-group').empty();
        for (var i = 0; i < staticUserRole.length; i++) {
            $('#userRole-group').append('<a class="list-group-item roleItem">' + staticUserRole[i].userEntity.name + '<span class="pull-right glyphicon glyphicon-remove" onclick="delUserRoleClick(' + i + ')"></span></a>')
        }
    }

    function onRoleItemDelClick(key) {
        swal({
            title: "确认",
            text: "确定要删除本角色么，此操作无法恢复",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        }, function () {
            $.ajax({
                url: "/role/ajax/delRole/" + key,
                type: "GET",
                success: function (data) {
                    if (data.result) {
                        swal({
                            title: "成功",
                            text: data.message,
                            type: "success"
                        }, function () {
                            location.reload()
                        })
                    } else {
                        swal({
                            title: "错误",
                            text: data.message,
                            type: "error"
                        }, function () {
                            location.reload()
                        })
                    }

                },
                error: function () {
                    swal({
                        title: "警告",
                        text: "请求异常",
                        type: "error"
                    }, function () {
                        location.reload()
                    })
                }
            })
        })
        stopBubble(window.event)
    }

    function addRoleSubmitClick() {
        var name = $('#addRoleModal input[name="roleName"]').val();
        var intro = $('#addRoleModal textarea[name="roleIntro"]').val();
        if (!name) {
            addRoleErroShow("角色名不可为空");
            return;
        }
        ;
        if (!intro) {
            addRoleErroShow("角色说明不可为空");
            return;
        }
        ;
        showLoadingLayer();
        $.ajax({
            url: "/role/ajax/addRole",
            type: "POST",
            data: {name: name, intro: intro},
            success: function (data) {
                removeLoadingLayer();
                if (data.result) {
                    addRoleModalHide();
                    swal({
                        title: "通知",
                        text: data.message,
                        type: "success"
                    }, function () {
                        location.reload();
                    })
                }
                else {
                    addRoleErroShow(data.message)
                }
            },
            error: function () {
                removeLoadingLayer();
                swal({
                    title: "警告",
                    text: "请求异常",
                    type: "error"
                })
            }
        })
    }
    function addRoleErroShow(text) {
        $("#addRoleError").empty().text(text).show();
    }
    function addRoleErroHide() {
        $("#addRoleError").empty().hide();
    }
    function addRoleModalHide() {
        $('#addRoleModal').modal('hide');
    }

    function setUsersChosenModalEvent() {
        $("#usersChosenModal").on("show.bs.modal", function (e) {
            getUsers();
            hideUserChosenError();
        })
    }

    function getUsers() {
        $("#usersList").html("加载中");
        $.ajax({
            url: "/ajax/users",
            type: "GET",
            success: function (data) {
                if (data.result) {
                    staticUsers = data.t;
                    refreshUsers();
                } else {
                    $("#usersList").html("加载失败");
                }
            },
            error: function () {
                $("#usersList").html("加载失败");
            }

        })
    }

    function showUserChosenError(text) {
        $("#usersChosenError").empty().text(text).show();
    }
    function hideUserChosenError() {
        $("#usersChosenError").empty().hide();
    }

    function refreshUsers() {
        $("#usersList").empty();
        for (var i = 0; i < staticUsers.length; i++) {
            $("#usersList").append('<li class="list-group-item roleItem"><input type="checkbox" onchange="onUserCheckBoxChange(this,' + i + ')"><span class="ml20">' + staticUsers[i].name + '</span></li>');
        }
    }

    function onUserCheckBoxChange(e, key) {
        var checked = $(e).is(":checked");
        staticUsers[key].checked = checked;
    }

    function addUserRoleSubmitClick() {
        var chosen = [];
        for (var i = 0; i < staticUsers.length; i++) {
            if (staticUsers[i].checked) {
                chosen.push(staticUsers[i].id);
            }
        }
        if (chosen.length == 0) {
            showUserChosenError("请至少选择一个用户");
            return;
        }
        $.ajax({
            url: "/role/ajax/addUserRole",
            type: "POST",
            data: {roleId: chosenRoleId, userIds: chosen},
            traditional: true,
            success: function (data) {
                console.log(data)
            }
        })
    }

    function delUserRoleClick(key) {
        var userRoleId = staticUserRole[key].id;
        swal({
            title: "确认",
            text: "是否删除该角色",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        }, function () {
            $.ajax({
                url: "/role/ajax/delUserRole/" + userRoleId,
                type: "GET",
                success: function (data) {
                    if (data.result) {
                        swal({
                            title: "成功",
                            text: data.message,
                            type: "success"
                        });
                    } else {
                        swal({
                            title: "错误",
                            text: data.massage,
                            type: "error"
                        })
                    }
                    getUserRoleWhithCurrentRoleId();
                },
                error: function () {
                    swal({
                        title: "警告",
                        text: "请求异常",
                        type: "error"
                    })
                    getUserRoleWhithCurrentRoleId();
                }
            })
        })
    }
    function getUserRoleWhithCurrentRoleId() {
        getUserRoleWithRoleID(chosenRoleId);
    }

    function onAddMenuSubmitClick() {
        var name = $('#addMenuModal input[name="menuName"]').val();
        var url = $('#addMenuModal textarea[name="menuURL"]').val();
        if (!name) {
            showAddMenuError("目录名称不可为空");
            return;
        }
        ;
        if (!url) {
            showAddMenuError("目录链接不可为空");
            return;
        }
        ;
        showLoadingLayer();
        $.ajax({
            url: "/role/ajax/addMenu",
            type: "POST",
            data: {name: name, url: url},
            success: function (data) {
                if (data.result) {
                    swal({
                        title: "成功",
                        text: data.message,
                        type: "success"
                    }, function () {
                        location.reload()
                    });
                } else {
                    swal({
                        title: "错误",
                        text: data.massage,
                        type: "error"
                    }, function () {
                        location.reload()
                    })
                }
            },
            error: function () {
                swal({
                    title: "警告",
                    text: "请求异常",
                    type: "error"
                }, function () {
                    location.reload()
                })
            }
        })
    }

    function showAddMenuError(text) {
        $("#addMenuError").empty().text(text).show();
    }
    function hideAddMenuError() {
        $("#addMenuError").empty().hide();
    }
    function onDelMenuClick(id) {
        swal({
            title: "确认",
            text: "是否删除该菜单",
            type: "info"
        }, function () {
            var a = $("<form action='/role/delMenu/" + id + "' method='get'></form>");
            a.appendTo($("body"));
            a.submit();
        })
    }
    function onToggleMenuClick(e) {
        var menuId = $(e).parent().parent().attr("key");
        console.log(menuId)
        var matched=isTheMenuBindWhitChosonRole(menuId);
        if(!matched){
            bindMenu(menuId,chosenRoleId);
        }else {
            unBindMenu(menuId,chosenRoleId);
        }
    }

    function bindMenu(menuId, roleId) {
        showLoadingLayer();
        $.ajax({
            "url": "/role/ajax/binMenu",
            data: {menuId:menuId,roleId:roleId},
            type:"POST",
            success:function (data) {
                removeLoadingLayer();
                if(data.result){
                    $("#menuList>a[key="+menuId+"]>div>span").removeClass("c-gray").addClass("c-gold").show();
                }else {
                    swal({
                        title:"警告",
                        text:data.message,
                        type:"error"
                    })
                }
            },
            error:function () {
                swal({
                    title:"警告",
                    text:"后台错误",
                    type:"error"
                })
            }
        })
    }

    function unBindMenu(menuId, roleId) {
        showLoadingLayer();
        $.ajax({
            "url": "/role/ajax/unBinMenu",
            data: {menuId:menuId,roleId:roleId},
            type:"POST",
            success:function (data) {
                removeLoadingLayer();
                if(data.result){
                    $("#menuList>a[key="+menuId+"]>div>span").removeClass("c-gold").addClass("c-gray").show();
                }else {
                    swal({
                        title:"警告",
                        text:data.message,
                        type:"error"
                    })
                }
            },
            error:function () {
                swal({
                    title:"警告",
                    text:"后台错误",
                    type:"error"
                })
            }
        })
    }


    function isTheMenuBindWhitChosonRole(menuId) {
        for(var i=0;i<staticRoleMenuList.length;i++){
            if(staticRoleMenuList[i].menuId==menuId){
                return true;
            }
        }
        return false;
    }


    function stopBubble(e) {
        //如果提供了事件对象，则这是一个非IE浏览器
        if (e && e.stopPropagation)
        //因此它支持W3C的stopPropagation()方法
            e.stopPropagation();
        else
        //否则，我们需要使用IE的方式来取消事件冒泡
            window.event.cancelBubble = true;
    }
</script>
</body>
</html>
