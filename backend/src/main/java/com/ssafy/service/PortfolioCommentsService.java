package com.ssafy.service;

import java.util.Optional;

import com.ssafy.vo.PortfolioComments;

public interface PortfolioCommentsService {
	public int countPortfolioComments(); // 전체 댓글 수
	public Optional<PortfolioComments> findPortfolioCommentById(int portfolioCommentId); // 댓글 가져오기
	public PortfolioComments savePortfolioComments(PortfolioComments portfolioComments); // 댓글 수정, 삽입
	public boolean deletePortfolioCommentById(int portfolioCommentId); // 댓글 삭제
}
