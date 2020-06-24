package com.iec.entity;

import com.mongodb.BasicDBObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "History")
public class History {

    @Id
    private String _id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateTime;
    private String type;
    private BasicDBObject changes;
    
    

    public History(Date dateTime, String type, BasicDBObject changes) {
		this.dateTime = dateTime;
		this.type = type;
		this.changes = changes;
	}
    
    public History() {
		
	}

	public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BasicDBObject getChanges() {
        return changes;
    }

    public void setChanges(BasicDBObject changes) {
        this.changes = changes;
    }

}
