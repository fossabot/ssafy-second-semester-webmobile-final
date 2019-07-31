package com.ssafy.vo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.ssafy.api.PortfoliosRestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


public class PortfoliosResource extends Resource<Portfolios> {

	public PortfoliosResource(Portfolios portfolios, Link... links) {
		super(portfolios, links);
		add(linkTo(PortfoliosRestController.class).slash(portfolios.getPortfolioId()).withSelfRel());
	}
	
}
