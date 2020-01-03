var opacity_value = 0;
var bg_img = document.getElementById("black");
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