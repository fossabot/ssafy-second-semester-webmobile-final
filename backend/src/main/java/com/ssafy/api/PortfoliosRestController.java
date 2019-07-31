package com.ssafy.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
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
		System.out.println("hello1");
		List<PortfoliosResource> portfolios = portfoliosRespository.findAll().stream().map(PortfoliosResource::new).collect(Collectors.toList());
		System.out.println("hello2");
		Resources<PortfoliosResource> resources = new Resources<>(portfolios);
		System.out.println("hello3");
		//HATEOAS
		//ControllerLinkBuilder selfLinkBuilder = linkTo(PortfoliosRestController.class); // http://localhost:9090/api/bears/portfolios
		String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
		System.out.println("hello4");
		//resources.add(linkTo(selfLinkBuilder).withSelfRel());
		resources.add(new Link(uriString, "self"));
		System.out.println("hello5");
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
