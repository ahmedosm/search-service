package com.se.axiom.search.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.se.axiom.search.model.MobileDevice;


public interface MobileDeviceRepository extends ReactiveMongoRepository<MobileDevice, Long>{ 

}
