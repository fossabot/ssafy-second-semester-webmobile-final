package com.ssafy.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.PostsService;
import com.ssafy.vo.Posts;

@CrossOrigin
@RestController
@RequestMapping(value = "/posts")
public class PostsRestController {

	@Autowired
	PostsService postsService;

	@GetMapping(value = "/page/{page_no}")
	public Page<Posts> findAllPosts(@PathVariable int pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 6, Sort.by("postCreatedAt"));
		return postsService.findAllPosts(pageable);
	}

	// -- 삽입
	@PostMapping(value = "")
	public Posts createPost(@RequestBody Posts post) {
		// boolean으로 바뀔 예정?? 어떻게 하지?? // 그냥 만들어진 post 던지기
		Posts createdPost = postsService.savePost(post);
		
		if (createdPost == null) {
			return null;
		}

		return createdPost;
	}

	@GetMapping(value = "/{postId}")
	public Posts findPostById(@PathVariable int postId) {
		Optional<Posts> optional = postsService.findPostById(postId);
		if (!optional.isPresent()) {
			// 임시 반환
			return null;
		}
		// 존재할시
		return optional.get();
	}

	// -- 수정
	@PutMapping(value = "/{postId}")
	public Posts updatePost(@PathVariable int postId, @RequestBody Posts post) {

		// 수정을 요청하는 아이디와 post의 아이디가 다른 경우
		if (postId != post.getPostId()) {
			return null;
		}

		Optional<Posts> optional = postsService.findPostById(postId);
		if (!optional.isPresent()) {
			// 수정하려는 데이터가 존재하지 않음
			// 우선 null이지만, 에러 핸들링 해야함
			return null;
		}

		Posts updatedPost = postsService.savePost(post);

		if (updatedPost == null) {
			// 수정하려는 데이터가 존재하지만
			// 수정에 실패함
			return null;
		}
		// 성공시 뱉어줌
		return updatedPost;
	}

	@DeleteMapping(value = "/{postId}")
	public boolean deletePostById(@PathVariable int postId) {
		boolean isDelected = postsService.deletePostById(postId); // 성공하면 true

		if (isDelected) {
			return true;
		} else {
			return false;
		}
	}

}
