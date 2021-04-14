/**
 * 响应方法
 * 关闭表单页，并重新加载表格
 */
function showResponseReloadTable(result){
	console.log("showResponseReloadTable")
	layer.close(layer.index);//关闭加载层
	$(":file").each(function(){
		$(this).after($(this).clone().val(""));
		$(this).remove();
	})
	if(result.code == 200){
		//关闭iframe弹窗
		layer.closeAll("iframe");
		setTimeout('toLoadTable()', 500);
		layer.msg(result.message);

	} else {
		layer.msg(result.message+"111111", {icon: 2});
		$("#importModalFormDiv").css("display","block");
		$("#importModalPreviewDiv").css("display","none");
	}
}

/**
 * 响应方法
 * 关闭表单页
 */
function showResponseCloseIframe(result){
	console.log("showResponseCloseIframe")
	layer.close(layer.index);//关闭加载层
	if(result.code == 200){
		//关闭iframe弹窗
		layer.closeAll("iframe");
		layer.msg(result.message);
	} else {
		layer.msg(result.message+"3333333333", {icon: 2});
	}
}

/**
 * 响应方法
 * 刷新当前页，不关闭
 */
function showResponseReload(result){
	console.log("showResponseReload")
	layer.close(layer.index);//关闭加载层
	if(result.code == 200){
		//关闭iframe弹窗
		layer.closeAll("iframe");
		layer.msg(result.message+",页面即将刷新");
		setTimeout('window.location.reload()', 500);

	} else {
		layer.msg(result.message+"44444444", {icon: 2});
	}
}


/**
 * 响应方法
 * 无动作
 */
function showResponseNoAction(result){
	console.log("showResponseNoAction")
	layer.closeAll('loading'); //关闭加载层
	layer.msg(result.message+"222222222");
}
