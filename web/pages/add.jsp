<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>新的文章 | MyBlog</title>
	<style>
		body {
			margin: 0;
			border: 0;
			padding: 0;
			background: #FFFFDF;
		}
		.title {
			font-size: 20px;
			margin: 10px;
			width: 10%;
			color: #333;
		}
		#title-input {
			width: 90%;
			height: 20px;
			border: 1px solid #999;
			border-radius: 3px;
			padding: 8px;
			color:#555;
			outline: 0;
			font-size: 15px;
		}
		.article-head {
			font-size: 15px;
			margin: 5px;
			width: 7%;
		}
		.message-input {
			width: 28%;
			height: 15px;
			padding: 5px;
			border: 1px solid #999;
			border-radius: 3px;
			color:#555;
			outline: 0;
			font-size: 15px;
		}
		#msg-div {
			margin: 10px;
		}
		#author {
			width: 24%;
		}
		#title-div {
			float: left;
			width: 80%;
		}
		#send-div {
			float: left;
			width: 20%;
		}
		#editorView {
			width: 90%;
			margin: 0 auto;
			padding: 2%;
		}
		#editormd {
			width: 90%;
			float: left;
		}
		#help {
			width: 20%;
			float: left;
			overflow: auto;
			height: 720px;
		}
	</style>
	<link rel="stylesheet" href="./editormd/css/editormd.min.css" />
	<script src="./editormd/jquery-3.4.1.min.js"></script>
	<script src="./editormd/lib/marked.min.js"></script>
	<script src="./editormd/lib/prettify.min.js"></script>

	<script src="./editormd/lib/raphael.min.js"></script>
	<script src="./editormd/lib/underscore.min.js"></script>
	<script src="./editormd/lib/sequence-diagram.min.js"></script>
	<script src="./editormd/lib/flowchart.min.js"></script>
	<script src="./editormd/lib/jquery.flowchart.min.js"></script>

	<script src="./editormd/editormd.min.js"></script>
</head>
<body>
<form action="">
	<div id="head-div">
		<div id="title-div">
			<div>
				<input type="hidden" name="article_id">
				<span class="title">标题</span>
				<input id="title-input" type="text" name="title" value="输入文章标题" onfocus="if (value=='输入文章标题') {value=''}" onblur="if (value=='') {value='输入文章标题'}">
			</div>
			<div id="msg-div">
				<input type="hidden" name="time" value="">
				<span class="article-head">作者：</span><input class="message-input" type="text" id="author" disabled value="作者"></input>
				<span class="article-head">分类</span><input class="message-input" type="text" name="sort">
				<span class="article-head">标签</span><input class="message-input" type="text" name="tags">
			</div>
		</div>
		<div id="send-div"><input type="submit" value="发布文章"></div>
	</div>
	<div id="editorView">
		<div id="editormd">
			<textarea style="display:none;"></textarea>
		</div>
		<div id="help">
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
</form>
</body>
<script type="text/javascript">
    var height = window.innerHeight;
    var head = document.getElementById("head-div").offsetHeight;//获取元素实际高度
    document.getElementsByTagName("body")[0].style.height=height;
    document.getElementById("help").style.height=height - head;
    var testEditor;
    // var jQuery = Zepto;
    $(function() {
        testEditor = editormd("editormd", {
            width  : "75%",
            height : height - head,
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

    testEditormdView = editormd.markdownToHTML("help", {
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