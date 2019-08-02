package com.ssafy.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.net.URI;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
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

import com.ssafy.auth.Auth;
import com.ssafy.common.RoleType;
import com.ssafy.service.PostCommentService;
import com.ssafy.vo.PostComment;
import com.ssafy.vo.resource.PostCommentResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/posts", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class PostCommentRestController {

	@Autowired
	PostCommentService postCommentService;

	@GetMapping(value = "/{postId}/comments/{postCommentId}")
	public ResponseEntity<PostCommentResource> findPostCommentByPostCommentId(
			@PathVariable final int postCommentId) throws Exception {
		
		Optional<PostComment> postCommentOpt = postCommentService
				.findPostCommentByPostCommentId(postCommentId);
		if (!postCommentOpt.isPresent()) {
			throw new Exception(); // NotFoundException
		}
		
		PostComment postComment = postCommentOpt.get();
		PostCommentResource postCommentResource = new PostCommentResource(postComment);
		return ResponseEntity.ok(postCommentResource);
	}

	@Auth(minimum = RoleType.VISITOR)
	@PostMapping(value = "/{postId}/comments")
	public ResponseEntity<PostCommentResource> createPostComment(
			@RequestHeader(value = "accountEmail") final String accountEmail,
			@RequestHeader(value = "accountAuth") final int accountAuth,
			@PathVariable final int postId,
			@RequestBody final PostComment postComment) throws Exception {
		
		if (accountAuth > 1) { // 관리자가 아니라면,
			if (!postComment.getAccountEmail().equals(accountEmail)) {
				throw new Exception(); // 권한없음 Exception
			}
		}
		
		PostComment createdPostComment = postCommentService.savePostComment(postComment);
		if (createdPostComment == null) {  
			throw new Exception(); // 업데이트 실패 Exception
		}
		
		ControllerLinkBuilder selfLinkBuilder = linkTo(PostRestController.class)
				.slash(createdPostComment.getPostCommentId());
        URI createdUri = selfLinkBuilder.toUri();

		PostCommentResource postCommetResource = new PostCommentResource(createdPostComment);
		postCommetResource.add(selfLinkBuilder.withRel("update"));
		postCommetResource.add(selfLinkBuilder.withRel("delete"));
		return ResponseEntity.created(createdUri).body(postCommetResource);
	}

	@Auth(minimum = RoleType.VISITOR)
	@PutMapping(value = "/{postId}/comments/{postCommentId}")
	public ResponseEntity<PostCommentResource> updatePostComment(
			@RequestHeader(value = "accountEmail") final String accountEmail,
			@RequestHeader(value = "accountAuth") final int accountAuth,
			@PathVariable final int postCommentId,
			@RequestBody final PostComment postComment) throws Exception {

		if (accountAuth > 1) { // 관리자가 아니라면,
			if (!postComment.getAccountEmail().equals(accountEmail)) {
				throw new Exception(); // 권한없음 Exception
			}
		}
		
		if (postCommentId != postComment.getPostCommentId()) {
			throw new Exception(); // BadRequest Exception 
		}

		Optional<PostComment> postCommentOpt = postCommentService
				.findPostCommentByPostCommentId(postCommentId);
		if (!postCommentOpt.isPresent()) {
			throw new Exception(); // NotFoundException
		}
		
		PostComment updatedPostComment = postCommentService
				.savePostComment(postComment);
		if (updatedPostComment == null) {
			throw new Exception(); // 업데이트 실패 Exception 
		}
		
		ControllerLinkBuilder selfLinkBuilder = linkTo(PostRestController.class)
				.slash(updatedPostComment.getPostCommentId());
		
		PostCommentResource postCommentResource = new PostCommentResource(updatedPostComment);
		postCommentResource.add(selfLinkBuilder.withRel("delete"));
		return ResponseEntity.ok(postCommentResource);
	}

	@Auth(minimum = RoleType.VISITOR)
	@DeleteMapping(value = "/{postId}/comments/{postCommentId}")
	public ResponseEntity<?> deletePostCommentByPostCommentId(
			@RequestHeader(value = "accountEmail") final String accountEmail,
			@RequestHeader(value = "accountAuth") final int accountAuth,
			@PathVariable final int postCommentId) throws Exception {

		if (accountAuth > 1) { // 관리자가 아니라면,
			Optional<PostComment> postCommentOpt = postCommentService
					.findPostCommentByPostCommentId(postCommentId);
			if(!postCommentOpt.isPresent()) {
				throw new Exception(); // NotFoundException
			}
			
			if(!postCommentOpt.get().getAccountEmail().equals(accountEmail)) {
				throw new Exception(); // 권한없음 Exception
			}
		}
		
		postCommentService.deletePostCommentByPostCommentId(postCommentId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{postId}/comments/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<String, Integer>> countPostComments() {
		
		HashMap<String, Integer> countPostCommentsMap = new HashMap<>();
		int countPostComments = postCommentService.countPostComments();
		countPostCommentsMap.put("countPostComments", countPostComments);
		return ResponseEntity.ok(countPostCommentsMap);
	}
	
}
