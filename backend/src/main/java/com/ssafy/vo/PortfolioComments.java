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
	private int portfolio_comment_id;

	@Column(name = "account_email")
	private String account_email;

	@Column(name = "account_name")
	private String account_name;

	@Column(name = "portfolio_comment_content")
	private String portfolio_comment_content;

	@Column(name = "portfolio_comment_create_at")
	private LocalDateTime portfolio_comment_create_at;

	@Column(name = "portfolio_id")
	private int portfolio_id;

}
