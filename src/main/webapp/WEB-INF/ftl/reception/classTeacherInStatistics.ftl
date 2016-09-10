<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<title>学生管理系统</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
		<meta name="author" content="Muhammad Usman">
		<link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">
		<link href="css/charisma-app.css" rel="stylesheet">
		<link rel="shortcut icon" href="img/favicon.ico">

		<!-- 基本的文件 -->
		<script src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
		<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
		<script src="http://cdn.hcharts.cn/highcharts/modules/exporting.js"></script>
		<!-- 插件文件 <script src="js/export-csv.js"></script>-->
		
		<script type="text/javascript">
			var chart;
			$(function() {
				chart = new Highcharts.Chart({
					chart: {
						renderTo:'divChart',
						type: 'column',
						inverted: true
					},
					navigation: {
						menuItemStyle: {
							padding: '6px 14px'
						}
					},
					exporting: {
        				filename: '数据'
    				},
					title: {
						text: '等待数据中。。。',
						style: {
							color: "#0000"
						}
					},

					subtitle: {
						text: ''
					},
					xAxis: {
						categories:[]
					},
					yAxis: {
						min: 0,
						title: {
							text: '统计显示'
						}
					},
					tooltip: {
						pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
						shared: true,

					},
					plotOptions: {
						column: {
							stacking: 'percent'
						},
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
						},
						series: {  
            				cursor: 'pointer',  
            				events: {  
                				click: function(e) {  
                    				alert(e.point.category);
                				}  
                			}
            			}  
					},
					series: [{
						name: '正常',
						data: []
					}, {
						name: '早退',
						data: []
					}, {
						name: '迟到',
						data: []
					}, {
						name: '旷课',
						data: []
					}]
				});
				var departmentId = "${departmentId!}";
				if(departmentId!=""){
					$("#departmentId").find("option[value="+departmentId+"]").attr("selected",true);
				}
				getData();
				
			});
			
			//获取数据
			function getData(){  
				chart.showLoading();
        		var categories = [];
        		var zhengChang =[];
        		var zaoTui =[];
        		var chiDao =[];
        		var kuangKe =[];
        		var startDate;
        		var endDate;
        		$.ajax({
	            	type : "POST",
	            	url : "getDIClassTeacherInStatistics",
	            	dataType : "json",
	            	success : function(result) {
	            		$(result).each(function(n,item){
        					categories.push(item.sysUserName);
        					zhengChang.push(item.zhengChang);
        					zaoTui.push(item.zaoTui);
        					chiDao.push(item.chiDao);
        					kuangKe.push(item.kuangKe);
        					startDate = item.startTime;
        					endDate = item.endTime;
        					var classCount = "<tr><td>"+item.sysUserName+"</td><td>"+item.zhengChang+"</td><td>"+item.chiDao+"</td><td>"+item.zaoTui+"</td><td>"+item.kuangKe+"</td></tr>";
        					$("#content").append(classCount);
       					});
       					$("#startDate").attr("value",startDate);
       					$("#endDate").attr("value",endDate);
       					
       					chart.xAxis[0].setCategories(categories);
       					chart.series[0].setData(zhengChang);
       					chart.series[1].setData(zaoTui);
       					chart.series[2].setData(chiDao);
       					chart.series[3].setData(kuangKe);
       					var diName = $("#departmentId option:selected").text();
       					$("#diName").html(diName);
       					$("#h2").html(diName+"任课教师上课汇总表");
       					var te = startDate+"到"+endDate+"任课教师上课到勤统计百分比";
       					$("#h5").html(startDate+"到"+endDate);
       					var subTitle = {
    						text:te,
						};
       					var title = {
    						text:diName,
						};
       					chart.setTitle(title,subTitle);
       					chart.hideLoading();
					}
					
				});
        	}
        	var container;
			$(function() {
					container = new Highcharts.Chart({
						chart: {
							plotBackgroundColor: null,
							renderTo:'container',
							plotBorderWidth: null,
							plotShadow: false
						},
						title: {
							text: '等待数据中。。。'
						},
						tooltip: {
							pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
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
								},
								showInLegend: true//是否显示图例
							}
						},
						series: [{
							type: 'pie',
							name: '百分比',
							data: [
								['正常', 0],
								['早退', 0],
								['迟到', 0],
								{
									name: '旷课',
									y: 0,
									sliced: true,
									selected: true
								}
							]
						}]
					});
					getClassTotalData();
				});
			//获取数据
			function getClassTotalData(){  
				container.showLoading();
        		var zhengChang =[];
        		var zaoTui =[];
        		var chiDao =[];
        		var kuangKe =[];
        		$.ajax({
	            	type : "POST",
	            	url : "getDIClassTeacherInStatistics?total=1",
	            	dataType : "json",
	            	success : function(result) {
	            		var data = [['正常',result.zhengChang],['早退', result.zaoTui],['迟到', result.chiDao],{
										name: '旷课',
										y: result.kuangKe,
										sliced: true,
										selected: true
									}
								];
       					container.series[0].setData(data);
       					var diName = $("#departmentId option:selected").text();
       					var title = {
    						text:diName+"任课老师考勤总百分比",
						};
       					container.setTitle(title);
       					container.hideLoading();
					}
					
				});
        	}  
		</script>
		<script src="js/laydate/laydate.js"></script>
		<script type="text/javascript">
        	function getTeacherCount(){
        		var startDate = $("#startDate").val();  
        		var endDate = $("#endDate").val(); 
        		var departmentId = $("#departmentId").val();
        		if(startDate=='' || endDate==''){
        				alert("开始结束日期都不能为空");
        		}else{
        			window.location.href= "classTeacherInStatistics?departmentId="+departmentId+"&startTime="+startDate+"&endTime="+endDate;
        		}
        	}
        </script>
	</head>

	<body>
		<div class="ch-container">
			<div class="row">
				<ul class="breadcrumb" style="color:#317eac;">
					<li>
						首页
					</li>
					<li>
						上课统计
					</li>
				</ul>
			</div>
			<div class="row">
				<div class="box col-md-12">
					<div class="box-inner">
						<div class="box-header well" data-original-title="">
							<h2><i class="glyphicon glyphicon-edit"></i><b id="diName"></b>任课老师上课到勤统计</h2>
						</div>
						<div class="box-content">
								<div class="form-group">
									
				            		<select name="departmentId" id="departmentId" class="btn btn-default btn-sm" onchange="">
				            			<#if departmentInfos??>  
						         			<#list departmentInfos as d>  
						         	 			<option value="${d.departmentId}">${d.deprtmentName}</option>  
						         			</#list>  
					        			</#if>  
				            		</select>　　
				            		 开始时间：<input name="startTime" id="startDate" placeholder="请输入日期" class="btn laydate-icon">
				            		  结束时间：<input name="endTime" id="endDate" placeholder="请输入日期" class="btn laydate-icon">
				            		<button type="button" class="btn btn-primary btn-sm" onclick="getTeacherCount()">查询</button>
				            		<button type="button" class="btn btn-default btn-sm" onclick="history.go(-1)">返回</button>
								</div>
								<div class="controls">
								<table class="table table-striped table-bordered bootstrap-datatable datatable responsive" id="table2">
									<caption>
										<h2 id="h2"></h2></caption>
									<caption>
										<h5 id="h5"></h5></caption>
									<thead>
										<tr>
											<th rowspan="2" style="vertical-align:middle; text-align:center;">任课老师</th>
											<th colspan="4" style="vertical-align:middle; text-align:center;">合计</th>
										</tr>
										<tr>
											<th style="vertical-align:middle; text-align:center;">签到次数</th>
											<th style="vertical-align:middle; text-align:center;">迟到（次）</th>
											<th style="vertical-align:middle; text-align:center;">早退（次）</th>
											<th style="vertical-align:middle; text-align:center;">旷课（节）</th>
										</tr>
									</thead>
									<tbody id="content">
									</tbody>
								</table>
							</div>
							<div id="divChart" style="min-width:400px;height:400px"></div>
							<div id="container" style="min-width:400px;height:400px"></div>
						</div>
					</div>
				</div>
				<!--/span-->

			</div>
			<!--/row-->
			
		</div>
	</body>
	<script>
			Highcharts.setOptions({
				lang: {
					printChart: '打印图表',
					downloadJPEG: '下载 JPEG 文件',
					downloadPDF: '下载 PDF   文件',
					downloadPNG: '下载 PNG  文件',
					downloadSVG: '下载 SVG  文件',
					downloadCSV: '下载 CSV  文件',
					downloadXLS: '下载 XLS   文件',
					loading: '数据载入中...' //设置载入动画的提示内容
				},
				//去掉右下链接
				credits: {
					text: 'HCharts.cn',
					href: 'http://www.hcharts.cn',
					enabled: false //设置隐藏版权信息
				}
			});
			var start = {
    			elem: '#startDate',
    			format: 'YYYY-MM-DD',
    			min: laydate.now(-356),
    			//设定最小日期为当前日期
    			max: laydate.now(),
    			//最大日期
    			istime: true,
    			istoday: false,
    			choose: function(datas) {
        			end.min = datas; //开始日选好后，重置结束日的最小日期
        			end.start = datas //将结束日的初始值设定为开始日
    			}
			};
			var end = {
    			elem: '#endDate',
    			format: 'YYYY-MM-DD',
    			min: laydate.now(-365),
    			max: laydate.now(),//不能大于当前天
    			istime: true,
    			istoday: false,
    			choose: function(datas) {
        			start.max = datas; //结束日选好后，重置开始日的最大日期
    			}
			};
			laydate(start);
			laydate(end);
	</script>
</html>