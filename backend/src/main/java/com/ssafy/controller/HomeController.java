package com.ssafy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * https://gmlwjd9405.github.io/2019/01/04/logging-with-slf4j.html : 로깅 처리
 * */

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	// post랑 다 추가함
	@RequestMapping(value = {"/articles/portfolios/**","/articles/posts/**"})
	public String redirect() {
		return "forward:/";
	}
	
	@RequestMapping(value = "/callback")
	public String callback() {
		return "forward:/";
	}

}
