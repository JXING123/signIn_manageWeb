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
		<!-- datetimepicker -->
		<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
		<script type="text/javascript" src="js/jquery-1.8.3.min.js" charset="UTF-8"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
		<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
		<!-- The fav icon -->
		<link rel="shortcut icon" href="img/favicon.ico">
		<script type="text/javaScript">
			//根据学校带出系别
			function getDepartment(){ 
				$("#departmentId").html("<option value=''>-请选择系别-</option>");
				$("#classId").html("<option value=''>-请选择班级-</option>");
				var id = $("#schoolId").val(); 
				$.post("selectAllDepartment",{"schoolId" : id},function callBack(data){
					 $("#departmentId").attr("data-rel",""); 
					 var l = eval("("+data+")"); 
					 for(var i=0; i<l.length; i++){
					 	 var deprtmentName=l[i].deprtmentName; 
					 	 var departmentId=l[i].departmentId; 
					 	 //ajax请求回来的数据对下拉框的操作 
					 	 var a="<option value='" +departmentId + "'>" + deprtmentName + "</option>"; 
					 	 $("#departmentId").append(a); 
					 } 
				}) 
			} 
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
			
		</script>
	</head>
	<script type="text/javascript" src="js/lib/jquery.poshytip.js"></script>
	<script type='text/javascript' src='js/lib/jq.validate.js'></script>
	<link rel="stylesheet" href="js/lib/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />

	<body>
		<div class="ch-container">
			<div class="row">
				<ul class="breadcrumb" style="color:#317eac;">
					<li>
						首页
					</li>
					<li>
						宿舍添加
					</li>
				</ul>
			</div>

			<div class="row">
				<div class="box col-md-12">

					<div class="box-inner">
						<div class="box-header well" data-original-title="">
							<h2><i class="glyphicon glyphicon-user"></i>宿舍添加</h2>
						</div>
						<div class="box-content">
							<div class="modal-header left">
								<h3>宿舍添加</h3>
							</div>
							<form action="addDormInfo" method="post" name="myForm">
								<div class="modal-body">
									<@shiro.hasRole name="系统管理员">
										<label class="control-label" for="inputIcon">宿舍所属学校：</label>
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
											<select name="school.schoolId" id="schoolId" class="form-control" onchange="getDepartment()" valType="required" msg="<font color=red>*</font>必须选择一个学校" style="width:50%">
														<option value="">-请选择学校-</option>
														<#if schoolList??>
															<#list schoolList as school>
																<option value="${school.schoolId!}">${school.schoolName!}</option>
															</#list>
														</#if>
											    </select>
										</div>
									</@shiro.hasRole>
									<label class="control-label" for="inputIcon">请选择系别：</label>
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
										<select id="departmentId" class="form-control" valType="required" msg="<font color=red>*</font>必须选择系别（显示该系别所有的班级）" style="width:50%" onchange="getClass()">
						    			<option  value="">-请选择系别-</option>
							    		<#if departmentInfoList??>  
									         <#list departmentInfoList as g>  
									         	 <option value="${g.departmentId}">${g.deprtmentName}</option>  
									         </#list>
								        </#if> 
						    	</select>
										<span id="nameDiv9"></span>
									</div>

									<label class="control-label" for="inputIcon">请选择班级：</label>
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
										<select id="classId" valType="required" msg="<font color=red>*</font>必须选择所在班级（请先选择班级所在的系别，如果没有请添加）" type="time" class="form-control" name="classes.classId" style="width:50%">
						    				<option value="">-请选择班级-</option>
						    			</select>
										<span id="nameDiv10"></span>
									</div>
									<label class="control-label" for="inputIcon">宿舍楼名称：</label>
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
										<input id="dormFloorName" name="dormFloorName" class="form-control" valType="required" msg="<font color=red>*</font>宿舍名称不能为空" placeholder="宿舍楼名称" style="width:50%" />
										<span id="nameDiv1"></span>
									</div>
									<label class="control-label" for="inputIcon">wifi名称：</label>
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
										<input id="wifiName" name="wifiName" valType="required" msg="<font color=red>*</font>wifi名称不能为空" type="text" class="form-control" placeholder="wifi名称" style="width:50%" />
										<span id="nameDiv2"></span>
									</div>
									<label class="control-label" for="inputIcon">MAC地址：</label>
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
										<input id="wifiRouteid" name="wifiRouteid" valType="required" msg="<font color=red>*</font>wifi唯一标识不能为空" type="text" class="form-control" placeholder="wifi唯一标识" style="width:50%" />
										<span id="nameDiv3"></span>
									</div>
									<label class="control-label" for="inputIcon">备注：</label>
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
										<input id="dormDesc" name="dormDesc" valType="required" msg="<font color=red>*</font>备注不能为空" type="text" class="form-control" placeholder="备注" style="width:50%" />
										<span id="nameDiv4"></span>
									</div>
									<label class="control-label" for="inputIcon">宿舍人数：</label>
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
										<input id="dormPopulation" name="dormPopulation" valType="NUMBER" msg="<font color=red>*</font>只能是数字" type="text" class="form-control" placeholder="宿舍人数" style="width:50%" />
										<span id="nameDiv5"></span>
									</div>
									<label class="control-label" for="inputIcon">宿舍编号：</label>
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
										<input id="dormAddress" name="dormAddress" valType="required" msg="<font color=red>*</font>宿舍编号不能为空" type="text" class="form-control" placeholder="宿舍编号" style="width:50%" />
										<span id="nameDiv6"></span>
									</div>
									<label class="control-label" for="inputIcon">签到时间：</label>
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
										<input id="dormSigninStartTime" name="dormSigninStartTime" valType="required" msg="<font color=red>*</font>签到不能为空" type="time" class="form-control" placeholder="签到时间" style="width:50%">
										<span id="nameDiv7"></span>
									</div>
									<label class="control-label" for="inputIcon">签退时间：</label>
									<div class="input-group">
										<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
										<input id="dormSigninEndTime" name="dormSigninEndTime" valType="required" msg="<font color=red>*</font>签退时间不能为空" type="time" class="form-control" placeholder="签到时间" type="time" class="form-control" placeholder="签退时间" style="width:50%">
										<span id="nameDiv8"></span>
									</div>

								</div>
								<div class="modal-footer right">
									<a href="javascript:void(0)" onclick="history.go(-1)" class="btn btn-default" data-dismiss="modal">返回</a>
									<button type="submit" id="disM" class="btn btn-primary" data-dismiss="modal">保存</a>
									</div>
								</form>
							</div>
					</div>
				</div>
			</div>
		</div>
		<!--/span-->
		</div>
		<!--/row-->
	</body>

</html>