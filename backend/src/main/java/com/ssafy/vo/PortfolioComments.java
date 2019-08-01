package com.ssafy.vo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< HEAD
=======
import lombok.ToString;
>>>>>>> 5603c1c04cb2b75e2d870b8c999a75acd8a98bf8

@Entity
@Table(name = "portfolio_comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
<<<<<<< HEAD
=======
@ToString
>>>>>>> 5603c1c04cb2b75e2d870b8c999a75acd8a98bf8
public class PortfolioComments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "portfolio_comment_id")
<<<<<<< HEAD
	private int portfolio_comment_id;

	@Column(name = "account_email")
	private String account_email;

	@Column(name = "account_name")
	private String account_name;

	@Column(name = "portfolio_comment_content")
	private String portfolio_comment_content;

	@Column(name = "portfolio_comment_create_at")
	private LocalDateTime portfolio_comment_create_at;

	@Column(name = "portfolio_id")
	private int portfolio_id;
=======
	private int portfolioCommentId;

	@Column(name = "account_email")
	private String accountEmail;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "portfolio_comment_content")
	private String portfolioCommentContent;

	@Column(name = "portfolio_comment_created_at")
	private LocalDateTime portfolioCommentCreatedAt;

	@Column(name = "portfolio_id")
	private int portfolioId;
>>>>>>> 5603c1c04cb2b75e2d870b8c999a75acd8a98bf8

}
