package com.ssafy.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.CommentServiceImpl;
import com.ssafy.vo.Comment;

@RestController
@RequestMapping(value = "/api/bears/articles/{article_category_name}/{article_no}/comments")
public class CommentRestController {

	CommentServiceImpl commentService;

	@Autowired
	public CommentRestController(CommentServiceImpl commentService) {
		this.commentService = commentService;
	}

	@GetMapping(value = "")
	public ResponseEntity<List<Comment>> selectAllCommentsByArticleNo(@PathVariable("article_no") int article_no) {
		List<Comment> list = commentService.selectAllCommentsByArticleNo(article_no);
		
		return list.size() != 0 ?
				new ResponseEntity<List<Comment>>(list, HttpStatus.OK)
				: new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
	} // end of func(selectAllCommentsByArticleNo)

	@PostMapping(value = "")
	public ResponseEntity<Boolean> insertComment(@PathVariable("article_no") int article_no,
			@RequestBody Comment comment) {
		comment.setArticle_no(article_no);
		return commentService.insertComment(comment) ? new ResponseEntity<Boolean>(true, HttpStatus.CREATED)
				: new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
	} // end of func(insertComment)
	
	@GetMapping(value = "/{comment_no}")
	public ResponseEntity<Comment> selectCommentByCommentNo(@PathVariable("comment_no") int comment_no) {
		Comment comment = commentService.selectCommentByCommentNo(comment_no);
		return comment != null ? new ResponseEntity<Comment>(comment, HttpStatus.OK)
				: new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
	} // end of func(selectCommentByCommentNo)

	@DeleteMapping(value = "/{comment_no}")
	public ResponseEntity<Boolean> deleteComment(@PathVariable("comment_no") int comment_no) {
		return commentService.deleteComment(comment_no) ? new ResponseEntity<Boolean>(true, HttpStatus.OK)
				: new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
	} // end of func(deleteComment)

	@PutMapping(value = "/{comment_no}")
	public ResponseEntity<Boolean> updateComment(@PathVariable("comment_no") int comment_no,
			@RequestBody Comment changeComment) {

		ResponseEntity<Comment> originCommentResponse = selectCommentByCommentNo(comment_no);
		if (originCommentResponse.getStatusCode().equals(HttpStatus.NOT_FOUND)) { // 데이터 없을 때,
			return ResponseEntity.notFound().build();
		}

		return commentService.updateComment(originCommentResponse.getBody(), changeComment)
				? new ResponseEntity<Boolean>(true, HttpStatus.OK)
				: new ResponseEntity<Boolean>(HttpStatus.NOT_MODIFIED);
	} // end of func(updateComment)

}
