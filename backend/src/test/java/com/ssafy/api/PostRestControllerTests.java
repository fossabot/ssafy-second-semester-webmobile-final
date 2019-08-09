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
import com.ssafy.vo.Post;

public class PostRestControllerTests extends BaseControllerTests {
	
	@Before
	public void setUp() {
		postRepository.deleteAll();
	}
	
	@Test
	public void createPost() throws Exception {
		//given
		Post testData = createTestPost();
		
		//when & then
		mockMvc.perform(post("/posts")
					.header("accountEmail", "test@email.com")
					.header("accountAuth", 2)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content(objectMapper.writeValueAsString(testData))
				)
			.andDo(print())
			.andExpect(status().isCreated())
			.andDo(document(
					"create-post",
					requestFields(
							fieldWithPath("postId").description("포스트 Id (Auto increase)"),
							fieldWithPath("accountEmail").description("작성자 Email"),
							fieldWithPath("accountName").description("작성자 이름"),
							fieldWithPath("postTitle").description("제목"),
							fieldWithPath("postContent").description("내용"),
							fieldWithPath("postViews").description("조회수 (0으로 자동 초기화)"),
							fieldWithPath("postThumbnailUrl").description("썸네일 이미지 경로")
					),
					responseFields(
							fieldWithPath("links[0].rel").description("포스트 HATEOAS 관계명"),
							fieldWithPath("links[0].href").description("포스트 HATEOAS URI"),
							fieldWithPath("postId").description("포스트 Id"),
							fieldWithPath("accountEmail").description("작성자 Email"),
							fieldWithPath("accountName").description("작성자 이름"),
							fieldWithPath("postTitle").description("제목"),
							fieldWithPath("postContent").description("내용"),
							fieldWithPath("postCreatedAt").description("생성 시간"),
							fieldWithPath("postViews").description("조회수"),
							fieldWithPath("postThumbnailUrl").description("썸네일 이미지 경로"),
							fieldWithPath("postComments").description("포스트 댓글 정보")
					)
			))
		;
	}
	
