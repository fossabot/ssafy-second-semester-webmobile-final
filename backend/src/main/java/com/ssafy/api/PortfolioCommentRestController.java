package com.ssafy.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.net.URI;
import java.util.HashMap;
import java.util.Optional;

import javax.validation.Valid;

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
import com.ssafy.exception.DataCreateException;
import com.ssafy.exception.DataNotFoundException;
import com.ssafy.exception.NoAuthenticationException;
import com.ssafy.exception.ParameterException;
import com.ssafy.service.PortfolioCommentService;
import com.ssafy.vo.Portfolio;
import com.ssafy.vo.PortfolioComment;
import com.ssafy.vo.resource.PortfolioCommentResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/portfolios", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class PortfolioCommentRestController {

	@Autowired
	PortfolioCommentService portfolioCommentService;

	@GetMapping(value = "/{portfolioId}/comments/{portfolioCommentId}")
	public ResponseEntity<PortfolioCommentResource> findPortfolioCommentByPortfolioCommentId(
			@PathVariable final long portfolioCommentId) throws Exception {

		Optional<PortfolioComment> portfolioCommentOpt = portfolioCommentService  
				.findPortfolioCommentByPortfolioCommentId(portfolioCommentId);
		if (!portfolioCommentOpt.isPresent()) {
			throw new DataNotFoundException(portfolioCommentId);
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
			@PathVariable final long portfolioId,
			@Valid @RequestBody final PortfolioComment portfolioComment) throws Exception {

		portfolioComment.setPortfolioCommentId(0);
		
		if (accountAuth > RoleType.SUPERVISOR.getRoleType()) {
			if (!portfolioComment.getAccountEmail().equals(accountEmail)) {
				throw new NoAuthenticationException(accountEmail);
			}
		}

		PortfolioComment createdPortfolioComment = portfolioCommentService.savePortfolioComment(portfolioId,portfolioComment);
		if (createdPortfolioComment == null) {
			throw new DataCreateException(portfolioComment);
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
			@PathVariable final long portfolioCommentId,
			@Valid @RequestBody final PortfolioComment portfolioComment) throws Exception {

		if (accountAuth > RoleType.SUPERVISOR.getRoleType()) {
			if (!portfolioComment.getAccountEmail().equals(accountEmail)) {
				throw new NoAuthenticationException(accountEmail); // 권한없음 Exception
			}
		}

		if (portfolioCommentId != portfolioComment.getPortfolioCommentId()) {
			throw new ParameterException(portfolioCommentId, portfolioComment.getPortfolioCommentId()); 
		}

		Optional<PortfolioComment> portfolioCommentOpt = portfolioCommentService
				.findPortfolioCommentByPortfolioCommentId(portfolioCommentId);
		if (!portfolioCommentOpt.isPresent()) {
			throw new DataNotFoundException(portfolioCommentId);
		}

		Portfolio portfolio = portfolioCommentOpt.get().getPortfolio();
		PortfolioComment updatedPortfolioComment = portfolioCommentService
				.savePortfolioComment(portfolio.getPortfolioId(),portfolioComment);
		if (updatedPortfolioComment == null) {
			throw new DataCreateException(portfolioComment); 
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
			@PathVariable final long portfolioCommentId) throws Exception {
		
		Optional<PortfolioComment> portfolioCommentOpt = portfolioCommentService
				.findPortfolioCommentByPortfolioCommentId(portfolioCommentId);
		if(!portfolioCommentOpt.isPresent()) {
			throw new DataNotFoundException(portfolioCommentId);
		}
		if (accountAuth > RoleType.SUPERVISOR.getRoleType()) {
			if(!portfolioCommentOpt.get().getAccountEmail().equals(accountEmail)) {
				throw new NoAuthenticationException(accountEmail);
			}
		}
		
		portfolioCommentService.deletePortfolioCommentByPortfolioCommentId(portfolioCommentId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/{portfolioId}/comments/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<String, Long>> countPortfolioComments() {

		HashMap<String, Long> countPortfolioCommentsMap = new HashMap<>();
		long countPortfolioComments = portfolioCommentService.countPortfolioComments();
		countPortfolioCommentsMap.put("countPortfolioComments", countPortfolioComments);
		return ResponseEntity.ok(countPortfolioCommentsMap);
	}

}
