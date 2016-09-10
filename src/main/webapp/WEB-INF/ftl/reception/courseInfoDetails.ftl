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
		<script type="text/javascript">
			$(function(){ 
				var xingqi = "${findCourseInfoById.week!}";
				$("#xingqi").find("option[value="+xingqi+"]").attr("selected",true);
				
			}); 
			
		
			//校验表单
			function check(){
	    		if(checkClassRoom() == false||
	    		   checkSchoolBuilding() == false||
	    		   checkSetSuji() == false||
	    		   checkStartTime() == false||
	    		   checkEndTime() == false||
	    		   checkWeekNext() == false||
	    		   checkWifiName() == false||
	    		   checkWifiRouteId() == false){
	    		}else{
	    			document.myForm.submit();
	    		}
	    	}
	    	
	    	//校验wifi名称
			function checkWifiName(){
			    var wifiName = $("#wifiName").val();
			    if(wifiName == null || wifiName == ""){
			    	$("#nameDiv7").html("<font color='red'>wifi名称不能为空</font>");
			    	return false;
			   }else {
			   		$("#nameDiv7").html("");
					return true;
			  }
			}
			
			//校验wifi唯一标识
			function checkWifiRouteId(){
			    var wifiRouteId = $("#wifiRouteId").val();
			    if(wifiRouteId == null || wifiRouteId == ""){
			    	$("#nameDiv8").html("<font color='red'>wifi唯一标识不能为空</font>");
			    	return false;
			   }else {
			   		$("#nameDiv8").html("");
					return true;
			  }
			}
			
			//校验教室
			function checkClassRoom(){
			    var classRoom = $("#classRoom").val();
			    if(classRoom == null || classRoom == ""){
			    	$("#nameDiv").html("<font color='red'>教室不能为空</font>");
			    	return false;
			   }else {
			   		$("#nameDiv").html("");
					return true;
			  }
			}
			
			//校验教学楼
			function checkSchoolBuilding(){
			    var schoolBuilding = $("#schoolBuilding").val();
			    if(schoolBuilding == null || schoolBuilding == ""){
			    	$("#nameDiv2").html("<font color='red'>教学楼不能为空</font>");
			    	return false;
			   }else {
			   		$("#nameDiv2").html("");
					return true;
			  }
			}
			
			//校验节次
			function checkSetSuji(){
			    var setSuji = $("#setSuji").val();
			    if(setSuji == null || setSuji == ""){
			    	$("#nameDiv3").html("<font color='red'>节次不能为空</font>");
			    	return false;
			   }else {
			   		$("#nameDiv3").html("");
					return true;
			  }
			}
			
			//校验开始时间
			function checkStartTime(){
			    var startTime = $("#startTime").val();
			    if(startTime == null || startTime == ""){
			    	$("#nameDiv4").html("<font color='red'>开始时间不能为空</font>");
			    	return false;
			   }else {
			   		$("#nameDiv4").html("");
					return true;
			  }
			}
			
			//校验结束时间
			function checkEndTime(){
			    var endTime = $("#endTime").val();
			    if(endTime == null || endTime == ""){
			    	$("#nameDiv5").html("<font color='red'>结束时间不能为空</font>");
			    	return false;
			   }else {
			   		$("#nameDiv5").html("");
					return true;
			  }
			}
			
			//校验周次
			function checkWeekNext(){
			    var weekNext = $("#weekNext").val();
			    if(weekNext == null || weekNext == ""){
			    	$("#nameDiv6").html("<font color='red'>周次不能为空</font>");
			    	return false;
			   }else {
			   		$("#nameDiv6").html("");
					return true;
			  }
			}
			//某个学校所有的任课老师
		function selectSysUser(){
			var schoolId = $("#schoolId1").val();
			$.post("findRoleTeacherSysUserList",{"schoolId":schoolId},function callBack(data){
    			var l = eval("("+data+")");
    			for(var i=0; i<l.length; i++){
    				var sysUserName = l[i].sysUserName;
    				var sysUserId = l[i].sysUserId;
    				//ajax请求回来的数据对下拉框的操作
        			var a = "<option value="+sysUserId +">" + sysUserName +"</option>";
        			$("#sysUserId").append(a);
    			}
    			var sysUserId = "${findCourseInfoById.sysUser.sysUserId!}";
				$("#sysUserId").find("option[value="+sysUserId+"]").attr("selected",true);
    		})
		}
		</script>	
		<script>
			
			$(function(){ 
				selectSysUser();
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
						课程表详细信息
					</li>
				</ul>
			</div>
			<div class="row">
				<div class="box col-md-12">
					<div class="box-inner">
						<div class="box-header well" data-original-title="">
							<h2><i class="glyphicon glyphicon-user"></i>课程详情</h2>
						</div>
						<div class="box-content"> 
							<form action="updateCourseInfo?courseId=${findCourseInfoById.courseId!}" method="post" name="myForm">
								<table class="table-striped table-bordered">
									<tr role="row">
										<td><label class="control-label" for="inputIcon">星期:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<select name="week" id="xingqi" class="form-control" style="width:100%">
													 <option value="星期一">星期一</option>
													 <option value="星期二">星期二</option>
													 <option value="星期三">星期三</option>
													 <option value="星期四">星期四</option>
													 <option value="星期五">星期五</option>
													 <option value="星期六">星期六</option>
													 <option value="星期日">星期日</option>
										    	</select>
											</div>
										</td>
										<td><label class="control-label" for="inputIcon">科目名称:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<input id="subjectName" name="subjectName" type="text" value="${findCourseInfoById.subject.subjectName?if_exists}" class="form-control" readonly="true">
											</div>
										</td>
										<tr>
											<td><label class="control-label" for="inputIcon">教室:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="classRoom" name="classRoom" onkeydown="checkClassRoom();" type="text" value="${findCourseInfoById.classRoom?if_exists}" class="form-control">
												</div><span id="nameDiv"></span>
											</td>
											<td><label class="control-label" for="inputIcon">教学楼:</label></td>
											<td class="center">
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="schoolBuilding" name="schoolBuilding" onkeydown="checkSchoolBuilding();" value="${findCourseInfoById.schoolBuilding?if_exists}"  type="text" class="form-control">
												</div><span id="nameDiv2"></span>
											</td>
										</tr>
										<tr>
											<td><label class="control-label" for="inputIcon">节次:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="setSuji" name="setSuji" onkeydown="checkSetSuji();" value="${findCourseInfoById.setSuji?if_exists}" type="text" class="form-control">
												</div><span id="nameDiv3"></span>
											</td>
											<td><label class="control-label" for="inputIcon">开始时间:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="startTime" type="time" onkeydown="checkStartTime();" class="form-control" name="startTime" value="${findCourseInfoById.startTime?if_exists}">
												</div><span id="nameDiv4"></span>
											</td>
										</tr>
										<tr>
											<td><label class="control-label" for="inputIcon">结束时间:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="endTime" name="endTime" onkeydown="checkEndTime();" value="${findCourseInfoById.endTime?if_exists}" type="time" class="form-control" >
												</div><span id="nameDiv5"></span>
											</td>
											<td><label class="control-label" for="inputIcon">周次:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="weekNext" name="weekNext" onkeydown="checkWeekNext();" value="${findCourseInfoById.weekNext?if_exists}" type="text" class="form-control">
												</div><span id="nameDiv6"></span>
											</td>
										</tr>
										<tr>
											<td><label class="control-label" for="inputIcon">班级:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="className" name="className" value="${findCourseInfoById.classes.className?if_exists}" type="text" class="form-control" readonly="true">
												</div>
											</td>
											<td><label class="control-label" for="inputIcon">wifi名称:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="wifiName" name="wifiName" onkeydown="checkWifiName();" value="${findCourseInfoById.wifiName?if_exists}" type="text" class="form-control">
												</div><span id="nameDiv7"></span>
											</td>
										</tr>
										<tr>
											<td><label class="control-label" for="inputIcon">wifi-MAC地址:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="wifiRouteId" name="wifiRouteId" onkeydown="checkWifiRouteId();" value="${findCourseInfoById.wifiRouteId?if_exists}" type="text" class="form-control">
												</div><span id="nameDiv8"></span>
											</td>
											<td><label class="control-label" for="inputIcon">任课老师:</label></td>
											<td>
												<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<select name="sysUser.sysUserId" id="sysUserId" class="form-control" style="width:100%">
										    	</select>
												</div>
											</td>
										</tr>
										
											<tr>
												<td colspan="2"><button type="button"  onclick="check()" class="btn btn-warning">更新</button></td>
												<td colspan="2"><button type="button" class="btn btn-primary" onclick="history.go(-1)">返回</button></td>
											</tr>
								</table>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--/span-->

		</div>
		<!--/row-->

		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>Settings</h3>
					</div>
					<div class="modal-body">
						<p>Here settings can be configured...</p>
					</div>
					<div class="modal-footer">
						<a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
						<a href="#" class="btn btn-primary" data-dismiss="modal">Save changes</a>
					</div>
				</div>
			</div>
		</div>

		</div>
		<!--/.fluid-container-->

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
			$('.form_time').datetimepicker({
				language: 'fr',
				weekStart: 1,
				todayBtn: 1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 1,
				minView: 0,
				maxView: 1,
				forceParse: 0
			});
		</script>
	</body>

</html>