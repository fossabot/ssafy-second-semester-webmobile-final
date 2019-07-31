package com.ssafy.vo.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.ssafy.api.PortfolioCommentsRestController;
import com.ssafy.vo.PortfolioComments;

public class PortfolioCommentsResource extends Resource<PortfolioComments> {
	public PortfolioCommentsResource(PortfolioComments portfolioComments, Link... links) {
		super(portfolioComments, links);
		add(linkTo(PortfolioCommentsRestController.class).slash(portfolioComments.getPortfolioCommentId()).withSelfRel());
	}
}
