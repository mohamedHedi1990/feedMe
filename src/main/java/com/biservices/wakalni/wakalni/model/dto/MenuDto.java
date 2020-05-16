package com.biservices.wakalni.wakalni.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.biservices.wakalni.wakalni.persistence.entities.Menu;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MenuDto {
	private Long id;

	private String name;
	private String ingredients;
	private double price;
	private boolean isInPromotion;
	private String pictureUrl;
	private Integer likeNumber;
	private Integer dontLikeNumber;
	private double score;
	private double starsAverage;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Tunis")
	private Date creationDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Tunis")
	private Date lastUpdatedDate;
	
	private MenuCategoryDto menuCategory;
	
	private List<CommentDto> comments;
	
	private Double estimatedPreparationTime;
	
	public MenuDto(Menu menu) {
		this.id=menu.getId();
		this.name=menu.getName();
		this.ingredients=menu.getIngredients();
		this.creationDate=menu.getCreationDate();
		this.dontLikeNumber=menu.getDontLikeNumber();
		this.isInPromotion=menu.isInPromotion();
		this.likeNumber=menu.getLikeNumber();
		this.price=menu.getPrice();
		this.pictureUrl=menu.getPictureUrl();
		this.score=menu.getScore();
		this.starsAverage= menu.getStarsAverage();
		this.lastUpdatedDate=menu.getLastUpdatedDate();
		if(menu.getEstimatedPreparationTime() != null)
		this.estimatedPreparationTime = menu.getEstimatedPreparationTime();
		if(menu.getComments() != null) {
			this.comments = new ArrayList<CommentDto>();
			menu.getComments().stream().forEach(comment -> this.comments.add(new CommentDto(comment)));
		}
		// this.menuCategory=new MenuCategoryDto(menu.getMenuCategory());
	}
}
