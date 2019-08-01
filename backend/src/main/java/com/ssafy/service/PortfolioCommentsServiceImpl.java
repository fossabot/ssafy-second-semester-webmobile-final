package com.ssafy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.respository.PortfoliosCommentsRepository;
import com.ssafy.vo.PortfolioComments;

@Service
public class PortfolioCommentsServiceImpl implements PortfolioCommentsService {
	
	@Autowired
	PortfoliosCommentsRepository portfoliosCommentsRepository;
	
	@Override
	public int countPortfolioComments() {
		return (int) portfoliosCommentsRepository.count();
	}

	@Override
	public Optional<PortfolioComments> findPortfolioCommentById(int portfolioCommentId) {
		return portfoliosCommentsRepository.findById(portfolioCommentId);
	}

	@Override
	public PortfolioComments savePortfolioComments(PortfolioComments portfolioComments) {
		return portfoliosCommentsRepository.save(portfolioComments);
	}

	@Override
	public boolean deletePortfolioCommentById(int portfolioCommentId) {
		Optional<PortfolioComments> portfolioCommentsOpt = portfoliosCommentsRepository.findById(portfolioCommentId);
		if (!portfolioCommentsOpt.isPresent()) {
			return false;
		}
		portfoliosCommentsRepository.deleteById(portfolioCommentId);
		return true;
	}

}
