package com.biservices.wakalni.wakalni.model.dto;

import com.biservices.wakalni.wakalni.persistence.entities.Comment;

import lombok.Data;

@Data
public class CommentDto {
private Long id;
	
	private String text;
	
	private String pictureUrl;
	
	public String fullNameUser;
	
	public String profileUserPictureUrl;
	
	public CommentDto (Comment comment) {
		this.id = comment.getId();
		this.text = comment.getText();
		if (comment.getPictureUrl() != null)
		this.pictureUrl = comment.getPictureUrl();
		this.fullNameUser = comment.getUser().getFirstName() + " " + comment.getUser().getLastName();
		this.profileUserPictureUrl = comment.getUser().getPictureUrl();
	}
	
}
