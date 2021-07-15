// JavaScript Document

var _isSupportTouch = "ontouchend" in document ? true : false;

$(function () {
	var isApple = (navigator.userAgent.toLowerCase().indexOf("iphone") >= 0 || navigator.userAgent.toLowerCase().indexOf("ipad") >= 0 || navigator.userAgent.toLowerCase().indexOf("windows phone") >= 0 || navigator.userAgent.toLowerCase().indexOf("android") >= 0);
	if(isApple){
		$("body,html").addClass("apple");
	}else{
		$("body,html").removeClass("apple");
	}


    var _isHover = false;
    var _w_win = $(window).width();

    var _$menu, _$menu_mobile;

    init();
    function init() {
        _$menu = $("#menu");
        _$menu_mobile = _$menu.clone();
        _$menu.after(_$menu_mobile);
        _$menu_mobile.removeClass().addClass('menu-mobile');
        _$menu_mobile.hide();

        _$menu.data("init", false);
        _$menu_mobile.data("init", false);

        initMenu();
        initQRcode();
        $(window).resize(function () {
            _w_win = $(window).width();
            initMenu();
            initQRcode();
        });
    }

    function initMenu() {
        if (_w_win > 768) {
            _$menu_mobile.hide();
            if (_$menu.data("init")) return false;
            _$menu.data("init", true);
            var $cur = $("." + _curWeb, _$menu);
            $("> ul", _$menu).append('<div class="clearBoth"></div>');
            $("> ul > li", _$menu).each(function () {
                if ($(this).children("ul").length) {
                    $(this).children("a").addClass('sub');
                }
            });
			
            $cur.addClass('on');

            $("> ul > li", _$menu).hover(
				function () {
				    if (!$(this).hasClass('on')) {
				        $cur.removeClass('on');
				        $(this).addClass('on');
				    }
				    var $sub = $(this).children(".submenu");
				    if ($sub.length) $sub.stop().fadeIn();
				    _isHover = true;
				}, function () {
				    if (!$cur.hasClass('on')) {
				        $(".on", _$menu).removeClass('on');
				        $cur.addClass('on');
				    }

				    var $sub = $(this).children(".submenu");
				    if ($sub.length) $sub.stop().fadeOut();

				    _isHover = false;
				}
			);

            if (_isSupportTouch) {
                $("> ul > li", _$menu).click(function (e) {
                    if (_isHover) {
                        _isHover = false;
                        return false;
                    }
                    var $sub = $(this).children("ul");
                    if ($sub.length) {
                        if ($sub.is(":visible")) {
                            $sub.stop().fadeOut();
                        } else {
                            $sub.stop().fadeIn();
                        }
                    }

                });
            }
        } else {
            if (_$menu_mobile.data("init")) return false;
            _$menu_mobile.data("init", true);
            $("." + _curWeb, _$menu_mobile).addClass('on');
            
            $(">ul>li>a", _$menu_mobile).click(function (e) {
                e.stopPropagation();
                var $ul = $(this).siblings(".submenu");

                if (!$ul.length) {
                    if (!$(this).hasClass('on')) {
                        $(".submenu", _$menu_mobile).stop().slideUp();
                        $ul.stop().slideToggle();
                    }
                } else {
                    $ul.stop().slideDown();
                }

                $("a.on", _$menu_mobile).removeClass('on');
                $(this).addClass('on');
            });
            $(".submenu a", _$menu_mobile).click(function (e) {
                e.stopPropagation();
                $(".submenu a.on", _$menu_mobile).removeClass('on');
                $(this).addClass('on');
            });
            $("#menu_mobile_open").click(function (e) {
                e.stopPropagation();
                if ($(this).hasClass('on')) {
                    $(this).removeClass('on');
                } else {
                    $("#menu_mobile_close").show();
                    $(this).addClass('on').hide();
                }
                _$menu_mobile.stop().slideToggle();
            });
            $("#menu_mobile_close").click(function (e) {
                $(this).hide();
                $("#menu_mobile_open").show().removeClass('on');
                if (_$menu_mobile.is(":visible")) _$menu_mobile.slideUp();
            });
        }
    }

    function initQRcode() {
        var $qrcodeCon = $("#qrcodeCon");
        if (_w_win > 999) {
            $qrcodeCon.css("right", (_w_win - 999) / 2 - 60);
        } else {
            $qrcodeCon.css("right", "0");
        }
        if ($qrcodeCon.length) {
            $(".wechat", $qrcodeCon).click(function (e) {
                $(".qrcodePic").show();
            });

            $(".dbbtn", $qrcodeCon).click(function () {
                $('body,html').stop().animate({ scrollTop: 0 }, "fast", "easeOutQuad");
            });
            $(".qrcodePic .close").click(function () {
                $(".qrcodePic").hide();
            });
        }
    }
});


//用UTF-8