package com.xassure.batch.repository;

import com.xassure.batch.model.LocatorStrategy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface LocatorRepository extends MongoRepository<LocatorStrategy, String> {
}
