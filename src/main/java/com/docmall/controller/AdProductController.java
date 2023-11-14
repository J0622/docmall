package com.docmall.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.CategoryVO;
import com.docmall.domain.ProductVO;
import com.docmall.dto.Criteria;
import com.docmall.dto.PageDTO;
import com.docmall.service.AdCategoryService;
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
	private final AdCategoryService adCategoryService;
	

//	메인 및 썸네일 업로드 폴더 경로 주입작업
	@Resource(name = "uploadPath") // servlet-context.xml에서 bean이름 참조
	private String uploadPath;

//	CKEditor 업로드 폴더 경로 주입작업
	@Resource(name = "uploadCKPath") // servlet-context.xml에서 bean이름 참조
	private String uploadCKPath;

//	상품 등록 폼
	@GetMapping("/pro_insert")
	public void pro_insert() {

		log.info("상품 등록 폼");
	}

//	상품정보 저장
//	파일 업로드 기능 사용방법
	/*
	 * 1)스프링에서 내장된 기본 라이브러리 이용 2)외부에서 라이브러리(commons-fileupload)를 받아와서 사용 공통작업) 파일업로드
	 * 라이브러리. servlet-context.xml 에서 bean등록작업
	 */
//	MultipartFile uploadFile 여기서 uploadFile이 이름은 pro_insert.jsp파일의 
//	이미지 미리보기 태그의 name과 일치 해야한다.
	@PostMapping("/pro_insert")
	public String pro_insert(ProductVO vo, MultipartFile uploadFile, RedirectAttributes rttr) {

		log.info("상품정보: " + vo);
//		파일 업로드
		String dateFolder = FileUtils.getDateFolder();
		String savedFileName = FileUtils.uploadFile(uploadPath, dateFolder, uploadFile);

		vo.setPro_img(savedFileName);
		vo.setPro_up_folder(dateFolder);

//		상품 정보 저장
		adProductService.pro_insert(vo);

		return "redirect:/admin/product/pro_list"; // 상품리스트 주소로 이동
	}

//	CkEditor에서 업로드 탭에서 파일 업로드시 동작하는 매핑주소
//	MultipartFile: 업로드 된 파일을 참조하는 객체
//	HttpServletRequest: jsp의 request객체 클래스 클라이언트의 요청을 담고 있음
//	HttpServletResponse: jsp의 response객체 클래스 클라이언트로 보낼 서버측의 응답정보를 가지고 있는 객체
	@PostMapping("/imageUpload")
	public void imageUpload(HttpServletRequest req, HttpServletResponse response, MultipartFile upload) {

//		전역변수로 한 이유: try/catch문에서 사용하기 위해서 (그냥 냅다쓰면 안됨)
		OutputStream out = null;
		PrintWriter printWriter = null; // 클라이언트에게 서버의 응답정보를 보낼때 사용

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset = utf-8");
//		
		try {
//			1) 파일 업로드 작업
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes(); // 업로드한 파일을 byte배열로 읽어들임

			String ckUploadPath = uploadCKPath + fileName;

			log.info("ckEditor 파일경로: " + ckUploadPath);

			out = new FileOutputStream(new File(ckUploadPath)); // 0kb의 파일 생성

			out.write(bytes); // 출력스트림 작업
			out.flush();

//			2) 파일정보를 CKEditor로 보내는 작업
			printWriter = response.getWriter();

//			브라우저의 CKEditor에서 사용할 업로드 한 파일정보를 참조하는 경로설정
//			1)톰캣 Context Path에서 Add External Web Module 작업을 해야 함.
//			Path : /upload, Document Base : C:\\dev\\upload\\ckeditor 설정
//			2)Tomcat server.xml에서 <Context docBase="C:\\dev\\devTools\\upload\\ckeditor"
//			path="/ckupload" reloadable="true" />

			String fileUrl = "/ckupload/" + fileName;
			// {"filename":"abc.gif", "uploaded":1, "url":"/upload/abc.gif"}
			printWriter.println("{\"filename\":\"" + fileName + "\", \"uploaded\":1,\"url\":\"" + fileUrl + "\"}");
			printWriter.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (printWriter != null)
				printWriter.close();

		}
//		
	}

//	상품 리스트 (목록 및 페이징작업)
	@GetMapping("/pro_list")
	public void pro_list(Criteria cri, Model model) throws Exception {
//		dto를 직접 변경하지 않고 해당 메소드에서 작업하는 방법
		cri.setAmount(2);
		
		
		
		List<ProductVO> pro_list = adProductService.pro_list(cri);
//		날짜 폴더의 역슬래시를 슬래시로 바꾸는 작업
//		이유: 역슬래시로 되어있는 정보가 스프리으으로 보내는 요청데이터에 사용되면 에러발생
//		스프링에서 처리를 안할경우 자바스크립스트에서도 처리 가능
		pro_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});

//		"pro_list" = jsp참조
		model.addAttribute("pro_list", pro_list);

		int totalcount = adProductService.getTotalCount(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, totalcount));

	}
	

