package com.se.axiom.search.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.se.axiom.search.custom.exception.MobileSerachException;
import com.se.axiom.search.dto.MobileDeviceCriteriaDto;
import com.se.axiom.search.model.Hardware;
import com.se.axiom.search.model.MobileDevice;
import com.se.axiom.search.model.Release;
import com.se.axiom.search.repository.MobileDeviceRepository;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class MobileDeviceSearchServiceTest {

	private static final Logger LOG = LoggerFactory.getLogger(MobileDeviceSearchServiceTest.class);
	@InjectMocks
	private MobileDeviceSearchServiceImpl mobileDeviceSearchService;
	@Mock
	private MobileDeviceSearchHelper mobileDeviceSearchHelper;
	@Mock
	private MobileDeviceRepository mobileDeviceRepository;

	@Test
	@SuppressWarnings("unchecked")
	public void testServiceFindAll() throws MobileSerachException {
		Mockito.when(mobileDeviceRepository.findAll(any(Example.class))).thenReturn(createMobileDeviceMockPrice());
		Mockito.when(mobileDeviceSearchHelper.mapMobileDeviceSearchRequest(any(MobileDeviceCriteriaDto.class)))
				.thenReturn(new MobileDevice());
		Flux<MobileDevice> initData = mobileDeviceSearchService.filterMobileDevice(new MobileDeviceCriteriaDto());
		StepVerifier.create(initData).expectNextCount(2).verifyComplete();
	}

	private static Flux<MobileDevice> createMobileDeviceMockPrice() {

		List<MobileDevice> list = new ArrayList<>();
		MobileDevice device = new MobileDevice();
		Hardware hardware = new Hardware();
		Release release = new Release();
		// device 24989l

		device.setId(24989l);
		device.setBrand("Apple");
		device.setPhone("Apple iPhone XS");
		device.setPicture("https://cdn2.gsmarena.com/vv/bigpic/apple-iphone-xs-new.jpg");
		device.setSim("Nano-SIM eSIM");
		hardware = new Hardware();
		hardware.setAudioJack("Np");
		hardware.setGps("Yes with A-GPS");
		hardware.setBattery("Li-Ion 2658 mAh battery (10.13 Wh)");
		device.setHardware(hardware);
		release = new Release();
		release.setAnnounceDate("2018 September");
		release.setPriceEur(1150);
		device.setRelease(release);
		list.add(device);
		// device 29709l

		device = new MobileDevice();
		device.setId(29709l);
		device.setBrand("Apple");
		device.setPhone("Apple iPad 2 Wi-Fi");
		device.setPicture("https://cdn2.gsmarena.com/vv/bigpic/apple-ipad2-new.jpg");
		device.setSim("No");
		hardware = new Hardware();
		hardware.setAudioJack("Yes");
		hardware.setGps("No");
		hardware.setBattery("Li-Po 6930 mAh battery (25 Wh)");
		device.setHardware(hardware);
		release = new Release();
		release.setAnnounceDate("2011 March");
		release.setPriceEur(200);
		device.setRelease(release);
		list.add(device);

		return Flux.fromIterable(list);

	}

}
