package com.ssafy.vo.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.ssafy.api.PortfolioCommentRestController;
import com.ssafy.vo.PortfolioComment;

public class PortfolioCommentResource extends Resource<PortfolioComment> {
	public PortfolioCommentResource(PortfolioComment portfolioComment, Link... links) {
		super(portfolioComment, links);
		add(linkTo(PortfolioCommentRestController.class).slash(portfolioComment.getPortfolioCommentId()).withSelfRel());
	}
}
