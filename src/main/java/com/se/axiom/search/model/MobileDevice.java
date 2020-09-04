package com.se.axiom.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "MobileDevice")
@ToString
@JsonIgnoreProperties
public class MobileDevice {
	@Id
	private Long id;
	private String brand;
	private String phone;
	private String picture;
	private Release release;
	private String sim;
	private String resolution;
	private Hardware hardware;

}
