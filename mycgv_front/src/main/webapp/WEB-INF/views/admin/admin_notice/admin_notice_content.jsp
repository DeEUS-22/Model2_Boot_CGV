<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV</title>
<link rel="stylesheet"  href="http://localhost:9004/css/cgv.css">
</head>
<body>
	<!-- Header Include -->
	<iframe src="http://localhost:9004/header" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>
	
	
	<!---------------------------------------------->
	<!--------------- Content ----------------------->
	<!---------------------------------------------->
	<div class="content">
		<h1>공지사항-상세보기</h1>
		<table class="boardContent">	
			<tr>				
				<th>등록일자</th>
				<td>${vo.ndate }</td>
				<th>조회수</th>
				<td>${vo.nhits }</td>
			</tr>		
			<tr>				
				<th>제목</th>
				<td colspan="3">${vo.ntitle }</td>
			</tr>
			<tr>				
				<th>내용</th>
				<td colspan="3">${vo.ncontent }<br><br>
				<c:if test="${vo.nsfile != null }">
					<img src="http://localhost:9004/upload/${vo.nsfile}"
						width="50%" >
				</c:if>
				<br><br></td>
			</tr>
			<tr>
				<td colspan="4">
					<a href="/admin_notice_update/${vo.nid }/${rpage}"><button type="button" class="btn_style">수정하기</button></a>
					<a href="/admin_notice_delete/${vo.nid }/${rpage}"><button type="button" class="btn_style">삭제하기</button></a>
					<a href="/admin_notice_list/${rpage}">
						<button type="button" class="btn_style">리스트</button></a>
					<a href="http://localhost:9004/admin"><button type="button" class="btn_style">관리자홈</button></a>
				</td>
			</tr>			
		</table>	
	</div>
	
	<!-- footer Include -->
	<iframe src="http://localhost:9004/footer" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
	
</body>
</html>






