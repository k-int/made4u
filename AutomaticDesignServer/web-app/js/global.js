/* 
 * Functions, etc. for use in all pages of the system
 */

function resizeFrame()  {
    var height = $(window).height() - $('#footer').height();
    $("#container").css('height', height);
}

$(window).resize(function() {
    resizeFrame();
});

$(document).ready(function() {
    resizeFrame();
});