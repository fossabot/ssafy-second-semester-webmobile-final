package com.ssafy.vo.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.ssafy.api.PostsRestController;
import com.ssafy.vo.Posts;

public class PostsResource extends Resource<Posts> {

	public PostsResource(Posts posts, Link... links) {
		super(posts, links);
		add(linkTo(PostsRestController.class).slash(posts.getPostId()).withSelfRel());
	}

}
