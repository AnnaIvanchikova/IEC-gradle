package com.iec.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iec.entity.Activity;
import com.iec.entity.History;
import com.iec.repository.ActivityRepository;
import com.mongodb.BasicDBObject;

@Service
public class ActivityServiceImpl implements ActivityService{
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private HistoryServiceImpl historyServiceImpl;
	
	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	
	
	private HistoryInterface check = (newField, oldField, type, fieldName) -> {
        if(!newField.equals(oldField)) {
        	BasicDBObject changes = new BasicDBObject();
            changes.put("fieldName", fieldName);
            changes.put("oldValue", newField);
            changes.put("newValue", oldField);
        	History history = new History(getCurrentDate(),
        			type, changes);
        	historyServiceImpl.save(history);
        }
            
    };
    
    
    private Date getCurrentDate() {
    	 Calendar calobj = Calendar.getInstance();
    	 try {
			Date currentDate = df.parse(df.format(calobj.getTime()));
			return  currentDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return null;
    }
   
	@Override
	public List<Activity> getAllActivity() {
		return activityRepository.findAll();
	}

	@Override
	public Activity save(Activity activity) {
		check.checkAndAddInHistory(activity.getDescription(), null, "COMPOSE", "description");
		check.checkAndAddInHistory(activity.getEndDateTime(), null, "COMPOSE", "endDateTime");
		check.checkAndAddInHistory(activity.getInfo(), null, "COMPOSE", "info");
		check.checkAndAddInHistory(activity.getStartDateTime(), null, "COMPOSE", "startDateTime");
		check.checkAndAddInHistory(activity.getSummary(), null, "COMPOSE", "summary");
		check.checkAndAddInHistory(activity.getTitle(), null, "COMPOSE", "title");
		return activityRepository.save(activity);
	}

	
	public ResponseEntity<Activity> updateActivity(Activity activity, String id) {
		Optional<Activity> activityDB = findActivityById(id);
		if(activityDB.isPresent()) {
			Activity activityUpdate = activityDB.get();
			activityUpdate.set_id(id);
			check.checkAndAddInHistory(activityUpdate.getDescription(), activity.getDescription(), "UPDATE", "description");
			activityUpdate.setDescription(activity.getDescription());
			check.checkAndAddInHistory(activityUpdate.getEndDateTime(), activity.getEndDateTime(), "UPDATE", "endDateTime");
			activityUpdate.setEndDateTime(activity.getEndDateTime());
			check.checkAndAddInHistory(activityUpdate.getInfo(), activity.getInfo(), "UPDATE", "info");
			activityUpdate.setInfo(activity.getInfo());
			check.checkAndAddInHistory(activityUpdate.getStartDateTime(), activity.getStartDateTime(), "UPDATE", "startDateTime");
			activityUpdate.setStartDateTime(activity.getStartDateTime());
			check.checkAndAddInHistory(activityUpdate.getSummary(), activity.getSummary(), "UPDATE", "summary");
			activityUpdate.setSummary(activity.getSummary());
			check.checkAndAddInHistory(activityUpdate.getTitle(), activity.getTitle(), "UPDATE", "title");
			activityUpdate.setTitle(activity.getTitle());
			activityRepository.save(activityUpdate);
			return new ResponseEntity<>(activityRepository.save(activityUpdate), HttpStatus.OK);
		}else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Optional<Activity> findActivityById(String id) {
		
		return activityRepository.findById(id);
	}

	@Override
	public void deleteActivity(String id) {
	
		activityRepository.deleteById(id);
	}
	
	

}


