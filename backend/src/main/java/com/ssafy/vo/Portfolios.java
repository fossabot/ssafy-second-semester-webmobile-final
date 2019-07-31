package com.ssafy.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "portfolios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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

	@CreationTimestamp
	@Column(name = "portfolio_created_at")
	private LocalDateTime portfolioCreatedAt;

	@Column(name = "portfolio_giturl")
	private String portfolioGiturl;
	
	@ColumnDefault(value = "0")
	@Column(name = "portfolio_views")
	private String portfolioViews;
	
	@Column(name = "portfolio_thumbnail")
	private String portfolioThumbnail;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "portfolio_id")
	List<PortfolioComments> portfolioComments;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "portfolio_id")
	List<PortfolioImages> portfolioImages;
	
}
