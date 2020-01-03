<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录 | MyBlog</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/MyBlog/css/public.css"/>
    <link rel="stylesheet" href="/MyBlog/css/login.css"/>
</head>
<body>
<div><img id="bg-img" src="https://api.xygeng.cn/bing/1366.php" alt=""></div>
<div id="black"></div>
<div class="Snow">
    <canvas id="Snow"></canvas>
</div>
<script src="/MyBlog/js/xue.js" defer></script>
<!--PageEndHtml Block End-->
<div class="container">
    <form
            action="/MyBlog/LoginServlet"
            method="post">

        <h1>
            <a href="/MyBlog/index.jsp">MyBlog</a>
        </h1>

        <label
                for="input"
                class="sr-only">用户名</label>

        <input
                type="text"
                id="input"
                class="form-control"
                placeholder="用户名"
                name="username"
                required
                onfocus="bgonfocus()"
                onblur="bgblur()">

        <label
                for="inputPassword"
                class="sr-only">密码</label>

        <input
                type="password"
                id="inputPassword"
                class="form-control"
                placeholder="密码"
                name="password"
                required
                onfocus="bgonfocus()"
                onblur="bgblur()">

        <button
                class="btn btn-lg btn-primary btn-block"
                type="submit"
                id="submit">登录
        </button>

        <a
                class="visitor"
                href="/MyBlog/index.jsp">访客登录</a>
    </form>
</div>
</body>
<script src="/MyBlog/js/login.js"></script>
</html>