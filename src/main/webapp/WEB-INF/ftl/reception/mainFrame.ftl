<!DOCTYPE html>
<html lang="en">

	<head>

		<meta charset="utf-8">
		<title>铛铛后台管理</title>
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

	</head>

	<body>
		<!-- topbar starts -->
		<div class="navbar navbar-default" role="navigation">

			<div class="navbar-inner">
				<button type="button" class="navbar-toggle pull-left animated flip">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
				<a class="navbar-brand" href="index.html"> <img alt="Charisma Logo" src="img/logo20.png" class="hidden-xs" />
					<span>铛铛管理后台</span></a>

				<!-- user dropdown starts -->
				<div class="btn-group pull-right">
					<button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs">${name!}</span>
                    <span class="caret"></span>
                </button>
					<ul class="dropdown-menu">
						<li><a href="#">个人中心</a></li>
						<li><a href="logout">退出</a></li>
					</ul>
				</div>
				<!-- user dropdown ends -->

				<ul class="collapse navbar-collapse nav navbar-nav top-menu">
					<li><a href="#"><i class="glyphicon glyphicon-globe"></i> 位置</a></li>
					<li class="dropdown">
						<a href="#" data-toggle="dropdown"><i class="glyphicon glyphicon-star"></i> 收藏</a>
					</li>
					<li>
						<form class="navbar-search pull-left">
							<input placeholder="搜索" class="search-query form-control col-md-10" name="query" type="text">
						</form>
					</li>
				</ul>
			</div>
		</div>
		<!-- topbar ends -->
		<div class="ch-container">
			<div class="row">

				<!-- left menu starts -->
				<div class="col-sm-2 col-lg-2">
					<div class="sidebar-nav">
						<div class="nav-canvas">
							<div class="nav-sm nav nav-stacked">

							</div>
							<ul class="nav nav-pills nav-stacked main-menu">
								<li class="nav-header">Main</li>
								<li><a class="ajax-link" href="index" target="i"><i class="glyphicon glyphicon-home"></i><span> 首页</span></a>
								</li>
								<li class="accordion"><a href="#"><i class="glyphicon glyphicon-eye-open"></i><span> 考勤管理</span></a>
									<ul class="nav nav-pills nav-stacked">
										<li><a href="findClockingList" target="i" onclick="loadIframe()">上课考勤</a></li>
										<li><a href="findDormSigninList" target="i" onclick="loadIframe()">宿舍考勤</a></li>
										<@shiro.hasPermission name="考勤统计">
										<li class="accordion"><a href="#" target="i" onclick="loadIframe()">考勤统计</a>
											<ul class="nav nav-pills nav-stacked">
												<li><a href="departmentAattendanceStatistics" target="i" onclick="loadIframe()">院系考勤汇总表</a><li>
												<li><a href="classAttendanceStatistics" target="i" onclick="loadIframe()">班级考勤汇总表</a><li>
												<li><a href="classAttendanceThedetail" target="i" onclick="loadIframe()">班级考勤明细表</a><li>
												<li><a href="findCoursePeriodOfTimeAttendance" target="i" onclick="loadIframe()">课程考勤汇总表</a><li>
												<li><a href="findCourseTeacherPeriodOfTimeAttendance" target="i" onclick="loadIframe()">老师课程考勤明细表</a><li>
												<li><a href="classCourseAttendanceThedetail" target="i" onclick="loadIframe()">班级课程考勤详情</a><li>
												<li><a href="classTeacherInStatistics" target="i" onclick="loadIframe()">老师考勤汇总表</a><li>
												<li><a href="schoolDormSignInStatistics" target="i" onclick="loadIframe()">宿舍考勤汇总表</a><li>
											</ul>
										<li>
										</@shiro.hasPermission>
									</ul>
								</li>
								<li><a href="findListStu" class="ajax-link" target="i"><i
                                class="glyphicon glyphicon-star"></i><span>学生管理</span></a></li>
								<li class="accordion"><a href="#"><i class="glyphicon glyphicon-hdd"></i><span> 学校管理</span></a>
									<ul class="nav nav-pills nav-stacked">
										<@shiro.hasRole name="系统管理员">
											<li><a href="selectSchoolInfo" target="i" onclick="loadIframe()">学校管理</a></li>
										</@shiro.hasRole>
										<li><a href="findDepartmentInfo" target="i" onclick="loadIframe()">院系管理</a></li>
										<li><a href="findClassList" target="i" onclick="loadIframe()">班级管理</a></li>
										<li><a href="selectAllDormInfo" target="i" onclick="loadIframe()">宿舍管理</a></li>
									</ul>
								</li>
								
								<li><a class="ajax-link" href="findSysUserInfoList" target="i" onclick="loadIframe()"><i class="glyphicon glyphicon-user"></i><span>用户管理</span></a></li>
								<li><a class="ajax-link" href="selectAllCourse" target="i" onclick="loadIframe()"><i class="glyphicon glyphicon-font"></i><span> 课程管理</span></a></li>
								<li><a class="ajax-link" href="selectAllSubjectInfo" target="i" onclick="loadIframe()"><i class="glyphicon glyphicon-th"></i><span> 科目管理</span></a></li>
								<li><a href="findLeaveList" class="ajax-link" target="i"><i class="glyphicon glyphicon-flag"></i><span>请假管理</span></a></li>
								<@shiro.hasPermission name="角色管理">
								<li><a class="ajax-link" href="findRoleList" target="i" onclick="loadIframe()"><i class="glyphicon glyphicon-lock"></i><span>角色管理</span></a></li>
								</@shiro.hasPermission>
								<!--
									<li><a class="ajax-link" href="means" target="i" onclick="loadIframe()"><i class="glyphicon glyphicon-picture"></i><span> 资料共享</span></a>
									</li>
									<li><a class="ajax-link" href="interaction" target="i" onclick="loadIframe()"><i
	                                class="glyphicon glyphicon-align-justify"></i><span> 教师互动</span></a></li>
									<li><a class="ajax-link" href="consult" target="i" onclick="loadIframe()"><i
	                                class="glyphicon glyphicon-th"></i><span> 资讯推送</span></a></li>
									<li><a href="help" target="i" onclick="loadIframe()"><i class="glyphicon glyphicon-globe"></i><span>　帮 助 </span></a></li>
									<li>
										<a class="ajax-link" href="consume" target="i" onclick="loadIframe()">
										<i class="glyphicon glyphicon-star"></i><span> 扫码消费</span></a>
									</li>
	                                <li class="accordion"><a href="#"><i class="glyphicon glyphicon-edit"></i><span> 作业管理</span></a>
										<ul class="nav nav-pills nav-stacked">
											<li><a href="workOnline" target="i" onclick="loadIframe()"> 在线作业</a></li>
											<li><a href="workCount" target="i" onclick="loadIframe()"> 作业统计</a></li>
										</ul>
									</li>
								</ul>
	                    		<li><a href="error"><i class="glyphicon glyphicon-ban-circle"></i><span> 错误页面</span></a>
	                            </li>
	                            <li><a href="login"><i class="glyphicon glyphicon-lock"></i><span> 登录页面</span></a>
	                            </li>
	                    		<label id="for-is-ajax" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>
                     		-->
						</div>
					</div>
				</div>
				<!--/span-->
				<!-- left menu ends -->

				<noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">Warning!</h4>

                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>

				<div id="content" class="col-lg-10 col-sm-10">
					<div id="content">
						<iframe class="ifram" src="index" id="iframe" name="i" width="100%" height="0" style="background-color: white;" runat="server" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes">	
	            </iframe>
						<div>

						</div>
						<!--/row-->
						<!-- content ends -->
					</div>
					<!--/#content.col-md-0-->
				</div>
				<!--/fluid-row-->

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
			<hr>
			<footer class="row">
				<p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; <a href="http://usman.it" target="_blank">江西通友科技</a> 2016</p>

				<p class="col-md-3 col-sm-3 col-xs-12 powered-by">Powered by: <a href="#">TONYOU</a></p>
			</footer>
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
				function reinitIframe() {

					var iframe = document.getElementById("iframe");
					try {
						var bHeight = iframe.contentWindow.document.body.scrollHeight;
						var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
						var height = Math.max(bHeight, dHeight);
						iframe.height = height;
						console.log(height);
					} catch (ex) {}
				}

				function loadIframe() {
					var iframe = document.getElementById("iframe");
					iframe.height = 0;
				}
				window.setInterval("reinitIframe()", 200);
			</script>
	</body>
</html>