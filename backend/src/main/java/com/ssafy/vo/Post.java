package com.ssafy.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Entity
@Table(name = "posts")
@NoArgsConstructor @AllArgsConstructor
@DynamicInsert @DynamicUpdate
@Getter @Setter @ToString(exclude = "postComments")
public class Post implements Comparable<Post> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private long postId;

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

	@UpdateTimestamp
	@Column(name = "post_created_at")
	private LocalDateTime postCreatedAt;

	@ColumnDefault("0")
	@Column(name = "post_views")
	private long postViews;

	@Column(name = "post_thumbnail_url")
	private String postThumbnailUrl;

	@OneToMany(
			mappedBy = "post",
			cascade = {CascadeType.MERGE, CascadeType.REMOVE},
			orphanRemoval = true
			)
	@JsonManagedReference
	private List<PostComment> postComments = new ArrayList<PostComment>();

	public void addPostComment(PostComment postComment) {
		postComments.add(postComment);
		postComment.setPost(this);
	}

	public void removePostComment(PostComment postComment) {
		postComments.remove(postComment);
		postComment.setPost(null);
	}

	public void setPostComments(List<PostComment> newPostComments) {
		this.postComments.clear();
		this.postComments.addAll(newPostComments);
	}

	@Override
	public int compareTo(Post o) {
		return (int) (o.postViews - postViews);
	}
}
