package com.ssafy.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Posts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private int postId;

	@Column(name = "account_email")
	private String accountEmail;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "post_title")
	private String postTitle;

	@Column(name = "post_content")
	private String postContent;

	@Column(name = "post_create_at")
	private LocalDateTime postCreateAt;

	@Column(name = "post_image_url")
	private String postImageUrl;

	@OneToMany(mappedBy = "posts")
	private List<PostComments> postComments = new ArrayList<PostComments>();

}
