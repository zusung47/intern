<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
</head>

<body>
<header>
	<div class="left-area">
	<a href="">
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
				<span class="dropdown-menu"><i class="ft-user"></i><b></b>님</span>
				<ul class="dropdown-list">
					<li><a href="<%=request.getContextPath()%>/logout.do"><i class="ft-power"></i> Logout</a></li>
				</ul>
			</div>
		</div>
	</div>
</header>
