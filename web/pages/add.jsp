<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>新的文章 | MyBlog</title>

	<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

	<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

	<!--引入jQuery-->
	<script src="./editormd/jquery-3.4.1.min.js"></script>

	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

	<link rel="stylesheet" href="../css/add.css" />

	<link rel="stylesheet" href="./editormd/css/editormd.min.css" />

	<script src="./editormd/lib/marked.min.js"></script>
	<script src="./editormd/lib/prettify.min.js"></script>

	<script src="./editormd/lib/raphael.min.js"></script>
	<script src="./editormd/lib/underscore.min.js"></script>
	<script src="./editormd/lib/sequence-diagram.min.js"></script>
	<script src="./editormd/lib/flowchart.min.js"></script>
	<script src="./editormd/lib/jquery.flowchart.min.js"></script>

	<script src="./editormd/editormd.min.js"></script>
</head>
<body id="body">
<div class="container-fluid">
	<form action="../servlet/NewArticleServlet" method="post">
		<div id="head-div" class="row">
			<div id="title-div" class="col-md-9">
				<div class="input-group input-group-lg">
					<input type="hidden" name="article_id">
					<span class="title input-group-addon">标题</span>
					<input class="form-control" placeholder="输入文章标题" id="title-input" type="text" name="title" value="输入文章标题" onfocus="if (value=='输入文章标题') {value=''}" onblur="if (value=='') {value='输入文章标题'}">
				</div>
				<div id="msg-div" class="row">
					<input type="hidden" name="time" value="">
					<div class="col-md-3 input-group message-group">
						<span class="input-group-addon">作者</span><input class="message-input form-control" placeholder="作者" type="text" id="author" disabled value="作者"></input>
					</div>
					<div class="col-md-3 input-group message-group">
						<span class="input-group-addon">分类</span><input class="message-input form-control" placeholder="请输入分类" type="text" name="sort">
					</div>
					<div class="col-md-3 input-group message-group">
						<span class="input-group-addon">标签</span><input class="message-input form-control" placeholder="请输入标签" type="text" name="tags">
					</div>
				</div>
			</div>
			<div id="send-div" class="col-md-3"><input class="btn btn-success btn-lg" type="submit" value="发布文章"></div>
		</div>
		<div id="editorView">
			<div id="editormd">
				<textarea style="display:none;"></textarea>
			</div>

			<div id="help">
				<div id="help-title"><h3>帮助文档</h3></div>
				<div id="help-content">
					<textarea style="display:none;" id="help-md">
## 快捷键

撤销：<kbd>Ctrl/Command</kbd> + <kbd>Z</kbd>
重做：<kbd>Ctrl/Command</kbd> + <kbd>Y</kbd>
加粗：<kbd>Ctrl/Command</kbd> + <kbd>B</kbd>
斜体：<kbd>Ctrl/Command</kbd> + <kbd>I</kbd>
标题：<kbd>Ctrl/Command</kbd> + <kbd>1</kbd> ~ <kbd>6</kbd>
无序列表：<kbd>Ctrl/Command</kbd> + <kbd>U</kbd>
有序列表：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>O</kbd>
检查列表：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>C</kbd>
插入代码：<kbd>Alt</kbd> + <kbd>Shift</kbd> + <kbd>C</kbd>
插入链接：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>L</kbd>
插入图片：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>I</kbd>
查找：<kbd>Ctrl/Command</kbd> + <kbd>F</kbd>
替换：<kbd>Ctrl</kbd> + <kbd>Shift</kbd> + <kbd>F</kbd>
## 标题
# # 一级标题
## ## 二级标题
### ### 三级标题
#### #### 四级标题
##### ##### 五级标题
###### ###### 六级标题
## 文本样式


					</textarea>
				</div>
			</div>
		</div>
	</form>
</div>
</body>
<script type="text/javascript">
    var testEditor;
    // var jQuery = Zepto;
    $(function() {
        testEditor = editormd("editormd", {
            width  : "75%",
            // height : height - head,
            path   : './editormd/lib/',
            codeFold : true,
            searchReplace : true,
            saveHTMLToTextarea : true,    // 保存 HTML 到 Textarea
            htmlDecode : "style,script,iframe|on*", // 开启 HTML 标签解析，为了安全性，默认不开启
            emoji : true,
            taskList : true,
            tocm: true,
            tex : true,
            flowChart : true,
            sequenceDiagram : true,
            imageUpload : true,
            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL : "/Blog/UploadPic",
            //后台只需要返回一个 JSON 数据
            onload : function() {
                //console.log("onload =>", this, this.id, this.settings);
            }
        });
        testEditor.setToolbarAutoFixed(false);//工具栏自动固定定位的开启与禁用
    });
    var testEditormdView;

    var md = document.getElementById("help-md").innerText;

    testEditormdView = editormd.markdownToHTML("help-content", {
        markdown        : md ,//+ "\r\n" + $("#append-test").text(),
        //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
        htmlDecode      : "style,script,iframe",  // you can filter tags decode
        //toc             : false,
        tocm            : true,    // Using [TOCM]
        //tocContainer    : "#custom-toc-container", // 自定义 ToC 容器层
        //gfm             : false,
        //tocDropdown     : true,
        // markdownSourceCode : true, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
        emoji           : true,
        taskList        : true,
        tex             : true,  // 默认不解析
        flowChart       : true,  // 默认不解析
        sequenceDiagram : true,  // 默认不解析
    });
</script>
</html>