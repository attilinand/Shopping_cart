package com.eshopping.product.dashboard.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
    private Long id;
    private String name;
    private String category;
    private Double retail_price;
    private Double discounted_price;
    private Boolean availability;

    public Product() {
    	
    }

    public Product(Long id, String name, String category, Double retailPrice, Double discountedPrice, Boolean availability) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.retail_price= retailPrice;
        this.discounted_price = discountedPrice;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public Double getRetail_price() {
		return retail_price;
	}

	public void setRetail_price(Double retail_price) {
		this.retail_price = retail_price;
	}

	public Double getDiscounted_price() {
		return discounted_price;
	}

	public void setDiscounted_price(Double discounted_price) {
		this.discounted_price = discounted_price;
	}

	public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
