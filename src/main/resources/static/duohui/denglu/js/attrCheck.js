// JavaScript Document
(function () {
    $('#seleAttr .botLine').each(function (i) {
        var $this = $(this);
        var liCount = $('.fl.seleList li', this).length;
        var hideLiCount = $('.fl.seleList li.hide', this).length;
        if (liCount == hideLiCount) {
            $this.hide();
        }
    });

    var $brandSele = $('#selectBrand');
    var $brandListSch = $brandSele.find('.seleList')[0].scrollHeight;
    if ($brandListSch > 90) {
        var $moreBtn = $('<a class="fr moreBtn"><span>更多</span>&nbsp;<i class="arrowIcon arrow_d">&nbsp;</i></a>');
        $moreBtn.appendTo($brandSele);
    }
    if ($brandListSch > 30 && $brandListSch <= 60) {
        var ni = '60';
    } else if ($brandListSch > 60) {
        var ni = '90'
    } else {
        var ni = '';
    }
    $brandSele.addClass('onOff' + ni);
    var $attrSele = $('.botLine:not(#selectBrand)');
    for (var i = 0; i < $attrSele.length; i++) {
        if ($attrSele[i].scrollHeight > 30) {
            $attrSele.eq(i).addClass('onOff');
            var $moreBtn = $('<a class="fr moreBtn"><span>更多</span>&nbsp;<i class="arrowIcon arrow_d">&nbsp;</i></a>');
            $moreBtn.appendTo($attrSele.eq(i));
        }
    }
    $('.moreBtn').bind('click',
    function () {
        $(this).attr('onselectstart', 'return false');
        var $span = $(this).find('span');
        $span.html() == '更多' ? $span.html('收起') : $span.html('更多');
        $(this).children('.arrowIcon')
            .toggleClass('arrow_d').toggleClass('arrow_u');
        var papa = $(this).closest('.botLine');
        var scH = papa[0].scrollHeight;
        var flag = true;
        var $papaId = papa.attr('id');
        if ($papaId == 'selectBrand') {
            papa.toggleClass('onOff' + ni);
        } else {
            papa.toggleClass('onOff');
        }

    });
    var $brandBtn = $('#selectBrand').find('.moreBtn');

    var $brandUl = $('#selectBrand').find('ul');

    $brandBtn.bind('click', function () {
        if ($brandListSch > 90) {
            $brandUl.toggleClass('auto' + (120));
        } else {
            $brandUl.toggleClass('auto' + ni);
        }
    });

    //品牌搜索s
    var $aBrand = $brandSele.find('.seleList').find('li');
    //alert($aBrand.length);
    var $brandSearch = $('#brandSearch');
    var $searchBox = $('.searchBox');
    $brandSearch.bind('keyup', function () {
        var $searchValue = $(this).val();
        if ($searchValue.length > 0) {
            var brandArr = [];
            for (var i = 0; i < $aBrand.length; i++) {
                var $brandName = $aBrand.eq(i).find('a').html();
                if ($brandName.indexOf($searchValue) != -1) {
                    $aBrand.eq(i).siblings().hide();
                    brandArr.push($aBrand.eq(i));
                } else {
                    $aBrand.eq(i).hide();
                }
            }
            for (var j = 0; j < brandArr.length; j++) {
                brandArr[j].show();
            }
        } else {
            $aBrand.show();
        }
    }).focus(function () {
        $searchBox.addClass('focus');
    }).blur(function () {
        $searchBox.removeClass('focus');
    });

    //品牌搜索e

    $('.toggle').hide();
    var flag = false;
    $('.onOffBtn').bind('click',
		function () {
		    flag = !flag;
		    if (flag) {
		        //$('.showBox').removeClass('showBoxOff').addClass('showBoxOn');
		        $(this).children('span').html('收起');
		    } else {
		        //$('.showBox').removeClass('showBoxOn').addClass('showBoxOff');
		        $(this).children('span').html('展开');
		    }
		    $(this).children('.arrowIcon').toggleClass('arrow_d').toggleClass('arrow_u');
		    $('.toggle').slideToggle();
		    //$('.showBox')
		});
    flag = null;
    var loc = window.location.href;
    //筛选框展开e
    //排序s
    var oldV = $('#resultSearch').val();
    var oldT = $('.currentBtn').html();
    $('#resultSearch').focus(function () {
        if ($(this).val() == oldV) {
            $(this).val('');
        }
    }).blur(function () {
        if ($(this).val() == '') {
            $(this).val(oldV);
        }
    });
    //排序e

    function findInArray(n, arr) {
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == n) {
                return true
            }
        }
        return false;
    }

    var curBrand = null; //当前选中品牌
    var curSeriesUrl = '';
    function init() {
        var curPath = window.location.pathname;
        var urlFragArr = curPath.split('/');
        var brandFrag = urlFragArr[1];
        var isOnlyBrand = (brandFrag.indexOf('.htm') >= 0 ? false : true);
        if (isOnlyBrand) {
            curBrand = curSeriesUrl = brandFrag;
        }
        else {
            var pIndex = brandFrag.indexOf('-search-');
            curBrand = curSeriesUrl = brandFrag.substring(0, pIndex);
        }
    }
    init();
    //获取search值e
    //if(curBrand){alert(5)}
    //alert(curBrand)
    //品牌添加s
    if (curBrand) {
        var brandList = $('.seleList:eq(0) li a');
        var brandListLen = brandList.length;
        var aBrandId = [];
        for (var i = 0; i < brandListLen; i++) {
            aBrandId.push(brandList.eq(i).attr('id'))
        }
        if (findInArray(curBrand, aBrandId)) {
            $('.seleList:eq(0)').closest('.botLine').hide();
            var $brandName = $('#' + curBrand).html();
            var $brand = $('<span class="activeDashed" id="brand">' + $brandName + '<i class="closeBtn brandClose">&nbsp;</i></span>');
            $brand.insertAfter('.choose')
        }
        //品牌添加e
    }
    var tmpVal = $('#attrIds').val();
    var objValue = (tmpVal == '' ? null : tmpVal);
    //alert(objValue)
    if (objValue != null) {
        var dataArr = objValue.split(',');
        var aAttr = $('#seleAttr .seleList a');
        for (var i = 0; i < aAttr.length; i++) {
            var atrLink = aAttr.eq(i);
            var id = atrLink.attr('id').split('-');
            var dataIndex = id[0];
            var oDate = id[1];
            if (dataArr[dataIndex] == oDate) {
                var atrSpan = $('<span class="activeDashed atr" id=' + atrLink.attr('id') + '>' + atrLink.html() + '<i class="closeBtn attrClose">&nbsp;</i></span>');
                $(atrSpan).appendTo('.bcNav');
                $(atrLink).closest('.botLine').hide();
            }
        }
    } else {
        var dataArr = null;
    }
    var hostPre = 'http://' + window.location.host;

