<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV</title>
<link rel="stylesheet"  href="http://localhost:9004/css/mycgv.css">
<link rel="stylesheet"  href="http://localhost:9004/css/am-pagination.css">
<script src="http://localhost:9004/js/jquery-3.6.0.min.js"></script>
<script src="http://localhost:9004/js/am-pagination.js"></script>
<script>
	$(document).ready(function(){
		
		//페이징 리스트 출력
		var pager = jQuery('#ampaginationsm').pagination({
		
		    maxSize: ${page.pageCount},	    		// max page size
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
/* 	           $(location).attr('href', "http://localhost:9000/mycgv/board_list.do?rpage="+e.page);          */
	           $(location).attr('href', "http://localhost:9004/board_list/"+e.page);         
	    });
		
 	});
</script> 
</head>
<body>
	<!-- Header Include -->
<!--<iframe src="http://localhost:9004/header.do" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>-->
	<iframe src="http://localhost:9004/header" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>
	
	
	<!---------------------------------------------->
	<!--------------- Content ----------------------->
	<!---------------------------------------------->
	<div class="content">
		<h1>게시판-리스트</h1>
		<table class="board">
			<tr>
				<td colspan="4">
				<!--<a href="board_write"> -->
					<a href="/board_write"> <!-- /를 안붙여주면 현재 호출된 페이지 뒤에 이어서 board_write가 붙어서 에러가 발생한다. /를 붙여줘라 + /board_list를 컨텍스트 패쓰로 인식해서 생기는 문제다. -->
					<button type="button" class="btn_style">글쓰기</button>
					</a>
				</td>
			</tr>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>등록날짜</th>
				<th>조회수</th>
			</tr>
			
			<c:forEach var="vo" items="${list}">
			<tr>
				<td>${vo.rno}</td>
<%-- 				<td><a href="board_content.do?bid=${vo.bid}">${vo.btitle}</a></td> 쿼리스트링방식--%>
				<td><a href="/board_content/${vo.bid}">${vo.btitle}</a></td> <!-- board_content앞에 /를 안주면 board_list 뒤에 붙는다. 컨텍스트 패스 뒤에 바로 붙인다는 뜻으로 /를 처리해주어야 한다. -->
				<td>${vo.bdate }</td>
				<td>${vo.bhits }</td>
			</tr>
			</c:forEach>
			
			<tr>
				<td colspan="4"><div id="ampaginationsm"></div></td>
			</tr>
		</table>	
	</div>
	
	<!-- footer Include -->
	<iframe src="http://localhost:9004/footer" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
	
</body>
</html>







