package com.ssafy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.PortfolioCommentsService;
import com.ssafy.vo.resource.PortfolioCommentsResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/portfolios/{portfolioId}/comments", produces = "application/hal+json")
public class PortfolioCommentsRestController {
	
	@Autowired
	PortfolioCommentsService portfolioCommentsService;
	
	@GetMapping
	public ResponseEntity<PortfolioCommentsResource> find(@PathVariable int portfolioId) {
		//20개
		
		
		return ResponseEntity.noContent().build();
	}
	

}
