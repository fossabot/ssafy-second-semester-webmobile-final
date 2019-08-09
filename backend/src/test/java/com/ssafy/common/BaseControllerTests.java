package com.ssafy.common;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.respository.PortfolioRepository;
import com.ssafy.respository.PostRepository;
import com.ssafy.vo.Portfolio;
import com.ssafy.vo.Post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ActiveProfiles("test")
@Import({TestRestDocConfiguration.class})
@Ignore
public class BaseControllerTests {
	
	@Autowired
	protected WebApplicationContext webApplicationContext;
	
	@Autowired
	protected ObjectMapper objectMapper;
	
	@Autowired
	protected MockMvc mockMvc;
	 
	@Autowired
	protected PortfolioRepository portfolioRepository;
	
	@Autowired
	protected PostRepository postRepository;
	 
	protected Post createTestPost() {
	    return Post.builder()
	    		.accountEmail("test@email.com")
				.accountName("test name")
				.postTitle("포스트 제목")
				.postContent("포스트 내용")
				.postThumbnailUrl("/image.png")
	    		.build();
	}
	
	protected Portfolio createTestPortfolio() {
        return Portfolio.builder()
                .accountEmail("test@email.com")
                .accountName("test name")
                .portfolioTitle("포트폴리오 제목")
                .portfolioContent("포트폴리오 내용")
                .portfolioGiturl("www.github.com/repository")
                .portfolioThumbnailUrl("/image.png")
        		.build();
    }

}
