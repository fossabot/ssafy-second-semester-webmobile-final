package com.ssafy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.vo.Portfolio;

public interface PortfolioService {
	public Page<Portfolio> findAllPortfolios(final Pageable pageable);
	public List<Portfolio> findAll();
	public Optional<Portfolio> findPortfolioByPortfolioId(final long portfolioId);
	public Portfolio savePortfolio(final Portfolio portfolios);
	public void deletePortfolioByPortfolioId(final long portfolioId);
	public long countPortfolios();
}
