<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<%@ include file="/common/include_header_js.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jwplayer/jwplayer.js"></script>
	<script type="text/javascript">jwplayer.key="ABCDEFGHIJKLMOPQ";</script>
</head>
<title>demo</title>
<body>
<div id="myElement">Loading the player...</div>
<script type="text/javascript">
	jwplayer("myElement").setup({
		//file: ctx+"/videos/llm.flv",
		file: "thunder://QUFmdHA6Ly93d3c6cGlhb2h1YS5jb21AZHkxMjYucGlhb2h1YS5jb206MjAxMTYvJUU5JUEzJTk4JUU4JThBJUIxJUU3JTk0JUI1JUU1JUJEJUIxcGlhb2h1YS5jb20lRTclOTclOUUlRTUlQUQlOTAlRTglOEIlQjElRTklOUIlODQlRTQlQjklOEIlRTklQkIlOEUlRTYlOTglOEUlRTUlOEQlODclRTglQjUlQjdCRDEyODAlRTklQUIlOTglRTYlQjglODUlRTUlOUIlQkQlRTglQUYlQUQlRTQlQjglQUQlRTglOEIlQjElRTUlOEYlOEMlRTUlQUQlOTcubWt2Wlo=",
		image: ctx+"/videos/player.jpg",
		width: 640,
		height: 360
	});
</script>









</body>
</html>
