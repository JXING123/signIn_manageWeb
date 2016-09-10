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
			$(function() {
				var departmentId = "${departmentId!}";
				if (departmentId != "") {
					$("#departmentId").find("option[value=" + departmentId + "]").attr("selected", true);
				}
				var classId = "${classId!}";
				if (classId != "") {
					$("#classId").find("option[value=" + classId + "]").attr("selected", true);
				}
				var courseId = "${courseId!}";
				if (courseId != "") {
					$("#courseId").find("option[value=" + courseId + "]").attr("selected", true);
				}
				var time = "${time!}";
				$("#time").attr("value",time);
			});
			//模态框居中的控制
			function centerModals() {
				$('.modal').each(function(i) { //遍历每一个模态框
					var $clone = $(this).clone().css('display', 'block').appendTo('body');
					var height = window.screen.availHeight / 6; //屏幕可用工作区高度
					var scrollTop = window.parent.document.documentElement.scrollTop;
					if (scrollTop == 0) {
						scrollTop = window.parent.document.body.scrollTop;
					}
					//var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);
					top = top > 0 ? top : 0;
					$clone.remove();
					$(this).find('.modal-content').css("margin-top", scrollTop + height); //修正原先已经有的30个像素
				});
			}

			$(function() {
				$('#myModal').on('show.bs.modal', function() {
					centerModals();
				});
			});
			//根据系别带出所属班级 
			function getClass() {
				$("#classId").html("<option value=''>-请选择班级-</option>");
				$("#courseId").html("<option value=''>-请选择课程-</option>");
				$("#time").attr("value", "");
				var id = $("#departmentId").val();
				$.post("getClass", {
					"departmentId": id
				}, function callBack(data) {
					var l = eval("(" + data + ")");
					for (var i = 0; i < l.length; i++) {
						var className = l[i].className;
						var classId = l[i].classId;
						//ajax请求回来的数据对下拉框的操作 
						var a = "<option value='" + classId + "'>" + className + "</option>";
						$("#classId").append(a);
					}
				})
			}
			//获取班级某天的课程信息
			function getClassTimeCourse() {
				var time = $("#time").val();
				var classId = $("#classId").val();
				$("#courseId").html("<option value=''>-请选择课程-</option>");
				$.post("findCourseByClassIdAndTime", {
					"classId": classId,
					"time": time
				}, function callBack(data) {
					var l = eval("(" + data + ")");
					for (var i = 0; i < l.length; i++) {
						var courseId = l[i].courseId;
						var subjectName = l[i].subject.subjectName;
						var sysUserName = l[i].sysUser.sysUserName;
						var startTime = l[i].startTime;
						var endTime = l[i].endTime;
						//ajax请求回来的数据对下拉框的操作 
						var a = "<option value='" + courseId + "'>"+sysUserName+":"+ subjectName + "(" + startTime + "-" + endTime + ")" + "</option>";
						$("#courseId").append(a);
					}
				})
			}

			function getClassStatistics() {
				var time = $("#time").val();
				var courseId = $("#courseId").val();
				var departmentId = $("#departmentId").val();
				var classId = $("#classId").val();
				if (classId == '') {
					alert('请选择班级，如果该系别没有请添加！');
				} else if (time == '') {
					alert("请选择上课时间");
				} else if (courseId == '') {
					alert("请选择课程，如果该班级没有请添加！");
				} else {
					window.location.href = "findCourseTimeAttendance?departmentId=" + departmentId + "&time=" + time + "&classId=" + classId + "&courseId=" + courseId;
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
							<h2><i class="glyphicon glyphicon-edit"></i>班级课程详细情况</h2>
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
				            		<#if classInfos??>  
						         			<#list classInfos as c>  
						         	 			<option value="${c.classId}">${c.className}</option> 
						         			</#list>  
					        			</#if> 
				            	</select>
								<input name="time" id="time" placeholder="上课日期" class="btn laydate-icon">
								<select name="courseId" id="courseId" class="btn btn-default btn-sm">
				            		<option value="">请选择课程</option>
				            		<#if courseInfos??>  
						         			<#list courseInfos as c>  
						         	 			<option value="${c.courseId}">${c.sysUser.sysUserName} ${c.subject.subjectName}(${c.startTime}-${c.endTime})</option>
						         			</#list>  
					        		</#if>   
				            	</select>
								<button type="button" class="btn btn-primary btn-sm" onclick="getClassStatistics()">查询</button>
								<button type="button" class="btn btn-default btn-sm" onclick="history.go(-1)">返回</button>

							</div>
							<div class="row">
								<div class="box col-md-6">
									<div class="box-inner">
										<div class="box-header well" data-original-title="">
											<h2>旷课</h2>
											<div class="box-icon">
												<a href="#" class="btn btn-setting btn-round btn-default"><i
                                class="glyphicon glyphicon-cog"></i></a>
												<a href="#" class="btn btn-minimize btn-round btn-default"><i
                                class="glyphicon glyphicon-chevron-up"></i></a>
												<a href="#" class="btn btn-close btn-round btn-default"><i
                                class="glyphicon glyphicon-remove"></i></a>
											</div>
										</div>
										<div class="box-content">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>学号</th>
														<th>姓名</th>
													</tr>
												</thead>
												<tbody>
													<#if kuangkeList ??>
														<#if (kuangkeList?size==0) >
															<tr><td class="center" colspan="2">情况很好，没有旷课记录！</td></tr>
															<#else>
																<#list kuangkeList as kk>
																	<tr>
																		<td>${kk.stuId}</td>
																		<td class="center">${kk.stuName}</td>
																	</tr>
															</#list>
														</#if>
														<#else>
														<tr><td class="center" colspan="2">情况很好，没有旷课记录！</td></tr>
													</#if>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<!--/span-->

								<div class="box col-md-6">
									<div class="box-inner">
										<div class="box-header well" data-original-title="">
											<h2>迟到</h2>

											<div class="box-icon">
												<a href="#" class="btn btn-setting btn-round btn-default"><i
                                class="glyphicon glyphicon-cog"></i></a>
												<a href="#" class="btn btn-minimize btn-round btn-default"><i
                                class="glyphicon glyphicon-chevron-up"></i></a>
												<a href="#" class="btn btn-close btn-round btn-default"><i
                                class="glyphicon glyphicon-remove"></i></a>
											</div>
										</div>
										<div class="box-content">
											<table class="table table-condensed">
												<thead>
													<tr>
														<th>学号</th>
														<th>姓名</th>
														<th>迟到时长</th>
													</tr>
												</thead>
												<tbody>
													<#if chidaoList ??>
														<#if (chidaoList?size==0) >
															<tr><td class="center">情况很好，没有迟到记录！</td></tr>
															<#else>
																<#list chidaoList as kk>
																	<tr>
																	<td class="center">${kk.stuId}</td>
																	<td class="center">${kk.stuName}</td>
																	<td class="center">${kk.date}</td>
																</tr>
															</#list>
														</#if>
														
														<#else>
														<tr><td class="center">情况很好，没有迟到记录！</td></tr>
													</#if>	
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<!--/span-->
							</div>
							<!--/row-->
							<div class="row">
								<div class="box col-md-6">
									<div class="box-inner">
										<div class="box-header well" data-original-title="">
											<h2>早退</h2>

											<div class="box-icon">
												<a href="#" class="btn btn-setting btn-round btn-default"><i
                                class="glyphicon glyphicon-cog"></i></a>
												<a href="#" class="btn btn-minimize btn-round btn-default"><i
                                class="glyphicon glyphicon-chevron-up"></i></a>
												<a href="#" class="btn btn-close btn-round btn-default"><i
                                class="glyphicon glyphicon-remove"></i></a>
											</div>
										</div>
										<div class="box-content">
											<table class="table">
												<thead>
													<tr>
														<th>学号</th>
														<th>姓名</th>
														<th>签到时间</th>
														<th>早退时长</th>
													</tr>
												</thead>
												<tbody>
													<#if zaotuiList ??>
														<#if (zaotuiList?size==0) >
																<tr><td class="center">情况很好，没有早退记录！</td></tr>
															<#else>
																<#list zaotuiList as kk>
																	<tr>
																		<td class="center">${kk.stuId}</td>
																		<td class="center">${kk.stuName}</td>
																		<td class="center">${kk.date}</td>
																	</tr>
															</#list>
														</#if>
														<#else>
														<tr><td class="center">情况很好，没有早退记录！</td></tr>
													</#if>	
												</tbody>
											</table>

										</div>
									</div>
								</div>
								<!--/span-->

								<div class="box col-md-6">
									<div class="box-inner">
										<div class="box-header well" data-original-title="">
											<h2>缺卡</h2>
											<div class="box-icon">
												<a href="#" class="btn btn-setting btn-round btn-default"><i
                                class="glyphicon glyphicon-cog"></i></a>
												<a href="#" class="btn btn-minimize btn-round btn-default"><i
                                class="glyphicon glyphicon-chevron-up"></i></a>
												<a href="#" class="btn btn-close btn-round btn-default"><i
                                class="glyphicon glyphicon-remove"></i></a>
											</div>
										</div>
										<div class="box-content">
											<table class="table table-striped">
												<thead>
													<tr>
														<th>学号</th>
														<th>姓名</th>
														<th>缺卡</th>
													</tr>
												</thead>
												<tbody>
													<#if queKa ??>
														<#if (queKa?size==0) >  
																<tr><td class="center" colspan="3">情况很好，没有缺卡记录！</td></tr>
															<#else>
																<#list queKa as kk>
																	<tr>
																		<td class="center">${kk.stuId}</td>
																		<td class="center">${kk.stuName}</td>
																		<td class="center">${kk.state}</td>
																	</tr>
																</#list>
														</#if>
														<#else>
														<tr><td class="center" colspan="3">情况很好，没有缺卡记录！</td></tr>
													</#if>	
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<!--/span-->
							</div>
							<!--/row-->
							<div class="row">
								<div class="box col-md-12">
									<div class="box-inner">
										<div class="box-header well" data-original-title="">
											<h2>请假信息</h2>

											<div class="box-icon">
												<a href="#" class="btn btn-setting btn-round btn-default"><i
                                class="glyphicon glyphicon-cog"></i></a>
												<a href="#" class="btn btn-minimize btn-round btn-default"><i
                                class="glyphicon glyphicon-chevron-up"></i></a>
												<a href="#" class="btn btn-close btn-round btn-default"><i
                                class="glyphicon glyphicon-remove"></i></a>
											</div>
										</div>
										<div class="box-content">
											<table class="table table-bordered table-striped table-condensed">
												<thead>
													<tr>
														<th>学号</th>
														<th>姓名</th>
														<th>请假时间</th>
														<th>请假类型</th>
														<th>请假事由</th>
													</tr>
												</thead>
												<tbody>
													<#if leaveInfos ??>
														<#if (leaveInfos?size==0) >
															<tr><td class="center" colspan="5">没有请假记录！</td></tr>
															<#else>  
															<#list leaveInfos as l>
																<tr>
																	<td class="center">${l.stu.stuId}</td>
																	<td class="center">${l.stu.stuName}</td>
																	<td class="center">${l.startLeaveTime}-${l.endLeaveTime}</td>
																	<td class="center">${l.leaveType}/td>
																	<td class="center">${l.leaveDesc}/td>
																</tr>
															</#list>
														</#if>
														<#else>
														<tr><td class="center" colspan="5">没有请假记录！</td></tr>
													</#if>	
												</tbody>
											</table>

										</div>
									</div>
								</div>
							</div>
							<!--/span-->

							<!-- content ends -->
						</div>
						<!--/#content.col-md-0-->
					</div>
					<!--/fluid-row-->

				</div>
	</body>
	<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script>
		laydate({
			elem: '#time',
			format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
			festival: true, //显示节日
			min: laydate.now(-365), //-1代表昨天，-2代表前天，以此类推
			istoday: true, //是否显示今天
			max: laydate.now(+0), //+1代表明天，+2代表后天，以此类推
			istime: true, //验证格式
			choose: function(datas) { //选择日期完毕的回调
				getClassTimeCourse();
			}
		});
	</script>
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