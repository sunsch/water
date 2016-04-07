<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>四度营造社</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page/main.css" type="text/css" />

<style type="text/css">
#menu_div li
{
padding-left: 10px;
background-repeat: no-repeat;
background-position: 0 .5em;
background: url(image/menuitembg.JPG);
font-size:16px;
}
</style>

</head>
<body>

<div class="gohome"><a href="<%=request.getContextPath()%>/home.jsp">返回首页</a></div>

<center>
<table style="width:90%;height:100%; border: solid 0px white;    ">
<tr>
<td><img src="../image/logo.jpg"></img></td>
</tr>

<tr>
<td>

<table style="width:90%;height:400px; border: solid 0px white;    ">
<tr><td align="center">
服务器内部可能发生了问题，您需要返回后重新请求，或者请求其它页面。
<a href="<%=request.getContextPath()%>/home.jsp">返回首页</a>
</td></tr>
</table>

</td>
</tr>

<tr>
<td>
<div align="center" ><span style="color:blue">四度营造社公司版权所有　　©2009-2010</span></div>
</td>
</tr>

</table>


</center>
</body>
</html>