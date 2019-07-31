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
	private int portfolio_id;

	@Column(name = "account_email")
	private String account_email;

	@Column(name = "account_name")
	private String account_name;

	@Column(name = "portfolio_title")
	private String portfolio_title;

	@Column(name = "portfolio_content")
	private String portfolio_content;

	@CreationTimestamp
	@Column(name = "portfolio_create_at")
	private LocalDateTime portfolio_create_at;

	@Column(name = "portfolio_giturl")
	private String portfolio_giturl;
	
	@ColumnDefault(value = "0")
	@Column(name = "portfolio_views")
	private String portfolio_views;
	
	@Column(name = "portfolio_thumbnail")
	private String portfolio_thumbnail;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "portfolio_id")
	List<PortfolioComments> portfolio_comments;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "portfolio_id")
	List<PortfolioImages> portfolio_images;
	
}
