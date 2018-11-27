<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=path%>/js/wangEditor.min.js"></script>
<script src="<%=path %>/js/jquery-2.1.4.min.js"></script>
<script src="<%=path %>/js/echarts.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
	.item{ height:120px; position:relative;width:100%}
	<%-- .item:after {content:''; position:absolute;backgrount:red; top:50%; right:10px; width:44px; height:44px; margin-top:-22px; background:url(<%=path %>/js/aa.png) 0 0 no-repeat;} --%>
</style>
</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3 col-md-offset-1">
				<div style="height:80px;width:100%;text-align:center;line-height:80px;margin-top:20px"><h1>DGUT光伏监控系统</h1></div>
			</div>
		</div>
		
	</div>
	<hr style="height:10px;border:none;border-top:10px groove skyblue;" />
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">总发电量</div>
					<div class="panel-body ">
						<div class="container item">
							<div style="width:160px;height:60px;line-height:40px;margin-top:14%;text-align:center;"><h2>30,008 KWH</h2></div>
							<div style="position:absolute;background:red; top:30%; right:10px;height:44px; width:44px;background:url(<%=path %>/js/aa.png) 0 0 no-repeat; background-position:0 -44px;"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">日发电量</div>
					<div class="panel-body">
						<div class="container item">
							<div style="width:160px;height:60px;line-height:40px;margin-top:14%;text-align:center;"><h2>30.58 KWH</h2></div>
							<div style="position:absolute;background:red; top:30%; right:10px;height:44px; width:44px;background:url(<%=path %>/js/aa.png) 0 0 no-repeat; background-position:-44px 0;"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">节能减排</div>
					<div class="panel-body">
						<div class="container item">
							<div style="width:160px;height:60px;line-height:40px;margin-top:14%;text-align:center;"><h2>30.58 Kt</h2></div>
							<div style="position:absolute;background:red; top:30%; right:10px;height:44px; width:44px;background:url(<%=path %>/js/pic.png) 0 0 no-repeat; background-position:-44px -44px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container" style="height:600px;">
		<ul class="nav nav-tabs nav-justified">
			<li role="presentation" class="active"><a href="#home" data-toggle="tab">功率曲线</a></li>
  			<li role="presentation"><a href="#monthbar" data-toggle="tab">月发电量</a></li>
  			
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="home">
				<div id="daypower" style="height:500px;width:100%"></div>
			</div>
			<div class="tab-pane fade" id="monthbar">
				<div id="monthenergy" style="height:500px;width:1140px"></div>
			</div>
			
		</div>
	</div>
</body>
<script>
	var date = [];
	var data = [];
	for (var i = 0; i < 25; i++) {
	    var xtime = i>10?i:('0'+i);
	    date.push(xtime);
	    if(i<7 || i > 19){
	        data.push(0)
	    }else{
	        var ydata = -(i - 12)*(i-12) + 144;
	        data.push(Math.random()*ydata)
	    }
	    
	}
	var option = {
	    tooltip: {
	        trigger: 'axis',
	        position: function (pt) {
	            return [pt[0], '10%'];
	        }
	    },
	    title: {
	        left: 'center',
	        text: '日发电量功率曲线',
	    },
	    toolbox: {
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data: date
	    },
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, '100%']
	    },
	    dataZoom: [{
	        type: 'inside',
	        start: 0,
	        end: 100
	    }, {
	        start: 0,
	        end: 10,
	        handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
	        handleSize: '80%',
	        handleStyle: {
	            color: '#fff',
	            shadowBlur: 3,
	            shadowColor: 'rgba(0, 0, 0, 0.6)',
	            shadowOffsetX: 2,
	            shadowOffsetY: 2
	        }
	    }],
	    series: [
	        {
	            name:'功率',
	            type:'line',
	            smooth:true,
	            symbol: 'none',
	            sampling: 'average',
	            itemStyle: {
	                color: 'rgb(255, 70, 131)'
	            },
	            areaStyle: {
	                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                    offset: 0,
	                    color: 'rgb(255, 158, 68)'
	                }, {
	                    offset: 1,
	                    color: 'rgb(255, 70, 131)'
	                }])
	            },
	            data: data
	        }
	    ]
	};
		   
	var myChart = echarts.init(document.getElementById('daypower'));
	myChart.setOption(option);   
	var option2 = {
	    title : {
	        text: '当月发电量',
	        subtext: '可选子标题'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['发电量']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            data : ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'蒸发量',
	            type:'bar',
	            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0,2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0,25.6],
	            markPoint : {
	                data : [
	                    {type : 'max', name: '最大值'},
	                    {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            }
	        }
	        
	    ]
	};
	
	
	var monthenergy = echarts.init(document.getElementById('monthenergy'));
	monthenergy.setOption(option2);   
   
</script>
</html>
