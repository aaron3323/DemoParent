<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<%@ include file="/common/include_header_js.jsp"%>
</head>

<body>
<h2>index</h2>
<h2><a href="${pageContext.request.contextPath}/main">main</a></h2>
<h2><a href="${pageContext.request.contextPath}/logout">登出</a></h2>
<h2><a href="https://localhost:8443/cas/logout">Logout of CAS</a></h2>
</body>
</html>
