function ajaxObject() {
    var xmlHttp;
    try {
        // Firefox, Opera 8.0+, Safari
        xmlHttp = new XMLHttpRequest();
    } catch (e) {
        // Internet Explorer
        try {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
                alert("您的浏览器不支持AJAX！");
                return false;
            }
        }
    }
    return xmlHttp;
}

// ajax post请求：
function ajaxPost ( url , data , fnSucceed , fnFail , fnLoading ) {
    var ajax = ajaxObject();
    ajax.open( "post" , url , true );
    ajax.setRequestHeader( "Content-Type" , "application/x-www-form-urlencoded" );
    ajax.onreadystatechange = function () {
        if( ajax.readyState == 4 ) {
            if( ajax.status == 200 ) {
                 fnSucceed( ajax.responseText );
                // console.log(ajax.responseText);
            }
            else {
                 fnFail( "HTTP请求错误！错误码："+ajax.status );
                // console.log("HTTP请求错误！错误码："+ajax.status);
            }
        } else {
            // fnLoading();
        }
    }
    ajax.send(data);
}


/**
 * 获取日志分类标签数据
 */
function getAST() {
    ajaxPost("/MyBlog/servlet/AskCountsServlet", "", getAST_Success, getAST_Fail);
}
function getAST_Success(res) {
    var tag_count = document.getElementById("tag-count");
    var sort_count = document.getElementById("sort-count");
    var article_count = document.getElementById("article-count");
    console.log(res);
    let temp = res.split("&");
    var tagCounts = temp[0].split("=")[1];
    var articleCounts = temp[1].split("=")[1];
    var articleSortCounts = temp[2].split("=")[1];
    tag_count.innerText = tagCounts;
    sort_count.innerText = articleSortCounts;
    article_count.innerText = articleCounts;
}
function getAST_Fail() {

}
window.onload = getAST();