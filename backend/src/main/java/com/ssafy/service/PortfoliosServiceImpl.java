package com.ssafy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.respository.PortfoliosRespository;
import com.ssafy.vo.Portfolios;

@Service
public class PortfoliosServiceImpl implements PortfoliosService {
	
	@Autowired
	PortfoliosRespository portfoliosRepository;

	@Override
	public int countPortfolios() {
		return (int) portfoliosRepository.count();
	}

	@Override
	public Page<Portfolios> findAllPortfolios(Pageable pageable) {
		return portfoliosRepository.findAll(pageable);
	}

	@Override
	public Optional<Portfolios> findPortfolioById(int portfolio_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Portfolios savePortfolio(Portfolios portfolios) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePortfolioById(int portfolio_id) {
		// TODO Auto-generated method stub
		return false;
	}

}
