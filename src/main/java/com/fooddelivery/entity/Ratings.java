package com.fooddelivery.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name  = "Ratings")
public class Ratings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RATING_ID")
	private int rating_id;
	
	@Column(name = "RATING")
	private int rating;
	
	@Column(name = " REVIEW")
	private String review;
	
	@ManyToOne
	@JoinColumn(name = " RESTAURANT_ID")
	private Restaurant restaurant;
	

	@ManyToOne
	@JoinColumn(name = " ORDER_ID")
	private Order order;
	
	

	public int getRating_id() {
		return rating_id;
	}


	public void setRating_id(int rating_id) {
		this.rating_id = rating_id;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public String getReview() {
		return review;
	}


	public void setReview(String review) {
		this.review = review;
	}


	public Restaurant getRestaurant() {
		return restaurant;
	}


	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}
	
	


	@Override
	public String toString() {
		return "Ratings [rating_id=" + rating_id + ", rating=" + rating + ", review=" + review + "]";
	}
	
}
