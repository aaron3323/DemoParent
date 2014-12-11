<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width" />
<link rel="apple-touch-icon" href="logo.png" />
<link rel="icon" type="image/vnd.microsoft.icon" href="favicon.ico">
<link rel="icon" type="image/png" href="favicon.png">
<title>Red5 - OFLADemo</title>
<style type="text/css">
<!--
body {
    background-color:#680000;
    font-family: sans-serif;
    font-size: 0.9em;
}
.code {
    font-family: monospace;
    font-size: 130%;
}
a {
    color: red;
    background: white;
    text-decoration: none;
}
#information {
    padding-top: 1em;
    padding-bottom: 1em;
}
.spiffy{display:block}
.spiffy *{
  display:block;
  height:1px;
  overflow:hidden;
  font-size:.01em;
  background:#fff}
.spiffy1{
  margin-left:3px;
  margin-right:3px;
  padding-left:1px;
  padding-right:1px;
  border-left:1px solid #b60600;
  border-right:1px solid #b60600;
  background:#df0b00}
.spiffy2{
  margin-left:1px;
  margin-right:1px;
  padding-right:1px;
  padding-left:1px;
  border-left:1px solid #8c0100;
  border-right:1px solid #8c0100;
  background:#e60c00}
.spiffy3{
  margin-left:1px;
  margin-right:1px;
  border-left:1px solid #e60c00;
  border-right:1px solid #e60c00;}
.spiffy4{
  border-left:1px solid #b60600;
  border-right:1px solid #b60600}
.spiffy5{
  border-left:1px solid #df0b00;
  border-right:1px solid #df0b00}
.spiffyfg{
    padding:1em;
    background-color:#fff;
}
  -->
</style>
</head>
<body>
	<%@ include file="/common/include_header_js.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jwplayer/jwplayer.js"></script>
	<script type="text/javascript">jwplayer.key="ABCDEFGHIJKLMOPQ";</script>
<div>
  <b class="spiffy">
  <b class="spiffy1"><b></b></b>
  <b class="spiffy2"><b></b></b>
  <b class="spiffy3"></b>
  <b class="spiffy4"></b>
  <b class="spiffy5"></b></b>
  
<div class="spiffyfg">

<center>
<b>RTMP</b>
<div id='mediaspace'>This text will be replaced</div>
<script type='text/javascript'>
  jwplayer('mediaspace').setup({
//     'flashplayer': 'player.swf',
    'file': "rtmp://localhost/oflaDemo/hobbit_vp6.flv",
    'streamer': 'rtmp://localhost/oflaDemo',
    'controlbar': 'bottom',
    'width': '848',
    'height': '360'
  });
</script>

</body>
</html>