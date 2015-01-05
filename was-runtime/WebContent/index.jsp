<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<%
	String ip = request.getServerName();
	String port = String.valueOf(request.getServerPort());
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css"> 

html, 

body { 
 
		height: 100%; 

		margin: 0; 

		padding: 0; 

		font-size:16px;

} 

.name {

	background-color: lightgray;

	text-align: right;

}

.value {

	background-color: white;

	text-align: left;

}

</style> 

<title>OKEYDOKEY</title>

</head>

<body>
	
	<table width="600" cellpadding="5" cellspacing="0" border="1" class="contents">

					<colgroup>

						<col width="35%"/>

						<col width="65%"/>

					</colgroup>
				  <tr>

						<td class="name">Server</td>

						<td class="value">OkeyDokey</td>

					</tr>
				  <tr>

						<td class="name">Root</td>

						<td class="value"><%= application.getRealPath("/") %></td>

					</tr>

					<tr>

						<td class="name">Server IP</td>

						<td class="value"><%= ip %></td>

					</tr>

					<tr>

						<td class="name">Server PORT</td>

						<td class="value"><%= port %></td>

					</tr>

					<tr>

						<td class="name">file encoding</td>

						<td class="value"><%= System.getProperty("file.encoding") %></td>

					</tr>

					<tr>

						<td class="name">java</td>

						<td class="value"><%= System.getProperty("java.version") %> (<%= System.getProperty("java.vendor") %>)</td>

					</tr>

					<tr>

						<td class="name">jvm</td>

						<td class="value"><%= System.getProperty("java.vm.name") %> <%= System.getProperty("java.vm.version") %> (<%= System.getProperty("java.vm.vendor") %>)</td>

					</tr>

					<tr>

						<td class="name">os</td>

						<td class="value"><%= System.getProperty("os.name") %> <%= System.getProperty("os.version") %> (<%= System.getProperty("os.arch") %>)</td>

					</tr>
				</table>


</body>

</html>