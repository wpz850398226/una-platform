
	/**
	 * 加载普通日期选择器，中文
	 */
	$(".una-laydate-date").each(function(){
		layui.laydate.render({
			elem: this
		});
	})

	/**
	 * 加载日期时间选择器，中文
	 */
	$(".una-laydate-datetime").each(function(){
		layui.laydate.render({
			elem: this
			,type: 'datetime'
		});
	})

	/**
	 * 加载日期时间选择器，中文
	 */
	$(".una-laydate-time").each(function(){
		layui.laydate.render({
			elem: this
			,type: 'time'
		});
	})

	/**
	 * 加载年选择器，中文
	 */
	$(".una-laydate-year").each(function(){
		layui.laydate.render({
			elem: this
			,type: 'year'
		});
	})

	/**
	 * 加载月选择器，中文
	 */
	$(".una-laydate-month").each(function(){
		layui.laydate.render({
			elem: this
			,type: 'month'
		});
	})