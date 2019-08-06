package com.ssafy.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.net.URI;
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
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ssafy.auth.Auth;
import com.ssafy.common.RoleType;
import com.ssafy.respository.PostRepository;
import com.ssafy.service.PostService;
import com.ssafy.vo.Post;
import com.ssafy.vo.resource.PostResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/posts", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
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

	@GetMapping(value = "/pages/{pageNo}")
	public ResponseEntity<PagedResources<PostResource>> findAllPosts(
			@PathVariable final int pageNo,
			PagedResourcesAssembler<Post> assembler) {
		
		Pageable pageable = PageRequest.of(pageNo - 1, 6, new Sort(Direction.DESC, "postCreatedAt"));
		Page<Post> posts = postService.findAllPosts(pageable);
		PagedResources<PostResource> pagedPostResources = assembler
				.toResource(posts, e -> new PostResource(e));
		return ResponseEntity.ok(pagedPostResources);
	}

	@GetMapping(value = "/{postId}")
	public ResponseEntity<PostResource> findPostByPostId(
			@PathVariable final int postId) throws Exception {
		
		Optional<Post> postOpt = postService.findPostByPostId(postId);
		if (!postOpt.isPresent()) {
			throw new Exception(); // NotFoundException
		}
		
		Post post = postOpt.get();
		PostResource postResource = new PostResource(post);
		return ResponseEntity.ok(postResource);
	}

	@Auth(minimum = RoleType.MEMBER)
	@PostMapping(value = "")
	public ResponseEntity<PostResource> createPost(
			@RequestHeader(value = "accountEmail") final String accountEmail,
			@RequestHeader(value = "accountAuth") final int accountAuth,
			@RequestBody final Post post) throws Exception {
		
		if (accountAuth > 1) { // 관리자가 아니라면,
			if (!post.getAccountEmail().equals(accountEmail)) {
				throw new Exception(); // 권한없음 Exception
			}
		}
		
		Post createdPost = postService.savePost(post);
		if (createdPost == null) {
			throw new Exception(); // 업데이트 실패 Exception
		}
		
		ControllerLinkBuilder selfLinkBuilder = linkTo(PortfolioRestController.class).slash(createdPost.getPostId());
        URI createdUri = selfLinkBuilder.toUri();
		
		PostResource postResource = new PostResource(createdPost);
		return ResponseEntity.created(createdUri).body(postResource);
	}

	@Auth(minimum = RoleType.MEMBER)
	@PutMapping(value = "/{postId}")
	public ResponseEntity<PostResource> updatePost(
			@RequestHeader(value = "accountEmail") final String accountEmail,
			@RequestHeader(value = "accountAuth") final int accountAuth,
			@PathVariable final int postId,
			@RequestBody final Post post) throws Exception {

		if (accountAuth > 1) { // 관리자가 아니라면,
			if (!post.getAccountEmail().equals(accountEmail)) {
				throw new Exception(); // 권한없음 Exception
			}
		}
		
		if (postId != post.getPostId()) {
			throw new Exception(); // BadRequest Exception 
		}

		Optional<Post> postOpt = postService.findPostByPostId(postId);
		if (!postOpt.isPresent()) {
			throw new Exception(); // NotFoundException
		}
		
		Post updatedPost = postService.savePost(post);
		if (updatedPost == null) {
			throw new Exception(); // 업데이트 실패 Exception 
		}
		
		PostResource postResource = new PostResource(updatedPost);
		return ResponseEntity.ok(postResource);
	}

	@Auth(minimum = RoleType.MEMBER)
	@DeleteMapping(value = "/{postId}")
	public ResponseEntity<?> deletePostByPostId(
			@RequestHeader(value = "accountEmail") final String accountEmail,
			@RequestHeader(value = "accountAuth") final int accountAuth,
			@PathVariable final int postId) throws Exception {
		
		if (accountAuth > 1) { // 관리자가 아니라면,
			Optional<Post> postOpt = postService
					.findPostByPostId(postId);
			if(!postOpt.isPresent()) {
				throw new Exception(); // NotFoundException
			}
			
			if(!postOpt.get().getAccountEmail().equals(accountEmail)) {
				throw new Exception(); // 권한없음 Exception
			}
		}
		
		postService.deletePostByPostId(postId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<String, Integer>> countPosts() {
		
		HashMap<String, Integer> countPostsMap = new HashMap<>();
		int countPosts = postService.countPosts();
		countPostsMap.put("countPosts", countPosts);
		return ResponseEntity.ok(countPostsMap);
	}
	
}