//	상품 리스트에서 보여줄 이미지
//	ajax 작업 진행시  @ResponseBody 어노테이션 사용
//	"/imageDisplay" 이 주소를 통해서 날짜경로와 이미지이름을 각각 보낼것임
	@ResponseBody
	@GetMapping("/imageDisplay")  //  매핑주소: "/admin/product/imageDisplay"
	public ResponseEntity<byte[]> imageDisplay(String dateFolderName, String fileName) 
			throws Exception {

		return FileUtils.getFile(uploadPath + dateFolderName, fileName);
	}

//	방법이 두 가지 있음
//	체크상품 목록 수정(ajax)
//	ajax에서 @RequestParam 요청시  배열형태의 파라미터일 경우 pro_num_arr[] 반드시 이 형태로 작성해야 한다.
//	단, 일반상태에서 요청시 []를 제외하고 작성해야 한다.
	@ResponseBody
	@PostMapping("/pro_checked_modify1")
	public ResponseEntity<String> pro_checked_modify1(
			@RequestParam("pro_num_arr[]")List<Integer> pro_num_arr,
			@RequestParam("pro_price_arr[]")List<Integer> pro_price_arr,
			@RequestParam("pro_buy_arr[]")List<String> pro_buy_arr
			) throws Exception{
		
		log.info("상품코드" + pro_num_arr);
		log.info("상품코드" + pro_price_arr);
		log.info("상품코드" + pro_buy_arr);
		
		ResponseEntity<String> entity = null;
		
//		체크상품 수정작업
		adProductService.pro_checked_modify1(
				pro_num_arr, 
				pro_price_arr, 
				pro_buy_arr
				);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	@ResponseBody
	@PostMapping("/pro_checked_modify2")
	public ResponseEntity<String> pro_checked_modify2(
			@RequestParam("pro_num_arr[]")List<Integer> pro_num_arr,
			@RequestParam("pro_price_arr[]")List<Integer> pro_price_arr,
			@RequestParam("pro_buy_arr[]")List<String> pro_buy_arr
			) throws Exception{
		
		log.info("상품코드" + pro_num_arr);
		log.info("상품코드" + pro_price_arr);
		log.info("상품코드" + pro_buy_arr);
		
		ResponseEntity<String> entity = null;
		
//		체크상품 수정작업
		adProductService.pro_checked_modify2(
				pro_num_arr, 
				pro_price_arr, 
				pro_buy_arr
				);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	
	@GetMapping("/pro_edit")
	public void pro_edit(@ModelAttribute("cri")
	Criteria cri, Integer pro_num, Model model) throws Exception{
		ProductVO productVO = adProductService.pro_edit(pro_num);
		productVO.setPro_up_folder(productVO.getPro_up_folder().replace("\\", "/"));
		model.addAttribute("productVO",productVO);
		
//		선택한 상품정보
		CategoryVO firstCategory = adCategoryService.get(productVO.getCg_code());
		model.addAttribute("fist_category",firstCategory);
		
//		1차 카테고리
//		상품카테고리에서 2차 카테고리를 이용한 1차 카테고리 정보를 참조
		model.addAttribute("second_categoryList",adCategoryService.getSecondCategoryList(firstCategory.getCg_parent_code()));
	}
	@PostMapping("pro_edit")
	public String pro_edit (Criteria cri,ProductVO vo, MultipartFile uploadFile, RedirectAttributes rttr)throws Exception {
//		상품리스트에서 사용할 정보(검색,페이징정보)
		log.info("cri" + cri);
//		상품수정내용
		log.info("vo" + vo);
		
		vo.setPro_up_folder(vo.getPro_up_folder().replace("/", "\\"));
		
//		파일이 변경될 경우 해야 할 작업 1)기존 이미지 파일 삭제, 2)업로드 작업
//		참고>클라이언트 파일명을 DB에 저장하는 부분
//		if(uploadFile.getSize() > 0)으로도 첨부파일을 확인할 수 있다.
		if(!uploadFile.isEmpty()) {
//			기존 이미지 삭제 
			FileUtils.deleteFile(uploadPath, vo.getPro_up_folder(), vo.getPro_img());
//			업로드 작업 
			String dateFolder = FileUtils.getDateFolder();
			String savedFileName = FileUtils.uploadFile(uploadPath, dateFolder, uploadFile);
//			DB에 저장할 새로운 날짜 폴더명 및 이미지명 갱신
			vo.setPro_img(savedFileName);
			vo.setPro_up_folder(dateFolder);

		}
		
//		DB연동 작업
		
		adProductService.pro_edit(vo);
		
		
		return "redirect:/admin/product/pro_list" + cri.getListLink();
	}
	
	@PostMapping("/pro_delete")
	public String pro_delete(Criteria cri, Integer pro_num)
			throws Exception {
		
		adProductService.pro_delete(pro_num);
		
		return "redirect:/admin/product/pro_list" + cri.getListLink();
	}
	
}	
