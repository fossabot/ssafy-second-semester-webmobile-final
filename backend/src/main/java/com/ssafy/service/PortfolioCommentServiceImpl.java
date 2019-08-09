package com.ssafy.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.respository.PortfolioCommentRepository;
import com.ssafy.respository.PortfolioRepository;
import com.ssafy.vo.Portfolio;
import com.ssafy.vo.PortfolioComment;
import com.ssafy.vo.Post;

@Service
public class PortfolioCommentServiceImpl implements PortfolioCommentService {

	@Autowired
	PortfolioRepository portfolioRepository;
	
	@Autowired
	PortfolioCommentRepository portfolioCommentRepository;

	@Override
	public Optional<PortfolioComment> findPortfolioCommentByPortfolioCommentId(final long portfolioCommentId) {
		return portfolioCommentRepository.findById(portfolioCommentId);
	}

	@Override
	@Transactional
	public PortfolioComment savePortfolioComment(final long portfolioId, final PortfolioComment portfolioComment) {
		Portfolio portfolio =portfolioRepository.findById(portfolioId).get();
		portfolio.addPortfolioComment(portfolioComment);
		return portfolioCommentRepository.save(portfolioComment);
	}

	@Override
	@Transactional
	public void deletePortfolioCommentByPortfolioCommentId(final long portfolioCommentId) {
		Portfolio porftfolio = portfolioCommentRepository.findById(portfolioCommentId).get().getPortfolio();
		porftfolio.removePortfolioComment(portfolioCommentRepository.findById(portfolioCommentId).get());
		portfolioCommentRepository.deleteById(portfolioCommentId);
	}

	@Override
	public long countPortfolioComments() {
		return portfolioCommentRepository.count();
	}

}
