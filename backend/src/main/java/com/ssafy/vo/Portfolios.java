package com.ssafy.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "portfolios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Portfolios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "portfolio_id")
	private int portfolioId;

	@Column(name = "account_email")
	private String accountEmail;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "portfolio_title")
	private String portfolioTitle;

	@Column(name = "portfolio_content")
	private String portfolioContent;

	@Column(name = "portfolio_create_at")
	private LocalDateTime portfolioCreateAt;

	@Column(name = "portfolio_giturl")
	private String portfolioGitUrl;

	@OneToMany(mappedBy = "portfolios", cascade = CascadeType.ALL)
	private List<PortfolioComments> portfolioComments = new ArrayList<PortfolioComments>();

	@OneToMany(mappedBy = "portfolios", cascade = CascadeType.ALL)
	private List<PortfolioImages> portfolioImages = new ArrayList<PortfolioImages>();

}
