<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线阅读</title>
<script type="text/javascript" src="../../plugins/flexpager/flexpaper_flash.js"></script>
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
	        <a id="viewerPlaceHolder" style="width:<%=(String)session.getAttribute("width")%>px;height:<%=(String)session.getAttribute("height")%>px;display:block"></a>
	        <script type="text/javascript"> 
				var fp = new FlexPaperViewer(	
						 'FlexPaperViewer',
						 'viewerPlaceHolder', { config : {
						 SwfFile : '../../doc/<%=(String)session.getAttribute("swfName")%>',//要浏览的swf文件
						 Scale : 1, // 初始化缩放比例，参数值应该是大于零的整数
						 ZoomTransition : 'easeOut',//Flexpaper中缩放样式   easenone, easeout, linear, easeoutquad
						 ZoomTime : 0.5,
						 ZoomInterval : 0.2,
						 FitPageOnLoad : true,
						 FitWidthOnLoad : true,
						 FullScreenAsMaxWindow : false,
						 ProgressiveLoading : false,
						 MinZoomSize : 0.2,
						 MaxZoomSize : 10,
						 SearchMatchAll : true,
						 InitViewMode : 'Portrait',
						 PrintPaperAsBitmap : false,
						 ViewModeToolsVisible : true,
						 ZoomToolsVisible : true,
						 NavToolsVisible : true,
						 CursorToolsVisible : true,
						 SearchToolsVisible : true,
  						 localeChain: 'zh_CN'
						 }});
	        </script>
        </div>

</body>
</html>