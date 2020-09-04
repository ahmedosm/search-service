package com.se.axiom.search.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MobileDeviceCriteriaDto {

	private Long id;
	private String brand;
	private String phone;
	private String picture;
	private String announceDate;
	private Integer priceEur;
	private String sim;
	private String resolution;
	private String audioJack;
	private String gps;
	private String battery;

}
