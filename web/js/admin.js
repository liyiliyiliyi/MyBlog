/* 
* 获取ajax处理对象		
 * @returns {xmlhttp}
 */

function getXHR(){	
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlhttp;	
}
/*
 *发送给服务器
 */
function sendURL(url){	
	// 获取ajax
	var xmlhttp = getXHR();		
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			//这里可以测试
		}
	}
	xmlhttp.open("get", url, true);
	xmlhttp.send();		
}
/**
 * 在某个分类中找到指定的一个input
 * @param class_name
 * @param name
 * @returns
 */
function findInputInClass(class_name , name){
	//获取class_name类的 input 所有输入框数组
	var inputs = document.getElementsByClassName(class_name);
	var input;		
	//找到值等于 name 的输入框
	for(var i=0 ;i<inputs.length;i++){		
		if(inputs[i].value == name){
			input = inputs[i];
			break;
		}			
	}
	return input;	
}

/**
 * 删除文章
 * @param article_id
 */
function delete_article(hod , article_id){
	//remove 视图
	var recorder = hod.parentNode.parentNode.parentNode;	
	var recorder_parent = recorder.parentNode;
	recorder_parent.removeChild(recorder);
	//send
	var url = "/MyBlog/servlet/AdminDataServlet?op=delete_article"+"&article_id="+article_id;
	sendURL(url);

}