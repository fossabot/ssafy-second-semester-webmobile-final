package com.ssafy.api;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ssafy.service.PostCommentService;
import com.ssafy.vo.PostComment;
import com.ssafy.vo.resource.PostCommentResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/posts", produces = "application/hal+json")
public class PostCommentRestController {

	@Autowired
	PostCommentService postCommentService;

	@GetMapping(value = "/{postId}/comments/{postCommentId}")
	public ResponseEntity<PostCommentResource> findPostCommentByPostCommentId(
			@PathVariable final int postCommentId) {
		
		Optional<PostComment> postCommentOpt = postCommentService
				.findPostCommentByPostCommentId(postCommentId);
		if (!postCommentOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		
		PostComment postComment = postCommentOpt.get();
		PostCommentResource postCommentResource = new PostCommentResource(postComment);
		return ResponseEntity.ok(postCommentResource);
	}

	@PostMapping(value = "/{postId}/comments")
	public ResponseEntity<PostCommentResource> createPostComment(
			@PathVariable final int postId,
			@RequestBody final PostComment postComment) {
		
		PostComment createdPostComment = postCommentService.savePostComment(postComment);
		if (createdPostComment == null) {  
			return ResponseEntity.badRequest().build();
		}

		PostCommentResource postCommetResource = new PostCommentResource(createdPostComment);
		return ResponseEntity.ok(postCommetResource);
	}

	@PutMapping(value = "/{postId}/comments/{postCommentId}")
	public ResponseEntity<PostCommentResource> updatePostComment(
			@PathVariable final int postCommentId,
			@RequestBody final PostComment postComment) {

		if (postCommentId != postComment.getPostCommentId()) {
			return ResponseEntity.badRequest().build();
		}

		Optional<PostComment> postCommentOpt = postCommentService
				.findPostCommentByPostCommentId(postCommentId);
		if (!postCommentOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		
		PostComment updatedPostComment = postCommentService
				.savePostComment(postComment);
		if (updatedPostComment == null) {
			return ResponseEntity.badRequest().build();
		}
		
		PostCommentResource postCommentResource = new PostCommentResource(updatedPostComment);
		return ResponseEntity.ok(postCommentResource);
	}

	@DeleteMapping(value = "/{postId}/comments/{postCommentId}")
	public ResponseEntity<?> deletePostCommentByPostCommentId(
			@PathVariable final int postCommentId) {
		
		boolean isDelected = postCommentService.deletePostCommentByPostCommentId(postCommentId);

		if (isDelected) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping(value = "/{postId}/comments/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<String, Integer>> countPostComments() {
		
		HashMap<String, Integer> countPostCommentsMap = new HashMap<>();
		int countPostComments = postCommentService.countPostComments();
		countPostCommentsMap.put("countPostComments", countPostComments);
		return ResponseEntity.ok(countPostCommentsMap);
	}
	
}
