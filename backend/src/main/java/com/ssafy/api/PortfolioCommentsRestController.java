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

import com.ssafy.service.PortfolioCommentsService;
import com.ssafy.vo.PortfolioComments;
import com.ssafy.vo.resource.PortfolioCommentsResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/portfolios/{portfolioId}/comments", produces = "application/hal+json")
public class PortfolioCommentsRestController {
	
	@Autowired
	PortfolioCommentsService portfolioCommentsService;
	
	@GetMapping("/{portfolioCommentId}")
	public ResponseEntity<PortfolioCommentsResource> findPortfolioCommentById(@PathVariable int portfolioCommentId) {
		Optional<PortfolioComments> portfolioCommentsOpt = portfolioCommentsService.findPortfolioCommentById(portfolioCommentId);
		if(!portfolioCommentsOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		PortfolioComments portfolioComments = portfolioCommentsOpt.get();

		//HATEOAS
		PortfolioCommentsResource portfolioCommentsResource = new PortfolioCommentsResource(portfolioComments);
		return ResponseEntity.ok(portfolioCommentsResource);
	}
	
	//댓글 총 개수
	@GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<String, Integer>> countPortfolioComments() {
		HashMap<String, Integer> countPortfolioComments = new HashMap<>();
		int count = portfolioCommentsService.countPortfolioComments();
		countPortfolioComments.put("countPortfolios", count);
		return ResponseEntity.ok(countPortfolioComments);
	}
	
	// -- 삽입
	@PostMapping("")
	public ResponseEntity<PortfolioCommentsResource> createPortfolioComment(@RequestBody PortfolioComments portfolioComments) {
		PortfolioComments createdPortfolioComment = portfolioCommentsService.savePortfolioComments(portfolioComments);
		if (createdPortfolioComment == null) {
			return ResponseEntity.badRequest().build();
		}
		
		PortfolioCommentsResource portfolioCommentsResource = new PortfolioCommentsResource(createdPortfolioComment);
		return ResponseEntity.ok(portfolioCommentsResource);
	}
	
	@PutMapping("")
	public ResponseEntity<PortfolioCommentsResource> updatePortfolioComment(@PathVariable int portfolioCommentId, @RequestBody PortfolioComments portfolioComment) {
		// 수정을 요청하는 아이디와 portfolioComment의 아이디가 다른 경우
		if (portfolioCommentId != portfolioComment.getPortfolioCommentId()) {
			return ResponseEntity.badRequest().build();
		}

		Optional<PortfolioComments> portfolioCommentOpt = portfolioCommentsService.findPortfolioCommentById(portfolioCommentId);
		if (!portfolioCommentOpt.isPresent()) {
			// 수정하려는 데이터가 존재하지 않음
			return ResponseEntity.badRequest().build();
		}

		PortfolioComments updatedPortfolioComment = portfolioCommentsService.savePortfolioComments(portfolioComment);
		if (updatedPortfolioComment == null) {
			// 수정하려는 데이터가 존재하지만
			// 수정에 실패함
			return ResponseEntity.badRequest().build();
		}
		PortfolioCommentsResource portfolioCommentsResource = new PortfolioCommentsResource(updatedPortfolioComment);
		// 성공시 뱉어줌
		return ResponseEntity.ok(portfolioCommentsResource);
	}
	
	@DeleteMapping(value = "/{portfolioCommentId}")
	public ResponseEntity<?> deletePortfolioCommentById(@PathVariable int portfolioCommentId) {
		boolean isDelected = portfolioCommentsService.deletePortfolioCommentById(portfolioCommentId); // 성공하면 true

		if (isDelected) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

}
