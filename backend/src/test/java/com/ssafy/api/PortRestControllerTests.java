package com.ssafy.api;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.common.BaseControllerTests;
import com.ssafy.respository.PostRepository;

public class PortRestControllerTests extends BaseControllerTests {
	
	@Autowired
	PostRepository postRepository;
	
	@Before
	public void setUp() {
		postRepository.deleteAll();
	}
	
	@Test
	public void test() {
		
	}
	
}
