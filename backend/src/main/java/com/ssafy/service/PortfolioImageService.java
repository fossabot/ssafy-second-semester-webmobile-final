package com.ssafy.service;

import java.util.Optional;

import com.ssafy.vo.PortfolioImage;

public interface PortfolioImageService {
	public Optional<PortfolioImage> findPortfolioImageByPortfolioImageId(final int portfolioImageId); 
	public PortfolioImage savePortfolioImage(final PortfolioImage portfolioImage);
	public void deletePortfolioImageByPortfolioImageId(final int portfolioImageId); 
	public int countPortfolioImages(); 
}
