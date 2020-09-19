package com.se.axiom.search.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.se.axiom.search.custom.exception.MobileSerachException;
import com.se.axiom.search.dto.MobileDeviceCriteriaDto;
import com.se.axiom.search.model.MobileDevice;
import com.se.axiom.search.service.MobileDeviceSearchService;
import com.se.axiom.search.service.MobileDeviceSearchServiceImpl;

import io.swagger.v3.oas.annotations.Parameter;
import reactor.core.publisher.Flux;

@RequestMapping("/mobile")

@RestController
public class MobileSearchController {
	private static final Logger LOG = LoggerFactory.getLogger(MobileSearchController.class);
	
	private final MobileDeviceSearchService mobileDeviceSearchService;
	
	public MobileSearchController(MobileDeviceSearchService mobileDeviceSearchService) {
		this.mobileDeviceSearchService=mobileDeviceSearchService;
	}
	
	@GetMapping("/search")
	public Flux<MobileDevice> mobileDeviceSearch(
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) String brand,
			@RequestParam(required = false) String phone, 
			@RequestParam(required = false) String picture,
			@RequestParam(required = false) String announceDate,
			@RequestParam(required = false) Long priceEur,
			@RequestParam(required = false) String sim,
			@RequestParam(required = false) String resolution,			
			@RequestParam(required = false) String audioJack, 
			@RequestParam(required = false) String gps,
			@RequestParam(required = false) String battery,
			@Parameter(hidden = true)
			MobileDeviceCriteriaDto mobileDeviceCriteriaDto) throws MobileSerachException {	
		LOG.debug(mobileDeviceCriteriaDto.toString());
		return mobileDeviceSearchService.filterMobileDevice(mobileDeviceCriteriaDto);
	}

}
