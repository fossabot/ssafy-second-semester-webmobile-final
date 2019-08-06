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
import com.ssafy.service.PortfolioCommentService;
import com.ssafy.vo.PortfolioComment;
import com.ssafy.vo.resource.PortfolioCommentResource;

@CrossOrigin()
@RestController
@RequestMapping(value = "/portfolios", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class PortfolioCommentRestController {

	@Autowired
	PortfolioCommentService portfolioCommentService;

	@GetMapping(value = "/{portfolioId}/comments/{portfolioCommentId}")
	public ResponseEntity<PortfolioCommentResource> findPortfolioCommentByPortfolioCommentId(
			@PathVariable final int portfolioCommentId) throws Exception {

		Optional<PortfolioComment> portfolioCommentOpt = portfolioCommentService
				.findPortfolioCommentByPortfolioCommentId(portfolioCommentId);
		if (!portfolioCommentOpt.isPresent()) {
			throw new Exception(); //NotFoundException
		}

		PortfolioComment portfolioComment = portfolioCommentOpt.get();

		PortfolioCommentResource portfolioCommentResource = new PortfolioCommentResource(portfolioComment);
		return ResponseEntity.ok(portfolioCommentResource);
	}

	@Auth(minimum = RoleType.VISITOR)
	@PostMapping(value = "/{portfolioId}/comments")
	public ResponseEntity<PortfolioCommentResource> createPortfolioComment(
			@RequestHeader(value = "accountEmail") final String accountEmail,
			@RequestHeader(value = "accountAuth") final int accountAuth,
			@RequestBody final PortfolioComment portfolioComment) throws Exception {

		if (accountAuth > 1) { // 관리자가 아니라면,
			if (!portfolioComment.getAccountEmail().equals(accountEmail)) {
				throw new Exception(); // 권한없음 Exception
			}
		}

		PortfolioComment createdPortfolioComment = portfolioCommentService.savePortfolioComment(portfolioComment);
		if (createdPortfolioComment == null) {
			throw new Exception(); // 업데이트 실패 Exception
		}
		
		ControllerLinkBuilder selfLinkBuilder = linkTo(PortfolioRestController.class)
				.slash(createdPortfolioComment.getPortfolioCommentId());
        URI createdUri = selfLinkBuilder.toUri();

		PortfolioCommentResource portfolioCommentResource = new PortfolioCommentResource(createdPortfolioComment);
		portfolioCommentResource.add(selfLinkBuilder.withRel("update"));
		portfolioCommentResource.add(selfLinkBuilder.withRel("delete"));
		return ResponseEntity.created(createdUri).body(portfolioCommentResource);
	}

	@Auth(minimum = RoleType.VISITOR)
	@PutMapping(value = "/{portfolioId}/comments/{portfolioCommentId}")
	public ResponseEntity<PortfolioCommentResource> updatePortfolioComment(
			@RequestHeader(value = "accountEmail") final String accountEmail,
			@RequestHeader(value = "accountAuth") final int accountAuth,
			@PathVariable final int portfolioCommentId,
			@RequestBody final PortfolioComment portfolioComment) throws Exception {

		if (accountAuth > 1) { // 관리자가 아니라면,
			if (!portfolioComment.getAccountEmail().equals(accountEmail)) {
				throw new Exception(); // 권한없음 Exception
			}
		}

		if (portfolioCommentId != portfolioComment.getPortfolioCommentId()) {
			throw new Exception(); // BadRequest Exception 
		}

		Optional<PortfolioComment> portfolioCommentOpt = portfolioCommentService
				.findPortfolioCommentByPortfolioCommentId(portfolioCommentId);
		if (!portfolioCommentOpt.isPresent()) {
			throw new Exception(); // NotFoundException
		}

		PortfolioComment updatedPortfolioComment = portfolioCommentService.savePortfolioComment(portfolioComment);
		if (updatedPortfolioComment == null) {
			throw new Exception(); // 업데이트 실패 Exception 
		}
		
		ControllerLinkBuilder selfLinkBuilder = linkTo(PortfolioRestController.class)
				.slash(updatedPortfolioComment.getPortfolioCommentId());

		PortfolioCommentResource portfolioCommentResource = new PortfolioCommentResource(updatedPortfolioComment);
		portfolioCommentResource.add(selfLinkBuilder.withRel("delete"));
		return ResponseEntity.ok(portfolioCommentResource);
	}

	@Auth(minimum = RoleType.VISITOR)
	@DeleteMapping(value = "/{portfolioId}/comments/{portfolioCommentId}")
	public ResponseEntity<?> deletePortfolioCommentByPortfolioCommentId(
			@RequestHeader(value = "accountEmail") final String accountEmail,
			@RequestHeader(value = "accountAuth") final int accountAuth,
			@PathVariable final int portfolioCommentId) throws Exception {
		
		if (accountAuth > 1) { // 관리자가 아니라면,
			Optional<PortfolioComment> portfolioCommentOpt = portfolioCommentService
					.findPortfolioCommentByPortfolioCommentId(portfolioCommentId);
			if(!portfolioCommentOpt.isPresent()) {
				throw new Exception(); // NotFoundException
			}
			
			if(!portfolioCommentOpt.get().getAccountEmail().equals(accountEmail)) {
				throw new Exception(); // 권한없음 Exception
			}
		}
		
		portfolioCommentService.deletePortfolioCommentByPortfolioCommentId(portfolioCommentId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/{portfolioId}/comments/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<String, Integer>> countPortfolioComments() {

		HashMap<String, Integer> countPortfolioCommentsMap = new HashMap<>();
		int countPortfolioComments = portfolioCommentService.countPortfolioComments();
		countPortfolioCommentsMap.put("countPortfolioComments", countPortfolioComments);
		return ResponseEntity.ok(countPortfolioCommentsMap);
	}

}
