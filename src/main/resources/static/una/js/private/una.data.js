//增加数据（打开增加表单）
function addObj(i){
	var checkStatus = true;
	//如果需要校验，则先校验是否可以添加
	if($("#whetherAddCheck").length>0){
		checkStatus = eval($("#whetherAddCheck").val());
	}

	if(checkStatus){
		//如果树结构模态框处于展示状态，则隐藏树结构模态框
		if(i==1){
			switchClassProperty($(".quick-operation"),"hidden");
			switchValue($("#quickOperationStatus"),0,1);
		}else{

		if($("#treeModal").length>0){
			if($("#treeModal").prop("class").indexOf("in")!=-1)$("#treeModalSwitchBtn").click();
		}

			var index = layer.open({
			      type: 2,
			      title: '新增'+$("#entityName").val(),
			      maxmin: true,
			      shadeClose: true, //点击遮罩关闭层
			      area : ['800px' , '520px'],
			      content: $("#entityPath").val()+"/form?"+$("#treeDataId").attr("name")+"="+$("#treeDataId").val()
			    });
			if($("#whetherFullForm").val().trim()=="是")layer.full(index);
		}

    }
}


//增加数据（打开增加表单）
function addObjWithParam(path,title){
	var checkStatus = true;
	//如果需要校验，则先校验是否可以添加
	if($("#whetherAddCheck").length>0){
		checkStatus = eval($("#whetherAddCheck").val());
	}

	if(checkStatus){
		//如果树结构模态框处于展示状态，则隐藏树结构模态框

			if($("#treeModal").length>0){
				if($("#treeModal").prop("class").indexOf("in")!=-1)$("#treeModalSwitchBtn").click();
			}
			$(".modal").modal("hide");

			//$("#saveObjModal").modal("show");
			layer.open({
			      type: 2,
			      title: title,
			      maxmin: true,
			      shadeClose: true, //点击遮罩关闭层
			      area : ['800px' , '520px'],
			      content: path+'/form'
			    });
		}


}

