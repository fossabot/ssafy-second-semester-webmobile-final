package com.ssafy.vo;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.ssafy.api.PortfolioCommentsRestController;

public class PortfolioCommentsResource extends Resource<PortfolioComments> {
	public PortfolioCommentsResource(PortfolioComments portfolioComments, Link... links) {
		super(portfolioComments, links);
		add(linkTo(PortfolioCommentsRestController.class).slash(portfolioComments.getPortfolio_comment_id()).withSelfRel());
	}
}
