<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Md2Html</title>

 <!-- preview的css -->
 <link rel="stylesheet" href="editormd/css/style.css" />
 <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/editormd/css/editormd.preview.min.css" />

 <!-- 引入editormd相关 -->
 <script src="${pageContext.request.contextPath}/pages/editormd/jquery-3.4.1.min.js"></script>

 <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/editormd/css/editormd.min.css" />
 <script src="${pageContext.request.contextPath}/pages/editormd/lib/marked.min.js"></script>
 <script src="${pageContext.request.contextPath}/pages/editormd/lib/prettify.min.js"></script>
 <script src="${pageContext.request.contextPath}/pages/editormd/lib/raphael.min.js"></script>
 <script src="${pageContext.request.contextPath}/pages/editormd/lib/underscore.min.js"></script>
 <script src="${pageContext.request.contextPath}/pages/editormd/lib/sequence-diagram.min.js"></script>
 <script src="${pageContext.request.contextPath}/pages/editormd/lib/flowchart.min.js"></script>
 <script src="${pageContext.request.contextPath}/pages/editormd/lib/jquery.flowchart.min.js"></script>

 <script src="${pageContext.request.contextPath}/pages/editormd/editormd.min.js"></script>
</head>


<body style="background:#eee;">
 <div id="mdView"  style="background:#eee;">
  <textarea id="article_content"  >${article.content}</textarea>
 </div>  
<br />
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