//删除数据
    			function deleteObj(id){
					layer.confirm('您确定要删除吗？', {
						  btn: ['确认','取消'] //按钮
					}, function(){
						$.ajax({
							type: 'POST',
							url: $("#entityPath").val()+"/delete",
							success:function(result){
								showResponseNoAction(result);
								return result.code;
							},
							dataType: 'json',
							data:{"ids":id},
							error: function(XMLHttpRequest, textStatus, errorThrown) {
								console.log(XMLHttpRequest.status);
								console.log(XMLHttpRequest.readyState);
								console.log(textStatus);
					        }
					   })
					})
		        }


    			//批量删除
    			 function deleteObjInBatch() {
    				 var ids = [];
    				 $(".checkId:checked").each(function(e){
    					 ids.push($(this).val());
    				 })
    				 if($(".checkId:checked").length==0){
    					 layer.msg('请选择您要操作的数据', function(){});
    				 }else{
						 layer.confirm('您确定要删除共' + $(".checkId:checked").length + '条数据吗？', {
							  btn: ['确认','取消'] //按钮
							}, function(){
								$.ajax({
    	 	                        type: 'POST',
    	 	    		            url: $("#entityPath").val()+"/delete",
    	 	    		            success:showResponseReloadTable,
    	 	    		            dataType: 'json',
    	 	    		            data:{"ids":ids},
    	 	    		            traditional: true,
    	 	    		            error: function(XMLHttpRequest, textStatus, errorThrown) {
    									console.log(XMLHttpRequest.status);
    					                console.log(XMLHttpRequest.readyState);
    					                console.log(textStatus);
    						        }
    	 	                  })
						})
    				 }


    			 }

    			//修改数据（模态框）
    			function updateObj(id,path,title){
    				if(path==null)path=$("#entityPath").val();
    				if(title==null)title='修改'+$("#entityName").val();
    				//如果树结构模态框处于展示状态，则隐藏树结构模态框
    				if($("#treeModal").length>0){
    					if($("#treeModal").prop("class").indexOf("in")!=-1)$("#treeModalSwitchBtn").click();
    				}

    				var index =layer.open({
    				      type: 2,
    				      title: title,
    				      maxmin: true,
    				      shadeClose: true, //点击遮罩关闭层
    				      area : ['800px' , '520px'],
    				      content: path+"/form?id="+id
    				    });

    				if($("#whetherFullForm").val().trim()=="是")layer.full(index);

    			}

    			//批量修改
	   			 function updateObjInBatch(path,title) {
	   				if(path==null)path=$("#entityPath").val();
    				if(title==null)title='修改'+$("#entityName").val();
    				//如果树结构模态框处于展示状态，则隐藏树结构模态框
    				if($("#treeModal").length>0){
    					if($("#treeModal").prop("class").indexOf("in")!=-1)$("#treeModalSwitchBtn").click();
    				}

	   				 var ids = [];
	   				 $(".checkId:checked").each(function(e){
	   					 ids.push($(this).val());
	   				 })
	   				 if($(".checkId:checked").length==0){
	   					layer.msg('请选择您要操作的数据', function(){});
	   				 }else{
	   					layer.open({
	    				      type: 2,
	    				      title: title,
	    				      maxmin: true,
	    				      shadeClose: true, //点击遮罩关闭层
	    				      area : ['800px' , '520px'],
	    				      content: path+"/form?ids="+ids
	    				    });
	   				 }


	   			 }

	   			 //批量已读
                 function readObjInBatch() {
	                 var ids = [];
	                 $(".checkId:checked").each(function(e){
		                 ids.push($(this).val());
	                 })
	                 if($(".checkId:checked").length==0){
		                 layer.msg('请选择您要操作的数据', function(){});
	                 }else{
	                 	layer.confirm('您确定要标注已读共' + $(".checkId:checked").length + '条数据吗？', {
	                 		btn: ['确认','取消'] //按钮
						}, function(){
			                $.ajax({
								type: 'POST',
								url: $("#entityPath").val()+"/read",
								success:showResponseReloadTable,
				                dataType: 'json',
				                data:{"ids":ids},
				                traditional: true,
								error: function(XMLHttpRequest, textStatus, errorThrown) {
					               console.log(XMLHttpRequest.status);
					               console.log(XMLHttpRequest.readyState);
					               console.log(textStatus);
				                }
			                })
		                })
	                 }


                  }


    			/*
    			 * 查询表格数据
    			 * @Param queryUrl:查询地址
    			 * @Param parameterFormId：参数表单id
    			 * @Param tableContainerId：加载表格容器id
    			 * （为满足同一页面内加载多张表格设计，每个表自由一套分页控制）
    			 */
    			function queryObj(queryUrl,parameterForm,tableContainer) {
    	        	$.ajax({url: queryUrl, // 提交地址，该方法能够跳转到一个页面
    					        global: false,
    					        type: "POST",
    					 		data: parameterForm.serialize(),
    					        dataType: "html",
    					        async: true,
    					        success: function(msg) {
    								layer.close(layer.index);//关闭加载层
    					            tableContainer.html(msg);
    					        },
    					        error: function(XMLHttpRequest, textStatus, errorThrown) {
    								console.log(XMLHttpRequest.status);
    				                console.log(XMLHttpRequest.readyState);
    				                console.log(textStatus);
    					        }
    					});
    			}


