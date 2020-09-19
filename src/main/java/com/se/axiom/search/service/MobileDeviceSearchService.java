package com.se.axiom.search.service;

import com.se.axiom.search.custom.exception.MobileSerachException;
import com.se.axiom.search.dto.MobileDeviceCriteriaDto;
import com.se.axiom.search.model.MobileDevice;

import reactor.core.publisher.Flux;

public interface MobileDeviceSearchService {
	/**
	 * filter the mobile device based on the search criteria 
	 * Create Example to filter the repository 
	 * @param requestDto
	 * @return
	 * @throws MobileSerachException
	 */
	public Flux<MobileDevice> filterMobileDevice(MobileDeviceCriteriaDto requestDto)throws MobileSerachException;
	

}
