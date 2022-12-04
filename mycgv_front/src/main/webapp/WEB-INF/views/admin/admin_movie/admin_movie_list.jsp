<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV</title>
<link rel="stylesheet"  href="http://localhost:9004/css/cgv.css">
<link rel="stylesheet"  href="http://localhost:9004/css/am-pagination.css">
<script src="http://localhost:9004/js/jquery-3.6.0.min.js"></script>
<script src="http://localhost:9004/js/am-pagination.js"></script>
<script>
$(document).ready(function(){
	
	//페이징 리스트 출력
	var pager = jQuery('#ampaginationsm').pagination({
	
	    maxSize: 7,	    		// max page size
	    totals: ${page.dbCount},	// total rows	
	    page: ${page.reqPage},		// initial page		
	    pageSize: ${page.pageSize},	// max number items per page
	
	    // custom labels		
	    lastText: '&raquo;&raquo;', 		
	    firstText: '&laquo;&laquo;',		
	    prevText: '&laquo;',		
	    nextText: '&raquo;',
			     
	    btnSize:'sm'	// 'sm'  or 'lg'		
	});
	
	//페이징 번호 클릭 시 이벤트 처리
	jQuery('#ampaginationsm').on('am.pagination.change',function(e){		
		   jQuery('.showlabelsm').text('The selected page no: '+e.page);
           $(location).attr('href', "http://localhost:9004/admin_movie_list.do/"+e.page);         
    });
	
	});
</script> 
</head>
<body>
	<!-- Header Include -->
	<iframe src="http://localhost:9004/header" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>
	
	
	<!---------------------------------------------->
	<!--------------- Content ----------------------->
	<!---------------------------------------------->
	<div class="content">
		<h1>영화관리-리스트</h1>
		<table class="board">	
			<tr>
				<td colspan="5">
					<a href="/admin_movie_regist">
					<button type="button" class="btn_style">영화등록</button>
					</a>
				</td>	
			</tr>	
			<tr>
				<th>번호</th>
				<th>영화제목</th>
				<th>장르</th>
				<th>등록날짜</th>				
			</tr>
			
			<c:forEach var="vo"  items="${list}">
			<tr>
				<td>${vo.rno }</td>
				<td><a href="/admin_movie_content/${vo.mid }/${page.reqPage}">${vo.mname }</a></td>
				<td>${vo.mcategory }</td>				
				<td>${vo.mdate }</td>
			</tr>			
			</c:forEach>
			
			<tr>
				<td colspan="5"><div id="ampaginationsm"></div></td>
			</tr>
		</table>	
	</div>
	
	<!-- footer Include -->
	<iframe src="http://localhost:9004/footer" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
	
</body>
</html>







