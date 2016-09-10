<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>学生管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
    <meta name="author" content="Muhammad Usman">
	<!-- 权限树 -->
	
    <!-- The styles -->
    <link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href="css/charisma-app.css" rel="stylesheet">
    <link href='bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <link rel="shortcut icon" href="img/favicon.ico">
	<!-- jQuery -->
    <script src="bower_components/jquery/jquery.min.js"></script>
</head>

<body>
    
<div class="ch-container">
    <div class="row">
    <ul class="breadcrumb" style="color:#317eac;">
        <li>
            	首页
        </li>
        <li>
           	角色管理
        </li>
    </ul>
</div>
    <div class="row">
    <div class="box col-md-12">
    <div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-user"></i> 角色管理</h2>
    </div>
    <div class="box-content">
    
    <div class="alert-info"> 
    	 <a class="btn btn-round btn-default btn-success" href="sysRoleInfoAddFtl" style="position:relative;">
          	<i class="glyphicon glyphicon-zoom-in icon-white"></i>添加
		 </a>
    </div>
    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
    <thead>
    <tr>
        <th>角色名称</th>
        <th>角色描述</th>
        <th>创建角色时间</th>
        <th>角色拥有权限</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
     	 <#if findRoleList??>
	    	<#list findRoleList as role>
			    <tr>
			        <td>${role.sysRoleName!}</td>
			        <td class="center">
			        	${role.sysRoleDesc!}
			        	 <ul id="treeDemo" class="ztree"></ul>
			    	 	<div id="nodes"></div>	
			        </td>
			        <td class="center">
			        	<#if role.createRoleTime??>
			        		<#assign createRoleTime = role.createRoleTime />
							${createRoleTime?string("yyyy-MM-dd hh:mm:ss")}
			        	</#if>
			        	
			        </td>
			        <td class="center">
			        	<div class="btn-group">
		                    <button class="btn btn-primary btn-sm">权限</button>
		                    <button class="btn dropdown-toggle btn btn-primary btn-sm" data-toggle="dropdown"><span
		                            class="caret"></span></button>
		                    <ul class="dropdown-menu">
		                    	<#if role.sysAuthInfos??>
			                        <#list role.sysAuthInfos as auth>
				                        <li><a href="#"><i class="glyphicon glyphicon-eye-close"></i>${auth.sysAuthName}</a></li>
				                        <li class="divider"></li>
					        		</#list>
					        	</#if>
		                    </ul>
		                </div>
			        	
			        	
			        		
			        </td>
			        <td class="center">
			            <a class="btn btn-info" href="findByIdRole?roleId=${role.sysRoleId!}">
			                <i class="glyphicon glyphicon-edit icon-white"></i>
			              	  详情/修改
			            </a>
			            <a class="btn btn-danger" href="deleteByIdRole?roleId=${role.sysRoleId!}">
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

