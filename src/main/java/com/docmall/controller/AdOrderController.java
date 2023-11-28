package com.docmall.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.domain.OrderDetailVO;
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
	
	@ResponseBody
	@GetMapping("/imageDisplay")  //  매핑주소: "/admin/product/imageDisplay"
	public ResponseEntity<byte[]> imageDisplay(String dateFolderName, String fileName) 
			throws Exception {

		return FileUtils.getFile(uploadPath + dateFolderName, fileName);
	}
	
	@GetMapping("/order_detail_info/{ord_code}")
		public ResponseEntity<List<OrderDetailVO>> list(@PathVariable("pro_num")Integer pro_num, @PathVariable("page") Integer page) throws Exception{
		ResponseEntity<List<OrderDetailVO>> entity = null;
		return entity;
	}
}
