<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<%@ include file="/common/include_header_js.jsp"%>
<title>Login Page</title>
</head>
<body onload='document.f.username.focus();'>
	<h3>自定义登陆页</h3>
	<h6>${error}</h6>
	<h6>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</h6>
	<form name='f' action='${pageContext.request.contextPath}/landing' method='POST'>
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username'></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="_spring_security_remember_me5" /></td>
				<td>两周之内不必登陆</td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit" value="Login" /></td>
			</tr>
		</table>
	</form>
</body>
</html>