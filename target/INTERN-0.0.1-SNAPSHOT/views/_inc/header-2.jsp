<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
</head>

<body>
<header>
	<div class="left-area">
	<a href="<%=request.getContextPath()%>/messageList.do">
		<button type="button" class="mobile-menu"><i class=" ft-menu"></i></button>
		<!-- <a class="" href="<%=request.getContextPath()%>/"> -->
			<h1>
				<img src="<%=request.getContextPath()%>/images/logo/logo-light-sm.png" alt=""/>
				<p>
					TG 1st Message
					<small>INTERN PROJECT</small>
				</p>
			</h1>
	</a>
		<button type="button" class="mobile-menu"><i class=" ft-more-vertical"></i></button>
	</div>
	<div class="right-area">
		<div class="inner-left">
			<p class="notice">
				
			</p>
		</div>
		<div class="user-box">
			<!-- a href="#"><i class="ft-download"></i>메뉴얼 다운로드</a-->
			<div class="dropdown">
				<span class="dropdown-menu"><i class="ft-user"></i><b><sec:authentication property="principal.member.userName"/></b>님</span>
				<ul class="dropdown-list">
					<li><a href="#" onclick="document.getElementById('logout').submit()"><i class="ft-power"></i>Logout</a></li>
					<form id="logout" action="/logout" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>
				</ul>
			</div>
		</div>
	</div>
</header>
