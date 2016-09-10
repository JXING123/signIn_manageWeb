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
    	
		//根据学校带出系别
			function getDepartment(){ 
				getSysUser();
				$("#departmentId1").html("<option value=''>-请选择院系-</option>");
				var id = $("#schoolId1").val(); 
				if(id ==undefined){
					id = "";
				}
				$.post("selectAllDepartment",{"schoolId" : id},function callBack(data){
					 var l = eval("("+data+")"); 
					 for(var i=0; i<l.length; i++){
					 	 var deprtmentName=l[i].deprtmentName; 
					 	 var departmentId=l[i].departmentId; 
					 	 //ajax请求回来的数据对下拉框的操作 
					 	 var a="<option value='" +departmentId + "'>" + deprtmentName + "</option>"; 
					 	 $("#departmentId1").append(a); 
					 } 
				}) 
			} 
		//获取学校所有的辅导员信息
			function getSysUser(){ 
				$("#sysUserId").html("<option value=''>-请选择辅导员-</option>");
				var id = $("#schoolId1").val(); 
				if(id ==undefined){
					id = "";
				}
				$.post("findRoleCoachSysUserList",{"schoolId" : id},function callBack(data){
					 var l = eval("("+data+")"); 
					 for(var i=0; i<l.length; i++){
					 	 var sysUserId=l[i].sysUserId; 
					 	 var sysUserName=l[i].sysUserName; 
					 	 //ajax请求回来的数据对下拉框的操作 
					 	 var a="<option value='" +sysUserId + "'>" + sysUserName + "</option>"; 
					 	 $("#sysUserId").append(a); 
					 } 
				}) 
			} 
		//添加班级，后台校验在同一个系别里面不能添加同一个班级
		function checkAddClassinfo(){
			var className = document.getElementById("className").value;
			var xibie = document.getElementById("departmentId1").value;
			$.post("checkAddClassinfo",{"className":className,"departmentId":xibie},function callBack(data){
				var l = eval("("+data+")");
			  	if (l == 0) {
					return true;
	            }else if (l == 1) {
	            	document.getElementById("className").focus();
                    alert("对不起该班级已存在");
                    return false;
	           }
			})
		}
		
		
		//点击删除查询后台
		function deleteClassInfo(n){
			classId = n;
			$.post("selectStuInfoByClassId",{"classId":classId},function callBack(data){
    			var l = eval("("+data+")");
    			if(l == 0){
				 	window.location.href='deleteClassInfo?classId='+classId	;
    			}else{
    				alert("此班级存在学生不能刪除！");
    			};
    		});
		}
	</script>
	<script type="text/javaScript">
		function getParamsClocking(){
			var schoolId = $("#schoolId").val();
			var departmentId = $("#departmentId").val();
			if(schoolId !=undefined && schoolId ==""){
				departmentId="";
			}
			if(schoolId ==undefined){
				schoolId = "";
			}
			
			if(departmentId ==undefined){
				departmentId = "";
			}
			window.location.href="findClassList?schoolId="+schoolId+"&departmentId="+departmentId;
		}
	</script>
	<script type="text/javascript">
			$(function(){ 
					var departmentId = "${departmentId!}";
					if(departmentId!=""){
						$("#departmentId").find("option[value="+departmentId+"]").attr("selected",true);
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
           班级管理
        </li>
    </ul>
</div>
    <div class="row">
    <div class="box col-md-12">
    <div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-user"></i>班级管理</h2>
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
			           系别名称： <select name="departmentId" id="departmentId" data-rel="chosen" onchange="getParamsClocking()">
	           				<option value="">　　全部院系　　</option>
	           				<#if departmentInfoList??>  
						         <#list departmentInfoList as d>  
						         	 <option value="${d.departmentId}">${d.deprtmentName}</option>  
						         </#list>  
					        </#if>  
	            		</select>　　
	            </@shiro.hasAnyRoles>
    	 <a class="btn btn-setting btn-round btn-default btn-success" onclick="getDepartment()" href="javaScript:(void)" style="position:relative;">
          	<i class="glyphicon glyphicon-zoom-in icon-white click_add"></i>添加
		 </a>
    </div>
   
  
    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
    <thead>
    <tr>
    	
    	<th>系别名称</th>
        <th>班级名称</th>
        <th>辅导员名称</th>
        <th>入学 年份</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
     	 <#if findListClassInfo??>
	    	<#list findListClassInfo as c>
			    <tr>
			    	<td><#if c.department??>${(c.department.deprtmentName)!}</#if></td>
			        <td><#if c.className??>${(c.className)!}</#if></td>
			        <td><#if c.sysUser??>${(c.sysUser.sysUserName)!}</#if></td>
			        <td class="center">
			        	<#if c.classYear??>
							${c.classYear}
						</#if>
					</td>
			        <td class="center">
			            <a class="btn btn-info" href="findByIdClass?classId=${c.classId!}">
			                <i class="glyphicon glyphicon-edit icon-white"></i>
			              	  详情/修改
			            </a>
			            <a id="delete" onclick="deleteClassInfo('${c.classId!}');" href="javaScript:(void)" class="btn btn-danger">
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
	                    <h3>班级添加</h3>
	                </div>
	                <form action="addClassInfo" method="post" name="myForm">
		                <div class="modal-body">
		                	<@shiro.hasRole name="系统管理员">
		                			<label class="control-label" for="inputIcon">选择学校：</label>
		                			<div class="input-group">
		                			<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
			          				 <select name="schoolId" required title="请选择学校" id="schoolId1" class="form-control"  style="width:80%" onchange="getDepartment()">
	           							<#if schoolInfoList??>  
						         			<#list schoolInfoList as s>  
						         	 			<option value="${s.schoolId}">${s.schoolName}</option>  
						         			</#list>  
					        			</#if>  
	            					</select>
	            				</div>
	            			</@shiro.hasRole>
		                 	<label class="control-label" for="inputIcon">选择院系：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <select id="departmentId1" class="form-control" name="department.departmentId" required title="请选择系别" style="width:80%">
						    			<option value="">-请选择院系-</option>
							    		<#if departmentInfoList??>  
									         <#list departmentInfoList as g>  
									         	 <option value="${g.departmentId}">${g.deprtmentName}</option>  
									         </#list>
								        </#if> 
						    	</select>
		                    </div>
			                <label class="control-label" for="inputIcon">班级名称：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="className" required title="班级名称不能为空" name="className" onblur="checkAddClassinfo()" type="text" class="form-control" placeholder="班级名称"  style="width:80%"/>
		                    	<span id="nameDiv"></span>
		                    </div>
		                    
		                    <label class="control-label" for="inputIcon">届数：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                    	<span id="nameDiv2"></span>
		                    	<select name="classYear"  class="form-control" required title="请选择班级届数" style="width:80%">
						    			<option  value="">-请选择届数-</option>
						    			<#list 2010 .. 2016 as t>
											<option value="${t}届">${t}届</option>  
										</#list>
						    	</select>
		                    </div>
		                    <label class="control-label" for="inputIcon">班级负责人（辅导员）：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <select id="sysUserId" class="form-control" name="sysUser.sysUserId" required title="班级负责人不能为空(如果不存在，请添加)" style="width:80%">
						    			<option  value="">-请选择辅导员-</option>
							    		
						    	</select>
		                    </div>
	                	</div>
		                <div class="modal-footer">
		                    <a href="#" class="btn btn-default" data-dismiss="modal">关闭</a>
		                    <button type="submit" id="disM"  class="btn btn-primary">保存</button>
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

