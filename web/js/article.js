var container; // 要删除的评论的节点
var component; // 踩或顶的节点
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
/**
 * 点赞这个ariticle
 * @param article_id
 */
function love_article(article_id){
    var url = "/MyBlog/servlet/StarServlet?id="+article_id ;
    ajaxSend(url, "POST", "", likeArticleCallback, consoleFun, emptyfun);
}

/**
 * 点赞成功的回调函数
 * @param res
 */
function likeArticleCallback(result) {
    // 解析json对象
    var res = eval('(' + result + ')');
    if (res.msg == "success") {
        //返回 ”success“
        document.getElementById("like-article").innerHTML= res.new_star;
        document.getElementById("ic-like").style.fill = "#F63D47";
    }else{
        alert("已经点过赞了噢...");
    }
}


/*
 *删除评论
 */
function deletecm(component,comm_id){
    container = component.parentNode.parentNode;
    var url = "/MyBlog/CMDeleteServlet?id="+comm_id ;
    ajaxSend(url, "post", "", deleteCommentCallback, consoleFun, emptyfun);
}

function deleteCommentCallback(result) {
    // 解析json对象
    var res = eval('(' + result + ')');
    if(res.msg == "success"){
        //删除评论的视图
        var p = container.parentNode;
        p.removeChild(container);
    }
}
function emptyfun() {}

function star_or_diss(component, attitude,  comm_id,) {
    switch (attitude) {
        case 0: {
            diss(component, comm_id);
            break;
        }
        case 1: {
            star(component, comm_id);
            break;
        }
    }
}

/**
 * 顶评论
 */
function star(com , comm_id) {
    component = com;
    var url = "/MyBlog/StarCommentServlet?id="+comm_id  + "&diss_or_star=star";
    ajaxSend(url, "post", "", starCallback, consoleFun, emptyfun);
    // 获取ajax
    // var xmlhttp = getXHR();
    // xmlhttp.onreadystatechange = function() {
    //     if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    //         // 处理服务器收到的请求响应
    //         var res = xmlhttp.responseText;
    //         // 解析json对象
    //         var res = eval('(' + res + ')');
    //         if (res.msg == "success") {
    //             //返回 ”success“
    //             component.innerHTML = res.new_star;
    //         }else{
    //             alert("不要狂点呀...");
    //         }
    //     }
    // }
    // xmlhttp.open("POST", url, true);
    // xmlhttp.send();
}

/**
 * 踩评论
 */
function diss(com , comm_id) {
    component = com;
    var url = "/MyBlog/StarCommentServlet?id="+comm_id + "&diss_or_star=diss";
    ajaxSend(url, "post", "", dissCallback, consoleFun, emptyfun());
    // // 获取ajax
    // var xmlhttp = getXHR();
    // xmlhttp.onreadystatechange = function() {
    //     if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    //         // 处理服务器收到的请求响应
    //         var res = xmlhttp.responseText;
    //         // 解析json对象
    //         var res = eval('(' + res + ')');
    //         if (res.msg == "success") {
    //             //返回 ”success“
    //             component.innerHTML = res.new_diss;
    //         }else{
    //             alert("不要狂点呀...");
    //         }
    //     }
    // }
    // xmlhttp.open("POST", url, true);
    // xmlhttp.send();
}
function starCallback(result) {
    console.log(result);
    // 解析json对象
    var res = eval('(' + result + ')');
    if (res.star == "starSuccess") {
        //返回 "success"
        component.innerHTML = res.starcount;
    }else{
        alert("不要狂点呀...");
    }
}
function dissCallback(result) {
    console.log(result);
    // 解析json对象
    var res = eval('(' + result + ')');
    if (res.diss == "dissSuccess") {
        //返回 "success"
        component.innerHTML = res.disscount;
    }else{
        alert("不要狂点呀...");
    }
}