/*
 * 保存数据，之后自动刷新表格或页面
 * @Param selector:数据所在表单内的元素，如“确定”按钮，“提交”按钮等
 * @Param responseType:保存成功后的操作：0为无操作，1为刷新表格，2为刷新页面；默认为1
 */
 function saveObj (selector){
	 //$(selector).closest("form").bootstrapv;


	 var responseType = $("#showResponseType").val();
	 var href = $("#showResponseHref").val();
	 var target = $("#showResponseTarget").val();
	 	//检测表单格式验证结果
    	if(checkForm(selector)){
    		//加载层
			var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
    		$(this).focus();
    		//如果要保存的表单内有“saveUrlForm”元素，则保存地址为该id的val值，否则保存地址为实体前缀+“save"
    		var url=$(selector).closest("form").find("#saveUrlInForm").length>0?
    				$(selector).closest("form").find("#saveUrlInForm").val():"save";
    		//ajax保存数据
			console.log("响应类型："+responseType)
    		$(selector).closest("form").ajaxSubmit({
	            type: 'POST',
	            url: url,
	            success:function(result){
	            	console.log("响应类型："+responseType)
					switch(responseType){
						case "0":
							showResponseNoAction(result)
							break;
						case "1":
							showResponseReloadTable(result)
							break;
						case "2":
							showResponseReload(result)
							break;
						case "3":
							showResponseCloseIframe(result)
							break;
						case "00":
							window.parent.showResponseNoAction(result)
							break;
						case "01":
							window.parent.showResponseReloadTable(result)
							break;
						case "02":
							window.parent.showResponseReload(result)
							break;
						case "03":
							window.parent.showResponseCloseIframe(result)
							break;
						case "001":
							window.parent.parent.showResponseReloadTable(result)
							window.parent.showResponseReloadTable(result)
							break;
						default:
							window.parent.showResponseReloadTable(result)
							break;
					}
					if(result.isSuccess&&href!=null&&href!="")setTimeout('window.open("'+href+'","'+target+'")', 1000);

					/*if(result.isSuccess&&activateFlag==1){
						//如果是保存并提交，保存成功后打开处理代办页
						var taskId = result.data.taskId;
						var taskName = result.data.taskName;
						setTimeout("toHandle("+taskId+",'"+taskName+"')", 2000)
					}*/
				},
	            error: function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(XMLHttpRequest.status);
	                console.log(XMLHttpRequest.readyState);
	                console.log(textStatus);
		        }
	        });
    	}
 }

/**
 * 保存数据并提交流程
 * @param selector 按钮元素
 */
function submitObj(selector){
	var responseType = $("#showResponseType").val();
	var href = $("#showResponseHref").val();
	var target = $("#showResponseTarget").val();
	//检测表单格式验证结果
	if(checkForm(selector)){
		layer.confirm('您确定要保存数据并提交流程吗？', {
			btn: ['确认','取消'] //按钮
		}, function(){
			//加载层
			var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
			$(this).focus();
			//如果要保存的表单内有“saveUrlForm”元素，则保存地址为该id的val值，否则保存地址为实体前缀+“save"
			var url=$(selector).closest("form").find("#saveUrlInForm").length>0?
				$(selector).closest("form").find("#saveUrlInForm").val():"save";
			//ajax保存数据
			$(selector).closest("form").ajaxSubmit({
				type: 'POST',
				url: url,
				success:function(result){
					//console.log("响应类型："+responseType)
					switch(responseType){
						case "0":
							showResponseNoAction(result)
							break;
						case "1":
							showResponseReloadTable(result)
							break;
						case "2":
							showResponseReload(result)
							break;
						case "3":
							showResponseCloseIframe(result)
							break;
						case "00":
							window.parent.showResponseNoAction(result)
							break;
						case "01":
							window.parent.showResponseReloadTable(result)
							break;
						case "02":
							window.parent.showResponseReload(result)
							break;
						case "03":
							window.parent.showResponseCloseIframe(result)
							break;
						case "001":
							window.parent.parent.showResponseReloadTable(result)
							window.parent.showResponseReloadTable(result)
							break;
						default:
							window.parent.showResponseReloadTable(result)
							break;
					}
					if(result.isSuccess&&href!=null&&href!="")setTimeout('window.open("'+href+'","'+target+'")', 1000);

					/*if(result.isSuccess&&activateFlag==1){
                        //如果是保存并提交，保存成功后打开处理代办页
                        var taskId = result.data.taskId;
                        var taskName = result.data.taskName;
                        setTimeout("toHandle("+taskId+",'"+taskName+"')", 2000)
                    }*/
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(XMLHttpRequest.status);
					console.log(XMLHttpRequest.readyState);
					console.log(textStatus);
				}
			});
		})
	}
 }

//$.post(url,$(selector).closest("form").serialize(),showResponseReloadTable,'json');

 //升序
 function increaseOrder(id){
		ajaxInvocationById($("#entityPath").val()+'/increaseOrder',id);
	}
