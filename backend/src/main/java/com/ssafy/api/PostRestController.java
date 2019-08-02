package com.ssafy.api;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
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

import com.ssafy.respository.PostRepository;
import com.ssafy.service.PostService;
import com.ssafy.vo.Post;
import com.ssafy.vo.resource.PostResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/posts", produces = "application/hal+json")
public class PostRestController {

	//-지울것
	@Autowired
	PostRepository postRepository;
	@GetMapping(value = "")
	public ResponseEntity<Resources<PostResource>> findAll() {
		List<PostResource> posts = postRepository.findAll().stream().map(PostResource::new)
				.collect(Collectors.toList());

		Resources<PostResource> postsResources = new Resources<>(posts);
		String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
		postsResources.add(new Link(uriString, "self"));
		return ResponseEntity.ok(postsResources);
	}
	//-
	
	@Autowired
	PostService postService;

	@GetMapping(value = "/page/{pageNo}")
	public ResponseEntity<PagedResources<PostResource>> findAllPosts(
			@PathVariable final int pageNo,
			PagedResourcesAssembler<Post> assembler) {
		
		Pageable pageable = PageRequest.of(pageNo - 1, 6, new Sort(Direction.DESC, "postCreatedAt"));
		Page<Post> posts = postService.findAllPosts(pageable);
		if (posts == null) {
			return ResponseEntity.badRequest().build();
		}
		
		PagedResources<PostResource> pagedPostResources = assembler
				.toResource(posts, e -> new PostResource(e));
		return ResponseEntity.ok(pagedPostResources);
	}

	@GetMapping(value = "/{postId}")
	public ResponseEntity<PostResource> findPostByPostId(
			@PathVariable final int postId) {
		
		Optional<Post> postOpt = postService.findPostByPostId(postId);
		if (!postOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		
		Post post = postOpt.get();
		PostResource postResource = new PostResource(post);
		return ResponseEntity.ok(postResource);
	}

	@PostMapping(value = "")
	public ResponseEntity<PostResource> createPost(
			@RequestBody final Post post) {
		
		Post createdPost = postService.savePost(post);
		if (createdPost == null) {
			return ResponseEntity.badRequest().build();
		}
		
		PostResource postResource = new PostResource(createdPost);
		return ResponseEntity.ok(postResource);
	}

	@PutMapping(value = "/{postId}")
	public ResponseEntity<PostResource> updatePost(
			@PathVariable final int postId,
			@RequestBody final Post post) {

		if (postId != post.getPostId()) {
			return ResponseEntity.badRequest().build();
		}

		Optional<Post> postOpt = postService.findPostByPostId(postId);
		if (!postOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		
		Post updatedPost = postService.savePost(post);
		if (updatedPost == null) {
			return ResponseEntity.badRequest().build();
		}
		
		PostResource postResource = new PostResource(updatedPost);
		return ResponseEntity.ok(postResource);
	}

	@DeleteMapping(value = "/{postId}")
	public ResponseEntity<?> deletePostByPostId(
			@PathVariable final int postId) {
		boolean isDelected = postService.deletePostByPostId(postId);

		if (isDelected) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<String, Integer>> countPosts() {
		
		HashMap<String, Integer> countPostsMap = new HashMap<>();
		int countPosts = postService.countPosts();
		countPostsMap.put("countPosts", countPosts);
		return ResponseEntity.ok(countPostsMap);
	}
	
}
