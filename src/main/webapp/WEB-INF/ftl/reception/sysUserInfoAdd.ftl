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
		<!-- 权限树 -->
		<script type="text/javascript" src="js/zTreeJs/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="js/zTreeJs/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="js/zTreeJs/jquery.ztree.excheck-3.5.js"></script>
		<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
		
		
		<script type="text/javascript" src="js/lib/jquery.poshytip.js"></script>
	<script type='text/javascript' src='js/lib/jq.validate.js'></script>
	<link rel="stylesheet" href="js/lib/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
	
	<script type="text/javascript">
		function isClick(){
			//取name为myCheckBox的对象数组 
			var chkArray= document.getElementsByName('roleIds' ); 
			if(chkArray.length==0){
				alert("最少分配一个角色"); 
				return false;
			}
		}
		//验证工号是否存在
		function checkAddSysUser(){
			var sysUserId = document.getElementById("sysUserId").value;
			$.post("checkAddSysUser",{"sysUserId":sysUserId},function callBack(data){
				var l = eval("("+data+")");
			  	if (l == 0) {
	            }else if (l == 1) {
                    alert("对不起该工号已存在");
                    document.getElementById("sysUserId").focus();
	           }
			})
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
						用户添加
						
					</li>
				</ul>
			</div>
			
			
			<div class="row">
				<div class="box col-md-12">
					
					<div class="box-inner">
						<div class="box-header well" data-original-title="">
							<h2><i class="glyphicon glyphicon-user"></i>用户添加</h2>
						</div>
						<div class="box-content">
								<div class="modal-header left">
									<h3>添加用户信息</h3>
								</div>
								<form action="addSysUserInfo" method="post" onsubmit="return isClick()">
									<div class="modal-body">
										<@shiro.hasRole name="系统管理员">
										<label class="control-label" for="inputIcon">用户所属学校：</label>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<select name="school.schoolId" id="clockState" class="form-control" style="width:50%">
														<#if schoolList??>
															<#list schoolList as school>
																<option value="${school.schoolId!}">${school.schoolName!}</option>
															</#list>
														</#if>
											    	</select>	
											</div>
										</@shiro.hasRole>
										<label class="control-label" for="inputIcon">用户工号：</label>
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
											<input id="sysUserId" name="sysUserId" onblur="checkAddSysUser();" type="text" class="form-control" placeholder="用户工号" valType="required" msg="<font color=red>*</font>工号不能为空" style="width:50%">
											<span id="nameDiv"></span>
										</div>
										<label class="control-label" for="inputIcon">用户名称：</label>
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
											<input id="sysUserName" name="sysUserName" type="text" class="form-control" placeholder="用户名称" valType="required" msg="<font color=red>*</font>名称不能为空" style="width:50%">
											<span id="nameDiv"></span>
										</div>
										<label class="control-label" for="inputIcon">用户年龄：</label>
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
											<input id="sysUserAge" name="sysUserAge" type="text" class="form-control" placeholder="用户年龄" valType="NUMBER" msg="<font color=red>*</font>年龄只能为数字" style="width:50%">
											<span id="nameDiv"></span>
										</div>
										<label class="control-label" for="inputIcon">用户密码：</label>
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
											<input id="sysUserPassWord" name="sysUserPassWord" type="password" class="form-control" valType="OTHER" regString="^[\s\S]{6,32}$" msg="<font color=red>*</font>密码6-22位" placeholder="用户密码" style="width:50%">
											<span id="nameDiv"></span> 
										</div>
										<label class="control-label" for="inputIcon">用户电话：</label>
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
											<input id="sysUserTel" name="sysUserTel"  valType="MOBILE" msg="<font color=red>*</font>手机格式不正确" type="text" class="form-control" placeholder="用户电话" style="width:50%">
											<span id="nameDiv"></span>
										</div>
										
										<label class="control-label" for="inputIcon">用户性别：</label>
						                <div class="input-group">
						                  	<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<select name="sysUserSex" id="clockState" class="form-control">
														 <option value="男">男</option>
														 <option value="女">女</option>
											    	</select>
											</div>
						                </div>
										
										<label class="control-label" for="inputIcon">用户地址：</label>
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
											<input id="sysUserAddress" name="sysUserAddress" valType="required" msg="<font color=red>*</font>地址不能为空" type="text" class="form-control" placeholder="用户地址" style="width:50%">
											<span id="nameDiv"></span>
										</div>

										<label class="control-label" for="inputIcon">用户角色：</label>
										<div class="input-group">
											<ul id="treeDemo" class="ztree"></ul>
			    	 						<div id="nodes"></div>
										</div>
									</div>
									<div class="modal-footer right">
										<a href="javascript:void(0)" onclick="history.go(-1)" class="btn btn-default" data-dismiss="modal">返回</a>
										<button id="disM" type="submit" class="btn btn-primary">保存</button>
									</div>
								</form>
							</div>
					</div>
				</div>
			</div>
		</div>
		<!--/span-->
		</div>
		<!--/row-->
	</body>
	<script type="text/javascript">
			var setting = {
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					onCheck: function(event, treeid, treeNode) {
						var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
						var sNodes = treeObj.getCheckedNodes(true);
						var selId = document.getElementById('nodes');
						$("#nodes").html("");
						for (var i = 0; i <= sNodes.length; i++) {
							var selId = document.getElementById('nodes');
							var ht = "<input type='checkBox' style='display :none;' checked='checked' name='roleIds' value='" + sNodes[i].id + "' />";
							$("#nodes").append(ht);
						}
					}
				}
			};
			//用el表达式取值
			var zNodes = ${gsonAllRoles!};
			$(document).ready(function() {
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
				var sNodes = treeObj.getCheckedNodes(true);
				for (var i = 0; i <= sNodes.length; i++) {
					var ht = "<input type='checkBox' style='display:none' checked='checked' name='roleIds' value='" + sNodes[i].id + "' />";
					$("#nodes").append(ht);
				}
			});
		</script>
</html>