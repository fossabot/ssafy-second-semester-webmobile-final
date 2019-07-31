package com.ssafy.vo;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.ssafy.api.PortfolioImagesRestController;

public class PortfolioImagesResource extends Resource<PortfolioImages> {

	public PortfolioImagesResource(PortfolioImages portfolioImages, Link... links) {
		super(portfolioImages, links);
		add(linkTo(PortfolioImagesRestController.class).slash(portfolioImages.getPortfolio_image_id()).withSelfRel());
	}

}
