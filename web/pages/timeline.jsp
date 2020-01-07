<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>


<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>时间轴 | MyBlog</title>

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

	<!--引入当前页面js及css-->
	<script src="/MyBlog/js/common.js"></script>
	<script src="/MyBlog/js/article.js"></script>
	<link rel="stylesheet" href="/MyBlog/css/buttons.css">
	<link rel="stylesheet" href="/MyBlog/css/common.css">
	<link rel="stylesheet" href="/MyBlog/css/timeline.css">
</head>
<body>
<div class="container">
	<div class="row center-block">
		<div class="col-md-3">
			<!--顶部的头像即信息-->
			<div class="info">
				<div id="title" class="text-center">
					<h2><a href="/MyBlog/pages/main.jsp">MyBlog</a></h2>
					<h5 class="text-muted">Winner Winner Chicken Dinner!</h5>
				</div>
				<div class="text-center" id="person_info">
					<img class="img-circle center-block" src="/MyBlog/images/header.jpg" height="130" width="130"
						 alt="丢失了我的头像?" class="img-circle">
					<h3>name</h3>
				</div>
				<div class="row text-center blog-message">
					<a href="/MyBlog/pages/main.jsp">
						<div class="col-md-4 blog-mes-div">
							<strong id="article-count">11</strong>
							<h5 class="text-muted">日志</h5>
						</div>
					</a>
					<a href="/MyBlog/servlet/SortServlet?get=all">
						<div class="col-md-4 blog-mes-div">
							<strong id="sort-count">10</strong>
							<h5 class="text-muted">分类</h5>
						</div>
					</a>
					<a href="/MyBlog/servlet/TagServlet?get=all">
						<div class="col-md-4 blog-mes-div">
							<strong id="tag-count">12</strong>
							<h5 class="text-muted">标签</h5>
						</div>
					</a>
				</div>
			</div>
			<!--导航-->
			<div class="blog-nav">
				<ul class="nav nav-pills nav-stacked text-center">
					<li class="">
						<a href="/MyBlog/pages/main.jsp">
							<span class="glyphicon glyphicon-home"></span>
							&nbsp;&nbsp;首页</a>
					</li>
					<li class="">
						<a href="/MyBlog/servlet/SortServlet?get=all"><span class="glyphicon glyphicon-list"></span>
							&nbsp;&nbsp;分类</a>
					</li>
					<li>
						<a href="/MyBlog/servlet/TagServlet?get=all"><span class="glyphicon glyphicon-tags"></span>
							&nbsp;&nbsp;标签</a>
					</li>
					<li class="active">
						<a href="/MyBlog/servlet/TimeLineServlet"><span class="glyphicon glyphicon-time"></span>
							&nbsp;&nbsp;时间轴</a>
					</li>
					<li>

					</li>
					<li>
						<a href="/MyBlog/pages/about.jsp"><span class="glyphicon glyphicon-info-sign"></span>
							&nbsp;&nbsp;关于</a>
					</li>
				</ul>
			</div>
			<!--阅读排行-->
			<div class="blog-visit">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">阅读排行</h3>
					</div>
					<!-- 这里初始化阅读排行 -->
					<c:forEach var="a"  items="${visit_rank}">
						<a href="/MyBlog/ArticleServlet?id=${a.id}">
							<div class="panel-body">
									${a.title}&nbsp;&nbsp; <span class="c_right">(${a.visit})</span>
							</div>
						</a>
					</c:forEach>
					<!-- 初始化结束 -->
				</div>
			</div><!-- visit-->

		</div>
		<!--博客右边内容-->
		<div class="col-md-8 blog-right">

			<!-- 右边内容 -->
			<div class="right-content">
				<!-- 时间轴 -->
				<div id="timeline">
					<!-- 这里初始化时间轴 -->
					<c:forEach var="axis" varStatus="status" items="${axis_list}">
						<c:choose>
							<c:when test="${axis.id==0}">

								<div class="line">
                <span type="button" class="timeline-year-btn btn"
					  data-container="body" data-toggle="popover" data-placement="right"
					  data-content="<h3> ${axis.year}</h3>">
              </span>
								</div>
							</c:when>
							<c:otherwise>
								<div class="line">
									<div type="button" class="timeline-content-btn btn"
										 data-container="body" data-toggle="show" data-placement="right"
										 data-content="<a href='/servlet/ArticleServlet?id=${axis.id}'>&nbsp;&nbsp;${axis.month}-${axis.day}
                      &nbsp;&nbsp;&nbsp;&nbsp;${axis.title}"></div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<div class="line"></div>
					<div class="line"></div>
					<div class="line last-line"></div>
					<!-- 初始化结束 -->
				</div>
			</div>
		</div>


		<div class="col-md-1">
			<!--返回顶部-->
			<div class="back-to-top">
				<div class="to-top-div"><span class="glyphicon glyphicon-chevron-up"></span></div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	// 时间轴按钮取消点击事件
	$(function () {
		$(".btn").popover({html: true});
		$(".btn").popover('show');
		$('.btn').unbind(); //移除所有
	});

	/**
	 * 返回顶部按钮
	 * @type {jQuery|HTMLElement}
	 */
	var backButton=$('.back-to-top');
	function backToTop() {
		$('html,body').animate({
			scrollTop: 0
		}, 800);
	}
	backButton.on('click', backToTop);

	$(window).on('scroll', function () {/*当滚动条的垂直位置大于浏览器所能看到的页面的那部分的高度时，回到顶部按钮就显示 */
		if ($(window).scrollTop() > $(window).height())
			backButton.fadeIn();
		else
			backButton.fadeOut();
	});
	$(window).trigger('scroll');/*触发滚动事件，避免刷新的时候显示回到顶部按钮*/

</script>
</body>
</html>