


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
			
			// 1차 카테고리를 불러오는 주소
			let url = '/category/secondCategory/' + cg_code;
			
			$.getJSON(url, function(category){
				// console.log(category);
				let str = '<ul class="nav justify-content-center" id="second_category">';
				for(let i=0; i<category.length; i++){
					str += '<li class="nav-item">';
					str += '<a class="nav-link active" href="#" data-cg_code="'+ category[i].cg_code + '"data-cg_name="'+ category[i].cg_name + '">'+ category[i].cg_name +'</a>';
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
		$("div#category_menu").on("click", "ul#second_category li a", function(e){
			// console.log("2차 카테고리 작업 테스트");
			let cg_code = $(this).data("cg_code");
			let cg_name = $(this).data("cg_name");
			// ${cg_code}이 부분은 jsp에서 사용할 경우 jsp는 EL문법으로 변환해서 사용해야 한다.
			
			// 현재 파일이 jsp일 경우 ${cg_code}이 형식을 변수로 인식하는 것이 아니라 jsp의 EL문법으로 서버에서 동작이 된다. 
			// location.href = `/user/product/pro_list?cg_code=${cg_code}&cg_name=${cg_name}`; (에러 발생) 
			
			// jsp파일에서 사용하는 경우 (백틱이 아닌 작은 따옴표인거 유의)
			// location.href = '/user/product/pro_list?cg_code='cg_code +'&cg_name=' + cg_name; 
			
			
			// 현재 파일이 js일 경우 
			location.href = `/user/product/pro_list?cg_code=${cg_code}&cg_name=${cg_name}`; 
		});








			
	});
		