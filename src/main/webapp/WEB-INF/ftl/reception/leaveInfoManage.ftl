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
    	//根据系别带出所属班级
		function getClass(){
			$("#banji").html("<option>-请选择班级-</option>")
			var id = $("#departmentId").val(); 
			$.post("getClass",{"departmentId" : id},function callBack(data){
			$("#banji").attr("data-rel","");
    			var l = eval("("+data+")");
    			for(var i=0; i<l.length; i++){
    				var className = l[i].className;
    				var classId = l[i].classId;
    				//ajax请求回来的数据对下拉框的操作
        			var a = "<option value='"+classId +"'>" + className +"</option>";
        			$("#banji").append(a);
    			}
    		})
		}
		
		//根据班级查询学生
		function getStu(){
			$("#stu").html("<option>-请选择学生-</option>")
			var id = $("#banji").val(); 
			$.post("getStuByClassId",{"classId" : id},function callBack(data){
			$("#banji").attr("data-rel","");
    			var l = eval("("+data+")");
    			for(var i=0; i<l.length; i++){
    				var stuName = l[i].stuName;
    				var stuId = l[i].stuId;
    				//ajax请求回来的数据对下拉框的操作
        			var a = "<option value='"+stuId +"'>" + stuName +"</option>";
        			$("#stu").append(a);
    			}
    		})
		}
		
		//校验表单
		function check(){
    		if(checkDepartmentId() == false||
    		   checkBanji() == false||
    		   checkStu() == false||
    		   checkStartLeaveDate() == false||
    		   checkEndLeaveDate() == false||
    		   checkLeaveDesc() == false){
    		}else{
    			document.myForm.submit();
    		}
    	}
    	
    	//校验系别
		function checkDepartmentId(){
			var testEle = document.getElementById("disM");   
		    var departmentId = $("#departmentId").val();
		    if(departmentId == "-请选择系别-"){
		   		$("#nameDiv1").html("<font color='red'>请选择系别</font>");
		   		testEle.setAttribute("data-dismiss","nomodel");
		    	return false;
		   }else {
		   		$("#nameDiv1").html("");
		   		testEle.setAttribute("data-dismiss","model")
				return true;
		  }
		}
		
		//校验班级
		function checkBanji(){
			var testEle = document.getElementById("disM");   
		    var banji = $("#banji").val();
		    if(banji == "-请选择班级-"){
		   		$("#nameDiv2").html("<font color='red'>请选择班级</font>");
		    	testEle.setAttribute("data-dismiss","nomodel");
		    	return false;
		   	}else {
		   		$("#nameDiv2").html("");
		   		testEle.setAttribute("data-dismiss","model")
				return true;
		  }
		}
		
		//校验学生
		function checkStu(){
			var testEle = document.getElementById("disM");   
		    var stu = $("#stu").val();
		    if(stu == "-请选择学生-"){
		   		$("#nameDiv3").html("<font color='red'>请选择学生</font>");
		    	testEle.setAttribute("data-dismiss","nomodel");
		    	return false;
		   	}else {
		   		$("#nameDiv3").html("");
		   		testEle.setAttribute("data-dismiss","model")
				return true;
		  }
		}
		
		//校验请假开始时间
		function checkStartLeaveDate(){
			var testEle = document.getElementById("disM");   
		    var startLeaveDate = $("#startLeaveDate").val();
		    if(startLeaveDate == null || startLeaveDate == ""){
		    	$("#nameDiv4").html("<font color='red'>请假开始时间不能为空</font>");
		    	testEle.setAttribute("data-dismiss","nomodel");
		    	return false;
		   } else {
		   		$("#nameDiv4").html("");
		   		testEle.setAttribute("data-dismiss","model")
				return true;
		  }
		}
		
		//校验请假结束时间
		function checkEndLeaveDate(){
			var testEle = document.getElementById("disM");   
		    var endLeaveDate = $("#endLeaveDate").val();
		    if(endLeaveDate == null || endLeaveDate == ""){
		    	$("#nameDiv5").html("<font color='red'>请假结束时间不能为空</font>");
		    	testEle.setAttribute("data-dismiss","nomodel");
		    	return false;
		   } else {
		   		$("#nameDiv5").html("");
		   		testEle.setAttribute("data-dismiss","model")
				return true;
		  }
		}
		
		//校验备注
		function checkLeaveDesc(){
			var testEle = document.getElementById("disM");   
		    var leaveDesc = $("#leaveDesc").val();
		    if(leaveDesc == null || leaveDesc == ""){
		    	$("#nameDiv6").html("<font color='red'>备注不能为空</font>");
		    	testEle.setAttribute("data-dismiss","nomodel");
		    	return false;
		   } else {
		   		$("#nameDiv6").html("");
		   		testEle.setAttribute("data-dismiss","model")
				return true;
		  }
		}
	</script>	
	<script type="text/javaScript">
		function getParamsClocking(){
			var departmentId = $("#departmentId").val();
			var classId = $("#classId").val();
			var selDate = $("#selDate").val(); 
			if(departmentId ==undefined){
				departmentId = "";
			}
			var schoolId = $("#schoolId").val();
			if(schoolId ==undefined){
				schoolId = "";
			}
			window.location.href="findLeaveList?schoolId="+schoolId+"&departmentId="+departmentId+"&classId="+classId+"&selDate="+selDate;
		}
		function deleteLeaveInfo(leaveId){
			var schoolId = $("#schoolId").val();
			var departmentId = $("#departmentId").val();
			var selDate = $("#selDate").val(); 
			var classId = $("#classId").val();
			if(schoolId !=undefined && schoolId ==""){
				departmentId="";
				classId="";
			}
			if(schoolId ==undefined){
				schoolId = "";
			}
			if(departmentId !=undefined && departmentId ==""){
				classId="";
			}
			if(departmentId ==undefined){
				departmentId = "";
			}
			window.location.href="deleteLeaveInfo?leaveId="+leaveId+"&schoolId="+schoolId+"&departmentId="+departmentId+"&classId="+classId+"&selDate="+selDate;
		}
		function updateState(leaveId){
			var schoolId = $("#schoolId").val();
			var departmentId = $("#departmentId").val();
			var selDate = $("#selDate").val(); 
			var classId = $("#classId").val();
			if(schoolId !=undefined && schoolId ==""){
				departmentId="";
				classId="";
			}
			if(schoolId ==undefined){
				schoolId = "";
			}
			if(departmentId !=undefined && departmentId ==""){
				classId="";
			}
			if(departmentId ==undefined){
				departmentId = "";
			}
			window.location.href="updateState?leaveId="+leaveId+"&schoolId="+schoolId+"&departmentId="+departmentId+"&classId="+classId+"&selDate="+selDate;
		}
	</script>
	<script src="js/laydate/laydate.js"></script>
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
					var selDate = "${selDate!}";
					$("#selDate").attr("value",selDate);
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
            请假管理
        </li>
    </ul>
