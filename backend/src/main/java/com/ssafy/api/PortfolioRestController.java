package com.ssafy.api;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ssafy.auth.Auth;
import com.ssafy.common.RoleType;
import com.ssafy.respository.PortfolioRepository;
import com.ssafy.service.PortfolioService;
import com.ssafy.vo.Portfolio;
import com.ssafy.vo.resource.PortfolioResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/portfolios", produces = "application/hal+json")
public class PortfolioRestController {

	//-- 삭제 될 값
	@Autowired
	PortfolioRepository portfoliosRepository;
	
	@GetMapping
	public ResponseEntity<Resources<PortfolioResource>> findAll() {
		List<PortfolioResource> portfolios = portfoliosRepository.findAll().stream().map(PortfolioResource::new)
				.collect(Collectors.toList());
		Resources<PortfolioResource> resources = new Resources<>(portfolios);
		// HATEOAS
		String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
		resources.add(new Link(uriString, "self"));
		return ResponseEntity.ok(resources);

	}
	//--

	@Autowired
	PortfolioService portfolioService;

	@GetMapping(value = "/pages/{pageNo}")
	public ResponseEntity<PagedResources<PortfolioResource>> findAllPortfolios(
			@PathVariable final int pageNo,
			PagedResourcesAssembler<Portfolio> assembler) {
		
		Pageable pageable = PageRequest.of(pageNo - 1, 6, new Sort(Direction.DESC, "portfolioCreatedAt"));
		Page<Portfolio> portfolios = portfolioService.findAllPortfolios(pageable);
		if (portfolios == null) {
			return ResponseEntity.badRequest().build();
		}

		PagedResources<PortfolioResource> pagedPortfoliosResources = assembler
				.toResource(portfolios,	e -> new PortfolioResource(e));
		return ResponseEntity.ok(pagedPortfoliosResources);
	}

	@GetMapping("/{portfolioId}")
	public ResponseEntity<PortfolioResource> findPortfolioById(
			@PathVariable final int portfolioId) {
		
		Optional<Portfolio> portfolioOpt = portfolioService.findPortfolioByPortfolioId(portfolioId);
		if (!portfolioOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}

		Portfolio portfolios = portfolioOpt.get();
		PortfolioResource portfoliosResource = new PortfolioResource(portfolios);
		return ResponseEntity.ok(portfoliosResource);
	}

	@Auth(minimum = RoleType.MEMBER)
	@PostMapping(value = "")
	public ResponseEntity<PortfolioResource> createPortfolio(
			@RequestBody final Portfolio portfolio) {
		
		Portfolio createdPortfolio = portfolioService.savePortfolio(portfolio);
		if (createdPortfolio == null) {
			return ResponseEntity.badRequest().build();
		}

		PortfolioResource portfolioResource = new PortfolioResource(createdPortfolio);
		return ResponseEntity.ok(portfolioResource);
	}

	@Auth(minimum = RoleType.MEMBER)
	@PutMapping(value = "/{portfolioId}")
	public ResponseEntity<PortfolioResource> updatePortfolio(
			@PathVariable final int portfolioId,
			@RequestBody final Portfolio portfolio) {
		
		if (portfolioId != portfolio.getPortfolioId()) {
			return ResponseEntity.badRequest().build();
		}

		Optional<Portfolio> optional = portfolioService.findPortfolioByPortfolioId(portfolioId);
		if (!optional.isPresent()) {
			return ResponseEntity.badRequest().build();
		}

		Portfolio updatedPortfolio = portfolioService.savePortfolio(portfolio);
		if (updatedPortfolio == null) {
			return ResponseEntity.badRequest().build();
		}

		PortfolioResource portfolioResource = new PortfolioResource(updatedPortfolio);
		return ResponseEntity.ok(portfolioResource);
	}

	@Auth(minimum = RoleType.MEMBER)
	@DeleteMapping(value = "/{portfolioId}")
	public ResponseEntity<?> deletePortfolioById(
			@PathVariable final int portfolioId) {
		
		boolean isDelected = portfolioService.deletePortfolioByPortfolioId(portfolioId);
		if (isDelected) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<String, Integer>> countPortfolios() {
		
		HashMap<String, Integer> countPortfoliosMap = new HashMap<>();
		int countPortfolios = portfolioService.countPortfolios();
		countPortfoliosMap.put("countPortfolios", countPortfolios);
		return ResponseEntity.ok(countPortfoliosMap);
	}

}
