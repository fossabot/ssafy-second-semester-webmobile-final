package com.ssafy.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ssafy.service.PostsService;
import com.ssafy.vo.Posts;
import com.ssafy.vo.resource.PostsResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/posts", produces = "application/hal+json")
public class PostsRestController {

	@Autowired
	PostsService postsService;

	@GetMapping
	public ResponseEntity<Resources<PostsResource>> findAll() {
		List<PostsResource> posts = postsService.findAll().stream().map(PostsResource::new)
				.collect(Collectors.toList());

		Resources<PostsResource> postsResources = new Resources<>(posts);
		String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
		postsResources.add(new Link(uriString, "self"));
		return ResponseEntity.ok(postsResources);
	}

	@GetMapping(value = "/page/{pageNo}")
	public ResponseEntity<PagedResources<PostsResource>> findAllPosts(@PathVariable int pageNo,
			PagedResourcesAssembler<Posts> assembler) {
		Pageable pageable = PageRequest.of(pageNo - 1, 6, Sort.by("postCreatedAt"));
		Page<Posts> posts = postsService.findAllPosts(pageable);
		if (posts == null) {
			return ResponseEntity.badRequest().build();
		}
		PagedResources<PostsResource> pagedPostsResources = assembler.toResource(posts, e -> new PostsResource(e));
		return ResponseEntity.ok(pagedPostsResources);
	}

	@GetMapping(value = "/{postId}")
	public ResponseEntity<?> findPostById(@PathVariable int postId) {
		Optional<Posts> postOpt = postsService.findPostById(postId);
		if (!postOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		Posts post = postOpt.get();
		// HATEOAS
		// http://localhost:9090/api/bears/posts
		String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
		PostsResource postsResource = new PostsResource(post);
		postsResource.add(new Link(uriString, "self"));
		return ResponseEntity.ok(postsResource);
	}

	// -- 삽입
	@PostMapping(value = "")
	public ResponseEntity<PostsResource> createPost(@RequestBody Posts post) {
		System.out.println(post);
		Posts createdPost = postsService.savePost(post);

		if (createdPost == null) {
			return ResponseEntity.badRequest().build();
		}
		PostsResource postsResource = new PostsResource(createdPost);
		return ResponseEntity.ok(postsResource);
	}

	// -- 수정
	@PutMapping(value = "/{postId}")
	public ResponseEntity<Posts> updatePost(@PathVariable int postId, @RequestBody Posts post) {

		// 수정을 요청하는 아이디와 post의 아이디가 다른 경우
		if (postId != post.getPostId()) {
			return ResponseEntity.badRequest().build();
		}

		Optional<Posts> postOpt = postsService.findPostById(postId);
		if (!postOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		Posts updatedPost = postsService.savePost(post);
		if (updatedPost == null) {
			return ResponseEntity.badRequest().build();
		}
		// 성공시 뱉어줌
		return ResponseEntity.ok(updatedPost);
	}

	@DeleteMapping(value = "/{postId}")
	public ResponseEntity<?> deletePostById(@PathVariable int postId) {
		boolean isDelected = postsService.deletePostById(postId);

		if (isDelected) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
