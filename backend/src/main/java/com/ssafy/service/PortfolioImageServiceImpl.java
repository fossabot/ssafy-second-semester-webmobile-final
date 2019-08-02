package com.ssafy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.respository.PortfolioImageRepository;
import com.ssafy.vo.PortfolioImage;

@Service
public class PortfolioImageServiceImpl implements PortfolioImageService {
	
	@Autowired
	PortfolioImageRepository portfolioImageRepository;

	@Override
	public Optional<PortfolioImage> findPortfolioImageByPortfolioImageId(final int portfolioImageId) {
		return portfolioImageRepository.findById(portfolioImageId);
	}

	@Override
	public PortfolioImage savePortfolioImage(final PortfolioImage portfolioImage) {
		return portfolioImageRepository.save(portfolioImage);
	}

	@Override
	public void deletePortfolioImageByPortfolioImageId(final int portfolioImageId) {
		portfolioImageRepository.deleteById(portfolioImageId);
	}
	
	@Override
	public int countPortfolioImages() {
		return (int) portfolioImageRepository.count();
	}

}
