<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线阅读</title>
<script type="text/javascript" src="../../plugins/jquery/jquery.mini.js"></script>
<script type="text/javascript" src="../../plugins/jquery/jquery.media.js"></script>
<style type="text/css">
html,body{height: 100%;}
body {
	margin: 0;padding: 0;overflow: auto;
}
#flashContent { display:none; }
</style>
</head>
<body>
<div style="position:absolute;left:10px;top:10px;">
	<a id="media" class="media" href="#"></a>
	<a class="media" href="../../doc/<%=(String)session.getAttribute("pdfName")%>"></a>
	        <script type="text/javascript"> 
			 $(function() {
                $('a.media').media({width:<%=(String)session.getAttribute("width")%>, height:<%=(String)session.getAttribute("height")%>});
            });
	        </script>
        </div>

</body>
</html>