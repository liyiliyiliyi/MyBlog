<%@ page import="model.Article" %>
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
	<link rel="stylesheet" href="/MyBlog/pages/editormd/css/editormd.min.css" />
	<script src="/MyBlog/pages/editormd/lib/marked.min.js"></script>
	<script src="/MyBlog/pages/editormd/lib/prettify.min.js"></script>
	<script src="/MyBlog/pages/editormd/lib/raphael.min.js"></script>
	<script src="/MyBlog/pages/editormd/lib/underscore.min.js"></script>
	<script src="/MyBlog/pages/editormd/lib/sequence-diagram.min.js"></script>
	<script src="/MyBlog/pages/editormd/lib/flowchart.min.js"></script>
	<script src="/MyBlog/pages/editormd/lib/jquery.flowchart.min.js"></script>

	<script src="/MyBlog/pages/editormd/editormd.min.js"></script>

	<!--引入当前页面js及css-->
	<script src="/MyBlog/js/common.js"></script>
	<script src="/MyBlog/js/article.js"></script>
	<link rel="stylesheet" href="/MyBlog/css/common.css">
	<link rel="stylesheet" href="/MyBlog/css/article.css">
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
					<li>
						<a href="/MyBlog/servlet/SortServlet?get=all"><span class="glyphicon glyphicon-list"></span>
							&nbsp;&nbsp;分类</a>
					</li>
					<li>
						<a href="/MyBlog/servlet/TagServlet?get=all"><span class="glyphicon glyphicon-tags"></span>
							&nbsp;&nbsp;标签</a>
					</li>
					<li>
						<a href="/MyBlog/servlet/TimeLineServlet"><span class="glyphicon glyphicon-time"></span>
							&nbsp;&nbsp;时间轴</a>
					</li>
					<li>

					</li>
					<li>
						<a href="/pages/about.html"><span class="glyphicon glyphicon-info-sign"></span>
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
		<!--博客右边内容-->
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

			<!--文章翻页-->
			<div>
				<div class="pull-left">
					<span class="glyphicon glyphicon-chevron-left"></span>
					<c:choose>
						<c:when test="${article_pre!=null}">
							<a href="/MyBlog/ArticleServlet?id=${article_pre.id}">&nbsp;上一篇:${article_pre.title}</a>
						</c:when>
						<c:otherwise>
							&nbsp;没有更早的文章了
						</c:otherwise>
					</c:choose>

				</div>
				<div class="pull-right">
					<c:choose>
						<c:when test="${article_next!=null}">
							<a href="/MyBlog/ArticleServlet?id=${article_next.id}">下一篇:&nbsp;${article_next.title}</a>
						</c:when>
						<c:otherwise>
							&nbsp;没有更多的文章了
						</c:otherwise>
					</c:choose>
					<span class="glyphicon glyphicon-chevron-right"></span>
				</div>
				<br/>
			</div>

			<!-- 评论 -->
			<div class="comment">

				<div class="pull-right">
					<a href="#comment"><span class="glyphicon glyphicon-pencil">&nbsp;写评论....</span></a>
				</div>

				<!-- 加载文章评论 -->
				<c:if test="${comment!=null}">
					<c:forEach var="comm" varStatus="status" items="${comment}">

						<div class="" >
							<div class="comment-title">
<%--								<img src="/Blog/img/comment.jpg" height="50" width="50"  class="img-circle"/>--%>
								&nbsp;&nbsp;
								<span style="color: #428bca"> ${comm.nickname}</span>
								<span>&nbsp;&nbsp;${comm.time}</span>
							</div>
							<div  id="c_content" class="c_left">
								<pre class="text-muted comment-content">${comm.content }</pre>
							</div>
							<div class="">
								<a class="like-dislike"><span class="glyphicon glyphicon-thumbs-up"  onclick="star(this,${comm.id})"> ${comm.star}</span></a>
								&nbsp;
								<a class="like-dislike"><span class="glyphicon glyphicon-thumbs-down" onclick="diss(this,${comm.id})"> ${comm.diss}</span></a>
								&nbsp;
								<!-- 管理员删除按钮 -->
								<c:if test="${sessionScope.user!=null}">
									<span id="delete-comment" class="glyphicon glyphicon-trash text-primary pull-right" onclick="deletecm(this,${comm.id})"></span>
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

				<form action="/MyBlog/NewCommentServlet?id=${article.id}" method="post">
					<input  style="width:30%" class="form-control" type="text" name="w_nickname" value="热心网友"  >
					<br/>
					<textarea style="resize:none; width:100%; height:180px;" name="w_content"></textarea>
					<br/>
					<br/>
					<input  class="btn btn-info pull-right"  type="submit"   value="评论"/>
					<br/>
				</form>
			</div>
			<!--  -->
		</div>

		<div class="col-md-1">
			<!--点赞-->
			<div class="like-div">
				<svg class="like-svg"  style="fill:#969696; width: 20px;height: 20px;" onclick="love_article(${article.id})" id="ic-like" viewBox="0 0 1084 1024"><path d="M728.064 343.943529c-17.648941-2.891294-23.552-20.239059-26.503529-28.912941V104.026353C701.560471 46.200471 654.396235 0 595.425882 0c-53.007059 0-97.28 40.478118-106.134588 89.569882-29.997176 184.862118-138.541176 255.457882-217.630118 280.937412a26.142118 26.142118 0 0 0-18.130823 24.877177v560.067764c0 19.817412 16.022588 35.84 35.84 35.84h535.973647c56.018824-11.565176 94.328471-31.804235 120.892235-86.738823l120.832-416.105412c23.552-75.173647-14.757647-147.395765-100.231529-144.564706h-238.772706z m-571.813647 31.744H76.619294C35.358118 375.687529 0 410.383059 0 450.861176v462.426353c0 43.369412 32.406588 78.004706 76.619294 78.004706h79.631059c27.708235 0 50.115765-22.407529 50.115765-50.115764V425.863529a50.115765 50.115765 0 0 0-50.115765-50.115764z"></path></svg >
				<div class="like-count text-muted"><span id="like-article">${article.star != null ? article.star : 0}</span>赞</div>
			</div>
			<!--返回顶部-->
			<div class="back-to-top">
				<div class="to-top-div"><span class="glyphicon glyphicon-chevron-up"></span></div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	// 检测是否已经点赞文章
	<%
		int id = ((Article)request.getAttribute("article")).getId();
		System.out.println(id);
		Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
        	System.out.println(cookie.getName());
            if (cookie.getName().equals("star_arti" + id) ) {
                // 已经有了这个cookie
	%>
		document.getElementById("ic-like").style.fill = "#F63D47";
	<%
                break;
            }
        }
	%>

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