package com.ssafy.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.respository.PortfolioRepository;
import com.ssafy.vo.Portfolio;

@Service
public class PortfolioServiceImpl implements PortfolioService {
	
	@Autowired
	PortfolioRepository portfolioRepository;

	@Override
	public Page<Portfolio> findAllPortfolios(final Pageable pageable) {
		return portfolioRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public Optional<Portfolio> findPortfolioByPortfolioId(final int portfolioId) {
		portfolioRepository.updatePortfolioViewsByPortfolioId(portfolioId);
		return portfolioRepository.findById(portfolioId);
	}


	@Override
	public Portfolio savePortfolio(final Portfolio portfolios) {
		return portfolioRepository.save(portfolios);
	}

	@Override
	public boolean deletePortfolioByPortfolioId(final int portfolioId) {
		Optional<Portfolio> optional = portfolioRepository.findById(portfolioId);
		if (!optional.isPresent()) {
			return false;
		}
		portfolioRepository.deleteById(portfolioId);
		return true;
	}
	
	@Override
	public int countPortfolios() {
		return (int) portfolioRepository.count();
	}

}
