package com.ssafy.service;

import java.util.Optional;

import com.ssafy.vo.PostComments;

public interface PostCommentsService {
	public int countPostComments(); // 전체 댓글 수
	public Optional<PostComments> findPostCommentById(int postId); // 게시글 읽기
	public PostComments savePostComment(PostComments postComment); // 게시글 수정, 삽입
	public boolean deletePostCommentById(int postCommentId); // 댓글 삭제
}
