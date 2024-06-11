package com.example.demo.controller;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

//Bean : 스프링이 알아서 만들고 관리하는 것


@Slf4j //Simple logging facade for java  > System out println 과 비슷한 종류
// System 출력에 비해 logging 사용이 메모리 부담이 적다.
// log를 이용한 메세지 출력시 자주 사용 (실무에서 자주 사용)
@RequestMapping("param") //공통적으로 모든 주소 앞에 param이 기본으로 붙는다.
@Controller //Bean에 등록하고, 응답/요청/제어 역할을 수행할 수 있도록한다.
public class ParameterController {
	
	@GetMapping("main") //param/main 주소로 GET 방식 요청 만듬
	public String paramMain() {
		return "param/main";
		//return 폴더/파일명
		// templates/param/main.html을 참조하게 된다.
	}
	
	@PostMapping("test1")
	public String paramTest1(HttpServletRequest req) {
		String inputName = req.getParameter("inputName");
		int inputAge = Integer.parseInt(req.getParameter("inputAge"));
		String inputAddress = req.getParameter("inputAddress");
		/* Spring 에서 redirect:[요청주소] 하면 되돌아가진다. */
		System.out.println("이름 : "+inputName);
		System.out.println("나이 : "+inputAge);
		System.out.println("주소 : "+inputAddress);//메모리 부담이 크다.
		
		log.debug("이름 : "+inputName);
		log.debug("나이 : "+inputAge);
		log.debug("주소 : "+inputAddress); //메모리 부담이 적어진다. 값이 잘못 될 때 추적할 수 있다.
		
		return "redirect:/param/main";
	}
	/*
	  2. RequestParam - 독립된 개별 파라미터 얻어오기
	  - HttpServletRequest 객체를 활용한 매개변수 전달 어노테이션
	  - 매개 변수 앞 해당 어노테이션 작성 시 매개변수에 값이 작성된다.
	  - 작성되는 데이터는 매개변수 자료형에 맞게 형변환이 자동으로 수행됨.
	  @RequestParam(value= = "", required="false",defaultValue="1");
	  
	 value 		> 전달 받은 input 태그의 name 속성 값
	 required 	> 넣어준 name 속성값 태그에게 입력 을 필수로 강제하게 함 (기본값 true)
	 			> required = true 인 파라미터가 존재하지 않거나 null인 경우 400 Bad Request 에러
	 defaultValue > 파라미터 중 일치하는 name 속성 값이 없는 경우 대입할 값 지정. (required false 일 때)
	 */
	@PostMapping("test2")
	public String paramTest2(/*RequestParam의 작성 위치*/) {
		
		log.info("문제 없이 insert 가능한지 확인하기");
		
		return "redirect:/param/main";
	}
	
	
}
