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
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Posts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private int post_id;

	@Column(name = "account_email")
	private String account_email;

	@Column(name = "account_name")
	private String account_name;

	@Column(name = "post_title")
	private String post_title;

	@Column(name = "post_content")
	private String post_content;

	@CreationTimestamp
	@Column(name = "post_create_at")
	private LocalDateTime post_create_at;

	@ColumnDefault("0")
	@Column(name = "post_views")
	private int post_views;

	@Column(name = "post_image_url")
	private String post_image_url;

	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="post_id")
	private List<PostComments> post_comments;

}
