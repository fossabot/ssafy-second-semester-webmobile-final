package com.ssafy.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "portfolios")
@NoArgsConstructor @AllArgsConstructor
@DynamicInsert @DynamicUpdate
@Getter @Setter @ToString
@Builder
public class Portfolio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "portfolio_id")
	private int portfolioId;

	@NotNull
	@Column(name = "account_email")
	private String accountEmail;

	@NotNull
	@Column(name = "account_name")
	private String accountName;

	@NotNull
	@Column(name = "portfolio_title")
	private String portfolioTitle;

	@NotNull
	@Column(name = "portfolio_content")
	private String portfolioContent;

	@CreationTimestamp
	@Column(name = "portfolio_created_at")
	private LocalDateTime portfolioCreatedAt;

	@Column(name = "portfolio_giturl")
	private String portfolioGiturl;

	@ColumnDefault(value = "0")
	@Column(name = "portfolio_views")
	private int portfolioViews;

	@Column(name = "portfolio_thumbnail_url")
	private String portfolioThumbnailUrl;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "portfolio_id")
	List<PortfolioComment> portfolioComments;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "portfolio_id")
	List<PortfolioImage> portfolioImages;
	
}
