package com.docmall.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.ProductVO;
import com.docmall.service.AdProductService;
import com.docmall.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/admin/product/*")
@Controller
@RequiredArgsConstructor
public class AdProductController {
	private final AdProductService adProductService;
	
//	업로드 폴더 경로 주입작업
	@Resource(name = "uploadPath") // servlet-context.xml에서 bean이름 참조
	private String uploadPath;
	
//	상품 등록 폼
	@GetMapping("/pro_insert")
	public void pro_insert() {
		
		log.info("상품 등록 폼");
	}
	
//	상품정보 저장
//	파일 업로드 기능 사용방법
	/* 
	 1)스프링에서 내장된 기본 라이브러리 이용 
	 2)외부에서 라이브러리(commons-fileupload)를 받아와서 사용  
	  공통작업) 파일업로드 라이브러리. servlet-context.xml 에서 bean등록작업 
	 */
//	MultipartFile uploadFile 여기서 uploadFile이 이름은 pro_insert.jsp파일의 
//	이미지 미리보기 태그의 name과 일치 해야한다.
	@PostMapping("/pro_insert")
	public String pro_insert(ProductVO vo, MultipartFile uploadFile , RedirectAttributes rttr) {
		
		log.info("상품정보: " + vo);
//		파일 업로드
		String dateFolder = FileUtils.getDateFolder();
		String savedFileName = FileUtils.uploadFile(uploadPath, dateFolder, uploadFile);
		
		vo.setPro_img(savedFileName);
		vo.setPro_up_folder(dateFolder);
		
//		상품 정보 저장
		adProductService.pro_insert(vo);
		
		return "redirect:/리스트";
	}
	
}
