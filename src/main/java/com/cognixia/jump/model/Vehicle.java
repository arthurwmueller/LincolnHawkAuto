package com.cognixia.jump.model;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Vehicle {
	
	@Id
	private String id;
	
	private String make;
	
	private String model;
	
	private String trim;
	
	private String color;
	
	@Range(min=1900,max=2023)
	private Integer year;
	
	private String bodyType;
	
	private Integer mpg;
	
	private Boolean allWheelDrive;
	
	private Long price;
	
	private Integer horsepower;
	
	private Integer towing;
	
	private Boolean sunroof;
	
	private Integer capacity;
	
	private String description;
	
	private String image;
	
	private String location;
	
	private Boolean sold;
	
	private Integer customer;
	
	public Vehicle() {
		
	}

	public Vehicle(String id, String make, String model, String trim, String color,
			@Range(min = 1900, max = 2023) Integer year, String bodyType, Integer mpg, Boolean allWheelDrive,
			Long price, Integer horsepower, Integer towing, Boolean sunroof, Integer capacity, String description,String image, String location,
			Boolean sold, Integer customer) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.trim = trim;
		this.color = color;
		this.year = year;
		this.bodyType = bodyType;
		this.mpg = mpg;
		this.allWheelDrive = allWheelDrive;
		this.price = price;
		this.horsepower = horsepower;
		this.towing = towing;
		this.sunroof = sunroof;
		this.capacity=capacity;
		this.description = description;
		this.image=image;
		this.location = location;
		this.sold = sold;
		this.customer = customer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTrim() {
		return trim;
	}

	public void setTrim(String trim) {
		this.trim = trim;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public Integer getMpg() {
		return mpg;
	}

	public void setMpg(Integer mpg) {
		this.mpg = mpg;
	}

	public Boolean getAllWheelDrive() {
		return allWheelDrive;
	}

	public void setAllWheelDrive(Boolean allWheelDrive) {
		this.allWheelDrive = allWheelDrive;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getHorsepower() {
		return horsepower;
	}

	public void setHorsepower(Integer horsepower) {
		this.horsepower = horsepower;
	}

	public Integer getTowing() {
		return towing;
	}

	public void setTowing(Integer towing) {
		this.towing = towing;
	}

	public Boolean getSunroof() {
		return sunroof;
	}

	public void setSunroof(Boolean sunroof) {
		this.sunroof = sunroof;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getSold() {
		return sold;
	}

	public void setSold(Boolean sold) {
		this.sold = sold;
	}

	public Integer getCustomer() {
		return customer;
	}

	public void setCustomer(Integer customer) {
		this.customer = customer;
	}
	
	
	
}
