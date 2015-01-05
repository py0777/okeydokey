<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	import="org.okeydokey.framework.data.impl.BizMap"%>
<%
    BizMap biz = (BizMap)request.getAttribute("BizMap");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HelloView</title>
</head>
<body>
<%=biz.get("KEY") %>
</body>
</html>