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
	
	private String location;
	
	private Boolean sold;
	
	private Integer customer;
	
}
