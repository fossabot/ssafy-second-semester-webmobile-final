package com.ssafy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.respository.PortfolioCommentRepository;
import com.ssafy.vo.PortfolioComment;

@Service
public class PortfolioCommentServiceImpl implements PortfolioCommentService {
	
	@Autowired
	PortfolioCommentRepository portfolioCommentRepository;

	@Override
	public Optional<PortfolioComment> findPortfolioCommentByPortfolioCommentId(final int portfolioCommentId) {
		return portfolioCommentRepository.findById(portfolioCommentId);
	}

	@Override
	public PortfolioComment savePortfolioComment(final PortfolioComment portfolioComment) {
		return portfolioCommentRepository.save(portfolioComment);
	}

	@Override
	public void deletePortfolioCommentByPortfolioCommentId(final int portfolioCommentId) {
		portfolioCommentRepository.deleteById(portfolioCommentId);
		return;
	}
	
	@Override
	public int countPortfolioComments() {
		return (int) portfolioCommentRepository.count();
	}

}
