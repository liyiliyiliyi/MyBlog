<%@ page contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>关于我们</title>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!--引入jQuery-->
    <!-- <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script> -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <!-- preview的css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/editormd/css/editormd.preview.min.css" />


    <!-- 引入editormd相关 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/editormd/css/editormd.min.css" />
    <script src="${pageContext.request.contextPath}/pages/editormd/lib/marked.min.js"></script>
    <script src="${pageContext.request.contextPath}/pages/editormd/lib/prettify.min.js"></script>
    <script src="${pageContext.request.contextPath}/pages/editormd/lib/raphael.min.js"></script>
    <script src="${pageContext.request.contextPath}/pages/editormd/lib/underscore.min.js"></script>
    <script src="${pageContext.request.contextPath}/pages/editormd/lib/sequence-diagram.min.js"></script>
    <script src="${pageContext.request.contextPath}/pages/editormd/lib/flowchart.min.js"></script>
    <script src="${pageContext.request.contextPath}/pages/editormd/lib/jquery.flowchart.min.js"></script>

    <script src="${pageContext.request.contextPath}/pages/editormd/editormd.min.js"></script>

    <!--引入当前页面js及css-->
    <script src="${pageContext.request.contextPath}/js/common.js"></script>
    <script src="${pageContext.request.contextPath}/js/article.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/article.css">
</head>
<body>
<div class="container">
    <div class="row center-block">
        <div class="col-md-3">
            <!--顶部的头像即信息-->
            <div class="info">
                <div id="title" class="text-center">
                    <h2><a href="${pageContext.request.contextPath}/pages/main.jsp">MyBlog</a></h2>
                    <h5 class="text-muted">Winner Winner Chicken Dinner!</h5>
                </div>
                <div class="text-center" id="person_info">
                    <img class="img-circle center-block" src="${pageContext.request.contextPath}/images/header.jpg" height="130" width="130"
                         alt="丢失了我的头像?">
                    <h3>name</h3>
                </div>
                <div class="row text-center blog-message">
                    <a href="${pageContext.request.contextPath}/pages/main.jsp">
                        <div class="col-md-4 blog-mes-div">
                            <strong id="article-count">11</strong>
                            <h5 class="text-muted">日志</h5>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/servlet/SortServlet?get=all">
                        <div class="col-md-4 blog-mes-div">
                            <strong id="sort-count">10</strong>
                            <h5 class="text-muted">分类</h5>
                        </div>
                    </a>
                    <a href="${pageContext.request.contextPath}/servlet/TagServlet?get=all">
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
                        <a href="${pageContext.request.contextPath}/pages/main.jsp">
                            <span class="glyphicon glyphicon-home"></span>
                            &nbsp;&nbsp;首页</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/servlet/SortServlet?get=all"><span class="glyphicon glyphicon-list"></span>
                            &nbsp;&nbsp;分类</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/servlet/TagServlet?get=all"><span class="glyphicon glyphicon-tags"></span>
                            &nbsp;&nbsp;标签</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/servlet/TimeLineServlet"><span class="glyphicon glyphicon-time"></span>
                            &nbsp;&nbsp;时间轴</a>
                    </li>
                    <li>

                    </li>
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/pages/about.jsp"><span class="glyphicon glyphicon-info-sign"></span>
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
                        <a href="${pageContext.request.contextPath}/ArticleServlet?id=${a.id}">
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
                    <h2>关于我们</h2>
                    <div class="text-muted title-msg-div">
						<span class="title-msg">
							<span class="glyphicon glyphicon-calendar"></span>发表于&nbsp;<span>2020-01-07</span>
						</span>
                        <span class="title-msg">
							<span class="glyphicon glyphicon-list"></span>
								&nbsp;分类&nbsp;<span>关于我们</span>
						</span>
                        <span class="title-msg">
							<span class="glyphicon glyphicon-user"></span>
                                作者<span>甘雨柔</span>
						</span>
                    </div>
                </div>
                <div id="mdView"  style="background:#eee;">
                        <textarea id="article_content"  >
# ABOUT US
### 主要功能：

        具有`注册`和`登录`的功能，没有注册可以直接通过网址访问，登录的话可以看到有关自己的信息（登录界面的图片可以每天更新呦！）
      主页可以浏览数据库中的全部文章，点击`阅读全文`则可以看到该文章的详细内容，如果觉得文章质量好，可以给它`点赞`哦。里面也有该文章的全部评论，评论可以点赞也可以踩哈。
      左侧的`阅读排行`是根据点击量进行排行的，点击最上侧的写文章可以`编写自己的博客`，点击`管理更多`可以`编辑`或者`删除`自己的文章（当然游客没有这个功能哦）
      左侧的`分类`是文章可以按分类进行归纳；`标签`是文章可以按标签进行归纳；`时间轴`则是文章按照时间进行显示。
### 不足：
      当时设置数据库**没有将用户表和文章表进行关联**。所以博客没有多用户的功能，仅仅实现了单用户的功能。但是外面的访客可以访问，没有做拦截功能
      界面可能过于简单，但是我们觉得好看。因为是自己美化的。

						</textarea>
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
<script src="${pageContext.request.contextPath}/js/background.js"></script>
</html>