<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<%@ include file="/common/include_header_js.jsp"%>
</head>

<body>
<br>
<br>
<p align="center"><span lang="zh-cn">超时，请重新登录</span></p>
<p align="center">
<span id=second lang="zh-cn">
   3
</span>
<span lang="zh-cn">秒后<a href="${pageContext.request.contextPath}">重新登录</a></span></p>
<script language="javascript">
   var time=3;
  
   function timedown()
   {
    time=time-1;
    if (time<0)
    	window.top.location.href=ctx;
    else
     document.getElementById("second").innerHTML = time;
   }
  
   setInterval("timedown()",1000);
</script>

</body>
</html>
