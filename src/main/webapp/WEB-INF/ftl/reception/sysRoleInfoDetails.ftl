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
								var ht = "<input type='checkBox' style='display :none;' checked='checked' name='authIds' value='" + sNodes[i].id + "'/>";
								$("#nodes").append(ht);
							}
						}
					}
				};
				//用el表达式取值
				var zNodes = ${gsonAllAuths!};
				$(document).ready(function() {
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
					var sNodes = treeObj.getCheckedNodes(true);
					for (var i = 0; i <= sNodes.length; i++) {
						var ht = "<input type='checkBox' style='display :none;' checked='checked' name='authIds' value='" + sNodes[i].id + "'/>";
						$("#nodes").append(ht);
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
						角色详细信息
					</li>
				</ul>
			</div>
			<div class="row">
				<div class="box col-md-12">
					<div class="box-inner">
						<div class="box-header well" data-original-title="">
							<h2><i class="glyphicon glyphicon-user"></i>角色详细信息</h2>
						</div>
						<div class="box-content">
							<form action="updateByIdRole?sysRoleId=${roleInfo.sysRoleId!}" method="POST" name="myForm">
								<table class="table-striped table-bordered">
									<tr>
										<td><label class="control-label" for="inputIcon">角色名称:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<input id="sysRoleName" name="sysRoleName" onkeydown="checkSubjectName();" type="text" value="${roleInfo.sysRoleName?if_exists}" class="form-control">
											</div><span id="nameDiv"></span>
										</td>
										<td><label class="control-label" for="inputIcon">角色描述:</label></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
												<input id="sysRoleDesc" name="sysRoleDesc" value="${roleInfo.sysRoleDesc?if_exists}" type="text" class="form-control">
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="1"><label class="control-label" for="inputIcon">角色权限:</label></td>
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
</html>