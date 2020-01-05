<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>首页</title>

	<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

	<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

	<!--引入jQuery-->
	<!-- <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script> -->
	<script src="/MyBlog/js/jquery-3.4.1.min.js"></script>

	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

	<!-- preview的css -->
	<link rel="stylesheet" href="/MyBlog/pages/editormd/css/editormd.preview.min.css" />


	<!-- 引入editormd相关 -->
	<script src="/MyBlog/pages/editormd/jquery-3.4.1.min.js"></script>

	<link rel="stylesheet" href="/MyBlog/pages/editormd/css/editormd.min.css" />
	<script src="/MyBlog/pages/editormd/lib/marked.min.js"></script>
	<script src="/MyBlog/pages/editormd/lib/prettify.min.js"></script>
	<script src="/MyBlog/pages/editormd/lib/raphael.min.js"></script>
	<script src="/MyBlog/pages/editormd/lib/underscore.min.js"></script>
	<script src="/MyBlog/pages/editormd/lib/sequence-diagram.min.js"></script>
	<script src="/MyBlog/pages/editormd/lib/flowchart.min.js"></script>
	<script src="/MyBlog/pages/editormd/lib/jquery.flowchart.min.js"></script>

	<script src="/MyBlog/pages/editormd/editormd.min.js"></script>

	<style>
		body {
			background: #F5F7F9;
		}
		.info {
			background: #fff;
			padding-top: 5%;
			border-radius: 8px;
		}
		.blog-message {
			margin-left: 10%;
			margin-right: 10%;
		}
		.blog-mes-div {
			padding: 0;
		}
		.blog-nav {
			background: #fff;
			margin-top: 10%;
			border-radius: 8px;
		}
		.blog-visit {
			margin-top: 10%;
		}
		.blog-right {
			background: #fff;
		}
		#mdView {
			border-radius: 10px;
			margin-bottom: 15px;
		}
		.title-msg {
			margin-left: 10px;
			margin-right: 10px;
		}
		.title-msg-div {
			margin-top: 3%;
			margin-bottom: 3%;
		}
		.container {
			margin-top: 30px;
		}
	</style>
</head>
<body>
<div class="container">
	<div class="row center-block">
		<div class="col-md-3">
			<div class="info">
				<div id="title" class="text-center">
					<h2><a href="/MyBlog/login.html">MyBlog</a></h2>
					<h5 class="text-muted">Winner Winner Chicken Dinner!</h5>
				</div>
				<div class="text-center" id="person_info">
					<img class="img-circle center-block" src="/MyBlog/images/header.jpg" height="130" width="130"
						 alt="丢失了我的头像?" class="img-circle">
					<h3>name</h3>
				</div>
				<div class="row text-center blog-message">
					<a href="">
						<div class="col-md-4 blog-mes-div">
							<strong>11</strong>
							<h5 class="text-muted">日志</h5>
						</div>
					</a>
					<a href="">
						<div class="col-md-4 blog-mes-div">
							<strong>10</strong>
							<h5 class="text-muted">分类</h5>
						</div>
					</a>
					<a href="">
						<div class="col-md-4 blog-mes-div">
							<strong>12</strong>
							<h5 class="text-muted">标签</h5>
						</div>
					</a>
				</div>
			</div>
			<div class="blog-nav">
				<ul class="nav nav-pills nav-stacked text-center">
					<li class="">
						<a href="/MyBlog/index.jsp">
							<span class="glyphicon glyphicon-home"></span>
							&nbsp;&nbsp;首页</a>
					</li>
					<li>
						<a href="../servlet/SortServlet?get=all"><span class="glyphicon glyphicon-list"></span>
							&nbsp;&nbsp;分类</a>
					</li>
					<li>
						<a href="../servlet/TagServlet?get=all"><span class="glyphicon glyphicon-tags"></span>
							&nbsp;&nbsp;标签</a>
					</li>
					<li>
						<a href="../servlet/TimeLineServlet"><span class="glyphicon glyphicon-time"></span>
							&nbsp;&nbsp;时间轴</a>
					</li>
					<li>

					</li>
					<li>
						<a href="/pages/about.html"><span class="glyphicon glyphicon-user"></span>
							&nbsp;&nbsp;关于</a>
					</li>
				</ul>
			</div>
			<div class="blog-visit">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">阅读排行</h3>
					</div>
					<!-- 这里初始化阅读排行 -->
					<c:forEach var="a"  items="${visit_rank}">
						<a href="/Blog/ArticleServlet?id=${a.id}">
							<div class="panel-body">
									${a.title}&nbsp;&nbsp; <span class="c_right">(${a.visit})</span>
							</div>
						</a>
					</c:forEach>
					<!-- 初始化结束 -->
				</div>
			</div><!-- visit-->

		</div>
		<div class="col-md-8 blog-right">
			<!-- 文章内容 -->
			<div >
				<!-- 文章标题信息部分 -->
				<div id="artitle-title" class="text-center">
					<h2>${article.title}</h2>
					<div class="text-muted title-msg-div">
						<span class="title-msg">
							<span class="glyphicon glyphicon-calendar"></span>发表于&nbsp;<span>${article.time}</span>
						</span>
						<span class="title-msg">
							<span class="glyphicon glyphicon-list"></span>
								&nbsp;分类&nbsp;<span>${article.sort}</span>
						</span>
						<span class="title-msg">
							<span class="glyphicon glyphicon-user"></span>
                                作者<span>${article.author}</span>
						</span>
					</div>
				</div>
				<div id="mdView"  style="background:#eee;">
                        <textarea id="article_content"  >
