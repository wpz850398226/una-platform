
//移除图片
function removePicture(selector) {
	$(selector).closest("li").remove();
}

//图片升序
function increasePictureOrder(selector) {
	$(selector).closest("li").prev("li").before($(selector).closest("li"));
}


	function loadKindEditor(){
		 window.K = KindEditor;
			K.create('.kindeditor', {
		        cssPath : '/resources/kindeditor/plugins/code/prettify.css',
		        fileManagerJson : '/resources/kindeditor/jsp/file_manager_json.jsp',
		        fillterMode:true,
		        width:'100%',
		        height : "500px",
		        allowFileManager : true,
		        allowImageUpload:true,
		        filePostName: 'editorUpload',
		        afterCreate : function() {
		            var self = this;
		            K.ctrl(document, 13, function() {
		                self.sync();
		                document.forms['forms'].submit();
		            });
		            K.ctrl(self.edit.doc, 13, function() {
		                self.sync();
		                document.forms['forms'].submit();
		            });
		        },
		        uploadJson : '/una/sys/upload',
		        afterUpload: function(){
		            this.sync();

		            }, //图片上传后，将上传内容同步到textarea中
		        afterBlur: function(){
		            this.sync();
		            },   ////失去焦点时，将上传内容同步到textarea中
		        htmlTags:{},
		        items : [//工具栏
		        	 'source', '|', 
		        	'undo', 'redo', '|', 
		        	'preview', 'print', 'template', 'code','cut','copy', 'paste',  'plainpaste', 'wordpaste', '|', 
		        	'justifyleft', 'justifycenter','justifyright', 'justifyfull', 'insertorderedlist', 
		        	'insertunorderedlist', 'indent','outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|',
		        	'fullscreen', 'formatblock', 'fontname', 'fontsize', '|', 
		        	'forecolor','hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight','removeformat', '|', 
		        	'image', 'multiimage', 'flash', 'media', 'insertfile', 'table','hr', 'emoticons', 'baidumap', 'pagebreak', 'anchor', 'link', 'unlink', '|', 
		        	'about'
		        ],
		    });
			
		}
	
	//加载提交非空的checkbox，选中为checkbox的value值，不选中为value的二级制倒数
	function loadCheckBoxNotNull (){
		$("input[type='checkbox']").each(function(){
			var checkboxVal = $(this).val()
				,antonym = $(this).data("antonym")
				,name = $(this).prop("name");
			if(antonym==null||antonym==""){
				switch(checkboxVal) {
					case '1':
						antonym = '0';
						break;
					case '是':
						antonym = '否';
						break;
				}
			}
			$(this).before('<input type="hidden" value="'+antonym+'" name="'+name+'" >');
		})
	}


/********************************************
 * 以下为una2.0新增方法
 *
 * ***********************************/


$("[lay-verify='required']").parent().prev().prepend("<span style='color: red'>*</span>");