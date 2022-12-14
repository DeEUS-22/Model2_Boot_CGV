<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV</title>
<link rel="stylesheet"  href="http://localhost:9004/css/cgv.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script>
	let login_result = '${login_result}';
	let logout_result = '${logout_result}';
	
	if(logout_result == 'ok'){
		alert("로그아웃에 성공하셨습니다.");
	}
	
	if(login_result == 'ok'){
		alert("로그인에 성공하셨습니다.");
	}else if(login_result == 'fail'){
		alert("아이디 또는 패스워드가 정확하지 않습니다. \r로그인을 다시 진행해주세요.")
		location.href = "http://localhost:9004/login";
		//$(location).attr("href", "http://localhost:9004/login");
	}
</script>
<script>
	$(document).ready(function(){
		$('.slider').bxSlider();
	});
</script>
</head>
<body>
<!-- Header Include -->
<iframe src="http://localhost:9004/header" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>


<!---------------------------------------------->
<!--------------- Content ----------------------->
<!---------------------------------------------->
<div class="carousel">
	<div class="slider">
		<div><img src="http://localhost:9004/images/event1.jpg"></div>
		<div><img src="http://localhost:9004/images/event2.jpg"></div>
		<div><img src="http://localhost:9004/images/event3.jpg"></div>
		<div><img src="http://localhost:9004/images/event4.jpg"></div>
	</div>
</div>
<div class="content">
	<section>
		<h1>무비차트 | 상영예정작</h1>
		<div class="s1_article">
			<article>
				<img src="http://localhost:9004/images/BlackPanther.jpg">
				<div>블랙팬서-와칸다 포··</div>
				<div>84%  | 예매율 33.2%</div>
			</article>
			<article>
				<img src="http://localhost:9004/images/Ditto.jpg">
				<div>동감</div>
				<div>99%  | 예매율 18.5%</div>
			</article>
			<article>
				<img src="http://localhost:9004/images/Decibel.jpg">
				<div>데시벨</div>
				<div>99%  | 예매율 16.3%</div>
			</article>
			<article>
				<img src="http://localhost:9004/images/Owl.jpg">
				<div>올빼미</div>
				<div>99%  | 예매율 8.8%</div>
			</article>
			<article>
				<img src="http://localhost:9004/images/The_fall.jpg">
				<div>폴-600미터</div>
				<div>99%  | 예매율 5.7%</div>
			</article>
		</div>
	</section>
	
	<section>
		<h1>특별관</h1>
		<div class="s2_article">
			<div>
				<img src="http://localhost:9004/images/suit.png">
			</div>
			<ul>
				<li>
					<span>SUITE CINEMA</span>
					<span>#호텔 컨셉의 프리미엄관</span>	
				</li>
				<li>
					<span>CINE & LIVINGROOM</span>
					<span>#신개념 소셜 상영관</span>	
				</li>
				<li>
					<span>4DX</span>
					<span>#모션시트 #오감체험</span>	
				</li>
				<li>
					<span>CINE de CHEF</span>
					<span>#쉐프가 있는 영화관</span>	
				</li>
			</ul>
		</div>
	</section>
	
	<section>
		<ul>
			<li>
				<h3>영화관람권</h3>
				<button type="button" class="btn_style2">더보기</button>
			</li>
			<li>
				<img src="http://localhost:9004/images/cgv_movie.jpg">
				<div>
					<label>CGV 영화관람권</label>
					<label>12,000원</label>
				</div>
			</li>
			<li>
				<img src="http://localhost:9004/images/cgv_gold.jpg">
				<div>
					<label>CGV 골드클래스</label>
					<label>40,000원</label>
				</div>
			</li>
			<li>
				<img src="http://localhost:9004/images/cgv_4DX.jpg">
				<div>
					<label>4DX관람권</label>
					<label>19,000원</label>
				</div>
			</li>
		</ul>
		<ul>
			<li>
				<h3>기프트카드</h3>
				<button type="button" class="btn_style2">더보기</button>
			</li>
			<li >
				<img src="http://localhost:9004/images/paconnie_a.jpg">
				<div>
					<label>PACONNIE A형</label>
					<label>금액충전형</label>
				</div>
			</li>
			<li>
				<img src="http://localhost:9004/images/paconnie_b.jpg">
				<div>
					<label>PACONNIE B형</label>
					<label>금액충전형</label>
				</div>
			</li>
			<li>
				<img src="http://localhost:9004/images/paconnie_c.jpg">
				<div>
					<label>PACONNIE C형</label>
					<label>금액충전형</label>
				</div>
			</li>
		</ul>
		<ul>
			<li>
				<h3>음료</h3>
				<button type="button" class="btn_style2">더보기</button>
			</li>
			<li >
				<img src="http://localhost:9004/images/coke.jpg">
				<div>
					<label>탄산음료(M)</label>
					<label>2,500원</label>
				</div>
			</li>
			<li>
				<img src="http://localhost:9004/images/Americano.jpg">
				<div>
					<label>아메리카노(HOT)</label>
					<label>3,500원</label>
				</div>
			</li>
			<li>
				<img src="http://localhost:9004/images/IcedAmericano.jpg">
				<div>
					<label>아메리카노(ICE)</label>
					<label>4,000원</label>
				</div>
			</li>
		</ul>
	</section>		
</div>

<!-- footer Include -->
<iframe src="http://localhost:9004/footer" width="100%" height="530px" scrolling="no" frameborder=0></iframe>

</body>
</html>







