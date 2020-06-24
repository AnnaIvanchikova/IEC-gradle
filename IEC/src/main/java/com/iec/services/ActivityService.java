package com.iec.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.iec.entity.Activity;

public interface ActivityService {
	List<Activity> getAllActivity();
	
	Activity save(Activity activity);
		
	ResponseEntity<Activity> updateActivity(Activity activity, String id);
	
	Optional<Activity> findActivityById(String id);
	
	void deleteActivity(String id);

}
