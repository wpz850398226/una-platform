//下载导入模板
function downloadTemplate(){
		var entityId = '${sysResponseParameter.sysEntity.id }';
		redirectInvocation("/una/sys/excel/template","entityId="+entityId);
	}

//内容为空的表格，自动填充“无”
$("td").each(function(){if($(this).html().trim().length==0)$(this).text("-")})
/*$("#dataTable").find("td").each(function(){
	$(this).addClass("pricing-box-small")
})*/

//限制列显示的长度，长度可在字段管理内设定
$("#dataTable").find("th").each(function(){
	var displayLength = $(this).data("display_length");
	var assignmentType = $(this).data("assignment_type");
	var boolean = $(this).hasClass("center")?'true':"false";
	var arr = $(this).prop("class").split(/\s+/)

	arr.forEach(function(item,index){
		if(item.indexOf("data-")!=-1){

			$("td."+item).each(function(){
				var tdText = $(this).text().replace(/[\r\n]/g,"").trim();
				//自动格式化显示长度
				if(displayLength>1){
					if(tdText.length>displayLength){
						$(this).attr("title",tdText);
						$(this).text(tdText.substring(0,displayLength)+'…');
					}
				}

				//自动设置是否型数据带背景颜色
				if(assignmentType=='开关单选'){
					switch(tdText) {
				     case '-':
				    	 $(this).html('<span class="badge badge-grey">'+tdText+'</span>');
				        break;
				     case '是':
				    	 $(this).html('<span class="badge badge-success">'+tdText+'</span>');
				        break;
				     case '否':
				    	 $(this).html('<span class="badge badge-danger">'+tdText+'</span>');
				    	 break;
				     /*default:
				    	 $(this).text('<span class="badge badge-danger">'+tdText+'</span>');*/
					}
				}

			})


			if(boolean=='true'){
				$("td."+item).addClass("center");
			}

		}
	})
})

/*
 * 通过表格前最近的#parameterForm加载表格，为同页内显示多个表格而设计
 * param:selector(tableFunctionPrimary所在的id)
 */
function loadTable(selector,pageNum,loadParam){
	loadParam!=null&&loadParam!=""?loadParam="?"+loadParam:loadParam="";
	if(pageNum!=null&&pageNum>0)$("#"+selector).find("#parameterForm").find(".pageNum").val(pageNum);
	queryObj($("#"+selector).find("#entityTableUrl").length>0?$("#"+selector).find("#entityTableUrl").val():$("#"+selector).find("#entityPath").val()+"/table"+loadParam
			,$("#"+selector).find("#parameterForm")
			,$("#"+selector).parent().find(".tableContainer:first"))
}

/*
 * 打开详情页，通用方法
 * @Param url:后台访问地址+参数值
 * @Param tabName:页签名称
 */
function queryDetailByUrl(url,tabName){

	if($("#indexTab").length>0){
		$("#indexIFrame").attr('src',url);
		addTabFromPage(tabName,url);
	}else{
		window.location.href=url;
		window.parent.addTabFromPage(tabName,url);
	}

}

//鼠标移入移除监听事件：切换表格行内隐藏元素&切换背景颜色
$("tr").not(".footing").mouseover (function(){
	$(this).find(".switch-hidden").removeClass("hidden");
	$(this).find("input[type=checkbox]").is(':checked')?'':$(this).css("background-color","#F0F8FF");
	//$(this).css("background-color")!='rgb(193, 255, 193)'?$(this).css("background-color","#F0F8FF"):'';
}).mouseout(function(){
	$(this).find(".switch-hidden").addClass("hidden");
	$(this).find("input[type=checkbox]").is(':checked')?'':$(this).css("background-color","");
	//$(this).css("background-color")!='rgb(193, 255, 193)'?$(this).css("background-color",""):'';
})

//鼠标点击事件：切换背景颜色
$("#dataTable").find("td").not(".una-tdCheck").not("tr.detail-row td").click(function(){

	$(this).closest("tbody").find("input[type=checkbox]:checked").removeAttr("checked").trigger("change");
	$(this).parent().find("input[type=checkbox]:first").prop("checked","checked").trigger("change");
})

//checkbox状态状态改变时的监听事件，改变所在行颜色
$("#dataTable").find("input[type=checkbox]").change(function(){
	if($(this).is(':checked')){
		$(this).closest("tr").css("background-color","#F0F8FF");
	}else{
		$(this).closest("tr").css("background-color","");
	}
})

//切换分页显示条数
$("#pageSizeSelect").change(function(){
	console.log("切换了")
	var pageSize = $(this).children('option:selected').val();
	//$(this).closest(".tableContainer").siblings().find("#parameterForm").find(".pageNum").val(1);
	//$(this).closest(".tableContainer").siblings().find("#parameterForm").find(".pageSize").val(pageSize);
	$(this).closest(".tableContainer").closest(".main-container").find("#parameterForm").find(".pageNum").val(1);
	$(this).closest(".tableContainer").closest(".main-container").find("#parameterForm").find(".pageSize").val(pageSize);
	//loadTable($(this).closest(".tableContainer").prev().prop("id"));
	toLoadTable();
})

/*
 * 表格翻页方法
 * @Param selector:被点击元素
 * @Param tableContainer:加载表格的容器id，当为空时，自动获取隐藏值“#tableContainer”的值作为容器id
 */
function turnPage(selector){
	var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
	var pageNum = $(selector).attr("data-pageNum");
	$(selector).closest(".tableContainer").closest(".main-container").find("#parameterForm").find(".pageNum").val(pageNum);
	toLoadTable();
}

