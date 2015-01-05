<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	import="org.okeydokey.framework.data.*"%>
<%
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Hello</title> <script type="text/javascript">
		function Go() {
			var frm = document.Form1;
			frm.action = "http://localhost:8080/was-runtime/HELLOWORLD.od";
			frm.submit();
		}
	</script>
</head>
<body>
	<form name="Form1">
		<input type="submit" value="test " id="send"
			onclick="javascript:Go();" /> <input type="text" id="id01" name="ID"
			value="FROM OKEDOKEY?" />
	</form>

	<form:form method="POST" action="http://localhost:8080/was-runtime/HELLOWORLD.od">
		<table>
			<tbody>
				<tr>
					<td>
						<ul>
							<select name="select">
								<option>select</option>
								<option value="11">text_11</option>
								<option value="22">text_22</option>
								<option value="33">text_33</option>
								<option value="44">text_44</option>
								<option value="55">text_55</option>
							</select>
						</ul>
					</td>
				</tr>
				<tr>
					<td><input value="Submit" type="submit"></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>