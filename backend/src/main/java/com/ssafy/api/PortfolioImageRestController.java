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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.auth.Auth;
import com.ssafy.common.RoleType;
import com.ssafy.exception.DataCreateException;
import com.ssafy.exception.DataNotFoundException;
import com.ssafy.exception.NoAuthenticationException;
import com.ssafy.exception.ParameterException;
import com.ssafy.service.PortfolioImageService;
import com.ssafy.service.PortfolioService;
import com.ssafy.vo.Portfolio;
import com.ssafy.vo.PortfolioImage;
import com.ssafy.vo.resource.PortfolioImageResource;

@CrossOrigin
@Controller
@RequestMapping(value = "/portfolios", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class PortfolioImageRestController {

	@Autowired
	PortfolioImageService portfolioImageService;
	
	@Autowired
	PortfolioService portfolioService;

	@GetMapping(value = "/{portfolioId}/images/{portfolioImageId}")
	public ResponseEntity<PortfolioImageResource> findPortfolioImageByPortfolioImageId(
			@PathVariable final long portfolioImageId) throws Exception {

		Optional<PortfolioImage> portfolioImageOpt = portfolioImageService
				.findPortfolioImageByPortfolioImageId(portfolioImageId);
		if (!portfolioImageOpt.isPresent()) {
			throw new DataNotFoundException(portfolioImageId);
		}

		PortfolioImage portfolioImage = portfolioImageOpt.get();
		PortfolioImageResource portfolioImageResource = new PortfolioImageResource(portfolioImage);
		return ResponseEntity.ok(portfolioImageResource);
	}

	@Auth(minimum = RoleType.MEMBER)
	@PostMapping(value = "/{portfolioId}/images")
	public ResponseEntity<PortfolioImageResource> createPortfolioImage(
			@RequestHeader(value = "accountEmail") final String accountEmail,
			@RequestHeader(value = "accountAuth") final int accountAuth,
			@RequestBody final PortfolioImage portfolioImage) throws Exception {

		portfolioImage.setPortfolioImageId(0);
		
//		Optional<Portfolio> porfolio = portfolioService.findPortfolioByPortfolioId(portfolioImage.getPortfolioId());
//		if(!porfolio.isPresent()) {
//			throw new DataNotFoundException(portfolioImage.getPortfolioId());
//		}
//		if (accountAuth > RoleType.SUPERVISOR.getRoleType()) {
//			if (!porfolio.get().getAccountEmail().equals(accountEmail)) {
//				throw new NoAuthenticationException(accountEmail);
//			}
//		}
		
		PortfolioImage createdPortfolioImage = portfolioImageService.savePortfolioImage(portfolioImage);
		if (createdPortfolioImage == null) {
			throw new DataCreateException(portfolioImage);
		}

		ControllerLinkBuilder selfLinkBuilder = linkTo(PortfolioRestController.class)
				.slash(createdPortfolioImage.getPortfolioImageId());
		URI createdUri = selfLinkBuilder.toUri();

		PortfolioImageResource portfolioImageResource = new PortfolioImageResource(createdPortfolioImage);
		portfolioImageResource.add(selfLinkBuilder.withRel("update"));
		portfolioImageResource.add(selfLinkBuilder.withRel("delete"));
		return ResponseEntity.created(createdUri).body(portfolioImageResource);
	}

	@Auth(minimum = RoleType.MEMBER)
	@PutMapping(value = "/{portfolioId}/images")
	public ResponseEntity<PortfolioImageResource> updatePortfolioImage(
			@RequestHeader(value = "accountEmail") final String accountEmail,
			@RequestHeader(value = "accountAuth") final int accountAuth,
			@PathVariable final long portfolioImageId,
			@RequestBody final PortfolioImage portfolioImage) throws Exception {

		if (portfolioImageId != portfolioImage.getPortfolioImageId()) {
			throw new ParameterException(portfolioImageId, portfolioImage.getPortfolioImageId());
		}

		Optional<PortfolioImage> portfolioImageOpt = portfolioImageService
				.findPortfolioImageByPortfolioImageId(portfolioImageId);
		if (!portfolioImageOpt.isPresent()) {
			throw new DataNotFoundException(portfolioImageId);
		}
//		Optional<Portfolio> porfolio = portfolioService.findPortfolioByPortfolioId(portfolioImage.getPortfolioId());
//		if(!porfolio.isPresent()) {
//			throw new DataNotFoundException(portfolioImage.getPortfolioId());
//		}
//		if (accountAuth > RoleType.SUPERVISOR.getRoleType()) {
//			if (!porfolio.get().getAccountEmail().equals(accountEmail)) {
//				throw new NoAuthenticationException(accountEmail);
//			}
//		}

		PortfolioImage updatedPortfolioImage = portfolioImageService.savePortfolioImage(portfolioImage);
		if (updatedPortfolioImage == null) {
			throw new DataCreateException(updatedPortfolioImage);
		}

		ControllerLinkBuilder selfLinkBuilder = linkTo(PortfolioRestController.class)
				.slash(updatedPortfolioImage.getPortfolioImageId());

		PortfolioImageResource portfolioImageResource = new PortfolioImageResource(updatedPortfolioImage);
		portfolioImageResource.add(selfLinkBuilder.withRel("delete"));
		return ResponseEntity.ok(portfolioImageResource);
	}

	@Auth(minimum = RoleType.MEMBER)
	@DeleteMapping(value = "/{portfolioId}/images/{portfolioImageId}")
	public ResponseEntity<?> deletePortfolioImageByPortfolioImageId(
			@RequestHeader(value = "accountEmail") final String accountEmail,
			@RequestHeader(value = "accountAuth") final int accountAuth,
			@PathVariable final long portfolioImageId) throws Exception {

		Optional<PortfolioImage> portfolioImageOpt = portfolioImageService
				.findPortfolioImageByPortfolioImageId(portfolioImageId);
		if (!portfolioImageOpt.isPresent()) {
			throw new DataNotFoundException(portfolioImageId);
		}
		Optional<Portfolio> porfolio = portfolioService.findPortfolioByPortfolioId(portfolioImageId);
		if(!porfolio.isPresent()) {
			throw new DataNotFoundException(portfolioImageId);
		}
		if (accountAuth > RoleType.SUPERVISOR.getRoleType()) {
			if (!porfolio.get().getAccountEmail().equals(accountEmail)) {
				throw new NoAuthenticationException(accountEmail);
			}
		}

		portfolioImageService.deletePortfolioImageByPortfolioImageId(portfolioImageId); // 성공하면 true
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{portfolioId}/images/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<String, Long>> countPortfolioImages() {

		HashMap<String, Long> countPortfolioImagesMap = new HashMap<>();
		long countPortfolioImages = portfolioImageService.countPortfolioImages();
		countPortfolioImagesMap.put("countPortfolioImages", countPortfolioImages);
		return ResponseEntity.ok(countPortfolioImagesMap);
	}

}
