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


		<script>	
			

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
			function getClassStatistics() {
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
				var departmentId = $("#departmentId").val();
				var classId = $("#classId").val();
				var className = $("#classId option:selected").text();
				if (startDate == '') {
					alert("开始日期不能为空");
				} else if(endDate == ''){
					alert("结束日期不能为空");
				}else if(classId==''){
					alert("请选择班级");
				}else{
					window.location.href = "classStatistics?departmentId=" + departmentId + "&startTime=" + startDate + "&endTime=" + endDate + "&className=" + className;
				}
			}
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
							<h2><i class="glyphicon glyphicon-edit"></i>班级上课到勤统计明细表</h2>
						</div>
						<div class="box-content">
							<div class="form-group" id="export">
								<select name="departmentId" id="departmentId" class="btn btn-default btn-sm" onchange="getClass()">
										<option value="">请选择院系</option>  
				            			<#if departmentInfos??>  
						         			<#list departmentInfos as d>  
						         	 			<option value="${d.departmentId}">${d.deprtmentName}</option>  
						         			</#list>  
					        			</#if>  
				            	</select>　　
				            	<select name="classId" id="classId" class="btn btn-default btn-sm">
				            		<option value="">请选择班级</option>  
				            	</select>
								开始时间：<input name="startTime" id="startDate" placeholder="请输入日期" class="btn laydate-icon"> 结束时间：
								<input name="endTime" id="endDate" placeholder="请输入日期" class="btn laydate-icon">
								<input value="${departmentId!}" type="hidden" id="departmentId">
								<input value="${className!}" type="hidden" id="className">
								<button type="button" class="btn btn-primary btn-sm" onclick="getClassStatistics()">查询</button>
								<button type="button" class="btn btn-default btn-sm" onclick="history.go(-1)">返回</button>
								
							</div>
							<div class="controls">
								<table class="table table-striped table-bordered bootstrap-datatable datatable responsive" id="table2">
									<caption>
										<h2>学生上课统计</h2></caption>
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
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!--/span-->

			</div>
			<!--/row-->
		</div>
	</body>
	<script>
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