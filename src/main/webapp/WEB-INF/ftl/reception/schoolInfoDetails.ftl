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
	<script type="text/javascript" src="js/lib/jquery.poshytip.js"></script>
	<script type='text/javascript' src='js/lib/jq.validate.js'></script>
	<link rel="stylesheet" href="js/lib/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
	
	</head>
	<body>
		<div class="ch-container">
			<div class="row">

				<ul class="breadcrumb" style="color:#317eac;">
					<li>
						首页
					</li>
					<li>
						学校详细信息
					</li>
				</ul>
			</div>
			<div class="row">
				<div class="box col-md-12">
					<div class="box-inner">
						<div class="box-header well" data-original-title="">
							<h2><i class="glyphicon glyphicon-user"></i>学校详情</h2>
						</div>
						<div class="box-content">
							<form action="updateSchoolInfo?schoolId=${findByIdSchool.schoolId!}" method="post"  name="myForm" onsubmit="return check()">
								<table class="table-striped table-bordered">
									<tr role="row">
										<td><label class="control-label" for="inputIcon">学校编号:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<input id="inputIcon" type="text" value="${findByIdSchool.schoolId?if_exists}" class="form-control" readonly="true">
											</div>
										</td>
										<td><label class="control-label" for="inputIcon">学校地址:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<input id="schoolAddress" valType="required" msg="<font color=red>*</font>学校地址不能为空" name="schoolAddress"  type="text" value="${findByIdSchool.schoolAddress?if_exists}" class="form-control">
											</div><span id="nameDiv"></span>
										</td>
										<tr>
											<td><label class="control-label" for="inputIcon">学校名称:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="schoolName" valType="required"  readonly="true" type="text" name="schoolName" value="${findByIdSchool.schoolName?if_exists}" class="form-control">
												</div><span id="nameDiv2"></span>
											</td>
											<td><label class="control-label" for="inputIcon">学校信息:</label></td>
											<td class="center">
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<textarea class="autogrow" name="schoolMessage" valType="required" msg="<font color=red>*</font>学校信息不能为空" type="text" class="form-control">${findByIdSchool.schoolMessage?if_exists}</textarea>
												</div><span id="nameDiv3"></span>
											</td>
										</tr>
										<tr>
											<td><label class="control-label" for="inputIcon">学校电话:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="schoolTel" valType="OTHER" regstring="^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$" msg="<font color=red>*</font>电话号码格式不正确" name="schoolTel" value="${findByIdSchool.schoolTel?if_exists}" type="text" class="form-control">
												</div><span id="nameDiv4"></span>
											</td>
											<td><label class="control-label" for="inputIcon">创建学校时间:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="inputIcon" type="text" class="form-control" name="stuIdCard" value="${schStarDate?if_exists}" readonly="true">
												</div>
											</td>
										</tr>
										<tr>
											<td><label class="control-label" for="inputIcon">学校代码:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="inputIcon" value="${findByIdSchool.schoolCode?if_exists}" type="text" class="form-control" readonly="true">
												</div>
											</td>
											<td> </td>
											<td>
											</td>
										</tr>
											<tr>
												<td colspan="2"><button type="submit" class="btn btn-warning">更新</button></td>
												<td colspan="2">
													<button type="button" class="btn btn-primary" onclick="history.go(-1)">返回</button>
												</td>
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