//隐藏与失效
function displayAndDisable(selector){
	selector.addClass("displayAndDisabled")
	selector.css("display","none");
	selector.attr("disabled","true");
}

//显示与生效
function showAndAble(selector){
	selector.removeClass("displayAndDisabled")
	selector.css("display","block");
	selector.attr("disabled","false");
}

//动态增加格式检验
function addValidation(object,checkClassName){
	if(checkClassName==null)checkClassName="checkNull";
	object.addClass("una-validateOnSubmit "+checkClassName)
	object.parent().append("<span class='formInputSpan'></span>");
}
//动态清除格式检验
function deleteValidation(object,checkClassName){
	object.removeClass("una-validateOnSubmit "+checkClassName)
	object.removeClass("una-validateOnBlur "+checkClassName)
	object.parent().find("span.formInputSpan").remove();
	object.css("border-color","");
}

//自动加载检测单元
/*
 * 通过类型，设置不同的检测触发方式
 * 3种方式：1、加载即检测；2、失去焦点时检测；3、点击提交按钮时检测
 */
$(document).ready(function(){
	$(".una-validateOnReady").each(function(){
		$(this).parent().find("span.formInputSpan").remove();
		$(this).parent().append("<span class='formInputSpan'></span>");
		$(this).attr("onblur","saveValidate(this)")
		saveValidate($(this));
	})
	$(".una-validateOnBlur").each(function(){
		/*$(this).parent().find("span.formInputSpan").remove();
		$(this).parent().append("<span class='formInputSpan'></span>");
		$(this).attr("onblur","saveValidate(this)")*/
		autoValidate($(this));
	})
	$(".una-validateOnSubmit").each(function(){
		/*$(this).parent().find("span.formInputSpan").remove();
		$(this).parent().append("<span class='formInputSpan'></span>");*/
		autoValidate($(this));
	})
	$(".checkNull").each(function(){
		if($(this).parents(".tdFormContent").length>0&&$(this).parent().prev().children().prop("class")!="requiredFormUnit"){
			$(this).parent().prev().prepend("<span class='requiredFormUnit'>*</span>")
		}
	})
})

function autoValidate(selector){
	$(selector).parent().find("span.formInputSpan").remove();
	$(selector).parent().append("<span class='formInputSpan'></span>");
	if($(selector).hasClass("una-validateOnBlur"))$(selector).attr("onblur","saveValidate(this)");
}

/*
 * 检测表单格式
 * @Param selector 元素选择器 必填
 */
function checkForm(selector){
	var judgement = true;

	$(selector).closest("form").find(".formInputSpan").each(function(){
		$(this).html("");
	})
	$(selector).closest("form").find(".una-validateOnBlur").each(function(){
		saveValidate($(this));
	})
	$(selector).closest("form").find(".una-validateOnSubmit").each(function(){
		saveValidate($(this));
	})

	$(selector).closest("form").find(".formInputSpan").each(function(){
		if(!$(this).closest(".displayAndDisabled").length>0&&$(this).html().trim()!=""){
			$(this).closest("div.tab-content").find("div.tab-pane").removeClass("in").removeClass("active");
			$(this).closest("div.tab-pane").addClass("in").addClass("active");
			$('html, body').animate({scrollTop: $(this).parent().offset().top}, 300)		//页面滑动到报错的位置
			var id = $(this).closest("div.tab-pane").attr("id");
			var hrefValue = "#"+id;
			$(this).closest("div.tabbable").find("ul.nav-tabs").find("li").removeClass("active").find("a").each(function(index,e){
				if($(e).attr("href")==hrefValue)$(e).parent().addClass("active");
			})

			judgement = false;
			return false;
		}
	})
	return judgement;
}

/*
 * 检验元素格式
 * @Param selector 元素选择器 必填
 */
function saveValidate(selector){
	if($(selector).closest(".displayAndDisabled").length>0)return false;
	if($(selector).hasClass("una-activiti")&&$("#activateFlag").val()==0)return false;

	var theClassName = $(selector).prop("class");
	//if($(selector).parent().find("span.formInputSpan").html().trim()!="")return false;
	if(theClassName.indexOf("checkRadioNull")!=-1){
		//单选组件-校验空
		checkRadioNull($(selector));
		if($(selector).parent().find("span.formInputSpan").html().trim()!="")return false;
	}

	if(theClassName.indexOf("checkCheckBoxNull")!=-1){
		//checkbox-校验空
		checkCheckBoxNull($(selector));
		if($(selector).parent().find("span.formInputSpan").html().trim()!="")return false;
	}


	if(theClassName.indexOf("checkNull")!=-1){
		//文本框-校验空
		checkNull($(selector));
		if($(selector).parent().find("span.formInputSpan").html().trim()!="")return false;
	}


	if(theClassName.indexOf("checkNumber")!=-1){
		//文本框-校验数字
		checkNumber($(selector))
	}
	if($(selector).parent().find("span.formInputSpan").html().trim()!="")return false;
	if(theClassName.indexOf("checkPhoneNumber")!=-1){
		//文本框-校验手机号
		checkPhoneNumber($(selector))
	}
	if($(selector).parent().find("span.formInputSpan").html().trim()!="")return false;
	if(theClassName.indexOf("checkChinese")!=-1){
		//文本框-校验中文
		checkChinese($(selector))
	}
	if($(selector).parent().find("span.formInputSpan").html().trim()!="")return false;
	if(theClassName.indexOf("checkUserName")!=-1){
		//文本框-校验中文名
		checkUserName($(selector))
	}
	if($(selector).parent().find("span.formInputSpan").html().trim()!="")return false;
	if(theClassName.indexOf("checkUserID")!=-1){
		//文本框-校验身份证号
		checkUserID($(selector))
	}
	if($(selector).parent().find("span.formInputSpan").html().trim()!="")return false;
	if(theClassName.indexOf("checkExcel")!=-1){
		//文本框-校验excel格式
		checkExcel($(selector))
	}
}



