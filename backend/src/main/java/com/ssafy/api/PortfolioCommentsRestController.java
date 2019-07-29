package com.ssafy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.ssafy.service.PortfolioCommentsService;
import com.ssafy.service.PortfoliosService;
import com.ssafy.vo.Comment;
import com.ssafy.vo.Portfolios;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/bears/portfolios/{article_no}/comments")
public class PortfolioCommentsRestController {

	PortfolioCommentsService portfolioCommentsService;

	@Autowired
	public PortfolioCommentsRestController(PortfolioCommentsService portfolioCommentsService) {
		this.portfolioCommentsService = portfolioCommentsService;
	}

	@GetMapping(value = "")
	public ResponseEntity<List<Portfolios>> selectAllCommentsByArticleNo(@PathVariable("article_no") int article_no) {
		List<Portfolios> list = portfoliosService.selectAllCommentsByArticleNo(article_no);

		return list.size() != 0 ? new ResponseEntity<List<Comment>>(list, HttpStatus.OK)
				: new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
	} // end of func(selectAllCommentsByArticleNo)

	@PostMapping(value = "")
	public ResponseEntity<Boolean> insertComment(@PathVariable("article_no") int article_no,
			@RequestBody Comment comment) {
	} // end of func(insertComment)

	@GetMapping(value = "/{comment_no}")
	public ResponseEntity<Comment> selectCommentByCommentNo(@PathVariable("comment_no") int comment_no) {
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
