package com.docmall.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.domain.ProductVO;
import com.docmall.dto.Criteria;
import com.docmall.dto.PageDTO;
import com.docmall.service.UserProductService;
import com.docmall.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user/product")
public class UserProductController {
	private final UserProductService userProductService;
	
	@Resource(name = "uploadPath") // servlet-context.xml에서 bean이름 참조
	private String uploadPath;
	
//	매핑주소 스타일
//	1)/user/product/pro_list?cg_code=2차 카테고리 코드
/*
	@GetMapping("/pro_list")
	public void pro_list(@RequestParam("cg_code") Integer cg_code)throws Exception{
		
	}
*/	
//	2)/user/product/pro_list/
	@GetMapping("/pro_list")
	public 	String pro_list(
			Criteria cri,
			@ModelAttribute("cg_code") Integer cg_code,
			@ModelAttribute("cg_name") String cg_name,
			Model model)
			throws Exception
	{
		
		cri.setAmount(8);
		List<ProductVO> pro_list = userProductService.pro_list(cg_code, cri);
		pro_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});

		model.addAttribute("pro_list", pro_list);

		int totalcount = userProductService.getTotalCount(cg_code);
		model.addAttribute("pageMaker", new PageDTO(cri, totalcount));
		
		return "/user/product/pro_list";
		
	}
	
	@ResponseBody
	@GetMapping("/imageDisplay")  //  매핑주소: "/user/product/imageDisplay?dateFolderName= fileName="
	public ResponseEntity<byte[]> imageDisplay(String dateFolderName, String fileName) 
		throws Exception {

		return FileUtils.getFile(uploadPath + dateFolderName, fileName);
	}

//	상품상세, 상품후기(하단에 위치)
//	Criteria: 기존 목록 페이지로 돌아가기 위해서
	@GetMapping("/pro_detail")
	public void pro_detail (Criteria cri, Integer pro_num, Model model)throws Exception {
		
		log.info("페이징정보: " + cri);
		log.info("상품코드: " + pro_num);
		
		ProductVO productVO = userProductService.pro_detail(pro_num);
		//클라이언트에서 이미지 출력작업.   \ 역슬래시가 서버로 보낼때 문제가 되어서, / 슬래시 사용하고자 
		productVO.setPro_up_folder(productVO.getPro_up_folder().replace("\\", "/"));
		
		model.addAttribute("productVO", productVO);
		
	}
	
}
