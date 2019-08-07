package com.ssafy.api;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import com.ssafy.common.BaseControllerTests;
import com.ssafy.vo.Portfolio;

public class PortfolioRestControllerTests extends BaseControllerTests {
	
	@Before
	public void setUp() {
		portfolioRepository.deleteAll();
	}
	
	@Test
	public void createPortfolio() throws Exception {
		//given
		Portfolio testData = createTestPortfolio();
		
		//when & then
		mockMvc.perform(post("/portfolios")
					.header("accountEmail", "test@email.com")
					.header("accountAuth", 2)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content(objectMapper.writeValueAsString(testData))
				)
			.andDo(print())
			.andExpect(status().isCreated())
			.andDo(document(
					"create-portfolio",
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
							fieldWithPath("links[0].rel").description("포트폴리오 HATEOAS 관계명"),
							fieldWithPath("links[0].href").description("포트폴리오 HATEOAS URI"),
							fieldWithPath("portfolioId").description("포트폴리오 Id"),
							fieldWithPath("accountEmail").description("작성자 Email"),
							fieldWithPath("accountName").description("작성자 이름"),
							fieldWithPath("portfolioTitle").description("제목"),
							fieldWithPath("portfolioContent").description("내용"),
							fieldWithPath("portfolioCreatedAt").description("생성 시간"),
							fieldWithPath("portfolioGiturl").description("Git repository 주소"),
							fieldWithPath("portfolioViews").description("조회수"),
							fieldWithPath("portfolioThumbnailUrl").description("썸네일 이미지 경로"),
							fieldWithPath("portfolioComments").description("포트폴리오 댓글 정보")
					)
			))
		;
	}
	
	@Test
	public void updatePortfolio() throws Exception {
		//given
		Portfolio testData = createTestPortfolio();
		testData = portfolioRepository.save(testData);
		testData.setPortfolioTitle("update title");
		testData.setPortfolioContent("update content");
		
		//when & then
		mockMvc.perform(RestDocumentationRequestBuilders.put("/portfolios/{portfolioId}", testData.getPortfolioId())
					.header("accountEmail", "test@email.com")
					.header("accountAuth", 2)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content(objectMapper.writeValueAsString(testData))
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andDo(document(
				"update-portfolio",
				pathParameters(
						parameterWithName("portfolioId").description("수정할 포트폴리오 Id")
				),
				requestFields(
						fieldWithPath("portfolioId").description("수정할 포트폴리오 Id"),
						fieldWithPath("accountEmail").description("작성자 Email"),
						fieldWithPath("accountName").description("작성자 이름"),
						fieldWithPath("portfolioTitle").description("제목"),
						fieldWithPath("portfolioContent").description("내용"),
						fieldWithPath("portfolioCreatedAt").description("생성 시간"),
						fieldWithPath("portfolioGiturl").description("Git repository 주소"),
						fieldWithPath("portfolioViews").description("조회수"),
						fieldWithPath("portfolioThumbnailUrl").description("썸네일 이미지 경로")
				),
				responseFields(
						fieldWithPath("links[0].rel").description("포트폴리오 HATEOAS 관계명"),
						fieldWithPath("links[0].href").description("포트폴리오 HATEOAS URI"),
						fieldWithPath("portfolioId").description("포트폴리오 Id"),
						fieldWithPath("accountEmail").description("작성자 Email"),
						fieldWithPath("accountName").description("작성자 이름"),
						fieldWithPath("portfolioTitle").description("제목"),
						fieldWithPath("portfolioContent").description("내용"),
						fieldWithPath("portfolioCreatedAt").description("생성 시간"),
						fieldWithPath("portfolioGiturl").description("Git repository 주소"),
						fieldWithPath("portfolioViews").description("조회수"),
						fieldWithPath("portfolioThumbnailUrl").description("썸네일 이미지 경로"),
						fieldWithPath("portfolioComments").description("포트폴리오 댓글 정보")
				)
			))
		;
	}
	
	@Test
	public void findAllPortfolios() throws Exception {
		//given
		Portfolio testData = createTestPortfolio();
		testData = portfolioRepository.save(testData);
		
		//when & then
		mockMvc.perform(RestDocumentationRequestBuilders.get("/portfolios/pages/{pageNo}", 1L))
				.andDo(print())
				.andExpect(status().isOk())
				.andDo(document(
						"find-all-portfolios",
						pathParameters(
								parameterWithName("pageNo").description("요청할 페이지 숫자")
						),
						responseFields(
								fieldWithPath("links[0].rel").description("포트폴리오 페이지 HATEOAS 관계명"),
								fieldWithPath("links[0].href").description("포트폴리오 페이지 HATEOAS URI"),
								fieldWithPath("content[0].portfolioId").description("포트폴리오 Id"),
								fieldWithPath("content[0].accountEmail").description("작성자 Email"),
								fieldWithPath("content[0].accountName").description("작성자 이름"),
								fieldWithPath("content[0].portfolioTitle").description("제목"),
								fieldWithPath("content[0].portfolioContent").description("내용"),
								fieldWithPath("content[0].portfolioCreatedAt").description("생성 시간"),
								fieldWithPath("content[0].portfolioGiturl").description("Git repository 주소"),
								fieldWithPath("content[0].portfolioViews").description("조회수"),
								fieldWithPath("content[0].portfolioThumbnailUrl").description("썸네일 이미지 경로"),
								fieldWithPath("content[0].portfolioComments").description("댓글 정보"),
								fieldWithPath("content[0].links[0].rel").description("각 포트폴리오의 HATEOAS 관계명"),
								fieldWithPath("content[0].links[0].href").description("각 포트폴리오의 HATEOAS URI"),
								fieldWithPath("page.size").description("한 페이지 내에 들어가는 포트폴리오 수"),
								fieldWithPath("page.totalElements").description("총 포트폴리오 수"),
								fieldWithPath("page.totalPages").description("총 페이지 수"),
								fieldWithPath("page.number").description("현재 페이지")
						)
				))
		;
	}
	
	@Test
	public void findPortfolioByPortfolioId() throws Exception {
		//given
		Portfolio testData = createTestPortfolio();
		testData = portfolioRepository.save(testData);
		
		//when & then
		mockMvc.perform(RestDocumentationRequestBuilders.get("/portfolios/{portfolioId}", testData.getPortfolioId()))
				.andDo(print())
				.andExpect(status().isOk())
				.andDo(document(
						"find-portfolio-by-portfolio-id",
						pathParameters(
								parameterWithName("portfolioId").description("요청할 포트폴리오 Id")
						),
						responseFields(
								fieldWithPath("links[0].rel").description("포트폴리오 HATEOAS 관계명"),
								fieldWithPath("links[0].href").description("포트폴리오 HATEOAS URI"),
								fieldWithPath("portfolioId").description("포트폴리오 Id"),
								fieldWithPath("accountEmail").description("작성자 Email"),
								fieldWithPath("accountName").description("작성자 이름"),
								fieldWithPath("portfolioTitle").description("제목"),
								fieldWithPath("portfolioContent").description("내용"),
								fieldWithPath("portfolioCreatedAt").description("생성 시간"),
								fieldWithPath("portfolioGiturl").description("Git repository 주소"),
								fieldWithPath("portfolioViews").description("조회수"),
								fieldWithPath("portfolioThumbnailUrl").description("썸네일 이미지 경로"),
								fieldWithPath("portfolioComments").description("댓글 정보")
						)
				))
		;
	}
	
	@Test
	public void deletePortfolio() throws Exception {
		//given
		Portfolio testData = createTestPortfolio();
		testData = portfolioRepository.save(testData);
		
		//when & then
		mockMvc.perform(RestDocumentationRequestBuilders.delete("/portfolios/{portfolioId}", testData.getPortfolioId())
						.header("accountEmail", "test@email.com")
						.header("accountAuth", 1)
					)
				.andDo(print())
				.andExpect(status().isNoContent())
				.andDo(document(
						"delete-portfolio",
						pathParameters(
								parameterWithName("portfolioId").description("삭제할 포트폴리오 Id")
						)
				))
		;
	}
	
	@Test
	public void countPortfolios() throws Exception {
		//given
		Portfolio testData = createTestPortfolio();
		portfolioRepository.save(testData);
		
		//when & then
		mockMvc.perform(get("/portfolios/count"))
			.andDo(print())
			.andExpect(status().isOk())
			.andDo(document(
					"count-portfolios",
					responseFields(
							fieldWithPath("countPortfolios").description("포트폴리오 총 개수")
					)
			))
		;
	}
	
}
