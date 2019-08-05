package com.ssafy.api;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ssafy.vo.Portfolio;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortfolioRestControllerTests {
	
	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	private RestDocumentationResultHandler restDocumentationResultHandler;
	
	@Before
	public void setUp() {
		this.restDocumentationResultHandler = document(
				"{class-name}/{method-name}",
				preprocessResponse(prettyPrint())
				
		);
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
				.apply(documentationConfiguration(this.restDocumentation))
				.alwaysDo(restDocumentationResultHandler)
				.build();
	}
	
	@Test
	public void countPortfolios() throws Exception {
		mockMvc.perform(get("/portfolios/count"))
			.andDo(print())
			.andExpect(status().isOk())
			.andDo(restDocumentationResultHandler.document(
					responseFields(
							fieldWithPath("countPortfolios").description("count of portfolios")
					)
			))
		;
	}
	
	@Test
	public void findAllPortfolios() throws Exception {
		//then
		mockMvc.perform(RestDocumentationRequestBuilders.get("/portfolios/pages/{pageNo}", 1L))
				.andDo(print())
				.andExpect(status().isOk())
				.andDo(restDocumentationResultHandler.document(
						pathParameters(
								parameterWithName("pageNo").description("Page Number of portfolio list")
						),
						responseFields(
								fieldWithPath("links.rel").description("Link relation"),
								fieldWithPath("links.href").description("Hypertext reference"),
								fieldWithPath("links.hreflang").description("language of hypertext reference"),
								fieldWithPath("links.media").description("media of link"),
								fieldWithPath("links.title").description("title of link"),
								fieldWithPath("links.type").description("type of link"),
								fieldWithPath("links.description").description("Portfolio's Id"),
								fieldWithPath("content").description("포트폴리오 내용"),
//								fieldWithPath("content.portfolioId").description("Portfolio's Id"),
//								fieldWithPath("content.accountEmail").description("Email of current user"),
//								fieldWithPath("content.accountName").description("Name of current user"),
//								fieldWithPath("content.portfolioTitle").description("Title of portfolio"),
//								fieldWithPath("content.portfolioContent").description("Content of portfolio"),
//								fieldWithPath("content.portfolioCreateAt").description("Creation time stamp of portfolio"),
//								fieldWithPath("content.portfolioGiturl").description("Git url of portfolio"),
//								fieldWithPath("content.portfolioViews").description("Views of portfolio"),
//								fieldWithPath("content.portfolioThumbnailUrl").description("Thumbnail url of portfolio"),
//								fieldWithPath("content.portfolioComments").description("Comments of portfolio"),
//								fieldWithPath("content.portfolioImages").description("Images of portfolio"),
//								fieldWithPath("content.link").description("HATEOST of portfolio"),
								fieldWithPath("page.size").description("size of page"),
								fieldWithPath("page.totalElements").description("total Elements count"),
								fieldWithPath("page.totalPages").description("total Pages count"),
								fieldWithPath("page.number").description("page number")
						)
				))
		;
				
	}
	
	@Test
	public void createPortfolio() throws Exception {
		//given
				Portfolio portfolio = Portfolio.builder()
						.accountEmail("abc@email.com")
						.accountName("abc")
						.portfolioTitle("포트폴리오 제목")
						.portfolioContent("포트폴리오 내용")
						.build();
				
		
	}
	
	
	
}
