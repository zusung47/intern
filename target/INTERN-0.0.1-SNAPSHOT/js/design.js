$(document).ready(function(){

	/* Dropdown */
	$('.dropdown-menu').click(function(){
		$('.dropdown-list').slideToggle();
	});

	/* LNB의 높이 계산 */
	setInterval(function (){
		leftH()
	}, 500);
	function leftH(){
		var chkHeight = window.innerHeight;
		var headerH = $('header .left-area').height();
		$('article').css({
		height : chkHeight-headerH,
		top : headerH
		});
	}
	/* LNB 구현 */
	$('nav li').click(function(){
		$('nav li').removeClass('open');
		$(this).addClass('open');
	});
	

	/* Tab contents */
	$('#tab-contents-1').click(function(){
		$('.tab-menu li').removeClass('active');
		$(this).addClass('active');
		$('.tab-contents div').removeClass('show');
		$('.tab-contents-1').addClass('show');
		
	});
	$('#tab-contents-2').click(function(){
		$('.tab-menu li').removeClass('active');
		$(this).addClass('active');
		$('.tab-contents div').removeClass('show');		
		$('.tab-contents-2').addClass('show');
		
	});
	$('#tab-contents-3').click(function(){
		$('.tab-menu li').removeClass('active');
		$(this).addClass('active');
		$('.tab-contents div').removeClass('show');		
		$('.tab-contents-3').addClass('show');
		
	});


	/* 조회기간 range dropdown */
	$('.calender').click(function(){
		$('.range').slideToggle();
	});

	/* 스위치 버튼 */
	$('.switch').click(function(){
		$(this).toggleClass('on');
	});

	/* 알림톡템플릿 textarea focus와 blur시 배경색 변경 */
	var text1 = $('.msgText01');
	text1.focus(function(){
		var text1 = $('.msgText01').css("background","#fff");
	});
	text1.blur(function(){
		var text1 = $('.msgText01').css("background","#eceff1");
	});

	var text2 = $('.msgText02');
	text2.focus(function(){
		var text2 = $('.msgText02').css("background","#fff");
	});
	text2.blur(function(){
		var text2 = $('.msgText02').css("background","#eceff1");
	});


	/* 알림톡템플릿 치환항목버튼 드롭다운 */
	btnchk = 0;
	$(".down-1").click(function(){
		btnchk++;
		if ( 1 >= btnchk) {
			$(".list-1").addClass('on');
		} else if ( btnchk >= 2 ) {
			$(".list-1").removeClass('on');
			btnchk = 0;
		}
	});
	$(".down-2").click(function(){
		btnchk++;
		if ( 1 >= btnchk) {
			$(".list-2").addClass('on');
		} else if ( btnchk >= 2 ) {
			$(".list-2").removeClass('on');
			btnchk = 0;
		}
	});

	/* Mobile LNB on/off */
	$('.mobile-menu .ft-menu').parent('button').click(function(){
		$('article').toggleClass('switch');
	});
	$('.mobile-menu .ft-more-vertical').parent('button').click(function(){
		$('header .right-area').slideToggle();
	});

//var chkHeight = window.innerHeight;
//$('.modal').append("<div class='dim display-block' style='height:"+chkHeight+"px;'></div>");

});/* $(document).ready(function(){ END */



/*새창팝업열기 */
function openPopup(popName,popWidth,popHeight){
	//alert('popWidth');
	var popUrl = "/contents/popup/" + popName + ".jsp";
	var popOption = "left=10, top=10, width="+popWidth+",height="+popHeight+", resizable=0, scrollbars=yes";
	window.open(popUrl, "_blank", popOption);

}

/* 모달팝업열기 */
var modalH = $(window).height();
function openModal(modalName){
	$('#'+modalName).addClass('display-block');
	$('#'+modalName).css('height',modalH);
	$('html').css('overflow-y','unset');

	/* 모달 닫힘 */
	function closeModal(){
		$('#'+modalName).removeClass('display-block');
		$('html').css('overflow-y','scroll');
	}

	// DIM영역 클릭시 모달 닫힘
	$('.modal').click(function(e){	//.modal 영역을 클릭했을때 작동되는 이벤트
		if($('.modal').hasClass('display-block')){		//.modal 영역이 display-block이라는 클래스를 갖고있을 때
			if(!$('.modal-box').has(e.target).length){		//.modal-box 제외한 영역을 클릭한다면 
				//console.log(e);
				//console.log(e.target);
				$('.modal').removeClass('display-block');		//.modal의 display-block클래스를 제거해라

			}
		}
	})
	

	//ESC버튼 누를 경우 모달 닫힘
	window.onkeyup = function(e) {
		var key = e.keyCode ? e.keyCode : e.which;
		if(key == 27) {
			closeModal();
		}
	}
	//상단X버튼, 하단 취소버튼 클릭시 
	$('.btnModalClose').click(closeModal);
}

// 로그인 세션이 끊긴 후 ajax 통신시 403에러 발생 처리 함수
$(document).ready(function() {
	$(document).ajaxError(function (event, xhr, options, exc) {
		var hostIndex = location.href.indexOf(location.host) + location.host.length;
		var contextPath = location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
		if (xhr.status == 400) {
			alert(xhr.responseText);
		} else if (xhr.status == 403) {
			alert("로그인 먼저 해주시기 바랍니다.");
			location.href = contextPath + '/loginForm.do';
		} else {
			alert("[" + xhr.status + "] 알 수 없는 문제가 발생하였습니다. 관리자에게 문의하세요.");
		}
	});
});