package com.ssafy.service;

import java.util.Optional;

import com.ssafy.vo.PortfolioComment;

public interface PortfolioCommentService {
	public Optional<PortfolioComment> findPortfolioCommentByPortfolioCommentId(final long portfolioCommentId);
	public PortfolioComment savePortfolioComment(long portfolioId, PortfolioComment portfolioComment);
	public void deletePortfolioCommentByPortfolioCommentId(final long portfolioCommentId); 
	public long countPortfolioComments();
}
