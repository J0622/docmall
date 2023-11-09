


	// 스크립트 태그는 제외하고 사용
	$(document).ready(function (){
		// 1차 카테고리 오버
		$("div#category_menu li a").on("mouseover", function(e){
			e.preventDefault();
			// console.log("1차 카테고리 오버");
			
			let sel_first_category = $(this);

			let cg_code = $(this).data("cg_code");

			// console.log("1차: ", cg_code);

			// return;

			let url = '/category/secondCategory/' + cg_code;
			$.getJSON(url, function(category){
				// console.log(category);
				let str = '<ul class="nav justify-content-center" id="second_category">';
				for(let i=0; i<category.length; i++){
					str += '<li class="nav-item">';
					str += '<a class="nav-link active" href="#" data-cg_code="'+ category[i].cg_code +'">'+ category[i].cg_name +'</a>';
					str += '</li>';
				}
				str += '</ul>';
				// console.log(str);
				sel_first_category.parent().parent().next().remove();
				sel_first_category.parent().parent().after(str);
				
			});
		});
			// 2차 카테고리 선택
			// [중요] 동적태그에 이벤트를 사용할때 반드시 동적태그 참조 선택자를 사용해야 한다.
			// $("정적태그 참조 선택자").on("click", "동적태그 참조 선택자", function()
		$("div#category_menu").on("click", "ul#second_category", function(){
			console.log("2차 카테고리 작업 테스트");

		});








			
	});
		