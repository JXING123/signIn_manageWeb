<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<title>学生管理系统</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
		<meta name="author" content="Muhammad Usman">
		<#assign basePath=request.contextPath>
			<!-- The styles -->
			<link id="bs-css" href="${basePath}/css/bootstrap-cerulean.min.css" rel="stylesheet">
			<link href="${basePath}/css/charisma-app.css" rel="stylesheet">
			<link href='${basePath}/bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
			<link rel="shortcut icon" href="${basePath}/img/favicon.ico">
			<!-- 权限树 -->
			<script type="text/javascript" src="${basePath}/js/zTreeJs/jquery-1.4.4.min.js"></script>
			<script type="text/javascript" src="${basePath}/js/zTreeJs/jquery.ztree.core-3.5.js"></script>
			<script type="text/javascript" src="${basePath}/js/zTreeJs/jquery.ztree.excheck-3.5.js"></script>
			<link rel="stylesheet" href="${basePath}/css/zTreeStyle/zTreeStyle.css" type="text/css">
			
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
						用户详细信息
					</li>
				</ul>
			</div>
			<div class="row">
				<div class="box col-md-12">
					<div class="box-inner">
						<div class="box-header well" data-original-title="">
							<h2><i class="glyphicon glyphicon-user"></i>用户详细信息</h2>
						</div>
						<div class="box-content">
							<form action="updateByIdSysUser?sysRoleId=${sysUser.sysUserId!}" method="POST" name="myForm">
								<table class="table-striped table-bordered">
									<tr>
										<td><label class="control-label" for="inputIcon">用户所属学校:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<input id="sysRoleName" name="sysUser" type="text" value="${sysUser.school.schoolName?if_exists}" readonly="true" class="form-control">
											</div><span id="nameDiv"></span>
										</td>
										<td><label class="control-label" for="inputIcon">用户工号:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<input id="sysUserId" name="sysUserId"  value="${sysUser.sysUserId?if_exists}" type="text" class="form-control">
											</div>
										</td>
									</tr>
									<tr>
										<td><label class="control-label" for="inputIcon">用户名称:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<input name="sysUserName" type="text" value="${sysUser.sysUserName?if_exists}" valType="required" msg="<font color=red>*</font>用户名称不能为空" class="form-control">
											</div><span id="nameDiv"></span>
										</td>
										<td><label class="control-label" for="inputIcon">用户密码:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<input id="sysUserPassWord" name="sysUserPassWord" valType="OTHER" regString="^[\s\S]{6,32}$" msg="<font color=red>*</font>密码6-22位" value="${sysUser.sysUserPassWord?if_exists}" type="password" class="form-control">
											</div>
										</td>
									</tr>
									<tr>
										<td><label class="control-label" for="inputIcon">用户电话:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<input id="sysUserTel" name="sysUserTel" valType="MOBILE" msg="<font color=red>*</font>手机格式不正确" value="${sysUser.sysUserTel?if_exists}" type="text" class="form-control">
											</div>
										</td>
										<td><label class="control-label" for="inputIcon">用户性别:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
													<select name="sysUserSex" id="clockState" class="form-control">
														 <option value="男">男</option>
														 <option value="女">女</option>
											    	</select>
											</div>
										</td>
									</tr>
									<tr>
										<td><label class="control-label" for="inputIcon">用户年龄:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<input name="sysUserAge" type="text" valType="NUMBER" msg="<font color=red>*</font>年龄只能为数字" value="${sysUser.sysUserAge?if_exists}" class="form-control">
											</div><span id="nameDiv"></span>
										</td>
										<td><label class="control-label" for="inputIcon">用户地址:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<input id="sysUserAddress" name="sysUserAddress" valType="required" msg="<font color=red>*</font>地址不能为空" value="${sysUser.sysUserAddress?if_exists}" type="text" class="form-control">
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="1"><label class="control-label" for="inputIcon">用户角色:</label></td>
										<td colspan="3">
											<ul id="treeDemo" class="ztree"></ul>
											<div id="nodes"></div>
										</td>
									</tr
									<tr>
										<td colspan="2"><button type="submit" class="btn btn-warning">更新</button></td>
										<td colspan="2"><button type="button" class="btn btn-primary" onclick="history.go(-1)">返回</button></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
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
								var ht = "<input type='checkBox' style='display :none;' checked='checked' name='roleIds' value='" + sNodes[i].id + "'/>";
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
						var ht = "<input type='checkBox' style='display :none;' checked='checked' name='roleIds' value='" + sNodes[i].id + "'/>";
						$("#nodes").append(ht);
					}
				});
			</script>
</html>