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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "posts")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
@DynamicInsert @DynamicUpdate
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private int postId;

	@NotNull
	@Column(name = "account_email")
	private String accountEmail;

	@NotNull
	@Column(name = "account_name")
	private String accountName;

	@NotNull
	@Column(name = "post_title")
	private String postTitle;

	@NotNull
	@Column(name = "post_content")
	private String postContent;

	@CreationTimestamp
	@Column(name = "post_created_at")
	private LocalDateTime postCreatedAt;

	@ColumnDefault("0")
	@Column(name = "post_views")
	private int postViews;

	@Column(name = "post_thumbnail_url")
	private String postThumbnailUrl;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	private List<PostComment> postComments;
	
}
