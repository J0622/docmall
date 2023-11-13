package com.docmall.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String pro_list(Criteria cri, @ModelAttribute("cg_code") Integer cg_code,@ModelAttribute("cg_name") String cg_name, Model model)throws Exception{
		
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

	
	
}
