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
	public Page<Posts> findAllPosts(@PathVariable int page_no) {
		Pageable pageable = PageRequest.of(page_no - 1, 6, Sort.by("post_create_at"));
		return postsService.findAllPosts(pageable);
	}

	// -- 삽입
	@PostMapping(value = "")
	public Posts createPost(@RequestBody Posts post) {
		// boolean으로 바뀔 예정?? 어떻게 하지?? // 그냥 만들어진 post 던지기
		Posts createdPost = postsService.savePost(post);

		if (createdPost == null) {
			// 임시 반환
			return null;
		}

		return createdPost;
	}

	@GetMapping(value = "/{post_id}")
	public Posts findPostById(@PathVariable int post_id) {
		Optional<Posts> optional = postsService.findPostById(post_id);
		if (!optional.isPresent()) {
			// 임시 반환
			return null;
		}
		// 존재할시
		return optional.get();
	}

	// -- 수정
	@PutMapping(value = "/{post_id}")
	public Posts updatePost(@PathVariable int post_id, @RequestBody Posts post) {

		// 수정을 요청하는 아이디와 post의 아이디가 다른 경우
		if (post_id != post.getPost_id()) {
			return null;
		}

		Optional<Posts> optional = postsService.findPostById(post_id);
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

	@DeleteMapping(value = "/{post_id}")
	public boolean deletePostById(@PathVariable int post_id) {
		boolean isDeleted = postsService.deletePostById(post_id); // 성공하면 true

		if (isDeleted) {
			return true;
		} else {
			return false;
		}
	}

}
