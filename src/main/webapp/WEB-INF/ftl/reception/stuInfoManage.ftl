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
	<script type="text/javaScript">
		function getParamsClocking(){
						var schoolId = $("#schoolId").val();
						var departmentId = $("#departmentId").val();
						var selDate = $("#selDate").val(); 
						var classId = $("#classId").val();
						if(schoolId !=undefined && schoolId ==""){
							departmentId="";
							classId="";
						}
						if(schoolId ==undefined){
							schoolId = "";
						}
			
						if(departmentId ==undefined){
							departmentId = "";
						}
						 window.location.href="findListStu?schoolId="+schoolId+"&departmentId="+departmentId+"&classId="+classId;
		}
		function deleteStuInfo(stuId){
			var schoolId = $("#schoolId").val();
			var departmentId = $("#departmentId").val();
			var classId = $("#classId").val();
			if(schoolId !=undefined && schoolId ==""){
				departmentId="";
				classId="";
			}
			if(schoolId ==undefined){
				schoolId = "";
			}
			if(departmentId !=undefined && departmentId ==""){
				classId="";
			}
			if(departmentId ==undefined){
				departmentId = "";
			}
			window.location.href="deleteStuInfo?stuId="+stuId+"&schoolId="+schoolId+"&departmentId="+departmentId+"&classId="+classId;
		}
		function updateStuState(stuId){
			var schoolId = $("#schoolId").val();
			var departmentId = $("#departmentId").val();
			var classId = $("#classId").val();
			if(schoolId !=undefined && schoolId ==""){
				departmentId="";
				classId="";
			}
			if(schoolId ==undefined){
				schoolId = "";
			}
			if(departmentId !=undefined && departmentId ==""){
				classId="";
			}
			if(departmentId ==undefined){
				departmentId = "";
			}
			window.location.href="updateStuState?stuId="+stuId+"&schoolId="+schoolId+"&departmentId="+departmentId+"&classId="+classId;
		}
	</script>
				<script type="text/javascript">
					$(function() {
						var departmentId = "${departmentId!}";
						if (departmentId != "") {
							$("#departmentId").find("option[value=" + departmentId + "]").attr("selected", true);
						}
						var classId = "${classId!}";
						if (classId != "") {
							$("#classId").find("option[value=" + classId + "]").attr("selected", true);
						}
						var schoolId = "${schoolId!}";
						if (schoolId != "") {
							$("#schoolId").find("option[value=" + schoolId + "]").attr("selected", true);
						}
					});
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
						学生管理
					</li>
				</ul>
			</div>
			<div class="row">
				<div class="box col-md-12">
					<div class="box-inner">
						<div class="box-header well" data-original-title="">
							<h2><i class="glyphicon glyphicon-user"></i> 　学生管理</h2>
						</div>
						<div class="box-content">
							<div class="controls">
							<@shiro.hasRole name="系统管理员">
								学校名称： <select name="schoolId" id="schoolId" data-rel="chosen" onchange="getParamsClocking()">
	           				<option value="">　　全部学校　　</option>
	           				<#if schoolInfoList??>  
						         <#list schoolInfoList as s>  
						         	 <option value="${s.schoolId}">${s.schoolName}</option>  
						         </#list>  
					        </#if>  
	            		</select>
						</@shiro.hasRole>
						<@shiro.hasAnyRoles name="系统管理员,学校管理员">
							院系名称： <select name="departmentId" id="departmentId" data-rel="chosen" onchange="getParamsClocking()">
	           				<option value="">　　全部院系　　</option>
	           				<#if departmentInfoList??>  
						         <#list departmentInfoList as d>  
						         	 <option value="${d.departmentId}">${d.deprtmentName}</option>  
						         </#list>  
					        </#if>  
	            		</select>
						</@shiro.hasAnyRoles>
							班级名称：<select id="classId" data-rel="chosen" onchange="getParamsClocking()">
			              	<option value="">　　全部班级　　</option>
			              	<#if classInfoList??>  
						         <#list classInfoList as c>  
						         	 <option value="${c.classId}">${c.className}</option>  
						         </#list>  
					        </#if>  
			          </select>
						</div>
						<br>
						<table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
							<thead>
								<tr>
									<th>班级名</th>
									<th>姓名</th>
									<th>性别</th>
									<th>宿舍楼名</th>
									<th>审核状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<#if findListStu??>
									<#list findListStu as stu>
										<tr>
											<td class="center">
												<#if stu.classes??>${(stu.classes.className)!}</#if>
											</td>
											<td>
												<#if stu.stuName??>${(stu.stuName)!}</#if>
											</td>
											<td class="center">
												<#if stu.stuSex??>
													${(stu.stuSex)!}
													<#else>
														占无性别
												</#if>
											</td>

											<td class="center">
												<#if stu.dorm??>
													${(stu.dorm.dormFloorName)!}
													<#else>
														未分配
												</#if>
											</td>
											<td class="center">
												<#if stu.stuState??>
													<span class="label label-success">${(stu.stuState)!}</span>
													<#else>
														<span class="label label-warning">未审核</span>
												</#if>
											</td>
											<td class="center">
												<a class="btn btn-info" href="findByIdStu?stuId=${stu.stuId!}">
													<i class="glyphicon glyphicon-edit icon-white"></i> 详情/修改
												</a>
												<a class="btn btn-success" onclick="updateStuState('${stu.stuId}')" href="javaScript:(void)">
													<i class="glyphicon glyphicon-zoom-in icon-white"></i> 审核
												</a>
												<a class="btn btn-danger" onclick="deleteStuInfo('${stu.stuId!}')" href="javaScript:(void)">
													<i class="glyphicon glyphicon-trash icon-white"></i> 删除
												</a>
											</td>
											</tr>
									</#list>
								</#if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!--/span-->
		</div>
		<!--/row-->

		

		<!-- external javascript -->

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
		<!-- history.js for cross-browser state change on ajax -->
		<script src="js/jquery.history.js"></script>
		<!-- application script for Charisma demo -->
		<script src="js/charisma.js"></script>
		<script type="text/javascript">
		</script>
	</body>

</html>