	@Test
	public void updatePost() throws Exception {
		//given
		Post testData = createTestPost();
		testData = postRepository.save(testData);
		testData.setPostTitle("update title");
		testData.setPostContent("update content");
		
		//when & then
		mockMvc.perform(RestDocumentationRequestBuilders.put("/posts/{postId}", testData.getPostId())
					.header("accountEmail", "test@email.com")
					.header("accountAuth", 2)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content(objectMapper.writeValueAsString(testData))
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andDo(document(
				"update-post",
				pathParameters(
						parameterWithName("postId").description("수정할 포스트 Id")
				),
				requestFields(
						fieldWithPath("postId").description("수정할 포스트 Id"),
						fieldWithPath("accountEmail").description("작성자 Email"),
						fieldWithPath("accountName").description("작성자 이름"),
						fieldWithPath("postTitle").description("제목"),
						fieldWithPath("postContent").description("내용"),
						fieldWithPath("postCreatedAt").description("생성 시간"),
						fieldWithPath("postViews").description("조회수"),
						fieldWithPath("postThumbnailUrl").description("썸네일 이미지 경로")
				),
				responseFields(
						fieldWithPath("links[0].rel").description("포스트 HATEOAS 관계명"),
						fieldWithPath("links[0].href").description("포스트 HATEOAS URI"),
						fieldWithPath("postId").description("포스트 Id"),
						fieldWithPath("accountEmail").description("작성자 Email"),
						fieldWithPath("accountName").description("작성자 이름"),
						fieldWithPath("postTitle").description("제목"),
						fieldWithPath("postContent").description("내용"),
						fieldWithPath("postCreatedAt").description("생성 시간"),
						fieldWithPath("postViews").description("조회수"),
						fieldWithPath("postThumbnailUrl").description("썸네일 이미지 경로"),
						fieldWithPath("postComments").description("포스트 댓글 정보")
				)
			))
		;
	}
	
	@Test
	public void findAllPosts() throws Exception {
		//given
		Post testData = createTestPost();
		testData = postRepository.save(testData);
		
		//when & then
		mockMvc.perform(RestDocumentationRequestBuilders.get("/posts/pages/{pageNo}", 1L))
				.andDo(print())
				.andExpect(status().isOk())
				.andDo(document(
						"find-all-posts",
						pathParameters(
								parameterWithName("pageNo").description("요청할 페이지 숫자")
						),
						responseFields(
								fieldWithPath("links[0].rel").description("포스트 페이지 HATEOAS 관계명"),
								fieldWithPath("links[0].href").description("포스트 페이지 HATEOAS URI"),
								fieldWithPath("content[0].postId").description("포스트 Id"),
								fieldWithPath("content[0].accountEmail").description("작성자 Email"),
								fieldWithPath("content[0].accountName").description("작성자 이름"),
								fieldWithPath("content[0].postTitle").description("제목"),
								fieldWithPath("content[0].postContent").description("내용"),
								fieldWithPath("content[0].postCreatedAt").description("생성 시간"),
								fieldWithPath("content[0].postViews").description("조회수"),
								fieldWithPath("content[0].postThumbnailUrl").description("썸네일 이미지 경로"),
								fieldWithPath("content[0].postComments").description("댓글 정보"),
								fieldWithPath("content[0].links[0].rel").description("각 포스트의 HATEOAS 관계명"),
								fieldWithPath("content[0].links[0].href").description("각 포스트의 HATEOAS URI"),
								fieldWithPath("page.size").description("한 페이지 내에 들어가는 포스트 수"),
								fieldWithPath("page.totalElements").description("총 포스트 수"),
								fieldWithPath("page.totalPages").description("총 페이지 수"),
								fieldWithPath("page.number").description("현재 페이지")
						)
				))
		;
	}
	
	@Test
	public void findPostByPostId() throws Exception {
		//given
		Post testData = createTestPost();
		testData = postRepository.save(testData);
		
		//when & then
		mockMvc.perform(RestDocumentationRequestBuilders.get("/posts/{postId}", testData.getPostId()))
				.andDo(print())
				.andExpect(status().isOk())
				.andDo(document(
						"find-post-by-post-id",
						pathParameters(
								parameterWithName("postId").description("요청할 포스트 Id")
						),
						responseFields(
								fieldWithPath("links[0].rel").description("포스트 HATEOAS 관계명"),
								fieldWithPath("links[0].href").description("포스트 HATEOAS URI"),
								fieldWithPath("postId").description("포스트 Id"),
								fieldWithPath("accountEmail").description("작성자 Email"),
								fieldWithPath("accountName").description("작성자 이름"),
								fieldWithPath("postTitle").description("제목"),
								fieldWithPath("postContent").description("내용"),
								fieldWithPath("postCreatedAt").description("생성 시간"),
								fieldWithPath("postViews").description("조회수"),
								fieldWithPath("postThumbnailUrl").description("썸네일 이미지 경로"),
								fieldWithPath("postComments").description("댓글 정보")
						)
				))
		;
	}
	
	@Test
	public void deletePost() throws Exception {
		//given
		Post testData = createTestPost();
		testData = postRepository.save(testData);
		
		//when & then
		mockMvc.perform(RestDocumentationRequestBuilders.delete("/posts/{postId}", testData.getPostId())
						.header("accountEmail", "test@email.com")
						.header("accountAuth", 1)
					)
				.andDo(print())
				.andExpect(status().isNoContent())
				.andDo(document(
						"delete-post",
						pathParameters(
								parameterWithName("postId").description("삭제할 포스트 Id")
						)
				))
		;
	}
	
	@Test
	public void countPosts() throws Exception {
		//given
		Post testData = createTestPost();
		testData = postRepository.save(testData);
		
		//when & then
		mockMvc.perform(get("/posts/count"))
			.andDo(print())
			.andExpect(status().isOk())
			.andDo(document(
					"count-posts",
					responseFields(
							fieldWithPath("countPosts").description("포스트 총 개수")
					)
			))
		;
	}
	
}
