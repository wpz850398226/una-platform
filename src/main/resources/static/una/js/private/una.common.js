$.ajaxSetup({
	cache: true,
	headers: {
		"token": localStorage.getItem("token")
	},
	error : function(xhr, textStatus, errorThrown) {
		var result = xhr.responseJSON;
		if(result!=null){
			console.log(result)
			var code = result['code'];
			var msg = result.message;

			if(code == 401){
				layer.msg(msg,function () {})
				localStorage.removeItem("token");
				setTimeout(function(){
					location.href = '/login.html';
				},1000);
			}else{
				layer.msg(msg,function () {})
			}
		}else{
			console.error(textStatus+":"+errorThrown)
		}

	}
})

//自动加载单选下拉选择的选项
$("select.una-select").each(function(){
	//$(this).parent().after("<img src='/una/images/loading.gif' style='display: none'>")
	if($(this).attr("data-param_value")!=null){
		prepareAutoSingleSelect($(this));
	}
})

//自动加载下拉选择的选项
/*$("input.una-select").each(function(){
	console.log("自动加载input下拉选择");
	autoLoadOption($(this));
})*/

//预备加载单选下拉框组件
function prepareAutoSingleSelect(selector){
	$(selector).find("option").remove();
	var placeholder = $(selector).data("placeholder");
	if(placeholder==null||placeholder.length==0)placeholder="请选择";
	$(selector).append("<option value=\"\">"+placeholder+"</option>");
	if($(selector).attr("lay-verify")!="required")$(selector).append("<option value=\"null\">空</option>");
	//$(selector).parent().next().css("display","block");
	autoLoadOption($(selector));
}

//加载多选下拉框组件
function prepareAutoMultipleSelect(selector,sample){
	var e = selector;
	//if(paramValue!=null&&paramValue!="")e.closest("div.form-group").find("img.una_loading").css("display","block");
	var paramValue = e.attr("data-param_value")
		,url = e.data("url")
		,paramName = e.data("param_name")
		,selectedVal = e.data("selected")
		,optionNameFieldCode = e.data("option_name_field_title")
		,optionValueFieldCode = e.data("option_value_field_title")
		,param=""
		,paramJson = {}
		,optionData=[]
		,name = e.attr("name");

	if(url!=null&&url.indexOf("queryPlural")==-1)url = url +"/queryPlural";
	if(optionNameFieldCode==null||optionNameFieldCode=="")optionNameFieldCode="title";
	if(optionValueFieldCode==null||optionValueFieldCode=="")optionValueFieldCode="id";

	if(paramName!=null&&paramName.indexOf(",")!=-1&&paramValue.indexOf(",")!=-1){
		//传递了多个查询条件
		var paramNameArray= new Array(); //定义一参数名数组
		var paramValueArray= new Array(); //定义一参数值数组
		paramNameArray=paramName.split(","); //字符分割
		paramValueArray=paramValue.split(","); //字符分割
		for (var i=0;i<paramNameArray.length ;i++ ){
			if(i>0)param = param + ",";
			var subParamValue = "";
			if(paramValueArray[i]!="null"){
				var transferParamValue = paramValueArray[i];
				if(transferParamValue.indexOf("&")!=-1){
					//如果选项包含&，用逗号替代
					transferParamValue = transferParamValue.replace("&",",");
				}
				subParamValue = transferParamValue;
			}
			param = param + paramNameArray[i]+":'"+subParamValue+"'";
		}
	}else{
		var transferParamValue = paramValue;
		if(paramValue!=null&&paramValue.indexOf("&")!=-1){
			//如果选项包含&，用逗号替代
			transferParamValue = transferParamValue.replace("&",",");
		}
		param = paramName+":'"+transferParamValue+"'";
	}

	//如果参数值为all，则查询参数为空
	if(paramValue!='all')paramJson = eval('({' + param + '})');

	if(paramValue!=null&&paramValue!=""){
		$.ajax({
			type: 'POST',
			url: url,
			data: paramJson,
			success: function(result) {
				var valueArray;
				if(sample!=null&&sample[name]!=null){
					valueArray = sample[name].split(',');
				}
				$.each(result,function(index,record){
					if(sample!=null&&valueArray!=null&&valueArray.contains(record[optionValueFieldCode])){
						record.selected=true;
					}
					optionData.push(record);
				})
				//e.closest("div.form-group").find("img.una_loading").css("display","none");
			},
			async:false,
			dataType: 'json'
		});

	}

	var eName = e.attr("name");
	xmSelect.render({
		el: '[name="'+eName+'"]',
		filterable: true,
		name:name,
		prop: {
			name: optionNameFieldCode,
			value: optionValueFieldCode,
		},
		data: optionData
	})
}


