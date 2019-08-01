package com.ssafy.api;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
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

import com.ssafy.service.PostCommentsService;
import com.ssafy.vo.PostComments;
import com.ssafy.vo.resource.PostCommentsResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/posts/{postId}/comments", produces = "application/hal+json")
public class PostCommentsRestController {

	@Autowired
	PostCommentsService postCommentsService;

	@GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<String, Integer>> countPostComments() {
		HashMap<String, Integer> map = new HashMap<>();
		int countPostComments = postCommentsService.countPostComments();
		map.put("countPosts", countPostComments);
		return ResponseEntity.ok(map);
	}

	@GetMapping(value = "/{postCommentId}")
	public ResponseEntity<?> findPostCommentById(@PathVariable int postCommentId) {
		Optional<PostComments> postCommentOpt = postCommentsService.findPostCommentById(postCommentId);
		if (!postCommentOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		PostComments postComment = postCommentOpt.get();
		// HATEOAS
		// http://localhost:9090/api/bears/posts
		String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
		PostCommentsResource postCommentsResource = new PostCommentsResource(postComment);
		postCommentsResource.add(new Link(uriString, "self"));
		return ResponseEntity.ok(postCommentsResource);
	}

	// -- 삽입
	@PostMapping(value = "")
	public ResponseEntity<PostCommentsResource> createPostComment(@RequestBody PostComments postComment) {
		PostComments createdPostComment = postCommentsService.savePostComment(postComment);

		if (createdPostComment == null) {
			return ResponseEntity.badRequest().build();
		}
		PostCommentsResource postCommetsResource = new PostCommentsResource(createdPostComment);
		return ResponseEntity.ok(postCommetsResource);
	}

	// -- 수정
	@PutMapping(value = "/{postCommentId}")
	public ResponseEntity<PostComments> updatePost(@PathVariable int postCommentId, @RequestBody PostComments postComment) {

		// 수정을 요청하는 아이디와 post의 아이디가 다른 경우
		if (postCommentId != postComment.getPostCommentId()) {
			return ResponseEntity.badRequest().build();
		}

		Optional<PostComments> postCommentOpt = postCommentsService.findPostCommentById(postCommentId);
		if (!postCommentOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		PostComments updatedPostComment = postCommentsService.savePostComment(postComment);
		if (updatedPostComment == null) {
			return ResponseEntity.badRequest().build();
		}
		// 성공시 뱉어줌
		return ResponseEntity.ok(updatedPostComment);
	}

	@DeleteMapping(value = "/{postCommentId}")
	public ResponseEntity<?> deletePostById(@PathVariable int postCommentId) {
		boolean isDelected = postCommentsService.deletePostCommentById(postCommentId);

		if (isDelected) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
