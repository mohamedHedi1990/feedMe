package com.biservices.wakalni.wakalni.persistence.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "wakalni_menu")
public class Menu {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Africa/Tunis")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedDate;
	
	@ManyToOne
	private Restaurant restaurant;
	
	@ManyToOne
	private MenuCategory menuCategory;
	
	private Double estimatedPreparationTime;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Comment> comments;
	
	@PrePersist
	private void persistId() {
		this.lastUpdatedDate = new Date();
		if(this.creationDate == null) {
			this.creationDate = new Date();
		}
	}

}
