package com.iec.services;

import com.iec.entity.Activity;
import com.iec.entity.History;
import com.iec.repository.ActivityRepository;
import com.iec.repository.HistoryRepository;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public List<History> getAllHistory() {
        // TODO Auto-generated method stub
        return historyRepository.findAll();
    }

    @Override
    public History save(History history) {
        
        return historyRepository.save(history);
    }

    @Override
    public ResponseEntity<History> updateHistory(History history, String id) {

        Optional<History> historyDB = findHistoryById(id);
        if(historyDB.isPresent()) {
            History historyUpdate = historyDB.get();
            historyUpdate.set_id(id);
            historyUpdate.setDateTime(history.getDateTime());
            //TO-DO check for UPDATE-COMPOSE values
            historyUpdate.setType(history.getType());

            BasicDBObject changes = new BasicDBObject();
            changes.put("fieldName", history.getChanges().get("fieldName"));
            changes.put("oldValue", history.getChanges().get("oldValue"));
            changes.put("newValue", history.getChanges().get("newValue"));
            historyUpdate.setChanges(changes);

            historyRepository.save(historyUpdate);
            return new ResponseEntity<>(historyRepository.save(historyUpdate), HttpStatus.OK);
        }else {
//		// TODO Auto-generated method stub
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Optional<History> findHistoryById(String id) {
        // TODO Auto-generated method stub
        return historyRepository.findById(id);
    }

    @Override
    public void deleteHistory(String id) {
        // TODO Auto-generated method stub
        historyRepository.deleteById(id);
    }

}
