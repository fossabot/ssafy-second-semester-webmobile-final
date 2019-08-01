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

import com.ssafy.respository.PortfoliosRepository;
import com.ssafy.service.PortfoliosService;
import com.ssafy.vo.Portfolios;
import com.ssafy.vo.resource.PortfoliosResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/portfolios", produces = "application/hal+json")
public class PortfoliosRestController {

	@Autowired
	PortfoliosRepository portfoliosRepository;

	@Autowired
	PortfoliosService portfoliosService;

	@GetMapping
	public ResponseEntity<Resources<PortfoliosResource>> findAll() {
		List<PortfoliosResource> portfolios = portfoliosRepository.findAll().stream().map(PortfoliosResource::new)
				.collect(Collectors.toList());
		Resources<PortfoliosResource> resources = new Resources<>(portfolios);
		// HATEOAS
		String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
		resources.add(new Link(uriString, "self"));
		return ResponseEntity.ok(resources);

	}

	@GetMapping(value = "/page/{pageNo}")
	public ResponseEntity<PagedResources<PortfoliosResource>> findAllPortfolios(@PathVariable int pageNo,
			PagedResourcesAssembler<Portfolios> assembler) {
		Pageable pageable = PageRequest.of(pageNo - 1, 6, new Sort(Direction.DESC, "portfolioCreatedAt"));

		Page<Portfolios> portfolios = portfoliosService.findAllPortfolios(pageable);
		if (portfolios == null) {
			return ResponseEntity.badRequest().build();
		}

		PagedResources<PortfoliosResource> pagedPortfoliosResources = assembler.toResource(portfolios,
				e -> new PortfoliosResource(e));

		return ResponseEntity.ok(pagedPortfoliosResources);
	}

	@GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<String, Integer>> countPortfolios() {
		HashMap<String, Integer> countPortfolios = new HashMap<>();
		int count = portfoliosService.countPortfolios();
		countPortfolios.put("countPortfolios", count);
		return ResponseEntity.ok(countPortfolios);
	}

	@GetMapping("/{portfolioId}")
	public ResponseEntity<PortfoliosResource> findAll(@PathVariable int portfolioId) {
		Optional<Portfolios> portfolioOpt = portfoliosService.findPortfolioById(portfolioId);

		if (!portfolioOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}

		Portfolios portfolios = portfolioOpt.get();

		// HATEOAS
		PortfoliosResource portfoliosResource = new PortfoliosResource(portfolios);

		return ResponseEntity.ok(portfoliosResource);
	}

	// -- 삽입
	@PostMapping
	public ResponseEntity<PortfoliosResource> createPortfolio(@RequestBody Portfolios portfolio) {
		Portfolios createdPortfolio = portfoliosService.savePortfolio(portfolio);

		if (createdPortfolio == null) {
			return ResponseEntity.badRequest().build();
		}

		PortfoliosResource portfoliosResource = new PortfoliosResource(createdPortfolio);
		return ResponseEntity.ok(portfoliosResource);
	}

	// -- 수정
	@PutMapping(value = "/{portfolioId}")
	public ResponseEntity<PortfoliosResource> updatePortfolio(@PathVariable int portfolioId,
			@RequestBody Portfolios portfolio) {
		// 수정을 요청하는 아이디와 portfolio의 아이디가 다른 경우
		if (portfolioId != portfolio.getPortfolioId()) {
			return ResponseEntity.badRequest().build();
		}

		Optional<Portfolios> optional = portfoliosService.findPortfolioById(portfolioId);
		if (!optional.isPresent()) {
			// 수정하려는 데이터가 존재하지 않음
			return ResponseEntity.badRequest().build();
		}

		Portfolios updatedPortfolio = portfoliosService.savePortfolio(portfolio);

		if (updatedPortfolio == null) {
			// 수정하려는 데이터가 존재하지만
			// 수정에 실패함
			return ResponseEntity.badRequest().build();
		}

		PortfoliosResource portfoliosResource = new PortfoliosResource(updatedPortfolio);

		// 성공시 뱉어줌
		return ResponseEntity.ok(portfoliosResource);
	}

	@DeleteMapping(value = "/{portfolioId}")
	public ResponseEntity<?> deletePortfolioById(@PathVariable int portfolioId) {
		boolean isDelected = portfoliosService.deletePortfolioById(portfolioId); // 성공하면 true

		if (isDelected) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

}
