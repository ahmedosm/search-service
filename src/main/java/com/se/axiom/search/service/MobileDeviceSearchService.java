package com.se.axiom.search.service;

import com.se.axiom.search.custom.exception.MobileSerachException;
import com.se.axiom.search.dto.MobileDeviceCriteriaDto;
import com.se.axiom.search.model.MobileDevice;

import reactor.core.publisher.Flux;

public interface MobileDeviceSearchService {
	public Flux<MobileDevice> filterMobileDevice(MobileDeviceCriteriaDto requestDto)throws MobileSerachException;
	

}
