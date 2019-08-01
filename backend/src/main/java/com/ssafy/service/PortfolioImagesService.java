package com.ssafy.service;

import java.util.Optional;

import com.ssafy.vo.PortfolioImages;

public interface PortfolioImagesService {
	public int countPortfolioImages(); // 전체 댓글 수
	public Optional<PortfolioImages> findPortfolioImageById(int portfolioImageId); // 댓글 가져오기
	public PortfolioImages savePortfolioImages(PortfolioImages portfolioImages); // 댓글 수정, 삽입
	public boolean deletePortfolioImageById(int portfolioImageId); // 댓글 삭제
}
