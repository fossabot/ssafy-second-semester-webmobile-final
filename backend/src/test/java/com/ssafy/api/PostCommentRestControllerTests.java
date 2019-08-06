package com.ssafy.api;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.common.BaseControllerTests;
import com.ssafy.respository.PostCommentRepository;

public class PostCommentRestControllerTests extends BaseControllerTests {

	@Autowired
	PostCommentRepository postCommentRepository;
	
	@Before
	public void setUp() {
		postCommentRepository.deleteAll();
	}
	
	@Test
	public void test() {
		
	}
	
}
