$(document).ready(function () {
	$("#menuButton, #closeButton").click(function () {
		$(".side-menu").animate({left: '0'}, 300);
	});
    $(".side-menu a, #closeButton").click(function () {
        $(".side-menu").animate({left: '-250px'}, 300);
	});
});