/**
 * 加载下拉选择的选项
 * param e:select元素
 */
function autoLoadOption(e){
	//if(paramValue!=null&&paramValue!="")e.closest("div.form-group").find("img.una_loading").css("display","block");
	var paramValue = e.attr("data-param_value")
		,url = e.data("url")
		,paramName = e.data("param_name")
		,selectedVal = e.data("selected")
		,optionNameFieldCode = e.data("option_name_field_title")
		,optionValueFieldCode = e.data("option_value_field_title")
		,oldOptionNum = e.find("option.una-dynamic").length
		,param=""
		,paramJson = {};

	if(url!=null&&url.indexOf("list")==-1)url = url +"/list";
	if(optionNameFieldCode==null)optionNameFieldCode="title";
	if(optionValueFieldCode==null||optionValueFieldCode=="")optionValueFieldCode="id";
	e.find("option.una-dynamic").remove();

	if(paramName!=null&&paramName.indexOf(",")!=-1&&paramValue.indexOf(",")!=-1){
		//传递了多个查询条件
		var paramNameArray= new Array(); //定义一参数名数组
		var paramValueArray= new Array(); //定义一参数值数组
		paramNameArray=paramName.split(","); //字符分割
		paramValueArray=paramValue.split(","); //字符分割
		for (var i=0;i<paramNameArray.length ;i++ ){
			if(i>0)param = param + ",";
			var subParamValue = "";
			if(paramValueArray[i]!="null"){
				var transferParamValue = paramValueArray[i];
				if(transferParamValue.indexOf("&")!=-1){
					//如果选项包含&，用逗号替代
					transferParamValue = transferParamValue.replace("&",",");
				}
				subParamValue = transferParamValue;
			}
			param = param + paramNameArray[i]+":'"+subParamValue+"'";
		}
	}else{
		var transferParamValue = paramValue;
		if(paramValue!=null&&paramValue.indexOf("&")!=-1){
			//如果选项包含&，用逗号替代
			transferParamValue = transferParamValue.replace("&",",");
		}
		param = paramName+":'"+transferParamValue+"'";
	}

	//如果参数值为all，则查询参数为空
	if(paramValue!='all')paramJson = eval('({' + param + '})');


	if(paramValue!=null&&paramValue!=""){
		$.ajax({
			type: 'GET',
			url: url,
			data: paramJson,
			success: function(result) {
				$.each(result,function(index,record){
					if(selectedVal==record[optionValueFieldCode]){
						e.append("<option class='una-dynamic' selected='selected' value='"+selectedVal+"'>"+record[optionNameFieldCode]+"</option>");
					}else{
						e.append("<option class='una-dynamic' value='"+record[optionValueFieldCode]+"'>"+record[optionNameFieldCode]+"</option>");
					}
				})
				e.parent().next().css("display","none");
			},
			async:false,
			dataType: 'json'
		});

		layui.form.render();

	}

	//联动的下拉选择元素选项清空
	if(oldOptionNum!=0){
		var subId = e.data("sub_id");
		if(subId!=null&&subId!=""){
			$("#"+subId).removeAttr("data-param_value");
			autoLoadOption($("#"+subId));
		}
	}
}

/**
 * 联动选择
 * 类名：una-select-linkage
 * 必要属性：
 * data-sub_id     要联动的子元素；
 * 子元素的data-param_name      子元素中存放参数名
 * 子元素的data-param_value     子元素中存放参数值
 */
