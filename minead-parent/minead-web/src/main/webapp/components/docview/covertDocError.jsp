<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../components/components.css" type="text/css">
<title>在线阅读</title>
<style type="text/css">
html,body{height: 100%;}
body {
	margin: 0;padding: 0;overflow: auto;
}
#flashContent { display:none; }
.MsgError{
		width: 500px;
		height: 500px;
		background: #eee;
		position: relative;
		border:1px solid #ddd;
	}
	.errorWrap{
		height: 100px;
		width: 300px;
		position: absolute;
		top: 50%;
	    left: 50%;
	    margin-top: -50px;
	    margin-left: -150px;
	}
	.errorTip{		
		line-height: 30px;
		
	}
	.errorTip span{
		display: block;
	    position: absolute;
	    font-size: 24px;
	    color:#f59c1a;
	}

</style>
</head>
<body>
	<div class="MsgError" style="width:<%=(String)session.getAttribute("width")%>px;height:<%=(String)session.getAttribute("height")%>px;">
	<div class="errorWrap">
		<div class="errorTip">
			<span><i class="fa fa-exclamation-triangle"></i></span>
			<h3 style="padding-left:30px;"><%=(String)session.getAttribute("errorMsg")%></h3>
		</div>
	</div>
 </div>

</body>
</html>