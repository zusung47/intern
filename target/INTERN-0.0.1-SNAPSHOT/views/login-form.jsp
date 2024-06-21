<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	window.onload = function() {
		var rsaPublicKeyModulus = document.getElementById("rsaPublicKeyModulus").value;
		var rsaPublicKeyExponent = document.getElementById("rsaPublicKeyExponent").value;

		console.log("Public Key Modulus:", rsaPublicKeyModulus);
		console.log("Public Key Exponent:", rsaPublicKeyExponent);
	};

	function validateEncryptedForm() {
		var username = document.getElementById("beforeUsername").value;
		var password = document.getElementById("beforePassword").value;
		if(!username || !password) {
			alert("아이디나 비밀번호를 입력해주세요.");
			return false;
		}

		try {
			var rsaPublicKeyModulus = document.getElementById("rsaPublicKeyModulus").value;
			var rsaPublicKeyExponent = document.getElementById("rsaPublicKeyExponent").value;
			submitEncryptedForm(username, password, rsaPublicKeyModulus, rsaPublicKeyExponent);
		} catch (err) {
			alert(err);
		}
	}

	function submitEncryptedForm(username, password, rsaPublicKeyModulus, rsaPublicKeyExponent) {
		var rsa = new RSAKey();
		rsa.setPublic(rsaPublicKeyModulus, rsaPublicKeyExponent);

		var securedUsername = rsa.encrypt(username);
		var securedPassword = rsa.encrypt(password);

		console.log("test : " + securedPassword);

		var securedLoginForm = document.getElementById("loginForm");
		securedLoginForm.TGusername.value = securedUsername;
		securedLoginForm.TGpassword.value = securedPassword;
		securedLoginForm.submit();
	}

	function loginChk() {
		validateEncryptedForm();
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
<%--		<span>--%>
<%--			<c:if test="${error}">--%>
<%--				<p> ERROR : <c:out value="${exception}"/></p>--%>
<%--			</c:if>--%>
<%--		</span>--%>
			<ul class="login-input mt-20">
				<li>
					<label for="login-id"><i class="ft-user"></i></label>
					<input type="text" class="" title="" value="" name="beforeUsername" id="beforeUsername"  onkeypress="if(event.keyCode==13) loginChk();" />
				</li>
				<li>
					<label for="loginPw"><i class="ft-unlock"></i></label>
					<input type="password" class="" title="" value="" name="beforePassword" id="beforePassword"  onkeypress="if(event.keyCode==13) loginChk();"/>
				</li>
				<input type="hidden" id="rsaPublicKeyModulus" value="<%=request.getAttribute("publicKeyModulus")%>"/>
				<input type="hidden" id="rsaPublicKeyExponent" value="<%=request.getAttribute("publicKeyExponent")%>"/>
			</ul>
			<!-- <button type="button" class=" mt-20 btn btn-login" onclick="javascritp:window.open('/html/contents/test-send/send-simple-1.jsp','_self');">로그인</button> -->
			<button type="button" class=" mt-20 btn btn-login" id="login_btn"  onclick="validateEncryptedForm();">로그인</button>
		<form id="loginForm" name="loginForm" method="post" action="/login">
			<input type="hidden" name="TGusername" id="TGusername"/>
			<input type="hidden" name="TGpassword" id="TGpassword"/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	</div>
</div>
<!--// 컨텐츠 영역 -->
</body>
</html>
<%-- <%@ include file="/WEB_INF/views/_inc/footer.jsp"%> --%>