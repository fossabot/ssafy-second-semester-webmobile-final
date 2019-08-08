package com.ssafy.exception;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import com.ssafy.common.BaseControllerTests;
import com.ssafy.vo.Portfolio;

public class ExceptionTests extends BaseControllerTests {
	
	@Before
	public void setUp() {
		portfolioRepository.deleteAll();
	}
	
//	@Test
//	public void dataCreateExceptionTest() throws Exception {
//		//given
//		Portfolio testData = createTestPortfolio();
//		
//		//when & then
//		mockMvc.perform(post("/portfolios")
//					.header("accountEmail", "test@email.com")
//					.header("accountAuth", 1)
//					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//					.content(objectMapper.writeValueAsString(testData))
//				)
//			.andDo(print())
//			.andExpect(status().isBadRequest())
//			.andDo(document(
//					"data-create-exception",
//					requestFields(
//							fieldWithPath("portfolioId").description("포트폴리오 Id (Auto increase)"),
//							fieldWithPath("accountEmail").description("작성자 Email"),
//							fieldWithPath("accountName").description("작성자 이름"),
//							fieldWithPath("portfolioTitle").description("제목"),
//							fieldWithPath("portfolioContent").description("내용"),
//							fieldWithPath("portfolioGiturl").description("Git repository 주소"),
//							fieldWithPath("portfolioViews").description("조회수 (0으로 자동 초기화)"),
//							fieldWithPath("portfolioThumbnailUrl").description("썸네일 이미지 경로")
//					),
//					responseFields(
//							fieldWithPath("code").description("Http status code"),
//							fieldWithPath("status").description("status 이름"),
//							fieldWithPath("error type").description("Exception 이름"),
//							fieldWithPath("error message").description("에러 메시지")
//					)
//			))
//		;
//	}
	
	@Test
	public void dataNotFoundExceptionTest() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/portfolios/{portfolioId}", 100))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andDo(document(
				"data-not-found-exception",
				pathParameters(
						parameterWithName("portfolioId").description("요청할 포트폴리오 Id")
				),
				responseFields(
						fieldWithPath("code").description("Http status code"),
						fieldWithPath("status").description("status 이름"),
						fieldWithPath("error type").description("Exception 이름"),
						fieldWithPath("error message").description("에러 메시지")
				)
		))
;
	}
	
	@Test
	public void noAuthenticationExceptionTest() throws Exception {
		//given
		Portfolio testData = createTestPortfolio();
		
		//when & then
		mockMvc.perform(post("/portfolios")
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content(objectMapper.writeValueAsString(testData))
				)
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andDo(document(
					"no-authentication-exception",
					requestFields(
							fieldWithPath("portfolioId").description("포트폴리오 Id (Auto increase)"),
							fieldWithPath("accountEmail").description("작성자 Email"),
							fieldWithPath("accountName").description("작성자 이름"),
							fieldWithPath("portfolioTitle").description("제목"),
							fieldWithPath("portfolioContent").description("내용"),
							fieldWithPath("portfolioGiturl").description("Git repository 주소"),
							fieldWithPath("portfolioViews").description("조회수 (0으로 자동 초기화)"),
							fieldWithPath("portfolioThumbnailUrl").description("썸네일 이미지 경로")
					),
					responseFields(
							fieldWithPath("code").description("Http status code"),
							fieldWithPath("status").description("status 이름"),
							fieldWithPath("error type").description("Exception 이름"),
							fieldWithPath("error message").description("에러 메시지")
					)
			))
		;
	}
	
	@Test
	public void parameterExceptionTest() throws Exception {
		//given
		Portfolio testData = createTestPortfolio();
		testData.setPortfolioId(3);
		
		//when & then
		mockMvc.perform(put("/portfolios/{portfolioId}", 100)
					.header("accountEmail", "test@email.com")
					.header("accountAuth", 1)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content(objectMapper.writeValueAsString(testData))
				)
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andDo(document(
					"parameter-exception",
					requestFields(
							fieldWithPath("portfolioId").description("포트폴리오 Id (Auto increase)"),
							fieldWithPath("accountEmail").description("작성자 Email"),
							fieldWithPath("accountName").description("작성자 이름"),
							fieldWithPath("portfolioTitle").description("제목"),
							fieldWithPath("portfolioContent").description("내용"),
							fieldWithPath("portfolioGiturl").description("Git repository 주소"),
							fieldWithPath("portfolioViews").description("조회수 (0으로 자동 초기화)"),
							fieldWithPath("portfolioThumbnailUrl").description("썸네일 이미지 경로")
					),
					responseFields(
							fieldWithPath("code").description("Http status code"),
							fieldWithPath("status").description("status 이름"),
							fieldWithPath("error type").description("Exception 이름"),
							fieldWithPath("error message").description("에러 메시지")
					)
			))
		;
	}
	
	@Test
	public void methodArgumentNotValidExceptionTest() throws Exception {
		//given
		Portfolio testData = createTestPortfolio();
		testData.setPortfolioTitle(null);
		
		//when & then
		mockMvc.perform(post("/portfolios")
					.header("accountEmail", "test@email.com")
					.header("accountAuth", 1)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content(objectMapper.writeValueAsString(testData))
				)
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andDo(document(
					"method-argument-not-valid-exception",
					requestFields(
							fieldWithPath("portfolioId").description("포트폴리오 Id (Auto increase)"),
							fieldWithPath("accountEmail").description("작성자 Email"),
							fieldWithPath("accountName").description("작성자 이름"),
							fieldWithPath("portfolioContent").description("내용"),
							fieldWithPath("portfolioGiturl").description("Git repository 주소"),
							fieldWithPath("portfolioViews").description("조회수 (0으로 자동 초기화)"),
							fieldWithPath("portfolioThumbnailUrl").description("썸네일 이미지 경로")
					),
					responseFields(
							fieldWithPath("code").description("Http status code"),
							fieldWithPath("status").description("status 이름"),
							fieldWithPath("error type").description("Exception 이름"),
							fieldWithPath("error message").description("에러 메시지")
					)
			))
		;
	}
	
}
