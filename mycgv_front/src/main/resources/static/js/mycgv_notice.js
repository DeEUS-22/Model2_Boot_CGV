$(document).ready(function(){
		
		initAjax(1);
		function initAjax(rpage){
			$.ajax({
				url : "/notice_list_json/"+rpage,
				success : function(result){
					//서버에서 데이터를 주고 받을 때는 String 형태로 전송하기 때문에 다시 JSON 객체로 변환하는 작업이 필요
					let data = JSON.parse(result);
					
					output = "<table class = 'board'>";
					output += "<tr><th>번호</th><th>제목</th><th>등록날짜</th><th>조회수</th></tr>";
					if(data.list.length == 0){
						output += "<tr><td colspan='4'>등록된 공지사항이 없습니다.</td></tr>"
					}else{
						for(dataset of data.list){
							output += "<tr>";
							output += "<td>" + dataset.rno + "</td>";
							output += "<td><a href='#' class='bclass' id='" + dataset.nid + "'>" + dataset.ntitle + "</a></td>";
							output += "<td>" + dataset.ndate + "</td>";
							output += "<td>" + dataset.nhits + "</td>";
							output += "</tr>";
						}
					}
					output += "<tr><td colspan='4'><div id='ampaginationsm'></div></td></tr>";
					output += "<table>";
					
					//출력
					$("table.board").remove();
					$("h1").after(output);
					
					//페이징 처리 이벤트는 외부 함수를 호출하여 처리
					noticePagination(data.dbCount, data.pageSize, data.reqPage);
					
					jQuery('#ampaginationsm').on('am.pagination.change',function(e){
						jQuery('.showlabelsm').text('The selected page no: '+e.page);
						initAjax(e.page);
					});
					
					//게시판 상세보기
					$(".bclass").click(function(){
						noticeContent($(this).attr("id"), data.reqPage);
					});
				}//success
			});//ajax
		}//initAjax
		
		function noticePagination(dbCount, pageSize, rpage){
			var pager = jQuery('#ampaginationsm').pagination({
				
			    maxSize: 7,	    		// max page size
			    totals: dbCount,		// total pages	
			    page: rpage,		// initial page		
			    pageSize: pageSize,		// max number items per page
			
			    // custom labels		
			    lastText: '&raquo;&raquo;', 		
			    firstText: '&laquo;&laquo;',		
			    prevText: '&laquo;',		
			    nextText: '&raquo;',
					     
			    btnSize:'sm'	// 'sm'  or 'lg'		
			});
		}//noticePagination()
		
		function noticeContent(nid){
			$.ajax({
				url : "/notice_content_json/"+nid,
				success : function(result){
					//String 객체를 JSON 객체로 변환
					let data = JSON.parse(result);
					output = "<table class = 'boardContent'>";
					output += "<tr><th>등록일자</th><td>" + data.ndate + "</td>";
					output += "<th>조회수</th><td>" + data.nhits + "</td></tr>";
					output += "<tr><th>제목</th><td colspan = '3'>" + data.ntitle + "</td></tr>";
					output += "<tr><th>내용</th><td colspan = '3'>" + data.ncontent + "<br><br>";
					if(data.nsfile != ""){
						output += "<img src = 'http://localhost:9004/upload/" + data.nsfile + "' width = '300px'>";
					}
					
					
					output += "</td></tr>";
					
					output += "<tr>";
					output += "<td colspan = '4'>";
					output += "<button type='button' class='btn_style' id = 'backList'>리스트</button>";
					output += "<button type='button' class='btn_style' id = 'backHome'>홈으로</button>";
					output += "</td>";
					output += "</tr>";
					output += "</table>";
					
					//출력
					$("table.board").remove();
					$("h1").after(output);
					
					$("#backList").click(function(){
						$(location).attr("href", "/notice_list");
					});
					$("#backHome").click(function(){
						$(location).attr("href", "/index");
					});
				}
			})//ajax
		}//noticeContent
	});//ready