$("select.una-select-linkage").change(function(){
	var value = $(this).find("option:selected").val();
	var subIds = $(this).data("sub_id");
	var subIdArray= new Array(); //定义一数组
	subIdArray=subIds.split(","); //字符分割
	//var index = layer.load(1, {shade: false}); //0代表加载的风格，支持0-2
	for (var i=0;i<subIdArray.length ;i++ ){
		var defaultValue = $("#"+subIdArray[i]).attr("data-param_value");
		var newParamValue;
		if(defaultValue!=null){
			newParamValue = defaultValue+value
			newParamValue = defaultValue.substring(0,(defaultValue.lastIndexOf(",")+1))+value;
		}else{
			newParamValue = value;
		}
		console.log(subIdArray[i])
		$("#"+subIdArray[i]).attr("data-param_value",newParamValue);
		autoLoadOption($("#"+subIdArray[i]));
	}

})

//获取页面路径的某个字段所对应的参数
function getUrlParam(name) {//封装方法
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); //匹配目标参数
	if (r != null) return unescape(r[2]);
	return null; //返回参数值
}

//清理redis缓存
function wipeRedis(key){
	if(key==null)key ='${sysResponseParameter.sysEntity.code}'+"List";
	$.post("/una/sys/redis/delete",{keys:key},showResponseNoAction,'json');
}

/*
	 * 通过索引切换元素的class属性值,属性值1和2互相替换
	 * obj:元素对象
	 * value1:目标属性值1
	 * value2:目标属性值2
	 */
	function switchClassProperty(obj,value1,value2){

		var propertyValue = obj.prop("class");
		if(propertyValue!=null){

			if(propertyValue.indexOf(value1)!=-1){
				//如果有该属性值
				obj.removeClass(value1);
				obj.addClass(value2);

			}else{
				obj.addClass(value1);
				obj.removeClass(value2);
			}

		}else{
			console.log("无此索引值");
		}



	}


	/*
	 * 通过索引切换元素的value属性值
	 * index:索引
	 * value:目标属性值
	 */
	function switchValue(obj,value1,value2){
		var value = obj.val();

		//如果有该属性
		if(value==value1){
			obj.val(value2);
		}else{
			obj.val(value1);
		}


	}


	/*
	 * 通过索引切换元素的css属性值
	 * index:索引
	 * value:目标属性值
	 */
	function switchCssProperty(obj,propertyName,value1,value2){
		var propertyValue = obj.css(propertyName);

		//如果有该属性
		if(propertyValue==value1){
			obj.css(propertyName,value2);
		}else{
			obj.css(propertyName,value1);
		}


	}


	/*
	 * 通过索引切换元素的任何属性值
	 * index:索引
	 * value:目标属性值
	 */
	function switchAnyProperty(obj,propertyName,value1,value2){

		var propertyValue = obj.attr(propertyName);

		//如果有该属性
		if(propertyValue==value1){
			obj.attr(propertyName,value2);
		}else{
			obj.attr(propertyName,value1);
		}


	}

//显示正在加载图标
	$.fn.spin = function(opts) {
		this.each(function() {
		  var $this = $(this),
			  data = $this.data();

		  if (data.spinner) {
			data.spinner.stop();
			delete data.spinner;
		  }
		  if (opts !== false) {
			data.spinner = new Spinner($.extend({color: $this.css('color')}, opts)).spin(this);
		  }
		});
		return this;
	};

	//校验（增加动作）
	function addActionCheckTreeData(){
		var treeDataId=$("#treeDataId").val();
		if(treeDataId!=""&&treeDataId!=0){
			//console.log(menuId);
			return true;
		}else{
			layer.msg('请选择父节点', function(){});
			return false;
		}

	}

	function uuid() {
		return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
			var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
			return v.toString(16);
		});
	}


//页面打印
function preview(oper){
	if (oper < 10){
		bdhtml=window.document.body.innerHTML;//获取当前页的html代码
		sprnstr="<!--startprint"+oper+"-->";//设置打印开始区域
		eprnstr="<!--endprint"+oper+"-->";//设置打印结束区域
		prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html
		prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html
		window.document.body.innerHTML=prnhtml;
		window.print();
		window.document.body.innerHTML=bdhtml;
	} else{
		window.print();
	}
}


