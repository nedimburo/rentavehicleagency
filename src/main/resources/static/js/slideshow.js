$(document).ready(function () {
    var slideIndex = 0;
    function showSlide(index) {
        $(".forSlide").fadeOut(500);
        $(".forSlide:eq(" + index + ")").fadeIn(500);
    }
    function showNextSlide() {
        slideIndex = (slideIndex + 1) % $(".forSlide").length;
        showSlide(slideIndex);
    }
    function showPrevSlide() {
        slideIndex = (slideIndex - 1 + $(".forSlide").length) % $(".forSlide").length;
        showSlide(slideIndex);
    }
    showSlide(slideIndex);
     $(".next").on("click", function () {
        showNextSlide();
    });
    $(".prev").on("click", function () {
        showPrevSlide();
    });
});