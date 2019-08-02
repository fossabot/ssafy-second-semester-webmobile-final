package com.ssafy.vo.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.ssafy.api.PostRestController;
import com.ssafy.vo.Post;

public class PostResource extends Resource<Post> {

	public PostResource(Post post, Link... links) {
		super(post, links);
		add(linkTo(PostRestController.class).slash(post.getPostId()).withSelfRel());
	}

}
