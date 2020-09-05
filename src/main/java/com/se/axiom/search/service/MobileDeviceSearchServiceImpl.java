package com.se.axiom.search.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.se.axiom.search.custom.exception.CustomError;
import com.se.axiom.search.custom.exception.MobileSerachException;
import com.se.axiom.search.dto.MobileDeviceCriteriaDto;
import com.se.axiom.search.model.MobileDevice;
import com.se.axiom.search.repository.MobileDeviceRepository;

import reactor.core.publisher.Flux;

@Service
public class MobileDeviceSearchServiceImpl implements MobileDeviceSearchService {
	private static final Logger LOG = LoggerFactory.getLogger(MobileDeviceSearchServiceImpl.class);
	@Autowired
	MobileDeviceRepository mobileDeviceRepository;
	@Autowired
	MobileDeviceSearchHelper searchHelper;

	public Flux<MobileDevice> filterMobileDevice(MobileDeviceCriteriaDto requestDto) throws MobileSerachException {
		try {
		MobileDevice mobileDevice=searchHelper.mapMobileDeviceSearchRequest(requestDto);
		LOG.info(mobileDevice.toString());
		Example<MobileDevice> example = Example.of(mobileDevice,
				ExampleMatcher.matchingAll().withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase());
		return mobileDeviceRepository.findAll(example);
		}catch (Exception e) {
			throw new MobileSerachException(CustomError.MOBILESERACH.getMessage(),CustomError.MOBILESERACH.getCode());
		}
	}
}
