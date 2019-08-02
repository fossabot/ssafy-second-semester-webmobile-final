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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.PortfolioCommentService;
import com.ssafy.vo.PortfolioComment;
import com.ssafy.vo.resource.PortfolioCommentResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/portfolios", produces = "application/hal+json")
public class PortfolioCommentRestController {

	@Autowired
	PortfolioCommentService portfolioCommentService;

	@GetMapping(value = "/{portfolioId}/comments/{portfolioCommentId}")
	public ResponseEntity<PortfolioCommentResource> findPortfolioCommentByPortfolioCommentId(
			@PathVariable final int portfolioCommentId) {
		
		Optional<PortfolioComment> portfolioCommentOpt = portfolioCommentService
				.findPortfolioCommentByPortfolioCommentId(portfolioCommentId);
		if (!portfolioCommentOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		
		PortfolioComment portfolioComment = portfolioCommentOpt.get();

		PortfolioCommentResource portfolioCommentResource = new PortfolioCommentResource(portfolioComment);
		return ResponseEntity.ok(portfolioCommentResource);
	}

	@PostMapping(value = "/{portfolioId}/comments")
	public ResponseEntity<PortfolioCommentResource> createPortfolioComment(
			@RequestHeader(value = "accountEmail") final String accountEmail,
			@RequestBody final PortfolioComment portfolioComment) {

		PortfolioComment createdPortfolioComment = portfolioCommentService
				.savePortfolioComment(portfolioComment);
		if (createdPortfolioComment == null) {
			return ResponseEntity.badRequest().build();
		}

		PortfolioCommentResource portfolioCommentResource = new PortfolioCommentResource(createdPortfolioComment);
		return ResponseEntity.ok(portfolioCommentResource);
	}

	@PutMapping(value = "/{portfolioId}/comments/{portfolioCommentId}")
	public ResponseEntity<PortfolioCommentResource> updatePortfolioComment(
			@PathVariable final int portfolioCommentId,
			@RequestBody final PortfolioComment portfolioComment) {

		if (portfolioCommentId != portfolioComment.getPortfolioCommentId()) {
			return ResponseEntity.badRequest().build();
		}

		Optional<PortfolioComment> portfolioCommentOpt = portfolioCommentService
				.findPortfolioCommentByPortfolioCommentId(portfolioCommentId);
		if (!portfolioCommentOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}

		PortfolioComment updatedPortfolioComment = portfolioCommentService.savePortfolioComment(portfolioComment);
		if (updatedPortfolioComment == null) {
			return ResponseEntity.badRequest().build();
		}
		
		PortfolioCommentResource portfolioCommentResource = new PortfolioCommentResource(updatedPortfolioComment);
		return ResponseEntity.ok(portfolioCommentResource);
	}

	@DeleteMapping(value = "/{portfolioId}/comments/{portfolioCommentId}")
	public ResponseEntity<?> deletePortfolioCommentByPortfolioCommentId(
			@PathVariable final int portfolioCommentId) {
		
		boolean isDelected = portfolioCommentService
				.deletePortfolioCommentByPortfolioCommentId(portfolioCommentId);
		if (isDelected) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping(value = "/{portfolioId}/comments/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<String, Integer>> countPortfolioComments() {
		
		HashMap<String, Integer> countPortfolioCommentsMap = new HashMap<>();
		int countPortfolioComments = portfolioCommentService.countPortfolioComments();
		countPortfolioCommentsMap.put("countPortfolioComments", countPortfolioComments);
		return ResponseEntity.ok(countPortfolioCommentsMap);
	}

}
