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
		<script src="js/myJs.js"></script>
		<script type="text/javascript">
			var chart;
			var container;
			var startDate = "${startTime}";
			var endDate = "${endTime}";
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
						text: '等待数据中。。。',
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
	
								}
							}
						}
					},
					series: [{
						name: '正常',
						data: []
					}, {
						name: '晚归',
						data: []
					}, {
						name: '未归',
						data: []
					}]
				});
				var categories = [];
				var zhengChang = [];
				var wanGui = [];
				var weiGui = [];
				
				
				<#if dormCountList??>
					<#list dormCountList as map>
						categories.push("${map.stuName}");
						zhengChang.push(${map.zhengChang});
						wanGui.push(${map.wanGui});
						weiGui.push(${map.weiGui});
					</#list>
				</#if>
				chart.xAxis[0].setCategories(categories);
				chart.series[0].setData(zhengChang);
				chart.series[1].setData(wanGui);
				chart.series[2].setData(weiGui);
				$("#startDate").attr("value", startDate);
				$("#endDate").attr("value", endDate);		
				var departmentId = "${departmentId!}";
				if (departmentId != "") {
					$("#departmentId").find("option[value=" + departmentId + "]").attr("selected", true);
				}
				var classId = "${classId!}";
				if (classId != "") {
					$("#classId").find("option[value=" + classId + "]").attr("selected", true);
				}
				var dormId = "${dormId!}";
				if (dormId != "") {
					$("#dormId").find("option[value=" + dormId + "]").attr("selected", true);
				}
				var name= "${dromName!}";
				$("#h2").html(name+"宿舍汇总表");
				$("#h5").html(startDate + "~" + endDate);
				var subTitle = {
					text: startDate + "~" + endDate,
				};
				
				$("#diName").html(name);
				var title = {
							text: name + "宿舍到勤统计",
						};
				chart.setTitle(title, subTitle);

			});
			
			$(function() {
				container = new Highcharts.Chart({
					chart: {
						plotBackgroundColor: null,
						renderTo: 'container',
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
							showInLegend: true //是否显示图例
						}
					},
					series: [{
						type: 'pie',
						name: '',
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
				var data = [
							['正常', ${countTotal.zhengChang}],
							['晚归', ${countTotal.wanGui}],
							{
								name: '未归',
								y: ${countTotal.weiGui},
								sliced: true,
								selected: true
							}
						];
				container.series[0].setData(data);
				var name = "${dromName!}";
				var title = {
					text: name + "宿舍到勤统计总体情况表",
				};
				var subTitle = {
					text: startDate + "~" + endDate,
				};
				container.setTitle(title,subTitle);
			});
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
			//模态框居中
			 $(function () { $('#myModal').on('show.bs.modal', function () {
      				centerModals();
      			});
   			});
		</script>
		<script src="js/laydate/laydate.js"></script>
		<script type="text/javascript">
			//根据系别带出所属班级 
			function getClass(){ 
				$("#classId").html("<option value=''>-请选择班级-</option>") 
				var id = $("#departmentId").val(); 
				$.post("getClass",{"departmentId" : id},function callBack(data){
					 $("#classId").attr("data-rel",""); 
					 var l = eval("("+data+")"); 
					 for(var i=0; i<l.length; i++){
					 	 var className=l[i].className; 
					 	 var classId=l[i].classId; 
					 	 //ajax请求回来的数据对下拉框的操作 
					 	 var a="<option value='" +classId + "'>" + className + "</option>"; 
					 	 $("#classId").append(a); 
					 } 
				}) 
			}
			//根据班级带出宿舍
			function getDorm(){ 
				$("#dormId").html("<option value=''>-请选择宿舍-</option>") 
				var id = $("#classId").val(); 
				$.post("findDormInfoByClassId",{"classId" : id},function callBack(data){
					 var l = eval("("+data+")"); 
					 for(var i=0; i<l.length; i++){
					 	 var dormAddress=l[i].dormAddress; 
					 	 var dormId=l[i].dormId; 
					 	 //ajax请求回来的数据对下拉框的操作 
					 	 var a="<option value='" +dormId + "'>" + dormAddress + "</option>"; 
					 	 $("#dormId").append(a); 
					 } 
				}) 
			}
			
			function getCount() {
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
				var departmentId = $("#departmentId").val();
				var dormId = $("#dormId").val();
				var classId = $("#classId").val();
				if (startDate == '') {
					alert("开始日期不能为空");
				} else if(endDate == ''){
					alert("结束日期不能为空");
				}else if(departmentId==''){
					window.location.href = "schoolDormSignInStatistics?startTime=" + startDate + "&endTime=" + endDate;
				}else if(classId =='' && departmentId!=''){
					window.location.href = "departmentDormSignInStatistics?departmentId=" + departmentId +"&startTime=" + startDate + "&endTime=" + endDate;
				}else if(classId !='' && departmentId!=''&& dormId==''){
					alert(dormId);
					window.location.href = "classDormSignInStatistics?departmentId=" + departmentId +"&startTime=" + startDate + "&endTime=" + endDate+ "&classId=" + classId;
				}else if(classId !='' && departmentId!='' && dormId!=''){
					window.location.href = "dormDormSignInStatistics?departmentId=" + departmentId +"&startTime=" + startDate + "&endTime=" + endDate+ "&classId=" + classId+ "&dormId=" + dormId;
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
						宿舍考勤统计
					</li>
				</ul>
			</div>
			<div class="row">
				<div class="box col-md-12">
					<div class="box-inner">
						<div class="box-header well" data-original-title="">
							<h2><i class="glyphicon glyphicon-edit"></i><b id="diName"></b>宿舍考勤统计</h2>
						</div>
						<div class="box-content">
							<div class="form-group">
								<select name="departmentId" id="departmentId" class="btn btn-default btn-sm" onchange="getClass()">
				            			<option value="">全部院系</option> 
				            			<#if departmentInfos??>  
						         			<#list departmentInfos as d>  
						         	 			<option value="${d.departmentId}">${d.deprtmentName}</option>  
						         			</#list>  
					        			</#if>  
				            		</select>
				            		<select name="classId" id="classId" class="btn btn-default btn-sm" onchange="getDorm()">
				            			<option value="">请选择班级</option>
				            			<#if classInfos??>  
						         			<#list classInfos as c>  
						         	 			<option value="${c.classId!}">${c.className!}</option>  
						         			</#list>  
					        			</#if>    
				            		</select>
				            		<select name="dormId" id="dormId" class="btn btn-default btn-sm">
				            			<option value="">请选择宿舍</option>
				            			<#if findDormList??>  
						         			<#list findDormList as f>  
						         	 			<option value="${f.dormId!}">${f.dormAddress!}</option>  
						         			</#list>  
					        			</#if>    
				            		</select>　　 开始时间：
								<input name="startTime" required id="startDate" placeholder="请输入日期" class="btn laydate-icon"> 结束时间：
								<input name="endTime" required id="endDate" placeholder="请输入日期" class="btn laydate-icon">
								<input type="hidden" id="classId1"  value="">
								<button type="button" class="btn btn-primary btn-sm" onclick="getCount()">查询</button>
							</div>
							<div class="controls">
								<table class="table table-striped table-bordered bootstrap-datatable datatable responsive" id="table2">
									<caption>
										<h2 id="h2"></h2></caption>
									<caption>
										<h5 id="h5"></h5></caption>
									<thead>
										<tr>
											<th rowspan="2" style="vertical-align:middle; text-align:center;">
												学号
											</th>
											<th rowspan="2" style="vertical-align:middle; text-align:center;">
												姓名
											</th>
											<th colspan="6" style="vertical-align:middle; text-align:center;">合计</th>
										</tr>
										<tr>
											<th style="vertical-align:middle; text-align:center;">正常</th>
											<th style="vertical-align:middle; text-align:center;">晚归（次）</th>
											<th style="vertical-align:middle; text-align:center;">未归（次）</th>
											<th style="vertical-align:middle; text-align:center;">请假（天）<br/>一天代表一次签到</th>
											<th style="vertical-align:middle; text-align:center;">需要签到次数（次）</th>
										</tr>
									</thead>
									<tbody>
										<#if dormCountList??>
											<#list dormCountList as map>
												<tr>
													<td>${map.stuId}</td>
													<td>${map.stuName}</td>
													<td>${map.zhengChang}次</td>
													<td>
														<#if map.wanGui=='0'>
															0
															<#else>
															<u style="cursor: pointer;" data-toggle="modal" data-target="#myModal" onclick="findDormSigninContent('${map.stuName}','${map.stuId}','晚归')">${map.wanGui}次</u>
														</#if>
													</td>
													<td>
														<#if map.weiGui=='0'>
															0
															<#else>
															<u style="cursor: pointer;" data-toggle="modal" data-target="#myModal" onclick="findDormSigninContent('${map.stuName}','${map.stuId}','未归')">${map.weiGui}次</u>
														</#if>
													</td>
													<td>
														<#if map.qingJia=='0'>
															0
															<#else>
															<u style="cursor: pointer;" data-toggle="modal" data-target="#myModal"  onclick="findLeaveContent('${map.stuName}','${map.stuId}','请假')">${map.qingJia}天</u>
														</#if>
													</td>
													<td>${map.allNumber}次</td>
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
					<b>
						<font color="#6C6C6C">
							<div class="modal-body" id="modal-body">
							</div>
						</font>
					</b>
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
	<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</html> 