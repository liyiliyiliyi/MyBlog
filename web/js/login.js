var opacity_value = 0;
var bg_img = document.getElementById("black");

var login_btn = document.getElementById("login-btn");
var reg_btn = document.getElementById("register-btn");
var repeat_pass = document.getElementById("repeatPassword");
var submit_btn = document.getElementById("submit");
var input_pass = document.getElementById("inputPassword");
var input_repat = document.getElementById("repeatPassword");
var warning_repat = document.getElementById("warning-repeat");
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
    fm.action = login_action;
}

function regist_page() {
    login_btn.style.background = "#fff";
    reg_btn.style.background = "#ddd";
    repeat_pass.style.display = "block";
    submit_btn.innerHTML = "注册";
    repeat_pass.required=true;
    fm.action = reg_action;
}
function check_repeat() {
    if (input_pass.value != input_repat.value) {
        warning_repat.innerHTML = "密码不一致";
    } else {
        warning_repat.innerHTML = "";
    }
}
