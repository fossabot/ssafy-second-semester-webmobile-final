package com.ssafy.vo.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.ssafy.api.PostCommentRestController;
import com.ssafy.vo.PostComment;

public class PostCommentResource extends Resource<PostComment> {
	public PostCommentResource(PostComment postComment, Link... links) {
		super(postComment, links);
		add(linkTo(PostCommentRestController.class).slash(postComment.getPostCommentId()).withSelfRel());
	}
}
