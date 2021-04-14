/*
     * ajax调用接口通用方法
     * type:post
     * 方法参数：url，id
     * 传参：id
     * 回调：showResponseReloadTable()
     */
    function ajaxInvocationById(url,id){
    		$.ajax({
                type: 'POST',  
                url: url,  
                success:showResponseReloadTable,
                dataType: 'json', 
                data:{"id":id},
                error: function(XMLHttpRequest, textStatus, errorThrown) {
    				console.log(XMLHttpRequest.status);
                    console.log(XMLHttpRequest.readyState);
                    console.log(textStatus);
    	        }
          	})
    		 
    }
    
    
    /*
     * ajax调用接口通用方法
     * type:post
     * 方法参数：url
     * 传参：paramForm
     * 回调：无
     */
    function ajaxInvocationByParam(url,parameterForm){
    	//console.log(parameterForm);
    	//var jsonParam = JSON.parse(parameterForm);
    	var jsonParam = eval("("+parameterForm+")")
    	//console.log(jsonParam);
    	$.ajax({
            type: 'POST',  
            url: url,
            success:showResponseReloadTable,
            dataType: 'json', 
            data:jsonParam,
            error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
                console.log(XMLHttpRequest.readyState);
                console.log(textStatus);
	        }
      	})
    	
    }
    
    
    /*
     * ajax调用接口通用方法
     * type:post
     * 方法参数：url
     * 传参：paramForm
     * 回调：无
     */
    /*function ajaxInvocationByParamFormNotHideModal(url,parameterForm){
    	$.ajax({
    		type: 'POST',  
    		url: url,
    		success:showResponseNoAction,
    		dataType: 'json', 
    		data:parameterForm,
    		error: function(XMLHttpRequest, textStatus, errorThrown) {
    			console.log(XMLHttpRequest.status);
    			console.log(XMLHttpRequest.readyState);
    			console.log(textStatus);
    		}
    	})
    	
    }*/
    
    
    /*
     * 页面跳转调用接口通用方法
     * type:post
     * 方法参数：url
     * 传参：paramForm
     * 回调：无
     */
    function redirectInvocation(url,parameterForm){
		//调用
		window.location.href=url+"?"+parameterForm;
    	
    }
    
    
    /*
     * 页面跳转调用接口通用方法
     * type:post
     * 方法参数：url
     * 传参：paramForm
     * 回调：无
     */
  //点击更多功能按钮
	function invocationToNewTab(url,parameterForm){
		window.open(url+"?openMode=window&"+parameterForm,"_blank"); 
	}