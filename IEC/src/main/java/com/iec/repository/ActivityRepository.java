package com.iec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.iec.entity.Activity;

public interface ActivityRepository extends MongoRepository<Activity, String>{
	
	

}
