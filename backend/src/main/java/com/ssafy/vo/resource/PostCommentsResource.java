package com.ssafy.vo.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.ssafy.api.PortfolioCommentsRestController;
import com.ssafy.vo.PostComments;

public class PostCommentsResource extends Resource<PostComments> {
	public PostCommentsResource(PostComments postComments, Link... links) {
		super(postComments, links);
		add(linkTo(PortfolioCommentsRestController.class).slash(postComments.getPostCommentId()).withSelfRel());
	}
}
