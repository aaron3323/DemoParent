<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<%@ include file="/common/include_header_js.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<title>main</title>
</head>
<body>
<%
	out.print(session.getId());
%>
	<h2>SSO</h2>
	<h2>用户名：<sec:authentication property="name"/></h2>
	<h2>main</h2>
	
	<h2><a href="${pageContext.request.contextPath}/j_spring_cas_security_logout">j_spring_cas_security_logout</a></h2>
	<h2><a href="${pageContext.request.contextPath}/logout">登出</a></h2>
	<h2><a href="https://localhost:8443/cas/logout">Logout of CAS</a></h2>
	
</body>
</html>