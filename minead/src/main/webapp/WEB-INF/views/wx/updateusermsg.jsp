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
	<form action="${pageContext.request.contextPath}/wx/updatecontent/usermsg" method="post">
		<input type="hidden" name="id" value="${data.id}">
		<table border="0">
			<tr>
				<td>appid</td>			
				<td> 
					<input type="text" disabled="disabled" value="${data.appid}">				
				</td>			
			</tr>
			<tr>
				<td>任务名</td>			
				<td>${data.name}</td>			
			</tr>
			<tr>
				<td>类型</td>			
				<td>
					<select  name="type" style="width: 60px">
						<option value="1" ${data.type=='1'?"selected='selected'":''} >文本</option>
						<option value="2" ${data.type=='2'?"selected='selected'":''} >图文</option>
					</select>
				</td>			
			</tr>
			<tr>
				<td>报文</td>			
				<td>
					<textarea name="content" style="font-size:150%" rows="20" cols="100">
						${data.content}
					</textarea>
				</td>			
			</tr>
			<tr >
				<td colspan="2"> <input type="submit" value="更新"></td>
			</tr>
		</table>
	</form>
</body>
</html>