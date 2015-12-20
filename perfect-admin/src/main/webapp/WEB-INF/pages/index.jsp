<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=10">
    <title>百思-用户管理中心</title>
    <jsp:include page="public/navujs.jsp"/>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="public/header.jsp"/>
    <div class="containers">
        <jsp:include page="public/nav.jsp"/>
        <div class="middle_containers">
            <div class="page_title">
                当前位置：用户管理
            </div>
            <div class="user_box">
                <div class="admin_title">
                    <label class="control-label fl"> 账户状态：</label>

                    <div class="fl select">
                        <div class="dropdown">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                所有账户
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <li><a href="#">所有账户</a></li>
                                <li><a href="#">启用</a></li>
                                <li><a href="#">禁用</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="fl">
                        <input type="text" class="adminInput" placeholder="请输入姓名、登录账号">
                    </div>
                    <div class="fl">
                        <span class="adminButton">搜索</span>
                    </div>
                    <!-- /.row -->

                </div>
                <table id="userAdmin" data-click-to-select="true" data-query-params="queryParams"
                       data-pagination="true">
                    <thead>
                    <tr>
                        <th data-field="state" data-checkbox="true"></th>
                        <th data-field="name">序号</th>
                        <th data-field="name">公司名称</th>
                        <th data-field="remark">用户名</th>
                        <th data-field="password" data-formatter="passwordFormatter" data-events="operateEvents">密码</th>
                        <th data-field="wedUrl">注册邮箱</th>
                        <th data-field="wedCode">注册日期</th>
                        <th data-field="wedCode">联系人</th>
                        <th data-field="wedCode">办公电话</th>
                        <th data-field="wedCode">移动电话</th>
                        <th data-field="wedCode">通讯地址</th>
                        <th data-field="look" data-formatter="LookUp" data-events="operateEvents">系统模块</th>
                        <th data-field="action" data-formatter="disableFormatter" data-events="operateEvents">账户状态
                        </th>
                    </tr>
                    </thead>
                </table>
            </div>
<%--查看的内容--%>
            <div id="userLookUpWrap" style="display: none">
                <div class="lookUpContent">
                    <table id="userLookUpTable">
                        <thead>
                        <tr>
                            <th data-field="systemModal">系统模块</th>
                            <th data-field="userProperty">用户属性</th>
                            <th data-field="openStates">开通状态</th>
                            <th data-field="startDate">开始日期</th>
                            <th data-field="endDate">结束日期</th>
                            <th data-field="authorityAssignment">权限分配</th>
                            <th data-field="relatedAccount">关联账户</th>
                            <th data-field="relatedAccountPwd">关联账户密码</th>
                            <th data-field="APICode">API代理</th>
                            <th data-field="URLAddress">URL地址</th>
                            <th data-field="APICode">统计代码</th>
                        </tr>
                        </thead>
                    </table>
                    <div class="lookUpConfirmBtn fr">
                        <span class="adminButton" onclick="cancelLookUp()">确定</span>
                        <span  class="adminButton" onclick="cancelLookUp()" style="background-color: #e0e0e0;color: #000">取消</span>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="home/homemodel.jsp"/>
<script type="text/javascript" src="/public/js/index/index.js"></script>
</body>
</html>
