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
		//校验表单
		function check(){
    		if(checkSubjectName() == false){
    		}else{
    			document.myForm.submit();
    		}
    	}
		//验证工号科目名称是否存在
		function checkAddSubject(){
			var subjectName = document.getElementById("subjectName").value;
			$.post("checkAddSubject",{"subjectName":subjectName},function callBack(data){
				var l = eval("("+data+")");
			  	if (l == 0) {
	            }else if (l == 1) {
                    alert("对不起该科目名称已存在");
                    document.getElementById("subjectName").focus();
	           }
			})
		}	
		
	</script>
	<script type="text/javaScript">
		function getParamsClocking(){
			var schoolId = $("#schoolId").val();
			if(schoolId ==undefined){
				schoolId = "";
			}

			window.location.href="selectAllSubjectInfo?schoolId="+schoolId;
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
            科目管理
        </li>
    </ul>
</div>
    <div class="row">
    <div class="box col-md-12">
    <div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-user"></i> 　科目管理</h2>
    </div>
    <div class="box-content">
    <div class="controls">
    	 <a class="btn btn-setting btn-round btn-default btn-success" href="#" style="position:relative;" onclick="selectAllSysuser()">
          	<i class="glyphicon glyphicon-zoom-in icon-white click_add"></i>添加
		 </a>
    </div>
   
  
    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
    <thead>
    <tr>
    	<th>科目编码</th>
        <th>科目名称</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
     	 <#if selectAllSubject??>
	    	<#list selectAllSubject as s>
			    <tr>
			    	<td><#if s.subjectId??>${(s.subjectId)!}</#if></td>
			        <td><#if s.subjectName??>${(s.subjectName)!}</#if></td>
			        <td class="center">
			            <a id="delete" href="deleteSubjectInfo?subjectId=${s.subjectId!}" class="btn btn-danger">
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
	                    <h3>科目添加</h3>
	                </div>
	                <form action="addSubjectInfo" method="post" name="myForm">
		                <div class="modal-body">
			                <label class="control-label" for="inputIcon">科目名称：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="subjectName" required title="科目名称不能为空" name="subjectName"  onblur="checkAddSubject();" type="text" class="form-control" placeholder="科目名称"  style="width:80%"/>
		                    	<span id="nameDiv"></span>
		                    </div>     
	                	</div>
		                <div class="modal-footer">
		                    <a href="#" class="btn btn-default" data-dismiss="modal">关闭</a>
		                    <button href="#" id="disM"  class="btn btn-primary">保存</button>
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

