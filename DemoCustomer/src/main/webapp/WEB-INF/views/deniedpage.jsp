<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<%@ include file="/common/include_header_js.jsp"%>
<title>Login Page</title>
</head>
<body>  
    <h1>你的权限不够!</h1>  
    <p>只有拥有Admin权限才能访问!</p>  
    <a href="${pageContext.request.contextPath}/login">退出登录</a>  
</body>
</html>