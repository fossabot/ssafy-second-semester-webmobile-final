package com.ssafy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.service.PortfolioImagesService;

@CrossOrigin
@Controller
@RequestMapping(value = "/portfolios/{portfolioId}/images", produces = "application/hal+json")
public class PortfolioImagesRestController {

	@Autowired
	PortfolioImagesService portfolioImagesService;
	
	
	
}
