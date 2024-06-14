<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>TG 1st UMS 통합메시지전송솔루션</title>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/logo/favicon.ico">
<link href="<%=request.getContextPath()%>/css/login.css" type="text/css" rel="stylesheet"  media="screen" />
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/rsa/rsa.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/rsa/jsbn.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/rsa/prng4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/rsa/rng.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>

<script>
	function messageListOpen() {
		$("#loginForm").attr("action", "<%=request.getContextPath()%>/messageList.do")
		$("#loginForm").submit();
	}
</script>

<body class="login">
<!-- 컨텐츠 영역 -->
<div class="container">
	<div class="card login-area">
		<h1 class="logo-box">
			<img src="<%=request.getContextPath()%>/images/logo/logo.png" alt="" width="10%"/>
			<span>TG 1st Message</span>
			<small>통합메시지전송솔루션</small>
		</h1>
		<form id="loginForm" name="loginForm">
			<ul class="login-input mt-20">
				<li>
					<label for="login-id"><i class="ft-user"></i></label>
					<input type="text" class="" title="" value="" name="userId" id="userId"  onkeypress="if(event.keyCode==13) loginChk();" />
				</li>
				<li>
					<label for="loginPw"><i class="ft-unlock"></i></label>
					<input type="password" class="" title="" value="" name="userPw" id="userPw"  onkeypress="if(event.keyCode==13) loginChk();"/>
				</li>
			</ul>
			<!-- <button type="button" class=" mt-20 btn btn-login" onclick="javascritp:window.open('/html/contents/test-send/send-simple-1.jsp','_self');">로그인</button> -->
			<button type="button" class=" mt-20 btn btn-login" onclick="messageListOpen()">로그인</button>
			<button type="button" class="testbtn" onclick="testdb()">테스트</button>
			<!-- <button type="button" class=" mt-20 btn btn-login" id="login_btn" >로그인</button> -->
		</form>
	</div>
</div>
<!--// 컨텐츠 영역 -->
</body>
</html>
<%-- <%@ include file="/WEB_INF/views/_inc/footer.jsp"%> --%>