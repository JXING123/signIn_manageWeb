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
		<link href="css/load.css" rel="stylesheet">
		<link rel="shortcut icon" href="img/favicon.ico">

		<!-- 基本的文件 -->
		<script src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
		<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
		<script src="http://cdn.hcharts.cn/highcharts/modules/exporting.js"></script>
		<!-- 插件文件 -->
		<script src="http://cdn.hcharts.cn/libs/highcharts-export-csv/export-csv.js"></script>
		<script type="text/javascript">
			var chart;
			$(function() {
				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'divChart',
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
						text: '',
						style: {
							color: "#0000"
						}
					},

					subtitle: {
						text: '百分比'
					},
					xAxis: {
						categories: []
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
									//var startDate = $("#startDate").val();
									//var endDate = $("#endDate").val();
									//window.location.href = "classAttendanceStatistics?departmentName=" +e.point.category + "&startTime=" + startDate + "&endTime=" + endDate;
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
				var categories = [];
				var zhengChang = [];
				var zaoTui = [];
				var chiDao = [];
				var kuangKe = [];
				var startDate;
				var endDate;
				<#if sysUserCountList??>
					<#list sysUserCountList as map>
						categories.push("${map.sysUserName}");
						zhengChang.push(${map.qianDaoChiShu-map.zaoTui-map.chiDao});
						zaoTui.push(${map.zaoTui});
						chiDao.push(${map.chiDao});
						kuangKe.push(${map.kuangKe});	
						startDate = "${map.startTime}";
						endDate = "${map.endTime}";
					</#list>
				</#if>
				chart.xAxis[0].setCategories(categories);
				chart.series[0].setData(zhengChang);
				chart.series[1].setData(zaoTui);
				chart.series[2].setData(chiDao);
				chart.series[3].setData(kuangKe);
				$("#startDate").attr("value", startDate);
				$("#diName").html("${schoolName!}"+"${courseName}");
				$("#endDate").attr("value", endDate);		
				$("#h5").html(startDate + "到" + endDate);
				var subTitle = {
					text: startDate + "到" + endDate,
				};
				
				var title = {
							text: "${schoolName!}" +"${subject.subjectName!}"+ "老师上课到勤统计",
						};
				chart.setTitle(title, subTitle);
				var subjectId = "${subject.subjectId!}";
				$("#subjectId").find("option[value="+subjectId+"]").attr("selected",true);
			});

			
		</script>
		<script src="js/laydate/laydate.js"></script>
		<script type="text/javascript">
			function getCount() {
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
				var diName = $("#subjectId option:selected").text();
				if (startDate == '' || endDate == '') {
					alert("开始结束日期都不能为空");
				} else {
					window.location.href = "findCourseTeacherPeriodOfTimeAttendance?startTime=" + startDate + "&endTime=" + endDate+"&courseName="+diName;
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
							<h2><i class="glyphicon glyphicon-edit"></i><b id="diName"></b>老师某个课程考勤统计</h2>
						</div>
						<div class="box-content">
							<div class="form-group">
								<select name="subjectName" id="subjectId" class="btn btn-default btn-sm">
				            			<#if selectAllSubject??>  
						         			<#list selectAllSubject as s>  
						         	 			<option value="${s.subjectId}">${s.subjectName}</option>  
						         			</#list>  
					        			</#if>  
				            	</select>　　
								 开始时间：
								<input name="startTime" required id="startDate" placeholder="请输入日期" class="btn laydate-icon"> 结束时间：
								<input name="endTime" required id="endDate" placeholder="请输入日期" class="btn laydate-icon">
								<button type="button" class="btn btn-primary btn-sm" onclick="getCount()">查询</button>
							</div>

							<div class="controls">
								<table class="table table-striped table-bordered bootstrap-datatable datatable responsive" id="table2">
									<caption>
										<h2>${schoolName!}${subject.subjectName!}任课老师到勤明细</h2></caption>
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
									<tbody>
										<#if sysUserCountList??>
											<#list sysUserCountList as map>
												<tr>
													<td>${map.sysUserName}</td>
													<td>${map.qianDaoChiShu}</td>
													<td>${map.chiDao}次</td>
													<td>${map.zaoTui}次</td>
													<td>${map.kuangKe}节</td>
												</tr>
											</#list>
										</#if>
									</tbody>
								</table>
							</div>
							<div id="divChart" style="min-width:400px;height:400px"></div>
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
			max: laydate.now(), //不能大于当前天
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