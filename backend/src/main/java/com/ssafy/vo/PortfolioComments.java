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
import lombok.ToString;

@Entity
@Table(name = "portfolio_comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PortfolioComments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "portfolio_comment_id")
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

}
