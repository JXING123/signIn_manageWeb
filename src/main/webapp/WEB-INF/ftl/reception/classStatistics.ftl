<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<title>学生管理系统</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
		<meta name="author" content="Muhammad Usman">
		<!-- The styles -->
		<link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">

		<link href="css/charisma-app.css" rel="stylesheet">
		<link href='bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
		<link href='bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
		<link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>
		<link href='bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
		<link href='bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
		<link href='bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
		<link href='css/jquery.noty.css' rel='stylesheet'>
		<link href='css/noty_theme_default.css' rel='stylesheet'>
		<link href='css/elfinder.min.css' rel='stylesheet'>
		<link href='css/elfinder.theme.css' rel='stylesheet'>
		<link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
		<link href='css/uploadify.css' rel='stylesheet'>
		<link href='css/animate.min.css' rel='stylesheet'>
		<!-- jQuery -->
		<script src="bower_components/jquery/jquery.min.js"></script>

		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

		<!-- The fav icon -->
		<link rel="shortcut icon" href="img/favicon.ico">

		<!-- 基本的文件 -->
		<script src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
		<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
		<script src="http://cdn.hcharts.cn/highcharts/modules/exporting.js"></script>
		<!-- 插件文件 -->
		<script src="http://cdn.hcharts.cn/libs/highcharts-export-csv/export-csv.js"></script>

		<script type="text/javascript">
			var chart;
			var container;
			var loaded = false;
			$(function() {
				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'divChart',
						type: 'column',
						inverted: true //横向展示
					},
					navigation: {
						menuItemStyle: {
							padding: '6px 14px'
						}
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
					loading: {
						showDuration: 1000, //设置淡入效果持续时间
						hideDuration: 1000 //设置淡出效果持续时间
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
				if (departmentId != "") {
					$("#departmentId").find("option[value=" + departmentId + "]").attr("selected", true);
				}
				getData();

			});

			//获取数据
			function getData() {
				chart.showLoading();
				var categories = [];
				var zhengChang = [];
				var zaoTui = [];
				var chiDao = [];
				var kuangKe = [];
				var startDate;
				var endDate;
				$.ajax({
					type: "POST",
					url: "getClassAttendanceStatus",
					dataType: "json",
					success: function(result) {
						$(result).each(function(n, item) {
							categories.push(item.stuName);
							zhengChang.push(item.zhengChang);
							zaoTui.push(item.zaoTui);
							chiDao.push(item.chiDao);
							kuangKe.push(item.kuangKe);
							startDate = item.startTime;
							endDate = item.endTime;
						});
						$("#startDate").attr("value", startDate);
						$("#endDate").attr("value", endDate);

						chart.xAxis[0].setCategories(categories);
						chart.series[0].setData(zhengChang);
						chart.series[1].setData(zaoTui);
						chart.series[2].setData(chiDao);
						chart.series[3].setData(kuangKe);
						var diName = $("#className").val();
						var title = {
							text: diName + "学生上课考勤百分比",
						};
						var sub = startDate + "~" + endDate
						$("#h5").html(sub);
						var subtitle = {
							text: sub,
						};
						chart.setTitle(title, subtitle);
						container.setTitle(null, subtitle);
						chart.hideLoading();
					}

				});
			}

			$(function() {
				container = new Highcharts.Chart({
					chart: {
						plotBackgroundColor: null,
						renderTo: 'container',
						plotBorderWidth: null,
						plotShadow: false
					},
					title: {
						text: '软件一班总体上课考勤百分比'
					},
					loading: {
						showDuration: 1000, //设置淡入效果持续时间
						hideDuration: 1000 //设置淡出效果持续时间
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
							showInLegend: true //是否显示图例
						}
					},
					series: [{
						type: 'pie',
						name: '百分比',
						data: [
							['正常', 0],
							['早退', 0],
							['迟到', 0], {
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
			function getClassTotalData() {
				container.showLoading();
				var zhengChang = [];
				var zaoTui = [];
				var chiDao = [];
				var kuangKe = [];
				$.ajax({
					type: "POST",
					url: "getClassAttendanceStatus?total=1",
					dataType: "json",
					success: function(result) {
						var data = [
							['正常', result.zhengChang],
							['早退', result.zaoTui],
							['迟到', result.chiDao], {
								name: '旷课',
								y: result.kuangKe,
								sliced: true,
								selected: true
							}
						];
						container.series[0].setData(data);
						var diName = $("#className").val();
						var title = {
							text: diName + "学生总体考勤百分比",
						};
						container.setTitle(title);
						container.hideLoading();
					}

				});
			}

			function getClassStatistics() {
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
				var departmentId = $("#departmentId").val();
				var className = $("#className").val();
				if (startDate == '' || endDate == '') {
					alert("开始结束日期都不能为空");
				} else {
					window.location.href = "classStatistics?departmentId=" + departmentId + "&startTime=" + startDate + "&endTime=" + endDate + "&className=" + className;
				}
			}

			//模态框居中的控制
			function centerModals(){
    			$('.modal').each(function(i){   //遍历每一个模态框
        			var $clone = $(this).clone().css('display', 'block').appendTo('body'); 
        			var height = window.screen.availHeight/6;   //屏幕可用工作区高度
        			var scrollTop = window.parent.document.documentElement.scrollTop;
					if(scrollTop==0) 
					{
    					scrollTop = window.parent.document.body.scrollTop;
					}
        			//var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);
        			top = top > 0 ? top : 0;
        			$clone.remove();
        			$(this).find('.modal-content').css("margin-top", scrollTop+height);  //修正原先已经有的30个像素
    			});
			}

			 $(function () { $('#myModal').on('show.bs.modal', function () {
      				centerModals();
      			});
   			});
		</script>
		<script src="js/laydate/laydate.js"></script>
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
							<h2><i class="glyphicon glyphicon-edit"></i>${className!}上课到勤统计</h2>
						</div>
						<div class="box-content">
							<div class="form-group" id="export">
								开始时间：<input name="startTime" id="startDate" placeholder="请输入日期" class="btn laydate-icon"> 结束时间：
								<input name="endTime" id="endDate" placeholder="请输入日期" class="btn laydate-icon">
								<input value="${departmentId!}" type="hidden" id="departmentId">
								<input value="${className!}" type="hidden" id="className">
								<button type="button" class="btn btn-primary btn-sm" onclick="getClassStatistics()">查询</button>
								<button type="button" data-type="xls" class="btn btn-success btn-sm">导出Excel</button>
								<button type="button" class="btn btn-default btn-sm" onclick="history.go(-1)">返回</button>
							</div>
							<div class="controls">
								<table class="table table-striped table-bordered bootstrap-datatable datatable responsive" id="table2">
									<caption>
										<h2>${className!}学生上课统计</h2></caption>
									<caption>
										<h5 id="h5"></h5></caption>
									<thead>
										<tr>
											<th rowspan="3" style="vertical-align:middle; text-align:center;">班级</th>
											<th rowspan="3" style="vertical-align:middle; text-align:center;">姓名</th>
											<th rowspan="3" style="vertical-align:middle; text-align:center;">学号</th>
											<th colspan="10" style="vertical-align:middle; text-align:center;">合计</th>

										</tr>
										<tr>
											<th rowspan="2" style="vertical-align:middle; text-align:center;">签到次数</th>
											<th rowspan="2" style="vertical-align:middle; text-align:center;">迟到（次）</th>
											<th rowspan="2" style="vertical-align:middle; text-align:center;">迟到时长（分钟）</th>
											<th rowspan="2" style="vertical-align:middle; text-align:center;">早退（次）</th>
											<th rowspan="2" style="vertical-align:middle; text-align:center;">早退时长</th>
											<th colspan="2" style="vertical-align:middle; text-align:center;">缺卡（次）</th>
											<th rowspan="2" style="vertical-align:middle; text-align:center;">旷课（节）</th>
											<th colspan="2" style="vertical-align:middle; text-align:center;">请假（天）</th>
										</tr>
										<tr>
											<th style="vertical-align:middle; text-align:center;">上课缺卡</th>
											<th style="vertical-align:middle; text-align:center;">下课缺卡</th>
											<th style="vertical-align:middle; text-align:center;">事假</th>
											<th style="vertical-align:middle; text-align:center;">病假</th>
										</tr>
									</thead>
									<tbody>
										<#if stuCountMaps??>
											<#list stuCountMaps as map>
												<tr>
													<td>${map.className}</td>
													<td>${map.stuName}</td>
													<td>${map.stuId}</td>
													<td>${map.clockingCount}</td>
													<td>
														<#if map.chiDao=='0'>
															0
															<#else>
															<u style="cursor: pointer;" data-toggle="modal" data-target="#myModal" onclick="findTruantContent('${map.stuName}','${map.stuId}','迟到')">${map.chiDao}次</u>
														</#if>
													</td>
													<td>
														<#if  map.chiDaoTime=='0'>
															0
															<#else>
															<u style="cursor: pointer;" data-toggle="modal" data-target="#myModal" onclick="findTruantContent('${map.stuName}','${map.stuId}','迟到时长')">${map.chiDaoTime}分钟</u>
														</#if>
													</td>
													<td>
														<#if map.zaoTui=='0'>
															0
															<#else>
															<u style="cursor: pointer;" data-toggle="modal" data-target="#myModal"  onclick="findTruantContent('${map.stuName}','${map.stuId}','早退')">${map.zaoTui}次</u>
														</#if>
													</td>
													<td>
														<#if map.zaoTuiTime=='0'>
															0
															<#else>
															<u style="cursor: pointer;" data-toggle="modal" data-target="#myModal"  onclick="findTruantContent('${map.stuName}','${map.stuId}','早退时长')">${map.zaoTuiTime}分钟</u>
														</#if>
													</td>
													<td>
														<#if map.shangKeQueKa=='0'>
															0
															<#else>
															<u style="cursor: pointer;" data-toggle="modal" data-target="#myModal" onclick="findTruantContent('${map.stuName}','${map.stuId}','上课缺卡')">${map.shangKeQueKa}次</u>
														</#if>
													</td>
													<td>
														<#if map.xiaKeQueKa=='0'>
															0
															<#else>
															<u style="cursor: pointer;" data-toggle="modal" data-target="#myModal"  onclick="findTruantContent('${map.stuName}','${map.stuId}','下课缺卡')">${map.xiaKeQueKa}次</u>
														</#if>
													</td>
													<td>
														<#if map.kuangKe=='0'>
															0
															<#else>
															<u style="cursor: pointer;" data-toggle="modal" data-target="#myModal" onclick="findTruantContent('${map.stuName}','${map.stuId}','旷课')">${map.kuangKe}节</u>
														</#if>
													</td>
													<td>
														<#if map.shiJia=='0'>
															0
															<#else>
															<u style="cursor: pointer;" data-toggle="modal" data-target="#myModal" onclick="findLeaveContent('${map.stuName}','${map.stuId}','事假')">${map.shiJia}天</u>
														</#if>
													</td>
													<td>
														<#if map.bingJia=='0'>
															0
															<#else>
															<u style="cursor: pointer;" data-toggle="modal" data-target="#myModal"  onclick="findLeaveContent('${map.stuName}','${map.stuId}','病假')">${map.bingJia}天</u>
														</#if>
													</td>
												</tr>
											</#list>
										</#if>
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
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
						<h4 class="modal-title" id="myModalLabel" style="color:#000000"></h4>
					</div>
						<font color="#6C6C6C"><span style="font-weight:bold;">
							<div class="modal-body" id="modal-body">
							</div></span>
						</font>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
		</div>
		<!-- /.modal -->
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
	<!-- 导出到excel表格 -->
	<script src="js/outExcel/Blob.js"></script>
	<script src="js/outExcel/FileSaver.js"></script>
	<script src="js/outExcel/tableExport.js"></script>
	<script>
		var exportLink = document.getElementById('export');
		var className = "${className!}" + "上课到勤统计";
		exportLink.addEventListener('click', function(e) {
			e.preventDefault();
			if (e.target.nodeName === "BUTTON") {
				tableExport('table2', className, e.target.getAttribute('data-type'));
			}
		}, false);
	</script>
	<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script src="js/jquery.cookie.js"></script>
<!-- calender plugin -->
<script src='bower_components/moment/min/moment.min.js'></script>
<script src='bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='js/jquery.dataTables.min.js'></script>

<!-- select or dropdown enhancer -->
<script src="bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script src="bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<script src="js/jquery.noty.js"></script>
<!-- library for making tables responsive -->
<script src="bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script src="bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<script src="js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->

<script src="js/jquery.uploadify-3.1.min.js"></script>
<script src="js/jquery.history.js"></script>
<script src="js/charisma.js"></script>
<script src="js/myJs.js"></script>
</html>