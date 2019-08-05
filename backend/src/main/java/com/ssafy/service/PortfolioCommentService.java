package com.ssafy.service;

import java.util.Optional;

import com.ssafy.vo.PortfolioComment;

public interface PortfolioCommentService {
	public Optional<PortfolioComment> findPortfolioCommentByPortfolioCommentId(final int portfolioCommentId);
	public PortfolioComment savePortfolioComment(final PortfolioComment portfolioComment);
	public void deletePortfolioCommentByPortfolioCommentId(final int portfolioCommentId); 
	public int countPortfolioComments();
}
