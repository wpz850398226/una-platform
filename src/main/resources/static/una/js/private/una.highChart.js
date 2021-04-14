//加载饼图
function drawPieChart(id,pieData,title,subTitle){
	if(title==null)title="";
	if(subTitle==null)subTitle="";
	 $('#'+id).highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
	        title: {
	            text: title
	        },
	        subtitle: {
		        text: subTitle
		    },
	        tooltip: {
	            pointFormat: '{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	    		pie: {
	    			allowPointSelect: true,
	    			cursor: 'pointer',
	    			dataLabels: {
	    				enabled: true,
	    				format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	    				style: {
	    					color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	    				}
	    			}
	    		}
	    	},
	        series: [{
	            type: 'pie',
	            data: pieData
	            
	        }],
	        credits: {  
	            enabled: false     //不显示LOGO 
	        }  
	    });
 }


//加载柱状图
function drawColumnChart(id,categoriesData,seriesData,unit,title,subTitle){
	if(title==null)title="";
	if(subTitle==null)subTitle="";
	if(unit==null)unit="个数";
	$('#'+id).highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type:'column',
        },
        title: {
            text: title
        },
        subtitle: {
	        text: subTitle
	    },
        xAxis: {
    		categories: categoriesData,
    		crosshair: true
    	},
    	yAxis: {
	        title: {
	            text: unit
	        }
	    },
        tooltip: {
    		// head + 每个 point + footer 拼接成完整的 table
    		headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
    		pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
    		'<td style="padding:0"><b>{point.y}</b></td></tr>',
    		footerFormat: '</table>',
    		shared: true,
    		useHTML: true
    	},
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series:seriesData,
        credits: {  
            enabled: false     //不显示LOGO 
        }  
    });
	
}


//加载柱状图
function drawLineChart(id,categoriesData,seriesData,unit,title,subTitle){
	if(title==null)title="";
	if(subTitle==null)subTitle="";
	if(unit==null)unit="个数";
	$('#'+id).highcharts({
		title: {
			text: title
		},
		subtitle: {
			text: subTitle
		},
		xAxis: {
			categories: categoriesData,
			crosshair: true
		},
		yAxis: {
			title: {
				text: unit
			}
		},
		tooltip: {
			// head + 每个 point + footer 拼接成完整的 table
			headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			'<td style="padding:0"><b>{point.y}</b></td></tr>',
			footerFormat: '</table>',
			shared: true,
			useHTML: true
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: false
				},
				showInLegend: true
			}
		},
		series:seriesData,
		credits: {  
			enabled: false     //不显示LOGO 
		}  
	});
	
}


//加载面积图
function drawAreaChart(id,categoriesData,seriesData,unit,title,subTitle){
	if(title==null)title="";
	if(subTitle==null)subTitle="";
	if(unit==null)unit="个数";
	$('#'+id).highcharts({
			chart: {
		        type: 'area'
		    },
		    title: {
		        text: title
		    },
		    subtitle: {
		        text: subTitle
		    },
		    xAxis: {
		        categories: categoriesData
		    },
		    yAxis: {
		        title: {
		            text: '个数'
		        }
		    },
		    plotOptions: {
		        line: {
		            dataLabels: {
		                enabled: true
		            },
		            enableMouseTracking: false
		        }
		    },
		    series: seriesData 
		});	
	
}