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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "post_comments")
@NoArgsConstructor @AllArgsConstructor
@DynamicInsert @DynamicUpdate
@Getter @Setter @ToString
public class PostComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_comment_id")
	private int postCommentId;

	@NotNull
	@Column(name = "account_email")
	private String accountEmail;

	@NotNull
	@Column(name = "account_name")
	private String accountName;

	@NotNull
	@Column(name = "post_comment_content")
	private String postCommentContent;

	@CreationTimestamp
	@Column(name = "post_comment_created_at")
	private LocalDateTime postCommentCreatedAt;

	@NotNull
	@Column(name = "post_id")
	private int postId;
	
}
