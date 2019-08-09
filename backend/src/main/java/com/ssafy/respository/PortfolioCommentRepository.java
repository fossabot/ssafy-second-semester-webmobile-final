package com.ssafy.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.vo.PortfolioComment;

@Repository
public interface PortfolioCommentRepository extends JpaRepository<PortfolioComment, Long> {

}
