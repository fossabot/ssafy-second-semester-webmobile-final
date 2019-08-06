package com.ssafy.api;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.common.BaseControllerTests;
import com.ssafy.respository.PortfolioCommentRepository;

public class PortfolioCommentRestControllerTests extends BaseControllerTests {
	
	@Autowired
	PortfolioCommentRepository portfolioCommentRepository;
	
	@Before
	public void setUp() {
		portfolioCommentRepository.deleteAll();
	}
	
	@Test
	public void test() {
		
	}
	
}
