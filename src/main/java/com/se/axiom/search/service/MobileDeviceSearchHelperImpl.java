package com.se.axiom.search.service;

import org.springframework.stereotype.Component;

import com.se.axiom.search.dto.MobileDeviceCriteriaDto;
import com.se.axiom.search.model.Hardware;
import com.se.axiom.search.model.MobileDevice;
import com.se.axiom.search.model.Release;

@Component
public class MobileDeviceSearchHelperImpl implements MobileDeviceSearchHelper {

	public MobileDevice mapMobileDeviceSearchRequest(MobileDeviceCriteriaDto requestDto) {
		MobileDevice mobileDevice = new MobileDevice();
		if (requestDto != null) {
			mobileDevice.setId(requestDto.getId());
			mobileDevice.setBrand(requestDto.getBrand());
			mobileDevice.setPhone(requestDto.getPhone());
			mobileDevice.setPicture(requestDto.getPicture());
			mobileDevice.setResolution(requestDto.getResolution());
			mobileDevice.setSim(requestDto.getSim());
			if (requestDto.getAnnounceDate() != null || requestDto.getPriceEur() != null) {
				Release release = new Release();
				release.setAnnounceDate(requestDto.getAnnounceDate());
				release.setPriceEur(requestDto.getPriceEur());
				mobileDevice.setRelease(release);
			}

			if (requestDto.getAudioJack() != null || requestDto.getBattery() != null || requestDto.getGps() != null) {
				Hardware hardware = new Hardware();
				hardware.setAudioJack(requestDto.getAudioJack());
				hardware.setBattery(requestDto.getBattery());
				hardware.setGps(requestDto.getGps());
				mobileDevice.setHardware(hardware);
			}
		}
		return mobileDevice;
	}

}
