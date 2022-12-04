<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV</title>
<link rel="stylesheet"  href="http://localhost:9004/css/cgv.css">
<script src="http://localhost:9004/js/jquery-3.6.0.min.js"></script>
<script src="http://localhost:9004/js/mycgv_rank.js"></script>
<style>
	span.mclass:hover { text-decoration:underline; }
	
	.kobis {
		width : 890px;
		margin : auto;
	}
	
	#output, #detail {
		width : 890px;
		margin : auto;
	}
	
	table.output , table.output th, table.output td,
 	table.detail, table.detail th, 
 	table.detail td {
 		border:1px solid #ccc;
 		border-collapse:collapse;
 		padding : 10px;
 	}
 	table.output th, table.detail th {
 		background-color:powderblue;
 	}
 	
 	
	
</style>
</head>	
<body>

<!-- Header Include -->
<iframe src="http://localhost:9004/header" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>

<!--------------- Content ----------------------->
<div class="kobis">
<h1>일별/주말 박스오피스 순위</h1>
<select>
	<option value="choice">선택</option>
	<option value="day">일별</option>
	<option value="week">주말</option>
</select>
<span>날짜입력</span>
<input type="text" id="sdate" placeholder="예)20220801">
<button type="button" id="search">결과보기</button>
</div>

<!-- footer Include -->
	<iframe src="http://localhost:9004/footer" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
</body>
</html>






