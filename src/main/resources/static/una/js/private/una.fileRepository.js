$(".selectUnit.ace-file-container").click(function(){
	openFileRepository($(this).prop("id"));
})

previewImage();
//图片预览功能
function previewImage(){
	var $overflow = '';
	var colorbox_params = {
		rel: 'colorbox',
		reposition:true,
		scalePhotos:true,
		scrolling:false,
		previous:'<i class="ace-icon fa fa-arrow-left"></i>',
		next:'<i class="ace-icon fa fa-arrow-right"></i>',
		close:'&times;',
		current:'{current} of {total}',
		maxWidth:'100%',
		maxHeight:'100%',
		onOpen:function(){
			$overflow = document.body.style.overflow;
			document.body.style.overflow = 'hidden';
		},
		onClosed:function(){
			document.body.style.overflow = $overflow;
		},
		onComplete:function(){
			$.colorbox.resize();
		}
	};

	$("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange fa-spin'></i>");//let's add a custom loading icon


	$(document).one('ajaxloadstart.page', function(e) {
		$('#colorbox, #cboxOverlay').remove();
	});
}

//去打开文件库
function toOpenFileRepository(filePlaceId,fileSubmitName,fileTypeName,limitFileNum){
	var checkedFileNum = $("#"+filePlaceId).find("img").length;
	openFileRepository(filePlaceId,fileSubmitName,fileTypeName,limitFileNum-checkedFileNum);
}

//打开文件库
function openFileRepository(filePlaceId,fileSubmitName,fileTypeName,fileNum){
	if(fileNum==null)fileNum=1;
	if(fileTypeName==null)fileTypeName="图片";
	layer.open({
		type: 2,
		title: '文件库',
		//maxmin: true,
		shadeClose: true, //点击遮罩关闭层
		area : ['1000px' , '600px'],
		content: '/una/sys/file/repositoryManage?filePlaceId='+filePlaceId+'&fileSubmitName='+fileSubmitName+'&fileTypeName='+fileTypeName+'&fileNum='+fileNum
	});
}

//取消文件选择
function unselectFile(selector){
	$(selector).prev().removeClass("hide-placeholder selected").find("img").remove();
	$(selector).prev().find("input").val("0");
}