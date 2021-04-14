$(function() {
		$('.flicker-example').flicker();
       $(".ar_list").jCarouselLite({
        btnNext: ".ar1",
        btnPrev: ".ar2",
		visible: 1,
		auto: 5000,
		speed: 1000,
		onMouse:true
		
    });
	 $(".m_intm").jCarouselLite({
        btnNext: ".m_intl",
        btnPrev: ".m_intr",
		visible: 6,
		auto: 5000,
		speed: 1000
    });
	  $(".m_intmtuan").jCarouselLite({
        btnNext: ".m_intl",
        btnPrev: ".m_intr",
		visible: 6,
		auto: 5000,
		speed: 1000
    });
	$(".kuan_dl").jCarouselLite({
		visible: 3,
		vertical: true,
		auto: 5000,
		speed: 1000
    });
	
});