/*姓名身份证，手机号提交*/
function isChinaName(val) {
    var pattern = /^[\u4E00-\u9FA5]{1,6}$/;
    return pattern.test(val);
}

/*验证中文*/
function isChinese(val) {
	var pattern = /^[\u4E00-\u9FA5]/;
	return pattern.test(val);
}

//验证数字
function isNumber(val){
	 var regPos = /^\d+(\.\d+)?$/; //非负浮点数
	    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
	    if(regPos.test(val) || regNeg.test(val)){
	        return true;
	    }else{
	        return false;
	    }
}

//验证excel
function isExcel(val){
	var fileType = (val.substring(val
            .lastIndexOf(".") + 1, val.length));
            //.toLowerCase();
    if (fileType !== 'xls' && fileType !== 'xlsx') {
        return false;
    }else{
    	return true;
    }
}


// 验证身份证
function isCardNo(val) {
    var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    return pattern.test(val);
}

// 验证手机号
function isPhoneNo(val) {
    var pattern = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
    return pattern.test(val);
}

/*中文名判断*/
function checkUserName(selector) {
	if (isChinaName($.trim(selector.val())) == false) {
    	selector.css("border-color","#256ec3");
    	selector.parent().find(".formInputSpan").html("× 中文项");
    	return false;
    }else{
    	selector.css("border-color","");
    	selector.parent().find(".formInputSpan").html("√");
    	return true;
    }
};

/*excel格式判断*/
function checkExcel(selector) {
	if (isExcel($.trim(selector.val())) == false) {
		selector.css("border-color","#256ec3");
		selector.parent().find(".formInputSpan").html("× 文件格式错误");
		return false;
	}else{
		selector.css("border-color","");
		selector.parent().find(".formInputSpan").html("");
		return true;
	}
};

/*身份证判断*/
function checkUserID(selector) {
    if (isCardNo($.trim(selector.val())) == false) {
    	selector.css("border-color","#256ec3");
    	selector.parent().find(".formInputSpan").html("× 身份证号格式错误");
    	return false;
    }else{
    	selector.css("border-color","");
    	selector.parent().find(".formInputSpan").html("");
    	return true;
    }
};

/*手机号判断*/

function checkPhoneNumber(selector) {
	if ($.trim(selector.val()).length != 0&&isPhoneNo($.trim(selector.val())) == false) {
    	selector.css("border-color","#256ec3");
    	selector.parent().find(".formInputSpan").html("× 手机号格式错误");
    	return false;
    }else{
    	selector.css("border-color","");
    	selector.parent().find(".formInputSpan").html("");
    	return true;
    }


};

/*非空判断*/
function checkNull(selector) {
	//console.log("checkNull")
	if ($.trim(selector.val()).length == 0) {
		//console.log("检测为空")
		selector.css("border-color","#256ec3");
		selector.parent().find(".formInputSpan").html("× 不能为空");
		return false;
	} else {
		selector.css("border-color","");
		selector.parent().find(".formInputSpan").html("");
    	return true;
	}

};

/*checkbox非空判断*/
function checkCheckBoxNull(selector) {
	if(selector.is(':checked')){
		selector.parent().find(".formInputSpan").html("");
		return true;
	}else{
		selector.parent().find(".formInputSpan").html("× 请选择");
		return false;
	}
};


/*select非空判断*/
function checkSelectNull(selector) {
	if (selector.parent().find("a.chosen-single-with-deselect").length <= 0
			&& selector.parent().find("li.search-choice").length <= 0) {
		selector.css("border-color","#256ec3");
		selector.parent().find(".formInputSpan").html("× 必选项不能为空");
		return false;
	} else {
		selector.css("border-color","");
		selector.parent().find(".formInputSpan").html("");
		return true;
	}
};

/*radio非空判断*/
function checkRadioNull(selector) {
	var val=selector.find('input:radio:checked').val();
	if(val==null){
		selector.parent().find(".formInputSpan").html("× 请选择");
		return false;
	}else{

		selector.parent().find(".formInputSpan").html("");
		return true;
	}

};

/*数字判断*/
function checkNumber(selector) {
	if (isNumber($.trim(selector.val())) == false) {
		selector.css("border-color","#256ec3");
		selector.parent().find(".formInputSpan").html("× 格式错误，必须是数字");
		return false;
	}else{
		selector.css("border-color","");
    	selector.parent().find(".formInputSpan").html("");
    	return true;
    }
};

/*中文判断*/
function checkChinese(selector) {
	if (isChinese($.trim(selector.val())) == false) {
		selector.css("border-color","#256ec3");
		selector.parent().find(".formInputSpan").html("× 格式错误，必须是中文");
		return false;
	}else{
		selector.css("border-color","");
    	selector.parent().find(".formInputSpan").html("");
    	return true;
    }
};
