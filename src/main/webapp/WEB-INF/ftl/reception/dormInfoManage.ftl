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
    <script type="text/javascript" src="js/lib/jquery.poshytip.js"></script>
	
	<link rel="stylesheet" href="js/lib/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />	
    	
	<script type="text/javaScript">
		//点击删除查询后台
		function deleteDormInfo(n){
			dormId = n;
			$.post("selectStuInfoByDormId",{"dormId":dormId},function callBack(data){
    			var l = eval("("+data+")");
    			if(l == 0){
				 	window.location.href='deleteDormInfo?dormId='+dormId;
    			}else{
    				alert("此宿舍存在学生不能刪除！");
    			};
    		});
		}
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
			window.location.href="selectAllDormInfo?schoolId="+schoolId+"&departmentId="+departmentId+"&classId="+classId;
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
            宿舍管理
        </li>
    </ul>
</div>
    <div class="row">
    <div class="box col-md-12">
    <div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-user"></i> 　宿舍管理</h2>
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
    	 <a class="btn  btn-round btn-default btn-success" href="addDormInfoFtl" style="position:relative;">
          	<i class="glyphicon glyphicon-zoom-in icon-white "></i>添加
		 </a>
    </div>
    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
    <thead>
    <tr>
    	<th>宿舍班级</th>
        <th>宿舍楼名称</th>
        <th>wifi名称</th>
        <th>备注</th>
        <th>宿舍人数</th>
        <th>开始签到时间</th>
        <th>截止签到时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
     	 <#if dormInfoList??>
	    	<#list dormInfoList as d>
			    <tr>
			    	<td class="center"><#if d.classes??>${(d.classes.className)!}</#if></td>
			        <td class="center"><#if d.dormFloorName??>${(d.dormFloorName)!}</#if></td>
			        <td><#if d.wifiRouteid??>${(d.wifiName)!}</#if></td>
			        <td class="center"><#if d.dormDesc??>${(d.dormDesc)!}</#if></td>
			        <td class="center"><#if d.dormPopulation??>${(d.dormPopulation)!}</#if></td>
			        <td class="center"><#if d.dormSigninStartTime??>${(d.dormSigninStartTime)!}</#if></td>
			        <td class="center"><#if d.dormSigninEndTime??>${(d.dormSigninEndTime)!}</#if></td>
			        <td class="center">
			            <a class="btn btn-info" href="findDormInfoById?dormId=${d.dormId!}">
			                <i class="glyphicon glyphicon-edit icon-white"></i>
			              	  详情/修改
			            </a> 
			            <a class="btn btn-danger" href="javaScript:(void)" onclick="deleteDormInfo('${d.dormId!}');">
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
</body>
</html>

