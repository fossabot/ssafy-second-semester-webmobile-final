package com.ssafy.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ssafy.respository.PortfoliosRespository;
import com.ssafy.vo.Portfolios;
import com.ssafy.vo.PortfoliosResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/portfolios")
public class PortfoliosRestController {
	
	@Autowired
	PortfoliosRespository portfoliosRespository;
;
	@GetMapping
	public ResponseEntity<Resources<PortfoliosResource>> findAll() {
		List<PortfoliosResource> portfolios = portfoliosRespository.findAll().stream().map(PortfoliosResource::new).collect(Collectors.toList());

		Resources<PortfoliosResource> resources = new Resources<>(portfolios);
		
		//HATEOAS
		//ControllerLinkBuilder selfLinkBuilder = linkTo(PortfoliosRestController.class); // http://localhost:9090/api/bears/portfolios
		String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
		resources.add(linkTo(uriString).withSelfRel());
		return ResponseEntity.ok(resources);
	}
	
	@GetMapping("/{portfolio_id}")
	public ResponseEntity<?> findAll(@PathVariable int portfolio_id) {
		
		Optional<Portfolios> portfolioOpt = portfoliosRespository.findById(portfolio_id);
		
		Portfolios portfolios = portfolioOpt.get();

		//HATEOAS
		ControllerLinkBuilder selfLinkBuilder = linkTo(PortfoliosRestController.class); // http://localhost:9090/api/bears/portfolios
		PortfoliosResource portfoliosResource = new PortfoliosResource(portfolios);
		//portfoliosResource.add(selfLinkBuilder.withRel("update").withType("PUT"));
		
		
		return ResponseEntity.ok(portfoliosResource);
	}
	

}
