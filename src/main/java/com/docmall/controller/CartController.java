package com.docmall.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.domain.CartVO;
import com.docmall.domain.MemberVO;
import com.docmall.dto.CartDTOList;
import com.docmall.service.CartService;
import com.docmall.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user/cart/*")
public class CartController {

	private final CartService cartService;
	
	@Resource(name = "uploadPath") // servlet-context.xml에서 bean이름 참조
	private String uploadPath;
	
	@ResponseBody
	@PostMapping("/cart_add")
	public ResponseEntity<String> cart_add(CartVO vo ,HttpSession session) throws Exception{
		ResponseEntity<String> entity = null;
		
//		ajax방식에서 상품코드,수량 2개 정보만 전송되어있는데 여기에 로그인한 사용자의 아이디 정보 추가 작업 진행
		String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();
		
//		vo에 id값을 추가해서 세션을 사용가능토록 함.
		vo.setMbsp_id(mbsp_id);
		cartService.cart_add(vo);
		
		entity = new ResponseEntity<String>("success",HttpStatus.OK);	
		
		return entity;
	}
	
//	장바구니 목록
	@GetMapping("/cart_list")
	public void cart_list(HttpSession session, Model model) throws Exception{
		String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();
		List<CartDTOList> cart_list = cartService.cart_list(mbsp_id);
		
		int cart_total_price = 0;
		
//		cart_list.forEach(vo -> {
//			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
//			
//			cart_total_price += (vo.getPro_price() - (vo.getPro_price() * (vo.getPro_discount() / 100))) * vo.getCart_amount();
//		});
		
		for(int i=0; i<cart_list.size(); i++) {
			CartDTOList vo = cart_list.get(i);
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
			cart_total_price += ((double)vo.getPro_price() - (vo.getPro_price() * vo.getPro_discount() * 1/100 )) * (double) vo.getCart_amount();
		}
		model.addAttribute("cart_list", cart_list);
		model.addAttribute("cart_total_price", cart_total_price);
		
	}
	
//	장바구니에서 사용할 이미지
	@ResponseBody
	@GetMapping("/imageDisplay")  //  매핑주소: "/user/product/imageDisplay?dateFolderName= fileName="
	public ResponseEntity<byte[]> imageDisplay(String dateFolderName, String fileName) 
			throws Exception {

		return FileUtils.getFile(uploadPath + dateFolderName, fileName);
	}

//	장바구니 수량 변경
	@PostMapping("/cart_amount_change")
	public ResponseEntity<String> cart_amount_change(Long cart_code,int cart_amount) throws Exception{
		ResponseEntity<String> entity = null;
		cartService.cart_amount_change(cart_code, cart_amount);
		
		entity = new ResponseEntity<String>("success" , HttpStatus.OK);
		
		return entity;
	}
	
//	장바구니 목록에서 개별삭제
	@PostMapping("/cart_list_del")
	public ResponseEntity<String> cart_list_del(Long cart_code) throws Exception{
		ResponseEntity<String> entity = null;
		cartService.cart_list_del(cart_code);
		entity = new ResponseEntity<String>("success",HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/cart_list_del")
	public String cart_list_del2(Long cart_code) throws Exception{
		cartService.cart_list_del(cart_code);
		return "redirect:/user/cart/cart_list";
	}
	
	//장바구니 선택삭제
	@PostMapping("/cart_sel_delete")
	public ResponseEntity<String> cart_sel_delete(@RequestParam("cart_code_arr[]") List<Long> cart_code_arr) {
		ResponseEntity<String> entity = null;
		
		//방법1. 하나씩 반복적으로 삭제.
		/*
		for(int i=0; i<cart_code_arr.size(); i++) {
			cartService.cart_delete(cart_code_arr.get(i));
		}
		*/
		
		//방법2. mybatis foreach : https://java119.tistory.com/85
		cartService.cart_sel_delete(cart_code_arr);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
}
