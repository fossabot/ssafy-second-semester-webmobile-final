package com.ssafy.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Entity
@Table(name = "portfolio_images")
@NoArgsConstructor @AllArgsConstructor
@DynamicInsert @DynamicUpdate
@Getter @Setter @ToString
public class PortfolioImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "portfolio_image_id")
	private int portfolioImageId;

	@NotNull
	@Column(name = "portfolio_image_url")
	private String portfolioImageUrl;

	@NotNull
	@Column(name = "portfolio_id")
	private int portfolioId;

}
