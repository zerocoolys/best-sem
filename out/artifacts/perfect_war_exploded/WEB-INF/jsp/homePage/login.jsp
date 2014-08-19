<%--
  Created by IntelliJ IDEA.
  User: baizz
  Date: 2014-6-26
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>Login Page</title>
    <meta name="description" content="User login page"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- basic styles -->
    <link href="public/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="public/css/bootstrap-responsive.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="public/css/font-awesome.min.css"/>
    <!--[if IE 7]>
    <link rel="stylesheet" href="public/css/font-awesome-ie7.min.css"/>
    <![endif]-->
    <!-- page specific plugin styles -->

    <!-- ace styles -->
    <link rel="stylesheet" href="public/css/ace.min.css"/>
    <link rel="stylesheet" href="public/css/ace-responsive.min.css"/>
    <!--[if lt IE 9]>
    <link rel="stylesheet" href="public/css/ace-ie.min.css"/>
    <![endif]-->
</head>
<body class="login-layout">

<div class="container-fluid" id="main-container">
    <div id="main-content">
        <div class="row-fluid">
            <div class="span12">

                <div class="login-container">
                    <div class="row-fluid">
                        <div class="center">
                            <h1><i class="icon-leaf green"></i> <span class="red">百度</span> <span
                                    class="white">推广</span></h1>
                            <h4 class="blue">&copy; Perfect</h4>
                        </div>
                    </div>
                    <div class="space-6"></div>
                    <div class="row-fluid">
                        <div class="position-relative">
                            <div id="login-box" class="visible widget-box no-border">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <h4 class="header lighter bigger"><i class="icon-coffee green"></i> Please Enter
                                            Your Information</h4>

                                        <div class="space-6"></div>

                                        <form action="../j_spring_security_check" method="post">
                                            <fieldset>
                                                <label>
						<span class="block input-icon input-icon-right">
							<input type="text" id="j_username" name="j_username" class="span10" style="margin-left: 9%"
                                   placeholder="用户名"/>
							<i class="icon-user"></i>
						</span>
                                                </label>
                                                <label>
						<span class="block input-icon input-icon-right">
							<input type="password" id="j_password" name="j_password" class="span10"
                                   style="margin-left: 9%" placeholder="密码"/>
							<i class="icon-lock"></i>
						</span>
                                                </label>

                                                <div class="space"></div>
                                                <div id="login-error" style="color: #f00">${error}</div>
                                                <div class="space"></div>
                                                <div class="row-fluid">
                                                    <label class="span8">
                                                        <input type="checkbox"/><span class="lbl"
                                                                                      style="margin-left: 20%"> 记住密码</span>
                                                    </label>
                                                    <input type="submit" class="btn btn-primary" value="登录"/>
                                                </div>

                                            </fieldset>
                                        </form>
                                    </div>
                                    <!--/widget-main-->
                                </div>
                                <!--/widget-body-->
                            </div>
                            <!--/login-box-->


                            <div id="forgot-box" class="widget-box no-border">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <h4 class="header red lighter bigger"><i class="icon-key"></i> Retrieve Password
                                        </h4>

                                        <div class="space-6"></div>

                                        <p>
                                            Enter your email and to receive instructions
                                        </p>

                                        <form>
                                            <fieldset>
                                                <label>
						<span class="block input-icon input-icon-right">
							<input type="email" class="span12" placeholder="Email"/>
							<i class="icon-envelope"></i>
						</span>
                                                </label>

                                                <div class="row-fluid">
                                                    <button onclick="return false;"
                                                            class="span5 offset7 btn btn-small btn-danger"><i
                                                            class="icon-lightbulb"></i> Send Me!
                                                    </button>
                                                </div>

                                            </fieldset>
                                        </form>
                                    </div>
                                    <!--/widget-main-->

                                    <div class="toolbar center">
                                        <a href="#" onclick="show_box('login-box'); return false;"
                                           class="back-to-login-link">Back to login <i class="icon-arrow-right"></i></a>
                                    </div>
                                </div>
                                <!--/widget-body-->
                            </div>
                            <!--/forgot-box-->


                            <div id="signup-box" class="widget-box no-border">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <h4 class="header green lighter bigger"><i class="icon-group blue"></i> New User
                                            Registration</h4>

                                        <div class="space-6"></div>
                                        <p>
                                            Enter your details to begin:
                                        </p>

                                        <form>
                                            <fieldset>
                                                <label>
						<span class="block input-icon input-icon-right">
							<input type="email" class="span12" placeholder="Email"/>
							<i class="icon-envelope"></i>
						</span>
                                                </label>
                                                <label>
						<span class="block input-icon input-icon-right">
							<input type="text" class="span12" placeholder="Username"/>
							<i class="icon-user"></i>
						</span>
                                                </label>
                                                <label>
						<span class="block input-icon input-icon-right">
							<input type="password" class="span12" placeholder="Password"/>
							<i class="icon-lock"></i>
						</span>
                                                </label>
                                                <label>
						<span class="block input-icon input-icon-right">
							<input type="password" class="span12" placeholder="Repeat password"/>
							<i class="icon-retweet"></i>
						</span>
                                                </label>

                                                <label>
                                                    <input type="checkbox"><span class="lbl"> I accept the <a href="#">User
                                                    Agreement</a></span>
                                                </label>

                                                <div class="space-24"></div>

                                                <div class="row-fluid">
                                                    <button type="reset" class="span6 btn btn-small"><i
                                                            class="icon-refresh"></i> Reset
                                                    </button>
                                                    <button onclick="return false;"
                                                            class="span6 btn btn-small btn-success">Register <i
                                                            class="icon-arrow-right icon-on-right"></i></button>
                                                </div>

                                            </fieldset>
                                        </form>
                                    </div>

                                    <div class="toolbar center">
                                        <a href="#" onclick="show_box('login-box'); return false;"
                                           class="back-to-login-link"><i class="icon-arrow-left"></i> Back to login</a>
                                    </div>
                                </div>
                                <!--/widget-body-->
                            </div>
                            <!--/signup-box-->


                        </div>
                        <!--/position-relative-->

                    </div>
                </div>
            </div>
            <!--/span-->
        </div>
        <!--/row-->
    </div>
</div>
<!--/.fluid-container-->
<!-- basic scripts -->
<script type="text/javascript" src="${pageContext.request.contextPath}/public/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/public/js/bootstrap.min.js"></script>
<script type="text/javascript">
    window.jQuery || document.write("<script src='public/js/jquery-1.11.1.min.js'>\x3C/script>");
</script>
<!-- page specific plugin scripts -->

<!-- inline scripts related to this page -->

<script type="text/javascript">

    function show_box(id) {
        $('.widget-box.visible').removeClass('visible');
        $('#' + id).addClass('visible');
    }
</script>
</body>
</html>
