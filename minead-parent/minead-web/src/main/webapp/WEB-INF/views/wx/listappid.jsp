<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
	th{
		width: 120px;
	}
</style>
</head>
<body>
	<table style="width: 100%">
		<tr>
			<th>appid</th>
			<th>secret</th>
			<th>url</th>
			<th>创建时间</th>
		</tr>
		<c:forEach items="${data}" var="vo">
			<tr>
				<td>${vo.appId}</td>
				<td>${vo.secret}</td>
				<td>${vo.createTimeStr}</td>
				<td>${vo.updateTimeStr}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>