package com.ssafy.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.respository.PortfoliosCommentsRepository;
import com.ssafy.vo.PortfolioComments;
import com.ssafy.vo.PortfolioCommentsResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/portfolios/{portfolioId}/comments")
public class PortfolioCommentsRestController {
	
	@Autowired
	PortfoliosCommentsRepository portfolioCommentsRepository;
	
	@GetMapping
	public ResponseEntity<?> findAll(@PathVariable int portfolioId) {
		List<PortfolioComments> portfolios = portfolioCommentsRepository.findByPortfolioId(portfolioId);
		List<PortfolioCommentsResource> portfolioCommentsResources = new ArrayList<>();
		
		for(int i = 0; i < portfolios.size(); i++) {
			portfolioCommentsResources.add(new PortfolioCommentsResource(portfolios.get(i)));
		}
		
		//HATEOAS
		ControllerLinkBuilder selfLinkBuilder = linkTo(PortfoliosRestController.class); // http://localhost:9090/api/bears/portfolios
		
		//PortfoliosResource portfoliosResource = new PortfoliosResource(portfolios);
		//portfoliosResource.add(selfLinkBuilder.slash(portfolios.getPortfolioId()).withRel("update"));
		
		
		Resources<PortfolioCommentsResource> rss = new Resources<>(portfolioCommentsResources);
		rss.add(selfLinkBuilder.withSelfRel().withType("GET"));
		//PagedResources<Portfolios> ps = new PagedResources<>(portfoliosOpt);
		
		return ResponseEntity.ok(rss);
	}

//	PortfolioCommentsService portfolioCommentsService;
//
//	@Autowired
//	public PortfolioCommentsRestController(PortfolioCommentsService portfolioCommentsService) {
//		this.portfolioCommentsService = portfolioCommentsService;
//	}
//
//	@GetMapping(value = "")
//	public ResponseEntity<List<Portfolios>> selectAllCommentsByArticleNo(@PathVariable("article_no") int article_no) {
//		List<Portfolios> list = portfoliosService.selectAllCommentsByArticleNo(article_no);
//
//		return list.size() != 0 ? new ResponseEntity<List<Comment>>(list, HttpStatus.OK)
//				: new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
//	} // end of func(selectAllCommentsByArticleNo)
//
//	@PostMapping(value = "")
//	public ResponseEntity<Boolean> insertComment(@PathVariable("article_no") int article_no,
//			@RequestBody Comment comment) {
//	} // end of func(insertComment)
//
//	@GetMapping(value = "/{comment_no}")
//	public ResponseEntity<Comment> selectCommentByCommentNo(@PathVariable("comment_no") int comment_no) {
//	} // end of func(selectCommentByCommentNo)
//
//	@DeleteMapping(value = "/{comment_no}")
//	public ResponseEntity<Boolean> deleteComment(@PathVariable("comment_no") int comment_no) {
//		return commentService.deleteComment(comment_no) ? new ResponseEntity<Boolean>(true, HttpStatus.OK)
//				: new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
//	} // end of func(deleteComment)
//
//	@PutMapping(value = "/{comment_no}")
//	public ResponseEntity<Boolean> updateComment(@PathVariable("comment_no") int comment_no,
//			@RequestBody Comment changeComment) {
//
//		ResponseEntity<Comment> originCommentResponse = selectCommentByCommentNo(comment_no);
//		if (originCommentResponse.getStatusCode().equals(HttpStatus.NOT_FOUND)) { // 데이터 없을 때,
//			return ResponseEntity.notFound().build();
//		}
//
//		return commentService.updateComment(originCommentResponse.getBody(), changeComment)
//				? new ResponseEntity<Boolean>(true, HttpStatus.OK)
//				: new ResponseEntity<Boolean>(HttpStatus.NOT_MODIFIED);
//	} // end of func(updateComment)

}
