package com.ssafy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.respository.PortfoliosImgesRepository;
import com.ssafy.vo.PortfolioImages;

@Service
public class PortfolioImagesServiceImpl implements PortfolioImagesService {
	
	@Autowired
	PortfoliosImgesRepository portfoliosImageRepository;

	@Override
	public int countPortfolioImages() {
		return (int) portfoliosImageRepository.count();
	}

	@Override
	public Optional<PortfolioImages> findPortfolioImageById(int portfolioImageId) {
		return portfoliosImageRepository.findById(portfolioImageId);
	}

	@Override
	public PortfolioImages savePortfolioImages(PortfolioImages portfolioImages) {
		return portfoliosImageRepository.save(portfolioImages);
	}

	@Override
	public boolean deletePortfolioImageById(int portfolioImageId) {
		Optional<PortfolioImages> portfolioImagesOpt = portfoliosImageRepository.findById(portfolioImageId);
		if (!portfolioImagesOpt.isPresent()) {
			return false;
		}
		portfoliosImageRepository.deleteById(portfolioImageId);
		return true;
	}

}
