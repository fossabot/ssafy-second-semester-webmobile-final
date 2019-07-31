package com.ssafy.vo;

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
@Table(name = "portfolio_images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PortfolioImages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "portfolio_image_id")
	private int portfolioImageId;

	@Column(name = "portfolio_image_url")
	private String portfolioImageUrl;

	@Column(name = "portfolio_id")
	private int portfolioId;

}
