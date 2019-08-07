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
import com.ssafy.respository.PostCommentRepository;
import com.ssafy.vo.Post;
import com.ssafy.vo.PostComment;

public class PostCommentRestControllerTests extends BaseControllerTests {

	@Autowired
	PostCommentRepository postCommentRepository;
	
	@Before
	public void setUp() {
		postRepository.deleteAll();
		postCommentRepository.deleteAll();
	}
	
	@Test
	public void createPostComment() throws Exception {
		//given
		Post testPost = createTestPost();
		testPost = postRepository.save(testPost);
		PostComment testData = createTestPostComment();
		
		//when & then
		mockMvc.perform(RestDocumentationRequestBuilders.post("/posts/{postId}/comments", testPost.getPostId())
					.header("accountEmail", "test@email.com")
					.header("accountAuth", 2)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content(objectMapper.writeValueAsString(testData))
				)
			.andDo(print())
			.andExpect(status().isCreated())
			.andDo(document(
					"create-post-comment",
					pathParameters(
							parameterWithName("postId").description("댓글을 쓸 포스트 Id")
					),
					requestFields(
							fieldWithPath("postCommentId").description("포스트 댓글 Id (Auto increase)"),
							fieldWithPath("accountEmail").description("작성자 Email"),
							fieldWithPath("accountName").description("작성자 이름"),
							fieldWithPath("postCommentContent").description("댓글 내용")
					),
					responseFields(
							fieldWithPath("links[0].rel").description("포스트 댓글 HATEOAS 관계명"),
							fieldWithPath("links[0].href").description("포스트 댓글 HATEOAS URI"),
							fieldWithPath("postCommentId").description("포스트 댓글 Id"),
							fieldWithPath("accountEmail").description("작성자 Email"),
							fieldWithPath("accountName").description("작성자 이름"),
							fieldWithPath("postCommentContent").description("댓글 내용"),
							fieldWithPath("postCommentCreatedAt").description("생성 시간")
					)
			))
		;
	}
	
	@Test
	public void updatePostComment() throws Exception {
		//given
		Post testPost = createTestPost();
		testPost = postRepository.save(testPost);
		PostComment testData = createTestPostComment();
		testData.setPost(testPost);
		testData.setPostCommentContent("내용 수정");
		testData = postCommentRepository.save(testData);
		
		//when & then
		mockMvc.perform(RestDocumentationRequestBuilders.put("/posts/{postId}/comments/{postCommentId}",
				testPost.getPostId(), testData.getPostCommentId())
					.header("accountEmail", "test@email.com")
					.header("accountAuth", 2)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content(objectMapper.writeValueAsString(testData))
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andDo(document(
					"update-post-comment",
					pathParameters(
							parameterWithName("postId").description("댓글을 쓴 포스트 Id"),
							parameterWithName("postCommentId").description("수정할 포스트 댓글 Id")
					),
					requestFields(
							fieldWithPath("postCommentId").description("포스트 댓글 Id"),
							fieldWithPath("accountEmail").description("작성자 Email"),
							fieldWithPath("accountName").description("작성자 이름"),
							fieldWithPath("postCommentContent").description("댓글 내용"),
							fieldWithPath("postCommentCreatedAt").description("생성 시간")
					),
					responseFields(
							fieldWithPath("links[0].rel").description("포스트 댓글 HATEOAS 관계명"),
							fieldWithPath("links[0].href").description("포스트 댓글 HATEOAS URI"),
							fieldWithPath("postCommentId").description("포스트 댓글 Id"),
							fieldWithPath("accountEmail").description("작성자 Email"),
							fieldWithPath("accountName").description("작성자 이름"),
							fieldWithPath("postCommentContent").description("댓글 내용"),
							fieldWithPath("postCommentCreatedAt").description("생성 시간")
					)
			))
		;
	}
	
	@Test
	public void deletePostComment() throws Exception {
		//given
		Post testPost = createTestPost();
		testPost = postRepository.save(testPost);
		PostComment testData = createTestPostComment();
		testData.setPost(testPost);
		testData = postCommentRepository.save(testData);
		
		//when & then
		mockMvc.perform(RestDocumentationRequestBuilders.delete("/posts/{postId}/comments/{postCommentId}",
				testPost.getPostId(), testData.getPostCommentId())
					.header("accountEmail", "test@email.com")
					.header("accountAuth", 2)
				)
			.andDo(print())
			.andExpect(status().isNoContent())
			.andDo(document(
					"delete-post-comment",
					pathParameters(
							parameterWithName("postId").description("댓글을 쓴 포스트 Id"),
							parameterWithName("postCommentId").description("삭제할 포스트 댓글 Id")
					)
			))
		;
	}
	
	private PostComment createTestPostComment() {
        return PostComment.builder()
                .accountEmail("test@email.com")
                .accountName("test name")
                .postCommentContent("댓글 내용")
        		.build();
    }
	
}
