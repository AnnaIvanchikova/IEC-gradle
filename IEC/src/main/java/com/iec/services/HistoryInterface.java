package com.iec.services;

import com.iec.entity.Activity;

@FunctionalInterface
public interface  HistoryInterface<T> {
	 
	public void checkAndAddInHistory(T newField, T oldField, String type, String fieldName) ;

}
