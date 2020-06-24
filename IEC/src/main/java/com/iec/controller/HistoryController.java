package com.iec.controller;

import com.iec.entity.Activity;
import com.iec.entity.History;
import com.iec.exception.IncorrectValueException;
import com.iec.services.ActivityServiceImpl;
import com.iec.services.HistoryServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private HistoryServiceImpl historyServiceImpl;

    @GetMapping(value = "/all")
    public List<History> getAllHistory(){
        return historyServiceImpl.getAllHistory();
    }


    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public History addHistory(@RequestBody History history) throws IncorrectValueException {
    	if (!(history.getType().equals("UPDATE") || history.getType().equals("COMPOSE"))) {
    	    throw new IncorrectValueException("Incorrect value entered for field type " +
    	            "in History Collection: " +  history.getType());
    	}
        return historyServiceImpl.save(history);
    }

    @PutMapping("/{id}")
    public ResponseEntity<History> saveOrUpdateActivity(@PathVariable String id, @RequestBody History history) {
        return historyServiceImpl.updateHistory(history, id);
    }

    @DeleteMapping("/{id}")
    public void deleteHisotry(@PathVariable String id) {
        historyServiceImpl.deleteHistory(id);
    }

}
