<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<link rel="stylesheet"  href="http://localhost:9004/css/cgv.css">
</head>
<body>
<!---------------------------------------------->
<!--------------- header ----------------------->
<!---------------------------------------------->
<header>
	<div class="header_contents1">
		<div>
			<a href="http://localhost:9004/index" target="_parent">
				<img src="http://localhost:9004/images/logoRed.png" width="150" height="70"></a>
			<span>CULTURFLEX</span>
		</div>
		<c:choose>	
		<c:when test="${sessionScope.sid == null}">	
		<div>
			<a href="http://localhost:9004/preparing" target="_parent" >
				<img src="http://localhost:9004/images/hcard.png" class="hcard">
			</a>
			<a href="http://localhost:9004/login" target="_parent" >
				<img src="http://localhost:9004/images/loginPassword.png">
				<span>로그인</span>
			</a>
			<a href="http://localhost:9004/join" target="_parent" >
				<img src="http://localhost:9004/images/loginJoin.png">
				<span>회원가입</span>
			</a>
			<a href="http://localhost:9004/mycgv" target="_parent">
				<img src="http://localhost:9004/images/loginMember.png">
				<span>MY CGV</span>
			</a>
			<a href="http://localhost:9004/board_list" target="_parent" >
				<img src="http://localhost:9004/images/loginCustomer.png">
				<span>게시판</span>
			</a>
			<a href="http://localhost:9004/notice_list" target="_parent" >
				<img src="http://localhost:9004/images/loginCustomer.png">
				<span>공지사항</span>
			</a>				
		</div>
		</c:when>
		
		<c:otherwise>
		<div>
			<img src="http://localhost:9004/images/hcard.png">
			<a href="http://localhost:9004/logout" target="_parent" >
				<img src="http://localhost:9004/images/loginPassword.png">
				<span>로그아웃</span>
			</a>
			<a href="http://localhost:9004/join" target="_parent" >
				<img src="http://localhost:9004/images/loginJoin.png">
				<span>회원가입</span>
			</a>
			<a href="http://localhost:9004/mycgv" target="_parent">
				<img src="http://localhost:9004/images/loginMember.png">
				<span>MY CGV</span>
			</a>
			<a href="http://localhost:9004/board_list" target="_parent" >
				<img src="http://localhost:9004/images/loginCustomer.png">
				<span>게시판</span>
			</a>
			<a href="http://localhost:9004/notice_list" target="_parent" >
				<img src="http://localhost:9004/images/loginCustomer.png">
				<span>공지사항</span>
			</a>
			<!-- 관리자 계정인 경우에만 admin 메뉴를 open -->
			<c:if test="${sessionScope.sid == 'admin' }">
			<a href="http://localhost:9004/admin" target="_parent" >
				<img src="http://localhost:9004/images/loginCustomer.png">
				<span>Admin</span>
			</a>
			</c:if>				
		</div>				
		</c:otherwise>
		
		</c:choose>
	</div>
	<div class="header_contents2">
		<nav>
			<ul class="dropdown">
				<li>
					<h2><a href="#">영화</a></h2>
					<dl class="dropdown-content">
						<dt><h2>영화</h2></dt>
						<dd><h3>무비차트</h3></dd>
						<dd><h3>아트하우스</h3></dd>
						<dd><h3>ICECON</h3></dd>							
					</dl>				
				</li>
				<li>
					<h2><a href="#">극장</a></h2>
					<dl class="dropdown-content">
						<dt><h2>극장</h2></dt>
						<dd><h3>CGV 극장</h3></dd>
						<dd><h3>특별관</h3></dd>
					</dl>				
				</li>
				<li>
					<h2><a href="#">예매</a></h2>
					<dl class="dropdown-content">
						<dt><h2>예매</h2></dt>
						<dd><h3>빠른예매</h3></dd>
						<dd><h3>상영스케줄</h3></dd>
						<dd><h3>English Ticketing</h3></dd>
						<dd><h3>English Schedule</h3></dd>
					</dl>				
				</li>
				<li>
					<h2><a href="#">스토어</a></h2>
					<dl class="dropdown-content">
						<dt><h2>영화관람권</h2></dt>
						<dd><h3>기프트카드</h3></dd>
						<dd><h3>콤보</h3></dd>
						<dd><h3>팝콘</h3></dd>
						<dd><h3>음료</h3></dd>
						<dd><h3>스낵</h3></dd>
						<dd><h3>플레이존</h3></dd>
					</dl>				
				</li>
				<li>
					<h2><a href="#">이벤트</a></h2>
					<dl class="dropdown-content">
						<dt><h2>이벤트</h2></dt>
						<dd><h3>SPECIAL</h3></dd>
						<dd><h3>영화/예매</h3></dd>
						<dd><h3>멤버십/CLUB</h3></dd>
						<dd><h3>CGV 극장별</h3></dd>	
					</dl>				
				</li>
				<li>
					<h2><a href="#">혜택</a></h2>
					<dl class="dropdown-content">
						<dt><h2>혜택</h2></dt>
						<dd><h3>CGV 할인정보</h3></dd>
						<dd><h3>CLUB 서비스</h3></dd>
						<dd><h3>VIP 라운지</h3></dd>							
					</dl>				
				</li>				
			</ul>
		</nav>		
	</div>	
</header>
</body>
</html>