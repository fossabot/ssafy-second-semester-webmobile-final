package com.ssafy.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.respository.PortfoliosCommentsRepository;
import com.ssafy.vo.PortfolioComments;
import com.ssafy.vo.resource.PortfolioCommentsResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/portfolios/{portfolioId}/comments")
public class PortfolioCommentsRestController {

	@Autowired
	PortfoliosCommentsRepository portfolioCommentsRepository;

	@GetMapping
	public ResponseEntity<?> findAll(@PathVariable int portfolioId) {
		List<PortfolioComments> portfolios = portfolioCommentsRepository.findByPortfolioId(portfolioId);
		List<PortfolioCommentsResource> portfolioCommentsResources = new ArrayList<>();

		for (int i = 0; i < portfolios.size(); i++) {
			portfolioCommentsResources.add(new PortfolioCommentsResource(portfolios.get(i)));
		}

		// HATEOAS
		ControllerLinkBuilder selfLinkBuilder = linkTo(PortfoliosRestController.class); // http://localhost:9090/api/bears/portfolios

		// PortfoliosResource portfoliosResource = new PortfoliosResource(portfolios);
		// portfoliosResource.add(selfLinkBuilder.slash(portfolios.getPortfolioId()).withRel("update"));

		Resources<PortfolioCommentsResource> rss = new Resources<>(portfolioCommentsResources);
		rss.add(selfLinkBuilder.withSelfRel().withType("GET"));
		// PagedResources<Portfolios> ps = new PagedResources<>(portfoliosOpt);

		return ResponseEntity.ok(rss);
	}

}
