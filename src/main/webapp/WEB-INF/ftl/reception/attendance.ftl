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
	<script src="js/laydate/laydate.js"></script>
    <!-- jQuery -->
    <script src="bower_components/jquery/jquery.min.js"></script>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">
	
	<script type="text/javaScript">
		function getParamsClocking(){
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
			
			window.location.href="findClockingList?schoolId="+schoolId+"&departmentId="+departmentId+"&classId="+classId+"&selDate="+selDate;
		}
		function deleteClocking(clockingId){
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
			window.location.href="deleteClocking?clockingId="+clockingId+"&schoolId="+schoolId+"&departmentId="+departmentId+"&classId="+classId+"&selDate="+selDate;
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
        <li><b>首页</b></li>
        <li><b>上课考勤签到记录</b></li>
    </ul>
</div>

<div class="row">
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-eye-open"></i> 学生上课签到记录</h2>
                <!--<div class="box-icon">
                    <a href="#" class="btn btn-setting btn-round btn-default"><i
                            class="glyphicon glyphicon-cog"></i></a>
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                    <a href="#" class="btn btn-close btn-round btn-default"><i
                            class="glyphicon glyphicon-remove"></i></a>
                </div>
                -->
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
			           班级名称：<select id="classId" data-rel="chosen" onchange="getParamsClocking()">
			              	<option value="">　　全部班级　　</option>
			              	<#if classInfoList??>  
						         <#list classInfoList as c>  
						         	 <option value="${c.classId}">${c.className}</option>  
						         </#list>  
					        </#if>  
			          </select>　　
			         日期：<input id="selDate" placeholder="请输入日期" class="form-control laydate-icon chosen-container chosen-container-single" style="width:15%;">
			     </div>
			     
			     <br>
                <table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
                    <thead>
                        <tr>
                        	<th>班级</th>
                            <th>学生姓名</th>
                            <th>考勤时间</th>
                            <th>考勤地点</th>
                            <th>上课时间
                            <th>上课地点</th>
                            <th>签到状态</th>
                            <th>上课/下课</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody id="clocking">
                        
                        <#if clockingInfos??>
                        	<#list clockingInfos as clocking>
                        		<tr>
                        			<td><#if clocking.stu.classes??>${(clocking.stu.classes.className)!}</#if></td>
		                            <td><#if clocking.stu??>${(clocking.stu.stuName)!}</#if></td>
		                            <td class="center">
		                            	<#if clocking.clockingDate??>
		                            		<#assign lastUpdated = clocking.clockingDate />
											${lastUpdated?string("yyyy-MM-dd")}<br/>
											${lastUpdated?string("HH:mm:ss")}
		                            	</#if>
									</td>
		                            <td class="center">${clocking.clockingAddress!}</td>
		                            <td class="center">
		                            	<#if clocking.course??>
		                            		<#if clocking.course.startTime??>
			                            		${clocking.course.startTime}-${clocking.course.endTime}
											</#if>
		                            	</#if>
									</td>
									<td class="center"><#if clocking.course??>${clocking.course.schoolBuilding!}</#if></td>
		                            <td class="center">
		                            	<#if clocking.clockState = '正常'>
		                            		<span class="label-success label label-default">${clocking.clockState!}</span>
			                            <#elseif clocking.clockState="旷课">
			                            	<span class="label-default label label-danger">${clocking.clockState!}</span>
			                            <#else>
		                            		<span class="label-warning label label-default">${clocking.clockState!}</span>
		                            	</#if>
		                            </td>
		                            <td class="center">${clocking.isvalid!}</td>
		                            <td class="center">
		                                <a class="btn btn-info" href="findByIdClocking?clockingId=${clocking.clockingId!}">
		                                    <i class="glyphicon glyphicon-edit icon-white"></i>
		                                  	  详情/修改
		                                </a>
		                                <a class="btn btn-danger" onclick="deleteClocking('${clocking.clockingId!}')" href="#">
		                                    <i class="glyphicon glyphicon-trash icon-white" ></i>
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

    <!-- content ends -->
    </div><!--/#content.col-md-0-->
</div><!--/fluid-row-->


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
<script src="js/jquery.history.js"></script>
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

