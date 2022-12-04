$(document).ready(()=>{
		
		//결과보기 버튼 이벤트 처리
		$("#search").click(()=>{
			//데이터 유효성 체크(값이 있는지여부) 후 api 호출진행
			if($("select").val() == "choice"){
				alert("박스오피스 타입을 선택해주세요");
				$("select").focus();
				return false;
			}else if($("#sdate").val() == ""){
				alert("날짜를 입력해주세요");
				$("#sdate").focus();
				return false;
			}else{
				let type = $("select").val();
				let mdate = $("#sdate").val();
				let url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/";
				if(type == "day"){
					//일별					
					url += "searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt="+mdate;
					$.getJSON(url,function(kobis){
						//출력형식:제목,날짜:h1태그형식, 박스오피스 리스트는 테이블형태로 출력
						//새로운 날짜 실행시 기존의 데이터는 삭제되도록 진행해주세요
						//순위, 영화제목, 개봉일, 누적관객수, 상영횟수
						let output = "<div id='output'><h1>"+ kobis.boxOfficeResult.boxofficeType+"</h1>";
						output += "<h3>"+ kobis.boxOfficeResult.showRange +"</h3>";
						output += "<table class='output'>";
						output += "<tr>";
						output += "<th>순위</th>";
						output += "<th>영화제목</th>";
						output += "<th>개봉일</th>";
						output += "<th>누적관객수</th>";
						output += "<th>상영횟수</th>";
						output += "</tr>";
						
						let mlist = kobis.boxOfficeResult.dailyBoxOfficeList;
						for(obj of mlist){
							output += "<tr>";
							output += "<td>" + obj.rank + "</td>";
							output += "<td><span class='mclass' id="+obj.movieCd+">" + obj.movieNm + "</span></td>";
							output += "<td>" + obj.openDt + "</td>";
							output += "<td>" + obj.audiCnt + "</td>";
							output += "<td>" + obj.showCnt + "</td>";
							output += "</tr>";
						}
						
						output += "</table></div>";
						
						//기존 출력내용 삭제 : remove()
						$("div#output").remove();
						$("div#detail").remove();
						$("div").after(output);
						
						
						//영화제목 클릭이벤트 - Dynamic HTML은 생성된 콜백함수에서 이벤트 실행!!!
						$("span.mclass").click(function(){
							//alert($(this).attr("id"));
							movieDetail($(this).attr("id"));
						});						
						
					});
				}else{
					//주말					
					url += "searchWeeklyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt="+mdate;
					$.getJSON(url, (kobis)=>{
						//출력형식:제목,날짜:h1태그형식, 박스오피스 리스트는 테이블형태로 출력
						//순위, 영화제목, 개봉일, 누적관객수, 상영횟수
					});
				}
			}
		});//결과보기 버튼 이벤트
		
		
		/*****************
			영화 상세 정보 출력
		*****************/
		function movieDetail(movieCd){
			//alert("영화코드-->" + movieCd);
			let url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=f5eef3421c602c6cb7ea224104795888&movieCd="+movieCd;
			
			$.getJSON(url, function(result){
				let info = result.movieInfoResult.movieInfo;
				let output = "<div id='detail'><h1>영화 상세 정보</h1>";
				output += "<h3>제목 : " + info.movieNm + "</h3>";
				output += "<table class='detail'>";
				output += "<tr>";
				output += "<th>장르</th>";
				output += "<td>"+ info.genres[0].genreNm +"</td>";
				output += "</tr>";
				output += "<tr>";
				output += "<th>감독</th>";
				output += "<td>"+ info.directors[0].peopleNm +"</td>";
				output += "</tr>";
				output += "<tr>";
				output += "<th>배우</th>";
				output += "<td>"+ info.actors[0].peopleNm +"</td>";
				output += "</tr>";
				output += "</table></div>";

				//기존 출력내용 삭제 : remove()
				$("div#output").remove();
				$("div#detail").remove();
				$("div").after(output);
				
			});//getJSON
			
		}//movieDetail
		
		
	});