${article.content}
						</textarea>
				</div>
			</div>

			<!-- 评论 -->
			<div class="comment">

				<div class="pull-right">
					<a href="#comment"><span class="glyphicon glyphicon-pencil">&nbsp;写评论....</span></a>
				</div>

				<!-- 加载文章评论 -->
				<c:if test="${comment!=null}">
					<c:forEach var="comm" varStatus="status" items="${comment}">

						<div class="row" >
							<div class="f_div">
								<img src="/Blog/img/comment.jpg" height="50" width="50"  class="img-circle"/>
								&nbsp;&nbsp;
								<span style="color: #428bca"> ${comm.nickname}</span>
								<span>&nbsp;&nbsp;${comm.time}</span>
							</div>
							<div  id="c_content" class="c_left">
								<pre>${comm.content }</pre>
							</div>
							<div class="r_div">
								<a><span class="glyphicon glyphicon-thumbs-up"  onclick="star(this,${comm.id})">${comm.star}</span></a>
								&nbsp;
								<a><span class="glyphicon glyphicon-thumbs-down" onclick="diss(this,${comm.id})">${comm.diss}</span></a>
								&nbsp;
								<!-- admin here -->
								<c:if test="${sessionScope.user!=null}">
									<span class="btn btn-default" style="color:red;" onclick="deletecm(this,${comm.id})">删除</span>
									&nbsp;
								</c:if>
							</div>
							<div class="line"></div>
						</div>

					</c:forEach>

				</c:if>
			</div>
			<!-- 这里可以扩展子评论 -->


			<!-- 写评论 -->
			<div id="comment">

				<form action="../servlet/NewCommentServlet?id=${article.id}" method="post">
					<input  style="width:30%" class="form-control" type="text" name="w_nickname" value="热心网友"  >
					<br/>
					<textarea style="resize:none; width:100%; height:180px;" name="w_content"></textarea>
					<br/>
					<br/>
					<input  class="btn btn-default"  type="submit"   value="评论" onclick="onclick"/>
					<br/>
				</form>
			</div>
			<!--  -->
			<div class="line"></div>

			<div id="footer">
				<a href="../pages/main.jsp">MyBlog首页&nbsp;&nbsp;</a>|
				<a href="#">&nbsp;&nbsp;返回顶部</a>
			</div>
			<!-- footer -->

		</div>

	</div>
</div>
<script type="text/javascript">
    $(function mdToHtml() {
        //获取要显示的内容
        var content = $("#article_content").text();
        editormd.markdownToHTML("mdView", {
            htmlDecode : "style,script,iframe",
            emoji : true,
            taskList : true,
            tex : true, // 默认不解析
            flowChart : true, // 默认不解析
            sequenceDiagram : true, // 默认不解析
        });
    });
</script>
</body>
</html>