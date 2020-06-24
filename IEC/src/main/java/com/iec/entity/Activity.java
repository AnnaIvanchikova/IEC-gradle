package com.iec.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "Activity")
public class Activity {

	@Id
	private String _id;
	private String title;
	private String summary;
	private String description;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date startDateTime;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date endDateTime;
	private String info;

	public Activity(String _id, String title, String summary, String description, Date startDateTime, Date endDateTime,
			String info) {

		this._id = _id;
		this.title = title;
		this.summary = summary;
		this.description = description;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.info = info;
	}

	public Activity() {

	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Activity [_id=" + _id + ", title=" + title + ", summary=" + summary + ", description=" + description
				+ ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime + ", info=" + info + "]";
	}

}
