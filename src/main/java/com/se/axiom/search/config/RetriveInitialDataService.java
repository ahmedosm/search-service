package com.se.axiom.search.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.axiom.search.custom.exception.CustomError;
import com.se.axiom.search.custom.exception.DataLoadingException;
import com.se.axiom.search.custom.exception.IntegrationException;
import com.se.axiom.search.model.MobileDevice;
import com.se.axiom.search.repository.MobileDeviceRepository;

@Configuration
public class RetriveInitialDataService {

	private static final Logger LOG = LoggerFactory.getLogger(RetriveInitialDataService.class);

	private final ObjectMapper mapper;
	private final MobileDeviceRepository mobileDeviceRepository;
	@Autowired
	private ApplicationContext appContext;
	public RetriveInitialDataService(ObjectMapper mapper, MobileDeviceRepository mobileDeviceRepository) {
		this.mapper = mapper;
		this.mobileDeviceRepository = mobileDeviceRepository;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Value("${mobile.device.service.url}")
	private String serviceUrl;

	@Value("${mobile.data.file.name}")
	private String fileName;

	@Bean
	CommandLineRunner loadInitialData() {
		try {
			mobileDeviceRepository.deleteAll().subscribe();
			List<MobileDevice> mobileList = loadMobileData();
			if (mobileList != null && !mobileList.isEmpty()) {
				mobileDeviceRepository.saveAll(mobileList).subscribe();
			} else {

			}

		} catch (DataLoadingException e) {
			e.printStackTrace();
			// SpringApplication.exit(appContext, () -> 0);
			// System.exit(0);
		}
		return null;
	}

	/**
	 * load mobile device data from stored file as an initial data
	 * 
	 * @return
	 * @throws DataLoadingException
	 */
	public List<MobileDevice> loadMobileData() throws DataLoadingException {
		List<MobileDevice> mobileList = null;
		try {
			mobileList = retriveMobileData();
		} catch (IntegrationException e) {
			try {
				LOG.info("load data from static json");
				mobileList = mapper.readValue(new ClassPathResource(fileName).getFile(),
						mapper.getTypeFactory().constructCollectionType(List.class, MobileDevice.class));

			} catch (Exception e1) {
				LOG.error("Error while loading json file  " + e1.getMessage());
				throw new DataLoadingException(CustomError.INITIALDATA.getMessage(), CustomError.INITIALDATA.getCode());
			}
		}
		return mobileList;

	}

	/**
	 * Retrieve mobiledevice data from Thirdparty service
	 * 
	 * @return
	 * @throws IntegrationException
	 */

	private List<MobileDevice> retriveMobileData() throws IntegrationException {
		List<MobileDevice> mobileList = null;
		try {
			mobileList = mapper.readValue(restTemplate().getForEntity(serviceUrl, String.class).getBody(),
					mapper.getTypeFactory().constructCollectionType(List.class, MobileDevice.class));
		} catch (Exception e) {
			LOG.error("Error while retrive data " + e.getMessage());
			throw new IntegrationException(CustomError.INTEGRATION.getMessage(), CustomError.INTEGRATION.getCode());
		}
		return mobileList;
	}

}
