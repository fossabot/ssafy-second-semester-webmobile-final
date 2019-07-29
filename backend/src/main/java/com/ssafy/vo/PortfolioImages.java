package com.ssafy.vo;

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
@Table(name = "portfolio_images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PortfolioImages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "portfolio_image_id")
	private int portfolioImageId;

	@Column(name = "portfolio_image_url")
	private String portfolioImageUrl;

	@ManyToOne
	@JoinColumn(name = "portfolio_id", nullable = false, updatable = false)
	private Portfolios portfolios;

	public void setPortfolios(Portfolios portfolios) {
		portfolios.getPortfolioImages().remove(this);
		this.portfolios = portfolios;
		portfolios.getPortfolioImages().add(this);
	}

}
