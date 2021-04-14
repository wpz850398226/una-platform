 $(function(){
    function resize(){
        var wh=$(window).height();
        if(wh<710){
            $('.hotnews').addClass('jr_height1');
            $('.fp-slidesNav').addClass('jr_height');
        }
        else{
            $('.hotnews').removeClass('jr_height1');
            $('.fp-slidesNav').removeClass('jr_height');
        }
    }
    setTimeout(function(){
        resize();
    },100)

    $(window).resize(function(){
        resize();
    })
    $('.logo').addClass('active');
    setTimeout(function(){
        $('.slide1').removeClass('active1');
    },5000)
    $('.nav_ul li').hover(function(){
        $(this).children('a').stop(false,true).animate({'top':'-32px'},200);
    },function(){
        $(this).children('a').stop(false,true).animate({'top':'0px'},200);
    })
    $('.xf_bot').click(function(){
        $(this).children('div').children('div').toggleClass('gb');
        $('.xf_top').fadeToggle();
    })
    $('.xf_top_k').hover(function(){
        $(this).children('.xf_top_k1').fadeIn();
        $(this).find('.xf_top_k_nr').stop(true,false).animate({'right':'10px'},300);
        $(this).css('z-index','111111');
    },function(){
        $(this).children('.xf_top_k1').fadeOut();
        $(this).find('.xf_top_k_nr').stop(true,false).animate({'right':'-154px'},300);

        $(this).css('z-index','111110');
    })
    $('.fhtop').click(function(){
        $('html,body').animate({'scrollTop':'0'},300);
    })


    fade('sec3more','abs');

    touM('sec2nr_bot a');
    touM('sec3nr1 a');

    tim2=setInterval(function(){

        huxideng('xf_bot>div');
    },3210)



    $('.ul1 li').each(function(){
        if($(this).index()%5==4){
            $(this).css('padding-right','0px');
        }
    })
    $('.map1 area').hover(function(){
        $('.sec4k>div').eq($(this).index()).find('.imagg2').stop(true,false).fadeToggle();
    })

    $('.ul2>li:odd').addClass('zyright');
    $('.ul2>li:even').addClass('zyleft');
    $('.ul2 li').hover(function(){
        $(this).find('.cl').stop(true,false).fadeToggle();
    })






    // 2016-6-30
    $('.xuanfu2 a').eq(0).css('border','none');
    $('.xuanfu2 a').hover(function(){
        $(this).children('.abs').stop(true,false).fadeToggle();
    })


    //2016-7-30

        //2016-08-05
        $('.ej_sec4 .gy_nr1 .light_ul1 a').each(function(){
            $(this).click(function(){
                $(this).parents('.gy_nr1').siblings('.y_div1').fadeIn().find('.y_tupian img').eq($(this).index('.ej_sec4 .gy_nr1 .picList li a')).show().siblings().hide();
            })
        })
        $('.ej_sec4 .y_div1 span').click(function(){
            $(this).parent('.y_div1').fadeOut();
        })

        //2016-08-29
        // $('.ej_sec4 .gy_nr1 .light_ul1 li').click(function(){
        //     $(this).find('.y_div1').fadeIn().find('.y_tupian img').eq($(this).index()).show().siblings().hide();
        //     $(this).css('z-index',1);
        //     $(this).siblings().css('z-index',0);
        // })
        // $('.ej_sec4 .y_div1 span').click(function(){
        //     $(this).parent('.y_div1').fadeOut();
        // })


        //2016-08-08
        // 人才招聘
        $('.y_kuai_02 .divv').click(function(){
            var y_this = $(this);
            var y_thisIndex = $(this).index();
            $(this).parent('.y_kk1').siblings('.y_div1').find('span').eq($(this).index()).toggleClass('xz');
            $(this).parent('.y_kk1').siblings('.y_div1').find('span').eq($(this).index()).siblings().removeClass('xz');
            $(this).parent('.y_kk1').siblings('.y_div1').find('span').eq($(this).index()).siblings().find('.mg1').fadeOut();
            $(this).parent('.y_kk1').siblings('.y_div1').find('span').eq($(this).index()).siblings().find('.mg2').fadeIn();
            if(y_thisIndex>=5){
                if(y_this.attr('data-ttt')==2){
                    y_this.parents('.y_kk1').animate({'margin-left':0},800);
                    wy1(y_this);
                }
                else{
                    y_this.parents('.y_kk1').animate({'margin-left':-315},800);
                    wy2(y_this);
                }
            }
            else{
                if(y_this.attr('data-ttt')==2){
                    wy1(y_this);
                }
                else{
                    if(y_this.parents('.y_kk1').css('margin-left')=='-315px'){
                        $(this).parents('.y_kk1').animate({'margin-left':0},800);
                    }
                    wy2(y_this);
                }
            }
        })
        $('.y_div1 span').click(function(){
            var y_index = $(this).index();
            var y_theindex = $(this).parent('.y_div1').siblings('.y_kk1').find('.divv').eq(y_index);
            $(this).toggleClass('xz');
            $(this).siblings().removeClass('xz');
            $(this).siblings().find('.mg1').fadeOut();
            $(this).siblings().find('.mg2').fadeIn();
            if(y_index>=5){
                if(y_theindex.attr('data-ttt')==2){
                    y_theindex.parents('.y_kk1').animate({'margin-left':0},800);
                    y_theindex.find('a').fadeIn(500);
                    y_theindex.find('.diva').fadeOut(500);
                    y_theindex.animate({'width':157},800);
                    y_theindex.attr('data-ttt',1);
                    y_theindex.siblings('.divv').attr('data-ttt',1);
                }
                else{
                    y_theindex.parents('.y_kk1').animate({'margin-left':-315},800);
                    y_theindex.find('a').fadeOut(500);
                    y_theindex.siblings().find('a').fadeIn(500);
                    y_theindex.find('.diva').fadeIn(500);
                    y_theindex.siblings().find('.diva').fadeOut(500);
                    y_theindex.animate({'width':472},800);
                    y_theindex.attr('data-ttt',2);
                    y_theindex.siblings('.divv').attr('data-ttt',1);
                    y_theindex.siblings('.divv').animate({'width':157},800);
                }
            }
            else{
                if(y_theindex.attr('data-ttt')==2){
                    y_theindex.find('a').fadeIn(500);
                    y_theindex.find('.diva').fadeOut(500);
                    y_theindex.animate({'width':157},800);
                    y_theindex.attr('data-ttt',1);
                    y_theindex.siblings('.divv').attr('data-ttt',1);
                }
                else{
                    if(y_theindex.parents('.y_kk1').css('margin-left')=='-315px'){
                        y_theindex.parents('.y_kk1').animate({'margin-left':0},800);
                    }
                    y_theindex.find('a').fadeOut(500);
                    y_theindex.siblings().find('a').fadeIn(500);
                    y_theindex.find('.diva').fadeIn(500);
                    y_theindex.siblings().find('.diva').fadeOut(500);
                    y_theindex.animate({'width':472},800);
                    y_theindex.attr('data-ttt',2);
                    y_theindex.siblings('.divv').attr('data-ttt',1);
                    y_theindex.siblings('.divv').animate({'width':157},800);
                }
            }
        })

        $('.y_kuai_02 .divv').hover(function(){
            $(this).parent('.y_kk1').siblings('.y_div1').find('span').eq($(this).index()).find('.mg2').fadeOut();
            $(this).parent('.y_kk1').siblings('.y_div1').find('span').eq($(this).index()).find('.mg1').fadeIn();
        },function(){
            if($(this).parent('.y_kk1').siblings('.y_div1').find('span').eq($(this).index()).hasClass('xz')){

            }
            else{
                $(this).parent('.y_kk1').siblings('.y_div1').find('span').eq($(this).index()).find('.mg1').fadeOut();
                $(this).parent('.y_kk1').siblings('.y_div1').find('span').eq($(this).index()).find('.mg2').fadeIn();
            }
        })

        $('.y_kuai_02 .y_div1 span').hover(function(){
            $(this).find('.mg2').fadeOut();
            $(this).find('.mg1').fadeIn();
        },function(){
            if($(this).hasClass('xz')){

            }
            else{
                $(this).find('.mg1').fadeOut();
                $(this).find('.mg2').fadeIn();
            }
        })



        // var y_h = $('.y_about_div2 .div1').height();
        // $('.y_about_div2 .div1').css('margin-top',-(y_h/2));

        // 关于
        $('.y_about .div').click(function(){
            $('.y_mmc').show();
            $('.y_about_div2').show();
            $('.y_about_div2').find('.div1').eq($(this).index()).show();
            $('.y_about_div2').find('.div1').eq($(this).index()).siblings().hide();
        })
        $('.y_mmc').click(function(){
            $(this).hide();
            $('.y_about_div2').hide();
        })

     })

