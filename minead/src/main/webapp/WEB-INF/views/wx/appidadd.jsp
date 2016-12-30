<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
	tr{
		width: 80px;
	}
</style>
</head>
<body>
	<form action="${pageContext.request.contextPath}/wx/put/appid" method="post">
	<table border="0">
			<tr>
				<td>名称</td>			
				<td><input name="name"></td>			
			</tr>
			<tr>
				<td>appid</td>			
				<td><input name="appId"></td>			
			</tr>
			<tr>
				<td>secret</td>			
				<td>
				<input name="secret">
				</td>			
			</tr>
			<tr>
				<td>URL</td>			
				<td><input name="url"></td>	
			</tr>
			<tr >
				<td colspan="2"> <input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</body>
</html>