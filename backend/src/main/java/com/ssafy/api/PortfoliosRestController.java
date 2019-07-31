package com.ssafy.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.respository.PortfoliosRespository;
import com.ssafy.vo.Portfolios;
import com.ssafy.vo.PortfoliosResource;

@CrossOrigin
@RestController
@RequestMapping(value = "/portfolios")
public class PortfoliosRestController {
	
	@Autowired
	PortfoliosRespository portfoliosRespository;
;
	@GetMapping
	public HeadersBuilder<?> findAll() {
		List<Portfolios> portfolios = portfoliosRespository.findAll();
		List<PortfoliosResource> portfoliosResources = new ArrayList<>();
		
		for(int i = 0; i < portfolios.size(); i++) {
			portfoliosResources.add(new PortfoliosResource(portfolios.get(i)));
		}
		
		//HATEOAS
		ControllerLinkBuilder selfLinkBuilder = linkTo(PortfoliosRestController.class); // http://localhost:9090/api/bears/portfolios
		
		//PortfoliosResource portfoliosResource = new PortfoliosResource(portfolios);
		//portfoliosResource.add(selfLinkBuilder.slash(portfolios.getPortfolioId()).withRel("update"));
		
		
		Resources<PortfoliosResource> rss = new Resources<>(portfoliosResources);
		rss.add(selfLinkBuilder.withSelfRel().withType("GET"));
		//PagedResources<Portfolios> ps = new PagedResources<>(portfoliosOpt);
		
		return ResponseEntity.notFound();
	}
	
	@GetMapping("/{portfolio_id}")
	public ResponseEntity<?> findAll(@PathVariable int portfolio_id) {
		
		Optional<Portfolios> portfolioOpt = portfoliosRespository.findById(portfolio_id);
		
		Portfolios portfolios = portfolioOpt.get();

		//HATEOAS
		ControllerLinkBuilder selfLinkBuilder = linkTo(PortfoliosRestController.class); // http://localhost:9090/api/bears/portfolios
		PortfoliosResource portfoliosResource = new PortfoliosResource(portfolios);
		//portfoliosResource.add(selfLinkBuilder.withRel("update").withType("PUT"));
		
		
		return ResponseEntity.ok(portfoliosResource);
	}
	
	@DeleteMapping
	public void test() {
		portfoliosRespository.test();
	}
	

//	PortfoliosService portfoliosService;
//
//	@Autowired
//	public PortfoliosRestController(PortfoliosService portfoliosService) {
//		this.portfoliosService = portfoliosService;
//	}
//
//	@GetMapping(value = "")
//	public ResponseEntity<List<Article>> selectAllArticles(
//			@PathVariable("article_category_name") String article_category_name) {
//
//		List<Article> list = articleService
//				.selectAllArticles(ArticleType.valueOf(article_category_name).getArticleType());
//		return list.size() != 0 ?
//				new ResponseEntity<List<Article>>(list, HttpStatus.OK)
//				: new ResponseEntity<List<Article>>(HttpStatus.NO_CONTENT);
//	} // end of func(selectAllArticles)
//
//	@PostMapping(value = "")
//	public ResponseEntity<Boolean> insertArticle(@PathVariable("article_category_name") String article_category_name,
//			@RequestBody Article article) {
//		article.setArticle_category_id(ArticleType.valueOf(article_category_name).getArticleType());
//		return articleService.insertArticle(article) ? new ResponseEntity<Boolean>(true, HttpStatus.CREATED)
//				: new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
//	} // end of func(insertArticle)
//
//	@GetMapping(value = "/{article_no}")
//	public ResponseEntity<Article> selectArticleByArticleNo(@PathVariable("article_no") int article_no) {
//		Article article = articleService.selectArticleByArticleNo(article_no);
//		return article != null ? new ResponseEntity<Article>(article, HttpStatus.OK)
//				: new ResponseEntity<Article>(HttpStatus.NOT_FOUND);
//	} // end of func(seletArticleByArticleNo)
//
//	@DeleteMapping(value = "/{article_no}")
//	public ResponseEntity<Boolean> deleteArticle(@PathVariable("article_no") int article_no) {
//		return articleService.deleteArticle(article_no) ? new ResponseEntity<Boolean>(true, HttpStatus.OK)
//				: new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
//	} // end of func(deleteArticle)
//
//	@PutMapping(value = "/{article_no}")
//	public ResponseEntity<Boolean> updateArticle(@PathVariable("article_no") int article_no,
//			@RequestBody Article changeArticle) {
//
//		ResponseEntity<Article> originArticleResponse = selectArticleByArticleNo(article_no);
//		if (originArticleResponse.getStatusCode().equals(HttpStatus.NOT_FOUND)) { // 데이터 없을 때,
//			return ResponseEntity.notFound().build();
//		}
//
//		return articleService.updateArticle(originArticleResponse.getBody(), changeArticle)
//				? new ResponseEntity<Boolean>(true, HttpStatus.OK)
//				: new ResponseEntity<Boolean>(HttpStatus.NOT_MODIFIED);
//	} // end of func(updateArticle)

}
