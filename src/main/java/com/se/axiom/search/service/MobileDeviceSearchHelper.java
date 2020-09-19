package com.se.axiom.search.service;

import com.se.axiom.search.dto.MobileDeviceCriteriaDto;
import com.se.axiom.search.model.MobileDevice;

public interface MobileDeviceSearchHelper {
	/**
	 * Used to map the criteria DTO to Mobile Device object 
	 * @param requestDto
	 * @return MobileDevice
	 */
	public MobileDevice mapMobileDeviceSearchRequest(MobileDeviceCriteriaDto requestDto);
}
