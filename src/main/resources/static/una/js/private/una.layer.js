/*
 * 关闭最新打开的layer弹窗
 */
function closeLatestLayer(){
	layer.close(layer.index); 
}


/*
 * 关闭所有layer弹窗
 * @Param layerType:要关闭的layer类型，若为空，则关闭所有
 * 类型值：dialog/page/iframe/loading/tips
 */
function closeAllLayer(layerType){
	layer.closeAll(layerType); //疯狂模式，关闭所有层
}



/*
 * iframe内调用，关闭所在iframe的弹窗
 * @Param selector:数据所在表单内的元素，如“关闭”按钮等
 */
function closeIframeLayerFromInner(){
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index); //再执行关闭 
}

/*
 * iframe内调用，关闭所在iframe所在父页面的弹窗
 * @Param selector:数据所在表单内的元素，如“关闭”按钮等
 */
function closeAllIframeLayerFromInner(layerType){
	parent.layer.closeAll(layerType); 
}