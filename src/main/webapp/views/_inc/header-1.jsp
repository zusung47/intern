<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>TG 1st 통합 메시지 전송 솔루션</title>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/logo/favicon.ico">
<link href="<%=request.getContextPath()%>/css/total.css" type="text/css" rel="stylesheet"  media="screen" />
<link href="<%=request.getContextPath()%>/css/mobile.css" type="text/css" rel="stylesheet"  media="screen" />
<link href="<%=request.getContextPath()%>/css/jquery-ui.css" type="text/css" rel="stylesheet"  media="screen" />
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js" language="javascript" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/design.js" language="javascript" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.min.js" language="javascript" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js" language="javascript" type="text/javascript"></script>
<%  
// 뒤로가기 post값 cache miss 방지
response.setHeader("Cache-Control","no-store");  
response.setHeader("Pragma","no-cache");  
response.setDateHeader("Expires",0);  
if (request.getProtocol().equals("HTTP/1.1"))
        response.setHeader("Cache-Control", "no-cache");
%>

