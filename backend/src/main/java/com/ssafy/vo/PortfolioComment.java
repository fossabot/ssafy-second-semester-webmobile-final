package com.ssafy.vo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
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
@Table(name = "portfolio_comments")
@NoArgsConstructor @AllArgsConstructor
@DynamicInsert @DynamicUpdate
@Getter @Setter @ToString 
public class PortfolioComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "portfolio_comment_id")
	private int portfolioCommentId;

	@NotNull
	@Column(name = "account_email")
	private String accountEmail;

	@NotNull
	@Column(name = "account_name")
	private String accountName;

	@NotNull
	@Column(name = "portfolio_comment_content")
	private String portfolioCommentContent;

	@CreationTimestamp
	@Column(name = "portfolio_comment_created_at")
	private LocalDateTime portfolioCommentCreatedAt;

	@NotNull
	@Column(name = "portfolio_id")
	private int portfolioId;
	
}