/********************************************
* 以下为una2.0新增方法
*
* ***********************************/
/**
 * 切换类名
 */
$.fn.switchClass = function () {
	var switchClass1 = this.data("switch1"),
		switchClass2 = this.data("switch2");
	if(switchClass1!=null&&switchClass2!=null){
		this.hasClass(switchClass1)?this.removeClass(switchClass1).addClass(switchClass2):this.removeClass(switchClass2).addClass(switchClass1);
	}
};


/**
 * 类名绑定点击事件：切换类名
 * 注：此方法需页面加载完成之后执行
 */
$(document).ready(function(){
	$('.una-switch-class').on("click",function(){
		$(this).switchClass();
		if($(this).find(".layui-icon").length>0){
			$(this).find(".layui-icon").switchClass();
		}
	});
});


/**
 * map转json
 */
function mapToJson(map){
	let obj= Object.create(null);
	for (let[k,v] of map) {
		obj[k] = v;
	}
	//object转json
	var dataJsonStr = JSON.stringify(obj);
	return $.parseJSON( dataJsonStr );
}


//开始全屏
function setFullScreen()
{
    var docElm = document.documentElement;
    //W3C
    if (docElm.requestFullscreen) {
        docElm.requestFullscreen();
    }
    //FireFox
    else if (docElm.mozRequestFullScreen) {
        docElm.mozRequestFullScreen();
    }
    //Chrome等
    else if (docElm.webkitRequestFullScreen) {
        docElm.webkitRequestFullScreen();
    }
    //IE11
    else if (elem.msRequestFullscreen) {
        elem.msRequestFullscreen();
    }
}

//退出全屏
function quitFullScreen() {


    if (document.exitFullscreen) {
        document.exitFullscreen();
    }
    else if (document.mozCancelFullScreen) {
        document.mozCancelFullScreen();
    }
    else if (document.webkitCancelFullScreen) {
        document.webkitCancelFullScreen();
    }
    else if (document.msExitFullscreen) {
        document.msExitFullscreen();
    }
}

/**
 * [isFullscreen 判断浏览器是否全屏]
 * @return [全屏则返回当前调用全屏的元素,不全屏返回false]
 */
function isFullscreen(){
    return document.fullscreenElement    ||
        document.msFullscreenElement  ||
        document.mozFullScreenElement ||
        document.webkitFullscreenElement || false;
}

/**
 * 字符串转json
 * 字符串参数类型：'{name=zhangsan,age = 16,  job =IT}'
 * @param str
 */
function formatJSON(str){
	var newObj={};
	str= str.substring(1,str.length-1);
	var reg = /\s+/g;//去除所有空格
	str = str.replace(reg,"");
	proData = str.split(",");//将字符串转成数组  name=liqin,  age = 12,
	for(var i=0,len=proData.length;i<len;i++){// name=liqin, 
		var key = proData[i].split("=")[0];//键名
		var val = proData[i].split("=")[1];//键值
		newObj[key] = val
	}
	return newObj;
}

/**
 * 给系统对象加一个扩展函数：
 * 判断数组是否包含某个字符串
 */


Array.prototype.contains = function (obj) {
	var index = this.length;
	while (index--) {
		if (this[index] === obj) {
			return true;
		}
	}
	return false;
}

//转为json对象
$.fn.serializeJson = function () {
	var serializeObj = {};
	var array = this.serializeArray();
	$.each(array, function () {
		if (serializeObj[this.name] !== undefined) {
			if (!serializeObj[this.name].push) {
				serializeObj[this.name] = [serializeObj[this.name]];
			}
			serializeObj[this.name].push(this.value || '');
		} else {
			serializeObj[this.name] = this.value || '';
		}
	});
	return serializeObj;
};

//数组查找指定的元素在数组中的位置
Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val) return i;
	}
	return -1;
};

//数组移除指定的某个元素
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};
