<!DOCTYPE html>
<html lang="en">

	<head>
		<!--
        ===
        This comment should NOT be removed.

        Charisma v2.0.0

        Copyright 2012-2014 Muhammad Usman
        Licensed under the Apache License v2.0
        http://www.apache.org/licenses/LICENSE-2.0

        http://usman.it
        http://twitter.com/halalit_usman
        ===
    -->
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
		//校验表单
		function check(){
    		if(checkDormFloorName() == false||
    		   checkDormPopulation() == false||
    		   checkDormDesc() == false||
    		   checkDormAddress() == false||
    		   checkDormSigninStartTime() == false||
    		   checkDormSigninEndTime() == false){
    		}else{
    			document.myForm.submit();
    		}
    	}
		
		//校验宿舍楼名称
		function checkDormFloorName(){
		    var dormFloorName = $("#dormFloorName").val();
		    var wifiRouteid = $("#wifiRouteid").val();
		     var wifiName = $("#wifiName").val();
		    if(dormFloorName == null || dormFloorName == ""){
		    	$("#nameDiv").html("<font color='red'>宿舍楼名称不能为空</font>");
		    	return false;
		   }else if(wifiName ==null || wifiName==''){
		   		$("#nameDiv").html("");
		   		$("#wifiName1").html("<font color='red'>wifi名称不能为空</font>");
		   		return false;
		   }else if(wifiRouteid ==null || wifiRouteid==''){
		   		$("#wifiName1").html("");
		   		$("#wifiRouteid1").html("<font color='red'>Mac地址不能为空</font>");
		   		return false;
		   } else {
		   		$("#wifiRouteid1").html("");
				return true;
		  }
		   
		}
		
		
		//校验宿舍人数
		function checkDormPopulation(){
		    var dormPopulation = $("#dormPopulation").val();
		    if(dormPopulation == null || dormPopulation == ""){
		    	$("#nameDiv2").html("<font color='red'>宿舍人数不能为空</font>");
		    	return false;
		   } else {
		   		$("#nameDiv2").html("");
				return true;
		  }
		}
		
		//校验宿舍备注
		function checkDormDesc(){
		    var dormDesc = $("#dormDesc").val();
		    if(dormDesc == null || dormDesc == ""){
		    	$("#nameDiv3").html("<font color='red'>宿舍备注不能为空</font>");
		    	return false;
		   } else {
		   		$("#nameDiv3").html("");
				return true;
		  }
		}
		
		//校验宿舍地址
		function checkDormAddress(){
		    var dormAddress = $("#dormAddress").val();
		    if(dormAddress == null || dormAddress == ""){
		    	$("#nameDiv4").html("<font color='red'>宿舍地址不能为空</font>");
		    	return false;
		   } else {
		   		$("#nameDiv4").html("");
				return true;
		  }
		}
		
		//校验签到时间
		function checkDormSigninStartTime(){
		    var dormSigninStartTime = $("#dormSigninStartTime").val();
		    if(dormSigninStartTime == null || dormSigninStartTime == ""){
		    	$("#nameDiv5").html("<font color='red'>签到时间不能为空</font>");
		    	return false;
		   } else {
		   		$("#nameDiv5").html("");
				return true;
		  }
		}
		
		//校验签退时间
		function checkDormSigninEndTime(){
		    var dormSigninEndTime = $("#dormSigninEndTime").val();
		    if(dormSigninEndTime == null || dormSigninEndTime == ""){
		    	$("#nameDiv6").html("<font color='red'>签退时间不能为空</font>");
		    	return false;
		   } else {
		   		$("#nameDiv6").html("");
				return true;
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
						宿舍详细信息
					</li>
				</ul>
			</div>
			<div class="row">
				<div class="box col-md-12">
					<div class="box-inner">
						<div class="box-header well" data-original-title="">
							<h2><i class="glyphicon glyphicon-user"></i>宿舍详情</h2>
						</div>
						<div class="box-content"> 
							<form action="updateDormInfo?dormId=${findDormInfoById.dormId!}" method="post" name="myForm">
								<table class="table-striped table-bordered">
									<tr role="row">
										<td><label class="control-label" for="inputIcon">宿舍楼名称:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<input id="dormFloorName" onkeydown="checkDormFloorName();" name="dormFloorName" type="text" value="${findDormInfoById.dormFloorName?if_exists}" class="form-control" >
											</div><span id="nameDiv"></span>
										</td>
										<td><label class="control-label" for="inputIcon">wifi名称:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<input id="wifiName" name="wifiName" type="text" value="${findDormInfoById.wifiName?if_exists}" class="form-control">
											</div><span id="wifiName1"></span>
										</td>
										<tr>
											<td><label class="control-label" for="inputIcon">MAC地址(wifi唯一标识):</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="wifiRouteid" name="wifiRouteid"  type="text" value="${findDormInfoById.wifiRouteid?if_exists}" class="form-control">
												</div><span id="wifiRouteid1"></span>
											</td>
											<td><label class="control-label" for="inputIcon">宿舍人数:</label></td>
											<td class="center">
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="dormPopulation" onkeydown="checkDormPopulation();" name="dormPopulation" value="${findDormInfoById.dormPopulation?if_exists}"  type="text" class="form-control">
												</div><span id="nameDiv2"></span>
											</td>
										</tr>
										<tr>
											<td><label class="control-label" for="inputIcon">备注:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="dormDesc" onkeydown="checkDormDesc();" name="dormDesc" value="${findDormInfoById.dormDesc?if_exists}" type="text" class="form-control">
												</div><span id="nameDiv3"></span>
											</td>
											<td><label class="control-label" for="inputIcon">宿舍编号:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="dormAddress" onkeydown="checkDormAddress();" type="text" class="form-control" name="dormAddress" value="${findDormInfoById.dormAddress?if_exists}">
												</div><span id="nameDiv4"></span>
											</td>
										</tr>
										<tr>
											<td><label class="control-label" for="inputIcon">签到时间:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="dormSigninStartTime" onkeydown="checkDormSigninStartTime();" name="dormSigninStartTime" value="${findDormInfoById.dormSigninStartTime?if_exists}" type="time" class="form-control" >
												</div><span id="nameDiv5"></span>
											</td>
											<td><label class="control-label" for="inputIcon">截止签到时间:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="dormSigninEndTime" onkeydown="checkDormSigninEndTime();" name="dormSigninEndTime" value="${findDormInfoById.dormSigninEndTime?if_exists}" type="time" class="form-control">
												</div><span id="nameDiv6"></span>
											</td>
										</tr>
										<tr>
											<td><label class="control-label" for="inputIcon">班级:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="className" name="className" value="${findDormInfoById.classes.className?if_exists}" type="text" class="form-control" readonly="true">
												</div>
											</td>
											<td></td>
											<td></td>
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