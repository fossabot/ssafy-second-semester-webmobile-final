package com.ssafy.api;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.service.PortfolioImageService;
import com.ssafy.vo.PortfolioImage;
import com.ssafy.vo.resource.PortfolioImageResource;

@CrossOrigin
@Controller
@RequestMapping(value = "/portfolios", produces = "application/hal+json")
public class PortfolioImageRestController {

	@Autowired
	PortfolioImageService portfolioImageService;
	
	@GetMapping(value = "/{portfolioId}/images/{portfolioImageId}")
	public ResponseEntity<PortfolioImageResource> findPortfolioImageByPortfolioImageId(
			@PathVariable final int portfolioImageId) {
		
		Optional<PortfolioImage> portfolioImageOpt = portfolioImageService
				.findPortfolioImageByPortfolioImageId(portfolioImageId);
		if(!portfolioImageOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		
		PortfolioImage portfolioImage = portfolioImageOpt.get();
		PortfolioImageResource portfolioImageResource = new PortfolioImageResource(portfolioImage);
		return ResponseEntity.ok(portfolioImageResource);
	}

	@PostMapping(value = "/{portfolioId}/images")
	public ResponseEntity<PortfolioImageResource> createPortfolioImage(
			@RequestBody final PortfolioImage portfolioImage) {
		
		PortfolioImage createdPortfolioImage = portfolioImageService
				.savePortfolioImage(portfolioImage);
		if (createdPortfolioImage == null) {
			return ResponseEntity.badRequest().build();
		}
		
		PortfolioImageResource portfolioImageResource = new PortfolioImageResource(createdPortfolioImage);
		return ResponseEntity.ok(portfolioImageResource);
	}
	
	@PutMapping(value = "/{portfolioId}/images")
	public ResponseEntity<PortfolioImageResource> updatePortfolioImage(
			@PathVariable final int portfolioImageId,
			@RequestBody final PortfolioImage portfolioImage) {
		
		if (portfolioImageId != portfolioImage.getPortfolioImageId()) {
			return ResponseEntity.badRequest().build();
		}

		Optional<PortfolioImage> portfolioImageOpt = portfolioImageService
				.findPortfolioImageByPortfolioImageId(portfolioImageId);
		if (!portfolioImageOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}

		PortfolioImage updatedPortfolioImage = portfolioImageService.savePortfolioImage(portfolioImage);
		if (updatedPortfolioImage == null) {
			return ResponseEntity.badRequest().build();
		}
		
		PortfolioImageResource portfolioImageResource = new PortfolioImageResource(updatedPortfolioImage);
		return ResponseEntity.ok(portfolioImageResource);
	}
	
	@DeleteMapping(value = "/{portfolioId}/images/{portfolioImageId}")
	public ResponseEntity<?> deletePortfolioImageByPortfolioImageId(
			@PathVariable final int portfolioImageId) {
		
		boolean isDelected = portfolioImageService
				.deletePortfolioImageByPortfolioImageId(portfolioImageId); // 성공하면 true

		if (isDelected) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping(value = "/{portfolioId}/images/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<String, Integer>> countPortfolioImages() {
		
		HashMap<String, Integer> countPortfolioImagesMap = new HashMap<>();
		int countPortfolioImages = portfolioImageService.countPortfolioImages();
		countPortfolioImagesMap.put("countPortfolioImages", countPortfolioImages);
		return ResponseEntity.ok(countPortfolioImagesMap);
	}
	
}
