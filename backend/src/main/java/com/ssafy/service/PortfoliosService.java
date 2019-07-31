package com.ssafy.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.vo.Portfolios;

public interface PortfoliosService {
	public int countPortfolios(); // 전체 게시글
	public Page<Portfolios> findAllPortfolios(Pageable pageable); // 게시글 리스트 불러오기
	public Optional<Portfolios> findPortfolioById(int portfolioId); // 게시글 읽기
	public Portfolios savePortfolio(Portfolios portfolios); // 게시글 수정, 삽입
	public boolean deletePortfolioById(int portfolioId); // 게시글 삭제
}
