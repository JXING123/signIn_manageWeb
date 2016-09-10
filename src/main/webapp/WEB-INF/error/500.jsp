<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0022)http://www.bn0691.com/1 -->
<HTML xmlns="http://www.w3.org/1999/xhtml">

	<HEAD>
		<TITLE>页面出了点小错误</TITLE>
		<META http-equiv=X-UA-Compatible content=IE=EmulateIE7>
		<META http-equiv=Content-Type content="text/html; charset=gb2312">
		<!-- <META http-equiv=refresh content=3;URL=http://bbs.lixinr.com> -->
		<LINK type=text/css rel=stylesheet>
		<STYLE>
			{
				PADDING-RIGHT: 0 px;
				PADDING-LEFT: 0 px;
				PADDING-BOTTOM: 0 px;
				MARGIN: 0 px;
				PADDING-TOP: 0 px
			}
			
			BODY {
				BACKGROUND: #dad9d7;
				FONT-FAMILY: "微软雅黑"
			}
			
			IMG {
				BORDER-TOP-STYLE: none;
				BORDER-RIGHT-STYLE: none;
				BORDER-LEFT-STYLE: none;
				BORDER-BOTTOM-STYLE: none
			}
			
			A {
				CURSOR: pointer
			}
			
			UL {
				LIST-STYLE-TYPE: none
			}
			
			LI {
				LIST-STYLE-TYPE: none
			}
			
			TABLE {
				TABLE-LAYOUT: fixed
			}
			
			TABLE TR TD {
				WORD-BREAK: break-all;
				WORD-WRAP: break-word
			}
			
			A {
				TEXT-DECORATION: none;
				outline: none
			}
			
			A:hover {
				TEXT-DECORATION: underline
			}
			
			.cf:unknown {
				CLEAR: both;
				DISPLAY: block;
				FONT-SIZE: 0px;
				VISIBILITY: hidden;
				HEIGHT: 0px;
				content: "."
			}
			
			.cf {
				CLEAR: both;
				ZOOM: 1
			}
			
			.bg {
				BACKGROUND: url(${ctx}/img/404/01.jpg) #dad9d7 no-repeat center top;
				LEFT: 0px;
				OVERFLOW: hidden;
				WIDTH: 100%;
				POSITION: absolute;
				TOP: 0px;
				HEIGHT: 600px
			}
			
			.cont {
				MARGIN: 0px auto;
				WIDTH: 500px;
				LINE-HEIGHT: 20px
			}
			
			.c1 {
				HEIGHT: 360px;
				TEXT-ALIGN: center
			}
			
			.c1 .img1 {
				MARGIN-TOP: 180px
			}
			
			.c1 .img2 {
				MARGIN-TOP: 165px
			}
			
			.cont H2 {
				FONT-WEIGHT: normal;
				FONT-SIZE: 18px;
				COLOR: #555;
				HEIGHT: 35px;
				TEXT-ALIGN: center
			}
			
			.c2 {
				HEIGHT: 35px;
				TEXT-ALIGN: center
			}
			
			.c2 A {
				DISPLAY: inline-block;
				FONT-SIZE: 14px;
				MARGIN: 0px 4px;
				COLOR: #626262;
				PADDING-TOP: 1px;
				HEIGHT: 23px;
				TEXT-ALIGN: left;
				TEXT-DECORATION: none
			}
			
			.c2 A:hover {
				COLOR: #626262;
				TEXT-DECORATION: none
			}
			
			.c2 A.home {
				PADDING-LEFT: 30px;
				BACKGROUND: url(${ctx}/img/404/02.png);
				WIDTH: 66px
			}
			
			.c2 A.home:hover {
				BACKGROUND: url(${ctx}/img/404/02.png) 0px -24px
			}
			
			.c2 A.home:active {
				BACKGROUND: url(${ctx}/img/404/02.png) 0px -48px
			}
			
			.c2 A.re {
				PADDING-LEFT: 30px;
				BACKGROUND: url(${ctx}/img/404/03.png);
				WIDTH: 66px
			}
			
			.c2 A.re:hover {
				BACKGROUND: url(${ctx}/img/404/03.png) 0px -24px
			}
			
			.c2 A.re:active {
				BACKGROUND: url(${ctx}/img/404/03.png) 0px -48px
			}
			
			.c2 A.sr {
				PADDING-LEFT: 28px;
				BACKGROUND: url(${ctx}/img/404/04.png);
				WIDTH: 153px
			}
			
			.c2 A.sr:hover {
				BACKGROUND: url(${ctx}/img/404/04.png) 0px -24px
			}
			
			.c2 A.sr:active {
				BACKGROUND: url(${ctx}/img/404/04.png) 0px -48px
			}
			
			.c3 {
				FONT-SIZE: 12px;
				COLOR: #999;
				HEIGHT: 180px;
				TEXT-ALIGN: center
			}
			
			#bf {
				LEFT: 0px;
				WIDTH: 100%;
				POSITION: absolute;
				TOP: 269px
			}
			
			.bf1 {
				PADDING-LEFT: 32px;
				MARGIN: 0px auto;
				WIDTH: 99px
			}
			
			.bd {
				OVERFLOW: hidden;
				HEIGHT: 600px
			}
			
			#box {
				LEFT: 0px;
				WIDTH: 100%;
				POSITION: absolute;
				TOP: 165px;
				TEXT-ALIGN: center
			}
			
			.bf1 {
				PADDING-LEFT: 32px;
				MARGIN: 0px auto;
				WIDTH: 99px
			}
		</STYLE>

		<META content="MSHTML 6.00.2900.5969" name=GENERATOR>
	</HEAD>

	<BODY>
		<DIV class=bg>
			<DIV class=cont>
				<DIV class=c1>
					<IMG class=img1 src="${ctx}/img/404/01.png">
				</DIV>
				<H2>Sorry…系统出了点小错误,请联系管理员</H2>
				<DIV class=c2>
					<A class=re href="#" onclick="history.go(-1)">返回</A>
					<A class=home href="login.jsp" target="_top">门户首页</A>
					<A href="https://www.baidu.com/" target="_blank" class=sr>搜索一下页面相关信息</A>
				</DIV>
				<DIV class=c3>
					<A class=c3 href="login.jsp">【系统】</A>提醒您 - 该页面有错误，请联系管理员！
				</DIV>
			</DIV>
		</DIV>
	</BODY>

</HTML>