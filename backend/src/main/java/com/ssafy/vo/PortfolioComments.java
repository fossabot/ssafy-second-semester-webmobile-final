package com.ssafy.vo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "portfolio_comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

	@Column(name = "portfolio_comment_create_at")
	private LocalDateTime portfolioCommentCreateAt;

	@ManyToOne
	@JoinColumn(name = "portfolio_id", nullable = false, updatable = false)
	private Portfolios portfolios;

	public void setPortfolios(Portfolios portfolios) {
		portfolios.getPortfolioComments().remove(this);
		this.portfolios = portfolios;
		portfolios.getPortfolioComments().add(this);
	}

}
