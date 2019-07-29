package com.ssafy.vo;

import java.time.LocalDateTime;

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
@Table(name = "post_comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostComments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_comment_id")
	private int postCommentId;

	@Column(name = "account_email")
	private String accountEmail;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "post_comment_content")
	private String postCommentContent;

	@Column(name = "post_comment_create_at")
	private LocalDateTime postCommentCreateAt;

	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false, updatable = false)
	private Posts posts;

	public void setPortfolios(Posts posts) {
		posts.getPostComments().remove(this);
		this.posts = posts;
		posts.getPostComments().add(this);
	}

}
