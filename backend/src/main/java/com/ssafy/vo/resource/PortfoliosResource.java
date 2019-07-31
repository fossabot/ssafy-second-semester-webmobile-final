package com.ssafy.vo.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.ssafy.api.PortfoliosRestController;
import com.ssafy.vo.Portfolios;


public class PortfoliosResource extends Resource<Portfolios> {

	public PortfoliosResource(Portfolios portfolios, Link... links) {
		super(portfolios, links);
		add(linkTo(PortfoliosRestController.class).slash(portfolios.getPortfolioId()).withSelfRel());
	}
	
}
