package com.ssafy.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.respository.PortfoliosRepository;
import com.ssafy.vo.Portfolios;

@Service
public class PortfoliosServiceImpl implements PortfoliosService {
	
	@Autowired
	PortfoliosRepository portfoliosRepository;

	@Override
	public int countPortfolios() {
		return (int) portfoliosRepository.count();
	}

	@Override
	public Page<Portfolios> findAllPortfolios(Pageable pageable) {
		return portfoliosRepository.findAll(pageable);
	}

	/**
	 * 포트폴리오 게시글 읽어오기 (조회수 올리기 + 읽어오기)
	 */
	@Override
	@Transactional
	public Optional<Portfolios> findPortfolioById(int portfolioId) {
		portfoliosRepository.updatePortfolioViewsByPortfolioId(portfolioId);
		return portfoliosRepository.findById(portfolioId);
	}


	@Override
	public Portfolios savePortfolio(Portfolios portfolios) {
		return portfoliosRepository.save(portfolios);
	}

	@Override
	public boolean deletePortfolioById(int portfolioId) {
		Optional<Portfolios> optional = portfoliosRepository.findById(portfolioId);
		if (!optional.isPresent()) {
			return false;
		}
		portfoliosRepository.deleteById(portfolioId);
		return true;
	}

}
