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
    <title>关系</title>
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
		
	</script>
	<script type="text/javaScript">
		function getParamsClocking(){
			var schoolId = $("#schoolId").val();
			var departmentId = $("#departmentId").val();
			var classId = $("#classId").val();
			if(schoolId !=undefined && schoolId ==""){
				departmentId="";
				classId="";
			}
			if(departmentId !=undefined && departmentId ==""){
				classId="";
			}
			if(schoolId ==undefined){
				schoolId = "";
			}
			
			if(departmentId ==undefined){
				departmentId = "";
			}
			window.location.href="selectAllCourse?schoolId="+schoolId+"&departmentId="+departmentId+"&classId="+classId;
		}
		
		//某个学校所有的任课老师
		function selectSysUser(){
			selectAllClasses();
			$("#sysUserId").html("<option value=''>请选择任课教师</option>");
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
    		})
		}
		//查询所有班级
		function selectAllClasses(){
			$("#classId1").html("<option value=''>请选择班级</option>");
			var schoolId = $("#schoolId1").val();
			$.post("selectAllClasses",{"schoolId":schoolId},function callBack(data){
    			var l = eval("("+data+")");
    			for(var i=0; i<l.length; i++){
    				var className = l[i].className;
    				var classId = l[i].classId;
    				//ajax请求回来的数据对下拉框的操作
        			var a = "<option value="+classId +">" + className +"</option>";
        			$("#classId1").append(a);
    			}
    		})
		}
	</script>
	<script type="text/javascript">
			$(function(){ 
					var departmentId = "${departmentId!}";
					if(departmentId!=""){
						$("#departmentId").find("option[value="+departmentId+"]").attr("selected",true);
					}
					var classId = "${classId!}";
					if(classId!=""){
						$("#classId").find("option[value="+classId+"]").attr("selected",true);
					}
					var schoolId = "${schoolId!}";
					if(schoolId!=""){
						$("#schoolId").find("option[value="+schoolId+"]").attr("selected",true);
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
            课程管理
        </li>
    </ul>
</div>
    <div class="row">
    <div class="box col-md-12">
    <div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-user"></i> 　课程管理</h2>
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
    	 <a class="btn btn-setting btn-round btn-default btn-success" href="#" onclick="selectSysUser()" style="position:relative;" onclick="selectAllSubject()">
          	<i class="glyphicon glyphicon-zoom-in icon-white click_add"></i>添加
		 </a>
    </div>
  
    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
    <thead>
    <tr>
    	<th>班级</th>
        <th>星期</th>
        <th>任课老师名称</th>
        <th>科目名称</th>
        <th>教室</th>
        <th>教学楼</th>
        <th>节次</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
     	 <#if courseList??>
	    	<#list courseList as s>
			    <tr>
			    	<td class="center"><#if s.classes??>${(s.classes.className)!}</#if></td>
			        <td class="center"><#if s.week??>${(s.week)!}</#if></td>
			        <td><#if s.sysUser??>${(s.sysUser.sysUserName)!}</#if></td>
			        <td><#if s.subject.subjectName??>${(s.subject.subjectName)!}</#if></td>
			        <td class="center"><#if s.classRoom??>${(s.classRoom)!}</#if></td>
			        <td class="center"><#if s.schoolBuilding??>${(s.schoolBuilding)!}</#if></td>
			        <td class="center"><#if s.setSuji??>${(s.setSuji)!}</#if></td>
			        <td class="center"><#if s.startTime??>${(s.startTime)!}</#if></td>
			        <td class="center"><#if s.endTime??>${(s.endTime)!}</#if></td>
			        <td class="center">
			            <a class="btn btn-info" href="findCourseInfoById?courseId=${s.courseId!}">
			                <i class="glyphicon glyphicon-edit icon-white"></i>
			              	  详情/修改
			            </a>
			            <a class="btn btn-danger" href="deleteCourseInfo?courseId=${s.courseId!}">
			                <i class="glyphicon glyphicon-trash icon-white"></i>
			                	删除
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

    </div><!--/row-->

   <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
	
        <div class="modal-dialog">
        	
	            <div class="modal-content">
	                <div class="modal-header center">
	                    <button type="button" class="close" data-dismiss="modal">×</button>
	                    <h3>课程添加</h3>
	                </div>
	                <form action="addCourseInfo" method="post">
		                <div class="modal-body">
			                <label class="control-label" for="inputIcon">科目：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <select  name="subject.subjectId" id="kemu" class="form-control" required title="必须选择科目" style="width:80%">
									 <#if subjectInfos??>
										<#list subjectInfos as subjectInfo>
											<option value="${subjectInfo.subjectId!}">${subjectInfo.subjectName!}</option>
										</#list>
									</#if>
						    	</select>
		                    </div>
		                    <@shiro.hasRole name="系统管理员">
										<label class="control-label" for="inputIcon">学校：</label>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<select name="school.schoolId" id="schoolId1" class="form-control" onchange="selectSysUser();" required title="学校不能为空" style="width:80%">
														<#if schoolInfoList??>
															<#list schoolInfoList as school>
																<option value="${school.schoolId!}">${school.schoolName!}</option>
															</#list>
														</#if>
											    	</select>	
											</div>
							</@shiro.hasRole>
							<label class="control-label" for="inputIcon">班级：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <select  name="classes.classId" id="classId1" class="form-control" required title="请选择班级"  style="width:80%">
									 <option value="">请选择班级</option>
						    	</select>
		                    </div>
		                    <label class="control-label" for="inputIcon">请选择任课教师：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <select name="sysUser.sysUserId" id="sysUserId" class="form-control" required title="任课老师不能为空" style="width:80%">
									 <option value="">请选择任课教师</option>
						    	</select>
		                    </div>
		                    <label class="control-label" for="inputIcon">星期：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <select name="week" id="xingqi" class="form-control" style="width:80%">
									 <option value="星期一">星期一</option>
									 <option value="星期二">星期二</option>
									 <option value="星期三">星期三</option>
									 <option value="星期四">星期四</option>
									 <option value="星期五">星期五</option>
									 <option value="星期六">星期六</option>
									 <option value="星期日">星期日</option>
						    	</select>
		                    </div>
		                    
		                    
		                    <label class="control-label" for="inputIcon">教学楼：</label>
		                	<div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="schoolBuilding" name="schoolBuilding" required title="请填写教学楼名称" type="text" class="form-control" placeholder="教学楼"  style="width:80%"/>
		                   		<span id="nameDiv"></span>
		                    </div>
		                    
		                    
		                    <label class="control-label" for="inputIcon">教室：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="classRoom" name="classRoom" required title="教室不能为空" type="text" class="form-control" placeholder="教室"  style="width:80%"/>
		                    	<span id="nameDiv2"></span>
		                    </div>
		                    
		                    <label class="control-label" for="inputIcon">周次：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="weekNext" name="weekNext" required title="周次不能为空" type="text" class="form-control" placeholder="周次"  style="width:80%"/>
		                    	<span id="nameDiv3"></span>
		                    </div>
		                    
		                    <label class="control-label" for="inputIcon">节次：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="setSuji" name="setSuji" required title="节次不能为空" type="text" class="form-control" placeholder="节次"  style="width:80%"/>
		                    	<span id="nameDiv4"></span>
		                    </div>
		                    
		                    <label class="control-label" for="inputIcon">上课开始时间：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="startTime" name="startTime" required title="请选择开始时间" type="time" class="form-control" placeholder="开始时间"  style="width:80%">
		                    	<span id="nameDiv5"></span>
		                    </div>
		                    
		                    <label class="control-label" for="inputIcon">结束时间：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="endTime" name="endTime" required title="请选择结束时间" type="time" class="form-control" placeholder="结束时间"  style="width:80%">
		                    	<span id="nameDiv6"></span>
		                    </div>
		                    
		                    
		                    
		                    <label class="control-label" for="inputIcon">wifi名称：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="wifiName" name="wifiName" required  title="请输入该教室的wifi名称" type="text" class="form-control" placeholder="wifi名称"  style="width:80%">
		                    	<span id="nameDiv7"></span>
		                    </div>
		                    
		                    <label class="control-label" for="inputIcon">MAC地址：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="wifiRouteId" name="wifiRouteId" required  title="请输入MAC地址" type="text" class="form-control" placeholder="wifi唯一标识"  style="width:80%">
		                    	<span id="nameDiv8"></span>
		                    </div>
	                	</div>
		                <div class="modal-footer">
		                    <a href="#" class="btn btn-default" data-dismiss="modal">关闭</a>
		                    <button type="submit" id="disM" class="btn btn-primary" >保存</button>
		                </div>
                </form>
            </div>
        </div>
    </div>
 
    

</div><!--/.fluid-container-->

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

