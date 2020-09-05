package com.se.axiom.search.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.se.axiom.search.custom.exception.MobileSerachException;
import com.se.axiom.search.dto.MobileDeviceCriteriaDto;
import com.se.axiom.search.model.Hardware;
import com.se.axiom.search.model.MobileDevice;
import com.se.axiom.search.model.Release;
import com.se.axiom.search.service.MobileDeviceSearchService;

import reactor.core.publisher.Flux;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest(MobileSearchController.class)
public class MobileSearchControllerTest {

	@MockBean
	MobileDeviceSearchService mobileDeviceSearchService;

	@Autowired
	private WebTestClient webClient;

	@Test
	public void testFilterDeviceByPriceEur() throws MobileSerachException {
		MobileDeviceCriteriaDto requestDTO = new MobileDeviceCriteriaDto();
		requestDTO.setId(24989l);
		Mockito.when(mobileDeviceSearchService.filterMobileDevice(requestDTO))
				.thenReturn(createMobileDeviceMockPrice());
		webClient.get().uri("/mobile/search?id=24989").header(HttpHeaders.ACCEPT, "application/json").exchange()
				.expectStatus().isOk().expectBodyList(MobileDevice.class);
		Mockito.verify(mobileDeviceSearchService, times(1)).filterMobileDevice(requestDTO);
	}

	@Test
	public void testFilterDeviceById() throws MobileSerachException {
		MobileDeviceCriteriaDto requestDTO = new MobileDeviceCriteriaDto();
		requestDTO.setPriceEur(200);
		Mockito.when(mobileDeviceSearchService.filterMobileDevice(requestDTO)).thenReturn(createMobileDeviceMockId());
		webClient.get().uri("/mobile/search?priceEur=200").header(HttpHeaders.ACCEPT, "application/json").exchange()
				.expectStatus().isOk().expectBody().jsonPath("$[0].id").isNotEmpty().jsonPath("$[0].id").isEqualTo(24989);
		Mockito.verify(mobileDeviceSearchService, times(1)).filterMobileDevice(requestDTO);
	}

	private Flux<MobileDevice> createMobileDeviceMockPrice() {

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

	private Flux<MobileDevice> createMobileDeviceMockId() {

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

		return Flux.fromIterable(list);

	}

}
