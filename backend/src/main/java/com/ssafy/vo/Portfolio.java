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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Entity
@Table(name = "portfolios")
@NoArgsConstructor @AllArgsConstructor
@DynamicInsert @DynamicUpdate
@Getter @Setter @ToString(exclude = "portfolioComments")
public class Portfolio implements Comparable<Portfolio> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "portfolio_id")
	private long portfolioId;

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

	@UpdateTimestamp
	@Column(name = "portfolio_created_at")
	private LocalDateTime portfolioCreatedAt;

	@Column(name = "portfolio_giturl")
	private String portfolioGiturl;

	@ColumnDefault(value = "0")
	@Column(name = "portfolio_views")
	private long portfolioViews;

	@Column(name = "portfolio_thumbnail_url")
	private String portfolioThumbnailUrl;

	@OneToMany(
			mappedBy = "portfolio",
			cascade = {CascadeType.MERGE, CascadeType.REMOVE},
			orphanRemoval = true
			)
	@JsonManagedReference
	private List<PortfolioComment> portfolioComments = new ArrayList<PortfolioComment>();

	public void addPortfolioComment(PortfolioComment portfolioComment) {
		portfolioComments.add(portfolioComment);
		portfolioComment.setPortfolio(this);
	}

	public void removePortfolioComment(PortfolioComment portfolioComment) {
		portfolioComments.remove(portfolioComment);
		portfolioComment.setPortfolio(null);
	}

	public void setPortfolioComments(List<PortfolioComment> newPortfolioComments) {
		this.portfolioComments.clear();
		this.portfolioComments.addAll(newPortfolioComments);
	}

	@Override
	public int compareTo(Portfolio o) {
		return (int) (o.portfolioViews - portfolioViews);
	}
}
