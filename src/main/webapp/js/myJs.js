/**
 * 获取学生某段时间的旷课的情况
 * @param stuId
 * @param leaveType
 */
function findTruantContent(stuName,stuId,state) {
	$("#modal-body").html("<img src='img/20140928103818165.gif'/>");
	var title  = chart.subtitle.textStr; //获取副标题的内容
	var strs= new Array(); //定义一数组 
	strs = title.split("~"); //字符串分割
	var start = strs[0].substring(5,7)+"月"+strs[0].substring(8,10)+"日";
	var end = strs[1].substring(5,7)+"月"+strs[1].substring(8,10)+"日";
	$("#myModalLabel").html("<b>"+stuName+" "+start+"-"+end+" "+state+"详情</b>");
	$.post("findStuPeriodOfTimeTruant",{"stuId":stuId,"startTime":strs[0],"endTime":strs[1],"state":state},function callBack(data){
		$("#modal-body").html("");
		var array = eval(data);
		for (var i = 0; i < array.length; i++) {
			var clockingDate = getDate(array[i].clockingDate);
			var course = array[i].courseName+"("+array[i].startTime+"-"+array[i].endTime+")";
			var week = array[i].week;
			var p = "";
			if(state=="早退时长"){
				var date = array[i].date;
				p = "<p>日期  "+clockingDate+"("+week+")"+" 上课早退"+date+"  "+course+"</p>";
			}else if(state=="迟到时长"){
				var date = array[i].date;
				p = "<p>日期  "+clockingDate+"("+week+")"+" 上课迟到"+date+"  "+course+"</p>";
			}else{
				p = "<p>日期  "+clockingDate+"("+week+")"+"  "+state+"  "+course+"</p>";
			}
			
			$("#modal-body").append(p);
		}
	});
}
function findDormSigninContent(stuName,stuId,state) {
	$("#modal-body").html("<img src='img/20140928103818165.gif'/>");
	var title  = chart.subtitle.textStr; //获取副标题的内容
	var strs= new Array(); //定义一数组 
	strs = title.split("~"); //字符串分割
	var start = strs[0].substring(5,7)+"月"+strs[0].substring(8,10)+"日";
	var end = strs[1].substring(5,7)+"月"+strs[1].substring(8,10)+"日";
	$("#myModalLabel").html("<b>"+stuName+" "+start+"-"+end+" "+state+"详情</b>");
	$.post("findStuPeriodOfTimeWeiGui",{"stuId":stuId,"startTime":strs[0],"endTime":strs[1],"state":state},function callBack(data){
		$("#modal-body").html("");
		var array = eval(data);
		for (var i = 0; i < array.length; i++) {
			var signinDate = getDate(array[i].signinDate);
			var week = array[i].week;
			var p = "";
			if(state=="晚归"){
				var date = array[i].date;
				p = "<p>日期  "+signinDate+"("+week+")"+" 晚归"+date+"   正常("+array[i].normalTime+")</p>";
			}else if(state=="未归"){
				p = "<p>日期  "+signinDate+"-("+week+")</p>";
			}
			
			$("#modal-body").append(p);
		}
	});
}
/**
 * 获取学生某段时间的请假的情况
 * @param stuName
 * @param stuId
 * @param leaveType
 */
function findLeaveContent(stuName,stuId,leaveType) {
	$("#modal-body").html("<img src='img/20140928103818165.gif'/>");
	var title  = chart.subtitle.textStr; //获取副标题的内容
	var strs= new Array(); //定义一数组 
	strs = title.split("~"); //字符串分割
	var start = strs[0].substring(5,7)+"月"+strs[0].substring(8,10)+"日";
	var end = strs[1].substring(5,7)+"月"+strs[1].substring(8,10)+"日";
	$("#myModalLabel").html("<b>"+stuName+start+"-"+end+leaveType+"详情</b>");
	if(leaveType=='请假'){
		leaveType = null;
	}
	$.post("findStuPeriodOfTimeLeave",{"stu.stuId":stuId,"startLeaveTime":strs[0],"endLeaveTime":strs[1],"leaveType":leaveType},function callBack(data){
		$("#modal-body").html("");
		var leaveArray = eval(data);
		for (var i = 0; i < leaveArray.length; i++) {
			var leave = leaveArray[i];
			var date = leave.startLeaveTime;
			var date1 = leave.endLeaveTime;
			var startTime = getDate(date);
			var endTime = getDate(date1);
			var getDateDiff = GetDateDiff(leave.startLeaveTime, leave.endLeaveTime);
			if(getDateDiff==0){
				getDateDiff=1;
			}
			var p = "<p>日期 "+startTime+"-"+endTime+" "+getDateDiff+"天</p>";
			$("#modal-body").append(p);
		}
	});
}
/**
 * 计算时间间隔天数
 * @param startDate
 * @param endDate
 * @returns {Number}
 */
function GetDateDiff(startDate,endDate) {  
    var startTime = new Date(Date.parse(startDate.replace(/-/g,   "/"))).getTime();     
    var endTime = new Date(Date.parse(endDate.replace(/-/g,   "/"))).getTime();     
    var dates = Math.abs((startTime - endTime))/(1000*60*60*24);     
    return  dates;    
}

function getDate(date){
	var start = date.substring(0,4)+"年"+date.substring(5,7)+"月"+date.substring(8,10)+"日";
	return start;
}