</div>
    <div class="row">
    <div class="box col-md-12">
    <div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-user"></i> 　请假管理</h2>
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
			          请假时间：<input id="selDate" placeholder="请输入日期" class="form-control laydate-icon chosen-container chosen-container-single" style="width:15%;">
    	 <!--<a class="btn btn-setting btn-round btn-default btn-success" style="position:relative;">
          	<i class="glyphicon glyphicon-zoom-in icon-white click_add"></i>添加
		 </a>-->
	</div>
			     
	<br>
    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
    <thead>
    <tr>
        <th>姓名</th>
        <th>班级</th>
        <th>院系名称</th>
        <th>请假开始时间</th>
        <th>请假结束时间</th>
        <th>请假类型</th>
        <th>请假状态</th>
        <th>备注</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
		<#if selectLeaveInfoList??>
	    	<#list selectLeaveInfoList as l>
			    <tr>
			        <td><#if l.stu??>${(l.stu.stuName)!}</#if></td>
			        <td class="center"><#if l.stu??>${(l.stu.classes.className)!}</#if></td>
			        <td class="center"><#if l.stu??>${(l.stu.classes.department.deprtmentName)!}</#if></td>
			        <td class="center"><#if l.startLeaveDate??>
			        	<#assign startTime = l.startLeaveDate />
						${startTime?string("yyyy-MM-dd")}
						</#if></td>
			        <td class="center"><#if l.endLeaveDate??>
			        	<#assign startTime = l.endLeaveDate />
						${startTime?string("yyyy-MM-dd")}
			        	</#if></td>
			        <td class="center"><#if l.leaveType??>${(l.leaveType)!}</#if></td>
			        <td class="center">
			        	<#if l.leaveState=='审核中'>
							<span class="label label-warning">未审核</span>
							<#else>
								<span class="label label-success">${l.leaveState!}</span>
						</#if>
			        </td>
			        <td class="center"><#if l.leaveDesc??>${(l.leaveDesc)!}</#if></td>
			        <td class="center">
			            <a class="btn btn-info" href="selectLeaveInfoById?leaveId=${l.leaveId!}">
			                <i class="glyphicon glyphicon-edit icon-white"></i>
			              	  详情/修改
			            </a>
			            <#if l.leaveState !='已审核'>
			            <a class="btn btn-success" onclick="updateState('${l.leaveId!}')" href="JavaScript:(void)">
			                <i class="glyphicon glyphicon-zoom-in icon-white"></i>
			                	审核
			            </a>
			            </#if>
			            <a class="btn btn-danger" onclick="deleteLeaveInfo('${l.leaveId!}')" href="JavaScript:(void)">
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
	                    <h3>请假添加</h3>
	                </div>
	                <form action="addLeaveInfo" method="post" name="myForm">
		                <div class="modal-body">
		                	<label class="control-label" for="inputIcon">请选择系别：</label>
		                	<div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
						    	<select id="departmentId" onkeydown="checkDepartmentId();" class="form-control"  style="width:80%" onchange="getClass()">
						    		<option>-请选择系别-</option>
							    	<#if departmentBySchoolId??>  
									         <#list departmentBySchoolId as g>  
									         	 <option value="${g.departmentId}">${g.deprtmentName}</option>  
									         </#list>  s
								        </#if> 
						    	</select>
						    	<span id="nameDiv1"></span>
		                    </div>
		                    
			                <label class="control-label" for="inputIcon">请选择班级:</label>
		                	 <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
						    	<select id="banji" onkeydown="checkBanji();" onchange="getStu()" class="form-control"  style="width:80%">
						    		<option>-请选择班级-</option>
						    	</select>
						    	<span id="nameDiv2"></span>
		                    </div>
		                    
		                    <label class="control-label" for="inputIcon">请选择学生:</label>
		                	 <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
						    	<select id="stu" name="stu.stuId" onkeydown="checkStu();" class="form-control"  style="width:80%">
						    		<option>-请选择学生-</option>
						    	</select>
						    	<span id="nameDiv3"></span>
		                    </div>
		                    
		                    <label class="control-label" for="inputIcon">请假开始时间：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="startLeaveDate" name="startLeaveTime" onkeydown="checkStartLeaveDate();" type="date" class="form-control" placeholder="请假开始时间"  style="width:80%">
		                    	<span id="nameDiv4"></span>
		                    </div>
		                    
		                    <label class="control-label" for="inputIcon">请假结束时间：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="endLeaveDate" name="endLeaveTime" onkeydown="checkEndLeaveDate();" type="date" class="form-control" placeholder="请假结束时间"  style="width:80%">
		                    	<span id="nameDiv5"></span>
		                    </div>
		                    
		                    <label class="control-label" for="inputIcon">请假类型:</label>
		                	<div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <select name="leaveType" class="form-control"  style="width:80%">
									 <option value="事假">事假</option>
									 <option value="公假">公假</option>
									 <option value="病假">病假</option>
						    	</select>
		                    </div>
		                    
		                    <label class="control-label" for="inputIcon">请假状态：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
						    	<input disabled="disabled" type="text" class="form-control" value="审核中"  style="width:80%"/>
		                    </div>
		                    
		                    <label class="control-label" for="inputIcon">备注：</label>
		                    <div class="input-group">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
		                        <input id="leaveDesc" name="leaveDesc" onkeydown="checkLeaveDesc();" type="text" class="form-control" placeholder="备注"  style="width:80%"/>
		                    	<span id="nameDiv6"></span>
		                    </div>
	                	</div>
		                <div class="modal-footer">
		                    <a href="#" class="btn btn-default" data-dismiss="modal">关闭</a>
		                    <a href="#" id="disM" onclick="check()" class="btn btn-primary" data-dismiss="modal">保存</a>
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

