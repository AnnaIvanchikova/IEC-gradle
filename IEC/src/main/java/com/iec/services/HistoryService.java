package com.iec.services;

import org.springframework.http.ResponseEntity;

import com.iec.entity.Activity;
import com.iec.entity.History;

import java.util.List;
import java.util.Optional;

public interface HistoryService {

    List<History> getAllHistory();

    History save(History history);

    ResponseEntity<History> updateHistory(History history, String id);

    Optional<History> findHistoryById(String id);

    void deleteHistory(String id);
}
