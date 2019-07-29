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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.auth.Auth;
import com.ssafy.common.ArticleType;
import com.ssafy.common.RoleType;
import com.ssafy.service.ArticleServiceImpl;
import com.ssafy.service.PortfoliosService;
import com.ssafy.vo.Article;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/bears/portfolios")
public class PortfoliosRestController {

	PortfoliosService portfoliosService;

	@Autowired
	public PortfoliosRestController(PortfoliosService portfoliosService) {
		this.portfoliosService = portfoliosService;
	}

	@GetMapping(value = "")
	public ResponseEntity<List<Article>> selectAllArticles(
			@PathVariable("article_category_name") String article_category_name) {

		List<Article> list = articleService
				.selectAllArticles(ArticleType.valueOf(article_category_name).getArticleType());
		return list.size() != 0 ?
				new ResponseEntity<List<Article>>(list, HttpStatus.OK)
				: new ResponseEntity<List<Article>>(HttpStatus.NO_CONTENT);
	} // end of func(selectAllArticles)

	@PostMapping(value = "")
	public ResponseEntity<Boolean> insertArticle(@PathVariable("article_category_name") String article_category_name,
			@RequestBody Article article) {
		article.setArticle_category_id(ArticleType.valueOf(article_category_name).getArticleType());
		return articleService.insertArticle(article) ? new ResponseEntity<Boolean>(true, HttpStatus.CREATED)
				: new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
	} // end of func(insertArticle)

	@GetMapping(value = "/{article_no}")
	public ResponseEntity<Article> selectArticleByArticleNo(@PathVariable("article_no") int article_no) {
		Article article = articleService.selectArticleByArticleNo(article_no);
		return article != null ? new ResponseEntity<Article>(article, HttpStatus.OK)
				: new ResponseEntity<Article>(HttpStatus.NOT_FOUND);
	} // end of func(seletArticleByArticleNo)

	@DeleteMapping(value = "/{article_no}")
	public ResponseEntity<Boolean> deleteArticle(@PathVariable("article_no") int article_no) {
		return articleService.deleteArticle(article_no) ? new ResponseEntity<Boolean>(true, HttpStatus.OK)
				: new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
	} // end of func(deleteArticle)

	@PutMapping(value = "/{article_no}")
	public ResponseEntity<Boolean> updateArticle(@PathVariable("article_no") int article_no,
			@RequestBody Article changeArticle) {

		ResponseEntity<Article> originArticleResponse = selectArticleByArticleNo(article_no);
		if (originArticleResponse.getStatusCode().equals(HttpStatus.NOT_FOUND)) { // 데이터 없을 때,
			return ResponseEntity.notFound().build();
		}

		return articleService.updateArticle(originArticleResponse.getBody(), changeArticle)
				? new ResponseEntity<Boolean>(true, HttpStatus.OK)
				: new ResponseEntity<Boolean>(HttpStatus.NOT_MODIFIED);
	} // end of func(updateArticle)

}
