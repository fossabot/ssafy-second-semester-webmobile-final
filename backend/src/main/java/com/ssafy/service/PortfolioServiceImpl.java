package com.ssafy.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.respository.PortfolioRepository;
import com.ssafy.vo.Portfolio;
import com.ssafy.vo.Post;

@Service
public class PortfolioServiceImpl implements PortfolioService {
	
	@Autowired
	PortfolioRepository portfolioRepository;

	@Override
	public Page<Portfolio> findAllPortfolios(final Pageable pageable) {
		return portfolioRepository.findAll(pageable);
	}
	
	@Override
	public List<Portfolio> findAll() {
		return portfolioRepository.findAll();
	}
	
	@Override
	@Transactional
	public Optional<Portfolio> findPortfolioByPortfolioId(final long portfolioId) {
		portfolioRepository.updatePortfolioViewsByPortfolioId(portfolioId);
		return portfolioRepository.findById(portfolioId);
	}


	@Override
	public Portfolio savePortfolio(final Portfolio portfolios) {
		return portfolioRepository.save(portfolios);
	}

	@Override
	public void deletePortfolioByPortfolioId(final long portfolioId) {
		portfolioRepository.deleteById(portfolioId);
	}
	
	@Override
	public long countPortfolios() {
		return portfolioRepository.count();
	}

}
