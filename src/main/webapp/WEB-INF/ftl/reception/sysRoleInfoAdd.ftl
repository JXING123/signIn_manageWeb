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
							var ht = "<input type='checkBox' style='display :none;' checked='checked' name='authIds' value='" + sNodes[i].id + "' />";
							$("#nodes").append(ht);
						}
					}
				}
			};
			//用el表达式取值
			var zNodes = ${gsonAllAuths};
			$(document).ready(function() {
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
				var sNodes = treeObj.getCheckedNodes(true);
				for (var i = 0; i <= sNodes.length; i++) {
					var ht = "<input type='checkBox' style='display:none' checked='checked' name='auths' value='" + sNodes[i].id + "' />";
					$("#nodes").append(ht);
				}
			});
		</script>
		<script type="text/javascript">
		function isClick(){
			//取name为myCheckBox的对象数组 
			var chkArray= document.getElementsByName('authIds' ); 
			if(chkArray.length==0){
				alert("请分配权限"); 
				return false;
			}
		}
		//验证工号是否存在
		function checkAddsysRoleName(){
			isClick();
			var sysRoleName = document.getElementById("sysRoleName").value;
			$.post("checkAddsysRoleName",{"sysRoleName":sysRoleName},function callBack(data){
				var l = eval("("+data+")");
			  	if (l == 0) {
			  		document.myForm.submit();
	            }else if (l == 1) {
                    alert("对不起该角色名已存在");
                    document.getElementById("sysRoleName").focus();
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
						角色添加
						
					</li>
				</ul>
			</div>
			
			<div class="row">
				<div class="box col-md-12">
					
					<div class="box-inner">
						<div class="box-header well" data-original-title="">
							<h2><i class="glyphicon glyphicon-user"></i>角色添加</h2>
						</div>
						<div class="box-content">

								<div class="modal-header left">
									<h3>添加角色信息</h3>
								</div>
								<form action="addSysRoleInfo" method="post" name="myForm" onsubmit="return false">
									<div class="modal-body">
										<label class="control-label" for="inputIcon">角色名称：</label>
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
											<input id="sysRoleName" name="sysRoleName" type="text" class="form-control" placeholder="角色名称" style="width:50%">
											<span id="nameDiv"></span>
										</div>

										<label class="control-label" for="inputIcon">角色描述：</label>
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-list-alt blue"></i></span>
											<input id="sysRoleDesc" name="sysRoleDesc" type="text" class="form-control" placeholder="角色描述" style="width:50%">
											<span id="nameDiv2"></span>
										</div>

										<label class="control-label" for="inputIcon">角色权限：</label>
										<div class="input-group">
											<ul id="treeDemo" class="ztree"></ul>
			    	 						<div id="nodes"></div>
										</div>
									</div>
									<div class="modal-footer right">
										<a href="javascript:void(0)" onclick="history.go(-1)" class="btn btn-default" data-dismiss="modal">返回</a>
										<a href="javascript:void(0)" id="disM" onclick="checkAddsysRoleName()" class="btn btn-primary" data-dismiss="modal">保存</a>
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

		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>Settings</h3>
					</div>
					<div class="modal-body">
						<p>Here settings can be configured...</p>
					</div>
					<div class="modal-footer">
						<a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
						<a href="#" class="btn btn-primary" data-dismiss="modal">Save changes</a>
					</div>
				</div>
			</div>
		</div>

		</div>
		<!--/.fluid-container-->

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