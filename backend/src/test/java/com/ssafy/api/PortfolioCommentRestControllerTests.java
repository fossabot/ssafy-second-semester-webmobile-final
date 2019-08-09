package com.ssafy.api;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import com.ssafy.common.BaseControllerTests;
import com.ssafy.respository.PortfolioCommentRepository;
import com.ssafy.vo.Portfolio;
import com.ssafy.vo.PortfolioComment;

public class PortfolioCommentRestControllerTests extends BaseControllerTests {
	
	@Autowired
	PortfolioCommentRepository portfolioCommentRepository;
	
	@Before
	public void setUp() {
		portfolioRepository.deleteAll();
		portfolioCommentRepository.deleteAll();
	}
	
	@Test
	public void createPortfolioComment() throws Exception {
		//given
		Portfolio testPortfolio = createTestPortfolio();
		testPortfolio = portfolioRepository.save(testPortfolio);
		PortfolioComment testData = createTestPortfolioComment();
		
		//when & then
		mockMvc.perform(RestDocumentationRequestBuilders.post("/portfolios/{portfolioId}/comments", testPortfolio.getPortfolioId())
					.header("accountEmail", "test@email.com")
					.header("accountAuth", 2)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content(objectMapper.writeValueAsString(testData))
				)
			.andDo(print())
			.andExpect(status().isCreated())
			.andDo(document(
					"create-portfolio-comment",
					pathParameters(
							parameterWithName("portfolioId").description("댓글을 쓸 포트폴리오 Id")
					),
					requestFields(
							fieldWithPath("portfolioCommentId").description("포트폴리오 댓글 Id (Auto increase)"),
							fieldWithPath("accountEmail").description("작성자 Email"),
							fieldWithPath("accountName").description("작성자 이름"),
							fieldWithPath("portfolioCommentContent").description("댓글 내용")
					),
					responseFields(
							fieldWithPath("links[0].rel").description("포트폴리오 댓글 HATEOAS 관계명"),
							fieldWithPath("links[0].href").description("포트폴리오 댓글 HATEOAS URI"),
							fieldWithPath("portfolioCommentId").description("포트폴리오 댓글 Id"),
							fieldWithPath("accountEmail").description("작성자 Email"),
							fieldWithPath("accountName").description("작성자 이름"),
							fieldWithPath("portfolioCommentContent").description("댓글 내용"),
							fieldWithPath("portfolioCommentCreatedAt").description("생성 시간")
					)
			))
		;
	}
	
	@Test
	public void updatePortfolioComment() throws Exception {
		//given
		Portfolio testPortfolio = createTestPortfolio();
		testPortfolio = portfolioRepository.save(testPortfolio);
		PortfolioComment testData = createTestPortfolioComment();
		testData.setPortfolio(testPortfolio);
		testData.setPortfolioCommentContent("내용 수정");
		testData = portfolioCommentRepository.save(testData);
		
		//when & then
		mockMvc.perform(RestDocumentationRequestBuilders.put("/portfolios/{portfolioId}/comments/{portfolioCommentId}",
				testPortfolio.getPortfolioId(), testData.getPortfolioCommentId())
					.header("accountEmail", "test@email.com")
					.header("accountAuth", 2)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content(objectMapper.writeValueAsString(testData))
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andDo(document(
					"update-portfolio-comment",
					pathParameters(
							parameterWithName("portfolioId").description("댓글을 쓴 포트폴리오 Id"),
							parameterWithName("portfolioCommentId").description("수정할 포트폴리오 댓글 Id")
					),
					requestFields(
							fieldWithPath("portfolioCommentId").description("포트폴리오 댓글 Id"),
							fieldWithPath("accountEmail").description("작성자 Email"),
							fieldWithPath("accountName").description("작성자 이름"),
							fieldWithPath("portfolioCommentContent").description("댓글 내용"),
							fieldWithPath("portfolioCommentCreatedAt").description("생성 시간")
					),
					responseFields(
							fieldWithPath("links[0].rel").description("포트폴리오 댓글 HATEOAS 관계명"),
							fieldWithPath("links[0].href").description("포트폴리오 댓글 HATEOAS URI"),
							fieldWithPath("portfolioCommentId").description("포트폴리오 댓글 Id"),
							fieldWithPath("accountEmail").description("작성자 Email"),
							fieldWithPath("accountName").description("작성자 이름"),
							fieldWithPath("portfolioCommentContent").description("댓글 내용"),
							fieldWithPath("portfolioCommentCreatedAt").description("생성 시간")
					)
			))
		;
	}
	
	@Test
	public void deletePortfolioComment() throws Exception {
		//given
		Portfolio testPortfolio = createTestPortfolio();
		testPortfolio = portfolioRepository.save(testPortfolio);
		PortfolioComment testData = createTestPortfolioComment();
		testData.setPortfolio(testPortfolio);
		testData = portfolioCommentRepository.save(testData);
		
		//when & then
		mockMvc.perform(RestDocumentationRequestBuilders.delete("/portfolios/{portfolioId}/comments/{portfolioCommentId}",
				testPortfolio.getPortfolioId(), testData.getPortfolioCommentId())
					.header("accountEmail", "test@email.com")
					.header("accountAuth", 1)
				)
			.andDo(print())
			.andExpect(status().isNoContent())
			.andDo(document(
					"delete-portfolio-comment",
					pathParameters(
							parameterWithName("portfolioId").description("댓글을 쓴 포트폴리오 Id"),
							parameterWithName("portfolioCommentId").description("삭제할 포트폴리오 댓글 Id")
					)
			))
		;
	}
	
	private PortfolioComment createTestPortfolioComment() {
        return PortfolioComment.builder()
                .accountEmail("test@email.com")
                .accountName("test name")
                .portfolioCommentContent("댓글 내용")
        		.build();
    }
	
}