//表格全选checkbox
$('table th input:checkbox').on('click' , function(){
	var selector = this;
	$(this).closest('table').find('tr > td:first-child input:checkbox')
	.each(function(){
		this.checked = selector.checked;
		$(this).closest('tr').toggleClass('selected');
		$(this).trigger('change');
	});


});

//复制表格当前页
function copyTable() {
    const table = document.getElementById('dataTable')
    const range = document.createRange()
    // 设定range包含的节点对象
    range.selectNode(table)
    // 窗口的selection对象，表示用户选择的文本
    const selection = window.getSelection()
    // 将已经包含的已选择的对象清除掉
    if (selection.rangeCount > 0) selection.removeAllRanges()
    // 将要复制的区域的range对象添加到selection对象中
    selection.addRange(range)
    // 执行copy命令，copy用户选择的文本
    document.execCommand('copy')
    // 将已选择的对象清除掉
    selection.removeAllRanges()
    var dataSize = $("#currentPageDataSize").val();
    layer.msg('共复制了'+dataSize+'条数据');
  }


/*
 * 表格查看详情方法
 * @Param selector:被点击元素
 */
function showDetail(selector){
	switchClassProperty($(selector).parents("tr").next(),'open',null);
	switchClassProperty($(selector).children(),'fa-angle-double-down','fa-angle-double-up');
	//
}

//点击常规条件查询按钮
function switchUsualQuery(){
	switchClassProperty($("#usualQueryDiv"),'hidden',null);
}

//点击高级查询按钮
function switchAdvancedQuery(){
	switchClassProperty($(".advancedQuery"),'hidden',null);
	switchClassProperty($("#angleDouble"),'fa-angle-double-down','fa-angle-double-up');
}

//点击更多功能按钮
function switchMoreFunction(){
	switchClassProperty($("#moreFunctionDiv"),'hidden',null);
}
//导出表格
function exportObj(url){
	var ids = [];
	if(url==null||url==""){
		url=$("#entityPath").val()+"/export";
	}

	 if($(".checkId:checked").length==0){
		 //如果没有勾选任何数据
	 }else{
		 //如果勾选了数据，优先导出勾选的数据
		 $(".checkId:checked").each(function(e){
			 ids.push($(this).val());
		 })
		 //redirectInvocation(url,{"ids":ids});
		 $("#idsInputHidden").val(ids);
	 }


	$('#parameterForm').first().attr("action",url).submit();

}
//打印表格，（单页展示表格）
function printTable(url){
	if(url==null||url==""){
		url=$("#entityPath").val()+"/table";
	}
	invocationToNewTab(url,$('#parameterForm').serialize());
}

//表格列排序
function sortTable(selector){
	//复原其他列图标
	$(".sorting,th").not($(selector).parents(".sorting,th")).each(function(){
		$(this).find("i").attr("class","fas fa-sort");
	})
	//切换正序倒序图标
	switchClassProperty($(selector).find("i"),'fa-sort-up','fa-sort-down');
	//获取排序关键字
	var sortkey = $(selector).closest("th.sorting").data("dql_name");
	//获取排序方式
	var iClassName = $(selector).find("i").prop("class");
	var sortord = '升序';
	if(iClassName.indexOf("down")!=-1)sortord='降序';

	$(selector).closest(".tableContainer").prev().find("#sortkey").val(sortkey);
	$(selector).closest(".tableContainer").prev().find("#sortord").val(sortord);

	loadTable($(selector).closest(".tableContainer").prev().prop("id"));

}

//切换表格列显示
function switchField(selector){
	var fieldId = $(selector).data("id");
	if($(selector)[0].checked){
		//console.log($(selector).val());
		$("."+$(selector).val()).removeClass("hidden")

		$.post("/una/sys/userField/delete",{"fieldId":fieldId},function(){},'json');
	}else{
		$("."+$(selector).val()).addClass("hidden")
		$.post("/una/sys/userField/save",{"fieldId":fieldId},function(){},'json');
	}

}

//加载表格时，自动切换表格列显示
$("#moreFunctionDiv").find(".fieldCheck").each(function(){
	if($(this).prop("checked")==""){
		$("."+$(this).attr("value")).addClass("hidden")
	}
})

//退出快速添加模式
function quitQuickOperation(){
	switchClassProperty($(".quick-operation"),"hidden");
	$("#quickOperationStatus").val(0);
}

//导入表格
function importObj(templateFileName){
	$("#importModalFormDiv").css("display","block");
	$("#importModalPreviewDiv").css("display","none");
	//$("#downloadTemplateBtn").attr("onclick","window.location.href='/document/"+templateFileName+".xlsx'")
	$("#importObjModal").modal("show");
}

//提交导入
function submitImport(selector){
	if(checkForm(selector)){
		$("#importModalFormDiv").css("display","none");
		$("#importModalPreviewDiv").css("display","block");
		var url=$(selector).closest("form").find("#saveUrlInForm").length>0?
				$(selector).closest("form").find("#saveUrlInForm").val():$("#entityPath").val()+"/import";
		//ajax保存数据，回调函数为showResponse
		$(selector).closest("form").ajaxSubmit({
            type: 'POST',
            url: url,
            success:showResponseReloadTable,
            dataType: 'json',
            error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
                console.log(XMLHttpRequest.readyState);
                console.log(textStatus);
	 }
        });
	}
}
