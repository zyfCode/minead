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
<form action="${pageContext.request.contextPath}/wx/list/usermsg" method="post">
	<select name="appid" style="width: 60px">
						<c:forEach  items="${appidData}" var="appid">
							<option value="${appid.appId}">${appid.name}</option>
						</c:forEach>
					</select>	
					<input type="submit" value="查找">
	</form>
	<c:forEach items="${data}" var="vo">
	<hr/>
		<table border="0">
			<tr>
				<td>appid</td>			
				<td> 
					<input disabled="disabled" value="${vo.appid}">		
				</td>			
			</tr>
			<tr>
				<td>任务名</td>			
				<td><input name="name"  disabled="disabled"  value="${vo.name}"></td>			
			</tr>
			<tr>
				<td>状态</td>			
				<td>${vo.status=='1'?'已发布':'未发布'}</td>			
			</tr>
			<tr>
				<td>类型</td>			
				<td>${vo.type}
				<input name="name"  disabled="disabled"  value="${vo.type=='1'?'文本':'图文'}">
				</td>			
			</tr>
			<tr>
				<td>报文</td>			
				<td>
					<textarea name="content" disabled="disabled" style="font-size:150%" rows="20" cols="100">${vo.content}</textarea>
				</td>			
			</tr>
		</table>
		<div style="width: 100%;margin-bottom: 20px">
			<div>
			<form action="${pageContext.request.contextPath}/wx/update/usermsg" method="post"> 
				<input type="hidden" value="${vo.id}" name="id">
				<input type="hidden" value="1" name="status">
				<input type="submit" value="发布">
			</form>
			</div>
			<div>
			<form action="${pageContext.request.contextPath}/wx/update/usermsg" method="post"> 
				<input type="hidden" value="${vo.id}" name="id">
				<input type="hidden" value="0" name="status">
				<input type="submit" value="下架">
			</form>
			</div>
			</div>
		</c:forEach>
</body>
</html>