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
		<script type="text/javascript">
			$(function(){ 
				<#if findByIdStu.dorm??>
					var dormFloor = "${findByIdStu.dorm.dormId!}";
					$("#dormFloorName").find("option[value="+dormFloor+"]").attr("selected",true);
				</#if>
				
				var cla = "${findByIdStu.classes.classId!}";
				$("#className").find("option[value="+cla+"]").attr("selected",true);
				
			}); 
			
			
		
		</script>	
		<script type="text/javascript">
			$(function(){ 
					var stuSex = "${findByIdStu.stuSex!}";
					$("#stuSex").find("option[value="+stuSex+"]").attr("selected",true);
					
					<#if findByIdStu.dorm??>
						var dormId = "${findByIdStu.dorm.dormId!}";
						$("#dormId").find("option[value="+dormId+"]").attr("selected",true);
					</#if>
					<#if findByIdStu.classes??>
						var classId = "${findByIdStu.classes.classId!}";
						$("#classId").find("option[value="+classId+"]").attr("selected",true);
					</#if>
			}); 
	</script>	
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
						学生详细信息
					</li>
				</ul>
			</div>
			<div class="row">
				<div class="box col-md-12">
					<div class="box-inner">
						<div class="box-header well" data-original-title="">
							<h2><i class="glyphicon glyphicon-user"></i>学生信息详情</h2>
						</div>
						<div class="box-content">
							<form action="updateStuInfo?stuId=${findByIdStu.stuId!}" method="post" name="myForm">
								<table class="table-striped table-bordered">
									<tr role="row">
										<td><label class="control-label" for="inputIcon">学号:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<input id="inputIcon" type="text" value="${findByIdStu.stuId?if_exists}" class="form-control" readonly="true">
											</div>
										</td>
										<td><label class="control-label" for="inputIcon">学生电话:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<input id="stuTel" name="stuTel" valType="MOBILE" msg="<font color=red>*</font>手机格式不正确"  type="text" value="${findByIdStu.stuTel?if_exists}" class="form-control">
											</div><span id="nameDiv"></span>
										</td>
										<tr>
											<td><label class="control-label" for="inputIcon">学生姓名:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="inputIcon" value="${findByIdStu.stuName?if_exists}" type="text"  valType="required" msg="<font color=red>*</font>学生姓名不能为空" class="form-control">
												</div>
											</td>
											<td><label class="control-label" for="inputIcon">身份证:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="stuIdCard" type="text" valType="IDENTITY" msg="<font color=red>*</font>身份证号码格式不正确" class="form-control" name="stuIdCard" value="${findByIdStu.stuIdCard?if_exists}">
												</div><span id="nameDiv2"></span>
											</td>
										</tr>
										<tr>
											<td><label class="control-label" for="inputIcon">性别:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<select name="stuSex" id="stuSex" class="form-control">
														 <option value="男">男</option> 
														 <option value="女">女</option> 
											    	</select>
												</div>
											</td>
											<td><label class="control-label" for="inputIcon">出生日期:</label></td>
											<script src="js/laydate/laydate.js"></script>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input name="stuBirthday" onclick="laydate()" valType="required" msg="<font color=red>*</font>出生日期不能为空"  placeholder="请选择日期"  value="${findByIdStu.stuBirthday?if_exists}" type="text" style="width:95%;height:36px;" class="laydate-icon form-control">
												</div>
											</td>
											
										</tr>
										<tr>
											<td><label class="control-label" for="inputIcon">民族:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="stuNative" valType="required" msg="<font color=red>*</font>民族不能为空" value="${findByIdStu.stuNative?if_exists}" type="text" class="form-control" name="stuNative">
												</div><span id="nameDiv3"></span>
											</td>
											<td><label class="control-label" for="inputIcon">班级:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<select name="classes.classId" id="classId" class="form-control" valType="required" msg="<font color=red>*</font>所在班级不能为空">
														<option value="">无</option>  
														 <#if classList??>  
													         <#list classList as g>  
													         	 <option value="${g.classId}">${g.className}</option>  
													         </#list>  
												        </#if>  
											    	</select>
												
												</div>
											</td>
										</tr>
										<tr align="center">
											<td><label class="control-label" for="inputIcon">地址:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="stuAddress" valType="required" msg="<font color=red>*</font>地址不能为空" value="${findByIdStu.stuAddress?if_exists}" type="text" class="form-control" name="stuAddress">
												</div><span id="nameDiv4"></span>
											</td>
											<td><label class="control-label" for="inputIcon">邮箱:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="inputIcon" valType="MAIL" msg="<font color=red>*</font>邮箱格式不正确" value="${findByIdStu.stuEmail?if_exists}" type="text" class="form-control" name="stuEmail">
												</div>
											</td>
										</tr>
										<tr>
											<td><label class="control-label" for="inputIcon">宿舍:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<select name="dorm.dormId" id="dormId" class="form-control" valType="required" msg="<font color=red>*</font>所在宿舍不能为空">
														<option value="">无</option>  
														 <#if dormList??>  
													         <#list dormList as d>  
													         	 <option value="${d.dormId}">${d.dormAddress}</option>  
													         </#list>  
												        </#if>  
											    	</select>
												
												</div>
											</td>
											<td><label class="control-label" for="inputIcon">审核状态:</label></td>
											<td>
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<input id="inputIcon" value="${findByIdStu.stuState?if_exists}" type="text" class="form-control" readonly="true">
												</div>
											</td>
											</tr>
											<tr>
												<td colspan="2" style="vertical-align:middle; text-align:center;"><button type="submit" class="btn btn-warning">更新</button></td>
												<td colspan="2" style="vertical-align:middle; text-align:center;">
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
		<script>
			laydate({
			    elem: '#selDate',
			    format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
			    festival: true, //显示节日
			    min: laydate.now(-365), //-1代表昨天，-2代表前天，以此类推
			    istoday: true, //是否显示今天
    			max: laydate.now(+0), //+1代表明天，+2代表后天，以此类推
			    istime: true,//验证格式
			    choose: function(datas){ //选择日期完毕的回调
			    	getParamsClocking()
			    }
			});
	</script>
	</body>
	
</html>