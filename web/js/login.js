let getElement = function (name) {
    return document.querySelector(name);
};
let getElements = function (name) {
    return document.querySelectorAll(name);
};
let log = function() {
    console.log.apply(console, arguments);
};

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
function ajaxSend (url, method, data, fnSucceed, fnFail, fnLoading) {
    var ajax = ajaxObject();
    ajax.open(method ,url ,true);
    ajax.setRequestHeader( "Content-Type" , "application/x-www-form-urlencoded" );
    ajax.onreadystatechange = function () {
        if( ajax.readyState == 4 ) {
            if( ajax.status == 200 ) {
                fnSucceed( ajax.responseText );
            }
            else {
                fnFail( "HTTP请求错误！错误码："+ajax.status );
                // console.log("HTTP请求错误！错误码："+ajax.status);
            }
        } else {
            fnLoading();
        }
    }
    ajax.send(data);
}
function consoleFun(res) {
    console.log(res);
}
function emptyFun() {}

var opacity_value = 0;
var bg_img = document.getElementById("black");

var login_btn = document.getElementById("login-btn");
var reg_btn = document.getElementById("register-btn");
var repeat_pass = document.getElementById("repeatPassword");
var submit_btn = document.getElementById("submit");
var input_pass = document.getElementById("inputPassword");
var input_repat = document.getElementById("repeatPassword");
var warning_repat = document.getElementById("warning-repeat");
var warning_password = document.getElementById("warning-password");
var login_action = "/MyBlog/LoginServlet";
var reg_action = "/MyBlog/RegisterServlet";

function bgonfocus() {
    opacity_value += 0.05;
    bg_img.style.opacity = opacity_value;
    if(opacity_value < 0.5) {
        setTimeout(bgonfocus, 10);
    }
}
function bgblur() {
    opacity_value -= 0.05;
    bg_img.style.opacity = opacity_value;
    if(opacity_value > 0) {
        setTimeout(bgblur, 10);
    }
}
function login_page() {
    login_btn.style.background = "#ddd";
    reg_btn.style.background = "#fff";
    repeat_pass.style.display = "none";
    submit_btn.innerHTML = "登录";
    repeat_pass.required=false;
    warning_password.innerHTML = "";
    warning_repat.innerHTML = "";
    fm.action = login_action;
}

function regist_page() {
    login_btn.style.background = "#fff";
    reg_btn.style.background = "#ddd";
    repeat_pass.style.display = "block";
    submit_btn.innerHTML = "注册";
    repeat_pass.required=true;
    warning_password.innerHTML = "";
    warning_repat.innerHTML = "";
    fm.action = reg_action;
}
function check_repeat() {
    if (input_pass.value != input_repat.value) {
        warning_repat.innerHTML = "密码不一致";
    } else {
        warning_repat.innerHTML = "";
    }
}

let getBackIMG = function () {
    ajaxSend('/MyBlog/BackgroundServlet', 'get', '', function (url) {
        getElement('#bg-img').src = url;
        log(url);
    }, consoleFun(), emptyFun());
};

let __main = function () {
    window.onload = getBackIMG();
};

__main();
//
// //提供jsonp服务的url地址 (不管是什么类型的地址，最终生成的返回值都是一段javascript代码)
// //var url = "http://localhost:80/test/jsonp.php";  //  这个路径不是在本地直接打开的文件路径（不能放到服务器里面，也就是www目录下面）
// var callback = function(data) {
//     console.log("请求返回的数据",data);
// }
//
// //这里封装了一个函数 可以多次调用
// /*
//     url参数就是请求的地址
//     callback是我们获取返回数据的回到函数
// */
// function ajaxJSONP(url,callback){
//     var script = document.createElement("script");
//     script.setAttribute("src",url+"?callback="+callback);
//     console.log(script);
//     document.getElementsByTagName("body")[0].appendChild(script);
// }
//
// function loadBgImg() {
//     var url = "http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1"
//     ajaxJSONP(url, callback)
// }
//
// window.onload = loadBgImg();