//    //brand筛选s
//    $('#selectBrand .seleList a').bind('click',
//    function () {
//        var id = $(this).attr('id');
//        var curSeriesUrlFrag = id;
//        if (objValue == null || objValue == '0,0,0,0,0,0,0,0,0,0,0,0,0,0,0') {
//            window.location.href = (hostPre + '/' + curSeriesUrlFrag + '/');
//        } else {
//            window.location.href = hostPre + '/' + curSeriesUrlFrag + '-search-p-' + objValue + '.htm';
//        }
//        return false;
//    })
    //brand筛选e
    //属性筛选s
    $('#seleAttr .seleList a').bind('click',
    function () {
        var id = $(this).attr('id').split('-');
        var dataIndex = id[0];
        var dataAttr = id[1];
        if (dataArr == null) {
            dataArr = [];
            for (var i = 0; i < 15; i++) {
                dataArr.push(0)
            };
            dataArr[dataIndex] = dataAttr;
            dataStr = dataArr.join(',');
            window.location.href = hostPre + '/' + curSeriesUrl + '-search-p-' + dataStr + '.htm';
        } else {
            dataArr[dataIndex] = dataAttr;
            dataStr = dataArr.join(',');
            window.location.href = hostPre + '/' + curSeriesUrl + '-search-p-' + dataStr + '.htm';
        }

        return false;
    })
    //属性筛选e
    //价格区间自定义
    $('#priceBtn').bind('click',
    function () {
        var p1 = $('.priceTxt').eq(0).val();
        var p2 = $('.priceTxt').eq(1).val();
        if (isNaN(parseInt(p1)) || isNaN(parseInt(p2))) {
            alert('请输入正确数字');
            return false;
        } else if ((p1 == '') || (p2 == '')) {
            alert('两个文本框都不能为空');
            return false;
        } else if (parseInt(p2) < parseInt(p1)) {
            alert('请输入正确的价格区间');
            return false;
        }
        var dataIndex = parseInt($('.priceTxt').eq(0).attr('data-index'));
        var id = dataIndex + p1 + '_' + p2;
        if (dataArr != null) {
            dataArr[dataIndex] = p1 + '_' + p2;
            var dataStr = dataArr.join(',');
            if (dataStr == '0,0,0,0,0,0,0,0,0,0,0,0,0,0,0') {
                window.location.href = hostPre + '/' + curSeriesUrl + '/';
            } else {
                window.location.href = hostPre + '/' + curSeriesUrl + '-search-p-' + dataStr + '.htm';
            }
        } else {
            dataArr = [];
            for (var i = 0; i < 15; i++) {
                dataArr.push(0)
            };
            dataArr[dataIndex] = p1 + '_' + p2; ;
            dataStr = dataArr.join(',');
            if (dataStr == '0,0,0,0,0,0,0,0,0,0,0,0,0,0,0') {
                window.location.href = hostPre + '/' + curSeriesUrl + '/';
            } else {
                window.location.href = hostPre + '/' + curSeriesUrl + '-search-p-' + dataStr + '.htm';
            }
        }
        return false;
    });
    //价格区间自定义s
    //价格自定义区间样式添加s
    if (dataArr) {
        var priceReg = /^\d+\_\d+$/g;
        if (priceReg.test(dataArr[14])) {
            var atrSpan = $('<span class="activeDashed atr" id=' + 14 + '-' + dataArr[14] + '>' + dataArr[14].split('_').join('-') + '<i class="closeBtn attrClose">&nbsp;</i></span>');
            $(atrSpan).appendTo('.bcNav');
            $('.priceTxt').closest('.botLine').hide();
        }
    }
    //价格自定义区间样式添加e
    //属性筛选
    //关闭筛选的属性s
    $('#brand.activeDashed').bind('click',
    function () {
        var arr = curSeriesUrl.split('-');
        var series = arr[0];
        if (objValue == null || objValue == '0,0,0,0,0,0,0,0,0,0,0,0,0,0,0') {
            window.location.href = (hostPre + '/' + series + '/');
        } else {

            window.location.href = hostPre + '/' + series + '-search-p-' + objValue + '.htm';
        }
        return false;
    })
    $('.activeDashed.atr').bind('click',
		function () {
		    var dataIndex = $(this).attr('id').split('-')[0];
		    dataArr[dataIndex] = 0;
		    dataStr = dataArr.join(',');
		    if (dataStr == '0,0,0,0,0,0,0,0,0,0,0,0,0,0,0') {
		        window.location.href = hostPre + '/' + curSeriesUrl + '/';
		    } else {
		        window.location.href = hostPre + '/' + curSeriesUrl + '-search-p-' + dataStr + '.htm';
		    }
		    return false;
		});
    //关闭筛选的属性e
    //当筛选条件变少时让toggle不再隐藏
    var aBox = $('.botLine');
    var showNum = 0;
    for (var i = 0; i < aBox.length; i++) {
        if (aBox.eq(i).css('display') == 'none') {
            showNum++;
        }
    }
    if (showNum >= 3) {
        $('.toggle').show();
        $('.onOffBtn').hide();
    }
})();                                             //code by vidy 2015,0610		
