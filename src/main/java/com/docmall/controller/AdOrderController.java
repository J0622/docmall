package com.docmall.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.domain.OrderDetailInfoVO;
import com.docmall.domain.OrderDetailProductVO;
import com.docmall.domain.OrderVO;
import com.docmall.dto.Criteria;
import com.docmall.dto.PageDTO;
import com.docmall.service.AdOrderService;
import com.docmall.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@Log4j
@RequestMapping("/admin/order/*")
public class AdOrderController {
	private final AdOrderService adOrderService;
	
	@Resource(name = "uploadPath") // servlet-context.xml에서 bean이름 참조
	private String uploadPath;
	
	@GetMapping("/order_list")
	public void order_list(Criteria cri, Model model) throws Exception {
//		dto를 직접 변경하지 않고 해당 메소드에서 작업하는 방법
		cri.setAmount(2);

		List<OrderVO> order_list = adOrderService.order_list(cri);
		model.addAttribute("order_list", order_list);

		int totalcount = adOrderService.getTotalCount(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, totalcount));

	}
	
	
//	주문상세 방법 1) 주문상세정보가 클라이언트 json 형태로 변환되어 보내진다. (pom.xml 에 jackson-databind 라이브러리 백그라운드로 작동)
	@GetMapping("/order_detail_info1/{ord_code}")
	public ResponseEntity<List<OrderDetailInfoVO>> order_detail_list1(@PathVariable("ord_code")Long ord_code) throws Exception{
	ResponseEntity<List<OrderDetailInfoVO>> entity = null;
	
	List<OrderDetailInfoVO> OrderDetailList  = adOrderService.orderDetailInfo1(ord_code);
	
//	브라우저에서 상품이미지 출력시 역슬래시 사용이 문제가 되므로 슬래시 변환 작업후 클라이언트로 전송
	OrderDetailList.forEach(vo -> {
		vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
	});
	
	entity = new ResponseEntity<List<OrderDetailInfoVO>>(OrderDetailList, HttpStatus.OK);
			
	return entity;
}
	
//	주문상세 내역에서 개별상품 삭제
	@GetMapping("/order_product_delete")
	public String order_product_delete(Criteria cri, Long ord_code, Integer pro_num) throws Exception {
		
		adOrderService.order_product_delete(ord_code, pro_num);
		
		return "redirect:/admin/order/order_list" + cri.getListLink();
	}
	
//	주문상세 2
	@GetMapping("/order_detail_info2/{ord_code}")
	public String order_detail_list2(@PathVariable("ord_code") Long ord_code,Model model) throws Exception{
		
		List<OrderDetailProductVO> orderProductList = adOrderService.orderDetailInfo2(ord_code);
		
//		orderProductList.forEach(vo -> {
//			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
//		});
		
		orderProductList.forEach(vo->{
			vo.getProductVO().setPro_up_folder(vo.getProductVO().getPro_up_folder().replace("\\", "/"));
		});
		
		model.addAttribute("orderProductList", orderProductList);
		
		return "/admin/order/order_detail_product";
		
	}
	
	
	@ResponseBody
	@GetMapping("/imageDisplay")  //  매핑주소: "/admin/product/imageDisplay?Name=값&Name=값"
	public ResponseEntity<byte[]> imageDisplay(String dateFolderName, String fileName) 
			throws Exception {

		return FileUtils.getFile(uploadPath + dateFolderName, fileName);
	}
	
	
}
