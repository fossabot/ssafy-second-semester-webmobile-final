package com.ssafy.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ssafy.vo.Portfolio;

@RunWith(SpringRunner.class)
@WebMvcTest(PortfolioRestController.class)
public class PortfolioRestControllerTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void findAllPortfolios() throws Exception {
		
		//given
		Portfolio portfolio = Portfolio.builder()
				.accountEmail("abc@email.com")
				.accountName("abc")
				.portfolioTitle("포트폴리오 제목")
				.portfolioContent("포트폴리오 내용")
				.build();
				
		//then
		//mockMvc.perform(get("/api/bears/portfolios"))
				//.contentType(MediaType.APPLICATION_JSON_UTF8)
				//.accept(MediaTypes.HAL_JSON_UTF8_VALUE)
				
				
	}
	
	@Test
	public void createPortfolio() throws Exception {
		
	}
	
}
