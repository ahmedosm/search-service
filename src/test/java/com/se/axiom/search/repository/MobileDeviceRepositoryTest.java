package com.se.axiom.search.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.se.axiom.search.model.Hardware;
import com.se.axiom.search.model.MobileDevice;
import com.se.axiom.search.model.Release;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class MobileDeviceRepositoryTest {
	private static final Logger LOG = LoggerFactory.getLogger(MobileDeviceRepositoryTest.class);

	private final MobileDeviceRepository mobileDeviceRepository;

	public MobileDeviceRepositoryTest(@Autowired MobileDeviceRepository mobileDeviceRepository) {

		this.mobileDeviceRepository = mobileDeviceRepository;
	}

	@BeforeEach
	void setUp() {
		Flux<MobileDevice> initData = mobileDeviceRepository.deleteAll()
				.thenMany(createMobileDeviceMockPrice().flatMap(mobileDeviceRepository::save))
				.thenMany(mobileDeviceRepository.findAll());
		StepVerifier.create(initData).expectNextCount(3).verifyComplete();
	}

		
	@Test
	public void testFindById() {
		LOG.info("find by Id !!!!");
		MobileDevice mobileDevice = new MobileDevice();
		mobileDevice.setId(24989l);
		Example<MobileDevice> example = Example.of(mobileDevice,
				ExampleMatcher.matchingAll().withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase());
		Flux<MobileDevice> flux = (Flux<MobileDevice>) mobileDeviceRepository.findAll(example);
		StepVerifier.create(flux).expectNextCount(1).verifyComplete();
	}
	
	@Test
	public void testFindByPrice() {
		LOG.info("find by price !!!!");
		MobileDevice mobileDevice = new MobileDevice();
		Release release = new Release();
		release.setPriceEur(200);
		mobileDevice.setRelease(release);
		Example<MobileDevice> example = Example.of(mobileDevice,
				ExampleMatcher.matchingAll().withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase());
		Flux<MobileDevice> flux = (Flux<MobileDevice>) mobileDeviceRepository.findAll(example);
		StepVerifier.create(flux).expectNextCount(2).verifyComplete();
	}
	
	@Test
	public void testFindByAnnounceDate() {
		LOG.info("find by AnnounceDate !!!!");
		MobileDevice mobileDevice = new MobileDevice();
		Release release = new Release();
		release.setAnnounceDate("2018");
		mobileDevice.setRelease(release);
		Example<MobileDevice> example = Example.of(mobileDevice,
				ExampleMatcher.matchingAll().withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase());
		Flux<MobileDevice> flux = (Flux<MobileDevice>) mobileDeviceRepository.findAll(example);
		StepVerifier.create(flux).expectNextCount(1).verifyComplete();
	}
	

	private  Flux<MobileDevice> createMobileDeviceMockPrice() {

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
		
		
		device = new MobileDevice();
		device.setId(20551l);
		device.setBrand("Apple");
		device.setPhone("Apple iPad 2 CDMA");
		device.setPicture("https://cdn2.gsmarena.com/vv/bigpic/apple-ipad2-new.jpg");
		device.setSim("Mini-SIM");
		hardware = new Hardware();
		hardware.setAudioJack("Yes");
		hardware.setGps("Yes with A-GPS");
		hardware.setBattery("Li-Po 6930 mAh battery (25 Wh)");
		device.setHardware(hardware);
		release = new Release();
		release.setAnnounceDate("2012 March");
		release.setPriceEur(200);
		device.setRelease(release);
		list.add(device);

		return Flux.fromIterable(list);

	}

}
