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
    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">
	<!-- jQuery -->
	<script type="text/javascript" src="js/lib/jquery.poshytip.js"></script>
	<script type='text/javascript' src='js/lib/jq.validate.js'></script>
	<link rel="stylesheet" href="js/lib/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
	<script src="js/jquery-1.8.3.min.js"></script>
	
    
    
    <script type="text/javaScript">
    	//添加学校，不能添加名称相同的学校
		function checkAddSchoolinfo(){
			var schoolName = document.getElementById("schoolName").value;
			var flag = false;
			$.ajax({
                     type:"post",
                     dataType:"json",
                     async:false,//同步方式(异步加载)
                     url:"checkAddSchoolinfo?schoolName="+schoolName,
                     success:function(dataResult){
                         if (dataResult != '1') {
                    		flag = true;
	           			}else{
	           				alert("对不起该学校已存在");
	           				document.getElementById("schoolName").focus();
	           				flag = false;
	           			}
                     }
                 });
        	return flag;
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
            学校管理
        </li>
    </ul>
</div>
    <div class="row">
    <div class="box col-md-12">
    <div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-user"></i>学校管理</h2>
    </div>
    <div class="box-content">
    
    <div class="alert alert-info"> 
    	 <a class="btn btn-setting btn-round btn-default btn-success" href="#" style="position:relative;">
          	<i class="glyphicon glyphicon-zoom-in icon-white click_add"></i>添加
		 </a>
    </div>
   
  
    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
    <thead>
    <tr>
        <th>学校名称</th>
        <th>学校地址</th>
        <th>学校电话</th>
        <th>创校时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
     	 <#if selectSchoolList??>
	    	<#list selectSchoolList as s>
			    <tr>
			        <td><#if s.schoolName??>${(s.schoolName)!}</#if></td>
			        <td class="center"><#if s.schoolAddress??>${(s.schoolAddress)!}</#if></td>
			        <td class="center"><#if s.schoolTel??>${(s.schoolTel)!}</#if></td>
			        <td class="center"><#if s.schoolStartTime??>${s.schoolStartTime!}</#if></td>
			        <td class="center">
			            <a class="btn btn-info" href="findByIdSchool?schoolId=${s.schoolId!}">
			                <i class="glyphicon glyphicon-edit icon-white"></i>
			              	  详情/修改
			            </a>
			            <a class="btn btn-danger" href="deleteSchool?schoolId=${s.schoolId!}">
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
	                    <h3>学校信息添加</h3>
	                </div>
	                <form action="addSchool" method="post" name="myForm" onsubmit="return checkAddSchoolinfo()">
		                <div class="modal-body">
			                <label class="control-label" for="inputIcon">学校名称：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="schoolName" name="schoolName" required title="学校名称不能为空" type="text" class="form-control" placeholder="学校名称"  style="width:80%"/>
		                    	<span id="nameDiv"></span>
		                    </div>
		                    
		                    <label class="control-label" for="inputIcon">学校地址：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="schoolAddress" name="schoolAddress"  required title="学校地址必填" type="text" class="form-control" placeholder="学校地址"  style="width:80%"/>
		                   		<span id="nameDiv2"></span>
		                    </div>
		                    
		                    
		                    <label class="control-label" for="inputIcon">学校信息：</label>
		                	<div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="schoolMessage" name="schoolMessage" required title="请描述下学校信息" type="text" class="form-control" placeholder="学校信息"  style="width:80%"/>
		                   		<span id="nameDiv3"></span>
		                    </div>
		                    
		                    
		                    <label class="control-label" for="inputIcon">学校电话(座机)：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="schoolTel" name="schoolTel" pattern="^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$" required title="电话号码格式不正确" type="text" class="form-control" placeholder="学校电话"  style="width:80%"/>
		                    	<span id="nameDiv4"></span>
		                    </div>
		                    
		                    <label class="control-label" for="inputIcon">创校时间：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="schoolStartTime" name="schoolStartTime" required title="请选择创校时间" type="date" class="form-control" placeholder="创建时间"  style="width:80%"/>
		                    	<span id="nameDiv5"></span>
		                    </div>
		                    
		                    <label class="control-label" for="inputIcon">学校代码：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="schoolCode" name="schoolCode" pattern="^[0-9]*[1-9][0-9]*$" required title="请填写正确的学校代码" type="text" class="form-control" placeholder="学校代码"  style="width:80%"/>
		                    	<span id="nameDiv6"></span>
		                    </div>
	                	</div>
		                <div class="modal-footer">
		                    <a href="#" class="btn btn-default" data-dismiss="modal">关闭</a>
		                    <button type="submit" class="btn btn-primary">保存</button>
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

