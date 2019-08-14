package com.ssafy.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ssafy.auth.Auth;
import com.ssafy.common.RoleType;
import com.ssafy.exception.DataCreateException;
import com.ssafy.exception.DataNotFoundException;
import com.ssafy.exception.NoAuthenticationException;
import com.ssafy.exception.ParameterException;
import com.ssafy.service.PortfolioService;
import com.ssafy.vo.Portfolio;
import com.ssafy.vo.resource.PortfolioResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/portfolios", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class PortfolioRestController {

	@Autowired
	PortfolioService portfolioService;

	@GetMapping(value = "/pages/{pageNo}")
	public ResponseEntity<PagedResources<PortfolioResource>> findAllPortfolios(
			@PathVariable final int pageNo,
			PagedResourcesAssembler<Portfolio> assembler) {
		
		Pageable pageable = PageRequest.of(pageNo - 1, 6, new Sort(Direction.DESC, "portfolioCreatedAt"));
		Page<Portfolio> portfolios = portfolioService.findAllPortfolios(pageable);

		PagedResources<PortfolioResource> pagedPortfoliosResources = assembler
				.toResource(portfolios,	e -> new PortfolioResource(e));
		return ResponseEntity.ok(pagedPortfoliosResources);
	}
	
	@GetMapping(value = "/best")
	public ResponseEntity<Resources<PortfolioResource>> bestPortfolios() {
		List<Portfolio> allPortfolios = portfolioService.findAll();
		Collections.sort(allPortfolios);
		allPortfolios = allPortfolios.subList(0, 3);
		
		List<PortfolioResource> portfolios = allPortfolios.stream().map(PortfolioResource::new)
				.collect(Collectors.toList());
		Resources<PortfolioResource> resources = new Resources<>(portfolios);
		// HATEOAS
		String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
		resources.add(new Link(uriString, "self"));
		return ResponseEntity.ok(resources);

	}

	@GetMapping("/{portfolioId}")
	public ResponseEntity<PortfolioResource> findPortfolioByPortfolioId(
			@PathVariable final long portfolioId) throws Exception {
		
		Optional<Portfolio> portfolioOpt = portfolioService.findPortfolioByPortfolioId(portfolioId);
		if (!portfolioOpt.isPresent()) {
			throw new DataNotFoundException(portfolioId);
		}

		Portfolio portfolios = portfolioOpt.get();
		PortfolioResource portfoliosResource = new PortfolioResource(portfolios);
		return ResponseEntity.ok(portfoliosResource);
	}

	@Auth(minimum = RoleType.MEMBER)
	@PostMapping(value = "")
	public ResponseEntity<PortfolioResource> createPortfolio(
			@RequestHeader(value = "accountEmail") final String accountEmail,
			@RequestHeader(value = "accountAuth") final int accountAuth,
			@Valid @RequestBody final Portfolio portfolio) throws Exception {
		
		portfolio.setPortfolioId(0);
		
		if (accountAuth > RoleType.SUPERVISOR.getRoleType()) {
			if (!portfolio.getAccountEmail().equals(accountEmail)) {
				throw new NoAuthenticationException(accountEmail);
			}
		}
		
		Portfolio createdPortfolio = portfolioService.savePortfolio(portfolio);
		if (createdPortfolio == null) {
			throw new DataCreateException(portfolio);
		}
		
		ControllerLinkBuilder selfLinkBuilder = linkTo(PortfolioRestController.class)
				.slash(portfolio.getPortfolioId());
		URI createdUri = selfLinkBuilder.toUri();

		PortfolioResource portfolioResource = new PortfolioResource(createdPortfolio);
		portfolioResource.add(selfLinkBuilder.withRel("update"));
		portfolioResource.add(selfLinkBuilder.withRel("delete"));
		return ResponseEntity.created(createdUri).body(portfolioResource);
	}

	@Auth(minimum = RoleType.MEMBER)
	@PutMapping(value = "/{portfolioId}")
	public ResponseEntity<PortfolioResource> updatePortfolio(
			@RequestHeader(value = "accountEmail") final String accountEmail,
			@RequestHeader(value = "accountAuth") final int accountAuth,
			@PathVariable final long portfolioId,
			@Valid @RequestBody final Portfolio portfolio) throws Exception {
		
		if (accountAuth > RoleType.SUPERVISOR.getRoleType()) { // 관리자가 아니라면,
			if (!portfolio.getAccountEmail().equals(accountEmail)) {
				throw new NoAuthenticationException(accountEmail);
			}
		}
		
		if (portfolioId != portfolio.getPortfolioId()) {
			throw new ParameterException(portfolioId, portfolio.getPortfolioId()); 
		}

		Optional<Portfolio> optional = portfolioService
				.findPortfolioByPortfolioId(portfolioId);
		if (!optional.isPresent()) {
			throw new DataNotFoundException(portfolioId);
		}

		Portfolio updatedPortfolio = portfolioService.savePortfolio(portfolio);
		if (updatedPortfolio == null) {
			throw new DataCreateException(portfolio); 
		}
		
		ControllerLinkBuilder selfLinkBuilder = linkTo(PortfolioRestController.class)
				.slash(portfolio.getPortfolioId());

		PortfolioResource portfolioResource = new PortfolioResource(updatedPortfolio);
		portfolioResource.add(selfLinkBuilder.withRel("delete"));
		return ResponseEntity.ok(portfolioResource);
	}

	@Auth(minimum = RoleType.MEMBER)
	@DeleteMapping(value = "/{portfolioId}")
	public ResponseEntity<?> deletePortfolioByPortfolioId(
			@RequestHeader(value = "accountEmail") final String accountEmail,
			@RequestHeader(value = "accountAuth") final int accountAuth,
			@PathVariable final long portfolioId) throws Exception {
	
		Optional<Portfolio> portfolioOpt = portfolioService.findPortfolioByPortfolioId(portfolioId);
		if(!portfolioOpt.isPresent()) {
			throw new DataNotFoundException(portfolioId);
		}
		if (accountAuth > RoleType.SUPERVISOR.getRoleType()) {
			if(!portfolioOpt.get().getAccountEmail().equals(accountEmail)) {
				throw new NoAuthenticationException(accountEmail); // 권한없음 Exception
			}
		}
		
		portfolioService.deletePortfolioByPortfolioId(portfolioId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<String, Long>> countPortfolios() {
		
		HashMap<String, Long> countPortfoliosMap = new HashMap<>();
		long countPortfolios = portfolioService.countPortfolios();
		countPortfoliosMap.put("countPortfolios", countPortfolios);
		return ResponseEntity.ok(countPortfoliosMap);
	}

}