function wy1(y_this){
    y_this.find('a').fadeIn(500);
    y_this.find('.diva').fadeOut(500);
    y_this.animate({'width':157},800);
    y_this.attr('data-ttt',1);
    y_this.siblings('.divv').attr('data-ttt',1);
}
function wy2(y_this){
    y_this.find('a').fadeOut(500);
    y_this.siblings().find('a').fadeIn(500);
    y_this.find('.diva').fadeIn(500);
    y_this.siblings().find('.diva').fadeOut(500);
    y_this.animate({'width':472},800);
    y_this.attr('data-ttt',2);
    y_this.siblings('.divv').attr('data-ttt',1);
    y_this.siblings('.divv').animate({'width':157},800);
}



function fade(fuJ,ziJ){
    tim=setInterval(function(){
        huxideng(fuJ);
    },3210)
    $('.'+fuJ).hover(function(){
        $(this).find('.'+ziJ).stop(true,false).fadeIn();
        clearInterval(tim);
    },function(){
        $(this).find('.'+ziJ).stop(true,false).fadeOut();
        tim=setInterval(function(){
            huxideng(fuJ);
        },3210)
    })
 }

function huxideng(ele){

        $('.'+ele).fadeOut(1000,function(){

            setTimeout(function(){
                $('.'+ele).fadeIn(500)
            },400)
        });

}
function touM(ele){
    $('.'+ele).hover(function(){
        $(this).find('.opa0').removeClass('opa0').addClass('opa1');
    },function(){
        $(this).find('.opa1').removeClass('opa1').addClass('opa0');
    })
}
// 下箭头
$(document).ready(function(){
    timer=setInterval(function(){
        setTimeout(function(){
            $('.bombtn').animate({'bottom':25},800);
            $('.bombtn').animate({'bottom':20},200);
            $('.bombtn').animate({'bottom':25},200);
            $('.bombtn').animate({'bottom':20},200);
            $('.bombtn').animate({'bottom':25},200);
            $('.bombtn').animate({'bottom':15},800);
        }, 0)
    },1000)
    // $('.bombtn').hover(function(){
    //     $(this).find('img').attr("src","images/next1_09.png");
    // },function(){
    //     $(this).find('img').attr("src","images/next_09.png");
    // })
})