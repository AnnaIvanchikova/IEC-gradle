package com.iec.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.iec.entity.History;

public interface HistoryRepository extends MongoRepository<History, String> {
}
