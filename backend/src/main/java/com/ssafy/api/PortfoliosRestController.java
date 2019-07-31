package com.ssafy.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ssafy.respository.PortfoliosRepository;
import com.ssafy.service.PortfoliosService;
import com.ssafy.vo.Portfolios;
import com.ssafy.vo.resource.PortfoliosResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/portfolios")
public class PortfoliosRestController {
	
	@Autowired
	PortfoliosRepository portfoliosRepository;
	
	@Autowired
	PortfoliosService portfoliosService;
	
	@GetMapping
	public ResponseEntity<Resources<PortfoliosResource>> findAll() {
		List<PortfoliosResource> portfolios = portfoliosRepository.findAll().stream().map(PortfoliosResource::new).collect(Collectors.toList());
		Resources<PortfoliosResource> resources = new Resources<>(portfolios);
		//HATEOAS
		//ControllerLinkBuilder selfLinkBuilder = linkTo(PortfoliosRestController.class); // http://localhost:9090/api/bears/portfolios
		String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
		//resources.add(linkTo(selfLinkBuilder).withSelfRel());
		resources.add(new Link(uriString, "self"));
		return ResponseEntity.ok(resources);
		
	}
	
	@GetMapping(value = "/page/{pageNo}")
	public ResponseEntity<PagedResources<PortfoliosResource>> findAllPortfolios(@PathVariable int pageNo, PagedResourcesAssembler<Portfolios> assembler) {
//		Pageable pageable = PageRequest.of(page_no - 1, 6, Sort.by("portfolio_created_at"));
		Pageable pageable = PageRequest.of(pageNo - 1, 6);
		Page<Portfolios> portfolios = portfoliosService.findAllPortfolios(pageable);
		PagedResources<PortfoliosResource> pagedPortfoliosResources = assembler.toResource(portfolios, e -> new PortfoliosResource(e));
		
		return ResponseEntity.ok(pagedPortfoliosResources);
	}
	
	@GetMapping("/{portfolio_id}")
	public ResponseEntity<?> findAll(@PathVariable int portfolioId) {
		
		Optional<Portfolios> portfolioOpt = portfoliosRepository.findById(portfolioId);
		
		Portfolios portfolios = portfolioOpt.get();

		//HATEOAS
		ControllerLinkBuilder selfLinkBuilder = linkTo(PortfoliosRestController.class); // http://localhost:9090/api/bears/portfolios
		PortfoliosResource portfoliosResource = new PortfoliosResource(portfolios);
		//portfoliosResource.add(selfLinkBuilder.withRel("update").withType("PUT"));
		
		
		return ResponseEntity.ok(portfoliosResource);
	}
	
	

}
