package com.ssafy.vo.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.ssafy.api.PortfolioRestController;
import com.ssafy.vo.Portfolio;


public class PortfolioResource extends Resource<Portfolio> {

	public PortfolioResource(Portfolio portfolio, Link... links) {
		super(portfolio, links);
		add(linkTo(PortfolioRestController.class).slash(portfolio.getPortfolioId()).withSelfRel());
	}
	
}
