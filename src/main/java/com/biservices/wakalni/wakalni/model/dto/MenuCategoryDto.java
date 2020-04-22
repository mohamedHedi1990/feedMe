package com.biservices.wakalni.wakalni.model.dto;

import java.util.Date;

import com.biservices.wakalni.wakalni.persistence.entities.MenuCategory;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MenuCategoryDto {
	private Long id;

	private String name;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Tunis")
	private Date creationDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Tunis")
	private Date lastUpdatedDate;
	
	public MenuCategoryDto (MenuCategory menuCategory) {
		this.id = menuCategory.getId();
		this.name = menuCategory.getName();
		this.creationDate = menuCategory.getCreationDate();
		this.lastUpdatedDate = menuCategory.getLastUpdatedDate();
	}
}
