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

import com.ssafy.service.PortfolioImagesService;
import com.ssafy.vo.PortfolioImages;
import com.ssafy.vo.resource.PortfolioImagesResource;

@CrossOrigin
@Controller
@RequestMapping(value = "/portfolios/{portfolioId}/images", produces = "application/hal+json")
public class PortfolioImagesRestController {

	@Autowired
	PortfolioImagesService portfolioImagesService;
	
	@GetMapping("/{portfolioImageId}")
	public ResponseEntity<PortfolioImagesResource> findPortfolioImageById(@PathVariable int portfolioImageId) {
		Optional<PortfolioImages> portfolioImagesOpt = portfolioImagesService.findPortfolioImageById(portfolioImageId);
		if(!portfolioImagesOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		PortfolioImages portfolioImages = portfolioImagesOpt.get();

		//HATEOAS
		PortfolioImagesResource portfolioImagesResource = new PortfolioImagesResource(portfolioImages);
		return ResponseEntity.ok(portfolioImagesResource);
	}
	
	//이미지 총 개수
	@GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HashMap<String, Integer>> countPortfolioImages() {
		HashMap<String, Integer> countPortfolioImages = new HashMap<>();
		int count = portfolioImagesService.countPortfolioImages();
		countPortfolioImages.put("countPortfolios", count);
		return ResponseEntity.ok(countPortfolioImages);
	}
	
	// -- 삽입
	@PostMapping("")
	public ResponseEntity<PortfolioImagesResource> createPortfolioImage(@RequestBody PortfolioImages portfolioImage) {
		PortfolioImages createdPortfolioImage = portfolioImagesService.savePortfolioImages(portfolioImage);
		if (createdPortfolioImage == null) {
			return ResponseEntity.badRequest().build();
		}
		
		PortfolioImagesResource portfolioImagesResource = new PortfolioImagesResource(createdPortfolioImage);
		return ResponseEntity.ok(portfolioImagesResource);
	}
	
	@PutMapping("")
	public ResponseEntity<PortfolioImagesResource> updatePortfolioImage(@PathVariable int portfolioImageId, @RequestBody PortfolioImages portfolioImage) {
		// 수정을 요청하는 아이디와 portfolioImage의 아이디가 다른 경우
		if (portfolioImageId != portfolioImage.getPortfolioImageId()) {
			return ResponseEntity.badRequest().build();
		}

		Optional<PortfolioImages> portfolioImageOpt = portfolioImagesService.findPortfolioImageById(portfolioImageId);
		if (!portfolioImageOpt.isPresent()) {
			// 수정하려는 데이터가 존재하지 않음
			return ResponseEntity.badRequest().build();
		}

		PortfolioImages updatedPortfolioImage = portfolioImagesService.savePortfolioImages(portfolioImage);
		if (updatedPortfolioImage == null) {
			// 수정하려는 데이터가 존재하지만
			// 수정에 실패함
			return ResponseEntity.badRequest().build();
		}
		PortfolioImagesResource portfolioImagesResource = new PortfolioImagesResource(updatedPortfolioImage);
		// 성공시 뱉어줌
		return ResponseEntity.ok(portfolioImagesResource);
	}
	
	@DeleteMapping(value = "/{portfolioImageId}")
	public ResponseEntity<?> deletePortfolioImageById(@PathVariable int portfolioImageId) {
		boolean isDelected = portfolioImagesService.deletePortfolioImageById(portfolioImageId); // 성공하면 true

		if (isDelected) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
