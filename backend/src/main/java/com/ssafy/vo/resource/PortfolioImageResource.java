package com.ssafy.vo.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.ssafy.api.PortfolioImageRestController;
import com.ssafy.vo.PortfolioImage;

public class PortfolioImageResource extends Resource<PortfolioImage> {

	public PortfolioImageResource(PortfolioImage portfolioImage, Link... links) {
		super(portfolioImage, links);
		add(linkTo(PortfolioImageRestController.class).slash(portfolioImage.getPortfolioImageId()).withSelfRel());
	}

}
